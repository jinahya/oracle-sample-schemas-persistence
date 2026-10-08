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
SELECT MIN(LENGTH(TRIM(FIRST_NAME))), MAX(LENGTH(TRIM(FIRST_NAME)))
FROM EMPLOYEES
;

-- the MIN/MAX above cannot tell whether a FIRST_NAME is blank: TRIM of an all-space value is '', which Oracle treats as
-- NULL, and MIN/MAX skip NULLs. List those rows directly.
-- FIRST_NAME is nullable, so this covers both a missing and a blank first name.
-- no rows: every employee has a FIRST_NAME with at least one non-space character.
SELECT EMPLOYEE_ID, FIRST_NAME
FROM EMPLOYEES
WHERE TRIM(FIRST_NAME) IS NULL
;

-- a FIRST_NAME which is not blank but carries leading or trailing spaces, which the trimmed lengths above hide.
-- no rows: no FIRST_NAME is padded, so the trimmed and the stored lengths agree.
SELECT EMPLOYEE_ID, '[' || FIRST_NAME || ']' AS FIRST_NAME
FROM EMPLOYEES
WHERE FIRST_NAME <> TRIM(FIRST_NAME)
;

-- ----------------------------------------------------------------------------------------------------------- LAST_NAME
SELECT MIN(LENGTH(TRIM(LAST_NAME))), MAX(LENGTH(TRIM(LAST_NAME)))
FROM EMPLOYEES
;

-- the MIN/MAX above cannot tell whether a LAST_NAME is blank: TRIM of an all-space value is '', which Oracle treats as
-- NULL, and MIN/MAX skip NULLs. List those rows directly.
-- LAST_NAME is NOT NULL, so only a blank last name -- all spaces -- can match.
-- no rows: every employee has a LAST_NAME with at least one non-space character.
SELECT EMPLOYEE_ID, LAST_NAME
FROM EMPLOYEES
WHERE TRIM(LAST_NAME) IS NULL
;

-- a LAST_NAME which is not blank but carries leading or trailing spaces, which the trimmed lengths above hide.
-- no rows: no LAST_NAME is padded, so the trimmed and the stored lengths agree.
SELECT EMPLOYEE_ID, '[' || LAST_NAME || ']' AS LAST_NAME
FROM EMPLOYEES
WHERE LAST_NAME <> TRIM(LAST_NAME)
;

-- --------------------------------------------------------------------------------------------------------------- EMAIL
SELECT MIN(LENGTH(EMAIL)), MAX(LENGTH(EMAIL))
FROM EMPLOYEES
;

-- EMAIL holds only the local part of an address -- the user id before the '@', e.g. SKING -- not a full address.
-- no rows: no EMAIL contains an '@', so none is a full address.
SELECT EMPLOYEE_ID, EMAIL
FROM EMPLOYEES
WHERE INSTR(EMAIL, '@') > 0
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
SELECT MIN(LENGTH(PHONE_NUMBER)), MAX(LENGTH(PHONE_NUMBER))
FROM EMPLOYEES
;

-- PHONE_NUMBER holds groups of digits separated by dots, e.g. 1.515.555.0100 -- no '+', spaces, dashes or parentheses.
-- counts the values which are anything else; a NULL is not counted, since REGEXP_LIKE of NULL is unknown.
-- 0: every PHONE_NUMBER is dot-separated digit groups.
SELECT COUNT(1)
FROM EMPLOYEES
WHERE NOT REGEXP_LIKE(PHONE_NUMBER, '^\d+(\.\d+)*$')
;

-- ----------------------------------------------------------------------------------------------------------- HIRE_DATE
SELECT MIN(LENGTH(HIRE_DATE)), MAX(LENGTH(HIRE_DATE))
FROM EMPLOYEES
;

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
SELECT COUNT(1)
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

-- SALARY 가 JOB.MIN_SALARY 보다 같거나 작은, 최저 SALARY
SELECT j.MIN_SALARY, e.SALARY, e.*
FROM EMPLOYEES e
         JOIN JOBS j ON e.JOB_ID = j.JOB_ID
WHERE e.SALARY <= j.MIN_SALARY
    FETCH FIRST 10 ROWS ONLY
;

-- SALARY 가 JOB.MAX_SALARY 보다 같거나 큰, 최고 SALARY
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
