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
-- ------------------------------------------------------------------------------------------------- CALENDAR_MONTH_DESC

-- ------------------------------------------------------------------------------------------------------------- DOLLARS

-- ------------------------------------------------------------------------------------------------------ CANDIDATE_KEYS
-- the narrowest NOT NULL and unique column(s) this view could map as a JPA @Id.
-- results: ../../../doc/IDs.asciidoc

-- how many rows does the view hold, and how many non-null / distinct values does each column hold?
-- a single-column candidate is one whose NOT_NULLS and DISTINCTS both equal ROW_COUNT.
SELECT COLUMN_NAME, ROW_COUNT, NOT_NULLS, DISTINCTS
FROM (SELECT COUNT(1)                            ROW_COUNT,
             COUNT(CALENDAR_MONTH_DESC)          NN_CALENDAR_MONTH_DESC,
             COUNT(DISTINCT CALENDAR_MONTH_DESC) DC_CALENDAR_MONTH_DESC,
             COUNT(DOLLARS)                      NN_DOLLARS,
             COUNT(DISTINCT DOLLARS)             DC_DOLLARS
      FROM CAL_MONTH_SALES_MV)
    UNPIVOT ((NOT_NULLS, DISTINCTS) FOR COLUMN_NAME IN (
        (NN_CALENDAR_MONTH_DESC, DC_CALENDAR_MONTH_DESC) AS 'CALENDAR_MONTH_DESC',
        (NN_DOLLARS, DC_DOLLARS ) AS 'DOLLARS'
        ))
ORDER BY DISTINCTS DESC, COLUMN_NAME
;

-- no rows: CALENDAR_MONTH_DESC is the GROUP BY key of the materialized query.
SELECT CALENDAR_MONTH_DESC, COUNT(1) c
FROM CAL_MONTH_SALES_MV
GROUP BY CALENDAR_MONTH_DESC
HAVING COUNT(1) > 1
;
