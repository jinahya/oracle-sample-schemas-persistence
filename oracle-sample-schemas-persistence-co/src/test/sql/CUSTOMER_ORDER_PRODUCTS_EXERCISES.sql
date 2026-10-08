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
-- Exercises on CUSTOMER_ORDER_PRODUCTS, a problem per query that reads CUSTOMER_ORDER_PRODUCTS alone; Basic, then
-- Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'CUSTOMER_ORDER_PRODUCTS' section of EXERCISES.adoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- CUSTOMER_ORDER_PRODUCTS has one row per order, with the customer's name and email address, the order's total, and its
-- product names in ITEMS. CustomerOrderProduct maps it as a read-only entity whose orderStatus is a String.
--


-- =============================================================================================================== BASIC


-- ------------------------------------------------------ CO-CUSTOMER_ORDER_PRODUCTS-B-01 Orders that included a product
-- A supplier has recalled a product. Customer care wants every order that included it, with who placed it, the most
-- recent first.
SELECT order_id, order_tms, full_name, email_address, items
FROM customer_order_products
WHERE items LIKE '%' || :productName || '%'
ORDER BY order_tms DESC
;


-- ======================================================================================================== INTERMEDIATE


-- --------------------------------------------------------- CO-CUSTOMER_ORDER_PRODUCTS-I-01 Average basket per customer
-- Marketing wants, per customer, the number of completed orders, what they came to and the average order value, the
-- biggest spenders first.
SELECT customer_id,
       full_name,
       COUNT(*)                   AS orders,
       SUM(order_total)           AS total_spent,
       ROUND(AVG(order_total), 2) AS average_order
FROM customer_order_products
WHERE order_status = 'COMPLETE'
GROUP BY customer_id, full_name
ORDER BY total_spent DESC, full_name
;


-- ============================================================================================================ ADVANCED


-- ------------------------------------------------------- CO-CUSTOMER_ORDER_PRODUCTS-A-01 Each customer's biggest order
-- For a thank-you letter, marketing wants each customer's most valuable order. A tie gives a customer more than one
-- row.
SELECT customer_id, full_name, order_id, order_tms, order_total
FROM (SELECT v.*, RANK() OVER (PARTITION BY v.customer_id ORDER BY v.order_total DESC) AS rnk
      FROM customer_order_products v)
WHERE rnk = 1
ORDER BY order_total DESC, full_name
;
