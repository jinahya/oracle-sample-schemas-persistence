# The HR schema

## Schema

Every table and view of the schema, as the installed sample schema
([db-sample-schemas](https://github.com/oracle-samples/db-sample-schemas), the `db-sample-schemas` submodule) defines
it. Generated from the data dictionary of a database installed from it.

- **Check**: `NOT NULL`, `PK`, `UNIQUE`, `FK → TABLE.COLUMN`, single-column `CHECK` constraints, and `IDENTITY`
  columns. A `*` marks a column in a constraint over more than one column; those are listed under the table.
- **Description**: the column's comment, as the schema defines it.
- **Notes**: observations about the installed data.

### COUNTRIES

Table (index-organized). country table. References with locations table.

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `COUNTRY_ID` | `CHAR(2)` | NOT NULL, PK | Primary key of countries table. |  |
| `COUNTRY_NAME` | `VARCHAR2(60)` |  | Country name |  |
| `REGION_ID` | `NUMBER` | FK → REGIONS.REGION_ID | Region ID for the country. Foreign key to region_id column in the departments table. |  |

### DEPARTMENTS

Table. Departments table that shows details of departments where employees work. references with locations, employees,
and job_history tables.

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `DEPARTMENT_ID` | `NUMBER(4)` | NOT NULL, PK | Primary key column of departments table. |  |
| `DEPARTMENT_NAME` | `VARCHAR2(30)` | NOT NULL | A not null column that shows name of a department. Administration, Marketing, Purchasing, Human Resources, Shipping, IT, Executive, Public Relations, Sales, Finance, and Accounting. |  |
| `MANAGER_ID` | `NUMBER(6)` | FK → EMPLOYEES.EMPLOYEE_ID | Manager_id of a department. Foreign key to employee_id column of employees table. The manager_id column of the employee table references this column. |  |
| `LOCATION_ID` | `NUMBER(4)` | FK → LOCATIONS.LOCATION_ID | Location id where a department is located. Foreign key to location_id column of locations table. |  |

### EMPLOYEES

Table. employees table. References with departments, jobs, job_history tables. Contains a self reference.

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `EMPLOYEE_ID` | `NUMBER(6)` | NOT NULL, PK | Primary key of employees table. |  |
| `FIRST_NAME` | `VARCHAR2(20)` |  | First name of the employee. A not null column. | The comment says not null, but the column is nullable. No value is blank or padded with spaces. |
| `LAST_NAME` | `VARCHAR2(25)` | NOT NULL | Last name of the employee. A not null column. | No value is blank or padded with spaces. |
| `EMAIL` | `VARCHAR2(25)` | NOT NULL, UNIQUE | Email id of the employee | Only the local part of an address, e.g. `SKING`; no value contains an `@`. |
| `PHONE_NUMBER` | `VARCHAR2(20)` |  | Phone number of the employee; includes country code and area code | Groups of digits separated by dots, e.g. `1.515.555.0100`; every value matches `^\d+(\.\d+)*$`. |
| `HIRE_DATE` | `DATE` | NOT NULL | Date when the employee started on this job. A not null column. | 7 of the 10 `JOB_HISTORY` rows, of 6 employees, end after the employee's `HIRE_DATE`, which contradicts the comment. |
| `JOB_ID` | `VARCHAR2(10)` | NOT NULL, FK → JOBS.JOB_ID | Current job of the employee; foreign key to job_id column of the jobs table. A not null column. |  |
| `SALARY` | `NUMBER(8,2)` | CHECK (salary > 0) | Monthly salary of the employee. Must be greater than zero (enforced by constraint emp_salary_min) |  |
| `COMMISSION_PCT` | `NUMBER(2,2)` |  | Commission percentage of the employee; Only employees in sales department eligible for commission percentage |  |
| `MANAGER_ID` | `NUMBER(6)` | FK → EMPLOYEES.EMPLOYEE_ID | Manager id of the employee; has same domain as manager_id in departments table. Foreign key to employee_id column of employees table. (useful for reflexive joins and CONNECT BY query) |  |
| `DEPARTMENT_ID` | `NUMBER(4)` | FK → DEPARTMENTS.DEPARTMENT_ID | Department id where employee works; foreign key to department_id column of the departments table |  |

### EMP_DETAILS_VIEW

View.

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `EMPLOYEE_ID` | `NUMBER(6)` | NOT NULL |  |  |
| `JOB_ID` | `VARCHAR2(10)` | NOT NULL |  |  |
| `MANAGER_ID` | `NUMBER(6)` |  |  |  |
| `DEPARTMENT_ID` | `NUMBER(4)` |  |  |  |
| `LOCATION_ID` | `NUMBER(4)` |  |  |  |
| `COUNTRY_ID` | `CHAR(2)` |  |  |  |
| `FIRST_NAME` | `VARCHAR2(20)` |  |  |  |
| `LAST_NAME` | `VARCHAR2(25)` | NOT NULL |  |  |
| `SALARY` | `NUMBER(8,2)` |  |  |  |
| `COMMISSION_PCT` | `NUMBER(2,2)` |  |  |  |
| `DEPARTMENT_NAME` | `VARCHAR2(30)` | NOT NULL |  |  |
| `JOB_TITLE` | `VARCHAR2(35)` | NOT NULL |  |  |
| `CITY` | `VARCHAR2(30)` | NOT NULL |  |  |
| `STATE_PROVINCE` | `VARCHAR2(25)` |  |  |  |
| `COUNTRY_NAME` | `VARCHAR2(60)` |  |  |  |
| `REGION_NAME` | `VARCHAR2(25)` |  |  |  |

### JOBS

Table. jobs table with job titles and salary ranges. References with employees and job_history table.

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `JOB_ID` | `VARCHAR2(10)` | NOT NULL, PK | Primary key of jobs table. |  |
| `JOB_TITLE` | `VARCHAR2(35)` | NOT NULL | A not null column that shows job title, e.g. AD_VP, FI_ACCOUNTANT |  |
| `MIN_SALARY` | `NUMBER(6)` |  | Minimum salary for a job title. |  |
| `MAX_SALARY` | `NUMBER(6)` |  | Maximum salary for a job title |  |

### JOB_HISTORY

Table. Table that stores job history of the employees. If an employee changes departments within the job or changes jobs
within the department, new rows get inserted into this table with old job information of the employee. Contains a
complex primary key: employee_id+start_date. References with jobs, employees, and departments tables.

Rows are inserted by the `UPDATE_JOB_HISTORY` trigger, `AFTER UPDATE OF job_id, department_id ON employees`, with the
employee's old `JOB_ID` and `DEPARTMENT_ID`, `START_DATE` = the old `HIRE_DATE`, and `END_DATE` = `SYSDATE`. The trigger
does not change `EMPLOYEES.HIRE_DATE`, so a second change for the same employee violates `JHIST_EMP_ID_ST_DATE_PK`.

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `EMPLOYEE_ID` | `NUMBER(6)` | NOT NULL, PK*, FK → EMPLOYEES.EMPLOYEE_ID | A not null column in the complex primary key employee_id+start_date. Foreign key to employee_id column of the employee table |  |
| `START_DATE` | `DATE` | NOT NULL, PK*, CHECK* | A not null column in the complex primary key employee_id+start_date. Must be less than the end_date of the job_history table. (enforced by constraint jhist_date_interval) | The trigger writes the employee's `HIRE_DATE` before the update. |
| `END_DATE` | `DATE` | NOT NULL, CHECK* | Last day of the employee in this job role. A not null column. Must be greater than the start_date of the job_history table. (enforced by constraint jhist_date_interval) | The trigger writes `SYSDATE`. No row ends in the future, and no employee's rows overlap. |
| `JOB_ID` | `VARCHAR2(10)` | NOT NULL, FK → JOBS.JOB_ID | Job role in which the employee worked in the past; foreign key to job_id column in the jobs table. A not null column. |  |
| `DEPARTMENT_ID` | `NUMBER(4)` | FK → DEPARTMENTS.DEPARTMENT_ID | Department id in which the employee worked in the past; foreign key to department_id column in the departments table |  |

\* Constraints over more than one column:

- `JHIST_EMP_ID_ST_DATE_PK`: PRIMARY KEY (EMPLOYEE_ID, START_DATE)
- `JHIST_DATE_INTERVAL`: CHECK (end_date > start_date)

### LOCATIONS

Table. Locations table that contains specific address of a specific office, warehouse, and/or production site of a
company. Does not store addresses / locations of customers. references with the departments and countries tables.

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `LOCATION_ID` | `NUMBER(4)` | NOT NULL, PK | Primary key of locations table |  |
| `STREET_ADDRESS` | `VARCHAR2(40)` |  | Street address of an office, warehouse, or production site of a company. Contains building number and street name |  |
| `POSTAL_CODE` | `VARCHAR2(12)` |  | Postal code of the location of an office, warehouse, or production site of a company. |  |
| `CITY` | `VARCHAR2(30)` | NOT NULL | A not null column that shows city where an office, warehouse, or production site of a company is located. |  |
| `STATE_PROVINCE` | `VARCHAR2(25)` |  | State or Province where an office, warehouse, or production site of a company is located. |  |
| `COUNTRY_ID` | `CHAR(2)` | FK → COUNTRIES.COUNTRY_ID | Country where an office, warehouse, or production site of a company is located. Foreign key to country_id column of the countries table. |  |

### REGIONS

Table. Regions table that contains region numbers and names. references with the Countries table.

| Name | Type | Check | Description | Notes |
| --- | --- | --- | --- | --- |
| `REGION_ID` | `NUMBER` | NOT NULL, PK | Primary key of regions table. |  |
| `REGION_NAME` | `VARCHAR2(25)` |  | Names of regions. Locations are in the countries of these regions. |  |
