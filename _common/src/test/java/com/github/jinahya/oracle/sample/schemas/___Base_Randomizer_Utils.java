package com.github.jinahya.oracle.sample.schemas;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public final class ___Base_Randomizer_Utils {

    private static final Map<Class<?>, ___Base_Randomizer<?>> RANDOMIZER_INSTANCES = new ConcurrentHashMap<>();

    @SuppressWarnings({
            "unchecked"
    })
    static <T> Optional<___Base_Randomizer<T>> getRandomizerInstance(final Class<T> valueClass) {
        Objects.requireNonNull(valueClass, "valueClass is null");
        return Optional.ofNullable(
                (___Base_Randomizer<T>) RANDOMIZER_INSTANCES.computeIfAbsent(valueClass, k -> {
                    final var randomizerName = valueClass.getName() + "_Randomizer";
                    final Class<?> randomizerClass;
                    try {
                        randomizerClass = Class.forName(randomizerName);
                    } catch (final ClassNotFoundException cnfe) {
                        log.warn("failed to find randomizer class for '{}'", randomizerName, cnfe);
                        return null;
                    }
                    final Constructor<?> constructor;
                    try {
                        constructor = randomizerClass.getDeclaredConstructor();
                    } catch (final NoSuchMethodException nsme) {
                        log.warn("failed to get constructor of {}", randomizerClass, nsme);
                        return null;
                    }
                    if (!constructor.canAccess(null)) {
                        constructor.setAccessible(true);
                    }
                    final ___Base_Randomizer<?> randomizer;
                    try {
                        randomizer = (___Base_Randomizer<?>) constructor.newInstance();
                    } catch (final ReflectiveOperationException roe) {
                        log.warn("failed to get new instance with {}", constructor, roe);
                        return null;
                    }
                    return randomizer;
                })
        );
    }

    public static <T> Optional<T> newRandomizedInstanceOf(final Class<T> valueClass) {
        Objects.requireNonNull(valueClass, "valueClass is null");
        return getRandomizerInstance(valueClass)
                .map(___Base_Randomizer::manufacturePojo);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ___Base_Randomizer_Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}