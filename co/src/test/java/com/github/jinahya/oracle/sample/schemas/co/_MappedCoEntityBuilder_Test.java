package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__Builder_Test;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class _MappedCoEntityBuilder_Test<
        BUILDER extends _MappedCoEntityBuilder<BUILDER, ENTITY>,
        ENTITY extends _MappedCoEntity<?>
        >
        extends __Builder_Test<BUILDER, ENTITY> {

    _MappedCoEntityBuilder_Test(final Class<BUILDER> builderClass, final Class<ENTITY> entityClass) {
        super(builderClass, entityClass);
    }
}
