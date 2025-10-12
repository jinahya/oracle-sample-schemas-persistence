package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;

/**
 * An abstract builder class for {@link _MappedShEntity}s.
 *
 * @param <SELF>   self type parameter
 * @param <TARGET> entity type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public abstract class _MappedShEntityBuilder<
        SELF extends _MappedShEntityBuilder<SELF, TARGET>,
        TARGET extends _MappedShEntity<?>
        >
        extends __MappedEntityBuilder<SELF, TARGET> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for building instances of the specified entity class.
     *
     * @param targetClass the entity class to build.
     * @see #targetClass
     */
    protected _MappedShEntityBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }
}
