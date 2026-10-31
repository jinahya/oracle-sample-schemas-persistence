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
-- Exercises on COSTS, a problem per query that reads COSTS alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'COSTS' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------------- SH_COSTS_I_01
-- Pricing wants, for each product, its average unit cost, average unit price and average unit margin between :fromDay
-- and :toDay, thinnest margin first.
SELECT PROD_ID,
       AVG(UNIT_COST)              AS AVG_COST,
       AVG(UNIT_PRICE)             AS AVG_PRICE,
       AVG(UNIT_PRICE - UNIT_COST) AS AVG_MARGIN
FROM COSTS
WHERE TIME_ID BETWEEN :fromDay AND :toDay
GROUP BY PROD_ID
ORDER BY AVG_MARGIN
;
