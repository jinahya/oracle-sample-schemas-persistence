#!/bin/sh
#
# Cleans up any previous run, brings the database up, and follows its log until you press
# Ctrl-C, which tears the stack back down again. Both ends use `down -v`.
#
# NOTE: every volume in docker-compose.yml is a BIND MOUNT, and `down -v` only removes
#       named/anonymous volumes -- it never touches bind mounts. So ./opt/oracle/oradata
#       (the database itself) SURVIVES this script. That is usually what you want: the
#       schemas installed by opt/oracle/scripts/startup are still there next time, and the
#       startup hook skips reinstalling them. To actually start from an empty database,
#       remove that directory by hand:
#
#           rm -rf opt/oracle/oradata
#
set -eu

cd "$(dirname "$0")"

if docker compose version >/dev/null 2>&1; then
    compose() { docker compose "$@"; }
else
    compose() { docker-compose "$@"; }
fi

CONTAINER="oracle-sample-schemas"
DB_PASSWORD="${SAMPLE_SCHEMAS_PASSWORD:-password}"
DB_URL="//localhost:1521/freepdb1"
# GNU parallel also ships a `sql`, and on Homebrew it wins the PATH, so name Oracle's
# binary explicitly. Override SQLCL to point somewhere else.
SQLCL="${SQLCL:-sqlcl}"

# ---------------------------------------------------------------------------------------
# SH, installed from here with SQLcl
# ---------------------------------------------------------------------------------------
# Oracle's SH installer bulk-loads six of its tables -- COSTS, CUSTOMERS, PROMOTIONS,
# SALES, TIMES and SUPPLEMENTARY_DEMOGRAPHICS -- with SQLcl's `LOAD <table> <file>.csv`
# command, and sales_history/README.md says so outright: "Requires SQLcl command prompt!".
# SQL*Plus does not implement `LOAD`: it reports SP2-0158 on the preceding `SET LOAD`,
# skips the six statements, and, because SP2- is a client-side error rather than a SQL
# one, `WHENEVER SQLERROR EXIT` never fires and the install still claims success. That is
# why opt/oracle/scripts/startup/01_install_sample_schemas.sh, which drives sqlplus INSIDE
# the container, leaves those six empty.
#
# SQLcl is a CLIENT tool: `LOAD` reads the CSV on this machine and inserts over JDBC. So it
# belongs here, run against the published port, and the container needs neither SQLcl nor a
# JRE. Nothing to build, nothing to maintain.

wait_for_healthy() {
    waited=0
    while [ "${waited}" -lt 600 ]; do
        if [ "$(docker inspect -f '{{.State.Health.Status}}' "${CONTAINER}" 2>/dev/null)" \
             = "healthy" ]; then
            return 0
        fi
        sleep 2
        waited=$((waited + 2))
    done
    return 1
}

# `1` only when SH exists and SALES has at least one row; an absent schema makes the query
# fail, which is exactly the "not loaded" answer we want
sh_is_loaded() {
    printf 'set heading off feedback off pagesize 0\nselect count(*) from (select 1 from sh.sales where rownum = 1);\nexit\n' \
        | "${SQLCL}" -S "sys/${DB_PASSWORD}@${DB_URL} as sysdba" 2>/dev/null \
        | tr -d '[:space:]' | grep -qx '1'
}

install_sh() {
    if ! command -v "${SQLCL}" >/dev/null 2>&1; then
        echo "==> ${SQLCL} not found; SH's six CSV-backed tables will stay empty."
        echo "    install it (brew install --cask sqlcl) and re-run, or do it by hand:"
        echo "      cd db-sample-schemas/sales_history"
        echo "      ${SQLCL} 'sys/${DB_PASSWORD}@${DB_URL} as sysdba' @sh_install.sql"
        return 0
    fi

    echo "==> waiting for the database to report healthy ..."
    if ! wait_for_healthy; then
        echo "==> it never did; skipping the SH install"
        return 0
    fi

    if sh_is_loaded; then
        echo "==> SH is already installed and populated; skipping"
        return 0
    fi

    echo "==> installing SH with SQLcl (drops and rebuilds the schema; takes a few minutes) ..."
    # The LOAD lines name their CSVs relatively, so the installer has to run from the
    # directory holding them. It spools sh_install.log there, which the submodule's own
    # .gitignore already covers.
    #
    # The three ACCEPT prompts, in order: password, tablespace (empty = the database
    # default), overwrite. SQLcl's ACCEPT reads piped stdin, so unlike the sqlplus hook
    # inside the container this needs no pty.
    if ( cd db-sample-schemas/sales_history \
         && printf '%s\n\nYES\n' "${DB_PASSWORD}" \
            | "${SQLCL}" -S "sys/${DB_PASSWORD}@${DB_URL} as sysdba" @sh_install.sql ); then
        echo "==> SH installed"
    else
        echo "==> the SH install reported an error;" \
             "see db-sample-schemas/sales_history/sh_install.log"
    fi
}

logs_pid=""

on_interrupt() {
    # stop trapping, so a second Ctrl-C can still kill a slow teardown
    trap - INT TERM
    # guard, not `&&`: under `set -e` an empty logs_pid (Ctrl-C during startup, before the
    # log tail exists) would make the handler exit before it ever ran `down -v`
    if [ -n "${logs_pid}" ]; then
        kill "${logs_pid}" 2>/dev/null || true
    fi
    echo
    echo "==> down -v ..."
    compose down -v
    exit 0
}

trap on_interrupt INT TERM

# clear out whatever a previous run left behind -- a container killed without the trap
# firing would otherwise be reused by `up -d`
echo "==> down -v (cleaning up any previous run) ..."
compose down -v

echo "==> up -d ..."
compose up -d

install_sh

echo "==> following the log; Ctrl-C to bring it down"
echo
# `logs -f` runs in the background and the script waits on it: a foreground child would
# make the shell defer the trap until it exited, and `logs -f` never exits on its own.
compose logs -f &
logs_pid=$!
wait "${logs_pid}"
