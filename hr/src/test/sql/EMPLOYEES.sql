--
-- create table HR.EMPLOYEES
-- (
--     EMPLOYEE_ID    NUMBER(6)    not null
--         constraint EMP_EMP_ID_PK
--             primary key,
--     FIRST_NAME     VARCHAR2(20),
--     LAST_NAME      VARCHAR2(25) not null
--         constraint EMP_LAST_NAME_NN
--             check ("LAST_NAME" IS NOT NULL),
--     EMAIL          VARCHAR2(25) not null
--         constraint EMP_EMAIL_UK
--             unique
--         constraint EMP_EMAIL_NN
--             check ("EMAIL" IS NOT NULL),
--     PHONE_NUMBER   VARCHAR2(20),
--     HIRE_DATE      DATE         not null
--         constraint EMP_HIRE_DATE_NN
--             check ("HIRE_DATE" IS NOT NULL),
--     JOB_ID         VARCHAR2(10) not null
--         constraint EMP_JOB_FK
--             references HR.JOBS
--         constraint EMP_JOB_NN
--             check ("JOB_ID" IS NOT NULL),
--     SALARY         NUMBER(8, 2)
--         constraint EMP_SALARY_MIN
--             check (salary > 0),
--     COMMISSION_PCT NUMBER(2, 2),
--     MANAGER_ID     NUMBER(6)
--         constraint EMP_MANAGER_FK
--             references HR.EMPLOYEES,
--     DEPARTMENT_ID  NUMBER(4)
--         constraint EMP_DEPT_FK
--             references HR.DEPARTMENTS
-- )
-- /
--
-- comment on table HR.EMPLOYEES is 'employees table. References with departments,
-- jobs, job_history tables. Contains a self reference.'
-- /
--
-- comment on column HR.EMPLOYEES.EMPLOYEE_ID is 'Primary key of employees table.'
-- /
--
-- comment on column HR.EMPLOYEES.FIRST_NAME is 'First name of the employee. A not null column.'
-- /
--
-- comment on column HR.EMPLOYEES.LAST_NAME is 'Last name of the employee. A not null column.'
-- /
--
-- comment on column HR.EMPLOYEES.EMAIL is 'Email id of the employee'
-- /
--
-- comment on column HR.EMPLOYEES.PHONE_NUMBER is 'Phone number of the employee; includes country code and area code'
-- /
--
-- comment on column HR.EMPLOYEES.HIRE_DATE is 'Date when the employee started on this job. A not null column.'
-- /
--
-- comment on column HR.EMPLOYEES.JOB_ID is 'Current job of the employee; foreign key to job_id column of the
-- jobs table. A not null column.'
-- /
--
-- comment on column HR.EMPLOYEES.SALARY is 'Monthly salary of the employee. Must be greater
-- than zero (enforced by constraint emp_salary_min)'
-- /
--
-- comment on column HR.EMPLOYEES.COMMISSION_PCT is 'Commission percentage of the employee; Only employees in sales
-- department eligible for commission percentage'
-- /
--
-- comment on column HR.EMPLOYEES.MANAGER_ID is 'Manager id of the employee; has same domain as manager_id in
-- departments table. Foreign key to employee_id column of employees table.
-- (useful for reflexive joins and CONNECT BY query)'
-- /
--
-- comment on column HR.EMPLOYEES.DEPARTMENT_ID is 'Department id where employee works; foreign key to department_id
-- column of the departments table'
-- /
--
-- create index HR.EMP_DEPARTMENT_IX
--     on HR.EMPLOYEES (DEPARTMENT_ID)
-- /
--
-- create index HR.EMP_JOB_IX
--     on HR.EMPLOYEES (JOB_ID)
-- /
--
-- create index HR.EMP_MANAGER_IX
--     on HR.EMPLOYEES (MANAGER_ID)
-- /
--
-- create index HR.EMP_NAME_IX
--     on HR.EMPLOYEES (LAST_NAME, FIRST_NAME)
-- /
--
-- create trigger HR.SECURE_EMPLOYEES
--     before insert or update or delete
--     on HR.EMPLOYEES
-- BEGIN
--     secure_dml;
-- END secure_employees;
-- /
--
-- create trigger HR.UPDATE_JOB_HISTORY
--     after update of JOB_ID,DEPARTMENT_ID
--     on HR.EMPLOYEES
--     for each row
-- BEGIN
--     add_job_history(:old.employee_id, :old.hire_date, sysdate,
--                     :old.job_id, :old.department_id);
-- END;
-- /


-- --------------------------------------------------------------------------------------------------------- EMPLOYEE_ID

-- ---------------------------------------------------------------------------------------------------------- FIRST_NAME

-- ----------------------------------------------------------------------------------------------------------- LAST_NAME

-- --------------------------------------------------------------------------------------------------------------- EMAIL

-- -------------------------------------------------------------------------------------------------------- PHONE_NUMBER

-- ----------------------------------------------------------------------------------------------------------- HIRE_DATE
SELECT MIN(HIRE_DATE), MAX(HIRE_DATE)
FROM EMPLOYEES
;

-- -------------------------------------------------------------------------------------------------------------- JOB_ID

-- -------------------------------------------------------------------------------------------------------------- SALARY
SELECT COUNT(1)
FROM EMPLOYEES
WHERE SALARY <= 0
;
SELECT MIN(SALARY), MAX(SALARY)
FROM EMPLOYEES
;

-- ------------------------------------------------------------------------------------------------------ COMMISSION_PCT
SELECT COUNT(1)
FROM EMPLOYEES
WHERE COMMISSION_PCT <= 0
;

SELECT MIN(COMMISSION_PCT), MAX(COMMISSION_PCT)
FROM EMPLOYEES
;

-- ---------------------------------------------------------------------------------------------------------- MANAGER_ID

-- ------------------------------------------------------------------------------------------------------- DEPARTMENT_ID