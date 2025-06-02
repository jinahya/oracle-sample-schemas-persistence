package com.github.jinahya.oracle.sample.schemas;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Id;

import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public final class _MappedEntityUtils {

    private static final Map<Class<?>, Field> ID_FIELDS = new HashMap<>();

    private static Field getIdField(final Class<?> entityClass) {
        return ID_FIELDS.computeIfAbsent(entityClass, k -> {
            for (var c = k; c != null; c = c.getSuperclass()) {
                for (var field : c.getDeclaredFields()) {
                    if (!field.getType().equals(Long.class)) {
                        continue;
                    }
                    if (!field.isAnnotationPresent(Id.class)) {
                        continue;
                    }
                    field.setAccessible(true);
                    return field;
                }
            }
            throw new IllegalArgumentException("no @Id field onward " + entityClass);
        });
    }

    private static final Map<Class<?>, VarHandle> ID_VAR_HANDLES = new HashMap<>();

    private static VarHandle idVarHandle(final Class<?> clazz) {
        return ID_VAR_HANDLES.computeIfAbsent(clazz, k -> {
            final var field = getIdField(k);
            assert field.isAccessible();
            try {
//                return __MappedEntityUtils.privateLookup(field.getDeclaringClass()).unreflectVarHandle(field);
                return __MappedEntityUtils.privateLookup(k).unreflectVarHandle(field);
            } catch (final IllegalAccessException iae) {
                throw new RuntimeException("failed to unreflect " + field, iae);
            }
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    static <ENTITY extends _MappedEntity<ENTITY>>
    Long getId(@Nonnull final Class<ENTITY> entityClass, @Nonnull final ENTITY entityInstance) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return (Long) idVarHandle(entityClass).get(entityInstance);
        }
        final var field = getIdField(entityClass);
        assert field.isAccessible();
        try {
            return (Long) field.get(entityInstance);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException("failed to get " + field + " from " + entityInstance, iae);
        }
    }

    private static <ENTITY extends _MappedEntity<ENTITY>>
    Long getIdHelper(@Nonnull final Class<ENTITY> entityClass, @Nonnull final Object entityInstance) {
        return getId(entityClass, entityClass.cast(entityInstance));
    }

    @SuppressWarnings({"unchecked"})
    static <ENTITY extends _MappedEntity<ENTITY>>
    Long getId(@Nonnull final ENTITY entityInstance) {
        return getIdHelper(entityInstance.getClass(), entityInstance);
    }

    static <ENTITY extends _MappedEntity<ENTITY>>
    void setId(@Nonnull final Class<ENTITY> entityClass, @Nonnull final ENTITY entityInstance,
               @Nullable final Long id) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            idVarHandle(entityClass).set(entityInstance, id);
            return;
        }
        final var field = getIdField(entityClass);
        assert field.isAccessible();
        try {
            field.set(entityInstance, id);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException("failed to set " + field + " to " + entityInstance, iae);
        }
    }

    static <ENTITY extends _MappedEntity<ENTITY>>
    void setIdHelper(@Nonnull final Class<ENTITY> entityClass, @Nonnull final Object entityInstance,
                     @Nullable final Long id) {
        setId(entityClass, entityClass.cast(entityInstance), id);
    }

    @SuppressWarnings({"unchecked"})
    static <ENTITY extends _MappedEntity<ENTITY>>
    void setId(@Nonnull final ENTITY entityInstance, final Long id) {
        setIdHelper(entityInstance.getClass(), entityInstance, id);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @jakarta.annotation.Nonnull
    public static <ENTITY extends _MappedEntity<ENTITY>>
    ENTITY of(@jakarta.annotation.Nonnull final Supplier<? extends ENTITY> supplier,
              @jakarta.annotation.Nullable final Long id) {
        return __MappedEntityUtils.of(supplier, id);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    private _MappedEntityUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
