package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class MappedJobHistoryId
        extends _MappedHr
        implements Serializable {

    @Serial
    private static final long serialVersionUID = -537237161047866359L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedJobHistoryId() {
        super();
    }

    MappedJobHistoryId(@Nonnull final MappedJobHistoryIdBuilder<?, ?> builder) {
        super(builder);
        employeeId = builder.employeeId();
        startDate = builder.startDate();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "employeeId=" + employeeId +
               ",startDate=" + startDate +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedJobHistoryId that)) {
            return false;
        }
        return Objects.equals(employeeId, that.employeeId)
               && Objects.equals(startDate, that.startDate);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(employeeId, startDate);
    }

    // ------------------------------------------------------------------------------------------------------ employeeId
    @Nonnull
    public Integer getEmployeeId() {
        return employeeId;
    }

    protected void setEmployeeId(@Nonnull final Integer employeeId) {
        this.employeeId = employeeId;
    }

    // ------------------------------------------------------------------------------------------------------- startDate
    @Nonnull
    public LocalDate getStartDate() {
        return startDate;
    }

    protected void setStartDate(@Nonnull final LocalDate startDate) {
        this.startDate = startDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
//    @Max(MappedJobHistory.MAX_EMPLOYEE_ID)
//    @Min(MappedJobHistory.MIN_EMPLOYEE_ID)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(
            name = MappedJobHistory.COLUMN_NAME_EMPLOYEE_ID,
            nullable = false,
            insertable = false,
            updatable = false
//            ,
//            precision = MappedJobHistory.COLUMN_PRECISION_EMPLOYEE_ID,
//            scale = MappedJobHistory.COLUMN_SCALE_EMPLOYEE_ID
    )
    private Integer employeeId;

    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = MappedJobHistory.COLUMN_NAME_START_DATE, nullable = false, insertable = false, updatable = false)
    private LocalDate startDate;
}
