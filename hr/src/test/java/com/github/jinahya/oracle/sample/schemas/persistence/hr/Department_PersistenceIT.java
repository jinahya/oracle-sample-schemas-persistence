package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceIT;

class Department_PersistenceIT extends _MappedHrEntity_PersistenceIT<Department, Integer> {

    Department_PersistenceIT() {
        super(Department.class, Integer.class);
    }
}