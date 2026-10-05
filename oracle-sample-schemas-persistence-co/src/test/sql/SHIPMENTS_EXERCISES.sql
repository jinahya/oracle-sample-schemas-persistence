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
-- Exercises on SHIPMENTS, a problem per query that reads SHIPMENTS alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'SHIPMENTS' section of EXERCISES.adoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- ----------------------------------------------------------------------------- CO-SHIPMENTS-B-01 Shipments on the road
-- Logistics wants every shipment that is currently in transit, with the address it is heading to.
SELECT shipment_id, store_id, customer_id, delivery_address
FROM shipments
WHERE shipment_status = 'IN-TRANSIT'
ORDER BY shipment_id
;


-- ------------------------------------------------------------------------------- CO-SHIPMENTS-B-02 Shipments to a town
-- A courier reports a road closure around one town. Find the shipments whose delivery address mentions it.
SELECT shipment_id, delivery_address, shipment_status
FROM shipments
WHERE delivery_address LIKE '%' || :town || '%'
ORDER BY shipment_id
;


-- ======================================================================================================== INTERMEDIATE


-- -------------------------------------------------------------------------- CO-SHIPMENTS-I-01 Open shipments per store
-- Logistics wants, per store, the number of shipments in each status other than DELIVERED.
SELECT store_id, shipment_status, COUNT(*) AS shipments
FROM shipments
WHERE shipment_status <> 'DELIVERED'
GROUP BY store_id, shipment_status
ORDER BY store_id, shipment_status
;


-- ============================================================================================================ ADVANCED


-- -------------------------------------------------------------- CO-SHIPMENTS-A-01 Shipments that could travel together
-- Before dispatch, logistics wants to merge shipments that are still CREATED and go from the same store to the same
-- customer at the same address. List them in pairs.
SELECT a.shipment_id, b.shipment_id AS other_shipment_id, a.store_id, a.customer_id, a.delivery_address
FROM shipments a
         JOIN shipments b ON b.store_id = a.store_id
    AND b.customer_id = a.customer_id
    AND b.delivery_address = a.delivery_address
    AND b.shipment_id > a.shipment_id
WHERE a.shipment_status = 'CREATED'
  AND b.shipment_status = 'CREATED'
ORDER BY a.shipment_id, b.shipment_id
;
