#!/bin/bash
#
# Installs the Oracle sample schemas (CO, HR, SH) into the PDB, once, and creates the
# `dmlonly` user the integration tests connect as.
#
# The official image runs everything in /opt/oracle/scripts/startup on EVERY container
# start, and this project bind-mounts /opt/oracle/oradata, so the database survives a
# restart. Each installer would happily DROP USER ... CASCADE and rebuild, so this script
# checks for the schema first and skips it when already present. Set
# SAMPLE_SCHEMAS_OVERWRITE=true to force a reinstall (destroys existing data).
#
# Oracle documents no non-interactive install: the installers ask for the password with
# `ACCEPT pass ... HIDE`, which reads the terminal rather than stdin, so piping answers
# straight into sqlplus leaves the password empty (ORA-20999). Running sqlplus under a
# pty via script(1) makes the prompts consume piped input as a human would.
#
# NOTE: runUserScripts.sh SOURCES this file ('. "$f"'), so it must never call `exit` --
#       that would abort the image's entrypoint -- and must not leak `set -e`.

__install_sample_schemas() {
    local pdb="${SAMPLE_SCHEMAS_PDB:-freepdb1}"
    local password="${SAMPLE_SCHEMAS_PASSWORD:-password}"
    local root="${SAMPLE_SCHEMAS_ROOT:-/db-sample-schemas}"
    local overwrite="${SAMPLE_SCHEMAS_OVERWRITE:-false}"
    local workdir schema installer existing

    if [ ! -d "${root}" ]; then
        echo "sample-schemas: ${root} is not mounted; nothing to install"
        return 0
    fi

    # the installers SPOOL <schema>_install.log into the working directory
    workdir="$(mktemp -d)" || return 0

    for pair in \
        "CO:${root}/customer_orders/co_install.sql" \
        "HR:${root}/human_resources/hr_install.sql" \
        "SH:${root}/sales_history/sh_install.sql"
    do
        schema="${pair%%:*}"
        installer="${pair#*:}"

        if [ ! -r "${installer}" ]; then
            echo "sample-schemas: ${schema} skipped, no installer at ${installer}"
            continue
        fi

        existing="$(sqlplus -s / as sysdba <<EOSQL | tr -d '[:space:]'
set pagesize 0 feedback off verify off heading off echo off
alter session set container=${pdb};
select count(*) from all_users where username = '${schema}';
exit
EOSQL
)"

        if [ "${existing}" != "0" ] && [ "${overwrite}" != "true" ]; then
            echo "sample-schemas: ${schema} already installed, skipping"
            continue
        fi

        echo "sample-schemas: installing ${schema} ..."
        # answers, in prompt order: password, tablespace (empty = the default), overwrite
        ( cd "${workdir}" && printf '%s\n%s\n%s\n%s\n%s\n' \
            "alter session set container=${pdb};" \
            "@${installer}" \
            "${password}" \
            "" \
            "YES" \
          | script -q -c "sqlplus -s / as sysdba" /dev/null >"${workdir}/${schema}.out" 2>&1 )

        # SH's sh_populate.sql bulk-loads six tables with SQLcl's `LOAD` command, which
        # classic SQL*Plus does not implement -- it reports SP2-0158 on the preceding
        # `SET LOAD` and skips them. The image ships no SQLcl, so those tables stay empty
        # whether you run this hook or follow the manual steps in the README. Everything
        # populated by INSERT (SH.CHANNELS, SH.COUNTRIES and SH.PRODUCTS) loads correctly.
        # Call that out rather than failing the whole schema: SQLcl is a client tool, so
        # ../../../../docker-compose-up.sh re-runs sh_install.sql from the host, where
        # SQLcl lives, and that install is the one that fills all nine tables.
        if grep -q 'SP2-0158.*"LOAD"' "${workdir}/${schema}.out"; then
            echo "sample-schemas: ${schema} installed (partial: tables bulk-loaded via" \
                 "SQLcl 'LOAD' are empty -- _docker-compose-up.sh finishes the job)"
        fi
        if grep -qE 'ORA-[0-9]+' "${workdir}/${schema}.out"; then
            echo "sample-schemas: ${schema} FAILED"
            grep -E 'ORA-[0-9]+' "${workdir}/${schema}.out" | head -5
        elif ! grep -q 'SP2-0158.*"LOAD"' "${workdir}/${schema}.out"; then
            echo "sample-schemas: ${schema} installed"
        fi
    done

    # ---------------------------------------------------------------------------------
    # the `dmlonly` user the integration-test persistence units connect as
    # ---------------------------------------------------------------------------------
    # No prompts here, so plain sqlplus is enough -- no pty needed. DROP USER IF EXISTS
    # makes this re-runnable, and dmlonly owns no objects of its own (only ANY TABLE
    # system privileges), so re-creating it every start costs nothing and keeps the
    # grants correct. Inlined rather than read from a .sql file so that it honours
    # ${password} like the schemas do.
    echo "sample-schemas: creating the dmlonly user ..."
    sqlplus -s / as sysdba >"${workdir}/dmlonly.out" 2>&1 <<EOSQL
whenever sqlerror exit failure
alter session set container=${pdb};
DROP USER IF EXISTS dmlonly CASCADE;
CREATE USER dmlonly IDENTIFIED BY "${password}"
    DEFAULT TABLESPACE SYSTEM
;
GRANT
    CREATE SESSION,
    SELECT ANY TABLE,
    INSERT ANY TABLE,
    UPDATE ANY TABLE,
    DELETE ANY TABLE
    TO dmlonly
;
exit
EOSQL
    if grep -qE 'ORA-[0-9]+|SP2-[0-9]+' "${workdir}/dmlonly.out"; then
        echo "sample-schemas: dmlonly FAILED"
        grep -E 'ORA-[0-9]+|SP2-[0-9]+' "${workdir}/dmlonly.out" | head -5
    else
        echo "sample-schemas: dmlonly created"
    fi

    rm -rf "${workdir}"
    return 0
}

__install_sample_schemas
unset -f __install_sample_schemas
