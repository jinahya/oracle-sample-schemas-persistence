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
-- Exercises on PROMOTIONS, a problem per query that reads PROMOTIONS alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'PROMOTIONS' section of EXERCISES.adoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- ---------------------------------------------------------------------- SH-PROMOTIONS-B-01 Promotions running on a day
-- A store manager asks which promotions are running on :day, ending soonest first.
SELECT PROMO_ID, PROMO_NAME, PROMO_CATEGORY, PROMO_BEGIN_DATE, PROMO_END_DATE
FROM PROMOTIONS
WHERE :day BETWEEN PROMO_BEGIN_DATE AND PROMO_END_DATE
ORDER BY PROMO_END_DATE, PROMO_NAME
;


-- ------------------------------------------------------------------------------- SH-PROMOTIONS-B-02 Kinds of promotion
-- The promotions team wants the distinct promotion categories it has run (TV, radio, internet, ...).
SELECT DISTINCT PROMO_CATEGORY
FROM PROMOTIONS
ORDER BY PROMO_CATEGORY
;


-- ======================================================================================================== INTERMEDIATE


-- ---------------------------------------------------------------------- SH-PROMOTIONS-I-01 Promotion spend by category
-- The promotions team wants, for each promotion category, the number of promotions it has run and what they cost in
-- total and on average, ignoring the NO PROMOTION placeholder.
SELECT PROMO_CATEGORY, COUNT(*) AS PROMOTIONS, SUM(PROMO_COST) AS TOTAL_COST, AVG(PROMO_COST) AS AVERAGE_COST
FROM PROMOTIONS
WHERE PROMO_CATEGORY <> 'NO PROMOTION'
GROUP BY PROMO_CATEGORY
ORDER BY TOTAL_COST DESC
;


-- ============================================================================================================ ADVANCED


-- ------------------------------------------------------------------------------ SH-PROMOTIONS-A-01 Clashing promotions
-- The promotions team suspects that promotions of the same subcategory run on top of each other and compete for the
-- same customers. For category :promoCategory (e.g. radio), list every pair of promotions of one subcategory whose
-- dates overlap.
SELECT a.PROMO_SUBCATEGORY,
       a.PROMO_ID         AS FIRST_ID,
       a.PROMO_BEGIN_DATE AS FIRST_BEGIN,
       a.PROMO_END_DATE   AS FIRST_END,
       b.PROMO_ID         AS SECOND_ID,
       b.PROMO_BEGIN_DATE AS SECOND_BEGIN,
       b.PROMO_END_DATE   AS SECOND_END
FROM PROMOTIONS a
         JOIN PROMOTIONS b ON b.PROMO_SUBCATEGORY_ID = a.PROMO_SUBCATEGORY_ID
    AND b.PROMO_ID > a.PROMO_ID
    AND b.PROMO_BEGIN_DATE <= a.PROMO_END_DATE
    AND a.PROMO_BEGIN_DATE <= b.PROMO_END_DATE
WHERE a.PROMO_CATEGORY = :promoCategory
ORDER BY a.PROMO_SUBCATEGORY, a.PROMO_BEGIN_DATE, a.PROMO_ID, b.PROMO_ID
;
