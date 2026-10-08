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
-- Exercises on CAL_MONTH_SALES_MV, a problem per query that reads CAL_MONTH_SALES_MV alone; Basic, then Intermediate,
-- then Advanced.
--
-- These are the SQL solutions of the 'CAL_MONTH_SALES_MV' section of EXERCISES.adoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- -------------------------------------------------------------- SH-CAL_MONTH_SALES_MV-B-01 Monthly revenue at a glance
-- The sales director wants total revenue for each month between :fromMonth and :toMonth (YYYY-MM, e.g. 2021-01 and
-- 2021-12), without waiting for a scan of the facts table.
SELECT CALENDAR_MONTH_DESC, DOLLARS
FROM CAL_MONTH_SALES_MV
WHERE CALENDAR_MONTH_DESC BETWEEN :fromMonth AND :toMonth
ORDER BY CALENDAR_MONTH_DESC
;


-- ======================================================================================================== INTERMEDIATE


-- ----------------------------------------------------------------- SH-CAL_MONTH_SALES_MV-I-01 Months above the average
-- The sales director wants the months between :fromMonth and :toMonth whose revenue beat the average month of that same
-- period, best first.
SELECT CALENDAR_MONTH_DESC, DOLLARS
FROM CAL_MONTH_SALES_MV
WHERE CALENDAR_MONTH_DESC BETWEEN :fromMonth AND :toMonth
  AND DOLLARS > (SELECT AVG(DOLLARS)
                 FROM CAL_MONTH_SALES_MV
                 WHERE CALENDAR_MONTH_DESC BETWEEN :fromMonth AND :toMonth)
ORDER BY DOLLARS DESC
;


-- ============================================================================================================ ADVANCED


-- --------------------------------------------------------- SH-CAL_MONTH_SALES_MV-A-01 Running total of monthly revenue
-- Finance wants each month's revenue between :fromMonth and :toMonth, with the year-to-date running total next to it.
SELECT CALENDAR_MONTH_DESC,
       DOLLARS,
       SUM(DOLLARS) OVER (ORDER BY CALENDAR_MONTH_DESC) AS RUNNING_TOTAL
FROM CAL_MONTH_SALES_MV
WHERE CALENDAR_MONTH_DESC BETWEEN :fromMonth AND :toMonth
ORDER BY CALENDAR_MONTH_DESC
;
