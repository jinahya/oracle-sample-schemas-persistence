package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__Mapped;

/**
 * An abstract super-class for the {@code CO} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class _MappedCo implements __Mapped {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedCo() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder to build from.
     */
    protected _MappedCo(final _MappedCoBuilder<?, ?> builder) {
        super();
    }
}
