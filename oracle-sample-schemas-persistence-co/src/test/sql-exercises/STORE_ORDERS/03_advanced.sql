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
-- Exercises on STORE_ORDERS, a problem per query that reads STORE_ORDERS alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'STORE_ORDERS' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- STORE_ORDERS aggregates the orders with GROUPING SETS. TOTAL tells the rows apart: it is null on a row per store and
-- status, STORE TOTAL on a row per store, STATUS TOTAL on a row per status, and GRAND TOTAL on the one row for the
-- chain. StoreOrder is a plain class, not an entity, and is not in the persistence unit, so the problems in this
-- section are SQL only; from Jakarta Persistence, run them as native queries.
--


-- ------------------------------------------------------------------------------------------------ CO_STORE_ORDERS_A_01
-- For every store with a store-total row, show its cancelled and refunded sales, `0` where it has none, and their share
-- of the store's sales and of the chain's, every status counted; a share is NULL where its total is `0`.
SELECT st.store_name,
       COALESCE(SUM(d.total_sales), 0)                                                 AS lost_sales,
       ROUND(100 * COALESCE(SUM(d.total_sales), 0) / NULLIF(MAX(st.total_sales), 0), 1) AS pct_of_store,
       ROUND(100 * COALESCE(SUM(d.total_sales), 0) / NULLIF(MAX(g.total_sales), 0), 2)  AS pct_of_chain
FROM store_orders st
         LEFT JOIN store_orders d ON d.store_name = st.store_name
    AND d.total IS NULL
    AND d.order_status IN ('CANCELLED', 'REFUNDED')
         CROSS JOIN (SELECT total_sales FROM store_orders WHERE total = 'GRAND TOTAL') g
WHERE st.total = 'STORE TOTAL'
GROUP BY st.store_name
ORDER BY pct_of_store DESC NULLS LAST, st.store_name
;
