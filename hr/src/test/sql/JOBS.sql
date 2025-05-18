--
-- create table HR.JOBS
-- (
--     JOB_ID     VARCHAR2(10) not null
--         constraint JOB_ID_PK
--             primary key,
--     JOB_TITLE  VARCHAR2(35) not null
--         constraint JOB_TITLE_NN
--             check ("JOB_TITLE" IS NOT NULL),
--     MIN_SALARY NUMBER(6),
--     MAX_SALARY NUMBER(6)
-- )
-- /
--
-- comment on table HR.JOBS is 'jobs table with job titles and salary ranges.
-- References with employees and job_history table.'
-- /
--
-- comment on column HR.JOBS.JOB_ID is 'Primary key of jobs table.'
-- /
--
-- comment on column HR.JOBS.JOB_TITLE is 'A not null column that shows job title, e.g. AD_VP, FI_ACCOUNTANT'
-- /
--
-- comment on column HR.JOBS.MIN_SALARY is 'Minimum salary for a job title.'
-- /
--
-- comment on column HR.JOBS.MAX_SALARY is 'Maximum salary for a job title'
-- /


-- -------------------------------------------------------------------------------------------------------------- JOB_ID
-- ----------------------------------------------------------------------------------------------------------- JOB_TITLE
-- ---------------------------------------------------------------------------------------------------------- MIN_SALARY
SELECT *
FROM JOBS
ORDER BY MIN_SALARY ASC
    FETCH FIRST 10 ROWS ONLY
;

-- ---------------------------------------------------------------------------------------------------------- MAX_SALARY
SELECT *
FROM JOBS
ORDER BY MAX_SALARY ASC
    FETCH FIRST 10 ROWS ONLY
;



SELECT MIN(MIN_SALARY), MAX(MAX_SALARY)
FROM JOBS
;


SELECT *
FROM JOBS
WHERE MIN_SALARY >= MAX_SALARY
;

SELECT *
FROM JOBS
WHERE MIN_SALARY < MAX_SALARY
;


SELECT s.*, s.MAX_SALARY - s.MIN_SALARY
FROM (SELECT *
      FROM JOBS
      ORDER BY (MAX_SALARY - MIN_SALARY) ASC) s
    FETCH FIRST 10 ROWS ONLY
;

SELECT s.*, s.MAX_SALARY - s.MIN_SALARY
FROM (SELECT *
      FROM JOBS
      ORDER BY (MAX_SALARY - MIN_SALARY) DESC) s
    FETCH FIRST 10 ROWS ONLY
;
