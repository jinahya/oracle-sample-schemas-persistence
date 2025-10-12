package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

import com.github.jinahya.persistence.mapped.__Mapped;
import jakarta.annotation.Nonnull;

import java.util.Objects;

@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class _MappedSh implements __Mapped {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedSh() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder to build from.
     */
    protected _MappedSh(@Nonnull final _MappedShBuilder<?, ?> builder) {
        super();
        Objects.requireNonNull(builder, "builder is null");
    }
}
