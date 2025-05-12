package com.github.jinahya.oracle.sample.schemas;

import jakarta.persistence.MappedSuperclass;

import java.io.Serial;
import java.util.function.Supplier;

/**
 * An abstract mapped-superclass with an id of {@code Long} type.
 *
 * @param <SELF> self type parameter
 */
@MappedSuperclass
public abstract class _MappedEntity<SELF extends _MappedEntity<SELF>>
        extends __MappedEntity<SELF, Long> {

    @Serial
    private static final long serialVersionUID = 8657726971456566178L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    protected static <T extends _MappedEntity<T>> T of(final Supplier<? extends T> supplier, final Long id) {
        return __MappedEntity.of(supplier, id);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedEntity() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString();
    }
}
