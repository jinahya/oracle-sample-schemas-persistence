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
-- Exercises on CUSTOMERS, a problem per query that reads CUSTOMERS alone, at the intermediate level.
--
-- These are the intermediate SQL solutions of the 'CUSTOMERS' section of EXERCISES.asciidoc, which also states each
-- problem in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- CO_CUSTOMERS_I_01
-- Accounts are meant to be opened as `first.last@...`. Data quality wants the customers whose email address, before the
-- `@`, is not their full name in lower case with the spaces turned into dots, including the addresses with nothing
-- before an `@` or no `@` at all; customers with the same name are ordered by `customer_id`.
SELECT customer_id, full_name, email_address
FROM customers
WHERE INSTR(email_address, '@') <= 1
   OR SUBSTR(email_address, 1, INSTR(email_address, '@') - 1) <> LOWER(REPLACE(full_name, ' ', '.'))
ORDER BY full_name, customer_id
;
