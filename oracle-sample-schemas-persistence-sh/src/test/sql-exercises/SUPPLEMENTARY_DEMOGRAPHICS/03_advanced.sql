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
-- advanced level.
--
-- These are the advanced SQL solutions of the 'SUPPLEMENTARY_DEMOGRAPHICS' section of EXERCISES.asciidoc, which also
-- states each problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it
-- answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ---------------------------------------------------------------------------------- SH_SUPPLEMENTARY_DEMOGRAPHICS_A_01
-- The loyalty programme wants the occupations whose affinity-card take-up rate is above the rate across all customers,
-- the highest first; the customers with no occupation recorded are not an occupation, and `OCCUPATION` breaks a tie.
-- SH_SUPPLEMENTARY_DEMOGRAPHICS_I_02 checks that every card indicator is `0` or `1`, which the averages assume.
SELECT OCCUPATION, COUNT(*) AS CUSTOMERS, AVG(AFFINITY_CARD) AS TAKE_UP
FROM SUPPLEMENTARY_DEMOGRAPHICS
WHERE OCCUPATION IS NOT NULL
GROUP BY OCCUPATION
HAVING AVG(AFFINITY_CARD) > (SELECT AVG(AFFINITY_CARD) FROM SUPPLEMENTARY_DEMOGRAPHICS)
ORDER BY TAKE_UP DESC, OCCUPATION
;
