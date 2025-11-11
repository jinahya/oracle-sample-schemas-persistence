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
SELECT e.EMPLOYEE_ID, e.MANAGER_ID, e.DEPARTMENT_ID, d.DEPARTMENT_ID, d.DEPARTMENT_NAME, d.MANAGER_ID
FROM EMPLOYEES e
         INNER JOIN DEPARTMENTS d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
         INNER JOIN EMPLOYEES m ON e.MANAGER_ID = m.EMPLOYEE_ID
WHERE e.DEPARTMENT_ID IS NOT NULL
  AND e.MANAGER_ID = d.MANAGER_ID
;

-- EMPLOYEE.MANAGER_ID <> DEPARTMENT.MANAGER_ID
SELECT e.EMPLOYEE_ID, e.MANAGER_ID, e.DEPARTMENT_ID, d.DEPARTMENT_ID, d.DEPARTMENT_NAME, d.MANAGER_ID
FROM EMPLOYEES e
         INNER JOIN DEPARTMENTS d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
         INNER JOIN EMPLOYEES m ON e.MANAGER_ID = m.EMPLOYEE_ID
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

-- MANAGER_ID 는 DE


SELECT e.EMPLOYEE_ID, e.MANAGER_ID, d.DEPARTMENT_ID, d.DEPARTMENT_NAME, d.MANAGER_ID
FROM EMPLOYEES e
         LEFT OUTER JOIN DEPARTMENTS d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
START WITH e.EMPLOYEE_ID = 107
CONNECT BY PRIOR e.MANAGER_ID = e.EMPLOYEE_ID
