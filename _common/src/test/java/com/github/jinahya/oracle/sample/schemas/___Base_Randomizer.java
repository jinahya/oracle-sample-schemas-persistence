package com.github.jinahya.oracle.sample.schemas;

import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategyImpl;

import java.util.Objects;

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
    protected DataProviderStrategy strategy() {
        return new RandomDataProviderStrategyImpl();
    }

    protected PodamFactory factory() {
        return new PodamFactoryImpl(strategy());
    }

    protected T get() {
        final var value = factory().manufacturePojo(valueClass);
        BeanValidationTestUtils.requireValid(value);
        return value;
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<T> valueClass;
}