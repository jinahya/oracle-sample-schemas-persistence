package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory_Persister;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

class JobHistoryWithEmbeddedId_Persister extends MappedJobHistory_Persister<JobHistoryWithEmbeddedId, JobHistoryId> {

    JobHistoryWithEmbeddedId_Persister() {
        super(JobHistoryWithEmbeddedId.class, JobHistoryId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager,
                        @Nonnull final JobHistoryWithEmbeddedId entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
