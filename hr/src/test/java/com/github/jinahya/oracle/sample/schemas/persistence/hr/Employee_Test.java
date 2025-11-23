package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Employee_Test extends _MappedHrEntity_Test<Employee, Integer> {

    Employee_Test() {
        super(Employee.class, Integer.class);
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Employee> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<Employee> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY);
    }
}
