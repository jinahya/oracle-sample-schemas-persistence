package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartment;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJob;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistoryWithIdClass;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistoryWithIdClass_;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Comparator;

@NamedQuery(name = "JobHistory.selectList_WhereEmployeeEqualTo_OrderByStartDateDesc",
            query = """
                    SELECT e
                    FROM JobHistory e
                    WHERE e.employee = :employee"""
)
@NamedQuery(name = "JobHistory.selectList_WhereEmployeeIdEqualTo_OrderByStartDateDesc",
            query = """
                    SELECT e
                    FROM JobHistory e
                    WHERE e.employeeId = :employeeId"""
)
@Entity(name = MappedJobHistory.ENTITY_NAME)
@Table(name = MappedJobHistory.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               MappedJobHistory.COLUMN_NAME_EMPLOYEE_ID,
                               MappedJobHistory.COLUMN_NAME_START_DATE
                       }
               )
       }
)
class JobHistoryWithIdClass extends MappedJobHistoryWithIdClass {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A comparator for comparing {@link JobHistoryWithIdClass} instances by their
     * {@value MappedJobHistoryWithIdClass_#startDate} attributes.
     */
    public static final Comparator<JobHistoryWithIdClass> COMPARING_START_DATE = comparingStartDate();

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected JobHistoryWithIdClass() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    // ------------------------------------------------------------------------------------------------ super.employeeId

    // ------------------------------------------------------------------------------------------------- super.startDate

    // --------------------------------------------------------------------------------------------------- super.endDate

    // ----------------------------------------------------------------------------------------------------- super.jobId

    // ---------------------------------------------------------------------------------------------- super.departmentId

    // -------------------------------------------------------------------------------------------------------- employee

    /**
     * Returns current value of {@link JobHistoryWithIdClass_#EMPLOYEE} attribute.
     *
     * @return current value of the {@link JobHistoryWithIdClass_#EMPLOYEE} attribute.
     */
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
                referencedColumnName = MappedEmployee.COLUMN_NAME_DEPARTMENT_ID,
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
                referencedColumnName = MappedJob.COLUMN_NAME_JOB_ID,
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
                referencedColumnName = MappedDepartment.COLUMN_NAME_DEPARTMENT_ID,
                nullable = COLUMN_NULLABLE_DEPARTMENT_ID,
                insertable = false,
                updatable = false
    )
    private Department department;
}
