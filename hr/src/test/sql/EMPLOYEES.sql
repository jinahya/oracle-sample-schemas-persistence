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

SELECT *
FROM EMPLOYEES
WHERE SALARY IS NULL
;

SELECT *
FROM EMPLOYEES e
         JOIN JOBS j ON e.JOB_ID = j.JOB_ID
WHERE e.SALARY < j.MIN_SALARY
   OR e.SALARY > j.MAX_SALARY
    FETCH FIRST 10 ROWS ONLY
;

SELECT *
FROM EMPLOYEES e
         JOIN JOBS j ON e.JOB_ID = j.JOB_ID
WHERE e.SALARY >= j.MIN_SALARY
  AND e.SALARY <= j.MAX_SALARY
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

-- ------------------------------------------------------------------------------------------------------- DEPARTMENT_ID
