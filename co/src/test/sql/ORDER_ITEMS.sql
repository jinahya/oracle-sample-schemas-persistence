-- #%L
-- co
-- %%
-- Copyright (C) 2024 - 2025 Jinahya, Inc.
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

-- -------------------------------------------------------------------------------------------------------- LINE_ITEM_ID

-- ---------------------------------------------------------------------------------------------------------- PRODUCT_ID

-- ---------------------------------------------------------------------------------------------------------- UNIT_PRICE
SELECT COUNT(1)
FROM ORDER_ITEMS
WHERE UNIT_PRICE <= 0
;

SELECT COUNT(1)
FROM ORDER_ITEMS oi
         JOIN PRODUCTS p ON oi.PRODUCT_ID = p.PRODUCT_ID
WHERE oi.UNIT_PRICE <> p.UNIT_PRICE
;

SELECT COUNT(1)
FROM ORDER_ITEMS oi
         JOIN PRODUCTS p ON oi.PRODUCT_ID = p.PRODUCT_ID
WHERE oi.UNIT_PRICE = p.UNIT_PRICE
;

SELECT oi.PRODUCT_ID, oi.UNIT_PRICE, p.PRODUCT_ID, p.UNIT_PRICE
FROM ORDER_ITEMS oi
         JOIN PRODUCTS p ON oi.PRODUCT_ID = p.PRODUCT_ID
WHERE oi.UNIT_PRICE <> p.UNIT_PRICE
;

-- ------------------------------------------------------------------------------------------------------------ QUANTITY
SELECT COUNT(1)
FROM ORDER_ITEMS
WHERE QUANTITY <= 0
;
-- --------------------------------------------------------------------------------------------------------- SHIPMENT_ID
SELECT ORDER_ID, LINE_ITEM_ID, SHIPMENT_ID, COUNT(1)
FROM ORDER_ITEMS oi
GROUP BY ORDER_ID, LINE_ITEM_ID, SHIPMENT_ID
HAVING COUNT(1) > 1
ORDER BY ORDER_ID, SHIPMENT_ID
;

SELECT oi.SHIPMENT_ID, s.SHIPMENT_ID, s.SHIPMENT_STATUS
FROM ORDER_ITEMS oi
         LEFT OUTER JOIN SHIPMENTS s ON oi.SHIPMENT_ID = s.SHIPMENT_ID
WHERE oi.SHIPMENT_ID IS NOT NULL
;

SELECT *
FROM (SELECT oi.SHIPMENT_ID oisi, s.SHIPMENT_ID ssi, s.SHIPMENT_STATUS sss
      FROM ORDER_ITEMS oi
               LEFT OUTER JOIN SHIPMENTS s ON oi.SHIPMENT_ID = s.SHIPMENT_ID
      WHERE oi.SHIPMENT_ID IS NOT NULL) sub
;

-- ORDERS.CUSTOMER_ID <> SHIPMENTS.CUSTOMER_ID
SELECT oi.SHIPMENT_ID, o.ORDER_ID, o.CUSTOMER_ID, s.SHIPMENT_ID, s.SHIPMENT_STATUS, s.CUSTOMER_ID
FROM ORDER_ITEMS oi
         LEFT OUTER JOIN ORDERS o ON oi.ORDER_ID = o.ORDER_ID
         LEFT OUTER JOIN SHIPMENTS s ON oi.SHIPMENT_ID = s.SHIPMENT_ID
WHERE o.CUSTOMER_ID <> s.CUSTOMER_ID
;

-- ORDERS.STORE_ID <> SHIPMENTS.STORE_ID
SELECT oi.SHIPMENT_ID, o.ORDER_ID, o.STORE_ID, s.SHIPMENT_ID, s.SHIPMENT_STATUS, s.STORE_ID
FROM ORDER_ITEMS oi
         LEFT OUTER JOIN ORDERS o ON oi.ORDER_ID = o.ORDER_ID
         LEFT OUTER JOIN SHIPMENTS s ON oi.SHIPMENT_ID = s.SHIPMENT_ID
WHERE o.STORE_ID <> s.STORE_ID
;
