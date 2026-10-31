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
-- Exercises on CUSTOMER_ORDER_PRODUCTS, a problem per query that reads CUSTOMER_ORDER_PRODUCTS alone, at the advanced
-- level.
--
-- These are the advanced SQL solutions of the 'CUSTOMER_ORDER_PRODUCTS' section of EXERCISES.asciidoc, which also
-- states each problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it
-- answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- CUSTOMER_ORDER_PRODUCTS has one row per order, with the customer's name and email address, the order's total, and its
-- product names in ITEMS. CustomerOrderProduct maps it as a read-only entity whose orderStatus is a String.
--


-- ------------------------------------------------------------------------------------- CO_CUSTOMER_ORDER_PRODUCTS_A_01
-- For a thank-you letter, marketing wants each customer's most valuable order. A tie gives a customer more than one
-- row.
SELECT customer_id, full_name, order_id, order_tms, order_total
FROM (SELECT v.*, RANK() OVER (PARTITION BY v.customer_id ORDER BY v.order_total DESC) AS rnk
      FROM customer_order_products v)
WHERE rnk = 1
ORDER BY order_total DESC, full_name
;
