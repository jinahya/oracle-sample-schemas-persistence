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
-- Exercises on EMPLOYEES, a problem per query that reads EMPLOYEES alone; Basic, then Intermediate, then Advanced.
--
-- These are the SQL solutions of the 'EMPLOYEES' section of EXERCISES.adoc, which also states each problem in full and
-- gives its JPQL solutions and notes. Each problem below repeats the request it answers.
--
-- Parameters are bind variables (:name); the IDE or SQL*Plus prompts for them when a statement runs.
--


-- =============================================================================================================== BASIC


-- --------------------------------------------------------------------- HR-EMPLOYEES-B-01 Look up a colleague by e-mail
-- The service desk has an e-mail ID, such as SKING, from a ticket and needs the employee it belongs to.
SELECT *
FROM EMPLOYEES
WHERE EMAIL = :email
;


-- ------------------------------------------------------------------------- HR-EMPLOYEES-B-02 A department's staff list
-- A department head wants everyone in their department (given the department), sorted by last name and then first name,
-- with anyone whose first name is missing listed first.
SELECT *
FROM EMPLOYEES
WHERE DEPARTMENT_ID = :departmentId
ORDER BY LAST_NAME ASC, FIRST_NAME ASC NULLS FIRST
;


-- -------------------------------------------------------------------------- HR-EMPLOYEES-B-03 Seniority list for a job
-- When a job is restructured, the union agreement protects the longest-serving holders first. HR needs everyone
-- currently in a given job, longest-serving first.
SELECT *
FROM EMPLOYEES
WHERE JOB_ID = :jobId
ORDER BY HIRE_DATE ASC, EMPLOYEE_ID ASC
;


-- ------------------------------------------------------------------------------ HR-EMPLOYEES-B-04 Who is on commission
-- Sales operations wants the employees who earn a commission, highest rate first.
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, COMMISSION_PCT
FROM EMPLOYEES
WHERE COMMISSION_PCT IS NOT NULL
ORDER BY COMMISSION_PCT DESC, LAST_NAME ASC
;


-- -------------------------------------------------------------------------- HR-EMPLOYEES-B-05 Staff with no department
-- An auditor checking the org chart wants every employee who is not assigned to a department.
SELECT *
FROM EMPLOYEES
WHERE DEPARTMENT_ID IS NULL
;


-- --------------------------------------------------------------------------------- HR-EMPLOYEES-B-06 Hired in a period
-- HR is preparing the probation review for everyone hired in a given period (from and to dates, both inclusive).
SELECT *
FROM EMPLOYEES
WHERE HIRE_DATE BETWEEN :fromDate AND :toDate
ORDER BY HIRE_DATE ASC
;


-- -------------------------------------------------------------------------- HR-EMPLOYEES-B-07 Name search at reception
-- Reception has part of a surname over the phone and needs the matching employees, whatever the case typed.
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER
FROM EMPLOYEES
WHERE UPPER(LAST_NAME) LIKE UPPER(:pattern)
ORDER BY LAST_NAME ASC, FIRST_NAME ASC
;


-- ---------------------------------------------------------------------- HR-EMPLOYEES-B-08 Staff of several departments
-- The operations director wants one list of everyone in Shipping, IT and Sales (departments 50, 60 and 80).
SELECT *
FROM EMPLOYEES
WHERE DEPARTMENT_ID IN (50, 60, 80)
ORDER BY DEPARTMENT_ID ASC, LAST_NAME ASC
;


-- ======================================================================================================== INTERMEDIATE


-- ------------------------------------------------------------------------- HR-EMPLOYEES-I-01 Above the company average
-- The board asks who earns more than the company-wide average salary.
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY
FROM EMPLOYEES
WHERE SALARY > (SELECT AVG(SALARY) FROM EMPLOYEES)
ORDER BY SALARY DESC
;


-- ------------------------------------------------------------ HR-EMPLOYEES-I-02 Total monthly pay including commission
-- Payroll needs each employee's expected monthly pay: salary plus commission, where commission is a rate on salary and
-- is absent for most staff. Label each employee as commissioned or salaried.
SELECT EMPLOYEE_ID,
       LAST_NAME,
       SALARY,
       COMMISSION_PCT,
       SALARY * (1 + COALESCE(COMMISSION_PCT, 0))                               AS TOTAL_PAY,
       CASE WHEN COMMISSION_PCT IS NULL THEN 'Salaried' ELSE 'Commissioned' END AS PAY_TYPE
FROM EMPLOYEES
ORDER BY TOTAL_PAY DESC
;


-- --------------------------------------------------------------------------- HR-EMPLOYEES-I-03 Headcount per pay grade
-- HR reports headcount in three pay grades: under 5,000, 5,000 up to 10,000, and 10,000 or more.
SELECT CASE
           WHEN SALARY < 5000 THEN 'Low'
           WHEN SALARY < 10000 THEN 'Medium'
           ELSE 'High'
           END  AS GRADE,
       COUNT(*) AS HEADCOUNT
FROM EMPLOYEES
GROUP BY CASE
             WHEN SALARY < 5000 THEN 'Low'
             WHEN SALARY < 10000 THEN 'Medium'
             ELSE 'High'
             END
;


-- ------------------------------------------------------------------------ HR-EMPLOYEES-I-04 Earning more than the boss
-- An auditor flags any employee paid more than their direct manager.
SELECT e.EMPLOYEE_ID,
       e.LAST_NAME,
       e.SALARY,
       m.EMPLOYEE_ID AS MANAGER_ID,
       m.LAST_NAME   AS MANAGER,
       m.SALARY      AS MANAGER_SALARY
FROM EMPLOYEES e
         JOIN EMPLOYEES m ON m.EMPLOYEE_ID = e.MANAGER_ID
WHERE e.SALARY > m.SALARY
;


-- ----------------------------------------------------------------------------------- HR-EMPLOYEES-I-05 Span of control
-- The COO wants every manager with the number of people reporting directly to them, most first.
SELECT m.EMPLOYEE_ID, m.FIRST_NAME, m.LAST_NAME, COUNT(*) AS DIRECT_REPORTS
FROM EMPLOYEES m
         JOIN EMPLOYEES e ON e.MANAGER_ID = m.EMPLOYEE_ID
GROUP BY m.EMPLOYEE_ID, m.FIRST_NAME, m.LAST_NAME
ORDER BY DIRECT_REPORTS DESC, m.EMPLOYEE_ID ASC
;


-- ============================================================================================================ ADVANCED


-- --------------------------------------------------------------------- HR-EMPLOYEES-A-01 Top earner of each department
-- Each department head is told who their best-paid employee is; ties are all reported.
SELECT e.DEPARTMENT_ID, e.EMPLOYEE_ID, e.LAST_NAME, e.SALARY
FROM EMPLOYEES e
WHERE e.SALARY = (SELECT MAX(x.SALARY) FROM EMPLOYEES x WHERE x.DEPARTMENT_ID = e.DEPARTMENT_ID)
ORDER BY e.DEPARTMENT_ID ASC
;

SELECT DEPARTMENT_ID, EMPLOYEE_ID, LAST_NAME, SALARY
FROM (SELECT e.*, RANK() OVER (PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) AS RNK
      FROM EMPLOYEES e)
WHERE RNK = 1
ORDER BY DEPARTMENT_ID ASC
;


-- -------------------------------------------------------------------- HR-EMPLOYEES-A-02 Three best-paid per department
-- Ahead of the bonus round, each department head wants their three highest salaries and who earns them.
SELECT DEPARTMENT_ID, EMPLOYEE_ID, LAST_NAME, SALARY, RNK
FROM (SELECT e.*, DENSE_RANK() OVER (PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) AS RNK
      FROM EMPLOYEES e
      WHERE DEPARTMENT_ID IS NOT NULL)
WHERE RNK <= 3
ORDER BY DEPARTMENT_ID ASC, RNK ASC
;


-- -------------------------------------------------------------------------- HR-EMPLOYEES-A-03 Everyone under a manager
-- For a reorganisation, HR needs everyone who reports to a given manager, directly or indirectly, with their depth
-- below that manager.
SELECT LEVEL - 1                           AS DEPTH,
       EMPLOYEE_ID,
       LAST_NAME,
       MANAGER_ID,
       SYS_CONNECT_BY_PATH(LAST_NAME, '/') AS CHAIN
FROM EMPLOYEES
WHERE LEVEL > 1
    START
WITH EMPLOYEE_ID = :managerId
CONNECT BY PRIOR EMPLOYEE_ID = MANAGER_ID
ORDER SIBLINGS BY LAST_NAME
;

WITH CHAIN (EMPLOYEE_ID, LAST_NAME, MANAGER_ID, DEPTH) AS (SELECT EMPLOYEE_ID, LAST_NAME, MANAGER_ID, 0
                                                           FROM EMPLOYEES
                                                           WHERE EMPLOYEE_ID = :managerId
                                                           UNION ALL
                                                           SELECT e.EMPLOYEE_ID, e.LAST_NAME, e.MANAGER_ID, c.DEPTH + 1
                                                           FROM EMPLOYEES e
                                                                    JOIN CHAIN c ON e.MANAGER_ID = c.EMPLOYEE_ID)
SELECT DEPTH, EMPLOYEE_ID, LAST_NAME, MANAGER_ID
FROM CHAIN
WHERE DEPTH > 0
ORDER BY DEPTH ASC, LAST_NAME ASC
;


-- -------------------------------------------------------------------- HR-EMPLOYEES-A-04 Out-earning a whole department
-- Before a salary benchmark, HR asks two things about a reference department (given its id): who earns more than
-- everyone in it, and who earns more than at least one person in it.
SELECT EMPLOYEE_ID, LAST_NAME, SALARY
FROM EMPLOYEES
WHERE SALARY > ALL (SELECT SALARY FROM EMPLOYEES WHERE DEPARTMENT_ID = :departmentId)
ORDER BY SALARY DESC
;

SELECT EMPLOYEE_ID, LAST_NAME, SALARY
FROM EMPLOYEES
WHERE SALARY > ANY (SELECT SALARY FROM EMPLOYEES WHERE DEPARTMENT_ID = :departmentId)
ORDER BY SALARY DESC
;


-- -------------------------------------------------------------------------------- HR-EMPLOYEES-A-05 Work anniversaries
-- HR sends a card on every work anniversary. Given a month (1–12), list who joined in that month and how many years
-- they complete this year.
SELECT EMPLOYEE_ID,
       FIRST_NAME,
       LAST_NAME,
       HIRE_DATE,
       EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE) AS YEARS
FROM EMPLOYEES
WHERE EXTRACT(MONTH FROM HIRE_DATE) = :month
ORDER BY EXTRACT(DAY FROM HIRE_DATE) ASC, LAST_NAME ASC
;


-- ----------------------------------------------------------------------- HR-EMPLOYEES-A-06 Badges and e-mail migration
-- IT is moving everyone to full e-mail addresses and reprinting badges. For each employee they need a proposed address
-- (first.last@example.com, lower case), the badge line (EMP- and the id, then the last name and first initial), the
-- phone number with dashes instead of dots, and its last four digits for the desk extension.
SELECT EMPLOYEE_ID,
       LOWER(COALESCE(FIRST_NAME, '') || '.' || LAST_NAME) || '@example.com'                         AS PROPOSED_EMAIL,
       'EMP-' || TO_CHAR(EMPLOYEE_ID) || ' ' || LAST_NAME || ', ' || SUBSTR(FIRST_NAME, 1, 1) || '.' AS BADGE,
       REPLACE(PHONE_NUMBER, '.', '-')                                                               AS PHONE,
       SUBSTR(PHONE_NUMBER, -4)                                                                      AS LAST_FOUR
FROM EMPLOYEES
ORDER BY EMPLOYEE_ID ASC
;
