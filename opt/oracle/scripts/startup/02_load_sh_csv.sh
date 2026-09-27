#!/bin/bash
#
# Loads the six SH tables that Oracle's own installer leaves empty.
#
# sh_populate.sql bulk-loads COSTS, CUSTOMERS, PROMOTIONS, SALES, TIMES and
# SUPPLEMENTARY_DEMOGRAPHICS with SQLcl's `LOAD <table> <file>.csv` command. SQL*Plus does
# not implement it -- it reports `SP2-0158: unknown SET option "LOAD"` on the preceding
# `SET LOAD`, skips the six statements, and, because SP2- is a client-side error rather
# than a SQL one, `WHENEVER SQLERROR EXIT` never fires and the install still reports
# success. The `-lite` image ships neither SQLcl nor sqlldr, so 01_install_sample_schemas.sh
# can only note the gap. This script closes it.
#
# The CSVs are already visible to the server under ${root} (docker-compose bind-mounts
# ./db-sample-schemas), and the ORACLE_LOADER access driver lives inside the database, so
# an external table over each CSV loads them with nothing but SQL*Plus. Every header row
# matches its table's column order exactly, so each external table is shaped from the
# target's own ALL_TAB_COLUMNS rows and the driver needs no field list; only the dates need
# telling, and they are all YYYY-MM-DD.
#
# Runs after 01_install_sample_schemas.sh (hence 02_) and skips itself once SALES has rows.
# Set SH_CSV_RELOAD=true to empty the six tables and load them again.
#
# NOTE: runUserScripts.sh SOURCES this file ('. "$f"'), so it must never call `exit` --
#       that would abort the image's entrypoint -- and must not leak `set -e`.

__load_sh_csv() {
    local pdb="${SAMPLE_SCHEMAS_PDB:-freepdb1}"
    local root="${SAMPLE_SCHEMAS_ROOT:-/db-sample-schemas}"
    local reload="${SH_CSV_RELOAD:-false}"
    local csvdir="${root}/sales_history"
    local workdir installed loaded missing csv

    if [ ! -d "${csvdir}" ]; then
        echo "sh-csv: ${csvdir} is not mounted; nothing to load"
        return 0
    fi

    missing=""
    for csv in costs customers promotions sales times supplementary_demographics; do
        [ -r "${csvdir}/${csv}.csv" ] || missing="${missing} ${csv}.csv"
    done
    if [ -n "${missing}" ]; then
        echo "sh-csv: skipped, missing CSV file(s):${missing}"
        return 0
    fi

    installed="$(sqlplus -s / as sysdba <<EOSQL | tr -d '[:space:]'
set pagesize 0 feedback off verify off heading off echo off
alter session set container=${pdb};
select count(*) from all_users where username = 'SH';
exit
EOSQL
)"
    if [ "${installed}" = "0" ]; then
        echo "sh-csv: skipped, the SH schema is not installed"
        return 0
    fi

    loaded="$(sqlplus -s / as sysdba <<EOSQL | tr -d '[:space:]'
set pagesize 0 feedback off verify off heading off echo off
alter session set container=${pdb};
select count(*) from (select 1 from sh.sales where rownum = 1);
exit
EOSQL
)"
    if [ "${loaded}" != "0" ] && [ "${reload}" != "true" ]; then
        echo "sh-csv: SH.SALES already has rows, skipping"
        return 0
    fi

    workdir="$(mktemp -d)" || return 0
    echo "sh-csv: loading the six CSV-backed SH tables ..."

    sqlplus -s / as sysdba >"${workdir}/load.out" 2>&1 <<EOSQL
whenever sqlerror exit failure
alter session set container=${pdb};
set define off
-- the CSVs were written under this territory; it is also what sh_install.sql loads under
alter session set nls_language=American;
alter session set nls_territory=America;

CREATE OR REPLACE DIRECTORY sh_csv_dir AS '${csvdir}';
GRANT READ ON DIRECTORY sh_csv_dir TO sh;

DECLARE
   -- target table -> CSV file, in foreign-key order: TIMES and PROMOTIONS before COSTS and
   -- SALES, CUSTOMERS (which needs COUNTRIES, already populated by INSERT) before SALES.
   -- Loading in this order is what lets the constraints sh_populate.sql re-enabled stay
   -- enabled and actually check the data, instead of being disabled around the load.
   TYPE t_name_list IS TABLE OF VARCHAR2(128);
   l_tables    t_name_list := t_name_list(
      'TIMES', 'PROMOTIONS', 'CUSTOMERS', 'SUPPLEMENTARY_DEMOGRAPHICS', 'COSTS', 'SALES');
   l_files     t_name_list := t_name_list(
      'times.csv', 'promotions.csv', 'customers.csv', 'supplementary_demographics.csv',
      'costs.csv', 'sales.csv');
   l_columns   VARCHAR2(32767);
   l_rows      PLS_INTEGER;
   l_reload    BOOLEAN := FALSE;
   -- the foreign keys a reload takes off, so that exactly those go back on
   TYPE t_fk IS RECORD (table_name VARCHAR2(128), constraint_name VARCHAR2(128));
   TYPE t_fk_list IS TABLE OF t_fk INDEX BY PLS_INTEGER;
   l_fks       t_fk_list;
BEGIN
   FOR i IN 1 .. l_tables.COUNT LOOP
      EXECUTE IMMEDIATE
         'SELECT COUNT(*) FROM (SELECT 1 FROM sh.' || l_tables(i) || ' WHERE rownum = 1)'
         INTO l_rows;
      IF l_rows > 0 THEN
         l_reload := TRUE;
      END IF;
   END LOOP;

   IF l_reload THEN
      -- TRUNCATE on a table referenced by an enabled foreign key raises ORA-02266 even
      -- when the child is empty, so SH's foreign keys come off first and go straight back
      -- on -- VALIDATE is free here, every table they touch is empty by then. Only the
      -- ones actually taken off are put back, so a key someone disabled by hand stays so.
      SELECT table_name, constraint_name
        BULK COLLECT INTO l_fks
        FROM all_constraints
       WHERE owner = 'SH' AND constraint_type = 'R' AND status = 'ENABLED';

      FOR i IN 1 .. l_fks.COUNT LOOP
         EXECUTE IMMEDIATE 'ALTER TABLE sh.' || l_fks(i).table_name ||
                           ' MODIFY CONSTRAINT ' || l_fks(i).constraint_name ||
                           ' DISABLE NOVALIDATE';
      END LOOP;

      FOR i IN REVERSE 1 .. l_tables.COUNT LOOP
         EXECUTE IMMEDIATE 'TRUNCATE TABLE sh.' || l_tables(i);
      END LOOP;

      FOR i IN 1 .. l_fks.COUNT LOOP
         EXECUTE IMMEDIATE 'ALTER TABLE sh.' || l_fks(i).table_name ||
                           ' MODIFY CONSTRAINT ' || l_fks(i).constraint_name ||
                           ' ENABLE VALIDATE';
      END LOOP;
   END IF;

   FOR i IN 1 .. l_tables.COUNT LOOP
      -- ORACLE_LOADER does not accept the AS-subquery form of CREATE TABLE, so the
      -- external table's column list is rendered from the target's own dictionary rows.
      -- Same columns, same order, which is also the CSV's column order.
      SELECT LISTAGG(
                column_name || ' ' || data_type ||
                CASE
                   WHEN data_type IN ('VARCHAR2', 'CHAR') THEN '(' || char_length || ')'
                   WHEN data_type = 'NUMBER' AND data_precision IS NOT NULL
                      THEN '(' || data_precision || ',' || NVL(data_scale, 0) || ')'
                END,
                ', ') WITHIN GROUP (ORDER BY column_id)
        INTO l_columns
        FROM all_tab_columns
       WHERE owner = 'SH' AND table_name = l_tables(i);

      EXECUTE IMMEDIATE
         'CREATE TABLE sh.ext_' || l_tables(i) || ' (' || l_columns || ') ' ||
         'ORGANIZATION EXTERNAL ( ' ||
         '   TYPE ORACLE_LOADER ' ||
         '   DEFAULT DIRECTORY sh_csv_dir ' ||
         '   ACCESS PARAMETERS ( ' ||
         '      RECORDS DELIMITED BY NEWLINE ' ||
         '      SKIP 1 ' ||                             -- the quoted header row
         '      NOLOGFILE NOBADFILE NODISCARDFILE ' ||  -- never write to the bind mount
         '      FIELDS TERMINATED BY '','' OPTIONALLY ENCLOSED BY ''"'' LRTRIM ' ||
         '      MISSING FIELD VALUES ARE NULL ' ||
         '      DATE_FORMAT DATE MASK "YYYY-MM-DD" ' ||
         '   ) ' ||
         '   LOCATION (''' || l_files(i) || ''') ' ||
         ') REJECT LIMIT 0';                            -- a rejected row is a bug, not a warning

      EXECUTE IMMEDIATE
         'INSERT /*+ APPEND */ INTO sh.' || l_tables(i) ||
         ' SELECT * FROM sh.ext_' || l_tables(i);
      COMMIT;

      -- scaffolding: leaving these behind would put six tables in SH that Oracle's
      -- installer never created, which is the kind of drift the ITs read the schema to catch
      EXECUTE IMMEDIATE 'DROP TABLE sh.ext_' || l_tables(i);
   END LOOP;
END;
/

-- both were built over an empty SALES
BEGIN
   DBMS_MVIEW.REFRESH('SH.CAL_MONTH_SALES_MV,SH.FWEEK_PSCAT_SALES_MV', 'CC');
END;
/

BEGIN
   DBMS_STATS.GATHER_SCHEMA_STATS('SH');
END;
/
exit
EOSQL

    if grep -qE 'ORA-[0-9]+|SP2-[0-9]+|KUP-[0-9]+' "${workdir}/load.out"; then
        echo "sh-csv: FAILED"
        grep -E 'ORA-[0-9]+|SP2-[0-9]+|KUP-[0-9]+' "${workdir}/load.out" | head -5
    else
        echo "sh-csv: loaded"
        sqlplus -s / as sysdba <<EOSQL
set pagesize 50 feedback off verify off heading on echo off
alter session set container=${pdb};
column "Table" format a28
select 'costs' as "Table", 82112 as "provided", count(1) as "actual" from sh.costs
union all select 'customers', 55500, count(1) from sh.customers
union all select 'promotions', 503, count(1) from sh.promotions
union all select 'sales', 918843, count(1) from sh.sales
union all select 'times', 1826, count(1) from sh.times
union all select 'supplementary_demographics', 4500, count(1)
   from sh.supplementary_demographics;
exit
EOSQL
    fi

    rm -rf "${workdir}"
    return 0
}

__load_sh_csv
unset -f __load_sh_csv
