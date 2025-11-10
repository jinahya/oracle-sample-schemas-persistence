# PROBLEMS

## DEPARTMENTS

### List up departments order by number of employees

```sql
SELECT e.MEMBER_COUNT, d.*
FROM DEPARTMENTS d
         JOIN (SELECT DEPARTMENT_ID, COUNT(DEPARTMENT_ID) as MEMBER_COUNT
               FROM EMPLOYEES
               GROUP BY DEPARTMENT_ID
               ORDER BY MEMBER_COUNT DESC) e
              ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
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

### List up ***manager***s order by number of subordinates.

```sql
SELECT m.SUBORDINATE_COUNT, e.*
FROM EMPLOYEES e
         JOIN (SELECT MANAGER_ID,
                      COUNT(MANAGER_ID) AS SUBORDINATE_COUNT
               FROM EMPLOYEES
               GROUP BY MANAGER_ID
               ORDER BY SUBORDINATE_COUNT DESC) m
              ON e.EMPLOYEE_ID = m.MANAGER_ID
ORDER BY m.SUBORDINATE_COUNT DESC
```

```jpaql
```
