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
;

-- -------------------------------------------------------------------------------------------------------- COUNTRY_NAME

-- ----------------------------------------------------------------------------------------------------------- REGION_ID