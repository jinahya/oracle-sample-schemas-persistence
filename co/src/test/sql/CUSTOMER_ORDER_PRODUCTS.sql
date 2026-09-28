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
-- ------------------------------------------------------------------------------------------------------------ ORDER_ID
SELECT ORDER_ID, COUNT(1)
FROM CUSTOMER_ORDER_PRODUCTS
GROUP BY ORDER_ID
HAVING COUNT(1) > 1
;


-- ----------------------------------------------------------------------------------------------------------- ORDER_TMS

-- -------------------------------------------------------------------------------------------------------- ORDER_STATUS

-- --------------------------------------------------------------------------------------------------------- CUSTOMER_ID

-- ------------------------------------------------------------------------------------------------------- EMAIL_ADDRESS

-- ----------------------------------------------------------------------------------------------------------- FULL_NAME

-- --------------------------------------------------------------------------------------------------------- ORDER_TOTAL

-- --------------------------------------------------------------------------------------------------------------- ITEMS

-- ------------------------------------------------------------------------------------------------------ CANDIDATE_KEYS
-- the narrowest NOT NULL and unique column(s) this view could map as a JPA @Id.
-- results: ../../../doc/IDs.asciidoc

-- how many rows does the view hold, and how many non-null / distinct values does each column hold?
-- a single-column candidate is one whose NOT_NULLS and DISTINCTS both equal ROW_COUNT.
SELECT COLUMN_NAME, ROW_COUNT, NOT_NULLS, DISTINCTS
FROM (SELECT COUNT(1) ROW_COUNT,
             COUNT(ORDER_ID)      NN_ORDER_ID,      COUNT(DISTINCT ORDER_ID)      DC_ORDER_ID,
             COUNT(ORDER_TMS)     NN_ORDER_TMS,     COUNT(DISTINCT ORDER_TMS)     DC_ORDER_TMS,
             COUNT(ORDER_STATUS)  NN_ORDER_STATUS,  COUNT(DISTINCT ORDER_STATUS)  DC_ORDER_STATUS,
             COUNT(CUSTOMER_ID)   NN_CUSTOMER_ID,   COUNT(DISTINCT CUSTOMER_ID)   DC_CUSTOMER_ID,
             COUNT(EMAIL_ADDRESS) NN_EMAIL_ADDRESS, COUNT(DISTINCT EMAIL_ADDRESS) DC_EMAIL_ADDRESS,
             COUNT(FULL_NAME)     NN_FULL_NAME,     COUNT(DISTINCT FULL_NAME)     DC_FULL_NAME,
             COUNT(ORDER_TOTAL)   NN_ORDER_TOTAL,   COUNT(DISTINCT ORDER_TOTAL)   DC_ORDER_TOTAL,
             COUNT(ITEMS)         NN_ITEMS,         COUNT(DISTINCT ITEMS)         DC_ITEMS
      FROM CUSTOMER_ORDER_PRODUCTS)
UNPIVOT ((NOT_NULLS, DISTINCTS) FOR COLUMN_NAME IN (
    (NN_ORDER_ID,      DC_ORDER_ID     ) AS 'ORDER_ID',
    (NN_ORDER_TMS,     DC_ORDER_TMS    ) AS 'ORDER_TMS',
    (NN_ORDER_STATUS,  DC_ORDER_STATUS ) AS 'ORDER_STATUS',
    (NN_CUSTOMER_ID,   DC_CUSTOMER_ID  ) AS 'CUSTOMER_ID',
    (NN_EMAIL_ADDRESS, DC_EMAIL_ADDRESS) AS 'EMAIL_ADDRESS',
    (NN_FULL_NAME,     DC_FULL_NAME    ) AS 'FULL_NAME',
    (NN_ORDER_TOTAL,   DC_ORDER_TOTAL  ) AS 'ORDER_TOTAL',
    (NN_ITEMS,         DC_ITEMS        ) AS 'ITEMS'
))
ORDER BY DISTINCTS DESC, COLUMN_NAME
;

-- no rows: ORDER_ID -- the GROUP BY key this view aggregates on -- identifies a row on its own.
SELECT ORDER_ID, COUNT(1) c
FROM CUSTOMER_ORDER_PRODUCTS
GROUP BY ORDER_ID
HAVING COUNT(1) > 1
;
