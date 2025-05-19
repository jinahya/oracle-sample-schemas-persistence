package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = Employee.TABLE_NAME)
public class Employee extends __MappedEntity<Employee, Integer> {

    @Serial
    private static final long serialVersionUID = -9109661411050742306L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "EMPLOYEES";

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID
    public static final String COLUMN_NAME_EMPLOYEE_ID = "EMPLOYEE_ID";

    public static final int COLUMN_PRECISION_EMPLOYEE_ID = 4;

    public static final int COLUMN_VALUE_MIN_EMPLOYEE_ID = 0x0000;

    public static final int COLUMN_VALUE_MAX_EMPLOYEE_ID = 0x270F; // 9999

    public static final int MIN_EMPLOYEE_ID = COLUMN_VALUE_MIN_EMPLOYEE_ID;

    public static final int MAX_EMPLOYEE_ID = COLUMN_VALUE_MAX_EMPLOYEE_ID;

    // ------------------------------------------------------------------------------------------------------ FIRST_NAME
    public static final String COLUMN_NAME_FIRST_NAME = "FIRST_NAME";

    // ------------------------------------------------------------------------------------------------------- LAST_NAME
    public static final String COLUMN_NAME_LAST_NAME = "LAST_NAME";

    // ----------------------------------------------------------------------------------------------------------- EMAIL
    public static final String COLUMN_NAME_EMAIL = "EMAIL";

    public static final int COLUMN_LENGTH_EMAIL = 25;

    public static final int SIZE_MAXL = COLUMN_LENGTH_EMAIL;

    // ---------------------------------------------------------------------------------------------------- PHONE_NUMBER
    public static final String COLUMN_NAME_PHONE_NUMBER = "PHONE_NUMBER";

    // ------------------------------------------------------------------------------------------------------- HIRE_DATE
    public static final String COLUMN_NAME_HIRE_DATE = "HIRE_DATE";

    // ---------------------------------------------------------------------------------------------------------- JOB_ID
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    public static final int COLUMN_LENGTH_JOB_ID = 10;

    public static final int SIZE_MAX_JOB_ID = COLUMN_LENGTH_JOB_ID;

    // ---------------------------------------------------------------------------------------------------------- SALARY
    public static final String COLUMN_NAME_SALARY = "SALARY";

    public static final int COLUMN_PRECISION_SALARY = 8;

    public static final int COLUMN_SCALE_SALARY = 2;

    public static final double COLUMN_VALUE_MIN_SALARY = 0000000.00d;

    public static final double COLUMN_VALUE_MAX_SALARY = 999999.99d;

    public static final String DECIMAL_MIN_SALARY = "000000.00";

    public static final String DECIMAL_MAX_SALARY = "999999.99";

    static {
        assert new BigDecimal(DECIMAL_MIN_SALARY).compareTo(BigDecimal.valueOf(COLUMN_VALUE_MIN_SALARY)) == 0;
        assert new BigDecimal(DECIMAL_MAX_SALARY).compareTo(BigDecimal.valueOf(COLUMN_VALUE_MAX_SALARY)) == 0;
    }

    // -------------------------------------------------------------------------------------------------- COMMISSION_PCT
    public static final String COLUMN_NAME_COMMISSION_PCT = "COMMISSION_PCT";

    public static final int COLUMN_PRECISION_COMMISSION_PCT = 2;

    public static final int COLUMN_SCALE_COMMISSION_PCT = 2;

    public static final double COLUMN_VALUE_MIN_COMMISSION_PCT = .00d;

    public static final double COLUMN_VALUE_MAX_COMMISSION_PCT = .99d;

    public static final String DECIMAL_MIN_COMMISSION_PCT = ".00";

    public static final String DECIMAL_MAX_COMMISSION_PCT = ".99";

    static {
        assert new BigDecimal(DECIMAL_MIN_COMMISSION_PCT).compareTo(
                BigDecimal.valueOf(COLUMN_VALUE_MIN_COMMISSION_PCT)) == 0;
        assert new BigDecimal(DECIMAL_MAX_COMMISSION_PCT).compareTo(
                BigDecimal.valueOf(COLUMN_VALUE_MAX_COMMISSION_PCT)) == 0;
    }

    // ------------------------------------------------------------------------------------------------------ MANAGER_ID
    public static final String COLUMN_NAME_MANAGER_ID = "MANAGER_ID";

    public static final int COLUMN_PRECISION_MANAGER_ID = 6;

    public static final int COLUMN_VALUE_MIN_MANAGER_ID = 0x000000;

    public static final int COLUMN_VALUE_MAX_MANAGER_ID = 0x0F423F;

    // --------------------------------------------------------------------------------------------------- DEPARTMENT_ID
    public static final String COLUMN_NAME_DEPARTMENT_ID = "DEPARTMENT_ID";

    public static final int COLUMN_PRECISION_DEPARTMENT_ID = 4;

    public static final int COLUMN_VALUE_MIN_DEPARTMENT_ID = 0x0000;

    public static final int COLUMN_VALUE_MAX_DEPARTMENT_ID = 0x270F;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Employee() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
                "employeeId=" + employeeId +
                ",firstName=" + firstName +
                ",lastName=" + lastName +
                ",email=" + email +
                ",phoneNumber=" + phoneNumber +
                ",hireDate=" + hireDate +
                ",jobId=" + jobId +
//                ",job=" + job +
                ",salary=" + salary +
                ",commissionPct=" + commissionPct +
                ",managerId=" + managerId +
//                ",manager=" + manager +
                ",departmentId=" + departmentId +
//                ",department=" + department +
                '}';
    }

    // ------------------------------------------------------------------------------------------------------ super._id_
    @Override
    protected final Integer _id_() {
        return getEmployeeId();
    }

    @Override
    protected final void _id_(final Integer _id_) {
        setEmployeeId(_id_);
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    // ------------------------------------------------------------------------------------------------------ employeeId
    @Nonnull
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(@Nonnull final Integer employeeId) {
        this.employeeId = employeeId;
    }

    // ------------------------------------------------------------------------------------------------------- firstName
    @Nullable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Nullable final String firstName) {
        this.firstName = firstName;
    }

    // -------------------------------------------------------------------------------------------------------- lastName
    @Nonnull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@Nonnull final String lastName) {
        this.lastName = lastName;
    }

    // ----------------------------------------------------------------------------------------------------------- email
    @Nonnull
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nonnull final String email) {
        this.email = email;
    }

    // ----------------------------------------------------------------------------------------------------- phoneNumber
    @Nullable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Nullable final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // -------------------------------------------------------------------------------------------------------- hireDate
    @Nonnull
    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(@Nonnull final LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    // ----------------------------------------------------------------------------------------------------------- jobId
    @Deprecated(forRemoval = true)
    @Nonnull
    String getJobId() {
        return jobId;
    }

    @Deprecated(forRemoval = true)
    void setJobId(@Nonnull final String jobId) {
        this.jobId = jobId;
    }

    // ------------------------------------------------------------------------------------------------------------- job
    @Nonnull
    public Job getJob() {
        return job;
    }

    public void setJob(@Nonnull final Job job) {
        this.job = job;
        setJobId(
                Optional.ofNullable(this.job)
                        .map(Job::getJobId)
                        .orElse(null)
        );
    }

    // ---------------------------------------------------------------------------------------------------------- salary
    @Nullable
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(@Nullable final BigDecimal salary) {
        this.salary = salary;
    }

    // --------------------------------------------------------------------------------------------------- commissionPct
    @Nullable
    public BigDecimal getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(@Nullable final BigDecimal commissionPct) {
        this.commissionPct = commissionPct;
        if (this.commissionPct != null) {
            this.commissionPct = this.commissionPct.setScale(COLUMN_SCALE_COMMISSION_PCT, BigDecimal.ROUND_HALF_UP);
//            if (this.commissionPct.compareTo(COLUMN_VALUE_MIN_COMMISSION_PCT) < 0) {
//                this.commissionPct = COLUMN_VALUE_MIN_COMMISSION_PCT;
//            } else if (this.commissionPct.compareTo(COLUMN_VALUE_MAX_COMMISSION_PCT) > 0) {
//                this.commissionPct = COLUMN_VALUE_MAX_COMMISSION_PCT;
//            }
        }
    }

    // Only employees in sales department eligible for commission percentage
    @Transient
    public boolean isEligibleForCommissionPercentage() {
        // TODO: implement 
        throw new UnsupportedOperationException("not yet implemented");
    }

    // ------------------------------------------------------------------------------------------------------- managerId
    @Deprecated(forRemoval = true)
    @Nullable
    Integer getManagerId() {
        return managerId;
    }

    @Deprecated(forRemoval = true)
    void setManagerId(@Nullable final Integer managerId) {
        this.managerId = managerId;
    }

    // --------------------------------------------------------------------------------------------------------- manager
    @Nullable
    public Employee getManager() {
        return manager;
    }

    public void setManager(@Nullable final Employee manager) {
        this.manager = manager;
        setManagerId(
                Optional.ofNullable(this.manager)
                        .map(Employee::getEmployeeId)
                        .orElse(null)
        );
    }

    // ---------------------------------------------------------------------------------------------------- departmentId
    @Deprecated(forRemoval = true)
    @Nullable
    Integer getDepartmentId() {
        return departmentId;
    }

    @Deprecated(forRemoval = true)
    void setDepartmentId(@Nullable final Integer departmentId) {
        this.departmentId = departmentId;
    }

    // ------------------------------------------------------------------------------------------------------ department
    @Nullable
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(@Nullable final Department department) {
        this.department = department;
        setDepartmentId(
                Optional.ofNullable(this.department)
                        .map(Department::getDepartmentId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Max(MAX_EMPLOYEE_ID)
    @Min(MIN_EMPLOYEE_ID)
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EMPLOYEE_ID, nullable = false, insertable = true, updatable = false,
            precision = COLUMN_PRECISION_EMPLOYEE_ID)
    private Integer employeeId;

    @Nullable
    @Size(max = 20)
    @Basic(optional = true)
    @Column(name = "FIRST_NAME", nullable = true, insertable = true, updatable = true, length = 20)
    private String firstName;

    @Nonnull
    @Size(max = 25)
    @NotNull
    @Basic(optional = false)
    @Column(name = "LAST_NAME", nullable = false, insertable = true, updatable = true, length = 25)
    private String lastName;

    @Nonnull
    @Size(max = SIZE_MAXL)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EMAIL, nullable = false, insertable = true, updatable = true, unique = true,
            length = COLUMN_LENGTH_EMAIL)
    private String email;

    @Nullable
    @Basic(optional = true)
    @Column(name = "PHONE_NUMBER", nullable = true, insertable = true, updatable = true, length = 20)
    private String phoneNumber;

    @Nonnull
    //    @PastOrPresent
    @NotNull
    @Basic(optional = false)
    @Column(name = "HIRE_DATE", nullable = false, insertable = true, updatable = true)
    private LocalDate hireDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    @Nonnull
    @Size(max = SIZE_MAX_JOB_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_JOB_ID, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_JOB_ID)
    private String jobId;

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_JOB_ID, nullable = false, insertable = false, updatable = false)
    private Job job;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @DecimalMax(value = DECIMAL_MAX_SALARY, inclusive = true)
    @DecimalMin(value = DECIMAL_MIN_SALARY, inclusive = true)
    @Positive
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SALARY, nullable = true, insertable = true, updatable = true,
            precision = COLUMN_PRECISION_SALARY, scale = COLUMN_SCALE_SALARY)
    private BigDecimal salary;

    @DecimalMax(value = DECIMAL_MAX_COMMISSION_PCT, inclusive = true)
    @DecimalMin(value = DECIMAL_MIN_COMMISSION_PCT, inclusive = true)
    @PositiveOrZero
    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMMISSION_PCT, nullable = true, insertable = true, updatable = true,
            precision = COLUMN_PRECISION_COMMISSION_PCT,
            scale = COLUMN_SCALE_COMMISSION_PCT)
    private BigDecimal commissionPct;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MANAGER_ID, nullable = true, insertable = true, updatable = true, precision = 6)
    private Integer managerId;

    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_MANAGER_ID, nullable = true, insertable = false, updatable = false)
    private Employee manager;

    @OneToMany(
            mappedBy = "manager",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST,
                    CascadeType.REFRESH,
//            CascadeType.REMOVE
            }
    )
    private List<@Valid @NotNull Employee> subordinates;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DEPARTMENT_ID, nullable = true, insertable = true, updatable = true, precision = 4)
    private Integer departmentId;

    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_DEPARTMENT_ID, nullable = true, insertable = false, updatable = false)
    private Department department;
}