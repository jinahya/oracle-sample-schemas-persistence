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
-- Exercises on STORES, a problem per query that reads STORES alone, at the basic level.
--
-- These are the basic SQL solutions of the 'STORES' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------------ CO_STORES_B_01
-- Customer service wants a store list with a single address for each: the web address of the online store, and the
-- postal address of every other one.
SELECT store_name, COALESCE(web_address, physical_address) AS address
FROM stores
ORDER BY store_name
;


-- ------------------------------------------------------------------------------------------------------ CO_STORES_B_02
-- For a map legend, list the stores from the northernmost to the southernmost. Stores without coordinates (the online
-- store) go at the end.
SELECT store_name, latitude, longitude
FROM stores
ORDER BY latitude DESC NULLS LAST
;
