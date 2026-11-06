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
-- For each store with both coordinates, show its nearest other store with both coordinates, all of them at a tie, and
-- the great-circle distance in kilometres.
WITH distances AS (SELECT s.store_id,
                          s.store_name,
                          n.store_name AS nearest_store,
                          6371 * ACOS(GREATEST(-1, LEAST(1,
                              SIN(s.latitude * ACOS(-1) / 180) * SIN(n.latitude * ACOS(-1) / 180)
                              + COS(s.latitude * ACOS(-1) / 180) * COS(n.latitude * ACOS(-1) / 180)
                                * COS((s.longitude - n.longitude) * ACOS(-1) / 180)))) AS km
                   FROM stores s
                            JOIN stores n ON n.store_id <> s.store_id
                   WHERE s.latitude IS NOT NULL
                     AND s.longitude IS NOT NULL
                     AND n.latitude IS NOT NULL
                     AND n.longitude IS NOT NULL)
SELECT store_name, nearest_store, ROUND(km, 2) AS km
FROM (SELECT d.*, RANK() OVER (PARTITION BY store_id ORDER BY km) AS rnk FROM distances d)
WHERE rnk = 1
ORDER BY store_name, nearest_store
;
