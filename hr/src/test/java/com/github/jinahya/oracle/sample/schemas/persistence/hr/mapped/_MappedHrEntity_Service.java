package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import jakarta.persistence.EntityManager;

import java.util.Objects;

public abstract class _MappedHrEntity_Service<ENTITY extends _MappedHrEntity<ID>, ID> {

    protected _MappedHrEntity_Service(final Class<ENTITY> entityClass, final Class<ID> idClass,
                                      final EntityManager entityManager) {
        super();
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
        this.entityManager = Objects.requireNonNull(entityManager, "entityManager is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    ENTITY findById(final ID id) {
        return entityManager.find(entityClass, id);
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<ENTITY> entityClass;

    protected final Class<ID> idClass;

    protected final EntityManager entityManager;
}
