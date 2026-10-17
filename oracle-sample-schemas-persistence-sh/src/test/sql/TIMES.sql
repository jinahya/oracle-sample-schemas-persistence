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
-- ------------------------------------------------------------------------------------------------------------- TIME_ID
SELECT MIN(TIME_ID), MAX(TIME_ID)
FROM TIMES
;
-- 2019-01-01, 2023-12-31

-- ------------------------------------------------------------------------------------------------------------ DAY_NAME
SELECT MIN(LENGTH(TRIM(DAY_NAME))), MAX(LENGTH(TRIM(DAY_NAME)))
FROM TIMES
;
-- 6, 9

SELECT MIN(DAY_NAME), MAX(DAY_NAME)
FROM TIMES
;
-- Friday, Wednesday

SELECT DISTINCT DAY_NAME
FROM SH.TIMES
;

-- -------------------------------------------------------------------------------------------------- DAY_NUMBER_IN_WEEK
SELECT MIN(DAY_NUMBER_IN_WEEK), MAX(DAY_NUMBER_IN_WEEK)
FROM TIMES
;
-- 1, 7

-- ------------------------------------------------------------------------------------------------- DAY_NUMBER_IN_MONTH
SELECT MIN(DAY_NUMBER_IN_MONTH), MAX(DAY_NUMBER_IN_MONTH)
FROM TIMES
;
-- 1, 31

-- ------------------------------------------------------------------------------------------------ CALENDAR_WEEK_NUMBER
SELECT MIN(CALENDAR_WEEK_NUMBER), MAX(CALENDAR_WEEK_NUMBER)
FROM TIMES
;
-- 1, 53

-- -------------------------------------------------------------------------------------------------- FISCAL_WEEK_NUMBER
SELECT MIN(FISCAL_WEEK_NUMBER), MAX(FISCAL_WEEK_NUMBER)
FROM TIMES
;
-- 1, 53

-- ----------------------------------------------------------------------------------------------------- WEEK_ENDING_DAY
SELECT MIN(WEEK_ENDING_DAY), MAX(WEEK_ENDING_DAY)
FROM TIMES
;
-- 2019-01-06, 2023-12-31

-- -------------------------------------------------------------------------------------------------- WEEK_ENDING_DAY_ID
SELECT MIN(WEEK_ENDING_DAY_ID), MAX(WEEK_ENDING_DAY_ID)
FROM TIMES
;
-- 1462, 2257

-- ----------------------------------------------------------------------------------------------- CALENDAR_MONTH_NUMBER
SELECT MIN(CALENDAR_MONTH_NUMBER), MAX(CALENDAR_MONTH_NUMBER)
FROM TIMES
;
-- 1, 12

-- ------------------------------------------------------------------------------------------------- FISCAL_MONTH_NUMBER
SELECT MIN(FISCAL_MONTH_NUMBER), MAX(FISCAL_MONTH_NUMBER)
FROM TIMES
;
-- 1, 12

-- ------------------------------------------------------------------------------------------------- CALENDAR_MONTH_DESC
SELECT MIN(LENGTH(TRIM(CALENDAR_MONTH_DESC))), MAX(LENGTH(TRIM(CALENDAR_MONTH_DESC)))
FROM TIMES
;
-- 7, 7

SELECT MIN(CALENDAR_MONTH_DESC), MAX(CALENDAR_MONTH_DESC)
FROM TIMES
;
-- 2019-01, 2023-12

-- --------------------------------------------------------------------------------------------------- CALENDAR_MONTH_ID
SELECT MIN(CALENDAR_MONTH_ID), MAX(CALENDAR_MONTH_ID)
FROM TIMES
;
-- 1672, 2223

-- --------------------------------------------------------------------------------------------------- FISCAL_MONTH_DESC
SELECT MIN(LENGTH(TRIM(FISCAL_MONTH_DESC))), MAX(LENGTH(TRIM(FISCAL_MONTH_DESC)))
FROM TIMES
;
-- 7, 7

SELECT MIN(FISCAL_MONTH_DESC), MAX(FISCAL_MONTH_DESC)
FROM TIMES
;
-- 2019-01, 2024-01

-- ----------------------------------------------------------------------------------------------------- FISCAL_MONTH_ID
SELECT MIN(FISCAL_MONTH_ID), MAX(FISCAL_MONTH_ID)
FROM TIMES
;
-- 1720, 2258

-- --------------------------------------------------------------------------------------------------- DAYS_IN_CAL_MONTH
SELECT MIN(DAYS_IN_CAL_MONTH), MAX(DAYS_IN_CAL_MONTH)
FROM TIMES
;
-- 28, 31

-- --------------------------------------------------------------------------------------------------- DAYS_IN_FIS_MONTH
SELECT MIN(DAYS_IN_FIS_MONTH), MAX(DAYS_IN_FIS_MONTH)
FROM TIMES
;
-- 25, 35

-- ---------------------------------------------------------------------------------------------------- END_OF_CAL_MONTH
SELECT MIN(END_OF_CAL_MONTH), MAX(END_OF_CAL_MONTH)
FROM TIMES
;
-- 2019-01-31, 2023-12-31

-- ---------------------------------------------------------------------------------------------------- END_OF_FIS_MONTH
SELECT MIN(END_OF_FIS_MONTH), MAX(END_OF_FIS_MONTH)
FROM TIMES
;
-- 2019-01-25, 2024-01-26

-- ------------------------------------------------------------------------------------------------- CALENDAR_MONTH_NAME
SELECT MIN(LENGTH(TRIM(CALENDAR_MONTH_NAME))), MAX(LENGTH(TRIM(CALENDAR_MONTH_NAME)))
FROM TIMES
;
-- 3, 9

SELECT MIN(CALENDAR_MONTH_NAME), MAX(CALENDAR_MONTH_NAME)
FROM TIMES
;
-- April, September

-- --------------------------------------------------------------------------------------------------- FISCAL_MONTH_NAME
SELECT MIN(LENGTH(TRIM(FISCAL_MONTH_NAME))), MAX(LENGTH(TRIM(FISCAL_MONTH_NAME)))
FROM TIMES
;
-- 3, 9

SELECT MIN(FISCAL_MONTH_NAME), MAX(FISCAL_MONTH_NAME)
FROM TIMES
;
-- April, September

-- ----------------------------------------------------------------------------------------------- CALENDAR_QUARTER_DESC
SELECT MIN(LENGTH(TRIM(CALENDAR_QUARTER_DESC))), MAX(LENGTH(TRIM(CALENDAR_QUARTER_DESC)))
FROM TIMES
;
-- 7, 7

SELECT MIN(CALENDAR_QUARTER_DESC), MAX(CALENDAR_QUARTER_DESC)
FROM TIMES
;
-- 2019-01, 2023-04

-- ------------------------------------------------------------------------------------------------- CALENDAR_QUARTER_ID
SELECT MIN(CALENDAR_QUARTER_ID), MAX(CALENDAR_QUARTER_ID)
FROM TIMES
;
-- 1769, 2150

-- ------------------------------------------------------------------------------------------------- FISCAL_QUARTER_DESC
SELECT MIN(LENGTH(TRIM(FISCAL_QUARTER_DESC))), MAX(LENGTH(TRIM(FISCAL_QUARTER_DESC)))
FROM TIMES
;
-- 7, 7

SELECT MIN(FISCAL_QUARTER_DESC), MAX(FISCAL_QUARTER_DESC)
FROM TIMES
;
-- 2019-01, 2024-01

-- --------------------------------------------------------------------------------------------------- FISCAL_QUARTER_ID
SELECT MIN(FISCAL_QUARTER_ID), MAX(FISCAL_QUARTER_ID)
FROM TIMES
;
-- 1785, 2260

-- ------------------------------------------------------------------------------------------------- DAYS_IN_CAL_QUARTER
SELECT MIN(DAYS_IN_CAL_QUARTER), MAX(DAYS_IN_CAL_QUARTER)
FROM TIMES
;
-- 90, 92

-- ------------------------------------------------------------------------------------------------- DAYS_IN_FIS_QUARTER
SELECT MIN(DAYS_IN_FIS_QUARTER), MAX(DAYS_IN_FIS_QUARTER)
FROM TIMES
;
-- 1, 98

-- -------------------------------------------------------------------------------------------------- END_OF_CAL_QUARTER
SELECT MIN(END_OF_CAL_QUARTER), MAX(END_OF_CAL_QUARTER)
FROM TIMES
;
-- 2019-03-31, 2023-12-31

-- -------------------------------------------------------------------------------------------------- END_OF_FIS_QUARTER
SELECT MIN(END_OF_FIS_QUARTER), MAX(END_OF_FIS_QUARTER)
FROM TIMES
;
-- 2019-03-29, 2024-03-30

-- --------------------------------------------------------------------------------------------- CALENDAR_QUARTER_NUMBER
SELECT MIN(CALENDAR_QUARTER_NUMBER), MAX(CALENDAR_QUARTER_NUMBER)
FROM TIMES
;
-- 1, 4

-- ----------------------------------------------------------------------------------------------- FISCAL_QUARTER_NUMBER
SELECT MIN(FISCAL_QUARTER_NUMBER), MAX(FISCAL_QUARTER_NUMBER)
FROM TIMES
;
-- 1, 4

-- ------------------------------------------------------------------------------------------------------- CALENDAR_YEAR
SELECT MIN(CALENDAR_YEAR), MAX(CALENDAR_YEAR)
FROM TIMES
;
-- 2019, 2023

-- ---------------------------------------------------------------------------------------------------- CALENDAR_YEAR_ID
SELECT MIN(CALENDAR_YEAR_ID), MAX(CALENDAR_YEAR_ID)
FROM TIMES
;
-- 1802, 1813

-- --------------------------------------------------------------------------------------------------------- FISCAL_YEAR
SELECT MIN(FISCAL_YEAR), MAX(FISCAL_YEAR)
FROM TIMES
;
-- 2019, 2024

-- ------------------------------------------------------------------------------------------------------ FISCAL_YEAR_ID
SELECT MIN(FISCAL_YEAR_ID), MAX(FISCAL_YEAR_ID)
FROM TIMES
;
-- 1806, 2259

-- ---------------------------------------------------------------------------------------------------- DAYS_IN_CAL_YEAR
SELECT MIN(DAYS_IN_CAL_YEAR), MAX(DAYS_IN_CAL_YEAR)
FROM TIMES
;
-- 365, 366

-- ---------------------------------------------------------------------------------------------------- DAYS_IN_FIS_YEAR
SELECT MIN(DAYS_IN_FIS_YEAR), MAX(DAYS_IN_FIS_YEAR)
FROM TIMES
;
-- 361, 371

-- ----------------------------------------------------------------------------------------------------- END_OF_CAL_YEAR
SELECT MIN(END_OF_CAL_YEAR), MAX(END_OF_CAL_YEAR)
FROM TIMES
;
-- 2019-12-31, 2023-12-31

-- ----------------------------------------------------------------------------------------------------- END_OF_FIS_YEAR
SELECT MIN(END_OF_FIS_YEAR), MAX(END_OF_FIS_YEAR)
FROM TIMES
;
-- 2019-12-27, 2024-12-28
