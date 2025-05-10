package com.github.jinahya.oracle.sample.schemas;

import jakarta.persistence.EntityManager;

public abstract class _MappedEntity_Persister<ENTITY extends _MappedEntity<ENTITY>>
        extends __MappedEntity_Persister<ENTITY> {

protected     _MappedEntity_Persister(final Class<ENTITY> entityClass) {
        super(entityClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected ENTITY persist(final EntityManager entityManager, final ENTITY entityInstance) {
        return super.persist(entityManager, entityInstance);
    }
}