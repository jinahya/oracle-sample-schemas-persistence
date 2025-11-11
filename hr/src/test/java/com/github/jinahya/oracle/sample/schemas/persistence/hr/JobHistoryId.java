package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistoryId;
import jakarta.persistence.Embeddable;

import java.io.Serial;

@Embeddable
public class JobHistoryId extends MappedJobHistoryId {

    @Serial
    private static final long serialVersionUID = -3442650400670358668L;

    // -------------------------------------------------------------------------------------------------------- BUILDERS
    public static JobHistoryIdBuilder builder() {
        return new JobHistoryIdBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected JobHistoryId() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder to build from.
     */
    private JobHistoryId(final JobHistoryIdBuilder builder) {
        super(builder);
    }

    // -------------------------------------------------------------------------------------------------------- employee

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: try to map Employee, at here.
}
