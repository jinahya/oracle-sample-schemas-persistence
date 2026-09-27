# oracle-sample-schemas-persistence

Persistence units for the [Oracle Database Sample Schemas](https://github.com/oracle-samples/db-sample-schemas).

## Submodule

The schema SQL itself lives in the
[oracle-samples/db-sample-schemas](https://github.com/oracle-samples/db-sample-schemas)
submodule. Init it once, and once only.

```shell
$ git submodule init
```

Update it, whenever possible, for the up-to-date data.

```shell
$ git submodule update --remote

$ ls -l db-sample-schemas
total NN
........................................... HH:mm .
........................................... HH:mm ..
........................................... HH:mm .git
...
........................................... HH:mm sh_install.log
```

## Preparing the database

### Prerequisite: SQLcl

Install [SQLcl](https://www.oracle.com/database/sqldeveloper/technologies/sqlcl/) before you
start. Upstream requires it -- `sales_history/README.md` says so outright, *"Requires SQLcl
command prompt!"* -- because `sh_populate.sql` bulk-loads six of the `SH` tables with
SQLcl's `LOAD <table> <file>.csv` command, which SQL\*Plus does not implement.

```shell
$ brew install --cask sqlcl
$ sqlcl -V
```

SQLcl is a **client** tool: `LOAD` reads the CSV on your machine and inserts over JDBC. So
it runs on the host, against the published port, and the container needs neither SQLcl nor
a JRE -- there is no image to build or maintain.

> Call it as `sqlcl`, not `sql`. GNU parallel ships a `sql` of its own, and on Homebrew it
> wins the `PATH`. Set `SQLCL=/path/to/sql` if yours lives elsewhere.

### Bringing it up

Use the wrapper, not `docker-compose up -d` directly:

```shell
$ ./docker-compose-up.sh
==> down -v (cleaning up any previous run) ...
==> up -d ...
==> waiting for the database to report healthy ...
==> installing SH with SQLcl (drops and rebuilds the schema; takes a few minutes) ...
==> SH installed
==> following the log; Ctrl-C to bring it down

...
DATABASE IS READY TO USE!
sample-schemas: installing CO ...
sample-schemas: CO installed
sample-schemas: installing HR ...
sample-schemas: HR installed
sample-schemas: creating the dmlonly user ...
sample-schemas: dmlonly created
```

It does three things plain `up -d` cannot: it waits for the container to report healthy,
installs `SH` through SQLcl, and brings the stack back down on Ctrl-C. `CO` and `HR` come
from the container's own startup hook,
`opt/oracle/scripts/startup/01_install_sample_schemas.sh`, which drives `sqlplus` inside the
image; only `SH` needs SQLcl, and only because of `LOAD`.

Everything uses the password `password`.

Installing takes a few minutes the first time. On later starts both the hook and the wrapper
find the schemas already populated and skip them, so the container comes up quickly:

```shell
==> SH is already installed and populated; skipping
sample-schemas: CO already installed, skipping
```

If `sqlcl` is not on the `PATH`, the wrapper says so and carries on rather than failing --
you get a working database with those six `SH` tables empty, and the command to finish it.

### Notes

The database lives in `./opt/oracle/oradata`, a **bind mount**, so it outlives
`docker-compose down` -- even `down -v`, which only removes named and anonymous volumes.
That is usually what you want. To start from an empty database, delete it yourself:

```shell
$ docker-compose down
$ rm -rf opt/oracle/oradata
```

`SH` installs only partially under SQL\*Plus, which is why `docker-compose-up.sh` reinstalls
it with SQLcl. `sh_populate.sql` bulk-loads `costs`, `customers`, `promotions`, `sales`,
`times` and `supplementary_demographics` with `LOAD`; SQL\*Plus reports `SP2-0158` on the
preceding `SET LOAD` and skips all six. That is a client-side error, not a SQL one, so
`WHENEVER SQLERROR EXIT` never fires and the install still reports success -- the tables are
silently empty. All six are mapped by this project, so it matters.

Oracle's installer leaves `CAL_MONTH_SALES_MV` and `FWEEK_PSCAT_SALES_MV` **empty** even on
a correct run: `sh_create.sql` builds both over an empty `SALES` and `sh_populate.sql` never
refreshes them. Neither is listed in a persistence unit. Refresh them yourself if you need
them:

```sql
BEGIN DBMS_MVIEW.REFRESH('SH.CAL_MONTH_SALES_MV,SH.FWEEK_PSCAT_SALES_MV', 'CC'); END;
/
```

The hook takes a few environment variables: `SAMPLE_SCHEMAS_PASSWORD`,
`SAMPLE_SCHEMAS_PDB`, `SAMPLE_SCHEMAS_ROOT`, and `SAMPLE_SCHEMAS_OVERWRITE=true` to force a
reinstall -- which **drops the existing schemas and their data**.

To install by hand instead, see [README_BAK.md](README_BAK.md).

## JDBC URLs

| Schema | Host      | Port | Service  | Driver | User | Password   | JDBC URL                                    |
|--------|-----------|------|----------|--------|------|------------|---------------------------------------------|
| CO     | localhost | 1521 | freepdb1 | THIN   | `co` | `password` | jdbc:oracle:thin:@//localhost:1521/freepdb1 |
| HR     |           |      |          |        | `hr` | `password` | jdbc:oracle:thin:@//localhost:1521/freepdb1 |
| SH     |           |      |          |        | `sh` | `password` | jdbc:oracle:thin:@//localhost:1521/freepdb1 |

## Jakarta EE alignment

These are pure Jakarta Persistence modules: no persistence helper libraries, and every
entity is a standalone class rather than a `@MappedSuperclass` hierarchy.

Jakarta EE 11 is the only supported platform generation. The spec versions come from
`jakarta.platform:jakarta.jakartaee-bom`; the implementations are pinned to releases
certified for that generation.

| Spec | Version | Implementation | Version |
| --- | --- | --- | --- |
| Jakarta Persistence | 3.2 | Hibernate ORM | 7.4.9.Final (also tested on 7.2.25.Final) |
| Jakarta Persistence | 3.2 | EclipseLink | 5.0.1 |
| Jakarta Validation | 3.1 | Hibernate Validator | 9.1.3.Final (also tested on 9.0.1.Final) |
| Jakarta Expression Language | 6.0 | Expressly | 6.0.0 |
| Jakarta CDI | 4.1 | Weld (weld-junit5) | 5.0.3.Final |

Run the build against every supported combination:

```shell
$ ./_mvn_jakarta_ee_11.sh test
```

Main sources target Java 21; tests target Java 25, so building the tests needs a
JDK 25 or newer.

## Oracle

### [SQL Language Reference](https://docs.oracle.com/en/database/oracle/oracle-database/23/sqlrf/)

#### [2 Basic Elements of Oracle SQL](https://docs.oracle.com/en/database/oracle/oracle-database/23/sqlrf/Basic-Elements-of-Oracle-SQL.html)

##### [Data Types](https://docs.oracle.com/en/database/oracle/oracle-database/23/sqlrf/Data-Types.html)

* [Oracle Built-in Data Types](https://docs.oracle.com/en/database/oracle/oracle-database/23/sqlrf/Data-Types.html#GUID-7B72E154-677A-4342-A1EA-C74C1EA928E6)

### Identity (auto-increment) columns are backed by a sequence

Oracle's auto-increment is the ANSI SQL `IDENTITY` column, and Oracle implements it
with **a sequence generator owned by the column** — that is what the `CO` schema uses
(`GENERATED BY DEFAULT ON NULL AS IDENTITY`) and what
`@GeneratedValue(strategy = GenerationType.IDENTITY)` maps onto here.

> Citations below point at `…/oracle-database/26/…` (Oracle AI Database 26ai), the
> current release; the `…/23/…` URLs used above now redirect (HTTP 302) to it.

#### [CREATE TABLE](https://docs.oracle.com/en/database/oracle/oracle-database/26/sqlrf/CREATE-TABLE.html)

##### [`identity_clause`](https://docs.oracle.com/en/database/oracle/oracle-database/26/sqlrf/CREATE-TABLE.html#GUID-F9CE0CC3-13AE-4744-A43C-EAC7A71AAAB6__GUID-A33A72C5-3F47-48BA-B32D-29FA8008B78F)

> Use this clause to specify an identity column. The identity column will be assigned an
> increasing or decreasing integer value from a sequence generator for each subsequent
> `INSERT` statement. You can use the `identity_options` clause to configure the sequence
> generator.

> To create an identity column in a schema other than your own, you must have the
> `CREATE ANY TABLE`, `CREATE ANY SEQUENCE`, and `SELECT ANY SEQUENCE` system privileges.

`ALWAYS`

> If you specify `ALWAYS`, then the database always uses the sequence generator to assign a
> value to the column. If you attempt to explicitly assign a value to the column using
> `INSERT` or `UPDATE`, then an error will be returned. This is the default.

`BY DEFAULT`

> If you specify `BY DEFAULT`, then the database uses the sequence generator to assign a
> value to the column by default, but you can also explicitly assign a specified value to
> the column. If you specify `ON NULL`, then the database uses the sequence generator to
> assign a value to the column when a subsequent `INSERT` statement attempts to assign a
> value that evaluates to NULL.

`identity_options`

> Use the `identity_options` clause to configure the sequence generator. The
> `identity_options` clause has the same parameters as the `CREATE SEQUENCE` statement.
> Refer to `CREATE SEQUENCE` for a full description of these parameters and characteristics.
> The exception is `START WITH LIMIT VALUE`, which is specific to `identity_options` and can
> only be used with `ALTER TABLE MODIFY`.

> **Note:** When you create an identity column, Oracle recommends that you specify the
> `CACHE` clause with a value higher than the default of 20 to enhance performance.

Restrictions on Identity Columns

> * You can specify only one identity column per table.
> * If you specify `identity_clause`, then you must specify a numeric data type for
>   `datatype` in the `column_definition` clause. You cannot specify a user-defined data type.
> * If you specify `identity_clause`, then you cannot specify the `DEFAULT` clause in the
>   `column_definition` clause.
> * When you specify `identity_clause`, the `NOT NULL` constraint and `NOT DEFERRABLE`
>   constraint state are implicitly specified. […]
> * `CREATE TABLE AS SELECT` will not inherit the identity property on a column.

* [Creating a Table with an Identity Column: Examples](https://docs.oracle.com/en/database/oracle/oracle-database/26/sqlrf/CREATE-TABLE.html#GUID-F9CE0CC3-13AE-4744-A43C-EAC7A71AAAB6__CJAHCAFF)

#### [ALTER TABLE](https://docs.oracle.com/en/database/oracle/oracle-database/26/sqlrf/ALTER-TABLE.html)

##### [`identity_clause` (adding a column)](https://docs.oracle.com/en/database/oracle/oracle-database/26/sqlrf/ALTER-TABLE.html#GUID-552E7373-BF93-477D-9DA3-B2C9386F2877__GUID-541316FE-E79B-41A3-95F0-E02198D20E52)

> When you add a new identity column to a table, all existing rows are updated using the
> sequence generator. The order in which a value is assigned to each existing row is
> nondeterministic.

##### [`modify_col_properties`](https://docs.oracle.com/en/database/oracle/oracle-database/26/sqlrf/ALTER-TABLE.html#GUID-552E7373-BF93-477D-9DA3-B2C9386F2877__GUID-3CA0C46E-FD90-4BE1-B08D-C150CD290005)

`START WITH LIMIT VALUE` — this is what `co_populate.sql` runs after loading the rows:

> `START WITH LIMIT VALUE`, which is specific to `identity_options`, can only be used with
> `ALTER TABLE MODIFY`. If you specify `START WITH LIMIT VALUE`, then the database locks the
> table and finds the maximum identity column value in the table (for increasing sequences)
> or the minimum identity column value (for decreasing sequences) and assigns the value as
> the sequence generator's high water mark. The next value returned by the sequence generator
> will be the high water mark + `INCREMENT BY` integer for increasing sequences, or the high
> water mark - `INCREMENT BY` integer for decreasing sequences.

`DROP IDENTITY` — the sequence belongs to the column, and goes away with it:

> Use this clause to remove the identity property from a column, including the sequence
> generator and `NOT NULL` and `NOT DEFERRABLE` constraints. Identity column values in
> existing rows are not affected.

#### [CREATE SEQUENCE](https://docs.oracle.com/en/database/oracle/oracle-database/26/sqlrf/CREATE-SEQUENCE.html)

The statement whose parameters `identity_options` reuses verbatim.

##### [Purpose](https://docs.oracle.com/en/database/oracle/oracle-database/26/sqlrf/CREATE-SEQUENCE.html#GUID-E9C78A8C-615A-4757-B2A8-5E6EFB130571__GUID-B6CD0DB8-00B2-45F6-99FB-2E94999C616C)

> A sequence is a database object used to produce unique integers, which are commonly used
> to populate a synthetic primary key column in a table.

> When a sequence number is generated, the sequence is incremented, independent of the
> transaction committing or rolling back. If two users concurrently increment the same
> sequence, then the sequence numbers each user acquires may have gaps, because sequence
> numbers are being generated by the other user.

##### [`CACHE`](https://docs.oracle.com/en/database/oracle/oracle-database/26/sqlrf/CREATE-SEQUENCE.html#GUID-E9C78A8C-615A-4757-B2A8-5E6EFB130571__GUID-7E390BE1-2F6C-4E5A-9D5C-5A2567D636FB)

> If a system failure occurs, then all cached sequence values that have not been used in
> committed DML statements are lost. The potential number of lost values is equal to the
> value of the `CACHE` parameter.

Identity values are therefore **unique, not gapless** — do not treat a generated
`CUSTOMER_ID` / `ORDER_ID` as a row count.

### [Database Reference](https://docs.oracle.com/en/database/oracle/oracle-database/26/refrn/)

#### [`ALL_TAB_IDENTITY_COLS`](https://docs.oracle.com/en/database/oracle/oracle-database/26/refrn/ALL_TAB_IDENTITY_COLS.html)

The dictionary view that names the sequence behind each identity column — the most direct
evidence that identity is sequence-backed:

| Column | Datatype | NULL | Description |
| --- | --- | --- | --- |
| `GENERATION_TYPE` | `VARCHAR2(10)` | | Generation type of the identity column. Possible values are `ALWAYS` or `BY DEFAULT`. |
| `SEQUENCE_NAME` | `VARCHAR2(128)` | `NOT NULL` | Name of the sequence associated with the identity column |
| `IDENTITY_OPTIONS` | `VARCHAR2(298)` | | Options for the identity column sequence generator |

```sql
SELECT table_name, column_name, generation_type, sequence_name, identity_options
  FROM user_tab_identity_cols
 ORDER BY table_name;
```

* [`USER_TAB_IDENTITY_COLS`](https://docs.oracle.com/en/database/oracle/oracle-database/26/refrn/USER_TAB_IDENTITY_COLS.html)
* [`ALL_SEQUENCES`](https://docs.oracle.com/en/database/oracle/oracle-database/26/refrn/ALL_SEQUENCES.html) — the generated sequence itself shows up here

### [Database Error Messages](https://docs.oracle.com/en/error-help/db/)

* [ORA-32795: cannot insert into a generated always identity column](https://docs.oracle.com/en/error-help/db/ora-32795/)

  > **Cause:** An attempt was made to insert a value into an identity column created with
  > `GENERATED ALWAYS` keywords.
  >
  > **Action:** A generated always identity column cannot be directly inserted. Instead, the
  > associated sequence generator must provide the value.

* [ORA-32794: cannot drop a system-generated sequence](https://docs.oracle.com/en/error-help/db/ora-32794/)

  > **Cause:** An attempt was made to drop a system-generated sequence.
  >
  > **Action:** A system-generated sequence, such as one created for an identity column,
  > cannot be dropped.

### [Oracle Database 12c Release 1 (12.1.0.1) New Features](https://docs.oracle.com/database/121/NEWFT/chapter12101.htm)

#### [2.1.6.3 IDENTITY Columns](https://docs.oracle.com/database/121/NEWFT/chapter12101.htm#NEWFT158)

Where auto-increment entered the database — before 12.1 the equivalent was a sequence plus
a `BEFORE INSERT` trigger:

> Table columns have been enhanced to support the American National Standards Institute
> (ANSI) SQL keyword `IDENTITY`.
>
> This provides a standards based approach to the declaration of automatically incrementing
> columns simplifying application development and making the migration of DDL to Oracle
> simpler.

### What this means in this repository

* The `CO` schema declares every surrogate key as
  `INTEGER GENERATED BY DEFAULT ON NULL AS IDENTITY`
  (`db-sample-schemas/customer_orders/co_create.sql:64`), so an explicit value *is*
  accepted — `ORA-32795` does not apply — and a `NULL` still draws from the sequence.
* `db-sample-schemas/customer_orders/co_populate.sql:8948` re-seeds each generator with
  `ALTER TABLE … MODIFY … GENERATED BY DEFAULT ON NULL AS IDENTITY (START WITH LIMIT VALUE)`
  after the literal `INSERT`s, per the `ALTER TABLE` clause quoted above.
* The entities therefore map these keys with
  `@GeneratedValue(strategy = GenerationType.IDENTITY)`, not `SEQUENCE` — the sequence
  exists, but it is the column's, unnamed in the mapping and not independently addressable
  (`ORA-32794`).

## EclipseLink

Activated by `-P__eclipselink-5.0-jakarta-ee-11`, which swaps the provider class, the
metamodel annotation processor and the runtime jar in one move:

| | |
| --- | --- |
| Version | 5.0.1 (`version.org.eclipse.persistence`) |
| Provider | `org.eclipse.persistence.jpa.PersistenceProvider` |
| Metamodel processor | `org.eclipse.persistence:org.eclipse.persistence.jpa.modelgen.processor` |

* [EclipseLink 5.0 release](https://eclipse.dev/eclipselink/releases/5.0.html) —
  Jakarta Persistence 3.2 / Jakarta EE 11
* [All releases](https://eclipse.dev/eclipselink/releases/index.html) ·
  [GitHub releases](https://github.com/eclipse-ee4j/eclipselink/releases)
* [Documentation Center](https://eclipse.dev/eclipselink/documentation/)
* [JPA Extensions Reference — persistence unit properties](https://eclipse.dev/eclipselink/documentation/4.0/jpa/extensions/persistenceproperties_ref.htm)
* [eclipse-ee4j/eclipselink](https://github.com/eclipse-ee4j/eclipselink)

### Things that bit us

**`insertable = false` on a `@GeneratedValue @Id`.** EclipseLink treats such a mapping as
read-only and fails descriptor initialisation with `EclipseLink-46` / `EclipseLink-41`
(verified on 5.0.1). Every identity key in `CO` therefore spells out `insertable = true` —
the JPA default — instead of leaving it off or setting it to `false`:

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true, updatable = false)
private Long customerId;
```

**The metamodel processor needs a `persistence.xml`,** and the only one lives in test
resources. A reference to a generated metamodel class (`Employee_`, `JobHistoryId_`, …)
from `src/main` therefore breaks this profile. Main sources use the entity's own
`ATTRIBUTE_NAME_*` constant instead — `@OneToMany(mappedBy = Employee.ATTRIBUTE_NAME_JOB)`.

**H2 identifier case.** The in-memory test URL carries
`;database_to_upper=false;MODE=LEGACY` because of
[eclipselink#1393](https://github.com/eclipse-ee4j/eclipselink/issues/1393).

Test-time properties in `src/test/resources/META-INF/persistence.xml`:
`eclipselink.target-database=…H2Platform`, `eclipselink.ddl-generation=create-tables`,
`eclipselink.logging.level.sql=FINE`, `eclipselink.logging.parameters=true`. Static
weaving is wired through `de.empulse.eclipselink:staticweave-maven-plugin` and is off by
default (`eclipselink.woven=false`).

## Hibernate

The default provider — no `-P` needed — pinned by `version.org.hibernate.orm`:

| | |
| --- | --- |
| Versions | 7.4.9.Final (`-P__hibernate-orm-7.4-jakarta-ee-11`), 7.2.25.Final (`-P__hibernate-orm-7.2-jakarta-ee-11`) |
| Provider | `org.hibernate.jpa.HibernatePersistenceProvider` |
| Metamodel processor | `org.hibernate.orm:hibernate-jpamodelgen` |
| Validator | 9.1.3.Final / 9.0.1.Final (`-P___hibernate-validator-9.{1,0}-jakarta-ee-11`), each with Expressly 6 |

* [ORM releases](https://hibernate.org/orm/releases/) ·
  [7.4](https://hibernate.org/orm/releases/7.4/) ·
  [7.2](https://hibernate.org/orm/releases/7.2/) ·
  [Validator releases](https://hibernate.org/validator/releases/)
* [Hibernate ORM User Guide](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html)
  * [3.2.41. XML mapping](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#basic-mapping-xml)
* [A Guide to Hibernate Query Language](https://docs.hibernate.org/orm/current/querylanguage/html_single/)
  * [1.4. Type system](https://docs.hibernate.org/orm/current/querylanguage/html_single/#type-system)

    > Going further, an expression like `local datetime - document.created` is assigned the
    > Java type `java.time.Duration`, a type which doesn't appear anywhere in the JPA
    > specification.
* [Hibernate Javadoc](https://docs.jboss.org/hibernate/orm/current/javadocs/)
* [Hibernate Validator reference (9.1)](https://docs.jboss.org/hibernate/validator/9.1/reference/en-US/html_single/)

### [3.7.10. Using IDENTITY columns](https://docs.jboss.org/hibernate/orm/7.4/userguide/html_single/Hibernate_User_Guide.html#identifiers-generators-identity)

What `@GeneratedValue(strategy = GenerationType.IDENTITY)` costs against the Oracle
identity columns documented above:

> It is important to realize that using IDENTITY columns imposes a runtime behavior where
> the entity row must be physically inserted prior to the identifier value being known.

> There is yet another important runtime impact of choosing IDENTITY generation: Hibernate
> will not be able to batch `INSERT` statements for the entities using the IDENTITY
> generation.

Hibernate reads the generated key back through `Statement#getGeneratedKeys` when the JDBC
environment supports it, falling back to the dialect's `INSERT … RETURNING` syntax or to a
separate identity `SELECT`.

Test-time properties: `hibernate.hbm2ddl.auto=create-drop`, `hibernate.show_sql`,
`hibernate.format_sql`, `hibernate.highlight_sql`, `hibernate.use_sql_comment`.

## Links

### oracle.com

* [SQL Developer](https://www.oracle.com/database/sqldeveloper/)

### github.com

#### [oracle-samples/db-sample-schemas](https://github.com/oracle-samples/db-sample-schemas)

* [ORA-65096: common user or role name must start with prefix C## #27](https://github.com/oracle-samples/db-sample-schemas/issues/27)
* [Populate customer_orders/STORES/logo data #30](https://github.com/oracle-samples/db-sample-schemas/issues/30)
* [What are relationships ORDER_ITEMS.SHIPMENTS.CUSTOMER and SHIPMENTS.CUSTOMER? #31](https://github.com/oracle-samples/db-sample-schemas/issues/31)
* [issues/34 Add more checks for HR.JOB](https://github.com/oracle-samples/db-sample-schemas/issues/34)
    * [Fix \[#34\] #35](https://github.com/oracle-samples/db-sample-schemas/pull/35)

#### spring-projects/spring-data-jpa

* [Wrong column value bound for transitive self-referencing parent #3850](https://github.com/spring-projects/spring-data-jpa/issues/3850)

#### microsoft/mssql-docker

* [Create a simpler way to customize the setup/provide startup/initialisation scripts (like de-facto standard /docker-entrypoint-initdb.d)](https://github.com/microsoft/mssql-docker/issues/928)

### stackoverflow.com

* [How to have docker compose init a SQL Server database](https://stackoverflow.com/questions/69941444/how-to-have-docker-compose-init-a-sql-server-database)

### iancarpenter.dev

* [Getting started with Oracle Database running on Docker](https://iancarpenter.dev/2023/09/13/getting-started-with-oracle-database-running-on-docker/)

### www.baeldung.com

* [How to Serialize and Deserialize java.sql.Blob With Jackson](https://www.baeldung.com/java-sql-blob-jackson-serialize-deserialize)
