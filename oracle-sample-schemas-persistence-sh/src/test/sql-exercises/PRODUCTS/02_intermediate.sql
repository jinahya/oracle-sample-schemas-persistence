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
-- Exercises on PRODUCTS, a problem per query that reads PRODUCTS alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'PRODUCTS' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ---------------------------------------------------------------------------------------------------- SH_PRODUCTS_I_01
-- Pricing wants the products whose list price is above the average list price of the whole catalogue.
SELECT PROD_ID, PROD_NAME, PROD_CATEGORY, PROD_LIST_PRICE
FROM PRODUCTS
WHERE PROD_LIST_PRICE > (SELECT AVG(PROD_LIST_PRICE) FROM PRODUCTS)
ORDER BY PROD_LIST_PRICE DESC
;


-- ---------------------------------------------------------------------------------------------------- SH_PRODUCTS_I_02
-- Pricing wants, for each product subcategory, how many products it has and how far, on average and at most, their list
-- price can be cut before it reaches the minimum price, the largest average headroom first.
SELECT PROD_CATEGORY,
       PROD_SUBCATEGORY_ID,
       PROD_SUBCATEGORY,
       COUNT(*)                                                                         AS PRODUCTS,
       ROUND(100 * AVG((PROD_LIST_PRICE - PROD_MIN_PRICE) / NULLIF(PROD_LIST_PRICE, 0)), 2) AS AVG_HEADROOM_PCT,
       MAX(PROD_LIST_PRICE - PROD_MIN_PRICE)                                            AS MAX_HEADROOM
FROM PRODUCTS
GROUP BY PROD_CATEGORY, PROD_SUBCATEGORY_ID, PROD_SUBCATEGORY
ORDER BY AVG_HEADROOM_PCT DESC NULLS LAST, PROD_SUBCATEGORY_ID
;


-- ---------------------------------------------------------------------------------------------------- SH_PRODUCTS_I_03
-- Purchasing wants, for each supplier, how many products and categories it supplies, and its lowest and highest list
-- prices, the largest catalogue first.
SELECT SUPPLIER_ID,
       COUNT(*)                         AS PRODUCTS,
       COUNT(DISTINCT PROD_CATEGORY_ID) AS CATEGORIES,
       MIN(PROD_LIST_PRICE)             AS LOWEST_PRICE,
       MAX(PROD_LIST_PRICE)             AS HIGHEST_PRICE
FROM PRODUCTS
GROUP BY SUPPLIER_ID
ORDER BY PRODUCTS DESC, SUPPLIER_ID
;
