package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHr_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class JobHistoryId_Test extends _MappedHr_Test<JobHistoryId> {

    JobHistoryId_Test() {
        super(JobHistoryId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<JobHistoryId> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<JobHistoryId> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier);
    }
}
