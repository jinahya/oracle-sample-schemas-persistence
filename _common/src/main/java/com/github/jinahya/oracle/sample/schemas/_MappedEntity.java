package com.github.jinahya.oracle.sample.schemas;

import jakarta.persistence.MappedSuperclass;

import java.io.Serial;

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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedEntity() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------------ super._id_
//    @Override
//    @SuppressWarnings({"unchecked"})
//    protected Long _id_() {
//        try {
//            return _MappedEntityUtils.getId((SELF) this);
//        } catch (final IllegalArgumentException iae) {
//            return null;
//        }
//    }
//
//    @Override
//    @SuppressWarnings({"unchecked"})
//    protected void _id_(Long _id_) {
//        try {
//            _MappedEntityUtils.setId((SELF) this, _id_);
//        } catch (final IllegalArgumentException iae) {
//            // empty
//        }
//    }
}
