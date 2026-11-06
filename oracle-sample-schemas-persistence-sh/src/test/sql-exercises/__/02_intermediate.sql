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
-- Exercises on the whole schema, a problem per query that reads more than one table or view, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'Whole schema' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- Problems that join or subquery more than one of the objects above.
--


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_I_01
-- Each product line manager wants revenue for their category per calendar quarter of :year, to see seasonality.
SELECT t.CALENDAR_QUARTER_DESC, p.PROD_CATEGORY, SUM(s.AMOUNT_SOLD) AS REVENUE
FROM SALES s
         JOIN PRODUCTS p ON p.PROD_ID = s.PROD_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY t.CALENDAR_QUARTER_DESC, p.PROD_CATEGORY
ORDER BY t.CALENDAR_QUARTER_DESC, REVENUE DESC
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_I_02
-- Merchandising wants the ten products that sold the most units in `:year`, with the revenue they brought in; `PROD_ID`
-- breaks a tie, and `WITH TIES` would keep them all instead.
SELECT p.PROD_ID, p.PROD_NAME, SUM(s.QUANTITY_SOLD) AS UNITS, SUM(s.AMOUNT_SOLD) AS REVENUE
FROM SALES s
         JOIN PRODUCTS p ON p.PROD_ID = s.PROD_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY p.PROD_ID, p.PROD_NAME
ORDER BY UNITS DESC, p.PROD_ID
FETCH FIRST 10 ROWS ONLY
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_I_03
-- Finance wants the countries whose customers spent more than :threshold in :year.
SELECT co.COUNTRY_NAME, SUM(s.AMOUNT_SOLD) AS REVENUE
FROM SALES s
         JOIN CUSTOMERS cu ON cu.CUST_ID = s.CUST_ID
         JOIN COUNTRIES co ON co.COUNTRY_ID = cu.COUNTRY_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY co.COUNTRY_NAME
HAVING SUM(s.AMOUNT_SOLD) > :threshold
ORDER BY REVENUE DESC
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_I_04
-- The promotions team wants to know which promotions actually moved revenue in calendar year :year: for each promotion
-- with sales that year, its revenue and units next to what the promotion cost. Sales made without a promotion are
-- booked against the NO PROMOTION promotion and do not count.
SELECT pr.PROMO_ID,
       pr.PROMO_NAME,
       pr.PROMO_CATEGORY,
       pr.PROMO_COST,
       SUM(s.QUANTITY_SOLD) AS UNITS,
       SUM(s.AMOUNT_SOLD)   AS REVENUE
FROM SALES s
         JOIN PROMOTIONS pr ON pr.PROMO_ID = s.PROMO_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
  AND pr.PROMO_CATEGORY <> 'NO PROMOTION'
GROUP BY pr.PROMO_ID, pr.PROMO_NAME, pr.PROMO_CATEGORY, pr.PROMO_COST
ORDER BY REVENUE DESC
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_I_06
-- Each channel manager wants to know how their channel's :year revenue splits between weekends and weekdays.
-- (DAY_NUMBER_IN_WEEK runs from 1, Monday, to 7, Sunday.)
SELECT ch.CHANNEL_DESC,
       SUM(CASE WHEN t.DAY_NUMBER_IN_WEEK IN (6, 7) THEN s.AMOUNT_SOLD ELSE 0 END) AS WEEKEND,
       SUM(CASE WHEN t.DAY_NUMBER_IN_WEEK IN (6, 7) THEN 0 ELSE s.AMOUNT_SOLD END) AS WEEKDAY
FROM SALES s
         JOIN CHANNELS ch ON ch.CHANNEL_ID = s.CHANNEL_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY ch.CHANNEL_DESC
ORDER BY ch.CHANNEL_DESC
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_I_07
-- Marketing wants :year revenue and the number of buying customers by gender and marital status, with customers of
-- unrecorded marital status shown as unknown rather than as a blank.
SELECT cu.CUST_GENDER,
       COALESCE(cu.CUST_MARITAL_STATUS, 'unknown') AS MARITAL_STATUS,
       COUNT(DISTINCT cu.CUST_ID)                  AS CUSTOMERS,
       SUM(s.AMOUNT_SOLD)                          AS REVENUE
FROM SALES s
         JOIN CUSTOMERS cu ON cu.CUST_ID = s.CUST_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY cu.CUST_GENDER, COALESCE(cu.CUST_MARITAL_STATUS, 'unknown')
ORDER BY cu.CUST_GENDER, REVENUE DESC
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_I_08
-- The loyalty programme wants to know what its affinity-card holders (AFFINITY_CARD = 1) spent in :year, broken down by
-- education level.
SELECT d.EDUCATION, COUNT(DISTINCT s.CUST_ID) AS CUSTOMERS, SUM(s.AMOUNT_SOLD) AS REVENUE
FROM SALES s
         JOIN SUPPLEMENTARY_DEMOGRAPHICS d ON d.CUST_ID = s.CUST_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
  AND d.AFFINITY_CARD = 1
GROUP BY d.EDUCATION
ORDER BY REVENUE DESC
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_I_09
-- Merchandising compares each selling product's average realised unit price in `:year` with its list price; a price or
-- a ratio that cannot be computed is NULL, and comes last; `PROD_ID` breaks a tie.
SELECT p.PROD_ID,
       p.PROD_NAME,
       p.PROD_LIST_PRICE,
       SUM(s.AMOUNT_SOLD) / NULLIF(SUM(s.QUANTITY_SOLD), 0)                                 AS REALISED_PRICE,
       SUM(s.AMOUNT_SOLD) / NULLIF(SUM(s.QUANTITY_SOLD), 0) / NULLIF(p.PROD_LIST_PRICE, 0) AS REALISED_RATIO
FROM SALES s
         JOIN PRODUCTS p ON p.PROD_ID = s.PROD_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY p.PROD_ID, p.PROD_NAME, p.PROD_LIST_PRICE
ORDER BY REALISED_RATIO NULLS LAST, p.PROD_ID
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_I_10
-- Marketing is inviting customers who respond to promotions to a preview evening in :city (e.g. Los Angeles). List the
-- customers of the city who have bought at least once under a promotion, by name.
SELECT cu.CUST_ID, cu.CUST_FIRST_NAME, cu.CUST_LAST_NAME
FROM CUSTOMERS cu
WHERE cu.CUST_CITY = :city
  AND EXISTS (SELECT 1
              FROM SALES s
                       JOIN PROMOTIONS pr ON pr.PROMO_ID = s.PROMO_ID
              WHERE s.CUST_ID = cu.CUST_ID
                AND pr.PROMO_CATEGORY <> 'NO PROMOTION')
ORDER BY cu.CUST_LAST_NAME, cu.CUST_FIRST_NAME, cu.CUST_ID
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_I_11
-- The promotions team wants every promotion it has run, with the number of sales lines and the revenue booked against
-- it, those that sold nothing included, the least productive first. An outer join keeps a promotion with no sale, with
-- a `COUNT` of `0`.
SELECT pr.PROMO_ID,
       pr.PROMO_NAME,
       pr.PROMO_CATEGORY,
       pr.PROMO_COST,
       COUNT(s.PROMO_ID)               AS SALES_LINES,
       COALESCE(SUM(s.AMOUNT_SOLD), 0) AS REVENUE
FROM PROMOTIONS pr
         LEFT JOIN SALES s ON s.PROMO_ID = pr.PROMO_ID
WHERE pr.PROMO_CATEGORY <> 'NO PROMOTION'
GROUP BY pr.PROMO_ID, pr.PROMO_NAME, pr.PROMO_CATEGORY, pr.PROMO_COST
ORDER BY REVENUE, pr.PROMO_COST DESC, pr.PROMO_ID
;
