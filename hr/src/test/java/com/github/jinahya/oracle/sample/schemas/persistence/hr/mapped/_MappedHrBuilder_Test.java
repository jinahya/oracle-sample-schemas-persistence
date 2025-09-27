package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.persistence.mapped.test.__MappedBuilder_Test;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class _MappedHrBuilder_Test<
        BUILDER extends _MappedHrBuilder<BUILDER, TARGET>,
        TARGET extends _MappedHr
        >
        extends __MappedBuilder_Test<BUILDER, TARGET> {

    protected _MappedHrBuilder_Test(final Class<BUILDER> builderClass, final Class<TARGET> targetClass) {
        super(builderClass, targetClass);
    }
}
