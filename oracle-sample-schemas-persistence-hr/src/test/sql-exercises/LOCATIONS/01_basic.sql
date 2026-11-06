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
-- Exercises on LOCATIONS, a problem per query that reads LOCATIONS alone, at the basic level.
--
-- These are the basic SQL solutions of the 'LOCATIONS' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- HR_LOCATIONS_B_01
-- Facilities lists the company's sites in one country (given its code, such as US).
SELECT LOCATION_ID, STREET_ADDRESS, CITY, STATE_PROVINCE
FROM LOCATIONS
WHERE COUNTRY_ID = :countryId
ORDER BY CITY ASC
;


-- --------------------------------------------------------------------------------------------------- HR_LOCATIONS_B_02
-- The mail room cannot send parcels to a site without a postal code. Which sites lack one?
SELECT LOCATION_ID, STREET_ADDRESS, CITY, COUNTRY_ID
FROM LOCATIONS
WHERE POSTAL_CODE IS NULL
;
