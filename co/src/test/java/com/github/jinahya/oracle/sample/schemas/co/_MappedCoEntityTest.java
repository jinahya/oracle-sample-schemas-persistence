package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityWithGeneratedIdentityTest;

abstract class _MappedCoEntityTest<ENTITY extends _MappedCoEntity>
        extends __MappedEntityWithGeneratedIdentityTest<ENTITY> {

    _MappedCoEntityTest(final Class<ENTITY> entityClass) {
        super(entityClass);
    }
}
