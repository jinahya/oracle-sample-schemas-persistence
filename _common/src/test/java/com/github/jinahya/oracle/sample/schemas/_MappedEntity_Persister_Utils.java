package com.github.jinahya.oracle.sample.schemas;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public final class _MappedEntity_Persister_Utils {

    private static final Map<Class<?>, _MappedEntity_Persister<?>> PERSISTER_INSTANCES = new ConcurrentHashMap<>();

    @SuppressWarnings({
            "unchecked"
    })
    static <T extends _MappedEntity<T>>
    Optional<_MappedEntity_Persister<T>> getPersisterInstance(final Class<T> entityClass) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        return Optional.ofNullable(
                (_MappedEntity_Persister<T>) PERSISTER_INSTANCES.computeIfAbsent(entityClass, k -> {
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
                    final _MappedEntity_Persister<?> persister;
                    try {
                        persister = (_MappedEntity_Persister<?>) constructor.newInstance();
                    } catch (final ReflectiveOperationException roe) {
                        log.warn("failed to get new instance with {}", constructor, roe);
                        return null;
                    }
                    return persister;
                })
        );
    }

    public static <T extends _MappedEntity<T>>
    Optional<T> newPersistedInstanceOf(final Class<T> entityClass, final EntityManager entityManager,
                                       final T entityInstance) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        Objects.requireNonNull(entityInstance, "entityInstance is null");
        return getPersisterInstance(entityClass)
                .map(p -> p.persist(entityManager, entityInstance));
    }

    public static <T extends _MappedEntity<T>>
    Optional<T> newPersistedInstanceOf(final Class<T> entityClass, final EntityManager entityManager) {
        return _MappedEntity_Randomizer_Utils.newRandomizedInstanceOf(entityClass).flatMap(
                ei -> newPersistedInstanceOf(
                        entityClass,
                        entityManager,
                        ei
                )
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private _MappedEntity_Persister_Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}