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