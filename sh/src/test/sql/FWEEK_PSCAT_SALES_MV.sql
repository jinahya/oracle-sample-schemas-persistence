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
-- ----------------------------------------------------------------------------------------------------- WEEK_ENDING_DAY

-- ---------------------------------------------------------------------------------------------------- PROD_SUBCATEGORY

-- ------------------------------------------------------------------------------------------------------------- DOLLARS

-- ---------------------------------------------------------------------------------------------------------- CHANNEL_ID

-- ------------------------------------------------------------------------------------------------------------ PROMO_ID

-- ------------------------------------------------------------------------------------------------------ CANDIDATE_KEYS
-- the narrowest NOT NULL and unique column(s) this view could map as a JPA @Id.
-- results: ../../../doc/IDs.asciidoc

-- how many rows does the view hold, and how many non-null / distinct values does each column hold?
-- a single-column candidate is one whose NOT_NULLS and DISTINCTS both equal ROW_COUNT.
SELECT COLUMN_NAME, ROW_COUNT, NOT_NULLS, DISTINCTS
FROM (SELECT COUNT(1)                         ROW_COUNT,
             COUNT(WEEK_ENDING_DAY)           NN_WEEK_ENDING_DAY,
             COUNT(DISTINCT WEEK_ENDING_DAY)  DC_WEEK_ENDING_DAY,
             COUNT(PROD_SUBCATEGORY)          NN_PROD_SUBCATEGORY,
             COUNT(DISTINCT PROD_SUBCATEGORY) DC_PROD_SUBCATEGORY,
             COUNT(DOLLARS)                   NN_DOLLARS,
             COUNT(DISTINCT DOLLARS)          DC_DOLLARS,
             COUNT(CHANNEL_ID)                NN_CHANNEL_ID,
             COUNT(DISTINCT CHANNEL_ID)       DC_CHANNEL_ID,
             COUNT(PROMO_ID)                  NN_PROMO_ID,
             COUNT(DISTINCT PROMO_ID)         DC_PROMO_ID
      FROM FWEEK_PSCAT_SALES_MV)
    UNPIVOT ((NOT_NULLS, DISTINCTS) FOR COLUMN_NAME IN (
        (NN_WEEK_ENDING_DAY, DC_WEEK_ENDING_DAY ) AS 'WEEK_ENDING_DAY',
        (NN_PROD_SUBCATEGORY, DC_PROD_SUBCATEGORY) AS 'PROD_SUBCATEGORY',
        (NN_DOLLARS, DC_DOLLARS ) AS 'DOLLARS',
        (NN_CHANNEL_ID, DC_CHANNEL_ID ) AS 'CHANNEL_ID',
        (NN_PROMO_ID, DC_PROMO_ID ) AS 'PROMO_ID'
        ))
ORDER BY DISTINCTS DESC, COLUMN_NAME
;

-- no rows: the four columns are the GROUP BY key of the materialized query; DOLLARS is its aggregate.
SELECT WEEK_ENDING_DAY, PROD_SUBCATEGORY, CHANNEL_ID, PROMO_ID, COUNT(1) c
FROM FWEEK_PSCAT_SALES_MV
GROUP BY WEEK_ENDING_DAY, PROD_SUBCATEGORY, CHANNEL_ID, PROMO_ID
HAVING COUNT(1) > 1
;
