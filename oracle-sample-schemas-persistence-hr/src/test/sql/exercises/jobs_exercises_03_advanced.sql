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
-- Exercises on JOBS, a problem per query that reads JOBS alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'JOBS' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- -------------------------------------------------------------------------------------------------------- HR_JOBS_A_01
-- Compensation suspects redundant grades: which jobs have a band that lies entirely within another job's band?
SELECT i.JOB_ID,
       i.MIN_SALARY,
       i.MAX_SALARY,
       o.JOB_ID     AS WITHIN_JOB,
       o.MIN_SALARY AS WITHIN_MIN,
       o.MAX_SALARY AS WITHIN_MAX
FROM JOBS i
         JOIN JOBS o ON o.JOB_ID <> i.JOB_ID
    AND o.MIN_SALARY <= i.MIN_SALARY
    AND i.MAX_SALARY <= o.MAX_SALARY
ORDER BY i.JOB_ID ASC, o.JOB_ID ASC
;


-- -------------------------------------------------------------------------------------------------------- HR_JOBS_A_02
-- For career planning, HR wants, for each job, the job whose band starts lowest above the top of this job's band — the
-- next rung on the pay ladder.
SELECT j.JOB_ID, j.MAX_SALARY, n.JOB_ID AS NEXT_JOB, n.MIN_SALARY AS NEXT_MIN
FROM JOBS j
         JOIN JOBS n ON n.MIN_SALARY = (SELECT MIN(x.MIN_SALARY) FROM JOBS x WHERE x.MIN_SALARY > j.MAX_SALARY)
ORDER BY j.MAX_SALARY ASC, j.JOB_ID ASC, n.JOB_ID ASC
;
