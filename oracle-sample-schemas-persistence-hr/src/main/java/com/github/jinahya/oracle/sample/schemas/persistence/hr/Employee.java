package com.github.jinahya.oracle.sample.schemas.persistence.hr;

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
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping the {@value Employee#TABLE_NAME} table.
 *
 * @author Jaehan Lim
 */
@Entity
@Table(name = Employee.TABLE_NAME)
public class Employee {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
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

    // TODO: use decimal

    /**
     * The minimum value of the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_EMPLOYEE_ID = 0xFF_F0_BD_C1;

    // -999999
    //                                               0b1111_1111_1111_0000_1011_1101_1100_0001
    // TODO: use decimal
//    public static final int COLUMN_MAX_EMPLOYEE_ID = 0x00_0F_42_3F; // +999999

    /**
     * The maximum value of the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_EMPLOYEE_ID = 0b0000_0000_0000_1111_0100_0010_0011_1111;

    // +999999

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = "employeeId";

    // TODO: remove casting when COLUMN_MIN_EMPLOYEE_ID uses decimal

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MIN_EMPLOYEE_ID = (long) COLUMN_MIN_EMPLOYEE_ID;

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
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_JOB} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    /**
     * The length of the {@value #COLUMN_NAME_JOB_ID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_JOB_ID = 10;

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_JOB} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_JOB_ID = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_JOB} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_JOB_ID = COLUMN_LENGTH_JOB_ID;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB = "job";

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

    //    public static final double COLUMN_MIN_SALARY = -999999.99d; // TODO: check the checks/EMP_SALARY_MIN

    /**
     * The exclusive minimum value of the {@value #COLUMN_NAME_SALARY} column. The value is {@value}.
     */
    public static final double COLUMN_MIN_SALARY_EXCLUSIVE = 0;

    // TODO: check the checks/EMP_SALARY_MIN

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

    // TODO: check the checks/EMP_SALARY_MIN

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
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_MANAGER} attribute maps. The value is
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
     * The minimum value of the {@value #ATTRIBUTE_NAME_MANAGER} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MIN_MANAGER_ID = COLUMN_MIN_MANAGER_ID;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_MANAGER} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MAX_MANAGER_ID = COLUMN_MAX_MANAGER_ID;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MANAGER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MANAGER = "manager";

    // ------------------------------------------------------------------------------------ DEPARTMENT_ID / departmentId

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute maps. The value is
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

    // TODO: use decimal

    /**
     * The minimum value of the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_DEPARTMENT_ID = 0xFF_FF_D8_F1;

    // -9999
    // TODO: use decimal

    /**
     * The maximum value of the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_DEPARTMENT_ID = 0x00_00_27_0F;

    // +9999

    // TODO: assign COLUMN_MIN_DEPARTMENT_ID

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MIN_DEPARTMENT_ID = 0xFF_FF_FF_FF_FF_FF_D8_F1L;

    // -9999L
    // TODO: assign COLUMN_MAX_DEPARTMENT_ID

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MAX_DEPARTMENT_ID = 0x00_00_00_00_00_00_27_0FL;

    // +9999L

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENT = "department";

    /**
     * The name of the attribute which maps the subordinates of this manager. The value is {@value}.
     *
     * @see #ATTRIBUTE_NAME_MANAGER
     */
    public static final String ATTRIBUTE_NAME_SUBORDINATES = "subordinates";

    /**
     * The name of the attribute which maps the departments this employee manages. The value is {@value}.
     *
     * @see Department#ATTRIBUTE_NAME_MANAGER
     */
    public static final String ATTRIBUTE_NAME_MANAGED_DEPARTMENTS = "managedDepartments";

    /**
     * The name of the attribute which maps the past job assignments of this employee. The value is {@value}.
     *
     * @see JobHistoryWithIdClass#ATTRIBUTE_NAME_EMPLOYEE
     */
    public static final String ATTRIBUTE_NAME_JOB_HISTORIES = "jobHistories";

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
               ",job=" + job +
               ",salary=" + salary +
               ",commissionPct=" + commissionPct +
               ",manager=" + manager +
               ",department=" + department +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Employee that)) {
            return false;
        }
        return Objects.equals(getEmployeeId(), that.getEmployeeId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getEmployeeId());
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    /**
     * Tests whether current value of the {@value #ATTRIBUTE_NAME_SALARY} attribute is less than or equal to the result
     * of the {@link #getJobMaxSalary()} method.
     *
     * @return {@code true} if the current value of the {@value #ATTRIBUTE_NAME_SALARY} attribute is less than or equal
     * to the result of the {@link #getJobMaxSalary()} method; {@code false} otherwise.
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

//    @jakarta.validation.constraints.AssertTrue

    /**
     * Indicates whether the {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute is non-negative.
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute is non-negative; {@code false}
     * otherwise.
     */
    protected boolean isCommissionPctNonNegative() {
        if (commissionPct == null) {
            return true;
        }
        return commissionPct.signum() >= 0;
    }

    //    @jakarta.validation.constraints.AssertTrue

    /**
     * Indicates whether the {@value #ATTRIBUTE_NAME_SALARY} attribute is greater than or equal to the
     * {@value Job#ATTRIBUTE_NAME_MIN_SALARY} of the job.
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_SALARY} attribute is greater than or equal to the
     * {@value Job#ATTRIBUTE_NAME_MIN_SALARY} of the job; {@code false} otherwise.
     */
    protected boolean isSalaryGreaterThanOrEqualToJobMinSalary() {
        if (salary == null || job == null || job.getMinSalary() == null) {
            return true;
        }
        return salary.compareTo(java.math.BigDecimal.valueOf(job.getMinSalary())) >= 0;
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
     * @param employeeId the new value of the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
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
    public LocalDate getHireDate() {
        return hireDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute with the specified value.
     *
     * @param hireDate new value for {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute.
     */
    public void setHireDate(@Nonnull final LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    // ------------------------------------------------------------------------------------------------------------- job

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB} attribute.
     */
    @Nonnull
    public Job getJob() {
        return job;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_JOB} attribute with the specified value.
     *
     * @param job new value for {@value #ATTRIBUTE_NAME_JOB} attribute.
     */
    public void setJob(@Nonnull final Job job) {
        this.job = job;
        // TODO: adjust current salary to between job.minSalary and job.maxSalary
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

    // --------------------------------------------------------------------------------------------------------- manager

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     */
    @Nonnull
    public Employee getManager() {
        return manager;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute with the specified value.
     *
     * @param manager new value for {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     */
    public void setManager(@Nonnull final Employee manager) {
        this.manager = manager;
    }

    // ----------------------------------------------------------------------------------------------- this.jobMinSalary

    /**
     * Returns the {@value Job#ATTRIBUTE_NAME_MIN_SALARY} of the {@value #ATTRIBUTE_NAME_JOB} of this employee.
     *
     * @return the {@value Job#ATTRIBUTE_NAME_MIN_SALARY} of the {@value #ATTRIBUTE_NAME_JOB} of this employee;
     * {@code null} when no job is assigned.
     */
    @Nullable
    protected BigDecimal getJobMinSalary() {
//        return this.getJobMinSalary();
        return Optional.ofNullable(job) // accessing the LAZY-fetching attribute !!!!
                .map(Job::getMinSalary)
                .map(BigDecimal::valueOf)
                .orElse(null);
    }

    // ----------------------------------------------------------------------------------------------- this.jobMaxSalary

    /**
     * Returns the {@value Job#ATTRIBUTE_NAME_MAX_SALARY} of the {@value #ATTRIBUTE_NAME_JOB} of this employee.
     *
     * @return the {@value Job#ATTRIBUTE_NAME_MAX_SALARY} of the {@value #ATTRIBUTE_NAME_JOB} of this employee;
     * {@code null} when no job is assigned.
     */
    @Nullable
    protected BigDecimal getJobMaxSalary() {
//        return this.getJobMaxSalary();
        return Optional.ofNullable(job) // accessing the LAZY-fetching attribute !!!!
                .map(Job::getMaxSalary)
                .map(BigDecimal::valueOf)
                .orElse(null);
    }

    // ------------------------------------------------------------------------------------------------------ department

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute with the specified value.
     *
     * @param department new value for {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute.
     */
    public void setDepartment(@Nonnull final Department department) {
        this.department = department;
    }

    // ---------------------------------------------------------------------------------------------------- subordinates

    /**
     * Returns <em>subordinates</em> of this <em>manager</em>.
     *
     * @return <em>subordinates</em> of this <em>manager</em>
     */
    List<Employee> getSubordinates() {
        return subordinates;
    }

    /**
     * Replaces current <em>subordinates</em> of this <em>manager</em> with the specified value.
     *
     * @param subordinates new <em>subordinates</em> of this <em>manager</em>.
     */
    void setSubordinates(final List<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    // ---------------------------------------------------------------------------------------------- managedDepartments

    /**
     * Returns the departments this employee manages.
     *
     * @return the departments this employee manages.
     */
    List<Department> getManagedDepartments() {
        return managedDepartments;
    }

    /**
     * Replaces the departments this employee manages.
     *
     * @param managedDepartments new departments this employee manages.
     */
    void setManagedDepartments(final List<Department> managedDepartments) {
        this.managedDepartments = managedDepartments;
    }

    // ---------------------------------------------------------------------------------------------------- jobHistories

    /**
     * Returns the past job assignments of this employee.
     *
     * @return the past job assignments of this employee.
     */
    List<JobHistoryWithIdClass> getJobHistories() {
        return jobHistories;
    }

    /**
     * Replaces the past job assignments of this employee.
     *
     * @param jobHistories new past job assignments of this employee.
     */
    void setJobHistories(final List<JobHistoryWithIdClass> jobHistories) {
        this.jobHistories = jobHistories;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
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
    @Size(min = SIZE_MIN_FIRST_NAME,
          max = SIZE_MAX_FIRST_NAME
    )
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_FIRST_NAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FIRST_NAME
    )
    private String firstName;

    @Nonnull
    @Size(min = SIZE_MIN_LAST_NAME,
          max = SIZE_MAX_LAST_NAME
    )
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
    @Size(min = SIZE_MIN_EMAIL, max = SIZE_MAX_EMAIL)
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
    @Size(max = SIZE_MAX_PHONE_NUMBER)
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
    @Column(name = COLUMN_NAME_HIRE_DATE, nullable = false, insertable = true, updatable = true)
    private LocalDate hireDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false,
               fetch = FetchType.LAZY,
               cascade = {
               }
    )
    @JoinColumn(name = COLUMN_NAME_JOB_ID,
                referencedColumnName = Job.COLUMN_NAME_JOB_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Job job;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
//    @Positive // @@?
    @DecimalMax(value = ATTRIBUTE_DECIMAL_MAX_SALARY, inclusive = true)
    @DecimalMin(value = ATTRIBUTE_DECIMAL_MIN_SALARY_EXCLUSIVE, inclusive = false)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_SALARY, nullable = true, insertable = true, updatable = true,
            precision = COLUMN_PRECISION_SALARY, scale = COLUMN_SCALE_SALARY)
    private BigDecimal salary;

    @Nullable
    @DecimalMax(value = ATTRIBUTE_DECIMAL_MAX_COMMISSION_PCT, inclusive = true)
    @DecimalMin(value = ATTRIBUTE_DECIMAL_MIN_COMMISSION_PCT, inclusive = true)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_COMMISSION_PCT, nullable = true, insertable = true, updatable = true,
            precision = COLUMN_PRECISION_COMMISSION_PCT, scale = COLUMN_SCALE_COMMISSION_PCT)
    private BigDecimal commissionPct;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true,
               fetch = FetchType.LAZY,
               cascade = {
               }
    )
    @JoinColumn(name = COLUMN_NAME_MANAGER_ID,
                referencedColumnName = COLUMN_NAME_EMPLOYEE_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Employee manager;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true,
               fetch = FetchType.LAZY,
               cascade = {
               }
    )
    @JoinColumn(name = COLUMN_NAME_DEPARTMENT_ID,
                referencedColumnName = Department.COLUMN_NAME_DEPARTMENT_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Department department;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = Employee.ATTRIBUTE_NAME_MANAGER,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Employee> subordinates;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = Department.ATTRIBUTE_NAME_MANAGER,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Department> managedDepartments;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = JobHistoryWithIdClass.ATTRIBUTE_NAME_EMPLOYEE,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull JobHistoryWithIdClass> jobHistories;
}
