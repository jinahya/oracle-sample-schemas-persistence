package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntityBuilder_Test;

class DepartmentBuilder_Test extends _MappedHrEntityBuilder_Test<DepartmentBuilder, Department, Integer> {

    DepartmentBuilder_Test() {
        super(DepartmentBuilder.class, Department.class, Integer.class);
    }
}
