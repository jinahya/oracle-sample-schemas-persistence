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
-- Exercises on the whole schema, a problem per query that reads more than one table or view, at the advanced level.
--
-- These are the advanced SQL solutions of the 'Whole schema' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- Problems that join or subquery more than one of the objects above.
--


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_A_01
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


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_A_02
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


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_A_03
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


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_A_04
-- Merchandising wants, per category, the three selling products with the highest revenue in `:year`; `PROD_ID` breaks a
-- tie, so a category shows three at most.
SELECT PROD_CATEGORY, PROD_ID, PROD_NAME, REVENUE, RNK
FROM (SELECT p.PROD_CATEGORY,
             p.PROD_ID,
             p.PROD_NAME,
             SUM(s.AMOUNT_SOLD)                                                                   AS REVENUE,
             ROW_NUMBER() OVER (PARTITION BY p.PROD_CATEGORY ORDER BY SUM(s.AMOUNT_SOLD) DESC, p.PROD_ID) AS RNK
      FROM SALES s
               JOIN PRODUCTS p ON p.PROD_ID = s.PROD_ID
               JOIN TIMES t ON t.TIME_ID = s.TIME_ID
      WHERE t.CALENDAR_YEAR = :year
      GROUP BY p.PROD_CATEGORY, p.PROD_ID, p.PROD_NAME)
WHERE RNK <= 3
ORDER BY PROD_CATEGORY, RNK
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_A_05
-- Finance wants revenue, cost, gross margin and margin ratio by category for `:year`, with the number of sales lines
-- that have no cost; a category with such lines has its cost and margin unknown, not computed from the rest.
SELECT p.PROD_CATEGORY,
       SUM(s.AMOUNT_SOLD)                                                                     AS REVENUE,
       COUNT(*) - COUNT(c.UNIT_COST)                                                          AS UNCOSTED_LINES,
       CASE WHEN COUNT(*) = COUNT(c.UNIT_COST) THEN SUM(s.QUANTITY_SOLD * c.UNIT_COST) END    AS COST,
       CASE WHEN COUNT(*) = COUNT(c.UNIT_COST)
            THEN SUM(s.AMOUNT_SOLD - s.QUANTITY_SOLD * c.UNIT_COST) END                       AS MARGIN,
       CASE WHEN COUNT(*) = COUNT(c.UNIT_COST)
            THEN SUM(s.AMOUNT_SOLD - s.QUANTITY_SOLD * c.UNIT_COST) / NULLIF(SUM(s.AMOUNT_SOLD), 0) END AS MARGIN_RATIO
FROM SALES s
         JOIN PRODUCTS p ON p.PROD_ID = s.PROD_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
         LEFT JOIN COSTS c ON c.PROD_ID = s.PROD_ID
    AND c.TIME_ID = s.TIME_ID
    AND c.CHANNEL_ID = s.CHANNEL_ID
    AND c.PROMO_ID = s.PROMO_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY p.PROD_CATEGORY
ORDER BY MARGIN DESC NULLS LAST, p.PROD_CATEGORY
;

SELECT p.PROD_CATEGORY,
       SUM(pr.AMOUNT_SOLD)                 AS REVENUE,
       SUM(pr.AMOUNT_SOLD - pr.TOTAL_COST) AS MARGIN
FROM PROFITS pr
         JOIN PRODUCTS p ON p.PROD_ID = pr.PROD_ID
         JOIN TIMES t ON t.TIME_ID = pr.TIME_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY p.PROD_CATEGORY
ORDER BY MARGIN DESC
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_A_06
-- The sales director wants each channel's revenue in :previousYear and :currentYear side by side, with the growth rate.
SELECT ch.CHANNEL_DESC,
       SUM(CASE WHEN t.CALENDAR_YEAR = :previousYear THEN s.AMOUNT_SOLD ELSE 0 END) AS PREVIOUS,
       SUM(CASE
               WHEN t.CALENDAR_YEAR = :currentYear THEN s.AMOUNT_SOLD
               ELSE 0 END)                                                          AS CURRENT_,
       (SUM(CASE WHEN t.CALENDAR_YEAR = :currentYear THEN s.AMOUNT_SOLD ELSE 0 END)
           - SUM(CASE WHEN t.CALENDAR_YEAR = :previousYear THEN s.AMOUNT_SOLD ELSE 0 END))
           / NULLIF(SUM(CASE WHEN t.CALENDAR_YEAR = :previousYear THEN s.AMOUNT_SOLD ELSE 0 END),
                    0)                                                              AS GROWTH
FROM SALES s
         JOIN CHANNELS ch ON ch.CHANNEL_ID = s.CHANNEL_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR IN (:previousYear, :currentYear)
GROUP BY ch.CHANNEL_DESC
ORDER BY GROWTH DESC NULLS LAST
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_A_07
-- For the quarterly business review, the sales director wants :year revenue per channel and calendar quarter, with a
-- subtotal per channel and a grand total, in one result.
SELECT CASE
           WHEN GROUPING(ch.CHANNEL_DESC) = 1 THEN 'All channels'
           ELSE ch.CHANNEL_DESC END         AS CHANNEL,
       CASE
           WHEN GROUPING(t.CALENDAR_QUARTER_DESC) = 1 THEN 'All quarters'
           ELSE t.CALENDAR_QUARTER_DESC END AS QUARTER,
       SUM(s.AMOUNT_SOLD)                   AS REVENUE
FROM SALES s
         JOIN CHANNELS ch ON ch.CHANNEL_ID = s.CHANNEL_ID
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE t.CALENDAR_YEAR = :year
GROUP BY ROLLUP (ch.CHANNEL_DESC, t.CALENDAR_QUARTER_DESC)
ORDER BY GROUPING(ch.CHANNEL_DESC), ch.CHANNEL_DESC, GROUPING(t.CALENDAR_QUARTER_DESC),
         t.CALENDAR_QUARTER_DESC
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_A_08
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


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_A_09
-- Before signing off a year-end report, finance wants proof that the calendar dimension and the facts agree: how many
-- sales of :year (by the sale date itself) are attributed to a different CALENDAR_YEAR in TIMES? The expected answer is
-- zero.
SELECT COUNT(*)
FROM SALES s
         JOIN TIMES t ON t.TIME_ID = s.TIME_ID
WHERE EXTRACT(YEAR FROM s.TIME_ID) = :year
  AND t.CALENDAR_YEAR <> EXTRACT(YEAR FROM s.TIME_ID)
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_A_10
-- Finance wants every month of `:year`, its revenue, and the average over it and the two months before it, a month
-- without sales counting as `0`; the average is NULL where the calendar has no three months before. The months before
-- January are taken into the window and filtered out of the result afterwards.
WITH BOUNDS AS (SELECT TO_DATE(:year || '-01-01', 'YYYY-MM-DD') AS YEAR_START FROM DUAL),
     MONTHLY AS (SELECT TRUNC(t.TIME_ID, 'MM') AS MONTH_START, COALESCE(SUM(s.AMOUNT_SOLD), 0) AS REVENUE
                 FROM TIMES t
                          CROSS JOIN BOUNDS b
                          LEFT JOIN SALES s ON s.TIME_ID = t.TIME_ID
                 WHERE t.TIME_ID >= ADD_MONTHS(b.YEAR_START, -2)
                   AND t.TIME_ID < ADD_MONTHS(b.YEAR_START, 12)
                 GROUP BY TRUNC(t.TIME_ID, 'MM')),
     AVERAGED AS (SELECT MONTH_START,
                         REVENUE,
                         CASE
                             WHEN COUNT(*) OVER (ORDER BY MONTH_START ROWS BETWEEN 2 PRECEDING AND CURRENT ROW) = 3
                                 THEN ROUND(AVG(REVENUE) OVER (ORDER BY MONTH_START ROWS BETWEEN 2 PRECEDING AND CURRENT ROW), 2)
                             END AS MOVING_AVERAGE
                  FROM MONTHLY)
SELECT TO_CHAR(a.MONTH_START, 'YYYY-MM') AS CALENDAR_MONTH_DESC, a.REVENUE, a.MOVING_AVERAGE
FROM AVERAGED a
         CROSS JOIN BOUNDS b
WHERE a.MONTH_START >= b.YEAR_START
ORDER BY a.MONTH_START
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_A_11
-- Sales wants every country's revenue in `:year`, `0` for a country without a sale, and its share of its region's
-- revenue, NULL where the region sold nothing.
WITH COUNTRY_REVENUE AS (SELECT cu.COUNTRY_ID, SUM(s.AMOUNT_SOLD) AS REVENUE
                         FROM SALES s
                                  JOIN CUSTOMERS cu ON cu.CUST_ID = s.CUST_ID
                                  JOIN TIMES t ON t.TIME_ID = s.TIME_ID
                         WHERE t.CALENDAR_YEAR = :year
                         GROUP BY cu.COUNTRY_ID),
     SHARES AS (SELECT co.COUNTRY_REGION,
                       co.COUNTRY_REGION_ID,
                       co.COUNTRY_ID,
                       co.COUNTRY_NAME,
                       COALESCE(r.REVENUE, 0)                                                                    AS REVENUE,
                       100 * COALESCE(r.REVENUE, 0)
                           / NULLIF(SUM(COALESCE(r.REVENUE, 0)) OVER (PARTITION BY co.COUNTRY_REGION_ID), 0) AS SHARE_PCT
                FROM COUNTRIES co
                         LEFT JOIN COUNTRY_REVENUE r ON r.COUNTRY_ID = co.COUNTRY_ID)
SELECT COUNTRY_REGION, COUNTRY_NAME, REVENUE, ROUND(SHARE_PCT, 2) AS SHARE_PCT
FROM SHARES
ORDER BY COUNTRY_REGION, COUNTRY_REGION_ID, SHARE_PCT DESC NULLS LAST, COUNTRY_ID
;


-- ------------------------------------------------------------------------------------------------------ SH_SCHEMA_A_12
-- The schema enforces neither the five keys of a sales line nor the four of a cost record, and a join to `COSTS`
-- multiplies the revenue if one repeats. List the keys of `SALES` and of `COSTS` found more than once; the check passes
-- when nothing comes back.
SELECT 'SALES' AS SOURCE_TABLE, PROD_ID, TIME_ID, CHANNEL_ID, PROMO_ID, CUST_ID, COUNT(*) AS RECORDS
FROM SALES
GROUP BY PROD_ID, TIME_ID, CHANNEL_ID, PROMO_ID, CUST_ID
HAVING COUNT(*) > 1
UNION ALL
SELECT 'COSTS', PROD_ID, TIME_ID, CHANNEL_ID, PROMO_ID, NULL, COUNT(*)
FROM COSTS
GROUP BY PROD_ID, TIME_ID, CHANNEL_ID, PROMO_ID
HAVING COUNT(*) > 1
ORDER BY 1, 2, 3, 4, 5, 6
;
