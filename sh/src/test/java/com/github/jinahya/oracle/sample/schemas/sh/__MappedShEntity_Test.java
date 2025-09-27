package com.github.jinahya.oracle.sample.schemas.sh;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;

abstract class __MappedShEntity_Test<
        ENTITY extends __MappedShEntity<ID>,
        ID
        >
        extends __MappedEntity_Test<ENTITY, ID> {

    __MappedShEntity_Test(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }
}
