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
-- Exercises on JOB_HISTORY, a problem per query that reads JOB_HISTORY alone, at the basic level.
--
-- These are the basic SQL solutions of the 'JOB_HISTORY' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------- HR_JOB_HISTORY_B_01
-- An employee asks HR for the positions they held before their current one (given the employee id), oldest first.
SELECT START_DATE, END_DATE, JOB_ID, DEPARTMENT_ID
FROM JOB_HISTORY
WHERE EMPLOYEE_ID = :employeeId
ORDER BY START_DATE ASC
;


-- ------------------------------------------------------------------------------------------------- HR_JOB_HISTORY_B_02
-- For an audit, HR wants every earlier position that ended within a given period (from and to dates, both inclusive).
SELECT EMPLOYEE_ID, START_DATE, END_DATE, JOB_ID, DEPARTMENT_ID
FROM JOB_HISTORY
WHERE END_DATE BETWEEN :fromDate AND :toDate
ORDER BY END_DATE ASC, EMPLOYEE_ID ASC
;
