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
-- Exercises on COSTS, a problem per query that reads COSTS alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'COSTS' section of EXERCISES.adoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- ------------------------------------------------------------------------------------- SH-COSTS-B-01 Prices below cost
-- Finance wants every cost record between :fromDay and :toDay whose unit price is below its unit cost: a product priced
-- at a loss on that day, through that channel, under that promotion.
SELECT TIME_ID, PROD_ID, CHANNEL_ID, PROMO_ID, UNIT_COST, UNIT_PRICE
FROM COSTS
WHERE TIME_ID BETWEEN :fromDay AND :toDay
  AND UNIT_PRICE < UNIT_COST
ORDER BY TIME_ID, PROD_ID
;


-- ======================================================================================================== INTERMEDIATE


-- ----------------------------------------------------------------------- SH-COSTS-I-01 Average unit margin per product
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


-- ============================================================================================================ ADVANCED


-- ------------------------------------------------------------------------------ SH-COSTS-A-01 When did a price change?
-- The :channelId channel manager wants the days on which the unit price of product :prodId under promotion :promoId
-- (e.g. 3, 120, 999) changed, with the price before and after.
SELECT TIME_ID, PREVIOUS_PRICE, UNIT_PRICE
FROM (SELECT TIME_ID,
             UNIT_PRICE,
             LAG(UNIT_PRICE) OVER (ORDER BY TIME_ID) AS PREVIOUS_PRICE
      FROM COSTS
      WHERE PROD_ID = :prodId
        AND CHANNEL_ID = :channelId
        AND PROMO_ID = :promoId)
WHERE UNIT_PRICE <> PREVIOUS_PRICE
ORDER BY TIME_ID
;
