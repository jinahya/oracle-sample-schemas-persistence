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
-- Exercises on STORES, a problem per query that reads STORES alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'STORES' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------------ CO_STORES_A_01
-- The regional manager pairs every physical store with its nearest other store, to share stock between them.
SELECT s.store_name,
       n.store_name AS nearest_store,
       ROUND(SQRT(POWER(n.latitude - s.latitude, 2) + POWER(n.longitude - s.longitude, 2)),
             2)     AS degrees_apart
FROM stores s
         JOIN stores n ON n.store_id <> s.store_id
WHERE s.latitude IS NOT NULL
  AND n.latitude IS NOT NULL
  AND POWER(n.latitude - s.latitude, 2) + POWER(n.longitude - s.longitude, 2)
    = (SELECT MIN(POWER(m.latitude - s.latitude, 2) + POWER(m.longitude - s.longitude, 2))
       FROM stores m
       WHERE m.store_id <> s.store_id)
ORDER BY s.store_name
;
