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
-- Exercises on JOBS, a problem per query that reads JOBS alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'JOBS' section of EXERCISES.adoc, which also states each problem in full and gives
-- its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- --------------------------------------------------------------------- HR-JOBS-B-01 Salary bands, unset values flagged
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


-- ------------------------------------------------------------------------------------------ HR-JOBS-B-02 Clerical jobs
-- Workforce planning wants every clerk job in the catalogue; their ids all end in _CLERK.
SELECT *
FROM JOBS
WHERE JOB_ID LIKE '%\_CLERK' ESCAPE '\'
ORDER BY JOB_ID ASC
;


-- ------------------------------------------------------------------------------------- HR-JOBS-B-03 Jobs an offer fits
-- A recruiter has a salary figure in mind and asks which jobs' bands it falls within.
SELECT JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY
FROM JOBS
WHERE :salary BETWEEN MIN_SALARY AND MAX_SALARY
ORDER BY MIN_SALARY ASC, JOB_ID ASC
;


-- ======================================================================================================== INTERMEDIATE


-- --------------------------------------------------------------------------------------- HR-JOBS-I-01 Widest pay bands
-- Compensation suspects some bands are too loose. They want the jobs whose maximum is at least twice the minimum, with
-- the band's width and that ratio, widest ratio first.
SELECT JOB_ID, JOB_TITLE, MAX_SALARY - MIN_SALARY AS WIDTH, ROUND(MAX_SALARY / MIN_SALARY, 2) AS RATIO
FROM JOBS
WHERE MAX_SALARY >= 2 * MIN_SALARY
ORDER BY RATIO DESC, JOB_ID ASC
;


-- ------------------------------------------------------------------------------ HR-JOBS-I-02 The catalogue at a glance
-- Workforce planning's summary of the job catalogue: how many jobs, the lowest minimum, the highest maximum, and the
-- average band midpoint.
SELECT COUNT(*)                                          AS JOBS,
       MIN(MIN_SALARY)                                   AS LOWEST,
       MAX(MAX_SALARY)                                   AS HIGHEST,
       ROUND((AVG(MIN_SALARY) + AVG(MAX_SALARY)) / 2, 2) AS AVERAGE_MIDPOINT
FROM JOBS
;


-- ============================================================================================================ ADVANCED


-- ------------------------------------------------------------------------------ HR-JOBS-A-01 Bands inside another band
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


-- --------------------------------------------------------------------------------------- HR-JOBS-A-02 The next step up
-- For career planning, HR wants, for each job, the job whose band starts lowest above the top of this job's band — the
-- next rung on the pay ladder.
SELECT j.JOB_ID, j.MAX_SALARY, n.JOB_ID AS NEXT_JOB, n.MIN_SALARY AS NEXT_MIN
FROM JOBS j
         JOIN JOBS n ON n.MIN_SALARY = (SELECT MIN(x.MIN_SALARY) FROM JOBS x WHERE x.MIN_SALARY > j.MAX_SALARY)
ORDER BY j.MAX_SALARY ASC, j.JOB_ID ASC, n.JOB_ID ASC
;
