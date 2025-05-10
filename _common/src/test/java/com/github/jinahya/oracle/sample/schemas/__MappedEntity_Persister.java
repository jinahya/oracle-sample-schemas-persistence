package com.github.jinahya.oracle.sample.schemas;

import jakarta.persistence.EntityManager;

import java.util.Objects;

public abstract class __MappedEntity_Persister<ENTITY extends __MappedEntity<ENTITY, ?>> {

    protected __MappedEntity_Persister(final Class<ENTITY> entityClass) {
        super();
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
    }

    protected ENTITY persist(final EntityManager entityManager, final ENTITY entityInstance) {
        entityManager.persist(entityInstance);
//        entityManager.flush();
        return entityInstance;
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<ENTITY> entityClass;
}