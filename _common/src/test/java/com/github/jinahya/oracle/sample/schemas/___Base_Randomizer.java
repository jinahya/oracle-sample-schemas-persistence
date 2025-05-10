package com.github.jinahya.oracle.sample.schemas;

import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategyImpl;

import java.util.Objects;
import java.util.Optional;

abstract class ___Base_Randomizer<T> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for generating randomized instance of specified value class.
     *
     * @param valueClass the entity class.
     */
    ___Base_Randomizer(final Class<T> valueClass) {
        super();
        this.valueClass = Objects.requireNonNull(valueClass, "valueClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected DataProviderStrategy dataProviderStrategy() {
        return new RandomDataProviderStrategyImpl();
    }

    protected PodamFactory podamFactory() {
        final var factory = new PodamFactoryImpl(dataProviderStrategy());
        Optional.ofNullable(classInfoStrategy()).ifPresent(factory::setClassStrategy);
        return factory;
    }

    protected ClassInfoStrategy classInfoStrategy() {
        return null;
    }

    protected T manufacturePojo() {
        return podamFactory().manufacturePojo(valueClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<T> valueClass;
}