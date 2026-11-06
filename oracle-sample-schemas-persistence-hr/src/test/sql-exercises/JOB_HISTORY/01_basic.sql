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
-- For an audit, HR wants every earlier position that ended within a given period, the from and to dates both included,
-- the earliest endings first. The schema's trigger writes `END_DATE` as `SYSDATE`, which has a time of day, so the
-- period runs up to, not including, the day after `:toDate`.
SELECT EMPLOYEE_ID, START_DATE, END_DATE, JOB_ID, DEPARTMENT_ID
FROM JOB_HISTORY
WHERE END_DATE >= TRUNC(:fromDate)
  AND END_DATE < TRUNC(:toDate) + 1
ORDER BY END_DATE ASC, EMPLOYEE_ID ASC, START_DATE ASC
;
