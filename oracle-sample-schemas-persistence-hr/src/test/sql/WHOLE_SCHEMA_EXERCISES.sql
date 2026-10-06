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
-- Exercises on the whole schema, a problem per query that reads more than one table or view; Basic, then Intermediate,
-- then Advanced.
--
-- These are the SQL solutions of the 'Whole schema' section of EXERCISES.adoc, which also states each problem in full
-- and gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--
-- Problems that join, or subquery, more than one table.
--


-- =============================================================================================================== BASIC


-- ----------------------------------------------------------------------------- HR-SCHEMA-B-01 Who manages a department
-- A new starter asks who runs a department (given the department id).
SELECT d.DEPARTMENT_NAME, m.EMPLOYEE_ID, m.FIRST_NAME, m.LAST_NAME, m.EMAIL
FROM DEPARTMENTS d
         JOIN EMPLOYEES m ON m.EMPLOYEE_ID = d.MANAGER_ID
WHERE d.DEPARTMENT_ID = :departmentId
;


-- ------------------------------------------------------------------------------ HR-SCHEMA-B-02 An employee's job title
-- The payslip shows the employee's job title, not the job id.
SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, j.JOB_TITLE
FROM EMPLOYEES e
         JOIN JOBS j ON j.JOB_ID = e.JOB_ID
WHERE e.EMPLOYEE_ID = :employeeId
;


-- ----------------------------------------------------------------------- HR-SCHEMA-B-03 Countries of a region, by name
-- A regional director knows their region by name, not by id, and wants its countries.
SELECT c.COUNTRY_ID, c.COUNTRY_NAME
FROM COUNTRIES c
         JOIN REGIONS r ON r.REGION_ID = c.REGION_ID
WHERE r.REGION_NAME = :regionName
ORDER BY c.COUNTRY_NAME ASC
;


-- ======================================================================================================== INTERMEDIATE


-- ----------------------------------------------------------------------------- HR-SCHEMA-I-01 Headcount per department
-- HR wants every department with its headcount, largest first, including departments that have nobody.
SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, COUNT(e.EMPLOYEE_ID) AS HEADCOUNT
FROM DEPARTMENTS d
         LEFT JOIN EMPLOYEES e ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME
ORDER BY HEADCOUNT DESC, d.DEPARTMENT_ID ASC
;


-- ------------------------------------------------------------------------ HR-SCHEMA-I-02 Monthly payroll by department
-- Payroll needs the monthly salary cost of each department, highest first, to plan next year's budget.
SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, SUM(e.SALARY) AS MONTHLY_COST
FROM EMPLOYEES e
         JOIN DEPARTMENTS d ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME
ORDER BY MONTHLY_COST DESC
;


-- ----------------------------------------------------------------------------- HR-SCHEMA-I-03 Departments above a size
-- Facilities allocates a floor warden to every department with at least a given number of staff.
SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, COUNT(*) AS HEADCOUNT
FROM DEPARTMENTS d
         JOIN EMPLOYEES e ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME
HAVING COUNT(*) >= :minHeadcount
ORDER BY HEADCOUNT DESC
;


-- ------------------------------------------------------------------------------------ HR-SCHEMA-I-04 Company directory
-- Internal communications is printing the directory: every employee with job title, department name and city. Staff
-- with no department must still appear.
SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, j.JOB_TITLE, d.DEPARTMENT_NAME, l.CITY
FROM EMPLOYEES e
         JOIN JOBS j ON j.JOB_ID = e.JOB_ID
         LEFT JOIN DEPARTMENTS d ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
         LEFT JOIN LOCATIONS l ON l.LOCATION_ID = d.LOCATION_ID
ORDER BY e.LAST_NAME ASC, e.FIRST_NAME ASC
;


-- -------------------------------------------------------------------------- HR-SCHEMA-I-05 Actual pay against the band
-- Compensation wants, per job, how many people hold it and the lowest, average and highest salary actually paid, next
-- to the job's band.
SELECT j.JOB_ID,
       j.JOB_TITLE,
       COUNT(*)                AS HOLDERS,
       MIN(e.SALARY)           AS LOWEST,
       ROUND(AVG(e.SALARY), 2) AS AVERAGE,
       MAX(e.SALARY)           AS HIGHEST,
       j.MIN_SALARY,
       j.MAX_SALARY
FROM JOBS j
         JOIN EMPLOYEES e ON e.JOB_ID = j.JOB_ID
GROUP BY j.JOB_ID, j.JOB_TITLE, j.MIN_SALARY, j.MAX_SALARY
ORDER BY j.JOB_ID ASC
;


-- --------------------------------------------------------------------- HR-SCHEMA-I-06 Due for a promotion conversation
-- HR wants the people paid in the top tenth of their job's band — at or above 90% of the band maximum — because they
-- have little room left to grow without a new role.
SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, e.JOB_ID, e.SALARY, j.MAX_SALARY
FROM EMPLOYEES e
         JOIN JOBS j ON j.JOB_ID = e.JOB_ID
WHERE e.SALARY >= j.MAX_SALARY * 0.9
ORDER BY e.SALARY / j.MAX_SALARY DESC
;


-- ---------------------------------------------------------------------- HR-SCHEMA-I-07 Departments with nobody in them
-- Facilities wants to reclaim space from departments that have no employees, and to know whether each still has a
-- manager on record.
SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, d.MANAGER_ID
FROM DEPARTMENTS d
WHERE NOT EXISTS (SELECT 1 FROM EMPLOYEES e WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID)
ORDER BY d.DEPARTMENT_ID ASC
;


-- ---------------------------------------------------------------------- HR-SCHEMA-I-08 Headcount by country and region
-- The regional directors want headcount per country, grouped under their region.
SELECT r.REGION_NAME, c.COUNTRY_NAME, COUNT(*) AS HEADCOUNT
FROM EMPLOYEES e
         JOIN DEPARTMENTS d ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
         JOIN LOCATIONS l ON l.LOCATION_ID = d.LOCATION_ID
         JOIN COUNTRIES c ON c.COUNTRY_ID = l.COUNTRY_ID
         JOIN REGIONS r ON r.REGION_ID = c.REGION_ID
GROUP BY r.REGION_NAME, c.COUNTRY_NAME
ORDER BY r.REGION_NAME ASC, HEADCOUNT DESC
;


-- --------------------------------------------------------------------- HR-SCHEMA-I-09 Employees who have changed roles
-- HR wants the employees with at least one earlier position on record, and how many.
SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, COUNT(*) AS EARLIER_POSITIONS
FROM EMPLOYEES e
         JOIN JOB_HISTORY h ON h.EMPLOYEE_ID = e.EMPLOYEE_ID
GROUP BY e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME
ORDER BY EARLIER_POSITIONS DESC, e.EMPLOYEE_ID ASC
;


-- ------------------------------------------------------------------------------------------ HR-SCHEMA-I-10 Never moved
-- For a retention study, HR wants the employees who have no earlier position on record at all — they still hold the job
-- and department they joined in.
SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, e.HIRE_DATE
FROM EMPLOYEES e
WHERE NOT EXISTS (SELECT 1 FROM JOB_HISTORY h WHERE h.EMPLOYEE_ID = e.EMPLOYEE_ID)
ORDER BY e.HIRE_DATE ASC
;


-- ============================================================================================================ ADVANCED


-- ----------------------------------------------------------------- HR-SCHEMA-A-01 Past positions in another department
-- Internal mobility wants every earlier position held in a department other than the employee's current one: who moved,
-- from where, to where.
SELECT h.EMPLOYEE_ID,
       h.START_DATE,
       h.END_DATE,
       h.JOB_ID,
       h.DEPARTMENT_ID AS FROM_DEPARTMENT,
       e.DEPARTMENT_ID AS TO_DEPARTMENT
FROM JOB_HISTORY h
         JOIN EMPLOYEES e ON e.EMPLOYEE_ID = h.EMPLOYEE_ID
WHERE h.DEPARTMENT_ID <> e.DEPARTMENT_ID
ORDER BY h.EMPLOYEE_ID ASC, h.START_DATE ASC
;


-- ---------------------------------------------------------------------- HR-SCHEMA-A-02 Career timeline of one employee
-- An employee asks HR for their record: every position they have held, the current one included, in date order.
SELECT h.START_DATE, h.END_DATE, h.JOB_ID, j.JOB_TITLE, h.DEPARTMENT_ID
FROM JOB_HISTORY h
         JOIN JOBS j ON j.JOB_ID = h.JOB_ID
WHERE h.EMPLOYEE_ID = :employeeId
UNION ALL
SELECT e.HIRE_DATE, NULL, e.JOB_ID, j.JOB_TITLE, e.DEPARTMENT_ID
FROM EMPLOYEES e
         JOIN JOBS j ON j.JOB_ID = e.JOB_ID
WHERE e.EMPLOYEE_ID = :employeeId
ORDER BY 1
;


-- ------------------------------------------------------------------------------- HR-SCHEMA-A-03 Back in an earlier job
-- HR wants the employees whose current job is one they held before — boomerangs within the company.
SELECT EMPLOYEE_ID, JOB_ID
FROM JOB_HISTORY
INTERSECT
SELECT EMPLOYEE_ID, JOB_ID
FROM EMPLOYEES
ORDER BY 1
;


-- --------------------------------------------------------------------------- HR-SCHEMA-A-04 Offices with no department
-- Facilities is reviewing the property list: which locations host no department at all?
SELECT LOCATION_ID
FROM LOCATIONS MINUS
SELECT LOCATION_ID
FROM DEPARTMENTS
;


-- ------------------------------------------------------------------ HR-SCHEMA-A-05 Department roster in one round trip
-- The intranet's site page shows, for one location, each department and its staff. Loading departments and then each
-- department's employees one by one is the N+1 problem; the page needs a single query.
SELECT d.*, e.*
FROM DEPARTMENTS d
         LEFT JOIN EMPLOYEES e ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
WHERE d.LOCATION_ID = :locationId
ORDER BY d.DEPARTMENT_ID ASC, e.LAST_NAME ASC
;
