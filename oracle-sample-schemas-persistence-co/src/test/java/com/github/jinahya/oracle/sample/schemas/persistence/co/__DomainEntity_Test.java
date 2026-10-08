package com.github.jinahya.oracle.sample.schemas.persistence.co;

import java.util.Objects;

public abstract class __DomainEntity_Test<T extends __DomainEntity<U>, U> {

    protected __DomainEntity_Test(final Class<T> entityClass, final Class<U> idClass) {
        super();
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<T> entityClass;

    protected final Class<U> idClass;
}