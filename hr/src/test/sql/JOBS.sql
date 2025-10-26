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

-- -------------------------------------------------------------------------------------------------------------- JOB_ID

-- ----------------------------------------------------------------------------------------------------------- JOB_TITLE

-- ---------------------------------------------------------------------------------------------------------- MIN_SALARY
SELECT *
FROM JOBS
ORDER BY MIN_SALARY ASC
    FETCH FIRST 10 ROWS ONLY
;

-- ---------------------------------------------------------------------------------------------------------- MAX_SALARY
SELECT *
FROM JOBS
ORDER BY MAX_SALARY ASC
    FETCH FIRST 10 ROWS ONLY
;

SELECT MIN(MIN_SALARY), MAX(MAX_SALARY)
FROM JOBS
;

SELECT *
FROM JOBS
WHERE MIN_SALARY IS NOT NULL
  AND MAX_SALARY IS NOT NULL
  AND MIN_SALARY >= MAX_SALARY
    FETCH FIRST 1 ROWS ONLY
;

SELECT *
FROM JOBS
WHERE MIN_SALARY IS NOT NULL
  AND MAX_SALARY IS NOT NULL
  AND MIN_SALARY <= MAX_SALARY
    FETCH FIRST 10 ROWS ONLY
;

-- min diff
SELECT s.*, s.MAX_SALARY - s.MIN_SALARY
FROM (SELECT *
      FROM JOBS
      ORDER BY (MAX_SALARY - MIN_SALARY) ASC) s
    FETCH FIRST 10 ROWS ONLY
;

-- max diff
SELECT s.*, s.MAX_SALARY - s.MIN_SALARY
FROM (SELECT *
      FROM JOBS
      ORDER BY (MAX_SALARY - MIN_SALARY) DESC) s
    FETCH FIRST 10 ROWS ONLY
;
