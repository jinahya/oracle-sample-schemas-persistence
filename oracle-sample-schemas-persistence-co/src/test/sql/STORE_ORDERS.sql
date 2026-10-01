-- #%L
-- co
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
-- --------------------------------------------------------------------------------------------------------------- TOTAL

-- ---------------------------------------------------------------------------------------------------------- STORE_NAME

-- ------------------------------------------------------------------------------------------------------------- ADDRESS

-- ------------------------------------------------------------------------------------------------------------ LATITUDE

-- ----------------------------------------------------------------------------------------------------------- LONGITUDE

-- -------------------------------------------------------------------------------------------------------- ORDER_STATUS

-- --------------------------------------------------------------------------------------------------------- ORDER_COUNT

-- --------------------------------------------------------------------------------------------------------- TOTAL_SALES

-- ------------------------------------------------------------------------------------------------------ CANDIDATE_KEYS
-- the narrowest NOT NULL and unique column(s) this view could map as a JPA @Id.
-- results: ../../../doc/IDs.asciidoc

-- how many rows does the view hold, and how many non-null / distinct values does each column hold?
-- a single-column candidate is one whose NOT_NULLS and DISTINCTS both equal ROW_COUNT.
SELECT COLUMN_NAME, ROW_COUNT, NOT_NULLS, DISTINCTS
FROM (SELECT COUNT(1)                     ROW_COUNT,
             COUNT(TOTAL)                 NN_TOTAL,
             COUNT(DISTINCT TOTAL)        DC_TOTAL,
             COUNT(STORE_NAME)            NN_STORE_NAME,
             COUNT(DISTINCT STORE_NAME)   DC_STORE_NAME,
             COUNT(ADDRESS)               NN_ADDRESS,
             COUNT(DISTINCT ADDRESS)      DC_ADDRESS,
             COUNT(LATITUDE)              NN_LATITUDE,
             COUNT(DISTINCT LATITUDE)     DC_LATITUDE,
             COUNT(LONGITUDE)             NN_LONGITUDE,
             COUNT(DISTINCT LONGITUDE)    DC_LONGITUDE,
             COUNT(ORDER_STATUS)          NN_ORDER_STATUS,
             COUNT(DISTINCT ORDER_STATUS) DC_ORDER_STATUS,
             COUNT(ORDER_COUNT)           NN_ORDER_COUNT,
             COUNT(DISTINCT ORDER_COUNT)  DC_ORDER_COUNT,
             COUNT(TOTAL_SALES)           NN_TOTAL_SALES,
             COUNT(DISTINCT TOTAL_SALES)  DC_TOTAL_SALES
      FROM STORE_ORDERS)
    UNPIVOT ((NOT_NULLS, DISTINCTS) FOR COLUMN_NAME IN (
        (NN_TOTAL, DC_TOTAL ) AS 'TOTAL',
        (NN_STORE_NAME, DC_STORE_NAME ) AS 'STORE_NAME',
        (NN_ADDRESS, DC_ADDRESS ) AS 'ADDRESS',
        (NN_LATITUDE, DC_LATITUDE ) AS 'LATITUDE',
        (NN_LONGITUDE, DC_LONGITUDE ) AS 'LONGITUDE',
        (NN_ORDER_STATUS, DC_ORDER_STATUS) AS 'ORDER_STATUS',
        (NN_ORDER_COUNT, DC_ORDER_COUNT ) AS 'ORDER_COUNT',
        (NN_TOTAL_SALES, DC_TOTAL_SALES ) AS 'TOTAL_SALES'
        ))
ORDER BY DISTINCTS DESC, COLUMN_NAME
;

-- the GROUPING SETS key, so no rows here -- but both columns are NULL in the subtotal
-- and grand-total rows, and an @Id may not be null.
SELECT STORE_NAME, ORDER_STATUS, COUNT(1) c
FROM STORE_ORDERS
GROUP BY STORE_NAME, ORDER_STATUS
HAVING COUNT(1) > 1
;
