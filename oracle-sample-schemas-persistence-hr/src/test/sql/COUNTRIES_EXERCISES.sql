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
-- Exercises on COUNTRIES, a problem per query that reads COUNTRIES alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'COUNTRIES' section of EXERCISES.adoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- ----------------------------------------------------------------------------------- HR-COUNTRIES-B-01 Country by code
-- The shipping form has a two-letter country code, such as US, and needs the country's name.
SELECT COUNTRY_NAME
FROM COUNTRIES
WHERE COUNTRY_ID = :countryId
;


-- ----------------------------------------------------------------------------- HR-COUNTRIES-B-02 Countries of a region
-- A regional director wants the countries of their region (given the region id), alphabetically.
SELECT COUNTRY_ID, COUNTRY_NAME
FROM COUNTRIES
WHERE REGION_ID = :regionId
ORDER BY COUNTRY_NAME ASC
;


-- ---------------------------------------------------------------- HR-COUNTRIES-B-03 Country names too long for a badge
-- The badge printer's country field holds 20 characters. Which country names will not fit?
SELECT COUNTRY_ID, COUNTRY_NAME, LENGTH(COUNTRY_NAME) AS NAME_LENGTH
FROM COUNTRIES
WHERE LENGTH(COUNTRY_NAME) > :maxLength
ORDER BY NAME_LENGTH DESC
;


-- ======================================================================================================== INTERMEDIATE


-- ------------------------------------------------------------------------------ HR-COUNTRIES-I-01 Countries per region
-- The regional directors compare territories: how many countries each region covers, most first.
SELECT REGION_ID, COUNT(*) AS COUNTRIES
FROM COUNTRIES
GROUP BY REGION_ID
ORDER BY COUNTRIES DESC, REGION_ID ASC
;


-- ============================================================================================================ ADVANCED


-- -------------------------------------------------------------------- HR-COUNTRIES-A-01 Regions above the average size
-- Head office wants the regions that cover more countries than a region does on average.
SELECT REGION_ID, COUNT(*) AS COUNTRIES
FROM COUNTRIES
GROUP BY REGION_ID
HAVING COUNT(*) > (SELECT COUNT(*) / COUNT(DISTINCT REGION_ID) FROM COUNTRIES)
ORDER BY COUNTRIES DESC
;
