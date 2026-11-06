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
-- Exercises on PRODUCT_ORDERS, a problem per query that reads PRODUCT_ORDERS alone, at the basic level.
--
-- These are the basic SQL solutions of the 'PRODUCT_ORDERS' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- PRODUCT_ORDERS has one row per product name and order status. It is queried through ProductOrder, whose key is
-- v.id.productName and v.id.orderStatus, both Strings.
--


-- ---------------------------------------------------------------------------------------------- CO_PRODUCT_ORDERS_B_01
-- Customer care suspects a quality problem. For one order status (say REFUNDED), list the products with the value and
-- number of such orders, the largest value first.
SELECT product_name, total_sales, order_count
FROM product_orders
WHERE order_status = :orderStatus
ORDER BY total_sales DESC
;
