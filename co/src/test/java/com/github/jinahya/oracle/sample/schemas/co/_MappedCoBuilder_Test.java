package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedBuilder_Test;

abstract class _MappedCoBuilder_Test<
        BUILDER extends _MappedCoBuilder<BUILDER, MAPPED>,
        MAPPED extends _MappedCo
        >
        extends __MappedBuilder_Test<BUILDER, MAPPED> {

    _MappedCoBuilder_Test(final Class<BUILDER> builderClass, final Class<MAPPED> mappedClass) {
        super(builderClass, mappedClass);
    }
}
