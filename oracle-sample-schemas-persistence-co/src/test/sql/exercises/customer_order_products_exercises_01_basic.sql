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
-- Exercises on CUSTOMER_ORDER_PRODUCTS, a problem per query that reads CUSTOMER_ORDER_PRODUCTS alone, at the basic
-- level.
--
-- These are the basic SQL solutions of the 'CUSTOMER_ORDER_PRODUCTS' section of EXERCISES.asciidoc, which also states
-- each problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- CUSTOMER_ORDER_PRODUCTS has one row per order, with the customer's name and email address, the order's total, and its
-- product names in ITEMS. CustomerOrderProduct maps it as a read-only entity whose orderStatus is a String.
--


-- ------------------------------------------------------------------------------------- CO_CUSTOMER_ORDER_PRODUCTS_B_01
-- A supplier has recalled a product. Customer care wants every order that included it, with who placed it, the most
-- recent first.
SELECT order_id, order_tms, full_name, email_address, items
FROM customer_order_products
WHERE items LIKE '%' || :productName || '%'
ORDER BY order_tms DESC
;
