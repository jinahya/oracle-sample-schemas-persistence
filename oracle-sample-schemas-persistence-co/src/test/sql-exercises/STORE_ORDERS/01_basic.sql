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
-- Exercises on STORE_ORDERS, a problem per query that reads STORE_ORDERS alone, at the basic level.
--
-- These are the basic SQL solutions of the 'STORE_ORDERS' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- STORE_ORDERS aggregates the orders with GROUPING SETS. TOTAL tells the rows apart: it is null on a row per store and
-- status, STORE TOTAL on a row per store, STATUS TOTAL on a row per status, and GRAND TOTAL on the one row for the
-- chain. StoreOrder is a plain class, not an entity, and is not in the persistence unit, so the problems in this
-- section are SQL only; from Jakarta Persistence, run them as native queries.
--


-- ------------------------------------------------------------------------------------------------ CO_STORE_ORDERS_B_01
-- The regional manager wants each store's number of orders and sales, best first, with the address to put on the
-- report.
SELECT store_name, address, order_count, total_sales
FROM store_orders
WHERE total = 'STORE TOTAL'
ORDER BY total_sales DESC
;
