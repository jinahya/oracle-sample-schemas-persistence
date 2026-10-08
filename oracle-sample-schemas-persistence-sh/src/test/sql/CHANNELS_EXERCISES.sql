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
-- Exercises on CHANNELS, a problem per query that reads CHANNELS alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'CHANNELS' section of EXERCISES.adoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- ----------------------------------------------------------------------------------- SH-CHANNELS-B-01 Routes to market
-- The sales operations team is drawing up an overview of the routes to market, and wants every sales channel listed
-- under its channel class (Direct, Indirect, Others), alphabetically within each class.
SELECT CHANNEL_CLASS, CHANNEL_ID, CHANNEL_DESC
FROM CHANNELS
ORDER BY CHANNEL_CLASS, CHANNEL_DESC
;


-- ======================================================================================================== INTERMEDIATE


-- --------------------------------------------------------------------------------- SH-CHANNELS-I-01 Channels per class
-- The sales operations team wants to know how many channels each channel class has.
SELECT CHANNEL_CLASS, COUNT(*) AS CHANNELS
FROM CHANNELS
GROUP BY CHANNEL_CLASS
ORDER BY CHANNELS DESC, CHANNEL_CLASS
;


-- ============================================================================================================ ADVANCED


-- ------------------------------------------------------------------------ SH-CHANNELS-A-01 Channel classes on one line
-- For a slide, the sales operations team wants one line per channel class that has more than one channel, naming its
-- channels alphabetically: Direct: Direct Sales, Tele Sales.
SELECT CHANNEL_CLASS || ': ' || LISTAGG(CHANNEL_DESC, ', ') WITHIN GROUP (ORDER BY CHANNEL_DESC) AS LINE
FROM CHANNELS
GROUP BY CHANNEL_CLASS
HAVING COUNT(*) > 1
ORDER BY CHANNEL_CLASS
;
