--
SELECT *
FROM PRODUCTS
;
-- ---------------------------------------------------------------------------------------------------------- PRODUCT_ID

-- -------------------------------------------------------------------------------------------------------- PRODUCT_NAME

-- ---------------------------------------------------------------------------------------------------------- UNIT_PRICE
SELECT MIN(UNIT_PRICE), MAX(UNIT_PRICE)
FROM PRODUCTS;

-- ----------------------------------------------------------------------------------------------------- PRODUCT_DETAILS
SELECT MAX(DBMS_LOB.GETLENGTH(PRODUCT_DETAILS))
FROM PRODUCTS
;

SELECT PRODUCT_ID, PRODUCT_DETAILS
FROM PRODUCTS
;

SELECT PRODUCT_DETAILS, DBMS_LOB.GETLENGTH(PRODUCT_DETAILS)
FROM PRODUCTS
ORDER BY DBMS_LOB.GETLENGTH(PRODUCT_DETAILS) DESC
;

-- https://stackoverflow.com/a/54866009/330457
SELECT JSON_QUERY(PRODUCT_DETAILS, '$' RETURNING VARCHAR2(4000) PRETTY)
FROM PRODUCTS
;

-- https://stackoverflow.com/a/54867633/330457
select json_serialize(
               PRODUCT_DETAILS returning clob pretty
       ) PRODUCT_DETAILS_PRETTY
from PRODUCTS;

SELECT DISTINCT JSON_VALUE(PRODUCT_DETAILS, '$.colour')
FROM PRODUCTS
;
SELECT COUNT(1)
FROM PRODUCTS
WHERe JSON_VALUE(PRODUCT_DETAILS, '$.colour') IS NULL
   OR LENGTH(JSON_VALUE(PRODUCT_DETAILS, '$.colour')) = 0
;

SELECT DISTINCT JSON_VALUE(PRODUCT_DETAILS, '$.gender')
FROM PRODUCTS
;
SELECT COUNT(1)
FROM PRODUCTS
WHERe JSON_VALUE(PRODUCT_DETAILS, '$.gender') IS NULL
   OR LENGTH(JSON_VALUE(PRODUCT_DETAILS, '$.gender')) = 0
;

SELECT DISTINCT JSON_VALUE(PRODUCT_DETAILS, '$.brand')
FROM PRODUCTS
;
SELECT COUNT(1)
FROM PRODUCTS
WHERe JSON_VALUE(PRODUCT_DETAILS, '$.brand') IS NULL
   OR LENGTH(JSON_VALUE(PRODUCT_DETAILS, '$.brand')) = 0
;
SELECT COUNT(1)
FROM PRODUCTS
WHERe JSON_VALUE(PRODUCT_DETAILS, '$.description') IS NULL
   OR LENGTH(JSON_VALUE(PRODUCT_DETAILS, '$.description')) = 0
;

WITH size_categories AS (SELECT j.size_value,
                                COUNT(*) as count, CASE
    WHEN REGEXP_LIKE(j.size_value, '^\d+\s*YR', 'i') THEN 'Age-Based (Years)'
    WHEN REGEXP_LIKE(j.size_value, '^(XXS|XS|S|M|L|XL|XXL|XXXL)$', 'i')
    THEN 'Standard Letter Size'
    WHEN REGEXP_LIKE(j.size_value, '^\d+$') THEN 'Numeric Size'
    WHEN REGEXP_LIKE(j.size_value, '^\d+[\.-]\d+$') THEN 'Decimal/Range Size'
    ELSE 'Other'
END
as size_category
                         FROM PRODUCTS p,
                              JSON_TABLE(p.PRODUCT_DETAILS, '$.sizes[*]'
                                         COLUMNS (size_value VARCHAR2(20) PATH '$')
                              ) j
                         GROUP BY j.size_value)
SELECT size_category,
       COUNT(DISTINCT size_value) as unique_sizes,
       SUM(count)                 as total_occurrences,
       LISTAGG(size_value || ' (' || count || ')', ', ')
                                     WITHIN GROUP (ORDER BY count DESC) as size_breakdown
FROM size_categories
GROUP BY size_category
ORDER BY total_occurrences DESC
;

SELECT DISTINCT j.rating
FROM PRODUCTS p,
     JSON_TABLE(p.PRODUCT_DETAILS, '$.reviews[*]'
         COLUMNS (rating NUMBER PATH '$.rating')) j
WHERE j.rating IS NOT NULL
ORDER BY j.rating ASC
;
SELECT COUNT(1)
FROM PRODUCTS p,
     JSON_TABLE(p.PRODUCT_DETAILS, '$.reviews[*]'
         COLUMNS (rating NUMBER PATH '$.rating')) j
WHERE j.rating IS NULL
;

SELECT MIN(j.rating) as min_rating,
       MAX(j.rating) as max_rating
FROM PRODUCTS p,
     JSON_TABLE(p.PRODUCT_DETAILS, '$.reviews[*]'
         COLUMNS (rating NUMBER PATH '$.rating')
     ) j
;


SELECT j.review_text
FROM PRODUCTS p,
     JSON_TABLE(p.PRODUCT_DETAILS, '$.reviews[*]'
         COLUMNS (review_text VARCHAR2(4000) PATH '$.review')
     ) j
WHERE j.review_text IS NOT NULL
;
SELECT COUNT(1)
FROM PRODUCTS p,
     JSON_TABLE(p.PRODUCT_DETAILS, '$.reviews[*]'
         COLUMNS (review_text VARCHAR2(4000) PATH '$.review')
     ) j
WHERE j.review_text IS NULL
   OR LENGTH(j.review_text) = 0
;

SELECT COUNT(CASE WHEN j.review_text IS NULL THEN 1 END)     as null_reviews,
       COUNT(CASE WHEN j.review_text IS NOT NULL THEN 1 END) as not_null_reviews
FROM PRODUCTS p,
     JSON_TABLE(p.PRODUCT_DETAILS, '$.reviews[*]'
         COLUMNS (review_text VARCHAR2(4000) PATH '$.review')
     ) j
;

-- ------------------------------------------------------------------------------------------------------- PRODUCT_IMAGE

-- ----------------------------------------------------------------------------------------------------- IMAGE_MIME_TYPE

-- ------------------------------------------------------------------------------------------------------ IMAGE_FILENAME

-- ------------------------------------------------------------------------------------------------------- IMAGE_CHARSET

-- -------------------------------------------------------------------------------------------------- IMAGE_LAST_UPDATED
