package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.persistence.mapped.test.__MappedBuilder_Test;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class _MappedHrEntityBuilder_Test<
        BUILDER extends _MappedHrEntityBuilder<BUILDER, TARGET>,
        TARGET extends _MappedHrEntity<?>
        >
        extends __MappedBuilder_Test<BUILDER, TARGET> {

    protected _MappedHrEntityBuilder_Test(final Class<BUILDER> builderClass, final Class<TARGET> targetClass) {
        super(builderClass, targetClass);
    }
}
