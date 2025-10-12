package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

import com.github.jinahya.persistence.mapped.test.__MappedBuilder_Test;

public abstract class _MappedShBuilder_Test<
        BUILDER extends _MappedShBuilder<BUILDER, TARGET>,
        TARGET extends _MappedSh
        >
        extends __MappedBuilder_Test<BUILDER, TARGET> {

    protected _MappedShBuilder_Test(final Class<BUILDER> builderClass, final Class<TARGET> targetClass) {
        super(builderClass, targetClass);
    }
}
