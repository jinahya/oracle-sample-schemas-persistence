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
-- Exercises on SHIPMENTS, a problem per query that reads SHIPMENTS alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'SHIPMENTS' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- CO_SHIPMENTS_I_01
-- Logistics wants, per store, the number of shipments in each status other than DELIVERED.
SELECT store_id, shipment_status, COUNT(*) AS shipments
FROM shipments
WHERE shipment_status <> 'DELIVERED'
GROUP BY store_id, shipment_status
ORDER BY store_id, shipment_status
;
