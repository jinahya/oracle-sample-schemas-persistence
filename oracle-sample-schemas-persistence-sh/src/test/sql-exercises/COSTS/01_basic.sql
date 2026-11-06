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
-- Exercises on COSTS, a problem per query that reads COSTS alone, at the basic level.
--
-- These are the basic SQL solutions of the 'COSTS' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------------- SH_COSTS_B_01
-- Finance wants every cost record between :fromDay and :toDay whose unit price is below its unit cost: a product priced
-- at a loss on that day, through that channel, under that promotion.
SELECT TIME_ID, PROD_ID, CHANNEL_ID, PROMO_ID, UNIT_COST, UNIT_PRICE
FROM COSTS
WHERE TIME_ID BETWEEN :fromDay AND :toDay
  AND UNIT_PRICE < UNIT_COST
ORDER BY TIME_ID, PROD_ID, CHANNEL_ID, PROMO_ID
;
