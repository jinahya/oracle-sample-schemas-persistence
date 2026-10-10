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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * An entity class for mapping the {@value Employee#TABLE_NAME} table.
 *
 * @author Jaehan Lim
 */
@Entity
@Table(name = Employee.TABLE_NAME)
public class Employee extends MappedEmployee implements __DomainEntity<Integer> {

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB = "job";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MANAGER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MANAGER = "manager";

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
     * @see JobHistory#ATTRIBUTE_NAME_EMPLOYEE
     */
    public static final String ATTRIBUTE_NAME_JOB_HISTORIES = "jobHistories";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Employee() {
        super();
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    /**
     * Tests whether current value of the {@value #ATTRIBUTE_NAME_SALARY} attribute is less than or equal to the result
     * of the {@link #getJobMaxSalary()} method.
     *
     * @return {@code true} if the current value of the {@value #ATTRIBUTE_NAME_SALARY} attribute is less than or equal
     * to the result of the {@link #getJobMaxSalary()} method, or either of them is {@code null}; {@code false}
     * otherwise.
     * @see #getJobMaxSalary()
     */
    @SuppressWarnings({
            "java:S3011" // Reflection should not be used to increase accessibility of classes, methods, or fields
    })
    protected boolean isSalaryLessThanOrEqualToJobMaxSalary() {
        final var salary = getSalary();
        if (salary == null) {
            return true;
        }
        final var jobMaxSalary = getJobMaxSalary();
        if (jobMaxSalary == null) {
            return true;
        }
        return salary.compareTo(jobMaxSalary) <= 0;
    }

    //    @jakarta.validation.constraints.AssertTrue

    /**
     * Indicates whether the {@value #ATTRIBUTE_NAME_SALARY} attribute is greater than or equal to the
     * {@value Job#ATTRIBUTE_NAME_MIN_SALARY} of the job.
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_SALARY} attribute is greater than or equal to the
     * {@value Job#ATTRIBUTE_NAME_MIN_SALARY} of the job, or there is no salary, no job, or no minimum salary to
     * compare; {@code false} otherwise.
     */
    protected boolean isSalaryGreaterThanOrEqualToJobMinSalary() {
        final var salary = getSalary();
        if (salary == null || job == null || job.getMinSalary() == null) {
            return true;
        }
        return salary.compareTo(java.math.BigDecimal.valueOf(job.getMinSalary())) >= 0;
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
     * <p>
     * Changing it on a persisted employee makes the database's {@code UPDATE_JOB_HISTORY} trigger insert a
     * {@value JobHistory#TABLE_NAME} row, with the previous job and department, when the change is flushed.
     *
     * @param job new value for {@value #ATTRIBUTE_NAME_JOB} attribute.
     */
    public void setJob(@Nonnull final Job job) {
        this.job = job;
        // TODO: adjust current salary to between job.minSalary and job.maxSalary
    }

    // --------------------------------------------------------------------------------------------------------- manager

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     */
    @Nullable
    public Employee getManager() {
        return manager;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute with the specified value.
     *
     * @param manager new value for {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     */
    public void setManager(@Nullable final Employee manager) {
        this.manager = manager;
    }

    // ----------------------------------------------------------------------------------------------- this.jobMinSalary

    /**
     * Returns the {@value Job#ATTRIBUTE_NAME_MIN_SALARY} of the {@value #ATTRIBUTE_NAME_JOB} of this employee.
     *
     * @return the {@value Job#ATTRIBUTE_NAME_MIN_SALARY} of the {@value #ATTRIBUTE_NAME_JOB} of this employee;
     * {@code null} when no job is assigned, or the job has none.
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
     * {@code null} when no job is assigned, or the job has none.
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
     * <p>
     * Changing it on a persisted employee makes the database's {@code UPDATE_JOB_HISTORY} trigger insert a
     * {@value JobHistory#TABLE_NAME} row, with the previous job and department, when the change is flushed.
     *
     * @param department new value for {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute.
     */
    public void setDepartment(@Nullable final Department department) {
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
    List<JobHistory> getJobHistories() {
        return jobHistories;
    }

    /**
     * Replaces the past job assignments of this employee.
     *
     * @param jobHistories new past job assignments of this employee.
     */
    void setJobHistories(final List<JobHistory> jobHistories) {
        this.jobHistories = jobHistories;
    }

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
    @OneToMany(mappedBy = JobHistory.ATTRIBUTE_NAME_EMPLOYEE,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull JobHistory> jobHistories;
}
