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
-- Exercises on COUNTRIES, a problem per query that reads COUNTRIES alone, at the basic level.
--
-- These are the basic SQL solutions of the 'COUNTRIES' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- HR_COUNTRIES_B_01
-- The shipping form has a two-letter country code, such as US, and needs the country's name.
SELECT COUNTRY_NAME
FROM COUNTRIES
WHERE COUNTRY_ID = :countryId
;


-- --------------------------------------------------------------------------------------------------- HR_COUNTRIES_B_02
-- A regional director wants the countries of their region (given the region id), alphabetically.
SELECT COUNTRY_ID, COUNTRY_NAME
FROM COUNTRIES
WHERE REGION_ID = :regionId
ORDER BY COUNTRY_NAME ASC
;


-- --------------------------------------------------------------------------------------------------- HR_COUNTRIES_B_03
-- The badge printer's country field holds 20 characters. Which country names will not fit?
SELECT COUNTRY_ID, COUNTRY_NAME, LENGTH(COUNTRY_NAME) AS NAME_LENGTH
FROM COUNTRIES
WHERE LENGTH(COUNTRY_NAME) > :maxLength
ORDER BY NAME_LENGTH DESC
;
