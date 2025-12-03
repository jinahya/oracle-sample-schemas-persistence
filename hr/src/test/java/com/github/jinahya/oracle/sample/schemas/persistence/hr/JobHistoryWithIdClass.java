package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistoryWithIdClass;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@NamedQuery(name = "JobHistoryWithIdClass.selectList_WhereEmployeeEqualTo_OrderByStartDateDesc",
            query = """
                    SELECT e
                    FROM JobHistoryWithIdClass e
                    WHERE e.employee = :employee"""
)
@NamedQuery(name = "JobHistoryWithIdClass.selectList_WhereEmployeeIdEqualTo_OrderByStartDateDesc",
            query = """
                    SELECT e
                    FROM JobHistoryWithIdClass e
                    WHERE e.employeeId = :employeeId"""
)
@Entity
@Table(name = MappedJobHistory.TABLE_NAME)
class JobHistoryWithIdClass extends MappedJobHistoryWithIdClass {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected JobHistoryWithIdClass() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final boolean equals(final Object obj) {
        return equalsWithEmployeeIdAndStartDate(obj);
    }

    @Override
    public final int hashCode() {
        return hashCodeWithEmployeeIdAndStartDate();
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    // ------------------------------------------------------------------------------------------------ super.employeeId
    @Nonnull
    @Override
    public Integer getEmployeeId() {
        return super.getEmployeeId();
    }

    // ------------------------------------------------------------------------------------------------- super.startDate

    // --------------------------------------------------------------------------------------------------- super.endDate

    // ----------------------------------------------------------------------------------------------------- super.jobId

    // ---------------------------------------------------------------------------------------------- super.departmentId

    // -------------------------------------------------------------------------------------------------------- employee
    @Nonnull
    public Employee getEmployee() {
        return employee;
    }

    // ------------------------------------------------------------------------------------------------------------- job
    @Nonnull
    public Job getJob() {
        return job;
    }

    // ------------------------------------------------------------------------------------------------------ department
    @Nullable
    public Department getDepartment() {
        return department;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_EMPLOYEE_ID,
                nullable = COLUMN_NULLABLE_EMPLOYEE_ID,
                insertable = false,
                updatable = false
    )
    private Employee employee;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_JOB_ID,
                nullable = COLUMN_NULLABLE_JOB_ID,
                insertable = false,
                updatable = false
    )
    private Job job;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_DEPARTMENT_ID,
                nullable = COLUMN_NULLABLE_DEPARTMENT_ID,
                insertable = false,
                updatable = false
    )
    private Department department;
}
