package com.github.jinahya.oracle.sample.schemas;

import lombok.extern.slf4j.Slf4j;
import org.mockito.Mockito;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.spy;

@Slf4j
abstract class ____MappedEntity_TestBase<ENTITY extends __MappedEntity<ENTITY, ?>> {

    ____MappedEntity_TestBase(final Class<ENTITY> entityClass) {
        super();
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
    }

    // ----------------------------------------------------------------------------------------------------- entityClass
    protected ENTITY newEntityInstance() {
        return __MappedEntity_Test_Utils.newEntityInstanceOf(entityClass);
    }

    protected ENTITY newEntitySpy() {
        return spy(newEntityInstance());
    }

    protected Optional<ENTITY> newRandomizedEntityInstance() {
        return __MappedEntity_Randomizer_Utils.newRandomizedInstanceOf(entityClass);
    }

    protected Optional<ENTITY> newRandomizedEntitySpy() {
        return newRandomizedEntityInstance().map(Mockito::spy);
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<ENTITY> entityClass;
}