package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartment;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJob;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistoryWithEmbeddedId;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

@Entity
@Table(name = MappedJobHistoryWithEmbeddedId.TABLE_NAME)
class JobHistoryWithEmbeddedId extends MappedJobHistoryWithEmbeddedId {

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected JobHistoryWithEmbeddedId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    // -------------------------------------------------------------------------------------------------------- employee

    // ------------------------------------------------------------------------------------------------------------- job
    @Nonnull
    public Job getJob() {
        return job;
    }

    @Deprecated(forRemoval = true)
    protected void setJob(final @Nonnull Job job) {
        this.job = job;
        setJobId(
                Optional.ofNullable(this.job)
                        .map(MappedJob::getJobId)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------ department
    @Nullable
    public Department getDepartment() {
        return department;
    }

    @Deprecated(forRemoval = true)
    protected void setDepartment(final @Nullable Department department) {
        this.department = department;
        setDepartmentId(
                Optional.ofNullable(this.department)
                        .map(MappedDepartment::getDepartmentId)
                        .orElse(null)

        );
    }

    // -----------------------------------------------------------------------------------------------------------------

    // TODO: map Employee

    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = MappedJobHistoryWithEmbeddedId.COLUMN_NAME_JOB_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Job job;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = MappedJobHistoryWithEmbeddedId.COLUMN_NAME_DEPARTMENT_ID,
                nullable = true,
                insertable = false,
                updatable = false
    )
    private Department department;
}
