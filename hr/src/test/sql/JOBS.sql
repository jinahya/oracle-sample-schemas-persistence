--

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
