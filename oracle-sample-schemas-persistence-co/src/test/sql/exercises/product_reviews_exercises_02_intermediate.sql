-- #%L
-- co
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
-- Exercises on PRODUCT_REVIEWS, a problem per query that reads PRODUCT_REVIEWS alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'PRODUCT_REVIEWS' section of EXERCISES.asciidoc, which also states
-- each problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- PRODUCT_REVIEWS turns the reviews array of each product's JSON into rows. ProductReview is a plain class, not an
-- entity, and is not in the persistence unit, so the problems in this section are SQL only; from Jakarta Persistence,
-- run them as native queries. A product with no reviews has one row whose RATING and REVIEW are null.
--


-- --------------------------------------------------------------------------------------------- CO_PRODUCT_REVIEWS_I_01
-- Quality assurance wants the products where more than half of the reviews give 3 or less, with the number of each.
SELECT product_name,
       COUNT(rating)                           AS reviews,
       COUNT(CASE WHEN rating <= 3 THEN 1 END) AS poor_reviews
FROM product_reviews
GROUP BY product_name
HAVING COUNT(CASE WHEN rating <= 3 THEN 1 END) * 2 > COUNT(rating)
ORDER BY poor_reviews DESC, product_name
;
