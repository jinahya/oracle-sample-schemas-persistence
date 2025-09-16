package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntityWithGeneratedIdentity;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
abstract class _MappedCoEntity extends __MappedEntityWithGeneratedIdentity {

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
