package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import jakarta.persistence.MappedSuperclass;

/**
 * An abstract mapped superclass for entities in the {@code CO} schema.
 *
 * @param <ID> id type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
abstract class _MappedCoEntity<ID> implements __MappedEntity<ID> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedCoEntity() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder to build from.
     */
    _MappedCoEntity(final _MappedCoEntityBuilder<?, ?> builder) {
//        super(builder);
        super();
    }
}
