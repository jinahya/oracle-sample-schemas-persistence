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
-- Exercises on PRODUCTS, a problem per query that reads PRODUCTS alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'PRODUCTS' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ---------------------------------------------------------------------------------------------------- SH_PRODUCTS_A_01
-- The product line managers want the most expensive product of every subcategory, by list price.
SELECT p.PROD_CATEGORY, p.PROD_SUBCATEGORY, p.PROD_ID, p.PROD_NAME, p.PROD_LIST_PRICE
FROM PRODUCTS p
WHERE p.PROD_LIST_PRICE = (SELECT MAX(p2.PROD_LIST_PRICE)
                           FROM PRODUCTS p2
                           WHERE p2.PROD_SUBCATEGORY_ID = p.PROD_SUBCATEGORY_ID)
ORDER BY p.PROD_CATEGORY, p.PROD_SUBCATEGORY, p.PROD_ID
;
