package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceTest;

public abstract class _MappedShEntity_PersistenceTest<ENTITY extends _MappedShEntity<ID>, ID>
        extends __MappedEntity_PersistenceTest<ENTITY, ID> {

    protected _MappedShEntity_PersistenceTest(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }
}
