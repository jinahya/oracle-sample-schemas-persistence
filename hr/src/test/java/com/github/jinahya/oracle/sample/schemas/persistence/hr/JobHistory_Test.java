package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class JobHistory_Test extends _MappedHrEntity_Test<JobHistory, JobHistoryId> {

    JobHistory_Test() {
        super(JobHistory.class, JobHistoryId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<JobHistory> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<JobHistory> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY);
    }
}
