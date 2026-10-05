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
-- Exercises on JOB_HISTORY, a problem per query that reads JOB_HISTORY alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'JOB_HISTORY' section of EXERCISES.adoc, which also states each problem in full
-- and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- ---------------------------------------------------------------- HR-JOB_HISTORY-B-01 One employee's earlier positions
-- An employee asks HR for the positions they held before their current one (given the employee id), oldest first.
SELECT START_DATE, END_DATE, JOB_ID, DEPARTMENT_ID
FROM JOB_HISTORY
WHERE EMPLOYEE_ID = :employeeId
ORDER BY START_DATE ASC
;


-- -------------------------------------------------------------------- HR-JOB_HISTORY-B-02 Positions closed in a period
-- For an audit, HR wants every earlier position that ended within a given period (from and to dates, both inclusive).
SELECT EMPLOYEE_ID, START_DATE, END_DATE, JOB_ID, DEPARTMENT_ID
FROM JOB_HISTORY
WHERE END_DATE BETWEEN :fromDate AND :toDate
ORDER BY END_DATE ASC, EMPLOYEE_ID ASC
;


-- ======================================================================================================== INTERMEDIATE


-- -------------------------------------------------------------------------------- HR-JOB_HISTORY-I-01 Turnover per job
-- Workforce planning wants, per job, how many times someone has moved on from it, and how many different people that
-- was.
SELECT JOB_ID, COUNT(*) AS POSITIONS, COUNT(DISTINCT EMPLOYEE_ID) AS PEOPLE
FROM JOB_HISTORY
GROUP BY JOB_ID
ORDER BY POSITIONS DESC, JOB_ID ASC
;


-- ---------------------------------------------------------------------------- HR-JOB_HISTORY-I-02 Moved more than once
-- HR wants the employees with more than one earlier position, with the first start and the last end on record.
SELECT EMPLOYEE_ID, COUNT(*) AS POSITIONS, MIN(START_DATE) AS FIRST_START, MAX(END_DATE) AS LAST_END
FROM JOB_HISTORY
GROUP BY EMPLOYEE_ID
HAVING COUNT(*) > 1
ORDER BY EMPLOYEE_ID ASC
;


-- ============================================================================================================ ADVANCED


-- ------------------------------------------------------------------------------- HR-JOB_HISTORY-A-01 Consecutive moves
-- Internal mobility wants each step recorded within the history: an earlier position, and the earlier position the same
-- employee held next — from which job to which, and when.
SELECT EMPLOYEE_ID, FROM_JOB, TO_JOB, MOVED_ON
FROM (SELECT EMPLOYEE_ID,
             JOB_ID                                                               AS FROM_JOB,
             LEAD(JOB_ID) OVER (PARTITION BY EMPLOYEE_ID ORDER BY START_DATE)     AS TO_JOB,
             LEAD(START_DATE) OVER (PARTITION BY EMPLOYEE_ID ORDER BY START_DATE) AS MOVED_ON
      FROM JOB_HISTORY)
WHERE TO_JOB IS NOT NULL
ORDER BY EMPLOYEE_ID ASC, MOVED_ON ASC
;


-- --------------------------------------------------------------------------- HR-JOB_HISTORY-A-02 Overlapping positions
-- An auditor checks the history's integrity: no employee should have two earlier positions whose dates overlap.
SELECT a.EMPLOYEE_ID, a.START_DATE, a.END_DATE, b.START_DATE AS OTHER_START, b.END_DATE AS OTHER_END
FROM JOB_HISTORY a
         JOIN JOB_HISTORY b ON b.EMPLOYEE_ID = a.EMPLOYEE_ID
    AND b.START_DATE > a.START_DATE
    AND b.START_DATE <= a.END_DATE
ORDER BY a.EMPLOYEE_ID ASC, a.START_DATE ASC
;
