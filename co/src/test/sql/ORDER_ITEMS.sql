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