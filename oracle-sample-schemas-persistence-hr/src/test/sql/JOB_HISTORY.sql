---
-- #%L
-- hr
-- %%
-- Copyright (C) 2024 - 2025 Jinahya, Inc.
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
-- --------------------------------------------------------------------------------------------------------- EMPLOYEE_ID

-- ---------------------------------------------------------------------------------------------------------- START_DATE

-- ------------------------------------------------------------------------------------------------------------ END_DATE
-- finds abnormal rows: JOB_HISTORY rows which end after now.
-- a JOB_HISTORY row records an assignment the employee has already left -- the current one is in EMPLOYEES -- so it
-- cannot end in the future. The UPDATE_JOB_HISTORY trigger writes END_DATE = SYSDATE, so its rows can never match.
-- no rows: every history row has ended.
SELECT EMPLOYEE_ID, START_DATE, END_DATE, JOB_ID, DEPARTMENT_ID
FROM JOB_HISTORY
WHERE END_DATE > SYSDATE
ORDER BY EMPLOYEE_ID, START_DATE
;


-- -------------------------------------------------------------------------------------------------------------- JOB_ID

-- ------------------------------------------------------------------------------------------------------- DEPARTMENT_ID


-- ---------------------------------------------------------------------------------------------------------------------
SELECT COUNT(1)
FROM JOB_HISTORY
WHERE START_DATE <= END_DATE
;

SELECT COUNT(1)
FROM JOB_HISTORY
WHERE START_DATE > END_DATE
;

-- finds abnormal rows: JOB_HISTORY rows which overlap the same employee's previous row, by START_DATE.
-- an employee holds one past assignment at a time, so each of their rows should start after the previous one ends; a
-- row starting on or before the previous row's END_DATE is abnormal. An employee's first row has no previous row and
-- never matches. No constraint enforces this.
-- no rows: no employee's history overlaps.
SELECT EMPLOYEE_ID, PREVIOUS_START_DATE, PREVIOUS_END_DATE, START_DATE, END_DATE
FROM (SELECT EMPLOYEE_ID,
             START_DATE,
             END_DATE,
             LAG(START_DATE) OVER (PARTITION BY EMPLOYEE_ID ORDER BY START_DATE) AS PREVIOUS_START_DATE, LAG(END_DATE) OVER (PARTITION BY EMPLOYEE_ID ORDER BY START_DATE)   AS PREVIOUS_END_DATE
      FROM JOB_HISTORY)
WHERE PREVIOUS_END_DATE >= START_DATE
ORDER BY EMPLOYEE_ID, START_DATE
;

create PROCEDURE secure_dml IS
BEGIN
    IF
TO_CHAR(SYSDATE, 'HH24:MI') NOT BETWEEN '08:00' AND '18:00'
        OR TO_CHAR(SYSDATE, 'DY') IN ('SAT', 'SUN')
    THEN
        RAISE_APPLICATION_ERROR(-20205, 'You may only make changes during normal office hours');
END IF;
END secure_dml;
