package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__BuilderTest;

abstract class _MappedCoEntityBuilderTest<
        BUILDER extends _MappedCoEntityBuilder<BUILDER, ENTITY>,
        ENTITY extends _MappedCoEntity
        >
        extends __BuilderTest<BUILDER, ENTITY> {

    _MappedCoEntityBuilderTest(final Class<BUILDER> builderClass, final Class<ENTITY> entityClass) {
        super(builderClass, entityClass);
    }
}
