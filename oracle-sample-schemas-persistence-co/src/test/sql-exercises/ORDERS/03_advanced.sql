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
-- Exercises on ORDERS, a problem per query that reads ORDERS alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'ORDERS' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------------ CO_ORDERS_A_01
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


-- ------------------------------------------------------------------------------------------------------ CO_ORDERS_A_02
-- Customer care, looking at one customer, wants each of their orders next to the one before it, and the whole days
-- between the two, to see how often they come back; orders placed at the same moment are ordered by `order_id`.
SELECT order_id,
       order_tms,
       previous_order_tms,
       EXTRACT(DAY FROM order_tms - previous_order_tms) AS days_since_previous
FROM (SELECT order_id, order_tms, LAG(order_tms) OVER (ORDER BY order_tms, order_id) AS previous_order_tms
      FROM orders
      WHERE customer_id = :customerId)
ORDER BY order_tms, order_id
;


-- ------------------------------------------------------------------------------------------------------ CO_ORDERS_A_03
-- Marketing wants to know whether the store still wins new customers or lives on its regulars: per month, how many
-- orders were a customer's first order and how many came from a customer who had ordered before.
SELECT TO_CHAR(order_tms, 'YYYY-MM')      AS order_month,
       COUNT(CASE WHEN rn = 1 THEN 1 END) AS first_orders,
       COUNT(CASE WHEN rn > 1 THEN 1 END) AS repeat_orders
FROM (SELECT order_tms,
             ROW_NUMBER() OVER (PARTITION BY customer_id ORDER BY order_tms, order_id) AS rn
      FROM orders)
GROUP BY TO_CHAR(order_tms, 'YYYY-MM')
ORDER BY order_month
;
