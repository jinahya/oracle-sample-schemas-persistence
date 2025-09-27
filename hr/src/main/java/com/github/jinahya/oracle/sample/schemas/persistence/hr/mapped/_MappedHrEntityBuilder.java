package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;

/**
 * An abstract builder class for {@link _MappedHrEntity}s.
 *
 * @param <SELF>   self type parameter
 * @param <TARGET> entity type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public abstract class _MappedHrEntityBuilder<
        SELF extends _MappedHrEntityBuilder<SELF, TARGET>,
        TARGET extends _MappedHrEntity<?>
        >
        extends __MappedEntityBuilder<SELF, TARGET> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for building instances of the specified entity class.
     *
     * @param targetClass the entity class to build.
     * @see #targetClass
     */
    protected _MappedHrEntityBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }
}
