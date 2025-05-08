package com.github.jinahya.oracle.sample.schemas;

import java.util.Objects;

public abstract class __MappedEntityTest<ENTITY extends __MappedEntity<ENTITY, ID>, ID extends java.io.Serializable> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected __MappedEntityTest(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super();
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<ENTITY> entityClass;

    protected final Class<ID> idClass;
}