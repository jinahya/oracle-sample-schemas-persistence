--
-- -------------------------------------------------------------------------------------------------------- INVENTORY_ID


-- ------------------------------------------------------------------------------------------------------------ STORE_ID


-- ---------------------------------------------------------------------------------------------------------- PRODUCT_ID


-- --------------------------------------------------------------------------------------------------- PRODUCT_INVENTORY
SELECT *
FROM INVENTORY
WHERE PRODUCT_INVENTORY < 0
;
SELECT STORE_ID, PRODUCT_ID, MAX(PRODUCT_INVENTORY)
FROM INVENTORY
GROUP BY STORE_ID, PRODUCT_ID
;

SELECT spx.*, s.STORE_NAME, p.PRODUCT_NAME
FROM (SELECT STORE_ID, PRODUCT_ID, MAX(PRODUCT_INVENTORY)
      FROM INVENTORY
      GROUP BY STORE_ID, PRODUCT_ID) spx
         JOIN STORES s ON spx.STORE_ID = s.STORE_ID
         JOIN CO.PRODUCTS p ON spx.PRODUCT_ID = p.PRODUCT_ID
;

SELECT spx.*, s.STORE_NAME, p.PRODUCT_NAME
FROM (SELECT STORE_ID, PRODUCT_ID, PRODUCT_INVENTORY
      FROM INVENTORY
      WHERE PRODUCT_INVENTORY = 0) spx
         JOIN STORES s ON spx.STORE_ID = s.STORE_ID
         JOIN CO.PRODUCTS p ON spx.PRODUCT_ID = p.PRODUCT_ID
;
