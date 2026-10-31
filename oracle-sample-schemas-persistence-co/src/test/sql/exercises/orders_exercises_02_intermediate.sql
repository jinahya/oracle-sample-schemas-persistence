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
-- Exercises on ORDERS, a problem per query that reads ORDERS alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'ORDERS' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------------ CO_ORDERS_I_01
-- Operations wants the number of orders in each status, with the first and the last order placed in it.
SELECT order_status, COUNT(*) AS orders, MIN(order_tms) AS first_order_tms, MAX(order_tms) AS last_order_tms
FROM orders
GROUP BY order_status
ORDER BY orders DESC
;
