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
-- Exercises on PRODUCT_ORDERS, a problem per query that reads PRODUCT_ORDERS alone; Basic, then Intermediate, then
-- Advanced.
--
-- These are the SQL solutions of the 'PRODUCT_ORDERS' section of EXERCISES.adoc, which also states each problem in full
-- and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- PRODUCT_ORDERS has one row per product name and order status. It is queried through ProductOrderWithEmbeddedId, whose
-- key is v.id.productName and v.id.orderStatus, both Strings.
--


-- =============================================================================================================== BASIC


-- ----------------------------------------------------------------- CO-PRODUCT_ORDERS-B-01 Which products get refunded?
-- Customer care suspects a quality problem. For one order status (say REFUNDED), list the products with the value and
-- number of such orders, the largest value first.
SELECT product_name, total_sales, order_count
FROM product_orders
WHERE order_status = :orderStatus
ORDER BY total_sales DESC
;


-- ======================================================================================================== INTERMEDIATE


-- -------------------------------------------- CO-PRODUCT_ORDERS-I-01 Sales per product, whatever happened to the order
-- Merchandising wants each product's number of orders and their value, over every status, the largest value first.
SELECT product_name, SUM(order_count) AS orders, SUM(total_sales) AS sales
FROM product_orders
GROUP BY product_name
ORDER BY sales DESC
;


-- ============================================================================================================ ADVANCED


-- ------------------------------------------------ CO-PRODUCT_ORDERS-A-01 Products that are often cancelled or refunded
-- Quality assurance wants the products whose cancelled and refunded orders make up more than :percent per cent (say 5)
-- of all their orders, with both counts.
SELECT product_name,
       SUM(order_count)                                                                     AS orders,
       SUM(CASE WHEN order_status IN ('CANCELLED', 'REFUNDED') THEN order_count ELSE 0 END) AS lost_orders,
       ROUND(100 * SUM(CASE WHEN order_status IN ('CANCELLED', 'REFUNDED') THEN order_count ELSE 0 END)
                 / SUM(order_count), 1)                                                     AS lost_pct
FROM product_orders
GROUP BY product_name
HAVING SUM(CASE WHEN order_status IN ('CANCELLED', 'REFUNDED') THEN order_count ELSE 0 END)
           * 100 > :percent * SUM(order_count)
ORDER BY lost_pct DESC, product_name
;
