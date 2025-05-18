--
-- create table HR.DEPARTMENTS
-- (
--     DEPARTMENT_ID   NUMBER(4)    not null
--         constraint DEPT_ID_PK
--             primary key,
--     DEPARTMENT_NAME VARCHAR2(30) not null
--         constraint DEPT_NAME_NN
--             check ("DEPARTMENT_NAME" IS NOT NULL),
--     MANAGER_ID      NUMBER(6)
--         constraint DEPT_MGR_FK
--             references HR.EMPLOYEES,
--     LOCATION_ID     NUMBER(4)
--         constraint DEPT_LOC_FK
--             references HR.LOCATIONS
-- )
-- /
--
-- comment on table HR.DEPARTMENTS is 'Departments table that shows details of departments where employees
-- work. references with locations, employees, and job_history tables.'
-- /
--
-- comment on column HR.DEPARTMENTS.DEPARTMENT_ID is 'Primary key column of departments table.'
-- /
--
-- comment on column HR.DEPARTMENTS.DEPARTMENT_NAME is 'A not null column that shows name of a department. Administration,
-- Marketing, Purchasing, Human Resources, Shipping, IT, Executive, Public
-- Relations, Sales, Finance, and Accounting. '
-- /
--
-- comment on column HR.DEPARTMENTS.MANAGER_ID is 'Manager_id of a department. Foreign key to employee_id column of employees table. The manager_id column of the employee table references this column.'
-- /
--
-- comment on column HR.DEPARTMENTS.LOCATION_ID is 'Location id where a department is located. Foreign key to location_id column of locations table.'
-- /
--
-- create index HR.DEPT_LOCATION_IX
--     on HR.DEPARTMENTS (LOCATION_ID)
-- /


-- ------------------------------------------------------------------------------------------------------- DEPARTMENT_ID

-- ----------------------------------------------------------------------------------------------------- DEPARTMENT_NAME

-- ---------------------------------------------------------------------------------------------------------- MANAGER_ID
SELECT MANAGER_ID, COUNT(1)
FROM DEPARTMENTS
GROUP BY MANAGER_ID
HAVING COUNT(1) > 1
;

-- --------------------------------------------------------------------------------------------------------- LOCATION_ID
SELECT LOCATION_ID, COUNT(1)
FROM DEPARTMENTS
GROUP BY LOCATION_ID
HAVING COUNT(1) > 1
;
