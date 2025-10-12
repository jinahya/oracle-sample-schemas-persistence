package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Job_Test extends __MappedEntity_Test<Job, String> {

    Job_Test() {
        super(Job.class, String.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Job> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<Job> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY)
                ;
    }
}
