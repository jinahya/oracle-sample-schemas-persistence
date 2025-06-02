package com.github.jinahya.oracle.sample.schemas;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.EmbeddedId;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public final class __MappedEntityUtils {

    private static final Map<Class<?>, Field> EMBEDDED_ID_FIELDS = new HashMap<>();

    private static Field embeddedIdField(final Class<?> entityClass) {
        return EMBEDDED_ID_FIELDS.computeIfAbsent(entityClass, k -> {
            for (var c = k; c != null; c = c.getSuperclass()) {
                for (var field : c.getDeclaredFields()) {
                    if (!field.getType().equals(Long.class)) {
                        continue;
                    }
                    if (!field.isAnnotationPresent(EmbeddedId.class)) {
                        continue;
                    }
                    field.setAccessible(true);
                    return field;
                }
            }
            throw new IllegalArgumentException("no @EmbeddedId field onward " + entityClass);
        });
    }

    private static final Map<Class<?>, MethodHandles.Lookup> PRIVATE_LOOKUPS = new HashMap<>();

    static MethodHandles.Lookup privateLookup(final Class<?> clazz) {
        return PRIVATE_LOOKUPS.computeIfAbsent(clazz, k -> {
            try {
                return MethodHandles.privateLookupIn(k, MethodHandles.lookup());
            } catch (final IllegalAccessException iae) {
                throw new RuntimeException("failed to get lookup for " + k, iae);
            }
        });
    }

    private static final Map<Class<?>, VarHandle> EMBEDDED_ID_VAR_HANDLES = new HashMap<>();

    private static VarHandle embeddedIdVarHandle(final Class<?> clazz) {
        return EMBEDDED_ID_VAR_HANDLES.computeIfAbsent(clazz, k -> {
            final var field = embeddedIdField(k);
            assert field.isAccessible();
            try {
                return privateLookup(field.getDeclaringClass()).unreflectVarHandle(field);
            } catch (final IllegalAccessException iae) {
                throw new RuntimeException("failed to unreflect " + field, iae);
            }
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({"unchecked"})
    static <ENTITY extends __MappedEntity<ENTITY, ID>, ID extends Serializable>
    ID getEmbeddedId(@Nonnull final Class<ENTITY> entityClass, @Nonnull final ENTITY entityInstance) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return (ID) embeddedIdVarHandle(entityClass).get(entityInstance);
        }
        final var field = embeddedIdField(entityClass);
        assert field.isAccessible();
        try {
            return (ID) field.get(entityInstance);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException("failed to get " + field + " from " + entityInstance, iae);
        }
    }

    private static <ENTITY extends __MappedEntity<ENTITY, ID>, ID extends Serializable>
    ID getEmbeddedIdHelper(@Nonnull final Class<ENTITY> entityClass, @Nonnull final Object entityInstance) {
        return getEmbeddedId(entityClass, entityClass.cast(entityInstance));
    }

    @SuppressWarnings({"unchecked"})
    static <ENTITY extends __MappedEntity<ENTITY, ID>, ID extends Serializable>
    ID getEmbeddedId(@Nonnull final ENTITY entityInstance) {
        return getEmbeddedIdHelper((Class<ENTITY>) entityInstance.getClass(), entityInstance);
    }

    static <ENTITY extends __MappedEntity<ENTITY, ID>, ID extends Serializable>
    void setEmbeddedId(@Nonnull final Class<ENTITY> entityClass, @Nonnull final ENTITY entityInstance,
                       @Nullable final ID id) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            embeddedIdVarHandle(entityClass).set(entityInstance, id);
            return;
        }
        final var field = embeddedIdField(entityClass);
        assert field.isAccessible();
        try {
            field.set(entityInstance, id);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException("failed to set " + field + " to " + entityInstance, iae);
        }
    }

    static <ENTITY extends __MappedEntity<ENTITY, ID>, ID extends Serializable>
    void setEmbeddedIdHelper(@Nonnull final Class<ENTITY> entityClass, @Nonnull final Object entityInstance,
                             @Nullable final ID id) {
        setEmbeddedId(entityClass, entityClass.cast(entityInstance), id);
    }

    @SuppressWarnings({"unchecked"})
    static <ENTITY extends __MappedEntity<ENTITY, ID>, ID extends Serializable>
    void setEmbeddedId(@Nonnull final ENTITY entityInstance, final ID id) {
        setEmbeddedIdHelper(entityInstance.getClass(), entityInstance, id);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @jakarta.annotation.Nonnull
    public static <
            ENTITY extends __MappedEntity<ENTITY, ID>,
            ID extends Serializable
            >
    ENTITY of(@jakarta.annotation.Nonnull final Supplier<? extends ENTITY> supplier,
              @jakarta.annotation.Nullable final ID id) {
        Objects.requireNonNull(supplier, "supplier is null");
        final var instance = Objects.requireNonNull(supplier.get(), "null supplied from " + supplier);
        instance._id_(id);
        return instance;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    private __MappedEntityUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
