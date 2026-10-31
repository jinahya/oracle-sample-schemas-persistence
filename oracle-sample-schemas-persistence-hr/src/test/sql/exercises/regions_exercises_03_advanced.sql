-- #%L
-- hr
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
-- Exercises on REGIONS, a problem per query that reads REGIONS alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'REGIONS' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ----------------------------------------------------------------------------------------------------- HR_REGIONS_A_01
-- The map legend abbreviates each region to its initial. Which regions share an initial with another region?
SELECT r.REGION_ID, r.REGION_NAME
FROM REGIONS r
WHERE EXISTS (SELECT 1
              FROM REGIONS o
              WHERE o.REGION_ID <> r.REGION_ID
                AND SUBSTR(o.REGION_NAME, 1, 1) = SUBSTR(r.REGION_NAME, 1, 1))
ORDER BY r.REGION_NAME ASC
;
