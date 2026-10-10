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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedEmployee#TABLE_NAME} table.
 * <p>
 * The {@value MappedEmployee#COLUMN_NAME_JOB_ID}, {@value MappedEmployee#COLUMN_NAME_MANAGER_ID} and
 * {@value MappedEmployee#COLUMN_NAME_DEPARTMENT_ID} columns are mapped read-only
 * ({@code insertable = false, updatable = false}), with a {@code protected} getter and no setter, for
 * {@link #toString()} and for queries. How the relationship behind each is mapped -- fetch type, cascade, whether there
 * is an association at all -- is the extending entity's decision, so the extending entity also owns their writable
 * mapping, by an association's {@link jakarta.persistence.JoinColumn @JoinColumn} or by an
 * {@link jakarta.persistence.AttributeOverride @AttributeOverride}. <strong>An extending entity which maps neither
 * never writes them.</strong> Being read-only, they are populated by a load or a refresh only.
 *
 * @author Jaehan Lim
 */
@MappedSuperclass
public abstract class MappedEmployee implements __MappedDomainEntity<Integer> {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "EMPLOYEES";

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_EMPLOYEE_ID = "EMPLOYEE_ID";

    /**
     * The precision of the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_EMPLOYEE_ID = 6;

    /**
     * The scale of the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_EMPLOYEE_ID = 0;

    /**
     * The minimum value of the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_EMPLOYEE_ID = -999999;

    /**
     * The maximum value of the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_EMPLOYEE_ID = +999999;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = "employeeId";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MIN_EMPLOYEE_ID = COLUMN_MIN_EMPLOYEE_ID;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MAX_EMPLOYEE_ID = COLUMN_MAX_EMPLOYEE_ID;

    // ------------------------------------------------------------------------------------------------------ FIRST_NAME

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FIRST_NAME = "FIRST_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_FIRST_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FIRST_NAME = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FIRST_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FIRST_NAME = "firstName";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_FIRST_NAME = 0;

    /**
     * The minimum size of a {@value #ATTRIBUTE_NAME_FIRST_NAME} value which reads as a name, as opposed to
     * {@link #SIZE_MIN_FIRST_NAME}, which is what the column accepts. No constraint applies it. The value is {@value}.
     */
    public static final int SIZE_MIN_FIRST_NAME_SEMANTIC = 2;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_FIRST_NAME = COLUMN_LENGTH_FIRST_NAME;

    // ------------------------------------------------------------------------------------------------------- LAST_NAME

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_LAST_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LAST_NAME = "LAST_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_LAST_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_LAST_NAME = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_NAME = "lastName";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_LAST_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_LAST_NAME = 0;

    /**
     * The minimum size of a {@value #ATTRIBUTE_NAME_LAST_NAME} value which reads as a name, as opposed to
     * {@link #SIZE_MIN_LAST_NAME}, which is what the column accepts. No constraint applies it. The value is {@value}.
     */
    public static final int SIZE_MIN_LAST_NAME_SEMANTIC = 2;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_LAST_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_LAST_NAME = COLUMN_LENGTH_LAST_NAME;

    // ----------------------------------------------------------------------------------------------------------- EMAIL

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_EMAIL} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMAIL = "EMAIL";

    /**
     * The length of the {@value #COLUMN_NAME_EMAIL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EMAIL = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMAIL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMAIL = "email";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_EMAIL} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_EMAIL = 0;

    /**
     * The minimum size of an {@value #ATTRIBUTE_NAME_EMAIL} value which carries meaning, as opposed to
     * {@link #SIZE_MIN_EMAIL}, which is what the column accepts. No constraint applies it. The value is {@value}.
     */
    public static final int SIZE_MIN_EMAIL_SEMANTIC = 1; // a@b.cc

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_EMAIL} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_EMAIL = COLUMN_LENGTH_EMAIL;

    // ---------------------------------------------------------------------------------------------------- PHONE_NUMBER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PHONE_NUMBER = "PHONE_NUMBER";

    /**
     * The length of the {@value #COLUMN_NAME_PHONE_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PHONE_NUMBER = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PHONE_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHONE_NUMBER = "phoneNumber";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_PHONE_NUMBER = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PHONE_NUMBER = COLUMN_LENGTH_PHONE_NUMBER;

    /**
     * The regular expression of the {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute -- groups of digits separated by
     * dots, e.g. {@code 1.515.555.0100}. The value is {@value}.
     *
     * @see jakarta.validation.constraints.Pattern#regexp()
     */
    public static final String PATTERN_REGEXP_PHONE_NUMBER = "\\d+(\\.\\d+)*";

    // ------------------------------------------------------------------------------------------------------- HIRE_DATE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_HIRE_DATE = "HIRE_DATE";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_HIRE_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_HIRE_DATE = "hireDate";

    // ---------------------------------------------------------------------------------------------------------- JOB_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_JOB_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    /**
     * The length of the {@value #COLUMN_NAME_JOB_ID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_JOB_ID = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB_ID = "jobId";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_JOB_ID} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_JOB_ID = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_JOB_ID} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_JOB_ID = COLUMN_LENGTH_JOB_ID;

    // ---------------------------------------------------------------------------------------------------------- SALARY

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_SALARY} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALARY = "SALARY";

    /**
     * The precision of the {@value #COLUMN_NAME_SALARY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SALARY = 8;

    /**
     * The scale of the {@value #COLUMN_NAME_SALARY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SALARY = 2;

    /**
     * The exclusive minimum value of the {@value #COLUMN_NAME_SALARY} column, as the {@code EMP_SALARY_MIN} check
     * constraint ({@code salary > 0}) requires. The value is {@value}.
     */
    public static final double COLUMN_MIN_SALARY_EXCLUSIVE = 0;

    /**
     * The maximum value of the {@value #COLUMN_NAME_SALARY} column. The value is {@value}.
     */
    public static final double COLUMN_MAX_SALARY = +999999.99d;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALARY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALARY = "salary";

    /**
     * The exclusive minimum value of the {@value #ATTRIBUTE_NAME_SALARY} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_SALARY_EXCLUSIVE = "-000000.00";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SALARY} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_SALARY = "+999999.99";

    // -------------------------------------------------------------------------------------------------- COMMISSION_PCT

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COMMISSION_PCT = "COMMISSION_PCT";

    /**
     * The precision of the {@value #COLUMN_NAME_COMMISSION_PCT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_COMMISSION_PCT = 2;

    /**
     * The scale of the {@value #COLUMN_NAME_COMMISSION_PCT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_COMMISSION_PCT = 2;

    /**
     * The minimum value of the {@value #COLUMN_NAME_COMMISSION_PCT} column. The value is {@value}.
     */
    public static final double COLUMN_MIN_COMMISSION_PCT = -0.99d;

    /**
     * The maximum value of the {@value #COLUMN_NAME_COMMISSION_PCT} column. The value is {@value}.
     */
    public static final double COLUMN_MAX_COMMISSION_PCT = +0.99d;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMMISSION_PCT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMMISSION_PCT = "commissionPct";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_COMMISSION_PCT = "-0.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_COMMISSION_PCT = "+0.99";

    // ------------------------------------------------------------------------------------------------------ MANAGER_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_MANAGER_ID = "MANAGER_ID";

    /**
     * The precision of the {@value #COLUMN_NAME_MANAGER_ID} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_MANAGER_ID = 6;

    /**
     * The scale of the {@value #COLUMN_NAME_MANAGER_ID} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_MANAGER_ID = 0;

    /**
     * The minimum value of the {@value #COLUMN_NAME_MANAGER_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_MANAGER_ID = -999999;

    /**
     * The maximum value of the {@value #COLUMN_NAME_MANAGER_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_MANAGER_ID = +999999;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MANAGER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MANAGER_ID = "managerId";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MIN_MANAGER_ID = COLUMN_MIN_MANAGER_ID;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MAX_MANAGER_ID = COLUMN_MAX_MANAGER_ID;

    // --------------------------------------------------------------------------------------------------- DEPARTMENT_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DEPARTMENT_ID = "DEPARTMENT_ID";

    /**
     * The precision of the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DEPARTMENT_ID = 4;

    /**
     * The scale of the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DEPARTMENT_ID = 0;

    /**
     * The minimum value of the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_DEPARTMENT_ID = -9999;

    /**
     * The maximum value of the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_DEPARTMENT_ID = +9999;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENT_ID = "departmentId";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MIN_DEPARTMENT_ID = COLUMN_MIN_DEPARTMENT_ID;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MAX_DEPARTMENT_ID = COLUMN_MAX_DEPARTMENT_ID;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedEmployee() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final String toString() {
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

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the {@code @Id} alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedEmployee that)) {
            return false;
        }
        return Objects.equals(getEmployeeId(), that.getEmployeeId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getEmployeeId());
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    /**
     * Tests whether current value of the {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute is positive.
     *
     * @return {@code true} if the current value of the {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute is positive,
     * or {@code null}; {@code false} otherwise.
     */
    protected boolean isCommissionPctPositive() {
        if (commissionPct == null) {
            return true;
        }
        return commissionPct.signum() > 0;
    }

    /**
     * Indicates whether the {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute is non-negative.
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute is non-negative, or {@code null};
     * {@code false} otherwise.
     */
    protected boolean isCommissionPctNonNegative() {
        if (commissionPct == null) {
            return true;
        }
        return commissionPct.signum() >= 0;
    }

    // ------------------------------------------------------------------------------------------------------ employeeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     */
    @Nonnull
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute with the specified value.
     *
     * @param employeeId new value for {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     */
    protected void setEmployeeId(@Nonnull final Integer employeeId) {
        this.employeeId = employeeId;
    }

    // ------------------------------------------------------------------------------------------------------- firstName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    @Nullable
    public String getFirstName() {
        return firstName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute with the specified value.
     *
     * @param firstName new value for {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    public void setFirstName(@Nullable final String firstName) {
        this.firstName = firstName;
    }

    // -------------------------------------------------------------------------------------------------------- lastName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    @Nonnull
    public String getLastName() {
        return lastName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute with the specified value.
     *
     * @param lastName new value for {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    public void setLastName(@Nonnull final String lastName) {
        this.lastName = lastName;
    }

    // ----------------------------------------------------------------------------------------------------------- email

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMAIL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMAIL} attribute.
     */
    @Nonnull
    public String getEmail() {
        return email;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMAIL} attribute with the specified value.
     *
     * @param email new value for {@value #ATTRIBUTE_NAME_EMAIL} attribute.
     */
    public void setEmail(@Nonnull final String email) {
        this.email = email;
    }

    // ----------------------------------------------------------------------------------------------------- phoneNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute.
     */
    @Nullable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute with the specified value.
     *
     * @param phoneNumber new value for {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute.
     */
    public void setPhoneNumber(@Nullable final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // -------------------------------------------------------------------------------------------------------- hireDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getHireDate() {
        return hireDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute with the specified value.
     *
     * @param hireDate new value for {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute.
     */
    public void setHireDate(@Nonnull final LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    // ----------------------------------------------------------------------------------------------------------- jobId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB_ID} attribute, which is {@code null} until this instance is
     * loaded.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB_ID} attribute.
     * @apiNote This method is {@code protected}, not package-private, so that a lazy proxy, which is a subclass in
     * another package, can override it.
     */
    @Nullable
    protected String getJobId() {
        return jobId;
    }

    // ---------------------------------------------------------------------------------------------------------- salary

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALARY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALARY} attribute.
     */
    @Nullable
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALARY} attribute with the specified value.
     *
     * @param salary new value for {@value #ATTRIBUTE_NAME_SALARY} attribute.
     */
    public void setSalary(@Nullable final BigDecimal salary) {
        this.salary = salary;
    }

    // --------------------------------------------------------------------------------------------------- commissionPct

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute.
     */
    @Nullable
    public BigDecimal getCommissionPct() {
        return commissionPct;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute with the specified value.
     *
     * @param commissionPct new value for {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute.
     */
    public void setCommissionPct(@Nullable final BigDecimal commissionPct) {
        this.commissionPct = commissionPct;
    }

    // ------------------------------------------------------------------------------------------------------- managerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute, which is {@code null} until this instance
     * is loaded.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute.
     * @apiNote This method is {@code protected}, not package-private, so that a lazy proxy, which is a subclass in
     * another package, can override it.
     */
    @Nullable
    protected Integer getManagerId() {
        return managerId;
    }

    // ---------------------------------------------------------------------------------------------------- departmentId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute, which is {@code null} until this
     * instance is loaded.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     * @apiNote This method is {@code protected}, not package-private, so that a lazy proxy, which is a subclass in
     * another package, can override it.
     */
    @Nullable
    protected Integer getDepartmentId() {
        return departmentId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    // TODO: remove; restates NUMBER(p) -- @Digits states the precision
//    @Max(ATTRIBUTE_MAX_EMPLOYEE_ID)
    // TODO: remove; restates NUMBER(p) -- @Digits states the precision
//    @Min(ATTRIBUTE_MIN_EMPLOYEE_ID)
    @Digits(integer = COLUMN_PRECISION_EMPLOYEE_ID, fraction = 0)
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
    @Size(min = SIZE_MIN_FIRST_NAME, max = SIZE_MAX_FIRST_NAME)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_FIRST_NAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FIRST_NAME
    )
    private String firstName;

    // TODO: remove; not constrained by the DDL -- EMPLOYEES.LAST_NAME is VARCHAR2(25) NOT NULL with no check
    //    @jakarta.validation.constraints.NotBlank
    @Nonnull
    @Size(min = SIZE_MIN_LAST_NAME, max = SIZE_MAX_LAST_NAME)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_LAST_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_LAST_NAME
    )
    private String lastName;

    // TODO: remove; not constrained by the DDL -- EMPLOYEES.EMAIL is VARCHAR2(25) NOT NULL with no check
    //    @jakarta.validation.constraints.NotBlank
    @Nonnull
    @Size(min = SIZE_MIN_EMAIL, max = SIZE_MAX_EMAIL)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_EMAIL,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_EMAIL,
            unique = true
    )
    private String email;

    @Nullable
    @Size(min = SIZE_MIN_PHONE_NUMBER, max = SIZE_MAX_PHONE_NUMBER)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_PHONE_NUMBER,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PHONE_NUMBER
    )
    private String phoneNumber;

    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_HIRE_DATE, nullable = false, insertable = true, updatable = true)
    private LocalDateTime hireDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    // no @NotNull: read-only duplicate of JOB_ID; the extending entity's writable mapping carries the constraint
    @Size(max = SIZE_MAX_JOB_ID)
    @Column(name = COLUMN_NAME_JOB_ID,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_JOB_ID
    )
    private String jobId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Positive // EMP_SALARY_MIN: CHECK (salary > 0)
    @Digits(integer = COLUMN_PRECISION_SALARY - COLUMN_SCALE_SALARY, fraction = COLUMN_SCALE_SALARY)
    // TODO: remove; restates NUMBER(p,s) -- @Digits states the precision
//    @DecimalMax(value = ATTRIBUTE_DECIMAL_MAX_SALARY, inclusive = true)
    // TODO: remove; duplicates @Positive, which already states EMP_SALARY_MIN (salary > 0)
//    @DecimalMin(value = ATTRIBUTE_DECIMAL_MIN_SALARY_EXCLUSIVE, inclusive = false)
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
    @Digits(integer = COLUMN_PRECISION_COMMISSION_PCT - COLUMN_SCALE_COMMISSION_PCT,
            fraction = COLUMN_SCALE_COMMISSION_PCT)
    // TODO: remove; restates NUMBER(p,s) -- @Digits states the precision
//    @DecimalMax(value = ATTRIBUTE_DECIMAL_MAX_COMMISSION_PCT, inclusive = true)
    // TODO: remove; restates NUMBER(p,s) -- @Digits states the precision
//    @DecimalMin(value = ATTRIBUTE_DECIMAL_MIN_COMMISSION_PCT, inclusive = true)
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
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    // TODO: remove; restates NUMBER(p) -- @Digits states the precision
//    @Max(ATTRIBUTE_MAX_MANAGER_ID)
    // TODO: remove; restates NUMBER(p) -- @Digits states the precision
//    @Min(ATTRIBUTE_MIN_MANAGER_ID)
    @Digits(integer = COLUMN_PRECISION_MANAGER_ID, fraction = 0)
    @Column(name = COLUMN_NAME_MANAGER_ID,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_MANAGER_ID,
            scale = COLUMN_SCALE_MANAGER_ID
    )
    private Integer managerId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    // TODO: remove; restates NUMBER(p) -- @Digits states the precision
//    @Max(ATTRIBUTE_MAX_DEPARTMENT_ID)
    // TODO: remove; restates NUMBER(p) -- @Digits states the precision
//    @Min(ATTRIBUTE_MIN_DEPARTMENT_ID)
    @Digits(integer = COLUMN_PRECISION_DEPARTMENT_ID, fraction = 0)
    @Column(name = COLUMN_NAME_DEPARTMENT_ID,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_DEPARTMENT_ID,
            scale = COLUMN_SCALE_DEPARTMENT_ID
    )
    private Integer departmentId;
}
