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
-- Exercises on INVENTORY, a problem per query that reads INVENTORY alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'INVENTORY' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- CO_INVENTORY_A_01
-- A store is closing for a refit. The regional manager wants the stores that carry every product it carries, to send
-- its customers to.
SELECT DISTINCT i.store_id
FROM inventory i
WHERE i.store_id <> :storeId
  AND NOT EXISTS (SELECT 1
                  FROM inventory r
                  WHERE r.store_id = :storeId
                    AND NOT EXISTS (SELECT 1
                                    FROM inventory x
                                    WHERE x.store_id = i.store_id
                                      AND x.product_id = r.product_id))
ORDER BY i.store_id
;
