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
-- Exercises on PRODUCTS, a problem per query that reads PRODUCTS alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'PRODUCTS' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ---------------------------------------------------------------------------------------------------- CO_PRODUCTS_A_01
-- The window display takes the ten most expensive products, and a product priced the same as the tenth goes in too.
SELECT product_id, product_name, unit_price
FROM products
WHERE unit_price IS NOT NULL
ORDER BY unit_price DESC
    FETCH FIRST 10 ROWS
WITH TIES
;
