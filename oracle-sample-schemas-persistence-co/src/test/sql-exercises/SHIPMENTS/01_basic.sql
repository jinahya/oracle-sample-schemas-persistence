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
-- Exercises on SHIPMENTS, a problem per query that reads SHIPMENTS alone, at the basic level.
--
-- These are the basic SQL solutions of the 'SHIPMENTS' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- CO_SHIPMENTS_B_01
-- Logistics wants every shipment that is currently in transit, with the address it is heading to.
SELECT shipment_id, store_id, customer_id, delivery_address
FROM shipments
WHERE shipment_status = 'IN-TRANSIT'
ORDER BY shipment_id
;


-- --------------------------------------------------------------------------------------------------- CO_SHIPMENTS_B_02
-- A courier reports a road closure around one town. Find the shipments whose delivery address mentions it.
SELECT shipment_id, delivery_address, shipment_status
FROM shipments
WHERE delivery_address LIKE '%' || :town || '%'
ORDER BY shipment_id
;
