--
-- create table HR.REGIONS
-- (
--     REGION_ID   NUMBER not null
--         constraint REG_ID_PK
--             primary key
--         constraint REGION_ID_NN
--             check ("REGION_ID" IS NOT NULL),
--     REGION_NAME VARCHAR2(25)
-- )
-- /
--
-- comment on table HR.REGIONS is 'Regions table that contains region numbers and names. references with the Countries table.'
-- /
--
-- comment on column HR.REGIONS.REGION_ID is 'Primary key of regions table.'
-- /
--
-- comment on column HR.REGIONS.REGION_NAME is 'Names of regions. Locations are in the countries of these regions.'
-- /


-- ----------------------------------------------------------------------------------------------------------- REGION_ID

-- --------------------------------------------------------------------------------------------------------- REGION_NAME