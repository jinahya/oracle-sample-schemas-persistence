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
-- Exercises on PROMOTIONS, a problem per query that reads PROMOTIONS alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'PROMOTIONS' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- -------------------------------------------------------------------------------------------------- SH_PROMOTIONS_A_01
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
