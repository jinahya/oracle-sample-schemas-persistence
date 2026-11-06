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
-- Exercises on the whole schema, a problem per query that reads more than one table or view, at the basic level.
--
-- These are the basic SQL solutions of the 'Whole schema' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- Problems that join, or subquery, more than one table.
--


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_B_01
-- A new starter asks who runs a department (given the department id).
SELECT d.DEPARTMENT_NAME, m.EMPLOYEE_ID, m.FIRST_NAME, m.LAST_NAME, m.EMAIL
FROM DEPARTMENTS d
         JOIN EMPLOYEES m ON m.EMPLOYEE_ID = d.MANAGER_ID
WHERE d.DEPARTMENT_ID = :departmentId
;


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_B_02
-- The payslip shows the employee's job title, not the job id.
SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, j.JOB_TITLE
FROM EMPLOYEES e
         JOIN JOBS j ON j.JOB_ID = e.JOB_ID
WHERE e.EMPLOYEE_ID = :employeeId
;


-- ------------------------------------------------------------------------------------------------------ HR_SCHEMA_B_03
-- A regional director knows their region by name, not by id, and wants its countries.
SELECT c.COUNTRY_ID, c.COUNTRY_NAME
FROM COUNTRIES c
         JOIN REGIONS r ON r.REGION_ID = c.REGION_ID
WHERE r.REGION_NAME = :regionName
ORDER BY c.COUNTRY_NAME ASC
;
