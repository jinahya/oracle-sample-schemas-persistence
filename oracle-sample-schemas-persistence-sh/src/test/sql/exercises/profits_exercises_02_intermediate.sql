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
-- Exercises on PROFITS, a problem per query that reads PROFITS alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'PROFITS' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ----------------------------------------------------------------------------------------------------- SH_PROFITS_I_01
-- Finance wants the ten products with the lowest margin ratio between :fromDay and :toDay, with the revenue and margin
-- behind it.
SELECT PROD_ID,
       SUM(AMOUNT_SOLD)                                 AS REVENUE,
       SUM(AMOUNT_SOLD - TOTAL_COST)                    AS MARGIN,
       SUM(AMOUNT_SOLD - TOTAL_COST) / SUM(AMOUNT_SOLD) AS MARGIN_RATIO
FROM PROFITS
WHERE TIME_ID BETWEEN :fromDay AND :toDay
GROUP BY PROD_ID
ORDER BY MARGIN_RATIO
    FETCH FIRST 10 ROWS ONLY
;
