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
-- Exercises on DEPARTMENTS, a problem per query that reads DEPARTMENTS alone, at the basic level.
--
-- These are the basic SQL solutions of the 'DEPARTMENTS' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------- HR_DEPARTMENTS_B_01
-- Facilities is planning work on one site (given the location) and needs the departments housed there.
SELECT *
FROM DEPARTMENTS
WHERE LOCATION_ID = :locationId
ORDER BY DEPARTMENT_ID ASC
;


-- ------------------------------------------------------------------------------------------------- HR_DEPARTMENTS_B_02
-- HR is filling vacant management posts and wants every department with no manager on record.
SELECT DEPARTMENT_ID, DEPARTMENT_NAME
FROM DEPARTMENTS
WHERE MANAGER_ID IS NULL
ORDER BY DEPARTMENT_ID ASC
;
