package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

/*-
 * #%L
 * hr
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

/**
 * An abstract mapped superclass for mapping {@value MappedEmployee#TABLE_NAME} table.
 *
 * @author Jaehan Lim &lt;gmmail25@gmail.com&gt;
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedEmployee extends _MappedHrEntity<Integer> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity is mapped. The value is {@value}.
     */
    public static final String TABLE_NAME = "EMPLOYEES";

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID
    public static final String COLUMN_NAME_EMPLOYEE_ID = "EMPLOYEE_ID";

    public static final int COLUMN_PRECISION_EMPLOYEE_ID = 6;

    public static final int COLUMN_SCALE_EMPLOYEE_ID = 0;

    public static final int COLUMN_MIN_EMPLOYEE_ID = -999999;

    public static final int COLUMN_MAX_EMPLOYEE_ID = +999999;

    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = "employeeId";

    public static final int ATTRIBUTE_MIN_EMPLOYEE_ID = COLUMN_MIN_EMPLOYEE_ID;

    public static final int ATTRIBUTE_MAX_EMPLOYEE_ID = COLUMN_MAX_EMPLOYEE_ID;

    // ------------------------------------------------------------------------------------------------------ FIRST_NAME
    public static final String COLUMN_NAME_FIRST_NAME = "FIRST_NAME";

    public static final int COLUMN_LENGTH_FIRST_NAME = 20;

    public static final String ATTRIBUTE_NAME_FIRST_NAME = "firstName";

    public static final int ATTRIBUTE_SIZE_MIN_FIRST_NAME = 0;

    public static final int ATTRIBUTE_SIZE_MAX_FIRST_NAME = COLUMN_LENGTH_FIRST_NAME;

    // --------------------=---------------------------------------------------------------------------------- LAST_NAME
    public static final String COLUMN_NAME_LAST_NAME = "LAST_NAME";

    public static final int COLUMN_LENGTH_LAST_NAME = 25;

    public static final String ATTRIBUTE_NAME_LAST_NAME = "lastName";

    public static final int ATTRIBUTE_SIZE_MIN_LAST_NAME = 0;

    public static final int ATTRIBUTE_SIZE_MAX_LAST_NAME = COLUMN_LENGTH_LAST_NAME;

    // ----------------------------------------------------------------------------------------------------------- EMAIL
    public static final String COLUMN_NAME_EMAIL = "EMAIL";

    public static final int COLUMN_LENGTH_EMAIL = 25;

    public static final String ATTRIBUTE_NAME_EMAIL = "email";

    public static final int ATTRIBUTE_SIZE_MIN_EMAIL = 0;

    public static final int ATTRIBUTE_SIZE_MAX_EMAIL = COLUMN_LENGTH_EMAIL;

    // ---------------------------------------------------------------------------------------------------- PHONE_NUMBER
    public static final String COLUMN_NAME_PHONE_NUMBER = "PHONE_NUMBER";

    public static final int COLUMN_LENGTH_PHONE_NUMBER = 20;

    public static final String ATTRIBUTE_NAME_PHONE_NUMBER = "phoneNumber";

    public static final int ATTRIBUTE_SIZE_MIN_PHONE_NUMBER = 0;

    public static final int ATTRIBUTE_SIZE_MAX_PHONE_NUMBER = COLUMN_LENGTH_PHONE_NUMBER;

    // ------------------------------------------------------------------------------------------------------- HIRE_DATE
    public static final String COLUMN_NAME_HIRE_DATE = "HIRE_DATE";

    public static final String ATTRIBUTE_NAME_HIRE_DATE = "hireDate";

    // ---------------------------------------------------------------------------------------------------------- JOB_ID
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    public static final int COLUMN_LENGTH_JOB_ID = 10;

    public static final String ATTRIBUTE_NAME_JOB_ID = "jobId";

    public static final int ATTRIBUTE_SIZE_MIN_JOB_ID = 0;

    public static final int ATTRIBUTE_SIZE_MAX_JOB_ID = COLUMN_LENGTH_JOB_ID;

    public static final String ATTRIBUTE_NAME_JOB = "job";

    // ---------------------------------------------------------------------------------------------------------- SALARY
    public static final String COLUMN_NAME_SALARY = "SALARY";

    public static final int COLUMN_PRECISION_SALARY = 8;

    public static final int COLUMN_SCALE_SALARY = 2;

    //    public static final double COLUMN_MIN_SALARY = -999999.99d; // TODO: check the checks/EMP_SALARY_MIN
    public static final double COLUMN_MIN_SALARY_EXCLUSIVE = 0; // TODO: check the checks/EMP_SALARY_MIN

    public static final double COLUMN_MAX_SALARY = +999999.99d;

    public static final String ATTRIBUTE_NAME_SALARY = "salary";

    /**
     * The decimal (exclusive) minimum value of the {@value #ATTRIBUTE_NAME_SALARY} attribute. The value is {@value}.
     */
    static final String ATTRIBUTE_DECIMAL_MIN_SALARY_EXCLUSIVE = "000000.00";

    public static final String ATTRIBUTE_DECIMAL_MAX_SALARY = "999999.99";

    // -------------------------------------------------------------------------------------------------- COMMISSION_PCT
    public static final String COLUMN_NAME_COMMISSION_PCT = "COMMISSION_PCT";

    public static final int COLUMN_PRECISION_COMMISSION_PCT = 2;

    public static final int COLUMN_SCALE_COMMISSION_PCT = 2;

    static final double COLUMN_MIN_COMMISSION_PCT = -0.99d;

    static final double COLUMN_MAX_COMMISSION_PCT = +0.99d;

    public static final String ATTRIBUTE_NAME_COMMISSION_PCT = "commissionPct";

    public static final String ATTRIBUTE_DECIMAL_MIN_COMMISSION_PCT = "-0.99";

    public static final String ATTRIBUTE_DECIMAL_MAX_COMMISSION_PCT = "+0.99";

    // ------------------------------------------------------------------------------------------------------ MANAGER_ID
    public static final String COLUMN_NAME_MANAGER_ID = "MANAGER_ID";

    public static final int COLUMN_PRECISION_MANAGER_ID = 6;

    public static final int COLUMN_SCALE_MANAGER_ID = 0;

    public static final int COLUMN_MIN_MANAGER_ID = -999999;

    public static final int COLUMN_MAX_MANAGER_ID = +999999;

    public static final String ATTRIBUTE_NAME_MANAGER_ID = "managerId";

    public static final long ATTRIBUTE_MIN_MANAGER_ID = COLUMN_MIN_MANAGER_ID;

    public static final long ATTRIBUTE_MAX_MANAGER_ID = COLUMN_MAX_MANAGER_ID;

    public static final String ATTRIBUTE_NAME_MANAGER = "manager";

    /**
     * The name of the attribute, of subordinates mapped by {@value ATTRIBUTE_NAME_MANAGER} attribute, of this employee.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUBORDINATES = "subordinates";

    // ------------------------------------------------------------------------------------ DEPARTMENT_ID / departmentId
    public static final String COLUMN_NAME_DEPARTMENT_ID = "DEPARTMENT_ID";

    public static final int COLUMN_PRECISION_DEPARTMENT_ID = 4;

    public static final int COLUMN_SCALE_DEPARTMENT_ID = 0;

    public static final int COLUMN_MIN_DEPARTMENT_ID = -9999;

    public static final int COLUMN_MAX_DEPARTMENT_ID = +9999;

    public static final String ATTRIBUTE_NAME_DEPARTMENT_ID = "departmentId";

    public static final int ATTRIBUTE_MIN_DEPARTMENT_ID = COLUMN_MIN_DEPARTMENT_ID;

    public static final int ATTRIBUTE_MAX_DEPARTMENT_ID = COLUMN_MAX_DEPARTMENT_ID;

    public static final String ATTRIBUTE_NAME_DEPARTMENT = "department";

    // -----------------------------------------------------------------------------------------------------------------
    public static final Comparator<MappedEmployee> COMPARING_HIRE_DATE =
            Comparator.comparing(MappedEmployee::getHireDate);

    public static final Comparator<MappedEmployee> COMPARING_LAST_NAME =
            Comparator.comparing(MappedEmployee::getLastName);

    /**
     * A comparator compares {@value MappedEmployee_#FIRST_NAME} attribute.
     *
     * @apiNote Note that this comparator is not null-friendly.
     * @see #COMPARING_FIRST_NAME_NULLS_FIRST
     * @see #COMPARING_FIRST_NAME_NULLS_LAST
     */
    public static final Comparator<MappedEmployee> COMPARING_FIRST_NAME =
            Comparator.comparing(MappedEmployee::getFirstName);

    /**
     * A comparator compares {@value MappedEmployee_#FIRST_NAME} attribute,
     * {@link Comparator#nullsFirst(Comparator) nulls first}.
     *
     * @see #COMPARING_FIRST_NAME_NULLS_LAST
     */
    public static final Comparator<MappedEmployee> COMPARING_FIRST_NAME_NULLS_FIRST =
            Comparator.nullsFirst(COMPARING_FIRST_NAME);

    /**
     * A comparator compares {@value MappedEmployee_#FIRST_NAME} attribute,
     * {@link Comparator#nullsLast(Comparator) nulls last}.
     *
     * @see #COMPARING_FIRST_NAME_NULLS_FIRST
     */
    public static final Comparator<MappedEmployee> COMPARING_FIRST_NAME_NULLS_LAST =
            Comparator.nullsLast(COMPARING_FIRST_NAME);

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedEmployee() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder from which a new instance is built.
     */
    protected MappedEmployee(@Nonnull final MappedEmployeeBuilder<?, ?> builder) {
        super(builder);
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

    protected final boolean equalsWithEmployeeId(final Object obj) {
        if (!(obj instanceof MappedEmployee that)) {
            return false;
        }
        return Objects.equals(getEmployeeId(), that.getEmployeeId());
    }

    protected final int hashCodeWithEmployeeId() {
        return Objects.hashCode(getEmployeeId());
    }

    protected final boolean equalsWithEmail(final Object obj) {
        if (!(obj instanceof MappedEmployee that)) {
            return false;
        }
        return Objects.equals(getEmail(), that.getEmail());
    }

    protected final int hashCodeWithEmail() {
        return Objects.hashCode(getEmail());
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    /**
     * Tests whether current value of the {@value MappedEmployee_#SALARY} attribute is greater than or equal to the
     * result of the {@link #getJobMinSalary()} method.
     *
     * @return {@code true} if the current value of the {@value MappedEmployee_#SALARY} attribute is greater than or
     * equal to the result of {@link #getJobMinSalary()} method; {@code false} otherwise.
     * @see #getJobMinSalary()
     */
    @SuppressWarnings({
            "java:S3011" // Reflection should not be used to increase accessibility of classes, methods, or fields
    })
    protected boolean isSalaryGreaterThanOrEqualToJobMinSalary() {
        if (salary == null) {
            return true;
        }
        final var jobMinSalary = getJobMinSalary();
        if (jobMinSalary == null) {
            return true;
        }
        return salary.compareTo(jobMinSalary) >= 0;
    }

    /**
     * Tests whether current value of the {@value #ATTRIBUTE_NAME_SALARY} attribute is greater than or equal to the
     * result of a method of {@link #getJobMaxSalary()} method.
     *
     * @return {@code true} if the current value of the {@value #ATTRIBUTE_NAME_SALARY} attribute is greater than or
     * equal to the result of a method of {@link #getJobMaxSalary()} method; {@code false} otherwise.
     * @see #getJobMaxSalary()
     */
    @SuppressWarnings({
            "java:S3011" // Reflection should not be used to increase accessibility of classes, methods, or fields
    })
    protected boolean isSalaryLessThanOrEqualToJobMaxSalary() {
        if (salary == null) {
            return true;
        }
        final var jobMaxSalary = getJobMaxSalary();
        if (jobMaxSalary == null) {
            return true;
        }
        return salary.compareTo(jobMaxSalary) <= 0;
    }

    /**
     * Tests whether current value of the {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute is non-negative.
     *
     * @return {@code true} if the current value of the {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute is
     * non-negative; {@code false} otherwise.
     */
    protected boolean isCommissionPctNonNegative() {
        if (commissionPct == null) {
            return true;
        }
        return commissionPct.signum() != -1;
    }

    /**
     * Tests whether current value of the {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute is positive.
     *
     * @return {@code true} if the current value of the {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute is positive;
     * {@code false} otherwise.
     */
    protected boolean isCommissionPctPositive() {
        if (commissionPct == null) {
            return true;
        }
        return commissionPct.signum() > 0;
    }

    // ------------------------------------------------------------------------------------------------------ employeeId

    /**
     * Returns current value of {@value MappedEmployee_#EMPLOYEE_ID} attribute.
     *
     * @return the current value of the {@value MappedEmployee_#EMPLOYEE_ID} attribute.
     */
    @Nonnull
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * Replaces current value of {@value MappedEmployee_#EMPLOYEE_ID} attribute with the specified value.
     *
     * @param employeeId the new value of the {@value MappedEmployee_#EMPLOYEE_ID} attribute.
     */
    protected void setEmployeeId(@Nonnull final Integer employeeId) {
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

    /**
     * Returns current value of {@value MappedEmployee_#JOB_ID} attribute.
     *
     * @return current value of the {@value MappedEmployee_#JOB_ID} attribute.
     */
    @Nonnull
    public String getJobId() {
        return jobId;
    }

    /**
     * Replaces current value of {@value MappedEmployee_#JOB_ID} attribute with the specified value.
     *
     * @param jobId new value of the {@value MappedEmployee_#JOB_ID} attribute.
     */
    protected void setJobId(@Nonnull final String jobId) {
        this.jobId = jobId;
    }

    /**
     * Returns the {@value MappedJob_#MIN_SALARY} of this employee's current job.
     *
     * @return the {@value MappedJob_#MIN_SALARY} of this employee's current job; {@code null} if this employee does not
     * have a job or the job's {@value MappedJob_#MIN_SALARY} is {@code null}.
     * @apiNote the {@code getJobMinSalary()} method of {@code MappedEmployee} class returns {@code null}.
     */
    @Transient
    protected @Nullable BigDecimal getJobMinSalary() {
        return null;
    }

    /**
     * Returns the {@value MappedJob_#MAX_SALARY} of this employee's current job.
     *
     * @return the {@value MappedJob_#MAX_SALARY} of this employee's current job; {@code null} if this employee does not
     * have a job or the job's {@value MappedJob_#MAX_SALARY} is {@code null}.
     * @apiNote the {@code getJobMaxSalary()} method of {@code MappedEmployee} class returns {@code null}.
     */
    @Transient
    protected @Nullable BigDecimal getJobMaxSalary() {
        return null;
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
    }

    // ------------------------------------------------------------------------------------------------------- managerId
    @Nullable
    protected Integer getManagerId() {
        return managerId;
    }

    protected void setManagerId(@Nullable final Integer managerId) {
        this.managerId = managerId;
    }

    // ---------------------------------------------------------------------------------------------------- departmentId

    /**
     * Returns current value of {@value MappedEmployee_#DEPARTMENT_ID} attribute.
     *
     * @return current value of the {@value MappedEmployee_#DEPARTMENT_ID} attribute.
     */
    @Nullable
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Replaces current value of {@value MappedEmployee_#DEPARTMENT_ID} attribute with the specified value.
     *
     * @param departmentId new value of the {@value MappedEmployee_#DEPARTMENT_ID} attribute.
     */
    protected void setDepartmentId(@Nullable final Integer departmentId) {
        this.departmentId = departmentId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Max(ATTRIBUTE_MAX_EMPLOYEE_ID)
    @Min(ATTRIBUTE_MIN_EMPLOYEE_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_EMPLOYEE_ID,
            nullable = false,
            insertable = true,
            updatable = false,
            precision = COLUMN_PRECISION_EMPLOYEE_ID,
            scale = COLUMN_SCALE_EMPLOYEE_ID
    )
    private Integer employeeId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(min = ATTRIBUTE_SIZE_MIN_FIRST_NAME, max = ATTRIBUTE_SIZE_MAX_FIRST_NAME)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_FIRST_NAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FIRST_NAME
    )
    private String firstName;

    @Nonnull
    @Size(min = ATTRIBUTE_SIZE_MIN_LAST_NAME, max = ATTRIBUTE_SIZE_MAX_LAST_NAME)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_LAST_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_LAST_NAME
    )
    private String lastName;

    @Nonnull
//    @Email
    @Size(min = ATTRIBUTE_SIZE_MIN_EMAIL, max = ATTRIBUTE_SIZE_MAX_EMAIL)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_EMAIL,
            nullable = false,
            insertable = true,
            updatable = false,
            length = COLUMN_LENGTH_EMAIL,
            unique = true
    )
    private String email;

    @Nullable
    @Size(min = ATTRIBUTE_SIZE_MIN_PHONE_NUMBER, max = ATTRIBUTE_SIZE_MAX_PHONE_NUMBER)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_PHONE_NUMBER,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PHONE_NUMBER
    )
    private String phoneNumber;

    @Nonnull
//    @jakarta.validation.constraints.PastOrPresent // @@?
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_HIRE_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDate hireDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(min = ATTRIBUTE_SIZE_MIN_JOB_ID, max = ATTRIBUTE_SIZE_MAX_JOB_ID)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_JOB_ID,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_JOB_ID
    )
    private String jobId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @DecimalMax(value = ATTRIBUTE_DECIMAL_MAX_SALARY, inclusive = true)
    @DecimalMin(value = ATTRIBUTE_DECIMAL_MIN_SALARY_EXCLUSIVE, inclusive = false)
//    @Positive // https://github.com/mtedone/podam/issues/328
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_SALARY,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_SALARY,
            scale = COLUMN_SCALE_SALARY
    )
    private BigDecimal salary;

    @Nullable
    @DecimalMax(value = ATTRIBUTE_DECIMAL_MAX_COMMISSION_PCT, inclusive = true)
    @DecimalMin(value = ATTRIBUTE_DECIMAL_MIN_COMMISSION_PCT, inclusive = true)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_COMMISSION_PCT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_COMMISSION_PCT,
            scale = COLUMN_SCALE_COMMISSION_PCT
    )
    private BigDecimal commissionPct;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Max(ATTRIBUTE_MAX_MANAGER_ID)
    @Min(ATTRIBUTE_MIN_MANAGER_ID)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_MANAGER_ID,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_MANAGER_ID,
            scale = COLUMN_SCALE_MANAGER_ID
    )
    private Integer managerId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Max(ATTRIBUTE_MAX_DEPARTMENT_ID)
    @Min(ATTRIBUTE_MIN_DEPARTMENT_ID)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_DEPARTMENT_ID,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_DEPARTMENT_ID,
            scale = COLUMN_SCALE_DEPARTMENT_ID
    )
    private Integer departmentId;
}
