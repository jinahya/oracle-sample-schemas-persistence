# PROBLEMS

## DEPARTMENTS

### List up departments order by number of employees

```sql
SELECT e.MEMBER_COUNT, d.*
FROM DEPARTMENTS d
         JOIN (SELECT DEPARTMENT_ID,
                      COUNT(DEPARTMENT_ID) as MEMBER_COUNT
               FROM EMPLOYEES
               GROUP BY DEPARTMENT_ID
               ORDER BY MEMBER_COUNT DESC) e
              ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
ORDER BY e.MEMBER_COUNT DESC
```

```jpaql
```

## EMPLOYEES

### Find the employee whose email address matches to given

```sql
SELECT *
FROM EMPLOYEES
WHERE EMAIL = ?
```

```jpaql
SELECT e
FROM Employee e
WHERE e.email = ?
```

### List up employees of a specific `JOB` order by their `HIRE_DATE`

```sql
SELECT *
FROM EMPLOYEES
WHERE JOB_ID = ?
ORDER BY HIRE_DATE ASC
```

```jpaql
SELECT e
FROM Employee e
WHERE e.jobId = ?
ORDER BY e.hireDate ASC
```

```jpaql
SELECT e
FROM Employee e
WHERE e.jobq = ?
ORDER BY e.hireDate ASC
```

```jpaql
SELECT e
FROM Employee e
WHERE e.job.jobId = ?
ORDER BY e.hireDate ASC
```

### List up ***manager***s order by number of ***subordinates***.

```sql
SELECT e.*, m.NUMBER_OF_SUBORDINATES
FROM EMPLOYEES e
         JOIN (SELECT MANAGER_ID, COUNT(MANAGER_ID) AS NUMBER_OF_SUBORDINATES
               FROM EMPLOYEES
               GROUP BY MANAGER_ID
               ORDER BY NUMBER_OF_SUBORDINATES DESC) m
              ON e.EMPLOYEE_ID = m.MANAGER_ID
WHERE m.NUMBER_OF_SUBORDINATES > 0
ORDER BY m.NUMBER_OF_SUBORDINATES DESC
```

```jpaql
SELECT e, COUNT(e.subordinates) AS numberOfSubordinates
FROM Employee AS e
WHERE numberOfSubordinates > 0
ORDER BY numberOfSubordinates ASC
```

## JOB_HISTORY

### List up employees who have worked for a specific `JOB`

```oracle
SELECT e.*
FROM JOB_HISTORY jh
         JOIN EMPLOYEES e ON jh.EMPLOYEE_ID = e.EMPLOYEE_ID
WHERE JOB_ID = :jobId
ORDER BY start_date ASC
```

```jpaql
SELECT jh.employee
FROM JobHistory jh
WHERE jh.job = :job
ORDER BY jh.id.startDate ASC
```

### List up employees who have worked for a specific `DEPARTMENT`

```oracle
SELECT d.*
FROM JOB_HISTORY jh
         JOIN HR.DEPARTMENTS d ON jh.EMPLOYEE_ID = d.DEPARTMENT_ID
WHERE JOB_ID = :jobId
ORDER BY start_date ASC
```

```jpaql
SELECT jh.employee
FROM JobHistory jh
WHERE jh.department = :department
ORDER BY jh.id.startDate ASC
```

## List up employees by longest period

```oracle
SELECT jh.end_date - jh.start_date AS days,
       jh.*
FROM JOB_HISTORY jh
ORDER BY days DESC
```

// may not work by service providers

```jpaql
SELECT jh.endDate - jh.id.startDate AS period,
       jh
FROM JobHistory jh
ORDER BY period DESC NULLS LAST
```

## JOBS

> [4.10. ORDER BY Clause]
>
> The keyword `NULLS` specifies the ordering of null values, either `FIRST` or `LAST`.
>
>* `FIRST` means that results are sorted so that all null values occur before all non-null values.
>
>* `LAST` means that results are sorted so that all null values occur after all non-null values.
>
>If `NULLS` is not specified, the database determines whether null values occur first or last.

### Select, order by `MIN_SALARY`, `NULL`s first

```oracle
SELECT *
FROM JOBS
ORDER BY MIN_SALARY ASC NULLS FIRST
```

```jpaql
SELECT e
FROM Job e
ORDER BY e.minSalary ASC NULLS FIRST
```

### Select, order by `MAX_SALARY`, `NULL`s last

```oracle
SELECT *
FROM JOBS
ORDER BY MAX_SALARY DESC NULLS LAST
```

```jpaql
SELECT e
FROM Job e
ORDER BY e.minSalary ASC NULLS FIRST
```

---

[4.10. ORDER BY Clause]: https://jakarta.ee/specifications/persistence/3.2/jakarta-persistence-spec-3.2#a5587
