package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntityWithGeneratedIdentityBuilder;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _MappedCoEntityBuilder<
        SELF extends _MappedCoEntityBuilder<SELF, ENTITY>,
        ENTITY extends _MappedCoEntity
        >
        extends __MappedEntityWithGeneratedIdentityBuilder<SELF, ENTITY> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected _MappedCoEntityBuilder(final Class<ENTITY> entityClass) {
        super(entityClass);
    }
}
