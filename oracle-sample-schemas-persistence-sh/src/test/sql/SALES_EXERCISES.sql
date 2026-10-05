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
-- Exercises on SALES, a problem per query that reads SALES alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'SALES' section of EXERCISES.adoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- ------------------------------------------------------------------- SH-SALES-B-01 A day's takings through one channel
-- Finance is checking the takings of :day through channel :channelId: every sales line, biggest amount first.
SELECT PROD_ID, CUST_ID, PROMO_ID, QUANTITY_SOLD, AMOUNT_SOLD
FROM SALES
WHERE TIME_ID = :day
  AND CHANNEL_ID = :channelId
ORDER BY AMOUNT_SOLD DESC, PROD_ID, CUST_ID
;


-- ======================================================================================================== INTERMEDIATE


-- ------------------------------------------------------------------------------ SH-SALES-I-01 Top spenders of a period
-- The loyalty programme wants the ten customers who spent the most between :fromDay and :toDay, with the number of
-- sales lines and units behind it.
SELECT CUST_ID, COUNT(*) AS LINES, SUM(QUANTITY_SOLD) AS UNITS, SUM(AMOUNT_SOLD) AS SPENT
FROM SALES
WHERE TIME_ID BETWEEN :fromDay AND :toDay
GROUP BY CUST_ID
ORDER BY SPENT DESC
    FETCH FIRST 10 ROWS ONLY
;


-- ============================================================================================================ ADVANCED


-- ------------------------------------------------------------------- SH-SALES-A-01 Days between a customer's purchases
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
