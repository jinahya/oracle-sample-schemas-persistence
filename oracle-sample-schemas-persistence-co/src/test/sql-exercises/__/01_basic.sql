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
-- Exercises on the whole schema, a problem per query that reads more than one table or view, at the basic level.
--
-- These are the basic SQL solutions of the 'Whole schema' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- Problems that join, or subquery, more than one table or view.
--


-- ------------------------------------------------------------------------------------------------------ CO_SCHEMA_B_01
-- Customer care has an order number. Show the order with the customer's name and email address and the store it was
-- placed at.
SELECT o.order_id, o.order_tms, o.order_status, c.full_name, c.email_address, s.store_name
FROM orders o
         JOIN customers c ON c.customer_id = o.customer_id
         JOIN stores s ON s.store_id = o.store_id
WHERE o.order_id = :orderId
;


-- ------------------------------------------------------------------------------------------------------ CO_SCHEMA_B_02
-- A customer asks for a receipt. List the lines of one order: line number, product, unit price, quantity and line
-- total.
SELECT oi.line_item_id,
       p.product_name,
       oi.unit_price,
       oi.quantity,
       oi.unit_price * oi.quantity AS line_total
FROM order_items oi
         JOIN products p ON p.product_id = oi.product_id
WHERE oi.order_id = :orderId
ORDER BY oi.line_item_id
;


-- ------------------------------------------------------------------------------------------------------ CO_SCHEMA_B_03
-- The warehouse wants every store and product whose stock is below :threshold, the emptiest shelves first.
SELECT s.store_name, p.product_name, i.product_inventory
FROM inventory i
         JOIN stores s ON s.store_id = i.store_id
         JOIN products p ON p.product_id = i.product_id
WHERE i.product_inventory < :threshold
ORDER BY i.product_inventory, s.store_name, p.product_name
;


-- ------------------------------------------------------------------------------------------------------ CO_SCHEMA_B_04
-- A supplier has recalled a product. Customer care wants every order that included it, with the customer's name and
-- email address, the most recent first; orders placed at the same moment are ordered by `order_id`.
SELECT o.order_id, o.order_tms, c.full_name, c.email_address
FROM order_items oi
         JOIN orders o ON o.order_id = oi.order_id
         JOIN customers c ON c.customer_id = o.customer_id
WHERE oi.product_id = :productId
ORDER BY o.order_tms DESC, o.order_id DESC
;
