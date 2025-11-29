package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import jakarta.persistence.EntityManager;

public abstract class MappedJobHistoryWithEmbeddedId_Service<
        ENTITY extends MappedJobHistoryWithEmbeddedId<ID>,
        ID extends MappedJobHistoryId
        >
        extends MappedJobHistory_Service<ENTITY, ID> {

    protected MappedJobHistoryWithEmbeddedId_Service(final Class<ENTITY> entityClass, final Class<ID> idClass,
                                                     final EntityManager entityManager) {
        super(entityClass, idClass, entityManager);
    }
}
