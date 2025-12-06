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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartment;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJob;
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
 * An entity class maps the {@value MappedEmployee#TABLE_NAME} table.
 *
 * @author Jaehan Lim
 */
@Entity
@Table(name = MappedEmployee.TABLE_NAME)
class Employee extends MappedEmployee {

    // -------------------------------------------------------------------------------------------------------- BUILDERS
    static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ----------------------------------------------------------------------------------------------------- CONSTRUCTOR

    /**
     * Creates a new instance.
     */
    protected Employee() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder from which a new instance is built.
     */
    Employee(final EmployeeBuilder builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public final boolean equals(final Object obj) {
        return equalsWithEmployeeId(obj);
    }

    @Override
    public final int hashCode() {
        return hashCodeWithEmployeeId();
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence
//    @jakarta.validation.constraints.AssertTrue
    @Override
    protected boolean isCommissionPctNonNegative() {
        return super.isCommissionPctNonNegative();
    }

    //    @jakarta.validation.constraints.AssertTrue
    @Override
    protected boolean isSalaryGreaterThanOrEqualToJobMinSalary() {
        return super.isSalaryGreaterThanOrEqualToJobMinSalary();
    }

    // ----------------------------------------------------------------------------------------------------- super.jobId

    // ---------------------------------------------------------------------------------------------- super.jobMinSalary
    @Nullable
    @Override
    protected BigDecimal getJobMinSalary() {
//        return super.getJobMinSalary();
        return Optional.ofNullable(job) // accessing the LAZY-fetching attribute !!!!
                .map(MappedJob::getMinSalary)
                .map(BigDecimal::valueOf)
                .orElse(null);
    }

    // ---------------------------------------------------------------------------------------------- super.jobMaxSalary
    @Nullable
    @Override
    protected BigDecimal getJobMaxSalary() {
//        return super.getJobMaxSalary();
        return Optional.ofNullable(job) // accessing the LAZY-fetching attribute !!!!
                .map(MappedJob::getMaxSalary)
                .map(BigDecimal::valueOf)
                .orElse(null);
    }

    // ------------------------------------------------------------------------------------------------- super.managerId

    // ---------------------------------------------------------------------------------------------- super.departmentId

    // ------------------------------------------------------------------------------------------------------------- job
    @Nonnull
    public Job getJob() {
        return job;
    }

    public void setJob(@Nonnull final Job job) {
        this.job = job;
        setJobId(
                Optional.ofNullable(this.job)
                        .map(MappedJob::getJobId)
                        .orElse(null)
        );
        // TODO: adjust current salary to between job.minSalary and job.maxSalary
    }

    // --------------------------------------------------------------------------------------------------------- manager

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     *
     * @return current value of the {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     */
    @Nonnull
    public Employee getManager() {
        return manager;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute with the specified value.
     *
     * @param manager new value for the {@value #ATTRIBUTE_NAME_MANAGER}.
     */
    public void setManager(@Nonnull final Employee manager) {
        this.manager = manager;
        setManagerId(
                Optional.ofNullable(this.manager)
                        .map(MappedEmployee::getEmployeeId)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------ department
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(@Nonnull final Department department) {
        this.department = department;
        setDepartmentId(
                Optional.ofNullable(this.department)
                        .map(MappedDepartment::getDepartmentId)
                        .orElse(null)
        );
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
                referencedColumnName = MappedJob.COLUMN_NAME_JOB_ID,
                nullable = false,
                insertable = false,
                updatable = false
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
                insertable = false,
                updatable = false
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
                referencedColumnName = MappedDepartment.COLUMN_NAME_DEPARTMENT_ID,
                nullable = true,
                insertable = false,
                updatable = false
    )
    private Department department;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = MappedEmployee.ATTRIBUTE_NAME_MANAGER,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Employee> subordinates;
}
