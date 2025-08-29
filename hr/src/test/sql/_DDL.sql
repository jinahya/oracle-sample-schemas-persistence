create sequence LOCATIONS_SEQ
    increment by 100
    maxvalue 9900 nocache
/

create sequence DEPARTMENTS_SEQ
    increment by 10
    maxvalue 9990 nocache
/

create sequence EMPLOYEES_SEQ nocache
/

create table REGIONS
(
    REGION_ID   NUMBER not null
        constraint REG_ID_PK
            primary key
        constraint REGION_ID_NN
            check ("REGION_ID" IS NOT NULL),
    REGION_NAME VARCHAR2(25)
) /

comment on table REGIONS is 'Regions table that contains region numbers and names. references with the Countries table.'
/

comment on column REGIONS.REGION_ID is 'Primary key of regions table.'
/

comment on column REGIONS.REGION_NAME is 'Names of regions. Locations are in the countries of these regions.'
/

create table COUNTRIES
(
    COUNTRY_ID   CHAR(2) not null
        constraint COUNTRY_C_ID_PK
            primary key
        constraint COUNTRY_ID_NN
            check ("COUNTRY_ID" IS NOT NULL),
    COUNTRY_NAME VARCHAR2(60),
    REGION_ID    NUMBER
        constraint COUNTR_REG_FK
            references REGIONS
) organization index
/

comment on table COUNTRIES is 'country table. References with locations table.'
/

comment on column COUNTRIES.COUNTRY_ID is 'Primary key of countries table.'
/

comment on column COUNTRIES.COUNTRY_NAME is 'Country name'
/

comment on column COUNTRIES.REGION_ID is 'Region ID for the country. Foreign key to region_id column in the departments table.'
/

create table LOCATIONS
(
    LOCATION_ID    NUMBER(4)    not null
        constraint LOC_ID_PK
            primary key,
    STREET_ADDRESS VARCHAR2(40),
    POSTAL_CODE    VARCHAR2(12),
    CITY           VARCHAR2(30) not null
        constraint LOC_CITY_NN
            check ("CITY" IS NOT NULL),
    STATE_PROVINCE VARCHAR2(25),
    COUNTRY_ID     CHAR(2)
        constraint LOC_C_ID_FK
            references COUNTRIES
) /

comment on table LOCATIONS is 'Locations table that contains specific address of a specific office,
warehouse, and/or production site of a company. Does not store addresses /
locations of customers. references with the departments and countries tables. '
/

comment on column LOCATIONS.LOCATION_ID is 'Primary key of locations table'
/

comment on column LOCATIONS.STREET_ADDRESS is 'Street address of an office, warehouse, or production site of a company.
Contains building number and street name'
/

comment on column LOCATIONS.POSTAL_CODE is 'Postal code of the location of an office, warehouse, or production site
of a company. '
/

comment on column LOCATIONS.CITY is 'A not null column that shows city where an office, warehouse, or
production site of a company is located. '
/

comment on column LOCATIONS.STATE_PROVINCE is 'State or Province where an office, warehouse, or production site of a
company is located.'
/

comment on column LOCATIONS.COUNTRY_ID is 'Country where an office, warehouse, or production site of a company is
located. Foreign key to country_id column of the countries table.'
/

create index LOC_CITY_IX
    on LOCATIONS (CITY)
    /

create index LOC_STATE_PROVINCE_IX
    on LOCATIONS (STATE_PROVINCE)
    /

create index LOC_COUNTRY_IX
    on LOCATIONS (COUNTRY_ID)
    /

create table DEPARTMENTS
(
    DEPARTMENT_ID   NUMBER(4)    not null
        constraint DEPT_ID_PK
            primary key,
    DEPARTMENT_NAME VARCHAR2(30) not null
        constraint DEPT_NAME_NN
            check ("DEPARTMENT_NAME" IS NOT NULL),
    MANAGER_ID      NUMBER(6),
    LOCATION_ID     NUMBER(4)
        constraint DEPT_LOC_FK
            references LOCATIONS
) /

comment on table DEPARTMENTS is 'Departments table that shows details of departments where employees
work. references with locations, employees, and job_history tables.'
/

comment on column DEPARTMENTS.DEPARTMENT_ID is 'Primary key column of departments table.'
/

comment on column DEPARTMENTS.DEPARTMENT_NAME is 'A not null column that shows name of a department. Administration,
Marketing, Purchasing, Human Resources, Shipping, IT, Executive, Public
Relations, Sales, Finance, and Accounting. '
/

comment on column DEPARTMENTS.MANAGER_ID is 'Manager_id of a department. Foreign key to employee_id column of employees table. The manager_id column of the employee table references this column.'
/

comment on column DEPARTMENTS.LOCATION_ID is 'Location id where a department is located. Foreign key to location_id column of locations table.'
/

create index DEPT_LOCATION_IX
    on DEPARTMENTS (LOCATION_ID)
    /

create table JOBS
(
    JOB_ID     VARCHAR2(10) not null
        constraint JOB_ID_PK
            primary key,
    JOB_TITLE  VARCHAR2(35) not null
        constraint JOB_TITLE_NN
            check ("JOB_TITLE" IS NOT NULL),
    MIN_SALARY NUMBER(6),
    MAX_SALARY NUMBER(6)
) /

comment on table JOBS is 'jobs table with job titles and salary ranges.
References with employees and job_history table.'
/

comment on column JOBS.JOB_ID is 'Primary key of jobs table.'
/

comment on column JOBS.JOB_TITLE is 'A not null column that shows job title, e.g. AD_VP, FI_ACCOUNTANT'
/

comment on column JOBS.MIN_SALARY is 'Minimum salary for a job title.'
/

comment on column JOBS.MAX_SALARY is 'Maximum salary for a job title'
/

create table EMPLOYEES
(
    EMPLOYEE_ID    NUMBER(6)    not null
        constraint EMP_EMP_ID_PK
            primary key,
    FIRST_NAME     VARCHAR2(20),
    LAST_NAME      VARCHAR2(25) not null
        constraint EMP_LAST_NAME_NN
            check ("LAST_NAME" IS NOT NULL),
    EMAIL          VARCHAR2(25) not null
        constraint EMP_EMAIL_UK
            unique
        constraint EMP_EMAIL_NN
            check ("EMAIL" IS NOT NULL),
    PHONE_NUMBER   VARCHAR2(20),
    HIRE_DATE      DATE not null
        constraint EMP_HIRE_DATE_NN
            check ("HIRE_DATE" IS NOT NULL),
    JOB_ID         VARCHAR2(10) not null
        constraint EMP_JOB_FK
            references JOBS
        constraint EMP_JOB_NN
            check ("JOB_ID" IS NOT NULL),
    SALARY         NUMBER(8, 2)
        constraint EMP_SALARY_MIN
            check (salary > 0),
    COMMISSION_PCT NUMBER(2, 2),
    MANAGER_ID     NUMBER(6)
        constraint EMP_MANAGER_FK
            references EMPLOYEES,
    DEPARTMENT_ID  NUMBER(4)
        constraint EMP_DEPT_FK
            references DEPARTMENTS
) /

comment on table EMPLOYEES is 'employees table. References with departments,
jobs, job_history tables. Contains a self reference.'
/

comment on column EMPLOYEES.EMPLOYEE_ID is 'Primary key of employees table.'
/

comment on column EMPLOYEES.FIRST_NAME is 'First name of the employee. A not null column.'
/

comment on column EMPLOYEES.LAST_NAME is 'Last name of the employee. A not null column.'
/

comment on column EMPLOYEES.EMAIL is 'Email id of the employee'
/

comment on column EMPLOYEES.PHONE_NUMBER is 'Phone number of the employee; includes country code and area code'
/

comment on column EMPLOYEES.HIRE_DATE is 'Date when the employee started on this job. A not null column.'
/

comment on column EMPLOYEES.JOB_ID is 'Current job of the employee; foreign key to job_id column of the
jobs table. A not null column.'
/

comment on column EMPLOYEES.SALARY is 'Monthly salary of the employee. Must be greater
than zero (enforced by constraint emp_salary_min)'
/

comment on column EMPLOYEES.COMMISSION_PCT is 'Commission percentage of the employee; Only employees in sales
department eligible for commission percentage'
/

comment on column EMPLOYEES.MANAGER_ID is 'Manager id of the employee; has same domain as manager_id in
departments table. Foreign key to employee_id column of employees table.
(useful for reflexive joins and CONNECT BY query)'
/

comment on column EMPLOYEES.DEPARTMENT_ID is 'Department id where employee works; foreign key to department_id
column of the departments table'
/

alter table DEPARTMENTS
    add constraint DEPT_MGR_FK
        foreign key (MANAGER_ID) references EMPLOYEES /

create index EMP_DEPARTMENT_IX
    on EMPLOYEES (DEPARTMENT_ID)
    /

create index EMP_JOB_IX
    on EMPLOYEES (JOB_ID)
    /

create index EMP_MANAGER_IX
    on EMPLOYEES (MANAGER_ID)
    /

create index EMP_NAME_IX
    on EMPLOYEES (LAST_NAME, FIRST_NAME)
    /

create trigger SECURE_EMPLOYEES
    before insert or
update or
delete
on EMPLOYEES
BEGIN
    secure_dml;
END secure_employees;
/

create trigger UPDATE_JOB_HISTORY
    after update of JOB_ID,DEPARTMENT_ID
    on EMPLOYEES
    for each row
BEGIN
    add_job_history(:old.employee_id, :old.hire_date, sysdate,
                    :old.job_id, :old.department_id);
END;
/

create table JOB_HISTORY
(
    EMPLOYEE_ID   NUMBER(6)    not null
        constraint JHIST_EMP_FK
            references EMPLOYEES
        constraint JHIST_EMPLOYEE_NN
            check ("EMPLOYEE_ID" IS NOT NULL),
    START_DATE    DATE not null
        constraint JHIST_START_DATE_NN
            check ("START_DATE" IS NOT NULL),
    END_DATE      DATE not null
        constraint JHIST_END_DATE_NN
            check ("END_DATE" IS NOT NULL),
    JOB_ID        VARCHAR2(10) not null
        constraint JHIST_JOB_FK
            references JOBS
        constraint JHIST_JOB_NN
            check ("JOB_ID" IS NOT NULL),
    DEPARTMENT_ID NUMBER(4)
        constraint JHIST_DEPT_FK
            references DEPARTMENTS,
    constraint JHIST_EMP_ID_ST_DATE_PK
        primary key (EMPLOYEE_ID, START_DATE),
    constraint JHIST_DATE_INTERVAL
        check (end_date > start_date)
) /

comment on table JOB_HISTORY is 'Table that stores job history of the employees. If an employee
changes departments within the job or changes jobs within the department,
new rows get inserted into this table with old job information of the
employee. Contains a complex primary key: employee_id+start_date.
References with jobs, employees, and departments tables.'
/

comment on column JOB_HISTORY.EMPLOYEE_ID is 'A not null column in the complex primary key employee_id+start_date.
Foreign key to employee_id column of the employee table'
/

comment on column JOB_HISTORY.START_DATE is 'A not null column in the complex primary key employee_id+start_date.
Must be less than the end_date of the job_history table. (enforced by
constraint jhist_date_interval)'
/

comment on column JOB_HISTORY.END_DATE is 'Last day of the employee in this job role. A not null column. Must be
greater than the start_date of the job_history table.
(enforced by constraint jhist_date_interval)'
/

comment on column JOB_HISTORY.JOB_ID is 'Job role in which the employee worked in the past; foreign key to
job_id column in the jobs table. A not null column.'
/

comment on column JOB_HISTORY.DEPARTMENT_ID is 'Department id in which the employee worked in the past; foreign key to department_id column in the departments table'
/

create index JHIST_JOB_IX
    on JOB_HISTORY (JOB_ID)
    /

create index JHIST_EMPLOYEE_IX
    on JOB_HISTORY (EMPLOYEE_ID)
    /

create index JHIST_DEPARTMENT_IX
    on JOB_HISTORY (DEPARTMENT_ID)
    /

create view EMP_DETAILS_VIEW as
SELECT e.employee_id,
       e.job_id,
       e.manager_id,
       e.department_id,
       d.location_id,
       l.country_id,
       e.first_name,
       e.last_name,
       e.salary,
       e.commission_pct,
       d.department_name,
       j.job_title,
       l.city,
       l.state_province,
       c.country_name,
       r.region_name
FROM employees e,
     departments d,
     jobs j,
     locations l,
     countries c,
     regions r
WHERE e.department_id = d.department_id
  AND d.location_id = l.location_id
  AND l.country_id = c.country_id
  AND c.region_id = r.region_id
  AND j.job_id = e.job_id
WITH READ ONLY
        /

create PROCEDURE secure_dml
    IS
BEGIN
    IF
TO_CHAR (SYSDATE, 'HH24:MI') NOT BETWEEN '08:00' AND '18:00'
        OR TO_CHAR (SYSDATE, 'DY') IN ('SAT', 'SUN') THEN
        RAISE_APPLICATION_ERROR (-20205,
                                 'You may only make changes during normal office hours');
END IF;
END secure_dml;
/

create PROCEDURE add_job_history(p_emp_id job_history.employee_id% type
, p_start_date job_history.start_date% type
, p_end_date job_history.end_date% type
, p_job_id job_history.job_id% type
, p_department_id job_history.department_id% type
)
    IS
BEGIN
INSERT INTO job_history (employee_id, start_date, end_date,
                         job_id, department_id)
VALUES (p_emp_id, p_start_date, p_end_date, p_job_id, p_department_id);
END add_job_history;
/
