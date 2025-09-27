package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.persistence.mapped.__Mapped;
import jakarta.annotation.Nonnull;

@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class _MappedHr implements __Mapped {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedHr() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder to build from.
     */
    protected _MappedHr(@Nonnull final _MappedHrBuilder<?, ?> builder) {
        super();
    }
}
