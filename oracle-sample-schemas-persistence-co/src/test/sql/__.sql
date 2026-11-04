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


-- ---------------------------------------------------------------------------------- TABLE COMMENTS
-- the comment of each table and view of the schema; what @Table(comment) holds
SELECT TABLE_NAME, TABLE_TYPE, COMMENTS
FROM ALL_TAB_COMMENTS
WHERE OWNER = 'CO'
ORDER BY TABLE_NAME ASC
;


-- --------------------------------------------------------------------------------- COLUMN COMMENTS
-- the comment of each column, in the order the table declares them; what @Column(comment) holds
SELECT c.TABLE_NAME, c.COLUMN_NAME, m.COMMENTS
FROM ALL_TAB_COLUMNS c
         LEFT JOIN ALL_COL_COMMENTS m
                   ON m.OWNER = c.OWNER
                       AND m.TABLE_NAME = c.TABLE_NAME
                       AND m.COLUMN_NAME = c.COLUMN_NAME
WHERE c.OWNER = 'CO'
ORDER BY c.TABLE_NAME ASC, c.COLUMN_ID ASC
;

