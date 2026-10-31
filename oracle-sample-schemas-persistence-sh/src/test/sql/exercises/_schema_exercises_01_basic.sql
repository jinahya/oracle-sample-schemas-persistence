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
-- Exercises on the whole schema, a problem per query that reads more than one table or view, at the basic level.
--
-- These are the basic SQL solutions of the 'Whole schema' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- Problems that join or subquery more than one of the objects above.
--


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_B_01
-- A customer-service agent needs the purchase history of customer :custId, newest first: day, product, channel,
-- quantity and amount.
SELECT s.TIME_ID, p.PROD_NAME, ch.CHANNEL_DESC, s.QUANTITY_SOLD, s.AMOUNT_SOLD
FROM SALES s
         JOIN PRODUCTS p ON p.PROD_ID = s.PROD_ID
         JOIN CHANNELS ch ON ch.CHANNEL_ID = s.CHANNEL_ID
WHERE s.CUST_ID = :custId
ORDER BY s.TIME_ID DESC, p.PROD_NAME
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_B_02
-- The account-management screen lists the customers of :city together with their country and region. Customer's country
-- association is lazy, so listing them naively issues one more query per country. Load both in one query.
SELECT cu.CUST_ID, cu.CUST_FIRST_NAME, cu.CUST_LAST_NAME, co.COUNTRY_ID, co.COUNTRY_NAME, co.COUNTRY_REGION
FROM CUSTOMERS cu
         JOIN COUNTRIES co ON co.COUNTRY_ID = cu.COUNTRY_ID
WHERE cu.CUST_CITY = :city
ORDER BY cu.CUST_LAST_NAME, cu.CUST_FIRST_NAME
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_B_03
-- The sales director wants units sold and revenue per channel for calendar year :year, biggest channel first.
SELECT ch.CHANNEL_DESC,
       SUM(s.QUANTITY_SOLD) AS UNITS,
       SUM(s.AMOUNT_SOLD)   AS REVENUE
FROM SALES s
         JOIN CHANNELS ch ON ch.CHANNEL_ID = s.CHANNEL_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY ch.CHANNEL_DESC
ORDER BY REVENUE DESC
;
