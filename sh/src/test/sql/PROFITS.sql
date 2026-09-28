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
-- ---------------------------------------------------------------------------------------------------------- CHANNEL_ID

-- ------------------------------------------------------------------------------------------------------------- CUST_ID

-- ------------------------------------------------------------------------------------------------------------- PROD_ID

-- ------------------------------------------------------------------------------------------------------------ PROMO_ID

-- ------------------------------------------------------------------------------------------------------------- TIME_ID

-- ----------------------------------------------------------------------------------------------------------- UNIT_COST

-- ---------------------------------------------------------------------------------------------------------- UNIT_PRICE

-- --------------------------------------------------------------------------------------------------------- AMOUNT_SOLD

-- ------------------------------------------------------------------------------------------------------- QUANTITY_SOLD

-- ---------------------------------------------------------------------------------------------------------- TOTAL_COST

-- ------------------------------------------------------------------------------------------------------ CANDIDATE_KEYS
-- the narrowest NOT NULL and unique column(s) this view could map as a JPA @Id.
-- results: ../../../doc/IDs.asciidoc

-- how many rows does the view hold, and how many non-null / distinct values does each column hold?
-- a single-column candidate is one whose NOT_NULLS and DISTINCTS both equal ROW_COUNT.
SELECT COLUMN_NAME, ROW_COUNT, NOT_NULLS, DISTINCTS
FROM (SELECT COUNT(1) ROW_COUNT,
             COUNT(CHANNEL_ID)    NN_CHANNEL_ID,    COUNT(DISTINCT CHANNEL_ID)    DC_CHANNEL_ID,
             COUNT(CUST_ID)       NN_CUST_ID,       COUNT(DISTINCT CUST_ID)       DC_CUST_ID,
             COUNT(PROD_ID)       NN_PROD_ID,       COUNT(DISTINCT PROD_ID)       DC_PROD_ID,
             COUNT(PROMO_ID)      NN_PROMO_ID,      COUNT(DISTINCT PROMO_ID)      DC_PROMO_ID,
             COUNT(TIME_ID)       NN_TIME_ID,       COUNT(DISTINCT TIME_ID)       DC_TIME_ID,
             COUNT(UNIT_COST)     NN_UNIT_COST,     COUNT(DISTINCT UNIT_COST)     DC_UNIT_COST,
             COUNT(UNIT_PRICE)    NN_UNIT_PRICE,    COUNT(DISTINCT UNIT_PRICE)    DC_UNIT_PRICE,
             COUNT(AMOUNT_SOLD)   NN_AMOUNT_SOLD,   COUNT(DISTINCT AMOUNT_SOLD)   DC_AMOUNT_SOLD,
             COUNT(QUANTITY_SOLD) NN_QUANTITY_SOLD, COUNT(DISTINCT QUANTITY_SOLD) DC_QUANTITY_SOLD,
             COUNT(TOTAL_COST)    NN_TOTAL_COST,    COUNT(DISTINCT TOTAL_COST)    DC_TOTAL_COST
      FROM PROFITS)
UNPIVOT ((NOT_NULLS, DISTINCTS) FOR COLUMN_NAME IN (
    (NN_CHANNEL_ID,    DC_CHANNEL_ID   ) AS 'CHANNEL_ID',
    (NN_CUST_ID,       DC_CUST_ID      ) AS 'CUST_ID',
    (NN_PROD_ID,       DC_PROD_ID      ) AS 'PROD_ID',
    (NN_PROMO_ID,      DC_PROMO_ID     ) AS 'PROMO_ID',
    (NN_TIME_ID,       DC_TIME_ID      ) AS 'TIME_ID',
    (NN_UNIT_COST,     DC_UNIT_COST    ) AS 'UNIT_COST',
    (NN_UNIT_PRICE,    DC_UNIT_PRICE   ) AS 'UNIT_PRICE',
    (NN_AMOUNT_SOLD,   DC_AMOUNT_SOLD  ) AS 'AMOUNT_SOLD',
    (NN_QUANTITY_SOLD, DC_QUANTITY_SOLD) AS 'QUANTITY_SOLD',
    (NN_TOTAL_COST,    DC_TOTAL_COST   ) AS 'TOTAL_COST'
))
ORDER BY DISTINCTS DESC, COLUMN_NAME
;

-- SALES' grain, carried through the join to COSTS: no rows, but SALES declares no primary key,
-- so this holds for the shipped data rather than by constraint.
SELECT CHANNEL_ID, CUST_ID, PROD_ID, PROMO_ID, TIME_ID, COUNT(1) c
FROM PROFITS
GROUP BY CHANNEL_ID, CUST_ID, PROD_ID, PROMO_ID, TIME_ID
HAVING COUNT(1) > 1
;
