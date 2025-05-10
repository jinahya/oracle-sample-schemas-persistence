package com.github.jinahya.oracle.sample.schemas;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public final class __MappedEntity_Persister_Utils {

    private static final Map<Class<?>, __MappedEntity_Persister<?>> PERSISTER_INSTANCES = new ConcurrentHashMap<>();

    @SuppressWarnings({
            "unchecked"
    })
    static <T extends __MappedEntity<T, ?>>
    Optional<__MappedEntity_Persister<T>> getPersisterInstance(final Class<T> entityClass) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        return Optional.ofNullable(
                (__MappedEntity_Persister<T>) PERSISTER_INSTANCES.computeIfAbsent(entityClass, k -> {
                    final var persisterName = entityClass.getName() + "_Persister";
                    final Class<?> persisterClass;
                    try {
                        persisterClass = Class.forName(persisterName);
                    } catch (final ClassNotFoundException cnfe) {
                        log.warn("failed to find persister class for '{}'", persisterName, cnfe);
                        return null;
                    }
                    final Constructor<?> constructor;
                    try {
                        constructor = persisterClass.getDeclaredConstructor();
                    } catch (final NoSuchMethodException nsme) {
                        log.warn("failed to get constructor of {}", persisterClass, nsme);
                        return null;
                    }
                    if (!constructor.canAccess(null)) {
                        constructor.setAccessible(true);
                    }
                    final __MappedEntity_Persister<?> persister;
                    try {
                        persister = (__MappedEntity_Persister<?>) constructor.newInstance();
                    } catch (final ReflectiveOperationException roe) {
                        log.warn("failed to get new instance with {}", constructor, roe);
                        return null;
                    }
                    return persister;
                })
        );
    }

    public static <T extends __MappedEntity<T, ?>>
    Optional<T> newPersistedInstanceOf(final Class<T> entityClass, final EntityManager entityManager,
                                       final T entityInstance) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        Objects.requireNonNull(entityInstance, "entityInstance is null");
        return getPersisterInstance(entityClass)
                .map(p -> p.persist(entityManager, entityInstance));
    }

    public static <T extends __MappedEntity<T, ?>>
    Optional<T> newPersistedInstanceOf(final Class<T> entityClass, final EntityManager entityManager) {
        return __MappedEntity_Randomizer_Utils.newRandomizedInstanceOf(entityClass).flatMap(
                ei -> newPersistedInstanceOf(
                        entityClass,
                        entityManager,
                        ei
                )
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private __MappedEntity_Persister_Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}