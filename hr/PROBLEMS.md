# PROBLEMS

## DEPARTMENTS

### List up all departments by `location`

```sql
SELECT *
FROM DEPARTMENTS
WHERE LOCATION_ID = ?
ORDER BY DEPARTMENT_ID ASC
```

```jpaql
SELECT e
FROM Department e
WHERE e.locationId = :locationId
ORDER BY e.departmentId ASC
```

```jpaql
SELECT e
FROM Department e
WHERE e.location = :location
ORDER BY e.departmentId ASC
```

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
WHERE e.jobId = :jobId
ORDER BY e.hireDate ASC
```

```jpaql
SELECT e
FROM Employee e
WHERE e.job = :job
ORDER BY e.hireDate ASC
```

```jpaql
SELECT e
FROM Employee e
WHERE e.job.jobId = :jobJobId
ORDER BY e.hireDate ASC
```

### List up employees of a specific `DEPARTMENT` order by their `LAST_NAME`, `FIRST_NAME`

```sql
SELECT *
FROM EMPLOYEES
WHERE DEPARTMENT_ID = ?
ORDER BY LAST_NAME ASC, FIRST_NAME ASC NULLS FIRST
```

```jpaql
SELECT e
FROM Employee e
WHERE e.departmentId = :departmentId
ORDER BY e.lastName ASC, e.firstName ASC NULLS FIRST
```

```jpaql
SELECT e
FROM Employee e
WHERE e.department = :department
ORDER BY e.lastName ASC, e.firstName ASC NULLS FIRST
```

```jpaql
SELECT e
FROM Employee e
WHERE e.department.departmentId = :departmentDepartmentId
ORDER BY e.lastName ASC, e.firstName ASC NULLS FIRST
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

### List up, for a specific `JOB_ID`, order by `START_DATE`

```oracle
SELECT jh.*
FROM JOB_HISTORY jh
WHERE JOB_ID = :jobId
ORDER BY start_date ASC
```

`JobHistoryWithEmbeddedId`

```jpaql
SELECT e
FROM JobHistory e
WHERE e.jobId = :jobId
ORDER BY e.id.startDate ASC
```

`JobHistoryWithIdClass`

```jpaql
SELECT e
FROM JobHistory e
WHERE e.jobId = :jobId
ORDER BY e.startDate ASC
```

### List up, for a specific `DEPARTMENT_ID`, order by `START_DATE`

```oracle
SELECT jh.*
FROM JOB_HISTORY jh
WHERE DEPARTMENT_ID = :departmentId
ORDER BY start_date ASC
```

`JobHistoryWithEmbeddedId`

```jpaql
SELECT e
FROM JobHistory e
WHERE e.departmentId = :departmentId
ORDER BY e.id.startDate ASC
```

`JobHistoryWithIdClass`

```jpaql
SELECT e
FROM JobHistory e
WHERE e.departmentId = :departmentId
ORDER BY e.startDate ASC
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
