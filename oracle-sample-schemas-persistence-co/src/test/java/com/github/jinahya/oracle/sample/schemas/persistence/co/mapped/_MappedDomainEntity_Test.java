package com.github.jinahya.oracle.sample.schemas.persistence.co.mapped;

import java.util.Objects;

public abstract class _MappedDomainEntity_Test<T extends _MappedDomainEntity<U>, U> {

    protected _MappedDomainEntity_Test(final Class<T> entityClass, final Class<U> idClass) {
        super();
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<T> entityClass;

    protected final Class<U> idClass;
}