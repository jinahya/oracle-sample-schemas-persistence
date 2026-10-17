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
-- ------------------------------------------------------------------------------------------------------------- PROD_ID
SELECT MIN(PROD_ID), MAX(PROD_ID)
FROM SALES
;
-- 13, 148

-- ------------------------------------------------------------------------------------------------------------- CUST_ID
SELECT MIN(CUST_ID), MAX(CUST_ID)
FROM SALES
;
-- 2, 101000

-- ------------------------------------------------------------------------------------------------------------- TIME_ID
SELECT MIN(TIME_ID), MAX(TIME_ID)
FROM SALES
;
-- 2019-01-01, 2022-12-31

-- ---------------------------------------------------------------------------------------------------------- CHANNEL_ID
SELECT MIN(CHANNEL_ID), MAX(CHANNEL_ID)
FROM SALES
;
-- 2, 9

-- ------------------------------------------------------------------------------------------------------------ PROMO_ID
SELECT MIN(PROMO_ID), MAX(PROMO_ID)
FROM SALES
;
-- 33, 999

-- ------------------------------------------------------------------------------------------------------- QUANTITY_SOLD
SELECT MIN(QUANTITY_SOLD), MAX(QUANTITY_SOLD)
FROM SALES
;
-- 1, 1

-- --------------------------------------------------------------------------------------------------------- AMOUNT_SOLD
SELECT MIN(AMOUNT_SOLD), MAX(AMOUNT_SOLD)
FROM SALES
;
-- 6.4, 1782.72

-- ------------------------------------------------------------------------------------------------------ CANDIDATE_KEYS
-- the narrowest NOT NULL and unique column(s) this table could map as a JPA @Id -- it declares none.
-- results: ../../../doc/IDs.asciidoc

-- how many rows does the table hold, and how many non-null / distinct values does each column hold?
-- a single-column candidate is one whose NOT_NULLS and DISTINCTS both equal ROW_COUNT.
SELECT COLUMN_NAME, ROW_COUNT, NOT_NULLS, DISTINCTS
FROM (SELECT COUNT(1)                      ROW_COUNT,
             COUNT(PROD_ID)                NN_PROD_ID,
             COUNT(DISTINCT PROD_ID)       DC_PROD_ID,
             COUNT(CUST_ID)                NN_CUST_ID,
             COUNT(DISTINCT CUST_ID)       DC_CUST_ID,
             COUNT(TIME_ID)                NN_TIME_ID,
             COUNT(DISTINCT TIME_ID)       DC_TIME_ID,
             COUNT(CHANNEL_ID)             NN_CHANNEL_ID,
             COUNT(DISTINCT CHANNEL_ID)    DC_CHANNEL_ID,
             COUNT(PROMO_ID)               NN_PROMO_ID,
             COUNT(DISTINCT PROMO_ID)      DC_PROMO_ID,
             COUNT(QUANTITY_SOLD)          NN_QUANTITY_SOLD,
             COUNT(DISTINCT QUANTITY_SOLD) DC_QUANTITY_SOLD,
             COUNT(AMOUNT_SOLD)            NN_AMOUNT_SOLD,
             COUNT(DISTINCT AMOUNT_SOLD)   DC_AMOUNT_SOLD
      FROM SALES)
    UNPIVOT ((NOT_NULLS, DISTINCTS) FOR COLUMN_NAME IN (
        (NN_PROD_ID, DC_PROD_ID ) AS 'PROD_ID',
        (NN_CUST_ID, DC_CUST_ID ) AS 'CUST_ID',
        (NN_TIME_ID, DC_TIME_ID ) AS 'TIME_ID',
        (NN_CHANNEL_ID, DC_CHANNEL_ID ) AS 'CHANNEL_ID',
        (NN_PROMO_ID, DC_PROMO_ID ) AS 'PROMO_ID',
        (NN_QUANTITY_SOLD, DC_QUANTITY_SOLD) AS 'QUANTITY_SOLD',
        (NN_AMOUNT_SOLD, DC_AMOUNT_SOLD ) AS 'AMOUNT_SOLD'
        ))
ORDER BY DISTINCTS DESC, COLUMN_NAME
;

-- the table declares no primary key, and no single column is unique; no rows here means
-- the five dimension columns identify a row in the shipped data.
SELECT PROD_ID, CUST_ID, TIME_ID, CHANNEL_ID, PROMO_ID, COUNT(1) c
FROM SALES
GROUP BY PROD_ID, CUST_ID, TIME_ID, CHANNEL_ID, PROMO_ID
HAVING COUNT(1) > 1
;
