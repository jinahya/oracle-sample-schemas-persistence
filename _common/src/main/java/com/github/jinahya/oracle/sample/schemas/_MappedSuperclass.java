package com.github.jinahya.oracle.sample.schemas;

import jakarta.persistence.MappedSuperclass;

/**
 * An abstract mapped superclass with an id of {@code Long} type.
 *
 * @param <SELF> self type parameter
 */
@MappedSuperclass
public abstract class _MappedSuperclass<SELF extends _MappedSuperclass<SELF>>
        extends __MappedSuperclass<SELF, Long> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedSuperclass() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString();
    }
}
