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
-- Exercises on EMPLOYEES, a problem per query that reads EMPLOYEES alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'EMPLOYEES' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_I_01
-- The board asks who earns more than the company-wide average salary, the highest first; people with the same salary
-- are ordered by `EMPLOYEE_ID`.
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY
FROM EMPLOYEES
WHERE SALARY > (SELECT AVG(SALARY) FROM EMPLOYEES)
ORDER BY SALARY DESC, EMPLOYEE_ID ASC
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_I_02
-- Payroll needs each employee's expected monthly pay: salary plus commission, where commission is a rate on salary and
-- is absent for most staff. Label each employee as commissioned or salaried.
SELECT EMPLOYEE_ID,
       LAST_NAME,
       SALARY,
       COMMISSION_PCT,
       SALARY * (1 + COALESCE(COMMISSION_PCT, 0))                               AS TOTAL_PAY,
       CASE WHEN COMMISSION_PCT IS NULL THEN 'Salaried' ELSE 'Commissioned' END AS PAY_TYPE
FROM EMPLOYEES
ORDER BY TOTAL_PAY DESC
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_I_03
-- HR reports headcount in three pay grades: under 5,000, 5,000 up to 10,000, and 10,000 or more, with the employees
-- whose salary is not recorded in a fourth, `Not recorded`. Show the grades from the lowest to the highest, `Not
-- recorded` last.
SELECT CASE
           WHEN SALARY IS NULL THEN 'Not recorded'
           WHEN SALARY < 5000 THEN 'Low'
           WHEN SALARY < 10000 THEN 'Medium'
           ELSE 'High'
           END  AS GRADE,
       COUNT(*) AS HEADCOUNT
FROM EMPLOYEES
GROUP BY CASE
             WHEN SALARY IS NULL THEN 'Not recorded'
             WHEN SALARY < 5000 THEN 'Low'
             WHEN SALARY < 10000 THEN 'Medium'
             ELSE 'High'
             END
ORDER BY MIN(SALARY) ASC NULLS LAST
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_I_04
-- An auditor flags any employee paid more than their direct manager.
SELECT e.EMPLOYEE_ID,
       e.LAST_NAME,
       e.SALARY,
       m.EMPLOYEE_ID AS MANAGER_ID,
       m.LAST_NAME   AS MANAGER,
       m.SALARY      AS MANAGER_SALARY
FROM EMPLOYEES e
         JOIN EMPLOYEES m ON m.EMPLOYEE_ID = e.MANAGER_ID
WHERE e.SALARY > m.SALARY
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_I_05
-- The COO wants every manager with the number of people reporting directly to them, most first.
SELECT m.EMPLOYEE_ID, m.FIRST_NAME, m.LAST_NAME, COUNT(*) AS DIRECT_REPORTS
FROM EMPLOYEES m
         JOIN EMPLOYEES e ON e.MANAGER_ID = m.EMPLOYEE_ID
GROUP BY m.EMPLOYEE_ID, m.FIRST_NAME, m.LAST_NAME
ORDER BY DIRECT_REPORTS DESC, m.EMPLOYEE_ID ASC
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_I_06
-- A vice president holds skip-level meetings: they meet the people who report to their direct reports. Given the vice
-- president, list those employees with the manager in between, by name.
SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, m.LAST_NAME AS MANAGER
FROM EMPLOYEES e
         JOIN EMPLOYEES m ON m.EMPLOYEE_ID = e.MANAGER_ID
WHERE m.MANAGER_ID = :managerId
ORDER BY e.LAST_NAME ASC, e.FIRST_NAME ASC
;
