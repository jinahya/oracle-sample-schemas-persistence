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
-- create view HR.EMP_DETAILS_VIEW as
-- SELECT e.employee_id,
--        e.job_id,
--        e.manager_id,
--        e.department_id,
--        d.location_id,
--        l.country_id,
--        e.first_name,
--        e.last_name,
--        e.salary,
--        e.commission_pct,
--        d.department_name,
--        j.job_title,
--        l.city,
--        l.state_province,
--        c.country_name,
--        r.region_name
-- FROM employees e,
--      departments d,
--      jobs j,
--      locations l,
--      countries c,
--      regions r
-- WHERE e.department_id = d.department_id
--   AND d.location_id = l.location_id
--   AND l.country_id = c.country_id
--   AND c.region_id = r.region_id
--   AND j.job_id = e.job_id
-- WITH READ ONLY
-- /


-- --------------------------------------------------------------------------------------------------------- EMPLOYEE_ID

-- -------------------------------------------------------------------------------------------------------------- JOB_ID

-- ---------------------------------------------------------------------------------------------------------- MANAGER_ID

-- ------------------------------------------------------------------------------------------------------- DEPARTMENT_ID

-- --------------------------------------------------------------------------------------------------------- LOCATION_ID

-- ---------------------------------------------------------------------------------------------------------- COUNTRY_ID

-- ---------------------------------------------------------------------------------------------------------- FIRST_NAME

-- ----------------------------------------------------------------------------------------------------------- LAST_NAME

-- -------------------------------------------------------------------------------------------------------------- SALARY

-- ------------------------------------------------------------------------------------------------------ COMMISSION_PCT

-- ----------------------------------------------------------------------------------------------------- DEPARTMENT_NAME

-- ----------------------------------------------------------------------------------------------------------- JOB_TITLE

-- ---------------------------------------------------------------------------------------------------------------- CITY

-- ------------------------------------------------------------------------------------------------------ STATE_PROVINCE

-- -------------------------------------------------------------------------------------------------------- COUNTRY_NAME

-- --------------------------------------------------------------------------------------------------------- REGION_NAME

-- ------------------------------------------------------------------------------------------------------ CANDIDATE_KEYS
-- the narrowest NOT NULL and unique column(s) this view could map as a JPA @Id.
-- results: ../../../doc/IDs.asciidoc

-- how many rows does the view hold, and how many non-null / distinct values does each column hold?
-- a single-column candidate is one whose NOT_NULLS and DISTINCTS both equal ROW_COUNT.
SELECT COLUMN_NAME, ROW_COUNT, NOT_NULLS, DISTINCTS
FROM (SELECT COUNT(1)                        ROW_COUNT,
             COUNT(EMPLOYEE_ID)              NN_EMPLOYEE_ID,
             COUNT(DISTINCT EMPLOYEE_ID)     DC_EMPLOYEE_ID,
             COUNT(JOB_ID)                   NN_JOB_ID,
             COUNT(DISTINCT JOB_ID)          DC_JOB_ID,
             COUNT(MANAGER_ID)               NN_MANAGER_ID,
             COUNT(DISTINCT MANAGER_ID)      DC_MANAGER_ID,
             COUNT(DEPARTMENT_ID)            NN_DEPARTMENT_ID,
             COUNT(DISTINCT DEPARTMENT_ID)   DC_DEPARTMENT_ID,
             COUNT(LOCATION_ID)              NN_LOCATION_ID,
             COUNT(DISTINCT LOCATION_ID)     DC_LOCATION_ID,
             COUNT(COUNTRY_ID)               NN_COUNTRY_ID,
             COUNT(DISTINCT COUNTRY_ID)      DC_COUNTRY_ID,
             COUNT(FIRST_NAME)               NN_FIRST_NAME,
             COUNT(DISTINCT FIRST_NAME)      DC_FIRST_NAME,
             COUNT(LAST_NAME)                NN_LAST_NAME,
             COUNT(DISTINCT LAST_NAME)       DC_LAST_NAME,
             COUNT(SALARY)                   NN_SALARY,
             COUNT(DISTINCT SALARY)          DC_SALARY,
             COUNT(COMMISSION_PCT)           NN_COMMISSION_PCT,
             COUNT(DISTINCT COMMISSION_PCT)  DC_COMMISSION_PCT,
             COUNT(DEPARTMENT_NAME)          NN_DEPARTMENT_NAME,
             COUNT(DISTINCT DEPARTMENT_NAME) DC_DEPARTMENT_NAME,
             COUNT(JOB_TITLE)                NN_JOB_TITLE,
             COUNT(DISTINCT JOB_TITLE)       DC_JOB_TITLE,
             COUNT(CITY)                     NN_CITY,
             COUNT(DISTINCT CITY)            DC_CITY,
             COUNT(STATE_PROVINCE)           NN_STATE_PROVINCE,
             COUNT(DISTINCT STATE_PROVINCE)  DC_STATE_PROVINCE,
             COUNT(COUNTRY_NAME)             NN_COUNTRY_NAME,
             COUNT(DISTINCT COUNTRY_NAME)    DC_COUNTRY_NAME,
             COUNT(REGION_NAME)              NN_REGION_NAME,
             COUNT(DISTINCT REGION_NAME)     DC_REGION_NAME
      FROM EMP_DETAILS_VIEW) UNPIVOT ((NOT_NULLS, DISTINCTS)
    FOR COLUMN_NAME IN (
   (NN_EMPLOYEE_ID
   , DC_EMPLOYEE_ID ) AS 'EMPLOYEE_ID'
   , (NN_JOB_ID
   , DC_JOB_ID ) AS 'JOB_ID'
   , (NN_MANAGER_ID
   , DC_MANAGER_ID ) AS 'MANAGER_ID'
   , (NN_DEPARTMENT_ID
   , DC_DEPARTMENT_ID ) AS 'DEPARTMENT_ID'
   , (NN_LOCATION_ID
   , DC_LOCATION_ID ) AS 'LOCATION_ID'
   , (NN_COUNTRY_ID
   , DC_COUNTRY_ID ) AS 'COUNTRY_ID'
   , (NN_FIRST_NAME
   , DC_FIRST_NAME ) AS 'FIRST_NAME'
   , (NN_LAST_NAME
   , DC_LAST_NAME ) AS 'LAST_NAME'
   , (NN_SALARY
   , DC_SALARY ) AS 'SALARY'
   , (NN_COMMISSION_PCT
   , DC_COMMISSION_PCT ) AS 'COMMISSION_PCT'
   , (NN_DEPARTMENT_NAME
   , DC_DEPARTMENT_NAME) AS 'DEPARTMENT_NAME'
   , (NN_JOB_TITLE
   , DC_JOB_TITLE ) AS 'JOB_TITLE'
   , (NN_CITY
   , DC_CITY ) AS 'CITY'
   , (NN_STATE_PROVINCE
   , DC_STATE_PROVINCE ) AS 'STATE_PROVINCE'
   , (NN_COUNTRY_NAME
   , DC_COUNTRY_NAME ) AS 'COUNTRY_NAME'
   , (NN_REGION_NAME
   , DC_REGION_NAME ) AS 'REGION_NAME'
    ))
ORDER BY DISTINCTS DESC, COLUMN_NAME
;

-- no rows: EMPLOYEE_ID -- EMPLOYEES' primary key, joined to at most one row of every other table --
-- identifies a row on its own.
SELECT EMPLOYEE_ID, COUNT(1) c
FROM EMP_DETAILS_VIEW
GROUP BY EMPLOYEE_ID
HAVING COUNT(1) > 1
;
