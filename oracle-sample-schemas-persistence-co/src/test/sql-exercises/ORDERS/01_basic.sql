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
-- Exercises on ORDERS, a problem per query that reads ORDERS alone, at the basic level.
--
-- These are the basic SQL solutions of the 'ORDERS' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------------ CO_ORDERS_B_01
-- Show all of a customer's orders, the most recent first; orders placed at the same moment are ordered by `order_id`,
-- the highest first.
SELECT order_id, order_tms, order_status, store_id
FROM orders
WHERE customer_id = :customerId
ORDER BY order_tms DESC, order_id DESC
;


-- ------------------------------------------------------------------------------------------------------ CO_ORDERS_B_02
-- Finance reviews every order that was cancelled or refunded, newest first.
SELECT order_id, order_tms, order_status, customer_id, store_id
FROM orders
WHERE order_status IN ('CANCELLED', 'REFUNDED')
ORDER BY order_tms DESC
;


-- ------------------------------------------------------------------------------------------------------ CO_ORDERS_B_03
-- A store manager wants the orders taken at their store during a period: on or after :fromTms, and before :toTms.
SELECT order_id, order_tms, order_status, customer_id
FROM orders
WHERE store_id = :storeId
  AND order_tms >= :fromTms
  AND order_tms < :toTms
ORDER BY order_tms
;


-- ------------------------------------------------------------------------------------------------------ CO_ORDERS_B_04
-- The account page shows a customer's orders `:pageSize` at a time, the most recent first. Show the page that starts
-- after the first `:offset` orders; `order_id` breaks a tie, so a page neither repeats nor skips an order.
SELECT order_id, order_tms, order_status, store_id
FROM orders
WHERE customer_id = :customerId
ORDER BY order_tms DESC, order_id DESC
OFFSET :offset ROWS FETCH NEXT :pageSize ROWS ONLY
;
