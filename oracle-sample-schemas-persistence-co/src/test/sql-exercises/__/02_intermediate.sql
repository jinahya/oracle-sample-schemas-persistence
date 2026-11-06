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
-- Exercises on the whole schema, a problem per query that reads more than one table or view, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'Whole schema' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- Problems that join, or subquery, more than one table or view.
--


-- ------------------------------------------------------------------------------------------------------ CO_SCHEMA_I_01
-- Show a customer what each of their orders cost in total, newest first.
SELECT o.order_id,
       o.order_tms,
       o.order_status,
       SUM(oi.unit_price * oi.quantity) AS order_total
FROM orders o
         JOIN order_items oi ON oi.order_id = o.order_id
WHERE o.customer_id = :customerId
GROUP BY o.order_id, o.order_tms, o.order_status
ORDER BY o.order_tms DESC
;


-- ------------------------------------------------------------------------------------------------------ CO_SCHEMA_I_02
-- Finance wants the revenue of completed orders per store, keeping only the stores above a target, best first.
SELECT s.store_id, s.store_name, SUM(oi.unit_price * oi.quantity) AS revenue
FROM stores s
         JOIN orders o ON o.store_id = s.store_id
         JOIN order_items oi ON oi.order_id = o.order_id
WHERE o.order_status = 'COMPLETE'
GROUP BY s.store_id, s.store_name
HAVING SUM(oi.unit_price * oi.quantity) > :target
ORDER BY revenue DESC
;


-- ------------------------------------------------------------------------------------------------------ CO_SCHEMA_I_03
-- Merchandising wants the ten products that sold the most units, counting completed orders only.
SELECT p.product_id, p.product_name, SUM(oi.quantity) AS units_sold
FROM products p
         JOIN order_items oi ON oi.product_id = p.product_id
         JOIN orders o ON o.order_id = oi.order_id
WHERE o.order_status = 'COMPLETE'
GROUP BY p.product_id, p.product_name
ORDER BY units_sold DESC, p.product_name
    FETCH FIRST 10 ROWS ONLY
;


-- ------------------------------------------------------------------------------------------------------ CO_SCHEMA_I_04
-- Marketing wants the customers who have placed at least :minOrders orders, with their order count, most orders first.
SELECT c.customer_id, c.full_name, c.email_address, COUNT(*) AS order_count
FROM customers c
         JOIN orders o ON o.customer_id = c.customer_id
GROUP BY c.customer_id, c.full_name, c.email_address
HAVING COUNT(*) >= :minOrders
ORDER BY order_count DESC, c.full_name
;


-- ------------------------------------------------------------------------------------------------------ CO_SCHEMA_I_05
-- Each order line records the price actually charged. Merchandising wants, per product, the lowest and highest price it
-- was ever sold at, next to today's list price, for the products where the two disagree.
SELECT p.product_id,
       p.product_name,
       p.unit_price       AS list_price,
       MIN(oi.unit_price) AS lowest_sold,
       MAX(oi.unit_price) AS highest_sold
FROM products p
         JOIN order_items oi ON oi.product_id = p.product_id
GROUP BY p.product_id, p.product_name, p.unit_price
HAVING MIN(oi.unit_price) <> p.unit_price
    OR MAX(oi.unit_price) <> p.unit_price
ORDER BY p.product_name
;


-- ------------------------------------------------------------------------------------------------------ CO_SCHEMA_I_06
-- Purchasing wants the products that cannot be bought anywhere: the total stock across all stores is zero, or no store
-- carries them at all.
SELECT p.product_id, p.product_name, COALESCE(SUM(i.product_inventory), 0) AS total_stock
FROM products p
         LEFT JOIN inventory i ON i.product_id = p.product_id
GROUP BY p.product_id, p.product_name
HAVING COALESCE(SUM(i.product_inventory), 0) = 0
ORDER BY p.product_name
;


-- ------------------------------------------------------------------------------------------------------ CO_SCHEMA_I_07
-- Marketing sorts orders into bands by their total: under 100 is small, under 300 is medium, anything else is large.
-- List each order with its total and band.
SELECT o.order_id,
       SUM(oi.unit_price * oi.quantity) AS order_total,
       CASE
           WHEN SUM(oi.unit_price * oi.quantity) < 100 THEN 'SMALL'
           WHEN SUM(oi.unit_price * oi.quantity) < 300 THEN 'MEDIUM'
           ELSE 'LARGE'
           END                          AS basket
FROM orders o
         JOIN order_items oi ON oi.order_id = o.order_id
GROUP BY o.order_id
ORDER BY o.order_id
;


-- ------------------------------------------------------------------------------------------------------ CO_SCHEMA_I_08
-- Logistics wants the orders, other than cancelled or refunded ones, that still have lines with no shipment assigned,
-- and how many such lines each has, the most first; orders with the same number are ordered by `order_id`.
SELECT o.order_id, o.order_status, COUNT(*) AS unshipped_lines
FROM orders o
         JOIN order_items oi ON oi.order_id = o.order_id
WHERE oi.shipment_id IS NULL
  AND o.order_status NOT IN ('CANCELLED', 'REFUNDED')
GROUP BY o.order_id, o.order_status
ORDER BY unshipped_lines DESC, o.order_id
;


-- ------------------------------------------------------------------------------------------------------ CO_SCHEMA_I_09
-- The exercises on order totals assume every order has at least one line; the foreign key holds only from the line to
-- the order. Find the orders with no line, the most recent first; the check passes when nothing comes back.
SELECT o.order_id, o.order_tms, o.order_status, o.customer_id, o.store_id
FROM orders o
WHERE NOT EXISTS (SELECT 1 FROM order_items oi WHERE oi.order_id = o.order_id)
ORDER BY o.order_tms DESC, o.order_id DESC
;
