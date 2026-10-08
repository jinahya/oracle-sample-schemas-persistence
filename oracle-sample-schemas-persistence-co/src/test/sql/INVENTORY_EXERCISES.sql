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
-- Exercises on INVENTORY, a problem per query that reads INVENTORY alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'INVENTORY' section of EXERCISES.adoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- ------------------------------------------------------------------------ CO-INVENTORY-B-01 How many are on the shelf?
-- A shop assistant asks how many units of a product their store holds.
SELECT product_inventory
FROM inventory
WHERE store_id = :storeId
  AND product_id = :productId
;


-- ----------------------------------------------------------------- CO-INVENTORY-B-02 Where a product is running lowest
-- A merchandiser wants every store's stock of a product, lowest first, to see which stores need it replenished first.
SELECT *
FROM inventory
WHERE product_id = :productId
ORDER BY product_inventory ASC
;


-- ------------------------------------------------------------------------ CO-INVENTORY-B-03 A store's emptiest shelves
-- A store manager wants every product their store stocks, lowest stock first, to plan the next order.
SELECT *
FROM inventory
WHERE store_id = :storeId
ORDER BY product_inventory ASC
;


-- ======================================================================================================== INTERMEDIATE


-- ----------------------------------------------------------------------------- CO-INVENTORY-I-01 What each store holds
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


-- ============================================================================================================ ADVANCED


-- ------------------------------------------------ CO-INVENTORY-A-01 Stores that carry everything another store carries
-- A store is closing for a refit. The regional manager wants the stores that carry every product it carries, to send
-- its customers to.
SELECT DISTINCT i.store_id
FROM inventory i
WHERE i.store_id <> :storeId
  AND NOT EXISTS (SELECT 1
                  FROM inventory r
                  WHERE r.store_id = :storeId
                    AND NOT EXISTS (SELECT 1
                                    FROM inventory x
                                    WHERE x.store_id = i.store_id
                                      AND x.product_id = r.product_id))
ORDER BY i.store_id
;
