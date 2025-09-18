package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;

/**
 * An abstract builder class for {@link _MappedCoEntity}s.
 *
 * @param <SELF>   self type parameter
 * @param <ENTITY> entity type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public abstract class _MappedCoEntityBuilder<
        SELF extends _MappedCoEntityBuilder<SELF, ENTITY>,
        ENTITY extends _MappedCoEntity<?>
        >
        extends __MappedEntityBuilder<SELF, ENTITY> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for building instances of the specified entity class.
     *
     * @param entityClass the entity class to build.
     * @see #targetClass
     */
    protected _MappedCoEntityBuilder(final Class<ENTITY> entityClass) {
        super(entityClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
}
