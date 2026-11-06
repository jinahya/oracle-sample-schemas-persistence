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
-- Exercises on JOB_HISTORY, a problem per query that reads JOB_HISTORY alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'JOB_HISTORY' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------- HR_JOB_HISTORY_I_01
-- Workforce planning wants, per job, how many times someone has moved on from it, and how many different people that
-- was.
SELECT JOB_ID, COUNT(*) AS POSITIONS, COUNT(DISTINCT EMPLOYEE_ID) AS PEOPLE
FROM JOB_HISTORY
GROUP BY JOB_ID
ORDER BY POSITIONS DESC, JOB_ID ASC
;


-- ------------------------------------------------------------------------------------------------- HR_JOB_HISTORY_I_02
-- HR wants the employees with more than one earlier position, with the first start and the last end on record.
SELECT EMPLOYEE_ID,
       COUNT(*)        AS POSITIONS,
       MIN(START_DATE) AS FIRST_START,
       MAX(END_DATE)   AS LAST_END
FROM JOB_HISTORY
GROUP BY EMPLOYEE_ID
HAVING COUNT(*) > 1
ORDER BY EMPLOYEE_ID ASC
;


-- ------------------------------------------------------------------------------------------------- HR_JOB_HISTORY_I_03
-- Workforce planning wants to know how long people stay in a job before they move on: per job, the number of earlier
-- positions and the shortest, average and longest of them in whole days, the longest average first. `TRUNC` takes each
-- date to its day, since the trigger writes `END_DATE` with a time.
SELECT JOB_ID,
       COUNT(*)                                            AS POSITIONS,
       MIN(TRUNC(END_DATE) - TRUNC(START_DATE) + 1)        AS SHORTEST_DAYS,
       ROUND(AVG(TRUNC(END_DATE) - TRUNC(START_DATE) + 1)) AS AVERAGE_DAYS,
       MAX(TRUNC(END_DATE) - TRUNC(START_DATE) + 1)        AS LONGEST_DAYS
FROM JOB_HISTORY
GROUP BY JOB_ID
ORDER BY AVERAGE_DAYS DESC, JOB_ID ASC
;
