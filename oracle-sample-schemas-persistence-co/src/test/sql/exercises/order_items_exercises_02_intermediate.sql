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
-- Exercises on ORDER_ITEMS, a problem per query that reads ORDER_ITEMS alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'ORDER_ITEMS' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- ORDER_ITEMS is queried through OrderItem (see the note on Order.orderItems above); its order and line number are
-- oi.id.orderId and oi.id.lineItemId.
--


-- ------------------------------------------------------------------------------------------------- CO_ORDER_ITEMS_I_01
-- Fraud prevention also reviews every order worth more than :amount, with its number of lines and units, from the lines
-- alone.
SELECT order_id,
       COUNT(*)                   AS lines,
       SUM(quantity)              AS units,
       SUM(unit_price * quantity) AS order_total
FROM order_items
GROUP BY order_id
HAVING SUM(unit_price * quantity) > :amount
ORDER BY order_total DESC
;
