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
-- Exercises on the whole schema, a problem per query that reads more than one table or view, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'Whole schema' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- Problems that join, or subquery, more than one table.
--


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_I_01
-- HR wants every department with its headcount, largest first, including departments that have nobody.
SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, COUNT(e.EMPLOYEE_ID) AS HEADCOUNT
FROM DEPARTMENTS d
         LEFT JOIN EMPLOYEES e ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME
ORDER BY HEADCOUNT DESC, d.DEPARTMENT_ID ASC
;


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_I_03
-- Facilities allocates a floor warden to every department with at least a given number of staff.
SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, COUNT(*) AS HEADCOUNT
FROM DEPARTMENTS d
         JOIN EMPLOYEES e ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME
HAVING COUNT(*) >= :minHeadcount
ORDER BY HEADCOUNT DESC
;


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_I_04
-- Internal communications is printing the directory: every employee with job title, department name and city. Staff
-- with no department must still appear.
SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, j.JOB_TITLE, d.DEPARTMENT_NAME, l.CITY
FROM EMPLOYEES e
         JOIN JOBS j ON j.JOB_ID = e.JOB_ID
         LEFT JOIN DEPARTMENTS d ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
         LEFT JOIN LOCATIONS l ON l.LOCATION_ID = d.LOCATION_ID
ORDER BY e.LAST_NAME ASC, e.FIRST_NAME ASC
;


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_I_05
-- Compensation wants, per job, how many people hold it and the lowest, average and highest salary actually paid, next
-- to the job's band.
SELECT j.JOB_ID,
       j.JOB_TITLE,
       COUNT(*)                AS HOLDERS,
       MIN(e.SALARY)           AS LOWEST,
       ROUND(AVG(e.SALARY), 2) AS AVERAGE,
       MAX(e.SALARY)           AS HIGHEST,
       j.MIN_SALARY,
       j.MAX_SALARY
FROM JOBS j
         JOIN EMPLOYEES e ON e.JOB_ID = j.JOB_ID
GROUP BY j.JOB_ID, j.JOB_TITLE, j.MIN_SALARY, j.MAX_SALARY
ORDER BY j.JOB_ID ASC
;


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_I_06
-- HR wants the people paid in the top tenth of their job's band — at or above 90% of the band maximum — because they
-- have little room left to grow without a new role.
SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, e.JOB_ID, e.SALARY, j.MAX_SALARY
FROM EMPLOYEES e
         JOIN JOBS j ON j.JOB_ID = e.JOB_ID
WHERE e.SALARY >= j.MAX_SALARY * 0.9
ORDER BY e.SALARY / j.MAX_SALARY DESC
;


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_I_07
-- Facilities wants to reclaim space from departments that have no employees, and to know whether each still has a
-- manager on record.
SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, d.MANAGER_ID
FROM DEPARTMENTS d
WHERE NOT EXISTS (SELECT 1 FROM EMPLOYEES e WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID)
ORDER BY d.DEPARTMENT_ID ASC
;


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_I_08
-- The regional directors want headcount per country, grouped under their region.
SELECT r.REGION_NAME, c.COUNTRY_NAME, COUNT(*) AS HEADCOUNT
FROM EMPLOYEES e
         JOIN DEPARTMENTS d ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
         JOIN LOCATIONS l ON l.LOCATION_ID = d.LOCATION_ID
         JOIN COUNTRIES c ON c.COUNTRY_ID = l.COUNTRY_ID
         JOIN REGIONS r ON r.REGION_ID = c.REGION_ID
GROUP BY r.REGION_NAME, c.COUNTRY_NAME
ORDER BY r.REGION_NAME ASC, HEADCOUNT DESC
;


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_I_09
-- HR wants the employees with at least one earlier position on record, and how many.
SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, COUNT(*) AS EARLIER_POSITIONS
FROM EMPLOYEES e
         JOIN JOB_HISTORY h ON h.EMPLOYEE_ID = e.EMPLOYEE_ID
GROUP BY e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME
ORDER BY EARLIER_POSITIONS DESC, e.EMPLOYEE_ID ASC
;


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_I_10
-- For a retention study, HR wants the employees who have no earlier position on record at all — they still hold the job
-- and department they joined in.
SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, e.HIRE_DATE
FROM EMPLOYEES e
WHERE NOT EXISTS (SELECT 1 FROM JOB_HISTORY h WHERE h.EMPLOYEE_ID = e.EMPLOYEE_ID)
ORDER BY e.HIRE_DATE ASC
;
