package com.github.jinahya.oracle.sample.schemas;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A utility for {@link _MappedEntity_Randomizer}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Slf4j
public final class _MappedEntity_Randomizer_Utils {

    private static final Map<Class<?>, _MappedEntity_Randomizer<?>> RANDOMIZER_INSTANCES = new ConcurrentHashMap<>();

    @SuppressWarnings({
            "unchecked", "rawtypes"
    })
    static <T extends _MappedEntity<T>>
    Optional<_MappedEntity_Randomizer<T>> getRandomizerInstanceOf(final Class<T> entityClass) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        return Optional.ofNullable(
                (_MappedEntity_Randomizer<T>) RANDOMIZER_INSTANCES.computeIfAbsent(entityClass, k -> {
                    if (true) {
                        return (_MappedEntity_Randomizer<?>)
                                __MappedEntity_Randomizer_Utils.getRandomizerInstanceOf((Class<__MappedEntity>) k)
                                        .orElse(null);
                    }
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
                    final _MappedEntity_Randomizer<?> randomizer;
                    try {
                        randomizer = (_MappedEntity_Randomizer<?>) constructor.newInstance();
                    } catch (final ReflectiveOperationException roe) {
                        log.warn("failed to get new instance with {}", constructor, roe);
                        return null;
                    }
                    return randomizer;
                })
        );
    }

    public static <T extends _MappedEntity<T>> Optional<T> newRandomizedInstanceOf(final Class<T> entityClass) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        return getRandomizerInstanceOf(entityClass)
                .map(_MappedEntity_Randomizer::manufacturePojo);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance, my ass.
     */
    private _MappedEntity_Randomizer_Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}