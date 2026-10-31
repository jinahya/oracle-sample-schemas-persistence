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
-- Exercises on the whole schema, a problem per query that reads more than one table or view, at the advanced level.
--
-- These are the advanced SQL solutions of the 'Whole schema' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- Problems that join, or subquery, more than one table.
--


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_A_01
-- Internal mobility wants every earlier position held in a department other than the employee's current one: who moved,
-- from where, to where.
SELECT h.EMPLOYEE_ID,
       h.START_DATE,
       h.END_DATE,
       h.JOB_ID,
       h.DEPARTMENT_ID AS FROM_DEPARTMENT,
       e.DEPARTMENT_ID AS TO_DEPARTMENT
FROM JOB_HISTORY h
         JOIN EMPLOYEES e ON e.EMPLOYEE_ID = h.EMPLOYEE_ID
WHERE h.DEPARTMENT_ID <> e.DEPARTMENT_ID
ORDER BY h.EMPLOYEE_ID ASC, h.START_DATE ASC
;


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_A_02
-- An employee asks HR for their record: every position they have held, the current one included, in date order.
SELECT h.START_DATE, h.END_DATE, h.JOB_ID, j.JOB_TITLE, h.DEPARTMENT_ID
FROM JOB_HISTORY h
         JOIN JOBS j ON j.JOB_ID = h.JOB_ID
WHERE h.EMPLOYEE_ID = :employeeId
UNION ALL
SELECT e.HIRE_DATE, NULL, e.JOB_ID, j.JOB_TITLE, e.DEPARTMENT_ID
FROM EMPLOYEES e
         JOIN JOBS j ON j.JOB_ID = e.JOB_ID
WHERE e.EMPLOYEE_ID = :employeeId
ORDER BY 1
;


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_A_03
-- HR wants the employees whose current job is one they held before — boomerangs within the company.
SELECT EMPLOYEE_ID, JOB_ID
FROM JOB_HISTORY
INTERSECT
SELECT EMPLOYEE_ID, JOB_ID
FROM EMPLOYEES
ORDER BY 1
;


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_A_04
-- Facilities is reviewing the property list: which locations host no department at all?
SELECT LOCATION_ID
FROM LOCATIONS
MINUS
SELECT LOCATION_ID
FROM DEPARTMENTS
;


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_A_05
-- The intranet's site page shows, for one location, each department and its staff. Loading departments and then each
-- department's employees one by one is the N+1 problem; the page needs a single query.
SELECT d.*, e.*
FROM DEPARTMENTS d
         LEFT JOIN EMPLOYEES e ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
WHERE d.LOCATION_ID = :locationId
ORDER BY d.DEPARTMENT_ID ASC, e.LAST_NAME ASC
;


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_A_06
-- An employee's request for leave has to be approved all the way up. Given the employee, list them and every manager
-- above them, with job titles, up to the president.
SELECT LEVEL - 1 AS STEPS_UP, e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, j.JOB_TITLE
FROM EMPLOYEES e
         JOIN JOBS j ON j.JOB_ID = e.JOB_ID
START WITH e.EMPLOYEE_ID = :employeeId
CONNECT BY e.EMPLOYEE_ID = PRIOR e.MANAGER_ID
ORDER BY STEPS_UP ASC
;
