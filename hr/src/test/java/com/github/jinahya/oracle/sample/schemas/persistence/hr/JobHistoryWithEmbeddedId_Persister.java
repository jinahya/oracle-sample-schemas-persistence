package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory_Persister;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

@Deprecated(forRemoval = true)
class JobHistoryWithEmbeddedId_Persister extends MappedJobHistory_Persister<JobHistoryWithEmbeddedId> {

    JobHistoryWithEmbeddedId_Persister() {
        super(JobHistoryWithEmbeddedId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager,
                        @Nonnull final JobHistoryWithEmbeddedId entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
