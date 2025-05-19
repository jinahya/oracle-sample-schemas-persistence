package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Test;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class JobHistory_Test extends __MappedEntity_Test<JobHistory, JobHistoryId> {

    JobHistory_Test() {
        super(JobHistory.class, JobHistoryId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<JobHistory> equalsVerifier() {
        return super.equalsVerifier();
    }
}