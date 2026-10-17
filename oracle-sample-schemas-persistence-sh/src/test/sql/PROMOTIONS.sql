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
-- ------------------------------------------------------------------------------------------------------------ PROMO_ID
SELECT MIN(PROMO_ID), MAX(PROMO_ID)
FROM PROMOTIONS
;
-- 33, 999

-- ---------------------------------------------------------------------------------------------------------- PROMO_NAME
SELECT MIN(LENGTH(TRIM(PROMO_NAME))), MAX(LENGTH(TRIM(PROMO_NAME)))
FROM PROMOTIONS
;
-- 12, 27

SELECT MIN(PROMO_NAME), MAX(PROMO_NAME)
FROM PROMOTIONS
;
-- NO PROMOTION #, radio promotion #32-73

-- --------------------------------------------------------------------------------------------------- PROMO_SUBCATEGORY
SELECT MIN(LENGTH(TRIM(PROMO_SUBCATEGORY))), MAX(LENGTH(TRIM(PROMO_SUBCATEGORY)))
FROM PROMOTIONS
;
-- 2, 27

SELECT MIN(PROMO_SUBCATEGORY), MAX(PROMO_SUBCATEGORY)
FROM PROMOTIONS
;
-- NO PROMOTION, radio program sponsorship

-- the distinct PROMO_SUBCATEGORY / PROMO_SUBCATEGORY_ID pairs: 22 of them, and every name has one id
SELECT DISTINCT PROMO_SUBCATEGORY_ID, PROMO_SUBCATEGORY
FROM PROMOTIONS
ORDER BY PROMO_SUBCATEGORY_ID ASC
;
--  11  NO PROMOTION
--  12  TV commercial
--  13  TV program sponsorship
--  14  ad
--  15  ad magazine
--  16  ad news
--  17  billboard
--  18  coupon magazine
--  19  coupon news
--  20  downtown billboard
--  21  freeway billboard
--  22  general flyer
--  23  hospital flyer
--  24  household flyer
--  25  loyal customer discount
--  26  manufacture rebate magazine
--  27  manufacture rebate news
--  28  newspaper
--  29  online discount
--  30  promotion movie
--  31  radio commercial
--  32  radio program sponsorship

-- ------------------------------------------------------------------------------------------------ PROMO_SUBCATEGORY_ID
SELECT MIN(PROMO_SUBCATEGORY_ID), MAX(PROMO_SUBCATEGORY_ID)
FROM PROMOTIONS
;
-- 11, 32

-- how many significant digits, and how many of them after the point, does the column actually use? a plain NUMBER
-- declares neither; TM9 is the shortest fixed-notation text of the value, and its digits are counted with and
-- without the point.
SELECT MIN(LENGTH(REPLACE(T, '.'))) MIN_PRECISION,
       MAX(LENGTH(REPLACE(T, '.'))) MAX_PRECISION,
       MIN(CASE WHEN INSTR(T, '.') = 0 THEN 0 ELSE LENGTH(T) - INSTR(T, '.') END) MIN_SCALE,
       MAX(CASE WHEN INSTR(T, '.') = 0 THEN 0 ELSE LENGTH(T) - INSTR(T, '.') END) MAX_SCALE
FROM (SELECT TO_CHAR(ABS(PROMO_SUBCATEGORY_ID), 'TM9') T FROM PROMOTIONS)
;
-- precision 2..2, scale 0..0

-- ------------------------------------------------------------------------------------------------------ PROMO_CATEGORY
SELECT MIN(LENGTH(TRIM(PROMO_CATEGORY))), MAX(LENGTH(TRIM(PROMO_CATEGORY)))
FROM PROMOTIONS
;
-- 2, 12

SELECT MIN(PROMO_CATEGORY), MAX(PROMO_CATEGORY)
FROM PROMOTIONS
;
-- NO PROMOTION, radio

-- the distinct PROMO_CATEGORY / PROMO_CATEGORY_ID pairs: 9 of them, and every name has one id
SELECT DISTINCT PROMO_CATEGORY_ID, PROMO_CATEGORY
FROM PROMOTIONS
ORDER BY PROMO_CATEGORY_ID ASC
;
--   2  NO PROMOTION
--   3  TV
--   4  ad news
--   5  flyer
--   6  internet
--   7  magazine
--   8  newspaper
--   9  post
--  10  radio

-- --------------------------------------------------------------------------------------------------- PROMO_CATEGORY_ID
SELECT MIN(PROMO_CATEGORY_ID), MAX(PROMO_CATEGORY_ID)
FROM PROMOTIONS
;
-- 2, 10

-- how many significant digits, and how many of them after the point, does the column actually use? a plain NUMBER
-- declares neither; TM9 is the shortest fixed-notation text of the value, and its digits are counted with and
-- without the point.
SELECT MIN(LENGTH(REPLACE(T, '.'))) MIN_PRECISION,
       MAX(LENGTH(REPLACE(T, '.'))) MAX_PRECISION,
       MIN(CASE WHEN INSTR(T, '.') = 0 THEN 0 ELSE LENGTH(T) - INSTR(T, '.') END) MIN_SCALE,
       MAX(CASE WHEN INSTR(T, '.') = 0 THEN 0 ELSE LENGTH(T) - INSTR(T, '.') END) MAX_SCALE
FROM (SELECT TO_CHAR(ABS(PROMO_CATEGORY_ID), 'TM9') T FROM PROMOTIONS)
;
-- precision 1..2, scale 0..0

-- ---------------------------------------------------------------------------------------------------------- PROMO_COST
SELECT MIN(PROMO_COST), MAX(PROMO_COST)
FROM PROMOTIONS
;
-- 0, 100000

-- how many significant digits, and how many of them after the point, does the column actually use? a plain NUMBER
-- declares neither; TM9 is the shortest fixed-notation text of the value, and its digits are counted with and
-- without the point.
SELECT MIN(LENGTH(REPLACE(T, '.'))) MIN_PRECISION,
       MAX(LENGTH(REPLACE(T, '.'))) MAX_PRECISION,
       MIN(CASE WHEN INSTR(T, '.') = 0 THEN 0 ELSE LENGTH(T) - INSTR(T, '.') END) MIN_SCALE,
       MAX(CASE WHEN INSTR(T, '.') = 0 THEN 0 ELSE LENGTH(T) - INSTR(T, '.') END) MAX_SCALE
FROM (SELECT TO_CHAR(ABS(PROMO_COST), 'TM9') T FROM PROMOTIONS)
;
-- precision 1..6, scale 0..0

-- ---------------------------------------------------------------------------------------------------- PROMO_BEGIN_DATE
SELECT MIN(PROMO_BEGIN_DATE), MAX(PROMO_BEGIN_DATE)
FROM PROMOTIONS
;
-- 2019-06-01, 9999-01-01

-- ------------------------------------------------------------------------------------------------------ PROMO_END_DATE
SELECT MIN(PROMO_END_DATE), MAX(PROMO_END_DATE)
FROM PROMOTIONS
;
-- 2019-08-01, 9999-01-01

-- how the two dates stand to each other: a promotion which begins no earlier than it ends, and one which begins no
-- later; the two counts overlap on the rows where the dates are equal, and sum to the row count plus that overlap.
SELECT COUNT(1)
FROM PROMOTIONS
WHERE PROMO_BEGIN_DATE >= PROMO_END_DATE
;
-- 1

SELECT COUNT(1)
FROM PROMOTIONS
WHERE PROMO_BEGIN_DATE <= PROMO_END_DATE
;
-- 503

-- --------------------------------------------------------------------------------------------------------- PROMO_TOTAL
SELECT MIN(LENGTH(TRIM(PROMO_TOTAL))), MAX(LENGTH(TRIM(PROMO_TOTAL)))
FROM PROMOTIONS
;
-- 15, 15

SELECT MIN(PROMO_TOTAL), MAX(PROMO_TOTAL)
FROM PROMOTIONS
;
-- Promotion total, Promotion total

-- the distinct PROMO_TOTAL / PROMO_TOTAL_ID pairs: 1 of them, and every name has one id
SELECT DISTINCT PROMO_TOTAL_ID, PROMO_TOTAL
FROM PROMOTIONS
ORDER BY PROMO_TOTAL_ID ASC
;
--   1  Promotion total

-- ------------------------------------------------------------------------------------------------------ PROMO_TOTAL_ID
SELECT MIN(PROMO_TOTAL_ID), MAX(PROMO_TOTAL_ID)
FROM PROMOTIONS
;
-- 1, 1

-- how many significant digits, and how many of them after the point, does the column actually use? a plain NUMBER
-- declares neither; TM9 is the shortest fixed-notation text of the value, and its digits are counted with and
-- without the point.
SELECT MIN(LENGTH(REPLACE(T, '.'))) MIN_PRECISION,
       MAX(LENGTH(REPLACE(T, '.'))) MAX_PRECISION,
       MIN(CASE WHEN INSTR(T, '.') = 0 THEN 0 ELSE LENGTH(T) - INSTR(T, '.') END) MIN_SCALE,
       MAX(CASE WHEN INSTR(T, '.') = 0 THEN 0 ELSE LENGTH(T) - INSTR(T, '.') END) MAX_SCALE
FROM (SELECT TO_CHAR(ABS(PROMO_TOTAL_ID), 'TM9') T FROM PROMOTIONS)
;
-- precision 1..1, scale 0..0
