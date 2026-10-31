-- #%L
-- hr
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
-- Exercises on EMPLOYEES, a problem per query that reads EMPLOYEES alone, at the basic level.
--
-- These are the basic SQL solutions of the 'EMPLOYEES' section of EXERCISES.asciidoc, which also states each problem in
-- full and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_B_01
-- The service desk has an e-mail ID, such as SKING, from a ticket and needs the employee it belongs to.
SELECT *
FROM EMPLOYEES
WHERE EMAIL = :email
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_B_02
-- A department head wants everyone in their department (given the department), sorted by last name and then first name,
-- with anyone whose first name is missing listed first.
SELECT *
FROM EMPLOYEES
WHERE DEPARTMENT_ID = :departmentId
ORDER BY LAST_NAME ASC, FIRST_NAME ASC NULLS FIRST
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_B_03
-- When a job is restructured, the union agreement protects the longest-serving holders first. HR needs everyone
-- currently in a given job, longest-serving first.
SELECT *
FROM EMPLOYEES
WHERE JOB_ID = :jobId
ORDER BY HIRE_DATE ASC, EMPLOYEE_ID ASC
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_B_04
-- Sales operations wants the employees who earn a commission, highest rate first.
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, COMMISSION_PCT
FROM EMPLOYEES
WHERE COMMISSION_PCT IS NOT NULL
ORDER BY COMMISSION_PCT DESC, LAST_NAME ASC
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_B_05
-- An auditor checking the org chart wants every employee who is not assigned to a department.
SELECT *
FROM EMPLOYEES
WHERE DEPARTMENT_ID IS NULL
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_B_06
-- HR is preparing the probation review for everyone hired in a given period (from and to dates, both inclusive).
SELECT *
FROM EMPLOYEES
WHERE HIRE_DATE BETWEEN :fromDate AND :toDate
ORDER BY HIRE_DATE ASC
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_B_07
-- Reception has part of a surname over the phone and needs the matching employees, whatever the case typed.
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER
FROM EMPLOYEES
WHERE UPPER(LAST_NAME) LIKE UPPER(:pattern)
ORDER BY LAST_NAME ASC, FIRST_NAME ASC
;


-- --------------------------------------------------------------------------------------------------- HR_EMPLOYEES_B_08
-- The operations director wants one list of everyone in Shipping, IT and Sales (departments 50, 60 and 80).
SELECT *
FROM EMPLOYEES
WHERE DEPARTMENT_ID IN (50, 60, 80)
ORDER BY DEPARTMENT_ID ASC, LAST_NAME ASC
;
