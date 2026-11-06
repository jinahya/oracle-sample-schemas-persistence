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
-- Exercises on INVENTORY, a problem per query that reads INVENTORY alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'INVENTORY' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- CO_INVENTORY_I_01
-- The regional manager wants, per store, how many products it stocks, how many units it holds in all, and how many of
-- its products are down to zero.
SELECT store_id,
       COUNT(*)                                          AS products,
       SUM(product_inventory)                            AS units,
       COUNT(CASE WHEN product_inventory = 0 THEN 1 END) AS empty_shelves
FROM inventory
GROUP BY store_id
ORDER BY store_id
;
