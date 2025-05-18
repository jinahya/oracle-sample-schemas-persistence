package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Test;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Employee_Test extends __MappedEntity_Test<Employee, Integer> {

    Employee_Test() {
        super(Employee.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<Employee> equalsVerifier() {
        return super.equalsVerifier();
    }
}