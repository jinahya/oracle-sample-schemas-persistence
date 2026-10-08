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
-- Exercises on PRODUCTS, a problem per query that reads PRODUCTS alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'PRODUCTS' section of EXERCISES.adoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- ------------------------------------------------------------------------- CO-PRODUCTS-B-01 Gift ideas within a budget
-- A merchandiser is building a "gifts between $X and $Y" page. List the products whose list price lies in the range,
-- cheapest first, and by name where prices tie.
SELECT product_id, product_name, unit_price
FROM products
WHERE unit_price BETWEEN :minPrice AND :maxPrice
ORDER BY unit_price, product_name
;


-- ------------------------------------------------------------------- CO-PRODUCTS-B-02 Price list, most expensive first
-- Print the catalogue from the most to the least expensive. UNIT_PRICE is nullable, and a product without a price yet
-- belongs at the bottom of the list, not the top.
SELECT product_id, product_name, unit_price
FROM products
ORDER BY unit_price DESC NULLS LAST, product_name
;


-- ----------------------------------------------------------------- CO-PRODUCTS-B-03 Products still waiting for a price
-- Before the catalogue goes to print, the merchandiser wants every product that has no list price.
SELECT product_id, product_name
FROM products
WHERE unit_price IS NULL
ORDER BY product_name
;


-- ======================================================================================================== INTERMEDIATE


-- ---------------------------------------------------------------------- CO-PRODUCTS-I-01 Products priced above average
-- Merchandising wants the products whose list price is above the catalogue's average list price.
SELECT product_id, product_name, unit_price
FROM products
WHERE unit_price > (SELECT AVG(unit_price) FROM products)
ORDER BY unit_price DESC
;


-- ---------------------------------------------------------------------------- CO-PRODUCTS-I-02 The range by department
-- The buyer for each department -- boys', girls', men's and women's -- wants the number of products and the price range
-- of their department. The department is the gender field of the JSON in PRODUCT_DETAILS.
SELECT JSON_VALUE(product_details, '$.gender') AS department,
       COUNT(*)                                AS products,
       MIN(unit_price)                         AS cheapest,
       MAX(unit_price)                         AS dearest,
       ROUND(AVG(unit_price), 2)               AS average_price
FROM products
GROUP BY JSON_VALUE(product_details, '$.gender')
ORDER BY department
;


-- ============================================================================================================ ADVANCED


-- ----------------------------------------------------- CO-PRODUCTS-A-01 The ten most expensive products, ties included
-- The window display takes the ten most expensive products, and a product priced the same as the tenth goes in too.
SELECT product_id, product_name, unit_price
FROM products
WHERE unit_price IS NOT NULL
ORDER BY unit_price DESC
    FETCH FIRST 10 ROWS
WITH TIES
;
