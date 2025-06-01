package com.github.jinahya.oracle.sample.schemas;

import java.io.Serializable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public final class __MappedEntity_Test_Utils {

    private static final Map<Class<?>, Constructor<?>> CONSTRUCTORS = new HashMap<>();

    @SuppressWarnings({"unchecked"})
    private static <T> Constructor<T> constructor(final Class<T> clazz) {
        Objects.requireNonNull(clazz, "clazz is null");
        return (Constructor<T>) CONSTRUCTORS.computeIfAbsent(clazz, k -> {
            try {
                final var constructor = k.getDeclaredConstructor();
                if (!constructor.canAccess(null)) {
                    constructor.setAccessible(true);
                }
                return constructor;
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException("failed to get constructor of " + k, roe);
            }
        });
    }

    private static final Map<Class<?>, MethodHandles.Lookup> LOOKUPS = new HashMap<>();

    private static MethodHandles.Lookup lookup(final Class<?> clazz) {
        Objects.requireNonNull(clazz, "clazz is null");
        return LOOKUPS.computeIfAbsent(clazz, k -> {
            try {
                return MethodHandles.privateLookupIn(k, MethodHandles.lookup());
            } catch (final IllegalAccessException iae) {
                throw new RuntimeException("failed to get lookup for " + k, iae);
            }
        });
    }

    private static final Map<Class<?>, MethodHandle> CONSTRUCTOR_HANDLES = new HashMap<>();

    private static MethodHandle constructorHandle(final Class<?> clazz) {
        Objects.requireNonNull(clazz, "clazz is null");
        return CONSTRUCTOR_HANDLES.computeIfAbsent(clazz, k -> {
            final var constructor = constructor(k);
            try {
                return lookup(k).unreflectConstructor(constructor);
            } catch (final IllegalAccessException iae) {
                throw new RuntimeException("failed to unreflect " + constructor, iae);
            }
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @jakarta.annotation.Nonnull
    public static <
            ENTITY extends __MappedEntity<ENTITY, ?>
            >
    ENTITY newEntityInstanceOf(@jakarta.annotation.Nonnull final Class<ENTITY> entityClass) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        if (ThreadLocalRandom.current().nextBoolean()) {
            try {
                final var constructor = entityClass.getDeclaredConstructor();
                if (!constructor.canAccess(null)) {
                    constructor.setAccessible(true);
                }
                return constructor.newInstance();
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException("failed to instantiate " + entityClass, roe);
            }
        } else {
            final var constructorHandle = constructorHandle(entityClass);
            try {
                return entityClass.cast(constructorHandle.invoke());
            } catch (final Throwable t) {
                throw new RuntimeException("failed to invoke " + constructorHandle, t);
            }
        }
    }

    @jakarta.annotation.Nonnull
    public static <
            ENTITY extends __MappedEntity<ENTITY, ID>,
            ID extends Serializable
            >
    ENTITY newEntityInstanceOf(@jakarta.annotation.Nonnull final Class<ENTITY> entityClass,
                               @jakarta.annotation.Nullable final ID _id_) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        final ENTITY instance = newEntityInstanceOf(entityClass);
        instance._id_(_id_);
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private __MappedEntity_Test_Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}