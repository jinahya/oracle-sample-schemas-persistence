package com.github.jinahya.oracle.sample.schemas;

import uk.co.jemos.podam.api.AbstractClassInfoStrategy;
import uk.co.jemos.podam.api.ClassAttribute;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategyImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ___Base_Randomizer<T> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for generating randomized instance of specified value class.
     *
     * @param valueClass the entity class.
     */
    protected ___Base_Randomizer(final Class<T> valueClass, final String... attributeNamesToExclude) {
        super();
        this.valueClass = Objects.requireNonNull(valueClass, "valueClass is null");
        this.attributeNamesToExclude = Arrays.stream(
                        Objects.requireNonNull(attributeNamesToExclude, "attributeNamesToExclude is null"))
                .collect(Collectors.toCollection(HashSet::new));
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected DataProviderStrategy dataProviderStrategy() {
        final var strategy = new RandomDataProviderStrategyImpl();
//        strategy.setMaxDepth(10);
//        strategy.setMemoization(false);
        return strategy;
    }

    protected ClassInfoStrategy classInfoStrategy() {
        return new AbstractClassInfoStrategy() {
            // https://github.com/mtedone/podam/pull/84
            @Override
            public boolean approve(final ClassAttribute attribute) {
                if (attributeNamesToExclude.contains(attribute.getName())) {
                    return false;
                }
                return true;
            }
        };
    }

    protected PodamFactory podamFactory() {
        final var factory = new PodamFactoryImpl(dataProviderStrategy());
        factory.setClassStrategy(classInfoStrategy());
        return factory;
    }

    protected T manufacturePojo() {
        return podamFactory().manufacturePojo(valueClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<T> valueClass;

    final Set<String> attributeNamesToExclude;
}