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
-- Exercises on EMP_DETAILS_VIEW, a problem per query that reads EMP_DETAILS_VIEW alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'EMP_DETAILS_VIEW' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- EmpDetailsView maps this view, but is not listed in the persistence units, so the problems here are solved in SQL
-- only. The view inner-joins all six tables, which leaves out the employee without a department.
--


-- -------------------------------------------------------------------------------------------- HR_EMP_DETAILS_VIEW_A_01
-- Each regional director is told who the best-paid people in their region are: the three highest salaries, ties
-- included, ordered by last name and `EMPLOYEE_ID`. Salaries not recorded are left out, so they never take a rank.
SELECT REGION_NAME, EMPLOYEE_ID, LAST_NAME, JOB_TITLE, SALARY, RNK
FROM (SELECT v.*, DENSE_RANK() OVER (PARTITION BY REGION_NAME ORDER BY SALARY DESC) AS RNK
      FROM EMP_DETAILS_VIEW v
      WHERE SALARY IS NOT NULL)
WHERE RNK <= 3
ORDER BY REGION_NAME ASC, RNK ASC, LAST_NAME ASC, EMPLOYEE_ID ASC
;


-- -------------------------------------------------------------------------------------------- HR_EMP_DETAILS_VIEW_A_02
-- Finance wants the monthly salary cost per country, with a subtotal for each region and a grand total. `GROUP BY
-- ROLLUP` adds the subtotal rows, and `GROUPING` tells them from the others.
SELECT REGION_NAME,
       COUNTRY_NAME,
       COUNT(*)                               AS HEADCOUNT,
       SUM(SALARY)                            AS MONTHLY_COST,
       GROUPING_ID(REGION_NAME, COUNTRY_NAME) AS SUBTOTAL_LEVEL
FROM EMP_DETAILS_VIEW
GROUP BY ROLLUP (REGION_NAME, COUNTRY_NAME)
ORDER BY GROUPING(REGION_NAME) ASC, REGION_NAME ASC, GROUPING(COUNTRY_NAME) ASC, COUNTRY_NAME ASC
;
