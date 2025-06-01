package com.github.jinahya.oracle.sample.schemas;

import java.util.function.Supplier;

public final class _MappedEntityUtils {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    @jakarta.annotation.Nonnull
    public static <
            ENTITY extends _MappedEntity<ENTITY>
            >
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
