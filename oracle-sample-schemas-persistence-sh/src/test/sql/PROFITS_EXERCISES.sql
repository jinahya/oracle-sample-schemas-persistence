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
-- Exercises on PROFITS, a problem per query that reads PROFITS alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'PROFITS' section of EXERCISES.adoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- ----------------------------------------------------------------------------------- SH-PROFITS-B-01 Loss-making sales
-- Finance wants the sales between :fromDay and :toDay whose invoiced amount was below what the goods cost.
SELECT TIME_ID,
       PROD_ID,
       CUST_ID,
       CHANNEL_ID,
       PROMO_ID,
       QUANTITY_SOLD,
       AMOUNT_SOLD,
       TOTAL_COST
FROM PROFITS
WHERE TIME_ID BETWEEN :fromDay AND :toDay
  AND AMOUNT_SOLD < TOTAL_COST
ORDER BY TIME_ID, PROD_ID, CUST_ID
;


-- ======================================================================================================== INTERMEDIATE


-- ------------------------------------------------------------------------------------ SH-PROFITS-I-01 Thinnest margins
-- Finance wants the ten products with the lowest margin ratio between :fromDay and :toDay, with the revenue and margin
-- behind it.
SELECT PROD_ID,
       SUM(AMOUNT_SOLD)                                 AS REVENUE,
       SUM(AMOUNT_SOLD - TOTAL_COST)                    AS MARGIN,
       SUM(AMOUNT_SOLD - TOTAL_COST) / SUM(AMOUNT_SOLD) AS MARGIN_RATIO
FROM PROFITS
WHERE TIME_ID BETWEEN :fromDay AND :toDay
GROUP BY PROD_ID
ORDER BY MARGIN_RATIO
    FETCH FIRST 10 ROWS ONLY
;


-- ============================================================================================================ ADVANCED


-- ------------------------------------------------------------------------ SH-PROFITS-A-01 Products whose margin shrank
-- Finance wants the products whose margin in calendar year :currentYear was lower than in :previousYear, with both
-- margins.
SELECT PROD_ID,
       SUM(CASE WHEN EXTRACT(YEAR FROM TIME_ID) = :previousYear THEN AMOUNT_SOLD - TOTAL_COST ELSE 0 END) AS PREVIOUS,
       SUM(CASE WHEN EXTRACT(YEAR FROM TIME_ID) = :currentYear THEN AMOUNT_SOLD - TOTAL_COST ELSE 0 END)  AS CURRENT_
FROM PROFITS
WHERE EXTRACT(YEAR FROM TIME_ID) IN (:previousYear, :currentYear)
GROUP BY PROD_ID
HAVING SUM(CASE WHEN EXTRACT(YEAR FROM TIME_ID) = :currentYear THEN AMOUNT_SOLD - TOTAL_COST ELSE 0 END)
           < SUM(CASE WHEN EXTRACT(YEAR FROM TIME_ID) = :previousYear THEN AMOUNT_SOLD - TOTAL_COST ELSE 0 END)
ORDER BY PROD_ID
;
