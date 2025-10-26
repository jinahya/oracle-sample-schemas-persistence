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
SELECT EMPLOYEE_ID, COUNT(1) c
FROM EMP_DETAILS_VIEW
GROUP BY EMPLOYEE_ID
HAVING COUNT(1) > 1
;

-- -------------------------------------------------------------------------------------------------------------- JOB_ID

-- ---------------------------------------------------------------------------------------------------------- MANAGER_ID

-- ------------------------------------------------------------------------------------------------------- DEPARTMENT_ID

-- --------------------------------------------------------------------------------------------------------- LOCATION_ID

-- ----------------------------------------------------------------------------------------------------- COUNTRY_ID

-- ----------------------------------------------------------------------------------------------------- FIRST_NAME

-- ----------------------------------------------------------------------------------------------------- LAST_NAME

-- ----------------------------------------------------------------------------------------------------- SALARY

-- ----------------------------------------------------------------------------------------------------- COMMISSION_PCT

-- ----------------------------------------------------------------------------------------------------- DEPARTMENT_NAME

-- ----------------------------------------------------------------------------------------------------- JOB_TITLE

-- ----------------------------------------------------------------------------------------------------- CITY

-- ----------------------------------------------------------------------------------------------------- STATE_PROVINCE

-- ----------------------------------------------------------------------------------------------------- COUNTRY_NAME

-- ----------------------------------------------------------------------------------------------------- REGION_NAME
