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
-- Exercises on ORDERS, a problem per query that reads ORDERS alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'ORDERS' section of EXERCISES.adoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- --------------------------------------------------------------------------- CO-ORDERS-B-01 A customer's order history
-- Show a customer all of their orders, the most recent first.
SELECT order_id, order_tms, order_status, store_id
FROM orders
WHERE customer_id = :customerId
ORDER BY order_tms DESC
;


-- ------------------------------------------------------------------------------- CO-ORDERS-B-02 Orders that went wrong
-- Finance reviews every order that was cancelled or refunded, newest first.
SELECT order_id, order_tms, order_status, customer_id, store_id
FROM orders
WHERE order_status IN ('CANCELLED', 'REFUNDED')
ORDER BY order_tms DESC
;


-- ------------------------------------------------------------------------ CO-ORDERS-B-03 A store's orders for a period
-- A store manager wants the orders taken at their store during a period: on or after :fromTms, and before :toTms.
SELECT order_id, order_tms, order_status, customer_id
FROM orders
WHERE store_id = :storeId
  AND order_tms >= :fromTms
  AND order_tms < :toTms
ORDER BY order_tms
;


-- ======================================================================================================== INTERMEDIATE


-- ----------------------------------------------------------------------------------- CO-ORDERS-I-01 Where orders stand
-- Operations wants the number of orders in each status, with the first and the last order placed in it.
SELECT order_status, COUNT(*) AS orders, MIN(order_tms) AS first_order_tms, MAX(order_tms) AS last_order_tms
FROM orders
GROUP BY order_status
ORDER BY orders DESC
;


-- ============================================================================================================ ADVANCED


-- -------------------------------------------------------------- CO-ORDERS-A-01 Customers of two stores, or of one only
-- Two store managers plan a joint promotion. Marketing wants (a) the customers who have ordered from both stores, (b)
-- those who ordered from the first store but never from the second, and (c) everyone who ordered from either.
SELECT customer_id
FROM orders
WHERE store_id = :storeA
INTERSECT
SELECT customer_id
FROM orders
WHERE store_id = :storeB
;

SELECT customer_id
FROM orders
WHERE store_id = :storeA
MINUS
SELECT customer_id
FROM orders
WHERE store_id = :storeB
;

SELECT customer_id
FROM orders
WHERE store_id = :storeA
UNION
SELECT customer_id
FROM orders
WHERE store_id = :storeB
;


-- --------------------------------------------------------------------- CO-ORDERS-A-02 Time between a customer's orders
-- Customer care, looking at one customer, wants each of their orders next to the one before it, to see how often they
-- come back.
SELECT order_id,
       order_tms,
       LAG(order_tms) OVER (ORDER BY order_tms)                               AS previous_order_tms,
       EXTRACT(DAY FROM order_tms - LAG(order_tms) OVER (ORDER BY order_tms)) AS days_since_previous
FROM orders
WHERE customer_id = :customerId
ORDER BY order_tms
;
