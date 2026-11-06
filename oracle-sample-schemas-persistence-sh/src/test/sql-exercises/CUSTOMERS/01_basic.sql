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
-- Exercises on CUSTOMERS, a problem per query that reads CUSTOMERS alone, at the basic level.
--
-- These are the basic SQL solutions of the 'CUSTOMERS' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- SH_CUSTOMERS_B_01
-- A customer-service agent has the caller on the phone and types the first few letters of the surname, :prefix, in
-- whatever case. Find the matching customers.
SELECT CUST_ID, CUST_FIRST_NAME, CUST_LAST_NAME, CUST_CITY, CUST_EMAIL
FROM CUSTOMERS
WHERE UPPER(CUST_LAST_NAME) LIKE UPPER(:prefix || '%')
ORDER BY CUST_LAST_NAME, CUST_FIRST_NAME, CUST_ID
;


-- --------------------------------------------------------------------------------------------------- SH_CUSTOMERS_B_02
-- Marketing is planning a mail drop in :city (e.g. Los Angeles) aimed at the lower income bands, :levels (e.g. A: Below
-- 30,000, B: 30,000 - 49,999).
SELECT CUST_ID, CUST_FIRST_NAME, CUST_LAST_NAME, CUST_STREET_ADDRESS, CUST_POSTAL_CODE, CUST_INCOME_LEVEL
FROM CUSTOMERS
WHERE CUST_CITY = :city
  AND CUST_INCOME_LEVEL IN ('A: Below 30,000', 'B: 30,000 - 49,999')
ORDER BY CUST_INCOME_LEVEL, CUST_LAST_NAME, CUST_FIRST_NAME, CUST_ID
;


-- --------------------------------------------------------------------------------------------------- SH_CUSTOMERS_B_03
-- The data-quality team suspects that many customer records have no marital status. How many are there?
SELECT COUNT(*)
FROM CUSTOMERS
WHERE CUST_MARITAL_STATUS IS NULL
;


-- --------------------------------------------------------------------------------------------------- SH_CUSTOMERS_B_04
-- The credit team wants the customers of `:city` ordered by income band, the unknown bands at the end, and within a
-- band by credit limit, the highest first and the unknown limits last; `CUST_ID` breaks a tie.
SELECT CUST_ID, CUST_LAST_NAME, CUST_INCOME_LEVEL, CUST_CREDIT_LIMIT
FROM CUSTOMERS
WHERE CUST_CITY = :city
ORDER BY CUST_INCOME_LEVEL ASC NULLS LAST, CUST_CREDIT_LIMIT DESC NULLS LAST, CUST_ID
;


-- --------------------------------------------------------------------------------------------------- SH_CUSTOMERS_B_05
-- The account-management team wants the customer records of `:city` in effect at `:asOf`: a record runs from
-- `CUST_EFF_FROM`, included, up to `CUST_EFF_TO`, not included, and a missing date means no bound on that side.
SELECT CUST_ID, CUST_FIRST_NAME, CUST_LAST_NAME, CUST_EFF_FROM, CUST_EFF_TO, CUST_VALID
FROM CUSTOMERS
WHERE CUST_CITY = :city
  AND (CUST_EFF_FROM IS NULL OR CUST_EFF_FROM <= :asOf)
  AND (CUST_EFF_TO IS NULL OR :asOf < CUST_EFF_TO)
ORDER BY CUST_LAST_NAME, CUST_FIRST_NAME, CUST_ID
;
