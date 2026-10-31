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
-- Exercises on SALES, a problem per query that reads SALES alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'SALES' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ------------------------------------------------------------------------------------------------------- SH_SALES_A_01
-- The retention team studies buying rhythm. For customer :custId, list each day they bought something and the day of
-- their previous purchase, so the gap between the two can be measured.
SELECT TIME_ID,
       LAG(TIME_ID) OVER (ORDER BY TIME_ID)           AS PREVIOUS_DAY,
       TIME_ID - LAG(TIME_ID) OVER (ORDER BY TIME_ID) AS DAYS_BETWEEN
FROM (SELECT DISTINCT TIME_ID
      FROM SALES
      WHERE CUST_ID = :custId)
ORDER BY TIME_ID
;
