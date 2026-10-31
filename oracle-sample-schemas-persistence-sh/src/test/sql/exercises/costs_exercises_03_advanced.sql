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
-- Exercises on COSTS, a problem per query that reads COSTS alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'COSTS' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------------- SH_COSTS_A_01
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
