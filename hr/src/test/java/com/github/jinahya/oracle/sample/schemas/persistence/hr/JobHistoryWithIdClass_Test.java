package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class JobHistoryWithIdClass_Test extends MappedJobHistory_Test<JobHistoryWithIdClass, JobHistoryId> {

    JobHistoryWithIdClass_Test() {
        super(JobHistoryWithIdClass.class, JobHistoryId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<JobHistoryWithIdClass> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<JobHistoryWithIdClass> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY)
                ;
    }
}
