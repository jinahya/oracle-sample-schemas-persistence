-- #%L
-- hr
-- %%
-- Copyright (C) 2024 - 2026 Jinahya, Inc.
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
-- Exercises on EMPLOYEES, a problem per query that reads EMPLOYEES alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'EMPLOYEES' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_A_01
-- Each department head is told who their best-paid employee is; ties are all reported.
SELECT e.DEPARTMENT_ID, e.EMPLOYEE_ID, e.LAST_NAME, e.SALARY
FROM EMPLOYEES e
WHERE e.SALARY = (SELECT MAX(x.SALARY) FROM EMPLOYEES x WHERE x.DEPARTMENT_ID = e.DEPARTMENT_ID)
ORDER BY e.DEPARTMENT_ID ASC
;

SELECT DEPARTMENT_ID, EMPLOYEE_ID, LAST_NAME, SALARY
FROM (SELECT e.*, RANK() OVER (PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) AS RNK
      FROM EMPLOYEES e)
WHERE RNK = 1
ORDER BY DEPARTMENT_ID ASC
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_A_02
-- Ahead of the bonus round, each department head wants their three highest salaries and who earns them.
SELECT DEPARTMENT_ID, EMPLOYEE_ID, LAST_NAME, SALARY, RNK
FROM (SELECT e.*, DENSE_RANK() OVER (PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) AS RNK
      FROM EMPLOYEES e
      WHERE DEPARTMENT_ID IS NOT NULL)
WHERE RNK <= 3
ORDER BY DEPARTMENT_ID ASC, RNK ASC
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_A_03
-- For a reorganisation, HR needs everyone who reports to a given manager, directly or indirectly, with their depth
-- below that manager.
SELECT LEVEL - 1                           AS DEPTH,
       EMPLOYEE_ID,
       LAST_NAME,
       MANAGER_ID,
       SYS_CONNECT_BY_PATH(LAST_NAME, '/') AS CHAIN
FROM EMPLOYEES
WHERE LEVEL > 1
    START
WITH EMPLOYEE_ID = :managerId
CONNECT BY PRIOR EMPLOYEE_ID = MANAGER_ID
ORDER SIBLINGS BY LAST_NAME
;

WITH CHAIN (EMPLOYEE_ID, LAST_NAME, MANAGER_ID, DEPTH)
         AS (SELECT EMPLOYEE_ID, LAST_NAME, MANAGER_ID, 0
             FROM EMPLOYEES
             WHERE EMPLOYEE_ID = :managerId
             UNION ALL
             SELECT e.EMPLOYEE_ID, e.LAST_NAME, e.MANAGER_ID, c.DEPTH + 1
             FROM EMPLOYEES e
                      JOIN CHAIN c ON e.MANAGER_ID = c.EMPLOYEE_ID)
SELECT DEPTH, EMPLOYEE_ID, LAST_NAME, MANAGER_ID
FROM CHAIN
WHERE DEPTH > 0
ORDER BY DEPTH ASC, LAST_NAME ASC
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_A_04
-- Before a salary benchmark, HR asks two things about a reference department (given its id): who earns more than
-- everyone in it, and who earns more than at least one person in it.
SELECT EMPLOYEE_ID, LAST_NAME, SALARY
FROM EMPLOYEES
WHERE SALARY > ALL (SELECT SALARY FROM EMPLOYEES WHERE DEPARTMENT_ID = :departmentId)
ORDER BY SALARY DESC
;

SELECT EMPLOYEE_ID, LAST_NAME, SALARY
FROM EMPLOYEES
WHERE SALARY > ANY (SELECT SALARY FROM EMPLOYEES WHERE DEPARTMENT_ID = :departmentId)
ORDER BY SALARY DESC
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_A_05
-- HR sends a card on every work anniversary. Given a month (1–12), list who joined in that month and how many years
-- they complete this year.
SELECT EMPLOYEE_ID,
       FIRST_NAME,
       LAST_NAME,
       HIRE_DATE,
       EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE) AS YEARS
FROM EMPLOYEES
WHERE EXTRACT(MONTH FROM HIRE_DATE) = :month
ORDER BY EXTRACT(DAY FROM HIRE_DATE) ASC, LAST_NAME ASC
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_A_06
-- IT is moving everyone to full e-mail addresses and reprinting badges. For each employee they need a proposed address
-- (first.last@example.com, lower case), the badge line (EMP- and the id, then the last name and first initial), the
-- phone number with dashes instead of dots, and its last four digits for the desk extension.
SELECT EMPLOYEE_ID,
       LOWER(COALESCE(FIRST_NAME, '') || '.' || LAST_NAME) || '@example.com' AS PROPOSED_EMAIL,
       'EMP-' || TO_CHAR(EMPLOYEE_ID) || ' ' || LAST_NAME || ', ' || SUBSTR(FIRST_NAME, 1, 1) ||
       '.'                                                                   AS BADGE,
       REPLACE(PHONE_NUMBER, '.', '-')                                       AS PHONE,
       SUBSTR(PHONE_NUMBER, -4)                                              AS LAST_FOUR
FROM EMPLOYEES
ORDER BY EMPLOYEE_ID ASC
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_A_07
-- Before the pay review, each department head wants every member of staff next to the average salary of the department,
-- and how far above or below it they are.
SELECT DEPARTMENT_ID,
       EMPLOYEE_ID,
       LAST_NAME,
       SALARY,
       ROUND(AVG(SALARY) OVER (PARTITION BY DEPARTMENT_ID), 2)          AS DEPARTMENT_AVERAGE,
       ROUND(SALARY - AVG(SALARY) OVER (PARTITION BY DEPARTMENT_ID), 2) AS DIFFERENCE
FROM EMPLOYEES
WHERE DEPARTMENT_ID IS NOT NULL
ORDER BY DEPARTMENT_ID ASC, SALARY DESC, EMPLOYEE_ID ASC
;
