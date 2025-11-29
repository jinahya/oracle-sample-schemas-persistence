package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistoryWithEmbeddedId_Service;
import jakarta.persistence.EntityManager;

class JobHistoryWithEmbeddedIdService
        extends MappedJobHistoryWithEmbeddedId_Service<JobHistoryWithEmbeddedId, JobHistoryId> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected JobHistoryWithEmbeddedIdService(final EntityManager entityManager) {
        super(JobHistoryWithEmbeddedId.class, JobHistoryId.class, entityManager);
    }
}
