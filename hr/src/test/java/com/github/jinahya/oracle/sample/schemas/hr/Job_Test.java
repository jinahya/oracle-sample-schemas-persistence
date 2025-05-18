package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Test;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Job_Test extends __MappedEntity_Test<Job, String> {

    Job_Test() {
        super(Job.class, String.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<Job> equalsVerifier() {
        return super.equalsVerifier();
    }
}