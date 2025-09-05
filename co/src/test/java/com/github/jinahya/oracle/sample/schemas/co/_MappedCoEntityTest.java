package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityWithGeneratedIdentity_Test;

abstract class _MappedCoEntityTest<ENTITY extends _MappedCoEntity>
        extends __MappedEntityWithGeneratedIdentity_Test<ENTITY> {

    _MappedCoEntityTest(final Class<ENTITY> entityClass) {
        super(entityClass);
    }
}
