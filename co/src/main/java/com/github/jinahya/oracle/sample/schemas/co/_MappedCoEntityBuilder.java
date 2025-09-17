package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
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
    protected _MappedCoEntityBuilder(final Class<ENTITY> entityClass) {
        super(entityClass);
    }
}
