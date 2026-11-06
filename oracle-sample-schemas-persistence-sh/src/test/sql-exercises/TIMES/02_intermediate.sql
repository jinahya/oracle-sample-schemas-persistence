-- #%L
-- sh
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
-- Exercises on TIMES, a problem per query that reads TIMES alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'TIMES' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------------- SH_TIMES_I_01
-- Finance wants the first and last day, and the number of days, of each fiscal month of fiscal year :fiscalYear.
SELECT FISCAL_MONTH_DESC, MIN(TIME_ID) AS FIRST_DAY, MAX(TIME_ID) AS LAST_DAY, COUNT(*) AS DAYS
FROM TIMES
WHERE FISCAL_YEAR = :fiscalYear
GROUP BY FISCAL_MONTH_DESC
ORDER BY FISCAL_MONTH_DESC
;
