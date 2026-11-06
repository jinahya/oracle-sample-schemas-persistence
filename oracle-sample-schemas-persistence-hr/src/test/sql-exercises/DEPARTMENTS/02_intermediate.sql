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
-- Exercises on DEPARTMENTS, a problem per query that reads DEPARTMENTS alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'DEPARTMENTS' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------- HR_DEPARTMENTS_I_01
-- Facilities sizes each site's reception by the number of departments it houses.
SELECT LOCATION_ID, COUNT(*) AS DEPARTMENTS
FROM DEPARTMENTS
GROUP BY LOCATION_ID
ORDER BY DEPARTMENTS DESC, LOCATION_ID ASC
;


-- ------------------------------------------------------------------------------------------------- HR_DEPARTMENTS_I_02
-- HR's quarterly dashboard shows three numbers: how many departments there are, how many of them have a manager, and on
-- how many sites they sit.
SELECT COUNT(*) AS DEPARTMENTS, COUNT(MANAGER_ID) AS MANAGED, COUNT(DISTINCT LOCATION_ID) AS SITES
FROM DEPARTMENTS
;
