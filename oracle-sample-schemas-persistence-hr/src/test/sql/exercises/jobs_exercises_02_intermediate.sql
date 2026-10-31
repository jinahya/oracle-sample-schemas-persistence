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
-- Exercises on JOBS, a problem per query that reads JOBS alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'JOBS' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- -------------------------------------------------------------------------------------------------------- HR_JOBS_I_01
-- Compensation suspects some bands are too loose. They want the jobs whose maximum is at least twice the minimum, with
-- the band's width and that ratio, widest ratio first.
SELECT JOB_ID, JOB_TITLE, MAX_SALARY - MIN_SALARY AS WIDTH, ROUND(MAX_SALARY / MIN_SALARY, 2) AS RATIO
FROM JOBS
WHERE MAX_SALARY >= 2 * MIN_SALARY
ORDER BY RATIO DESC, JOB_ID ASC
;


-- -------------------------------------------------------------------------------------------------------- HR_JOBS_I_02
-- Workforce planning's summary of the job catalogue: how many jobs, the lowest minimum, the highest maximum, and the
-- average band midpoint.
SELECT COUNT(*)                                          AS JOBS,
       MIN(MIN_SALARY)                                   AS LOWEST,
       MAX(MAX_SALARY)                                   AS HIGHEST,
       ROUND((AVG(MIN_SALARY) + AVG(MAX_SALARY)) / 2, 2) AS AVERAGE_MIDPOINT
FROM JOBS
;
