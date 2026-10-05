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
-- Exercises on PRODUCT_REVIEWS, a problem per query that reads PRODUCT_REVIEWS alone; Basic, then Intermediate, then
-- Advanced.
--
-- These are the SQL solutions of the 'PRODUCT_REVIEWS' section of EXERCISES.adoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- PRODUCT_REVIEWS turns the reviews array of each product's JSON into rows. ProductReview is a plain class, not an
-- entity, and is not in the persistence unit, so the problems in this section are SQL only; from Jakarta Persistence,
-- run them as native queries. A product with no reviews has one row whose RATING and REVIEW are null.
--


-- =============================================================================================================== BASIC


-- -------------------------------------------------------------------- CO-PRODUCT_REVIEWS-B-01 Read a product's reviews
-- A shopper asks what other people thought of a product. Show its reviews, the best first.
SELECT rating, review
FROM product_reviews
WHERE product_name = :productName
ORDER BY rating DESC NULLS LAST
;


-- ======================================================================================================== INTERMEDIATE


-- ----------------------------------------------------------- CO-PRODUCT_REVIEWS-I-01 Products with mostly poor reviews
-- Quality assurance wants the products where more than half of the reviews give 3 or less, with the number of each.
SELECT product_name,
       COUNT(rating)                           AS reviews,
       COUNT(CASE WHEN rating <= 3 THEN 1 END) AS poor_reviews
FROM product_reviews
GROUP BY product_name
HAVING COUNT(CASE WHEN rating <= 3 THEN 1 END) * 2 > COUNT(rating)
ORDER BY poor_reviews DESC, product_name
;


-- ============================================================================================================ ADVANCED


-- -------------------------------------------------------------- CO-PRODUCT_REVIEWS-A-01 Each product's harshest review
-- Before a supplier meeting, the buyer wants the lowest-rated review of every product, and how far it falls below the
-- product's average.
SELECT product_name, rating, avg_rating, avg_rating - rating AS below_average, review
FROM (SELECT r.*, ROW_NUMBER() OVER (PARTITION BY r.product_name ORDER BY r.rating, r.review) AS rn
      FROM product_reviews r
      WHERE r.rating IS NOT NULL)
WHERE rn = 1
ORDER BY below_average DESC, product_name
;
