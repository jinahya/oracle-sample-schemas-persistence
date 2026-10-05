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
-- Exercises on DEPARTMENTS, a problem per query that reads DEPARTMENTS alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'DEPARTMENTS' section of EXERCISES.adoc, which also states each problem in full
-- and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- ----------------------------------------------------------------------- HR-DEPARTMENTS-B-01 Departments in a building
-- Facilities is planning work on one site (given the location) and needs the departments housed there.
SELECT *
FROM DEPARTMENTS
WHERE LOCATION_ID = :locationId
ORDER BY DEPARTMENT_ID ASC
;


-- ------------------------------------------------------------------- HR-DEPARTMENTS-B-02 Departments without a manager
-- HR is filling vacant management posts and wants every department with no manager on record.
SELECT DEPARTMENT_ID, DEPARTMENT_NAME
FROM DEPARTMENTS
WHERE MANAGER_ID IS NULL
ORDER BY DEPARTMENT_ID ASC
;


-- ======================================================================================================== INTERMEDIATE


-- ---------------------------------------------------------------------------- HR-DEPARTMENTS-I-01 Departments per site
-- Facilities sizes each site's reception by the number of departments it houses.
SELECT LOCATION_ID, COUNT(*) AS DEPARTMENTS
FROM DEPARTMENTS
GROUP BY LOCATION_ID
ORDER BY DEPARTMENTS DESC, LOCATION_ID ASC
;


-- ------------------------------------------------------------------------- HR-DEPARTMENTS-I-02 Departments at a glance
-- HR's quarterly dashboard shows three numbers: how many departments there are, how many of them have a manager, and on
-- how many sites they sit.
SELECT COUNT(*) AS DEPARTMENTS, COUNT(MANAGER_ID) AS MANAGED, COUNT(DISTINCT LOCATION_ID) AS SITES
FROM DEPARTMENTS
;


-- ============================================================================================================ ADVANCED


-- --------------------------------------------------------------------- HR-DEPARTMENTS-A-01 Next free department number
-- Department numbers go up in tens. Before creating a department, HR wants the lowest number that follows an existing
-- department and is not taken yet.
SELECT MIN(d.DEPARTMENT_ID + 10) AS NEXT_FREE
FROM DEPARTMENTS d
WHERE NOT EXISTS (SELECT 1 FROM DEPARTMENTS x WHERE x.DEPARTMENT_ID = d.DEPARTMENT_ID + 10)
;
