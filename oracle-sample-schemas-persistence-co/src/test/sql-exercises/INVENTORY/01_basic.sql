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
-- Exercises on INVENTORY, a problem per query that reads INVENTORY alone, at the basic level.
--
-- These are the basic SQL solutions of the 'INVENTORY' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- CO_INVENTORY_B_01
-- A shop assistant asks how many units of a product their store holds.
SELECT product_inventory
FROM inventory
WHERE store_id = :storeId
  AND product_id = :productId
;


-- --------------------------------------------------------------------------------------------------- CO_INVENTORY_B_02
-- A merchandiser wants every store's stock of a product, lowest first, to see which stores need it replenished first;
-- stores with the same stock are ordered by `store_id`.
SELECT store_id, product_inventory
FROM inventory
WHERE product_id = :productId
ORDER BY product_inventory, store_id
;


-- --------------------------------------------------------------------------------------------------- CO_INVENTORY_B_04
-- Nothing in the schema keeps `PRODUCT_INVENTORY` from going below `0`, which the replenishment exercises assume. Find
-- the inventory rows with a negative stock, the most negative first; the check passes when nothing comes back.
SELECT inventory_id, store_id, product_id, product_inventory
FROM inventory
WHERE product_inventory < 0
ORDER BY product_inventory, store_id, product_id
;
