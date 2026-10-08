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
-- Exercises on EMP_DETAILS_VIEW, a problem per query that reads EMP_DETAILS_VIEW alone; Basic, then Intermediate, then
-- Advanced.
--
-- These are the SQL solutions of the 'EMP_DETAILS_VIEW' section of EXERCISES.adoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- EmpDetailsView maps this view, but is not listed in the persistence units, so the problems here are solved in SQL
-- only. The view inner-joins all six tables, which leaves out the employee without a department.
--


-- =============================================================================================================== BASIC


-- ---------------------------------------------------------------------------- HR-EMP_DETAILS_VIEW-B-01 Staff on a site
-- Reception at one site (given its city, such as Seattle) wants the staff who work there, with their job titles and
-- departments.
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, JOB_TITLE, DEPARTMENT_NAME
FROM EMP_DETAILS_VIEW
WHERE CITY = :city
ORDER BY LAST_NAME ASC, FIRST_NAME ASC
;


-- ======================================================================================================== INTERMEDIATE


-- ------------------------------------------------------------------------- HR-EMP_DETAILS_VIEW-I-01 Payroll by country
-- Finance books the monthly salary cost per country, grouped under its region.
SELECT REGION_NAME, COUNTRY_NAME, COUNT(*) AS HEADCOUNT, SUM(SALARY) AS MONTHLY_COST
FROM EMP_DETAILS_VIEW
GROUP BY REGION_NAME, COUNTRY_NAME
ORDER BY REGION_NAME ASC, MONTHLY_COST DESC
;


-- ============================================================================================================ ADVANCED


-- ------------------------------------------------------------------- HR-EMP_DETAILS_VIEW-A-01 Best-paid in each region
-- Each regional director is told who the best-paid people in their region are — the three highest salaries, ties
-- included.
SELECT REGION_NAME, EMPLOYEE_ID, LAST_NAME, JOB_TITLE, SALARY, RNK
FROM (SELECT v.*, DENSE_RANK() OVER (PARTITION BY REGION_NAME ORDER BY SALARY DESC) AS RNK
      FROM EMP_DETAILS_VIEW v)
WHERE RNK <= 3
ORDER BY REGION_NAME ASC, RNK ASC, LAST_NAME ASC
;
