package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceIT;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Employee_PersistenceIT extends _MappedHrEntity_PersistenceIT<Employee, Integer> {

    // -----------------------------------------------------------------------------------------------------------------
    Employee_PersistenceIT() {
        super(Employee.class, Integer.class);
    }
}
