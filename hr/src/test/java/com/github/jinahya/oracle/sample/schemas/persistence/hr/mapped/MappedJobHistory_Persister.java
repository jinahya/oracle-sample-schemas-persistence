package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

public class MappedJobHistory_Persister<ENTITY extends MappedJobHistory<ID>, ID extends MappedJobHistoryId>
        extends _MappedHrEntity_Persister<ENTITY, ID> {

    protected MappedJobHistory_Persister(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final ENTITY entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
