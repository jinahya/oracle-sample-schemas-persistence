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
-- Exercises on CUSTOMERS, a problem per query that reads CUSTOMERS alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'CUSTOMERS' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- SH_CUSTOMERS_I_01
-- The data-quality team wants the e-mail addresses on file for more than one customer, most shared first.
SELECT CUST_EMAIL, COUNT(*) AS CUSTOMERS
FROM CUSTOMERS
WHERE CUST_EMAIL IS NOT NULL
GROUP BY CUST_EMAIL
HAVING COUNT(*) > 1
ORDER BY CUSTOMERS DESC, CUST_EMAIL
;


-- --------------------------------------------------------------------------------------------------- SH_CUSTOMERS_I_02
-- Marketing wants the customer base by decade of birth, with the number of women and men in each decade, the oldest
-- decade first.
SELECT FLOOR(CUST_YEAR_OF_BIRTH / 10) * 10                AS DECADE,
       COUNT(*)                                           AS CUSTOMERS,
       SUM(CASE WHEN CUST_GENDER = 'F' THEN 1 ELSE 0 END) AS WOMEN,
       SUM(CASE WHEN CUST_GENDER = 'M' THEN 1 ELSE 0 END) AS MEN
FROM CUSTOMERS
GROUP BY FLOOR(CUST_YEAR_OF_BIRTH / 10) * 10
ORDER BY DECADE
;
