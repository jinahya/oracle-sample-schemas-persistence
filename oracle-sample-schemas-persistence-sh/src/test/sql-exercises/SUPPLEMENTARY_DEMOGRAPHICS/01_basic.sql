-- #%L
-- sh
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
-- Exercises on SUPPLEMENTARY_DEMOGRAPHICS, a problem per query that reads SUPPLEMENTARY_DEMOGRAPHICS alone, at the
-- basic level.
--
-- These are the basic SQL solutions of the 'SUPPLEMENTARY_DEMOGRAPHICS' section of EXERCISES.asciidoc, which also
-- states each problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it
-- answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ---------------------------------------------------------------------------------- SH_SUPPLEMENTARY_DEMOGRAPHICS_B_01
-- The loyalty programme is inviting its affinity-card holders of occupation :occupation (e.g. Exec.) to a focus group,
-- and wants their customer ids with education, household size and years of residence.
SELECT CUST_ID, EDUCATION, HOUSEHOLD_SIZE, YRS_RESIDENCE
FROM SUPPLEMENTARY_DEMOGRAPHICS
WHERE OCCUPATION = :occupation
  AND AFFINITY_CARD = 1
ORDER BY CUST_ID
;
