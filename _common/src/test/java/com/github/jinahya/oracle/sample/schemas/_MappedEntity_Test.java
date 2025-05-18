package com.github.jinahya.oracle.sample.schemas;

/**
 * An abstract class for testing specific subclass of {@link _MappedEntity} class.
 *
 * @param <ENTITY> entity type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class _MappedEntity_Test<ENTITY extends _MappedEntity<ENTITY>>
        extends __MappedEntity_Test<ENTITY, Long> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for testing specified entity class.
     *
     * @param entityClass the entity class to test.
     */
    protected _MappedEntity_Test(final Class<ENTITY> entityClass) {
        super(entityClass, Long.class);
    }
}