package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;

/**
 * An abstract builder class for {@link _MappedCoEntity}s.
 *
 * @param <SELF>   self type parameter
 * @param <TARGET> entity type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public abstract class _MappedCoEntityBuilder<
        SELF extends _MappedCoEntityBuilder<SELF, TARGET>,
        TARGET extends _MappedCoEntity<?>
        >
        extends __MappedEntityBuilder<SELF, TARGET> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for building instances of the specified entity class.
     *
     * @param targetClass the entity class to build.
     * @see #targetClass
     */
    protected _MappedCoEntityBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
}
