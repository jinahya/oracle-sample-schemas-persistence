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
-- Exercises on CUSTOMERS, a problem per query that reads CUSTOMERS alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'CUSTOMERS' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- CO_CUSTOMERS_A_01
-- Before a mailing, marketing wants pairs of accounts that may belong to one person: the same name, ignoring case and
-- surrounding spaces, or the same email address before the @ at a different domain.
SELECT a.customer_id,
       a.full_name,
       a.email_address,
       b.customer_id   AS other_customer_id,
       b.full_name     AS other_full_name,
       b.email_address AS other_email_address
FROM customers a
         JOIN customers b ON b.customer_id > a.customer_id
WHERE UPPER(TRIM(b.full_name)) = UPPER(TRIM(a.full_name))
   OR SUBSTR(b.email_address, 1, INSTR(b.email_address, '@') - 1)
    = SUBSTR(a.email_address, 1, INSTR(a.email_address, '@') - 1)
ORDER BY a.customer_id, b.customer_id
;
