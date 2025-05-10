package com.github.jinahya.oracle.sample.schemas;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Objects;

@Slf4j
abstract class ___MappedEntity_TestBase<ENTITY extends __MappedEntity<ENTITY, ID>, ID extends Serializable>
        extends ____MappedEntity_TestBase<ENTITY> {

    ___MappedEntity_TestBase(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass);
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
    }

    // ----------------------------------------------------------------------------------------------- super.entityClass

    // --------------------------------------------------------------------------------------------------------- idClass

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<ID> idClass;
}