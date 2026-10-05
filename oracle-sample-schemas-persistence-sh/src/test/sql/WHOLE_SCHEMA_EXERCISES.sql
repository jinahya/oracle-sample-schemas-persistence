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
-- Exercises on the whole schema, a problem per query that reads more than one table or view; Basic, then Intermediate,
-- then Advanced.
--
-- These are the SQL solutions of the 'Whole schema' section of EXERCISES.adoc, which also states each problem in full
-- and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- Problems that join or subquery more than one of the objects above.
--


-- =============================================================================================================== BASIC


-- ------------------------------------------------------------------------ SH-SCHEMA-B-01 A customer's purchase history
-- A customer-service agent needs the purchase history of customer :custId, newest first: day, product, channel,
-- quantity and amount.
SELECT s.TIME_ID, p.PROD_NAME, ch.CHANNEL_DESC, s.QUANTITY_SOLD, s.AMOUNT_SOLD
FROM SALES s
         JOIN PRODUCTS p ON p.PROD_ID = s.PROD_ID
         JOIN CHANNELS ch ON ch.CHANNEL_ID = s.CHANNEL_ID
WHERE s.CUST_ID = :custId
ORDER BY s.TIME_ID DESC, p.PROD_NAME
;


-- ------------------------------------------------------ SH-SCHEMA-B-02 Customers with their country, in one round trip
-- The account-management screen lists the customers of :city together with their country and region. Customer's country
-- association is lazy, so listing them naively issues one more query per country. Load both in one query.
SELECT cu.CUST_ID, cu.CUST_FIRST_NAME, cu.CUST_LAST_NAME, co.COUNTRY_ID, co.COUNTRY_NAME, co.COUNTRY_REGION
FROM CUSTOMERS cu
         JOIN COUNTRIES co ON co.COUNTRY_ID = cu.COUNTRY_ID
WHERE cu.CUST_CITY = :city
ORDER BY cu.CUST_LAST_NAME, cu.CUST_FIRST_NAME
;


-- ----------------------------------------------------------------------------------- SH-SCHEMA-B-03 Revenue by channel
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


-- ======================================================================================================== INTERMEDIATE


-- -------------------------------------------------------------------------- SH-SCHEMA-I-01 Category revenue by quarter
-- Each product line manager wants revenue for their category per calendar quarter of :year, to see seasonality.
SELECT t.CALENDAR_QUARTER_DESC, p.PROD_CATEGORY, SUM(s.AMOUNT_SOLD) AS REVENUE
FROM SALES s
         JOIN PRODUCTS p ON p.PROD_ID = s.PROD_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY t.CALENDAR_QUARTER_DESC, p.PROD_CATEGORY
ORDER BY t.CALENDAR_QUARTER_DESC, REVENUE DESC
;


-- ------------------------------------------------------------------------------------- SH-SCHEMA-I-02 Ten best sellers
-- Merchandising wants the ten products that sold the most units in :year, with the revenue they brought in.
SELECT p.PROD_ID, p.PROD_NAME, SUM(s.QUANTITY_SOLD) AS UNITS, SUM(s.AMOUNT_SOLD) AS REVENUE
FROM SALES s
         JOIN PRODUCTS p ON p.PROD_ID = s.PROD_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY p.PROD_ID, p.PROD_NAME
ORDER BY UNITS DESC
    FETCH FIRST 10 ROWS ONLY
;


-- --------------------------------------------------------------------- SH-SCHEMA-I-03 Countries above a revenue target
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


-- ------------------------------------------------------------------------ SH-SCHEMA-I-04 Promotions that moved revenue
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


-- ------------------------------------------------------------------------------ SH-SCHEMA-I-05 Revenue by fiscal month
-- Finance reports by fiscal period. Give revenue per fiscal month of fiscal year :fiscalYear.
SELECT t.FISCAL_MONTH_DESC, SUM(s.AMOUNT_SOLD) AS REVENUE
FROM SALES s
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.FISCAL_YEAR = :fiscalYear
GROUP BY t.FISCAL_MONTH_DESC
ORDER BY t.FISCAL_MONTH_DESC
;


-- ------------------------------------------------------------------------------- SH-SCHEMA-I-06 Weekend versus weekday
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


-- ----------------------------------------------------------------- SH-SCHEMA-I-07 Revenue by gender and marital status
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


-- ------------------------------------------------------------------- SH-SCHEMA-I-08 Affinity-card holders by education
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


-- -------------------------------------------------------------------- SH-SCHEMA-I-09 Realised price against list price
-- The product line managers suspect heavy discounting. For each product, compare the average price actually realised
-- per unit in :year with its list price.
SELECT p.PROD_ID,
       p.PROD_NAME,
       p.PROD_LIST_PRICE,
       SUM(s.AMOUNT_SOLD) / SUM(s.QUANTITY_SOLD)                     AS REALISED_PRICE,
       SUM(s.AMOUNT_SOLD) / SUM(s.QUANTITY_SOLD) / p.PROD_LIST_PRICE AS REALISED_RATIO
FROM SALES s
         JOIN PRODUCTS p ON p.PROD_ID = s.PROD_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY p.PROD_ID, p.PROD_NAME, p.PROD_LIST_PRICE
ORDER BY REALISED_RATIO
;


-- ============================================================================================================ ADVANCED


-- ----------------------------------------------------------------------- SH-SCHEMA-A-01 Dormant products and customers
-- Two clean-up requests. Merchandising wants the products that have never been sold; marketing wants the number of
-- customers, per country, who have never bought anything.
SELECT p.PROD_ID, p.PROD_NAME
FROM PRODUCTS p
WHERE NOT EXISTS (SELECT 1 FROM SALES s WHERE s.PROD_ID = p.PROD_ID)
ORDER BY p.PROD_ID
;

SELECT co.COUNTRY_NAME, COUNT(*) AS DORMANT_CUSTOMERS
FROM CUSTOMERS cu
         JOIN COUNTRIES co ON co.COUNTRY_ID = cu.COUNTRY_ID
WHERE NOT EXISTS (SELECT 1 FROM SALES s WHERE s.CUST_ID = cu.CUST_ID)
GROUP BY co.COUNTRY_NAME
ORDER BY DORMANT_CUSTOMERS DESC
;


-- ------------------------------------------------------------------------------------- SH-SCHEMA-A-02 Lapsed customers
-- The retention team wants the customers who bought something in :previousYear but nothing in :currentYear.
SELECT s.CUST_ID
FROM SALES s
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :previousYear
MINUS
SELECT s.CUST_ID
FROM SALES s
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :currentYear
;


-- --------------------------------------------------------------- SH-SCHEMA-A-03 Customers who buy through two channels
-- The channel managers for Internet and Direct Sales want to know which customers bought through both of their channels
-- in :year.
SELECT s.CUST_ID
FROM SALES s
         JOIN CHANNELS ch ON ch.CHANNEL_ID = s.CHANNEL_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
  AND ch.CHANNEL_DESC = :channelA
INTERSECT
SELECT s.CUST_ID
FROM SALES s
         JOIN CHANNELS ch ON ch.CHANNEL_ID = s.CHANNEL_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
  AND ch.CHANNEL_DESC = :channelB
;


-- ------------------------------------------------------------------ SH-SCHEMA-A-04 Top three products of each category
-- Each product line manager wants the three products of their category with the highest revenue in :year.
SELECT PROD_CATEGORY, PROD_NAME, REVENUE, RNK
FROM (SELECT p.PROD_CATEGORY,
             p.PROD_NAME,
             SUM(s.AMOUNT_SOLD)                                                          AS REVENUE,
             RANK() OVER (PARTITION BY p.PROD_CATEGORY ORDER BY SUM(s.AMOUNT_SOLD) DESC) AS RNK
      FROM SALES s
               JOIN PRODUCTS p ON p.PROD_ID = s.PROD_ID
               JOIN TIMES t ON t.TIME_ID = s.TIME_ID
      WHERE t.CALENDAR_YEAR = :year
      GROUP BY p.PROD_CATEGORY, p.PROD_ID, p.PROD_NAME)
WHERE RNK <= 3
ORDER BY PROD_CATEGORY, RNK
;


-- ----------------------------------------------------------------------------- SH-SCHEMA-A-05 Gross margin by category
-- Finance wants the gross margin of each product category in :year: revenue minus cost, where the cost of a sale is the
-- quantity sold times the unit cost recorded in COSTS for the same product, day, channel and promotion.
SELECT p.PROD_CATEGORY,
       SUM(s.AMOUNT_SOLD)                                 AS REVENUE,
       SUM(s.QUANTITY_SOLD * c.UNIT_COST)                 AS COST,
       SUM(s.AMOUNT_SOLD - s.QUANTITY_SOLD * c.UNIT_COST) AS MARGIN,
       SUM(s.AMOUNT_SOLD - s.QUANTITY_SOLD * c.UNIT_COST)
           / SUM(s.AMOUNT_SOLD)                           AS MARGIN_RATIO
FROM SALES s
         JOIN COSTS c ON c.PROD_ID = s.PROD_ID
    AND c.TIME_ID = s.TIME_ID
    AND c.CHANNEL_ID = s.CHANNEL_ID
    AND c.PROMO_ID = s.PROMO_ID
         JOIN PRODUCTS p ON p.PROD_ID = s.PROD_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY p.PROD_CATEGORY
ORDER BY MARGIN DESC
;

SELECT p.PROD_CATEGORY, SUM(pr.AMOUNT_SOLD) AS REVENUE, SUM(pr.AMOUNT_SOLD - pr.TOTAL_COST) AS MARGIN
FROM PROFITS pr
         JOIN PRODUCTS p ON p.PROD_ID = pr.PROD_ID
         JOIN TIMES t ON t.TIME_ID = pr.TIME_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY p.PROD_CATEGORY
ORDER BY MARGIN DESC
;


-- --------------------------------------------------------------------- SH-SCHEMA-A-06 Year-over-year growth by channel
-- The sales director wants each channel's revenue in :previousYear and :currentYear side by side, with the growth rate.
SELECT ch.CHANNEL_DESC,
       SUM(CASE WHEN t.CALENDAR_YEAR = :previousYear THEN s.AMOUNT_SOLD ELSE 0 END)                  AS PREVIOUS,
       SUM(CASE WHEN t.CALENDAR_YEAR = :currentYear THEN s.AMOUNT_SOLD ELSE 0 END)                   AS CURRENT_,
       (SUM(CASE WHEN t.CALENDAR_YEAR = :currentYear THEN s.AMOUNT_SOLD ELSE 0 END)
           - SUM(CASE WHEN t.CALENDAR_YEAR = :previousYear THEN s.AMOUNT_SOLD ELSE 0 END))
           / NULLIF(SUM(CASE WHEN t.CALENDAR_YEAR = :previousYear THEN s.AMOUNT_SOLD ELSE 0 END), 0) AS GROWTH
FROM SALES s
         JOIN CHANNELS ch ON ch.CHANNEL_ID = s.CHANNEL_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR IN (:previousYear, :currentYear)
GROUP BY ch.CHANNEL_DESC
ORDER BY GROWTH DESC NULLS LAST
;


-- ------------------------------------------------------------------------ SH-SCHEMA-A-07 Channel and quarter subtotals
-- For the quarterly business review, the sales director wants :year revenue per channel and calendar quarter, with a
-- subtotal per channel and a grand total, in one result.
SELECT CASE WHEN GROUPING(ch.CHANNEL_DESC) = 1 THEN 'All channels' ELSE ch.CHANNEL_DESC END                 AS CHANNEL,
       CASE WHEN GROUPING(t.CALENDAR_QUARTER_DESC) = 1 THEN 'All quarters' ELSE t.CALENDAR_QUARTER_DESC END AS QUARTER,
       SUM(s.AMOUNT_SOLD)                                                                                   AS REVENUE
FROM SALES s
         JOIN CHANNELS ch ON ch.CHANNEL_ID = s.CHANNEL_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY ROLLUP (ch.CHANNEL_DESC, t.CALENDAR_QUARTER_DESC)
ORDER BY GROUPING(ch.CHANNEL_DESC), ch.CHANNEL_DESC, GROUPING(t.CALENDAR_QUARTER_DESC), t.CALENDAR_QUARTER_DESC
;


-- --------------------------------------------------------------------------------------- SH-SCHEMA-A-08 Mailing labels
-- The direct-mail agency wants one line per customer of :city in country :isoCode (e.g. Los Angeles, US): an account
-- reference C-<id>, the name as initial and upper-case surname, the street address, the postal code and city, and the
-- phone number with its dashes removed.
SELECT 'C-' || TO_CHAR(cu.CUST_ID)                                          AS ACCOUNT,
       SUBSTR(cu.CUST_FIRST_NAME, 1, 1) || '. ' || UPPER(cu.CUST_LAST_NAME) AS NAME,
       cu.CUST_STREET_ADDRESS                                               AS STREET,
       cu.CUST_POSTAL_CODE || ' ' || cu.CUST_CITY                           AS TOWN,
       REPLACE(cu.CUST_MAIN_PHONE_NUMBER, '-', '')                          AS PHONE
FROM CUSTOMERS cu
         JOIN COUNTRIES co ON co.COUNTRY_ID = cu.COUNTRY_ID
WHERE cu.CUST_CITY = :city
  AND co.COUNTRY_ISO_CODE = :isoCode
ORDER BY cu.CUST_LAST_NAME, cu.CUST_FIRST_NAME
;


-- --------------------------------------------------------------- SH-SCHEMA-A-09 Reconcile sale dates with the calendar
-- Before signing off a year-end report, finance wants proof that the calendar dimension and the facts agree: how many
-- sales of :year (by the sale date itself) are attributed to a different CALENDAR_YEAR in TIMES? The expected answer is
-- zero.
SELECT COUNT(*)
FROM SALES s
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE EXTRACT(YEAR FROM s.TIME_ID) = :year
  AND t.CALENDAR_YEAR <> EXTRACT(YEAR FROM s.TIME_ID)
;
