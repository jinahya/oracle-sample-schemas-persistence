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
-- Exercises on ORDER_ITEMS, a problem per query that reads ORDER_ITEMS alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'ORDER_ITEMS' section of EXERCISES.adoc, which also states each problem in full
-- and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- ORDER_ITEMS is queried through OrderItemWithEmbeddedId (see the note on Order.orderItems above); its order and line
-- number are oi.id.orderId and oi.id.lineItemId.
--


-- =============================================================================================================== BASIC


-- -------------------------------------------------------------------------------------- CO-ORDER_ITEMS-B-01 Bulk lines
-- Fraud prevention watches for bulk buying. List the order lines of :minQuantity units or more, the largest first.
SELECT order_id, line_item_id, product_id, unit_price, quantity
FROM order_items
WHERE quantity >= :minQuantity
ORDER BY quantity DESC, order_id, line_item_id
;


-- ======================================================================================================== INTERMEDIATE


-- ---------------------------------------------------------------- CO-ORDER_ITEMS-I-01 Orders worth more than an amount
-- Fraud prevention also reviews every order worth more than :amount, with its number of lines and units, from the lines
-- alone.
SELECT order_id, COUNT(*) AS lines, SUM(quantity) AS units, SUM(unit_price * quantity) AS order_total
FROM order_items
GROUP BY order_id
HAVING SUM(unit_price * quantity) > :amount
ORDER BY order_total DESC
;


-- ============================================================================================================ ADVANCED


-- -------------------------------------------------------------------------- CO-ORDER_ITEMS-A-01 Each order's main line
-- Customer care settles a complaint about an order against its main line: the line with the largest value. List it for
-- every order; a tie gives an order more than one row.
SELECT order_id, line_item_id, product_id, unit_price * quantity AS line_total
FROM (SELECT oi.*, RANK() OVER (PARTITION BY oi.order_id ORDER BY oi.unit_price * oi.quantity DESC) AS rnk
      FROM order_items oi)
WHERE rnk = 1
ORDER BY order_id, line_item_id
;
