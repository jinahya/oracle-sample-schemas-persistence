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
-- Exercises on PRODUCTS, a problem per query that reads PRODUCTS alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'PRODUCTS' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ---------------------------------------------------------------------------------------------------- CO_PRODUCTS_I_01
-- Merchandising wants the products whose list price is above the catalogue's average list price.
SELECT product_id, product_name, unit_price
FROM products
WHERE unit_price > (SELECT AVG(unit_price) FROM products)
ORDER BY unit_price DESC
;


-- ---------------------------------------------------------------------------------------------------- CO_PRODUCTS_I_02
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
