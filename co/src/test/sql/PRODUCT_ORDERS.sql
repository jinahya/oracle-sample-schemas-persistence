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
-- -------------------------------------------------------------------------------------------------------- PRODUCT_NAME

-- -------------------------------------------------------------------------------------------------------- ORDER_STATUS

-- --------------------------------------------------------------------------------------------------------- TOTAL_SALES

-- --------------------------------------------------------------------------------------------------------- ORDER_COUNT

-- ------------------------------------------------------------------------------------------------------ CANDIDATE_KEYS
-- the narrowest NOT NULL and unique column(s) this view could map as a JPA @Id.
-- results: ../../../doc/IDs.asciidoc

-- how many rows does the view hold, and how many non-null / distinct values does each column hold?
-- a single-column candidate is one whose NOT_NULLS and DISTINCTS both equal ROW_COUNT.
SELECT COLUMN_NAME, ROW_COUNT, NOT_NULLS, DISTINCTS
FROM (SELECT COUNT(1)                     ROW_COUNT,
             COUNT(PRODUCT_NAME)          NN_PRODUCT_NAME,
             COUNT(DISTINCT PRODUCT_NAME) DC_PRODUCT_NAME,
             COUNT(ORDER_STATUS)          NN_ORDER_STATUS,
             COUNT(DISTINCT ORDER_STATUS) DC_ORDER_STATUS,
             COUNT(TOTAL_SALES)           NN_TOTAL_SALES,
             COUNT(DISTINCT TOTAL_SALES)  DC_TOTAL_SALES,
             COUNT(ORDER_COUNT)           NN_ORDER_COUNT,
             COUNT(DISTINCT ORDER_COUNT)  DC_ORDER_COUNT
      FROM PRODUCT_ORDERS)
    UNPIVOT ((NOT_NULLS, DISTINCTS) FOR COLUMN_NAME IN (
        (NN_PRODUCT_NAME, DC_PRODUCT_NAME) AS 'PRODUCT_NAME',
        (NN_ORDER_STATUS, DC_ORDER_STATUS) AS 'ORDER_STATUS',
        (NN_TOTAL_SALES, DC_TOTAL_SALES ) AS 'TOTAL_SALES',
        (NN_ORDER_COUNT, DC_ORDER_COUNT ) AS 'ORDER_COUNT'
        ))
ORDER BY DISTINCTS DESC, COLUMN_NAME
;

-- no single column is unique; no rows here: the GROUP BY pair identifies a row.
SELECT PRODUCT_NAME, ORDER_STATUS, COUNT(1) c
FROM PRODUCT_ORDERS
GROUP BY PRODUCT_NAME, ORDER_STATUS
HAVING COUNT(1) > 1
;
