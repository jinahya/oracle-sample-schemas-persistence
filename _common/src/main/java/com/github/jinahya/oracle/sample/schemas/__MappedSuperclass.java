package com.github.jinahya.oracle.sample.schemas;

import jakarta.persistence.MappedSuperclass;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * An abstract mapped superclass with an id of a specific type.
 *
 * @param <SELF> self type parameter
 * @param <ID>   id type parameter
 */
@MappedSuperclass
public abstract class __MappedSuperclass<SELF extends __MappedSuperclass<SELF, ID>, ID extends java.io.Serializable>
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 5479115049307251838L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected __MappedSuperclass() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
                "_id_=" + _id_() +
                '}';
    }

    // https://jqno.nl/equalsverifier/manual/jpa-entities/
    // https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof __MappedSuperclass<?, ?>)) {
            return false;
        }
        return Objects.equals(
                _id_(),
                ((__MappedSuperclass<?, ?>) obj)._id_()
        );
    }

    // https://jqno.nl/equalsverifier/manual/jpa-entities/
    // https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    @Override
    public final int hashCode() {
        return Objects.hashCode(
                _id_()
        );
    }

    // ------------------------------------------------------------------------------------------------------------ _id_

    /**
     * Returns an instance of {@link ID} type of this entity.
     *
     * @return an instance of {@link ID} type of this entity.
     */
    @SuppressWarnings({
            "java:S100" // Method names should comply with a naming convention
    })
    protected abstract ID _id_();

    /**
     * Replaces current value of {@link ID} type of this entity with specified value.
     *
     * @param _id_ new value of {@link ID} type of this entity
     */
    @SuppressWarnings({
            "java:S100", // Method names should comply with a naming convention
            "java:S117" // Local variable and method parameter names should comply with a naming convention
    })
    protected abstract void _id_(final ID _id_);
}
