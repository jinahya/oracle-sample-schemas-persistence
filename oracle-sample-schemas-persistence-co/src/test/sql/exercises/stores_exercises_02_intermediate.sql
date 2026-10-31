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
-- Exercises on STORES, a problem per query that reads STORES alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'STORES' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------------ CO_STORES_I_01
-- The signage team needs store names without spaces, for file names: New York City becomes New-York-City, and the last
-- four characters of each are used as a short code.
SELECT store_name,
       REPLACE(store_name, ' ', '-') AS slug,
       UPPER(SUBSTR(store_name, -4)) AS short_code
FROM stores
ORDER BY store_name
;
