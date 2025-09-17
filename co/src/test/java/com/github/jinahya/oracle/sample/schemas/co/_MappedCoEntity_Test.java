package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class _MappedCoEntity_Test<ENTITY extends _MappedCoEntity<ID>, ID>
        extends __MappedEntity_Test<ENTITY, ID> {

    _MappedCoEntity_Test(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }
}
