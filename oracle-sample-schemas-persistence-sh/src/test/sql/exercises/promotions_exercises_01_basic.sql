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
-- Exercises on PROMOTIONS, a problem per query that reads PROMOTIONS alone, at the basic level.
--
-- These are the basic SQL solutions of the 'PROMOTIONS' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- -------------------------------------------------------------------------------------------------- SH_PROMOTIONS_B_01
-- A store manager asks which promotions are running on :day, ending soonest first.
SELECT PROMO_ID, PROMO_NAME, PROMO_CATEGORY, PROMO_BEGIN_DATE, PROMO_END_DATE
FROM PROMOTIONS
WHERE :day BETWEEN PROMO_BEGIN_DATE AND PROMO_END_DATE
ORDER BY PROMO_END_DATE, PROMO_NAME
;


-- -------------------------------------------------------------------------------------------------- SH_PROMOTIONS_B_02
-- The promotions team wants the distinct promotion categories it has run (TV, radio, internet, ...).
SELECT DISTINCT PROMO_CATEGORY
FROM PROMOTIONS
ORDER BY PROMO_CATEGORY
;
