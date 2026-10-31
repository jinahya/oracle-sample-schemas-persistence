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
-- Exercises on JOB_HISTORY, a problem per query that reads JOB_HISTORY alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'JOB_HISTORY' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------- HR_JOB_HISTORY_A_01
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


-- ------------------------------------------------------------------------------------------------- HR_JOB_HISTORY_A_02
-- An auditor checks the history's integrity: no employee should have two earlier positions whose dates overlap.
SELECT a.EMPLOYEE_ID, a.START_DATE, a.END_DATE, b.START_DATE AS OTHER_START, b.END_DATE AS OTHER_END
FROM JOB_HISTORY a
         JOIN JOB_HISTORY b ON b.EMPLOYEE_ID = a.EMPLOYEE_ID
    AND b.START_DATE > a.START_DATE
    AND b.START_DATE <= a.END_DATE
ORDER BY a.EMPLOYEE_ID ASC, a.START_DATE ASC
;
