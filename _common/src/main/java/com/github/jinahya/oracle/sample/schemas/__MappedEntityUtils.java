package com.github.jinahya.oracle.sample.schemas;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Supplier;

public final class __MappedEntityUtils {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    private __MappedEntityUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
