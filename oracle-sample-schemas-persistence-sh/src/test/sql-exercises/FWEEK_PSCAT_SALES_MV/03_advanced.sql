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
-- Exercises on FWEEK_PSCAT_SALES_MV, a problem per query that reads FWEEK_PSCAT_SALES_MV alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'FWEEK_PSCAT_SALES_MV' section of EXERCISES.asciidoc, which also states
-- each problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ---------------------------------------------------------------------------------------- SH_FWEEK_PSCAT_SALES_MV_A_01
-- The :channelId channel manager (e.g. 3, Direct Sales) wants, for each product subcategory, the week in which it sold
-- best through that channel between :fromDay and :toDay, across all promotions.
SELECT PROD_SUBCATEGORY, WEEK_ENDING_DAY, DOLLARS
FROM (SELECT f.PROD_SUBCATEGORY,
             f.WEEK_ENDING_DAY,
             SUM(f.DOLLARS) AS DOLLARS,
             RANK()            OVER (PARTITION BY f.PROD_SUBCATEGORY ORDER BY SUM(f.DOLLARS) DESC NULLS LAST) AS RNK
      FROM FWEEK_PSCAT_SALES_MV f
      WHERE f.CHANNEL_ID = :channelId
        AND f.WEEK_ENDING_DAY BETWEEN :fromDay AND :toDay
      GROUP BY f.PROD_SUBCATEGORY, f.WEEK_ENDING_DAY)
WHERE RNK = 1
ORDER BY PROD_SUBCATEGORY
;
