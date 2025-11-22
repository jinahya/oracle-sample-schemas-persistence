package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntityBuilder_Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeBuilder_Test extends _MappedHrEntityBuilder_Test<EmployeeBuilder, Employee, Integer> {

    EmployeeBuilder_Test() {
        super(EmployeeBuilder.class, Employee.class, Integer.class);
    }
}