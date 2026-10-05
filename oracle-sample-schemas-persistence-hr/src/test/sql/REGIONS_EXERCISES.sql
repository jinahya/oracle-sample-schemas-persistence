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
-- Exercises on REGIONS, a problem per query that reads REGIONS alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'REGIONS' section of EXERCISES.adoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- -------------------------------------------------------------------------------------- HR-REGIONS-B-01 Region by name
-- A report parameter arrives as a region name typed by a user, in whatever case; the report needs the region.
SELECT REGION_ID, REGION_NAME
FROM REGIONS
WHERE UPPER(REGION_NAME) = UPPER(:regionName)
;


-- ------------------------------------------------------------------------------------- HR-REGIONS-B-02 The region list
-- The reporting tool's drop-down lists every region by name.
SELECT REGION_ID, REGION_NAME
FROM REGIONS
ORDER BY REGION_NAME ASC
;


-- ======================================================================================================== INTERMEDIATE


-- --------------------------------------------------------------------------- HR-REGIONS-I-01 A number for a new region
-- Head office is opening a region. Region ids go up in tens; which id comes next?
SELECT COALESCE(MAX(REGION_ID), 0) + 10 AS NEXT_REGION_ID
FROM REGIONS
;


-- ============================================================================================================ ADVANCED


-- --------------------------------------------------------------------------------- HR-REGIONS-A-01 Initials that clash
-- The map legend abbreviates each region to its initial. Which regions share an initial with another region?
SELECT r.REGION_ID, r.REGION_NAME
FROM REGIONS r
WHERE EXISTS (SELECT 1
              FROM REGIONS o
              WHERE o.REGION_ID <> r.REGION_ID
                AND SUBSTR(o.REGION_NAME, 1, 1) = SUBSTR(r.REGION_NAME, 1, 1))
ORDER BY r.REGION_NAME ASC
;
