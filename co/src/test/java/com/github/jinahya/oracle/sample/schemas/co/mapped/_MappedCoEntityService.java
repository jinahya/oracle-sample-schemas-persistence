package com.github.jinahya.oracle.sample.schemas.co.mapped;

import java.util.Objects;

public abstract class _MappedCoEntityService<ENTITY extends _MappedCoEntity<ID>, ID> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected _MappedCoEntityService(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super();
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<ENTITY> entityClass;

    protected final Class<ID> idClass;
}
