package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceTest;

class Employee_PersistenceTest extends _MappedHrEntity_PersistenceTest<Employee, Integer> {

    Employee_PersistenceTest() {
        super(Employee.class, Integer.class);
    }
}
