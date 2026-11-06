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
-- Exercises on DEPARTMENTS, a problem per query that reads DEPARTMENTS alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'DEPARTMENTS' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------- HR_DEPARTMENTS_A_01
-- Department numbers go up in tens. Before creating a department, HR wants the lowest number that follows an existing
-- department and is not taken yet.
SELECT MIN(d.DEPARTMENT_ID + 10) AS NEXT_FREE
FROM DEPARTMENTS d
WHERE NOT EXISTS (SELECT 1 FROM DEPARTMENTS x WHERE x.DEPARTMENT_ID = d.DEPARTMENT_ID + 10)
;
