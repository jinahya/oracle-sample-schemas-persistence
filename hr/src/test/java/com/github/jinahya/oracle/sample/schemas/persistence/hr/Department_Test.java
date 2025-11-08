package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Department_Test extends _MappedHrEntity_Test<Department, Integer> {

    Department_Test() {
        super(Department.class, Integer.class);
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Department> equals_Verify_(
            @Nonnull SingleTypeEqualsVerifierApi<Department> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier).suppress(Warning.SURROGATE_KEY);
    }
}