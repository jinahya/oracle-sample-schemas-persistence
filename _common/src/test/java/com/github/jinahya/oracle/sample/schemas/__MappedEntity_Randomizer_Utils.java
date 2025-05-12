package com.github.jinahya.oracle.sample.schemas;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public final class __MappedEntity_Randomizer_Utils {

    private static final Map<Class<?>, __MappedEntity_Randomizer<?>> RANDOMIZER_INSTANCES = new ConcurrentHashMap<>();

    @SuppressWarnings({
            "unchecked"
    })
    static <T extends __MappedEntity<T, ?>>
    Optional<__MappedEntity_Randomizer<T>> getRandomizerInstanceOf(final Class<T> entityClass) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        return Optional.ofNullable(
                (__MappedEntity_Randomizer<T>) RANDOMIZER_INSTANCES.computeIfAbsent(entityClass, k -> {
                    final var randomizerName = entityClass.getName() + "_Randomizer";
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
                    final __MappedEntity_Randomizer<?> randomizer;
                    try {
                        randomizer = (__MappedEntity_Randomizer<?>) constructor.newInstance();
                    } catch (final ReflectiveOperationException roe) {
                        log.warn("failed to get new instance with {}", constructor, roe);
                        return null;
                    }
                    return randomizer;
                })
        );
    }

    public static <T extends __MappedEntity<T, ?>> Optional<T> newRandomizedInstanceOf(final Class<T> entityClass) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        return getRandomizerInstanceOf(entityClass)
                .map(__MappedEntity_Randomizer::manufacturePojo);
    }

    public static <T extends __MappedEntity<T, ?>> T newRandomizedInstanceOfOrElseThrow(final Class<T> entityClass) {
        return newRandomizedInstanceOf(entityClass).orElseThrow();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private __MappedEntity_Randomizer_Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}