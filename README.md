# oracle-sample-schemas-persistence

## Installing schemas

Init and update the submodule.

```shell
$ git submodule init
$ git submodule update --remote
$ ls -l db-sample-schemas
total NN
........................................... HH:mm .
........................................... HH:mm ..
........................................... HH:mm .git
...
........................................... HH:mm sh_install.log

```

Run docker container.

```shell
$ docker-compose up -d
```

(Wait some, and) connect to the container.

```shell
$ docker exec -it oracle-sample-schemas sh
```

```shell
$ cd /db-sample-schemas
```

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

### Creating and granting the `dmlonly` user

A user, with no destructive privileges, is required for running integration tests.

```shell
$ cd /db-sample-schemas-local

$ sqlplus / as sysdba

SQL> alter session set container=freepdb1;

SQL> @create_dmlonly_user.sql

...

Grant succeeded.

SQL> quit

$ 
```


## JDBC URLs

| Schema | Host      | Port | Service  | Driver | User | Password   | JDBC URL                                    |
|--------|-----------|------|----------|--------|------|------------|---------------------------------------------|
| CO     | localhost | 1521 | freepdb1 | THIN   | `co` | `password` | jdbc:oracle:thin:@//localhost:1521/freepdb1 |
| HR     |           |      |          |        | `hr` | `password` | jdbc:oracle:thin:@//localhost:1521/freepdb1 |
| SH     |           |      |          |        | `sh` | `password` | jdbc:oracle:thin:@//localhost:1521/freepdb1 |
