package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.persistence.mapped.test.__Disable_PersistEntityInstance_Test;

@__Disable_PersistEntityInstance_Test
public abstract class MappedJobHistory_PersistenceIT<
        ENTITY extends MappedJobHistory<ID>,
        ID extends MappedJobHistoryId
        >
        extends _MappedHrEntity_PersistenceIT<ENTITY, ID> {

    protected MappedJobHistory_PersistenceIT(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }
}
