package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityWithGeneratedIdentity_Test;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class _MappedCoEntityTest<ENTITY extends _MappedCoEntity>
        extends __MappedEntityWithGeneratedIdentity_Test<ENTITY> {

    _MappedCoEntityTest(final Class<ENTITY> entityClass) {
        super(entityClass);
    }
}
