package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * An entity class for mapping {@value JobHistory#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = JobHistory.TABLE_NAME)
public class JobHistory extends __MappedEntity<JobHistoryId> {

    @Serial
    private static final long serialVersionUID = -6473751636008005001L;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "JOB_HISTORY";

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID

    /**
     * The name of the table column to which the {@link JobHistoryId_#employeeId id.eployeeId} attribute maps. The value
     * is {@value}.
     *
     * @see JobHistoryId
     */
    public static final String COLUMN_NAME_EMPLOYEE_ID = "EMPLOYEE_ID";

    public static final int COLUMN_PRECISION_EMPLOYEE_ID = 6;

    public static final int COLUMN_VALUE_MIN_EMPLOYEE_ID = 0x000000;

    public static final int COLUMN_VALUE_MAX_EMPLOYEE_ID = 0x0F423F; // 999999

    public static final int MIN_EMPLOYEE_ID = COLUMN_VALUE_MIN_EMPLOYEE_ID;

    public static final int MAX_EMPLOYEE_ID = COLUMN_VALUE_MAX_EMPLOYEE_ID;

    // ------------------------------------------------------------------------------------------------------ START_DATE

    /**
     * The name of the table column to which the {@link JobHistoryId_#startDate id.startDate} attribute maps. The value
     * is {@value}.
     *
     * @see JobHistoryId
     */
    public static final String COLUMN_NAME_START_DATE = "START_DATE";

    // -------------------------------------------------------------------------------------------------------- END_DATE

    /**
     * The name of the table column to which the {@link JobHistory_#endDate endDate} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_END_DATE = "END_DATE";

    // ---------------------------------------------------------------------------------------------------------- JOB_ID
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    // --------------------------------------------------------------------------------------------------- DEPARTMENT_ID
    public static final String COLUMN_NAME_DEPARTMENT_ID = "DEPARTMENT_ID";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected JobHistory() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
                "id=" + id +
//                ",employee=" + employee +
                ",endDate=" + endDate +
                ",jobId=" + jobId +
//                ",job=" + job +
                ",departmentId=" + departmentId +
//                ",department=" + department +
                '}';
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation
    @AssertTrue
    private boolean isIdStartDateIsBeforeEndDate() {
        final var idStartDate = Optional.ofNullable(getId()).map(JobHistoryId::getStartDate).orElse(null);
        final var endDate = getEndDate();
        return idStartDate == null || endDate == null || idStartDate.isBefore(endDate);
    }

    // -------------------------------------------------------------------------------------------------------------- id
    @Nonnull
    public JobHistoryId getId() {
        return id;
    }

    public void setId(@Nonnull final JobHistoryId id) {
        this.id = id;
    }

    @Nonnull
    protected JobHistoryId getIdOrElseSetSuppliedAndGet(@Nonnull final Supplier<? extends JobHistoryId> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        return Optional.ofNullable(getId()).orElseGet(() -> {
            setId(Objects.requireNonNull(supplier.get(), "null supplied"));
            return getId();
        });
    }

    // -------------------------------------------------------------------------------------------------------- employee
    @Nonnull
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(@Nonnull final Employee employee) {
        this.employee = employee;
        if (false) { // @MapsId
            getIdOrElseSetSuppliedAndGet(JobHistoryId::new).setEmployeeId(
                    Optional.ofNullable(this.employee)
                            .map(Employee::getEmployeeId)
                            .orElse(null)
            );
        }
    }

    // --------------------------------------------------------------------------------------------------------- endDate
    @Nonnull
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@Nonnull final LocalDate endDate) {
        this.endDate = endDate;
    }

    // ----------------------------------------------------------------------------------------------------------- jobId
    @Deprecated(forRemoval = true)
    @Nonnull
    public String getJobId() {
        return jobId;
    }

    @Deprecated(forRemoval = true)
    public void setJobId(@Nonnull final String jobId) {
        this.jobId = jobId;
    }

    // ------------------------------------------------------------------------------------------------------------- job
    @Nonnull
    public MappedJob getJob() {
        return job;
    }

    public void setJob(@Nonnull final MappedJob job) {
        this.job = job;
        setJobId(
                Optional.ofNullable(this.job)
                        .map(MappedJob::getJobId)
                        .orElse(null)
        );
    }

    // ---------------------------------------------------------------------------------------------------- departmentId
    @Deprecated(forRemoval = true)
    @Nonnull
    public Integer getDepartmentId() {
        return departmentId;
    }

    @Deprecated(forRemoval = true)
    public void setDepartmentId(@Nonnull final Integer departmentId) {
        this.departmentId = departmentId;
    }

    // ------------------------------------------------------------------------------------------------------ department
    @Nonnull
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(@Nonnull final Department department) {
        this.department = department;
        setDepartmentId(
                Optional.ofNullable(this.department)
                        .map(Department::getDepartmentId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @EmbeddedId
    private JobHistoryId id;

    @Nonnull
    @NotNull
    @MapsId("employeeId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_EMPLOYEE_ID, nullable = false, insertable = false, updatable = false)
    private Employee employee;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_END_DATE, nullable = false, insertable = true, updatable = false)
    private LocalDate endDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    @Nonnull
    @Size(max = 20)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_JOB_ID, nullable = false, insertable = true, updatable = false, length = 20)
    private String jobId;

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_JOB_ID, nullable = false, insertable = false, updatable = false)
    private MappedJob job;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DEPARTMENT_ID, nullable = false, insertable = true, updatable = false, precision = 4)
    private Integer departmentId;

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_DEPARTMENT_ID, nullable = false, insertable = false, updatable = false)
    private Department department;
}
