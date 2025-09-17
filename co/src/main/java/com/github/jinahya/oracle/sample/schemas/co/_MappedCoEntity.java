package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
abstract class _MappedCoEntity<ID> extends __MappedEntity<ID> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedCoEntity() {
        super();
    }

    _MappedCoEntity(final _MappedCoEntityBuilder<?, ?> builder) {
        super(builder);
    }
}
