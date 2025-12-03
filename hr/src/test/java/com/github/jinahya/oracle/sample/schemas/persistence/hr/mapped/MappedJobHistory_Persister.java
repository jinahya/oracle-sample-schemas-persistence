package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

public class MappedJobHistory_Persister<ENTITY extends MappedJobHistory>
        extends _MappedHrEntity_Persister<ENTITY, JobHistoryId> {

    protected MappedJobHistory_Persister(final Class<ENTITY> entityClass) {
        super(entityClass, JobHistoryId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final ENTITY entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
