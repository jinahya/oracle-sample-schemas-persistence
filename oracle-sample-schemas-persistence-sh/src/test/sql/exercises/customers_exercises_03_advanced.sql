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
-- Exercises on CUSTOMERS, a problem per query that reads CUSTOMERS alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'CUSTOMERS' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- SH_CUSTOMERS_A_01
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
