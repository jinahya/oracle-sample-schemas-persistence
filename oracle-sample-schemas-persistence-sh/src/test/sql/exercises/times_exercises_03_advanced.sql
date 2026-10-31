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
-- Exercises on TIMES, a problem per query that reads TIMES alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'TIMES' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------------- SH_TIMES_A_01
-- TIMES repeats the length and the last day of each fiscal month on every day of it (DAYS_IN_FIS_MONTH,
-- END_OF_FIS_MONTH). Finance uses them for per-day averages, and wants the fiscal months where they disagree with the
-- days the table actually holds.
SELECT FISCAL_MONTH_DESC, COUNT(*) AS DAYS, DAYS_IN_FIS_MONTH, MAX(TIME_ID) AS LAST_DAY, END_OF_FIS_MONTH
FROM TIMES
GROUP BY FISCAL_MONTH_DESC, DAYS_IN_FIS_MONTH, END_OF_FIS_MONTH
HAVING COUNT(*) <> DAYS_IN_FIS_MONTH
    OR MAX(TIME_ID) <> END_OF_FIS_MONTH
ORDER BY FISCAL_MONTH_DESC
;
