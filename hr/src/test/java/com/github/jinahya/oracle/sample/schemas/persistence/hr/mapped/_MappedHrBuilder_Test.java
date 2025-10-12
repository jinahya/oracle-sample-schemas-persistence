package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.persistence.mapped.test.__MappedBuilder_Test;

/**
 * An abstract class for testing a specific subclass of {@link _MappedHrBuilder} class.
 *
 * @param <BUILDER> builder type parameter
 * @param <TARGET>  target type parameter author  &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class _MappedHrBuilder_Test<
        BUILDER extends _MappedHrBuilder<BUILDER, TARGET>,
        TARGET extends _MappedHr
        >
        extends __MappedBuilder_Test<BUILDER, TARGET> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for testing specified builder class.
     *
     * @param builderClass the builder class to test.
     * @param targetClass  the target class of the {@code builderClass}.
     */
    protected _MappedHrBuilder_Test(final Class<BUILDER> builderClass, final Class<TARGET> targetClass) {
        super(builderClass, targetClass);
    }
}
