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
-- Exercises on STORE_ORDERS, a problem per query that reads STORE_ORDERS alone; Basic, then Intermediate, then
-- Advanced.
--
-- These are the SQL solutions of the 'STORE_ORDERS' section of EXERCISES.adoc, which also states each problem in full
-- and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- STORE_ORDERS aggregates the orders with GROUPING SETS. TOTAL tells the rows apart: it is null on a row per store and
-- status, STORE TOTAL on a row per store, STATUS TOTAL on a row per status, and GRAND TOTAL on the one row for the
-- chain. StoreOrder is a plain class, not an entity, and is not in the persistence unit, so the problems in this
-- section are SQL only; from Jakarta Persistence, run them as native queries.
--


-- =============================================================================================================== BASIC


-- ----------------------------------------------------------------------- CO-STORE_ORDERS-B-01 Store totals, best first
-- The regional manager wants each store's number of orders and sales, best first, with the address to put on the
-- report.
SELECT store_name, address, order_count, total_sales
FROM store_orders
WHERE total = 'STORE TOTAL'
ORDER BY total_sales DESC
;


-- ======================================================================================================== INTERMEDIATE


-- ------------------------------------------------------------------------ CO-STORE_ORDERS-I-01 Stores that lose orders
-- Customer care wants the stores with cancelled or refunded orders, with how many there were and what they were worth,
-- the largest loss first.
SELECT store_name, SUM(order_count) AS lost_orders, SUM(total_sales) AS lost_sales
FROM store_orders
WHERE total IS NULL
  AND order_status IN ('CANCELLED', 'REFUNDED')
GROUP BY store_name
ORDER BY lost_sales DESC, store_name
;


-- ============================================================================================================ ADVANCED


-- -------------------------------------- CO-STORE_ORDERS-A-01 Each store's losses against its own sales and the chain's
-- Finance wants each store's cancelled and refunded sales as a share of the store's own sales and of the whole chain's.
SELECT d.store_name,
       SUM(d.total_sales)                                       AS lost_sales,
       ROUND(100 * SUM(d.total_sales) / MAX(st.total_sales), 1) AS pct_of_store,
       ROUND(100 * SUM(d.total_sales) / MAX(g.total_sales), 2)  AS pct_of_chain
FROM store_orders d
         JOIN store_orders st ON st.total = 'STORE TOTAL' AND st.store_name = d.store_name
         CROSS JOIN (SELECT total_sales FROM store_orders WHERE total = 'GRAND TOTAL') g
WHERE d.total IS NULL
  AND d.order_status IN ('CANCELLED', 'REFUNDED')
GROUP BY d.store_name
ORDER BY pct_of_store DESC, d.store_name
;
