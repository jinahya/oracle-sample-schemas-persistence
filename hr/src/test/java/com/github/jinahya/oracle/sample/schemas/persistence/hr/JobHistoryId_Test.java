package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory;
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
        return super.equals_Verify_(equalsVerifier)
                .withOnlyTheseFields(
                        MappedJobHistory.ATTRIBUTE_NAME_EMPLOYEE_ID,
                        MappedJobHistory.ATTRIBUTE_NAME_START_DATE
                );
    }
}
