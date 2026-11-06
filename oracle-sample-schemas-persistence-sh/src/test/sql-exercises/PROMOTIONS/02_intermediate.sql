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
-- Exercises on PROMOTIONS, a problem per query that reads PROMOTIONS alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'PROMOTIONS' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- -------------------------------------------------------------------------------------------------- SH_PROMOTIONS_I_01
-- The promotions team wants, for each promotion category, the number of promotions it has run and what they cost in
-- total and on average, ignoring the NO PROMOTION placeholder.
SELECT PROMO_CATEGORY,
       COUNT(*)        AS PROMOTIONS,
       SUM(PROMO_COST) AS TOTAL_COST,
       AVG(PROMO_COST) AS AVERAGE_COST
FROM PROMOTIONS
WHERE PROMO_CATEGORY <> 'NO PROMOTION'
GROUP BY PROMO_CATEGORY
ORDER BY TOTAL_COST DESC
;


-- -------------------------------------------------------------------------------------------------- SH_PROMOTIONS_I_02
-- The promotions team is planning next year's calendar and wants to know how long each kind of promotion usually runs:
-- per category, the number of promotions and the shortest, average and longest run in days, ignoring the NO PROMOTION
-- placeholder.
SELECT PROMO_CATEGORY,
       COUNT(*)                                          AS PROMOTIONS,
       MIN(PROMO_END_DATE - PROMO_BEGIN_DATE + 1)        AS SHORTEST_DAYS,
       ROUND(AVG(PROMO_END_DATE - PROMO_BEGIN_DATE + 1)) AS AVERAGE_DAYS,
       MAX(PROMO_END_DATE - PROMO_BEGIN_DATE + 1)        AS LONGEST_DAYS
FROM PROMOTIONS
WHERE PROMO_CATEGORY <> 'NO PROMOTION'
GROUP BY PROMO_CATEGORY
ORDER BY AVERAGE_DAYS DESC, PROMO_CATEGORY
;
