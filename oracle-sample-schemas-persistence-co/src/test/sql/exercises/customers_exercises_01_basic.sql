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
-- Exercises on CUSTOMERS, a problem per query that reads CUSTOMERS alone, at the basic level.
--
-- These are the basic SQL solutions of the 'CUSTOMERS' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- CO_CUSTOMERS_B_01
-- The support desk has a caller who gives their email address. Find the customer account for it.
SELECT customer_id, email_address, full_name
FROM customers
WHERE email_address = :emailAddress
;


-- --------------------------------------------------------------------------------------------------- CO_CUSTOMERS_B_02
-- The caller's name was only half heard. List the customers whose full name starts with the given text, ignoring case,
-- in alphabetical order.
SELECT customer_id, full_name, email_address
FROM customers
WHERE UPPER(full_name) LIKE UPPER(:prefix) || '%'
ORDER BY full_name
;
