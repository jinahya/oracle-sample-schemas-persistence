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
-- Exercises on PRODUCTS, a problem per query that reads PRODUCTS alone, at the basic level.
--
-- These are the basic SQL solutions of the 'PRODUCTS' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ---------------------------------------------------------------------------------------------------- CO_PRODUCTS_B_01
-- A merchandiser is building a "gifts between $X and $Y" page. List the products whose list price lies in the range,
-- cheapest first, and by name where prices tie.
SELECT product_id, product_name, unit_price
FROM products
WHERE unit_price BETWEEN :minPrice AND :maxPrice
ORDER BY unit_price, product_name
;


-- ---------------------------------------------------------------------------------------------------- CO_PRODUCTS_B_02
-- Print the catalogue from the most to the least expensive. UNIT_PRICE is nullable, and a product without a price yet
-- belongs at the bottom of the list, not the top.
SELECT product_id, product_name, unit_price
FROM products
ORDER BY unit_price DESC NULLS LAST, product_name
;


-- ---------------------------------------------------------------------------------------------------- CO_PRODUCTS_B_03
-- Before the catalogue goes to print, the merchandiser wants every product that has no list price.
SELECT product_id, product_name
FROM products
WHERE unit_price IS NULL
ORDER BY product_name
;
