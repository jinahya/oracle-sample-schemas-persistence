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
-- Exercises on CUSTOMERS, a problem per query that reads CUSTOMERS alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'CUSTOMERS' section of EXERCISES.adoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- --------------------------------------------------------------------- SH-CUSTOMERS-B-01 Look up a customer by surname
-- A customer-service agent has the caller on the phone and types the first few letters of the surname, :prefix, in
-- whatever case. Find the matching customers.
SELECT CUST_ID, CUST_FIRST_NAME, CUST_LAST_NAME, CUST_CITY, CUST_EMAIL
FROM CUSTOMERS
WHERE UPPER(CUST_LAST_NAME) LIKE UPPER(:prefix || '%')
ORDER BY CUST_LAST_NAME, CUST_FIRST_NAME, CUST_ID
;


-- ---------------------------------------------------------------- SH-CUSTOMERS-B-02 Customers in selected income bands
-- Marketing is planning a mail drop in :city (e.g. Los Angeles) aimed at the lower income bands, :levels (e.g. A: Below
-- 30,000, B: 30,000 - 49,999).
SELECT CUST_ID, CUST_FIRST_NAME, CUST_LAST_NAME, CUST_STREET_ADDRESS, CUST_POSTAL_CODE, CUST_INCOME_LEVEL
FROM CUSTOMERS
WHERE CUST_CITY = :city
  AND CUST_INCOME_LEVEL IN ('A: Below 30,000', 'B: 30,000 - 49,999')
ORDER BY CUST_INCOME_LEVEL, CUST_LAST_NAME
;


-- ---------------------------------------------------------------- SH-CUSTOMERS-B-03 Customers without a marital status
-- The data-quality team suspects that many customer records have no marital status. How many are there?
SELECT COUNT(*)
FROM CUSTOMERS
WHERE CUST_MARITAL_STATUS IS NULL
;


-- ----------------------------------------------------------------------- SH-CUSTOMERS-B-04 Income bands, unknowns last
-- The credit team wants the customers of :city ordered by income band, highest credit limit first within a band, and
-- the customers whose income band is unknown at the end of the list rather than the start.
SELECT CUST_ID, CUST_LAST_NAME, CUST_INCOME_LEVEL, CUST_CREDIT_LIMIT
FROM CUSTOMERS
WHERE CUST_CITY = :city
ORDER BY CUST_INCOME_LEVEL ASC NULLS LAST, CUST_CREDIT_LIMIT DESC
;


-- ======================================================================================================== INTERMEDIATE


-- ------------------------------------------------------- SH-CUSTOMERS-I-01 Email addresses shared by several customers
-- The data-quality team wants the e-mail addresses on file for more than one customer, most shared first.
SELECT CUST_EMAIL, COUNT(*) AS CUSTOMERS
FROM CUSTOMERS
WHERE CUST_EMAIL IS NOT NULL
GROUP BY CUST_EMAIL
HAVING COUNT(*) > 1
ORDER BY CUSTOMERS DESC, CUST_EMAIL
;


-- ============================================================================================================ ADVANCED


-- -------------------------------------------------------------- SH-CUSTOMERS-A-01 Credit limits above the band average
-- The credit-risk team is reviewing :city, and wants its customers whose credit limit is above the average credit limit
-- of their income band across the whole customer base, highest band first.
SELECT cu.CUST_ID, cu.CUST_LAST_NAME, cu.CUST_INCOME_LEVEL, cu.CUST_CREDIT_LIMIT
FROM CUSTOMERS cu
WHERE cu.CUST_CITY = :city
  AND cu.CUST_CREDIT_LIMIT > (SELECT AVG(cu2.CUST_CREDIT_LIMIT)
                              FROM CUSTOMERS cu2
                              WHERE cu2.CUST_INCOME_LEVEL = cu.CUST_INCOME_LEVEL)
ORDER BY cu.CUST_INCOME_LEVEL DESC, cu.CUST_CREDIT_LIMIT DESC
;
