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
-- Exercises on PRODUCTS, a problem per query that reads PRODUCTS alone, at the basic level.
--
-- These are the basic SQL solutions of the 'PRODUCTS' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ---------------------------------------------------------------------------------------------------- SH_PRODUCTS_B_01
-- The product line manager for :category (e.g. Golf, Tennis) is reviewing the range between :minPrice and :maxPrice and
-- wants those products, most expensive first.
SELECT PROD_ID, PROD_NAME, PROD_SUBCATEGORY, PROD_LIST_PRICE, PROD_MIN_PRICE
FROM PRODUCTS
WHERE PROD_CATEGORY = :category
  AND PROD_LIST_PRICE BETWEEN :minPrice AND :maxPrice
ORDER BY PROD_LIST_PRICE DESC, PROD_NAME
;
