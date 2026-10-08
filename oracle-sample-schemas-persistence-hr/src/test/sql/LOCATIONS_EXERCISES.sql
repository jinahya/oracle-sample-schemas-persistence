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
-- Exercises on LOCATIONS, a problem per query that reads LOCATIONS alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'LOCATIONS' section of EXERCISES.adoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- -------------------------------------------------------------------------------- HR-LOCATIONS-B-01 Sites in a country
-- Facilities lists the company's sites in one country (given its code, such as US).
SELECT LOCATION_ID, STREET_ADDRESS, CITY, STATE_PROVINCE
FROM LOCATIONS
WHERE COUNTRY_ID = :countryId
ORDER BY CITY ASC
;


-- ----------------------------------------------------------------------- HR-LOCATIONS-B-02 Sites without a postal code
-- The mail room cannot send parcels to a site without a postal code. Which sites lack one?
SELECT LOCATION_ID, STREET_ADDRESS, CITY, COUNTRY_ID
FROM LOCATIONS
WHERE POSTAL_CODE IS NULL
;


-- ======================================================================================================== INTERMEDIATE


-- --------------------------------------------------------------------------------- HR-LOCATIONS-I-01 Sites per country
-- Facilities reports how many sites the company has in each country, most first.
SELECT COUNTRY_ID, COUNT(*) AS SITES
FROM LOCATIONS
GROUP BY COUNTRY_ID
ORDER BY SITES DESC, COUNTRY_ID ASC
;


-- ------------------------------------------------------------------------------------ HR-LOCATIONS-I-02 Address labels
-- The mail room prints one address line per site: street, city, then the state or province and the postal code where
-- there is one.
SELECT LOCATION_ID,
       STREET_ADDRESS || ', ' || CITY
           || CASE WHEN STATE_PROVINCE IS NOT NULL THEN ', ' || STATE_PROVINCE END
           || CASE WHEN POSTAL_CODE IS NOT NULL THEN ' ' || POSTAL_CODE END AS LABEL
FROM LOCATIONS
ORDER BY LOCATION_ID ASC
;


-- ============================================================================================================ ADVANCED


-- ------------------------------------------------------------------- HR-LOCATIONS-A-01 The country with the most sites
-- Facilities wants the country, or countries if tied, with the most sites.
SELECT COUNTRY_ID, COUNT(*) AS SITES
FROM LOCATIONS
GROUP BY COUNTRY_ID
HAVING COUNT(*) >= ALL (SELECT COUNT(*) FROM LOCATIONS GROUP BY COUNTRY_ID)
;
