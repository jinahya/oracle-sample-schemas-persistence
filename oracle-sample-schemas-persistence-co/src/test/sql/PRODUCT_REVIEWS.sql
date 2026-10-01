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

-- -------------------------------------------------------------------------------------------------------------- RATING

-- ---------------------------------------------------------------------------------------------------------- AVG_RATING

-- -------------------------------------------------------------------------------------------------------------- REVIEW

-- ------------------------------------------------------------------------------------------------------ CANDIDATE_KEYS
-- the narrowest NOT NULL and unique column(s) this view could map as a JPA @Id.
-- results: ../../../doc/IDs.asciidoc

-- how many rows does the view hold, and how many non-null / distinct values does each column hold?
-- a single-column candidate is one whose NOT_NULLS and DISTINCTS both equal ROW_COUNT.
SELECT COLUMN_NAME, ROW_COUNT, NOT_NULLS, DISTINCTS
FROM (SELECT COUNT(1)                     ROW_COUNT,
             COUNT(PRODUCT_NAME)          NN_PRODUCT_NAME,
             COUNT(DISTINCT PRODUCT_NAME) DC_PRODUCT_NAME,
             COUNT(RATING)                NN_RATING,
             COUNT(DISTINCT RATING)       DC_RATING,
             COUNT(AVG_RATING)            NN_AVG_RATING,
             COUNT(DISTINCT AVG_RATING)   DC_AVG_RATING,
             COUNT(REVIEW)                NN_REVIEW,
             COUNT(DISTINCT REVIEW)       DC_REVIEW
      FROM PRODUCT_REVIEWS)
    UNPIVOT ((NOT_NULLS, DISTINCTS) FOR COLUMN_NAME IN (
        (NN_PRODUCT_NAME, DC_PRODUCT_NAME) AS 'PRODUCT_NAME',
        (NN_RATING, DC_RATING ) AS 'RATING',
        (NN_AVG_RATING, DC_AVG_RATING ) AS 'AVG_RATING',
        (NN_REVIEW, DC_REVIEW ) AS 'REVIEW'
        ))
ORDER BY DISTINCTS DESC, COLUMN_NAME
;

-- the narrowest unique combination, but REVIEW is nullable (and 4000 characters wide),
-- so this is a coincidence of the shipped data rather than an @Id.
SELECT PRODUCT_NAME, REVIEW, COUNT(1) c
FROM PRODUCT_REVIEWS
GROUP BY PRODUCT_NAME, REVIEW
HAVING COUNT(1) > 1
;
