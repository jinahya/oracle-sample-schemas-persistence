package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class JobHistoryId implements Serializable {

    @Serial
    private static final long serialVersionUID = -5083671108409053960L;

    // -----------------------------------------------------------------------------------------------------------------
    public JobHistoryId() {
        super();
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
    public boolean equals(final Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        JobHistoryId that = (JobHistoryId) obj;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, startDate);
    }

    // ------------------------------------------------------------------------------------------------------ employeeId
    @Nonnull
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(@Nonnull final Integer employeeId) {
        this.employeeId = employeeId;
    }

    // ------------------------------------------------------------------------------------------------------- startDate
    @Nonnull
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@Nonnull final LocalDate startDate) {
        this.startDate = startDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Max(JobHistory.MAX_EMPLOYEE_ID)
    @Min(JobHistory.MIN_EMPLOYEE_ID)
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = JobHistory.COLUMN_NAME_EMPLOYEE_ID, nullable = false, insertable = true, updatable = false)
    private Integer employeeId;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = JobHistory.COLUMN_NAME_START_DATE, nullable = false, insertable = true, updatable = false)
    private LocalDate startDate;
}
