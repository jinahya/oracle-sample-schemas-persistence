package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class JobHistoryWithEmbeddedId_Test extends MappedJobHistory_Test<JobHistoryWithEmbeddedId> {

    JobHistoryWithEmbeddedId_Test() {
        super(JobHistoryWithEmbeddedId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<JobHistoryWithEmbeddedId> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<JobHistoryWithEmbeddedId> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY)
                ;
    }
}
