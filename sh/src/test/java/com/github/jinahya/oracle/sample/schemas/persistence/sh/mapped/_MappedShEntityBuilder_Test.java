package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

import com.github.jinahya.persistence.mapped.test.__MappedBuilder_Test;

public abstract class _MappedShEntityBuilder_Test<
        BUILDER extends _MappedShEntityBuilder<BUILDER, TARGET>,
        TARGET extends _MappedShEntity<?>
        >
        extends __MappedBuilder_Test<BUILDER, TARGET> {

    protected _MappedShEntityBuilder_Test(final Class<BUILDER> builderClass, final Class<TARGET> targetClass) {
        super(builderClass, targetClass);
    }
}
