# Installing by hand

Superseded by `_docker-compose-up.sh` and the startup hook -- see
[README.md](README.md), where all of this happens for you. Kept here for when you want to
re-run a single schema, watch an installer's prompts, or work against a database the hook
did not set up.

Connect to the running container.

```shell
$ docker exec -it oracle-sample-schemas sh
```

```shell
sh-x.y$ cd /db-sample-schemas
```

## Installing the schemas

Install the `CO` schema.

```shell
sh-x.y$ sqlplus / as sysdba
SQL> alter session set container=freepdb1;
SQL> @customer_orders/co_install.sql
...
Enter a password for the user CO: password

Enter a tablespace for CO [SYSTEM]: <Enter>
Do you want to overwrite the schema, if it already exists? (Y) [N]: <Enter>
...

sh-x.y$ 
```

Install the `HR` schema.

```shell
sh-x.y$ sqlplus / as sysdba
SQL> alter session set container=freepdb1;
SQL> @human_resources/hr_install.sql
...
Enter a password for the user HR: password

Enter a tablespace for HR [SYSTEM]: <Enter>
Do you want to overwrite the schema, if it already exists? (Y) [N]: <Enter>
...

sh-x.y$ 
```

Install the `SH` schema.

```shell
sh-x.y$ sqlplus / as sysdba
SQL> alter session set container=freepdb1;
SQL> @sales_history/sh_install.sql
...
Enter a password for the user SH: password

Enter a tablespace for SH [SYSTEM]: <Enter>
Do you want to overwrite the schema, if it already exists? (Y) [N]: <Enter>
...

sh-x.y$ 
```

## Creating and granting the `dmlonly` user

A user with no destructive privileges, used by the integration tests. The startup hook
creates this for you; run it by hand only to re-apply the grants.

```shell
sh-x.y$ sqlplus / as sysdba
SQL> alter session set container=freepdb1;
SQL> DROP USER IF EXISTS dmlonly CASCADE;
SQL> CREATE USER dmlonly IDENTIFIED BY "password" DEFAULT TABLESPACE SYSTEM;
SQL> GRANT CREATE SESSION,
  2        SELECT ANY TABLE,
  3        INSERT ANY TABLE,
  4        UPDATE ANY TABLE,
  5        DELETE ANY TABLE
  6    TO dmlonly;
...
Grant succeeded.
SQL> quit
sh-x.y$ 
```
