-- #%L
-- sh
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
-- Exercises on COUNTRIES, a problem per query that reads COUNTRIES alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'COUNTRIES' section of EXERCISES.adoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- ----------------------------------------------------------------------- SH-COUNTRIES-B-01 Countries of a sales region
-- The regional sales director for :region (e.g. Europe) wants the countries of that region, grouped by subregion.
SELECT COUNTRY_SUBREGION, COUNTRY_ISO_CODE, COUNTRY_NAME
FROM COUNTRIES
WHERE COUNTRY_REGION = :region
ORDER BY COUNTRY_SUBREGION, COUNTRY_NAME
;


-- ======================================================================================================== INTERMEDIATE


-- --------------------------------------------------------------------------- SH-COUNTRIES-I-01 Countries per subregion
-- The regional directors want the number of countries in each region and subregion.
SELECT COUNTRY_REGION, COUNTRY_SUBREGION, COUNT(*) AS COUNTRIES
FROM COUNTRIES
GROUP BY COUNTRY_REGION, COUNTRY_SUBREGION
ORDER BY COUNTRY_REGION, COUNTRY_SUBREGION
;


-- ============================================================================================================ ADVANCED


-- ------------------------------------------------------------ SH-COUNTRIES-A-01 Is the geography hierarchy consistent?
-- COUNTRIES is a snowflaked dimension: every row repeats its subregion and its region, by name and by id. Before the
-- regional roll-ups are trusted, the data-warehouse team wants proof that each subregion id carries one name and
-- belongs to one region. List the subregion ids that break either rule.
SELECT COUNTRY_SUBREGION_ID,
       COUNT(DISTINCT COUNTRY_SUBREGION) AS NAMES,
       COUNT(DISTINCT COUNTRY_REGION_ID) AS REGIONS
FROM COUNTRIES
GROUP BY COUNTRY_SUBREGION_ID
HAVING COUNT(DISTINCT COUNTRY_SUBREGION) > 1
    OR COUNT(DISTINCT COUNTRY_REGION_ID) > 1
;
