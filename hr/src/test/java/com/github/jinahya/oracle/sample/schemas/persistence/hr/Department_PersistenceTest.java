package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceTest;

class Department_PersistenceTest extends _MappedHrEntity_PersistenceTest<Department, Integer> {

    Department_PersistenceTest() {
        super(Department.class, Integer.class);
    }
}