---
-- #%L
-- hr
-- %%
-- Copyright (C) 2024 - 2025 Jinahya, Inc.
-- %%
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- 
--      http://www.apache.org/licenses/LICENSE-2.0
-- 
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
-- #L%
---
--

-- --------------------------------------------------------------------------------------------------------- EMPLOYEE_ID
SELECT MIN(EMPLOYEE_ID), MAX(EMPLOYEE_ID)
FROM EMPLOYEES
;

-- ---------------------------------------------------------------------------------------------------------- FIRST_NAME
SELECT MIN(LENGTH(FIRST_NAME)), MAX(LENGTH(FIRST_NAME))
FROM EMPLOYEES
;

-- ----------------------------------------------------------------------------------------------------------- LAST_NAME
SELECT MIN(LENGTH(LAST_NAME)), MAX(LENGTH(LAST_NAME))
FROM EMPLOYEES
;

-- --------------------------------------------------------------------------------------------------------------- EMAIL
SELECT MIN(LENGTH(EMAIL)), MAX(LENGTH(EMAIL))
FROM EMPLOYEES
;

-- https://github.com/hibernate/hibernate-validator/blob/main/engine/src/main/java/org/hibernate/validator/internal/constraintvalidators/AbstractEmailValidator.java#L37
SELECT COUNT(1)
FROM EMPLOYEES
WHERE REGEXP_LIKE(EMAIL, '[a-z0-9!#$%&''*+/=?^_`{|}~\u0080-\uFFFF-]')
;

SELECT COUNT(1)
FROM EMPLOYEES
WHERE NOT REGEXP_LIKE(EMAIL, '[a-z0-9!#$%&''*+/=?^_`{|}~\u0080-\uFFFF-]')
;


-- -------------------------------------------------------------------------------------------------------- PHONE_NUMBER

-- ----------------------------------------------------------------------------------------------------------- HIRE_DATE
SELECT MIN(HIRE_DATE), MAX(HIRE_DATE)
FROM EMPLOYEES
;

SELECT *
FROM EMPLOYEES
ORDER BY HIRE_DATE ASC
    FETCH FIRST 10 ROWS ONLY
;

-- -------------------------------------------------------------------------------------------------------------- JOB_ID
SELECT MIN(JOB_ID), MAX(JOB_ID)
FROM EMPLOYEES
;

-- JOB_ID COUNT 역순
SELECT JOB_ID, COUNT(1) AS JOB_COUNT
FROM EMPLOYEES
GROUP BY JOB_ID
ORDER BY COUNT(JOB_ID) DESC
    FETCH FIRST 10 ROWS ONLY
;

-- -------------------------------------------------------------------------------------------------------------- SALARY
SELECT COUNT(1)
FROM EMPLOYEES
WHERE SALARY <= 0
;

SELECT MIN(SALARY), MAX(SALARY)
FROM EMPLOYEES
;

SELECT *
FROM EMPLOYEES
WHERE SALARY IS NULL
;

-- SALARY 가 JOB.MIN_SALARY 와 JOB.MAX_SALARY 를 벗어나는
SELECT *
FROM EMPLOYEES e
         JOIN JOBS j ON e.JOB_ID = j.JOB_ID
WHERE e.SALARY < j.MIN_SALARY
   OR e.SALARY > j.MAX_SALARY
    FETCH FIRST 10 ROWS ONLY
;

-- SALARY 가 JOB.MIN_SALARY 와 JOB.MAX_SALARY 에 포함되는
SELECT *
FROM EMPLOYEES e
         JOIN JOBS j ON e.JOB_ID = j.JOB_ID
WHERE e.SALARY >= j.MIN_SALARY
  AND e.SALARY <= j.MAX_SALARY
    FETCH FIRST 10 ROWS ONLY
;

-- SALARY 가 JOB.MIN_SALARY 보다 같거나 작은
SELECT j.MIN_SALARY, e.SALARY, e.*
FROM EMPLOYEES e
         JOIN JOBS j ON e.JOB_ID = j.JOB_ID
WHERE e.SALARY <= j.MIN_SALARY
    FETCH FIRST 10 ROWS ONLY
;

-- SALARY 가 JOB.MAX_SALARY 보다 같거나 큰
SELECT j.MAX_SALARY, e.SALARY, e.*
FROM EMPLOYEES e
         JOIN JOBS j ON e.JOB_ID = j.JOB_ID
WHERE e.SALARY >= j.MAX_SALARY
    FETCH FIRST 10 ROWS ONLY
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
SELECT MIN(MANAGER_ID), MAX(MANAGER_ID)
FROM EMPLOYEES
;

-- 가장 많은 사람을을 manage 하는
SELECT m.SUBORDINATE_COUNT, e.*
FROM EMPLOYEES e
         JOIN (SELECT MANAGER_ID, COUNT(MANAGER_ID) as SUBORDINATE_COUNT
               FROM EMPLOYEES
               GROUP BY MANAGER_ID
               ORDER BY SUBORDINATE_COUNT DESC) m
              ON e.EMPLOYEE_ID = m.MANAGER_ID
ORDER BY m.SUBORDINATE_COUNT DESC
;

SELECT e.EMPLOYEE_ID, e.MANAGER_ID, e.DEPARTMENT_ID, d.MANAGER_ID
FROM EMPLOYEES e
         LEFT OUTER JOIN DEPARTMENTS d ON e.MANAGER_ID = d.MANAGER_ID
WHERE e.MANAGER_ID IS NOT NULL
  AND d.MANAGER_ID IS NOT NULL
    FETCH FIRST 10 ROWS ONLY
;

SELECT e.EMPLOYEE_ID, e.MANAGER_ID, e.DEPARTMENT_ID, d.MANAGER_ID
FROM EMPLOYEES e
         LEFT OUTER JOIN DEPARTMENTS d ON e.MANAGER_ID = d.MANAGER_ID
WHERE e.MANAGER_ID IS NOT NULL
  AND d.MANAGER_ID IS NULL
    FETCH FIRST 10 ROWS ONLY
;

-- EMPLOYEE.MANAGER_ID = (EMPLOYEE.DEPARTMENT_ID).MANAGER_ID
SELECT e.EMPLOYEE_ID, e.MANAGER_ID, e.DEPARTMENT_ID, d.DEPARTMENT_ID, d.MANAGER_ID, d.DEPARTMENT_NAME
FROM EMPLOYEES e
         INNER JOIN DEPARTMENTS d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
         INNER JOIN EMPLOYEES m ON e.MANAGER_ID = m.EMPLOYEE_ID
WHERE e.DEPARTMENT_ID IS NOT NULL
  AND e.MANAGER_ID = d.MANAGER_ID
;

-- EMPLOYEE.MANAGER_ID <> (EMPLOYEE.DEPARTMENT).MANAGER_ID
SELECT e.EMPLOYEE_ID, e.MANAGER_ID, e.DEPARTMENT_ID, d.DEPARTMENT_ID, d.DEPARTMENT_NAME, d.MANAGER_ID
FROM EMPLOYEES e
         INNER JOIN DEPARTMENTS d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
         INNER JOIN EMPLOYEES m ON d.MANAGER_ID = m.EMPLOYEE_ID
WHERE e.DEPARTMENT_ID IS NOT NULL
  AND e.MANAGER_ID <> d.MANAGER_ID
;


-- ------------------------------------------------------------------------------------------------------- DEPARTMENT_ID
SELECT MIN(DEPARTMENT_ID), MAX(DEPARTMENT_ID)
FROM EMPLOYEES
;

-- 가장 많은 employee 가 소속된
SELECT e.MEMBER_COUNT, d.*
FROM DEPARTMENTS d
         JOIN (SELECT DEPARTMENT_ID, COUNT(DEPARTMENT_ID) as MEMBER_COUNT
               FROM EMPLOYEES
               GROUP BY DEPARTMENT_ID
               ORDER BY MEMBER_COUNT DESC) e
              ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
ORDER BY e.MEMBER_COUNT DESC
;

-- specific path frm the leaf
SELECT e.EMPLOYEE_ID, e.MANAGER_ID, d.DEPARTMENT_ID, d.MANAGER_ID, d.DEPARTMENT_NAME
FROM EMPLOYEES e
         LEFT OUTER JOIN DEPARTMENTS d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
START WITH e.EMPLOYEE_ID = 107
CONNECT BY PRIOR e.MANAGER_ID = e.EMPLOYEE_ID;


-- all in a tree
SELECT LEVEL,
       SYS_CONNECT_BY_PATH(LAST_NAME, '/') AS full_path,
       e.*
FROM EMPLOYEES e
START WITH e.MANAGER_ID IS NULL
CONNECT BY PRIOR e.EMPLOYEE_ID = e.MANAGER_ID
ORDER BY LEVEL ASC, LAST_NAME ASC
;
























[EL Fine]: sql: 2025.11.28 14:50:13.569--ClientSession(2146473561)--Connection(1914435501)--INSERT INTO JOBS (JOB_ID, JOB_TITLE, MAX_SALARY, MIN_SALARY) VALUES (?, ?, ?, ?)
	bind => [u, aJ2O0vxjyntfsJrPKAlaH8vSSP7cVSjTBX, 843990, 120196]
[EL Fine]: sql: 2025.11.28 14:50:13.571--ClientSession(2146473561)--Connection(1914435501)--INSERT INTO JOBS (JOB_ID, JOB_TITLE, MAX_SALARY, MIN_SALARY) VALUES (?, ?, ?, ?)
	bind => [EDBPM, So_7w0ZRGtnGx, null, -594997]

[EL Fine]: sql: 2025.11.28 14:50:13.572--ClientSession(2146473561)--Connection(1914435501)--INSERT INTO EMPLOYEES (EMPLOYEE_ID, COMMISSION_PCT, DEPARTMENT_ID, EMAIL, FIRST_NAME, HIRE_DATE, JOB_ID, LAST_NAME, MANAGER_ID, PHONE_NUMBER, SALARY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
	bind => [-499449, 0.9719336254404962383013355520233744755387306213378906250, null, CjrJMVaXsV, bkHoQsvovNcj_44El, 2025-11-28, EDBPM, XX4MjCiE, null, B7Pzr, 223815.9450008266513674815012535646019387058913707733154296875]


[EL Fine]: sql: 2025.11.28 14:50:13.573--ClientSession(2146473561)--Connection(1914435501)--INSERT INTO EMPLOYEES (EMPLOYEE_ID, COMMISSION_PCT, DEPARTMENT_ID, EMAIL, FIRST_NAME, HIRE_DATE, JOB_ID, LAST_NAME, MANAGER_ID, PHONE_NUMBER, SALARY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
	bind => [322017, 0.436682475679905586218865209957584738731384277343750, -6205, ODBbH1, 0CZj, 2025-11-28, u, f, -499449, rPiPso3, 469813.2113768807575101271734041574745788238942623138427734375]


[EL Fine]: sql: 2025.11.28 14:50:13.574--ClientSession(2146473561)--SELECT 1
[EL Warning]: 2025.11.28 14:50:13.574--UnitOfWork(1114221271)--Exception [EclipseLink-4002] (Eclipse Persistence Services - 5.0.0-B11.v202510061359-de1b7e29059f4840401bfb73dc00e458b032e712): org.eclipse.persistence.exceptions.DatabaseException
Internal Exception: org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException: Referential integrity constraint violation: "FK_EMPLOYEES_DEPARTMENT_ID: PUBLIC.EMPLOYEES FOREIGN KEY(DEPARTMENT_ID) REFERENCES PUBLIC.DEPARTMENTS(DEPARTMENT_ID) (-6205)"; SQL statement:
INSERT INTO EMPLOYEES (EMPLOYEE_ID, COMMISSION_PCT, DEPARTMENT_ID, EMAIL, FIRST_NAME, HIRE_DATE, JOB_ID, LAST_NAME, MANAGER_ID, PHONE_NUMBER, SALARY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) [23506-240]
Error Code: 23506
Call: INSERT INTO EMPLOYEES (EMPLOYEE_ID, COMMISSION_PCT, DEPARTMENT_ID, EMAIL, FIRST_NAME, HIRE_DATE, JOB_ID, LAST_NAME, MANAGER_ID, PHONE_NUMBER, SALARY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
	bind => [322017, 0.436682475679905586218865209957584738731384277343750, -6205, ODBbH1, 0CZj, 2025-11-28, u, f, -499449, rPiPso3, 469813.2113768807575101271734041574745788238942623138427734375]
Query: InsertObjectQuery(com.github.jinahya.oracle.sample.schemas.persistence.hr.Employee@4e9e1{employeeId=322017,firstName=0CZj,lastName=f,email=ODBbH1,phoneNumber=rPiPso3,hireDate=2025-11-28,jobId=u,salary=469813.2113768807575101271734041574745788238942623138427734375,commissionPct=0.436682475679905586218865209957584738731384277343750,managerId=-499449,departmentId=-6205})
14:50:13.581 [main] DEBUG c.g.j.p.m.t.__MappedEntity_Persistence_ -- onShutdown(jakarta.enterprise.event.Shutdown@645ba24a)
