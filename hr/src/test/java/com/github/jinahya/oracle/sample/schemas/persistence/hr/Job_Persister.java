package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Persister;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

class Job_Persister extends __MappedEntity_Persister<Job, String> {

    Job_Persister() {
        super(Job.class, String.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final Job entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
