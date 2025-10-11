package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@MappedSuperclass
public abstract class MappedEmployee extends _MappedHrEntity<Integer> {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "EMPLOYEES";

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID
    public static final String COLUMN_NAME_EMPLOYEE_ID = "EMPLOYEE_ID";

    public static final int COLUMN_PRECISION_EMPLOYEE_ID = 6;

    public static final int COLUMN_VALUE_MIN_EMPLOYEE_ID = 0xFF_F0_BD_C1; // -999999

    public static final int COLUMN_VALUE_MAX_EMPLOYEE_ID = 0x00_0F_42_3F; // +999999

    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = "employeeId";

    public static final int MIN_EMPLOYEE_ID = COLUMN_VALUE_MIN_EMPLOYEE_ID;

    public static final int MAX_EMPLOYEE_ID = COLUMN_VALUE_MAX_EMPLOYEE_ID;

    // ------------------------------------------------------------------------------------------ FIRST_NAME / firstName
    public static final String COLUMN_NAME_FIRST_NAME = "FIRST_NAME";

    public static final int COLUMN_LENGTH_FIRST_NAME = 20;

    public static final String ATTRIBUTE_NAME_FIRST_NAME = "firstName";

    public static final int SIZE_MAX_FIRST_NAME = COLUMN_LENGTH_FIRST_NAME;

    // ---------=---------------------------------------------------------------------------------- LAST_NAME / lastName
    public static final String COLUMN_NAME_LAST_NAME = "LAST_NAME";

    public static final int COLUMN_LENGTH_LAST_NAME = 20;

    public static final String ATTRIBUTE_NAME_LAST_NAME = "lastName";

    public static final int SIZE_MAX_LAST_NAME = COLUMN_LENGTH_LAST_NAME;

    // --------------------------------------------------------------------------------------------------- EMAIL / email
    public static final String COLUMN_NAME_EMAIL = "EMAIL";

    public static final int COLUMN_LENGTH_EMAIL = 25;

    public static final String ATTRIBUTE_NAME_EMAIL = "email";

    public static final int SIZE_MAX_EMAIL = COLUMN_LENGTH_EMAIL;

    // ---------------------------------------------------------------------------------------------------- PHONE_NUMBER
    public static final String COLUMN_NAME_PHONE_NUMBER = "PHONE_NUMBER";

    public static final int COLUMN_LENGTH_PHONE_NUMBER = 20;

    public static final String ATTRIBUTE_NAME_PHONE_NUMBER = "phoneNumber";

    public static final int SIZE_MAX_PHONE_NUMBER = COLUMN_LENGTH_EMAIL;

    // -------------------------------------------------------------------------------------------- HIRE_DATE / hireDate
    public static final String COLUMN_NAME_HIRE_DATE = "HIRE_DATE";

    public static final String ATTRIBUTE_NAME_HIRE_DATE = "hireDate";

    // -------------------------------------------------------------------------------------------------- JOB_ID / jobId
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    public static final int COLUMN_LENGTH_JOB_ID = 10;

    public static final String ATTRIBUTE_NAME_JOB_ID = "jobId";

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

    // ------------------------------------------------------------------------------------------- MANAGER_ID / manageId
    public static final String COLUMN_NAME_MANAGER_ID = "MANAGER_ID";

    public static final int COLUMN_PRECISION_MANAGER_ID = 6;

    public static final int COLUMN_MIN_MANAGER_ID = 0x000000;

    public static final int COLUMN_MAX_MANAGER_ID = 0x0F423F;

    public static final String ATTRIBUTE_NAME_MANAGER_ID = "manageId";

    // ------------------------------------------------------------------------------------ DEPARTMENT_ID / departmentId
    public static final String COLUMN_NAME_DEPARTMENT_ID = "DEPARTMENT_ID";

    public static final int COLUMN_PRECISION_DEPARTMENT_ID = 4;

    public static final int COLUMN_MIN_DEPARTMENT_ID = 0x0000;

    public static final int COLUMN_MAX_DEPARTMENT_ID = 0x270F;

    public static final String ATTRIBUTE_NAME_DEPARTMENT_ID = "departmentId";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedEmployee() {
        super();
    }

    protected MappedEmployee(@Nonnull final MappedEmployeeBuilder<?, ?> builder) {
        employeeId = builder.employeeId();
        firstName = builder.firstName();
        lastName = builder.lLastName();
        email = builder.email();
        phoneNumber = builder.phoneNumber();
        hireDate = builder.hireDate();
        jobId = builder.jobId();
        salary = builder.salary();
        commissionPct = builder.commissionPct();
        managerId = builder.managerId();
        departmentId = builder.departmentId();
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
               ",salary=" + salary +
               ",commissionPct=" + commissionPct +
               ",managerId=" + managerId +
               ",departmentId=" + departmentId +
               '}';
    }

    // TODO: add equals/hashCode

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
    @Nonnull
    public String getJobId() {
        return jobId;
    }

    protected void setJobId(@Nonnull final String jobId) {
        this.jobId = jobId;
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
//        if (this.commissionPct != null) {
//            this.commissionPct = this.commissionPct.setScale(COLUMN_SCALE_COMMISSION_PCT, RoundingMode.HALF_UP);
//        }
    }

    // ------------------------------------------------------------------------------------------------------- managerId
    @Nullable
    public Integer getManagerId() {
        return managerId;
    }

    protected void setManagerId(@Nullable final Integer managerId) {
        this.managerId = managerId;
    }

    // ---------------------------------------------------------------------------------------------------- departmentId
    @Nullable
    public Integer getDepartmentId() {
        return departmentId;
    }

    protected void setDepartmentId(@Nullable final Integer departmentId) {
        this.departmentId = departmentId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Max(MAX_EMPLOYEE_ID)
    @Min(MIN_EMPLOYEE_ID)
    @NotNull
    @Id
//    @Basic(optional = false)
    @Column(
            name = COLUMN_NAME_EMPLOYEE_ID,
            nullable = false,
            insertable = true,
            updatable = false,
            precision = COLUMN_PRECISION_EMPLOYEE_ID
    )
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
    @Size(max = SIZE_MAX_EMAIL)
    @NotNull
    @Basic(optional = false)
    @Column(
            name = COLUMN_NAME_EMAIL,
            nullable = false,
            insertable = true,
            updatable = false,
            length = COLUMN_LENGTH_EMAIL,
            unique = true
    )
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
    @Nonnull
    @Size(max = SIZE_MAX_JOB_ID)
    @NotNull
    @Basic(optional = false)
    @Column(
            name = COLUMN_NAME_JOB_ID,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_JOB_ID
    )
    private String jobId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @DecimalMax(value = DECIMAL_MAX_SALARY, inclusive = true)
    @DecimalMin(value = DECIMAL_MIN_SALARY, inclusive = true)
    @Positive
    @Basic(optional = true)
    @Column(
            name = COLUMN_NAME_SALARY,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_SALARY,
            scale = COLUMN_SCALE_SALARY
    )
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
    @Nullable
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(
            name = COLUMN_NAME_MANAGER_ID,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_MANAGER_ID
    )
    private Integer managerId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(
            name = COLUMN_NAME_DEPARTMENT_ID,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_DEPARTMENT_ID
    )
    private Integer departmentId;
}
