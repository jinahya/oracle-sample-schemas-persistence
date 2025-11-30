-- #%L
-- hr
-- %%
-- Copyright (C) 2024 - 2025 Jinahya, Inc.
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
-- ------------------------------------------------------------------------------------------------------ LOCATION_ID
SELECT l.LOCATION_ID, c.COUNTRY_ID, r.REGION_ID
FROM LOCATIONS l
         JOIN COUNTRIES c ON l.COUNTRY_ID = c.COUNTRY_ID
         JOIN REGIONS r ON c.REGION_ID = r.REGION_ID
;
-- ------------------------------------------------------------------------------------------------------ STREET_ADDRESS

-- --------------------------------------------------------------------------------------------------------- POSTAL_CODE

-- ---------------------------------------------------------------------------------------------------------------- CITY

-- ------------------------------------------------------------------------------------------------------ STATE_PROVINCE

-- ---------------------------------------------------------------------------------------------------------- COUNTRY_ID
