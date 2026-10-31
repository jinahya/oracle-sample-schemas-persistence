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
-- Exercises on CHANNELS, a problem per query that reads CHANNELS alone, at the advanced level.
--
-- These are the advanced SQL solutions of the 'CHANNELS' section of EXERCISES.asciidoc, which also states each problem
-- in full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- ---------------------------------------------------------------------------------------------------- SH_CHANNELS_A_01
-- For a slide, the sales operations team wants one line per channel class that has more than one channel, naming its
-- channels alphabetically: Direct: Direct Sales, Tele Sales.
SELECT CHANNEL_CLASS || ': ' || LISTAGG(CHANNEL_DESC, ', ') WITHIN GROUP (ORDER BY CHANNEL_DESC) AS LINE
FROM CHANNELS
GROUP BY CHANNEL_CLASS
HAVING COUNT(*) > 1
ORDER BY CHANNEL_CLASS
;
