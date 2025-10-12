package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedBuilder;

@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public abstract class _MappedCoBuilder<
        SELF extends _MappedCoBuilder<SELF, TARGET>,
        TARGET extends _MappedCo
        >
        extends __MappedBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedCoBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
}
