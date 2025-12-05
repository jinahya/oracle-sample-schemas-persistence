package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory_Persister;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

@Deprecated(forRemoval = true)
class JobHistoryWithIdClass_Persister extends MappedJobHistory_Persister<JobHistoryWithIdClass> {

    JobHistoryWithIdClass_Persister() {
        super(JobHistoryWithIdClass.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager,
                        @Nonnull final JobHistoryWithIdClass entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
