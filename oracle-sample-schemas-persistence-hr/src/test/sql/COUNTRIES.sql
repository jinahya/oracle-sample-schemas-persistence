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
-- create table HR.COUNTRIES
-- (
--     COUNTRY_ID   CHAR(2) not null
--         constraint COUNTRY_C_ID_PK
--             primary key
--         constraint COUNTRY_ID_NN
--             check ("COUNTRY_ID" IS NOT NULL),
--     COUNTRY_NAME VARCHAR2(60),
--     REGION_ID    NUMBER
--         constraint COUNTR_REG_FK
--             references HR.REGIONS
-- )
--     organization index
-- /
--
-- comment on table HR.COUNTRIES is 'country table. References with locations table.'
-- /
--
-- comment on column HR.COUNTRIES.COUNTRY_ID is 'Primary key of countries table.'
-- /
--
-- comment on column HR.COUNTRIES.COUNTRY_NAME is 'Country name'
-- /
--
-- comment on column HR.COUNTRIES.REGION_ID is 'Region ID for the country. Foreign key to region_id column in the departments table.'
-- /


-- ---------------------------------------------------------------------------------------------------------- COUNTRY_ID
SELECT COUNTRY_ID
FROM COUNTRIES
ORDER BY COUNTRY_ID ASC
OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY
;

-- -------------------------------------------------------------------------------------------------------- COUNTRY_NAME

-- ----------------------------------------------------------------------------------------------------------- REGION_ID
