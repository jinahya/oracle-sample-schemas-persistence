package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import java.time.LocalDate;

@SuppressWarnings({
        "unchecked"
})
public abstract class MappedJobHistoryIdBuilder<
        SELF extends MappedJobHistoryIdBuilder<SELF, TARGET>,
        TARGET extends MappedJobHistoryId
        >
        extends _MappedHrBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    MappedJobHistoryIdBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------------ employeeId
    public Integer employeeId() {
        return employeeId;
    }

    public SELF employeeId(final Integer employeeId) {
        this.employeeId = employeeId;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- startDate
    public LocalDate startDate() {
        return startDate;
    }

    public SELF startDate(final LocalDate startDate) {
        this.startDate = startDate;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Integer employeeId;

    private LocalDate startDate;
}
