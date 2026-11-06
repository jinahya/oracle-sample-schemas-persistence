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
-- Exercises on JOBS, a problem per query that reads JOBS alone, at the basic level.
--
-- These are the basic SQL solutions of the 'JOBS' section of EXERCISES.asciidoc, which also states each problem in full
-- and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- -------------------------------------------------------------------------------------------------------- HR_JOBS_B_01
-- Compensation is reviewing the job catalogue. They want it twice: by minimum salary, lowest first, with any job that
-- has no minimum listed first; and by maximum salary, highest first, with any job that has no maximum listed last.
--
-- 4.10. ORDER BY Clause
--
-- The keyword NULLS specifies the ordering of null values, either FIRST or LAST.
--
-- * FIRST means that results are sorted so that all null values occur before all non-null values.
--
-- * LAST means that results are sorted so that all null values occur after all non-null values.
--
-- If NULLS is not specified, the database determines whether null values occur first or last.
SELECT *
FROM JOBS
ORDER BY MIN_SALARY ASC NULLS FIRST, JOB_ID ASC
;

SELECT *
FROM JOBS
ORDER BY MAX_SALARY DESC NULLS LAST, JOB_ID ASC
;


-- -------------------------------------------------------------------------------------------------------- HR_JOBS_B_02
-- Workforce planning wants every clerk job in the catalogue; their ids all end in _CLERK.
SELECT *
FROM JOBS
WHERE JOB_ID LIKE '%\_CLERK' ESCAPE '\'
ORDER BY JOB_ID ASC
;


-- -------------------------------------------------------------------------------------------------------- HR_JOBS_B_03
-- A recruiter has a salary figure in mind and asks which jobs' bands it falls within.
SELECT JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY
FROM JOBS
WHERE :salary BETWEEN MIN_SALARY AND MAX_SALARY
ORDER BY MIN_SALARY ASC, JOB_ID ASC
;


-- -------------------------------------------------------------------------------------------------------- HR_JOBS_B_04
-- Nothing in the schema makes a job's salary band complete, positive, or its minimum below its maximum, which the
-- exercises on bands assume. Find the jobs with a bound missing, a bound not above `0`, or a minimum above the maximum,
-- by `JOB_ID`; the check passes when nothing comes back.
SELECT JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY
FROM JOBS
WHERE MIN_SALARY IS NULL
   OR MAX_SALARY IS NULL
   OR MIN_SALARY <= 0
   OR MAX_SALARY <= 0
   OR MIN_SALARY > MAX_SALARY
ORDER BY JOB_ID ASC
;
