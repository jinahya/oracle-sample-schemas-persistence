package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

import com.github.jinahya.persistence.mapped.__MappedBuilder;

@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public abstract class _MappedShBuilder<
        SELF extends _MappedShBuilder<SELF, TARGET>,
        TARGET extends _MappedSh
        >
        extends __MappedBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedShBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
}
