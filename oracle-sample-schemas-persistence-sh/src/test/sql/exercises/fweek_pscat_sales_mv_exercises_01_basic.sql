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
-- Exercises on FWEEK_PSCAT_SALES_MV, a problem per query that reads FWEEK_PSCAT_SALES_MV alone, at the basic level.
--
-- These are the basic SQL solutions of the 'FWEEK_PSCAT_SALES_MV' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ---------------------------------------------------------------------------------------- SH_FWEEK_PSCAT_SALES_MV_B_01
-- The :channelId channel manager wants the sales of the week ending :weekEndingDay (a Sunday, e.g. 2021-01-17) through
-- that channel, by subcategory and promotion, best first.
SELECT PROD_SUBCATEGORY, PROMO_ID, DOLLARS
FROM FWEEK_PSCAT_SALES_MV
WHERE WEEK_ENDING_DAY = :weekEndingDay
  AND CHANNEL_ID = :channelId
ORDER BY DOLLARS DESC NULLS LAST, PROD_SUBCATEGORY
;
