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
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * An entity class for mapping the {@value Department#TABLE_NAME} table.
 *
 * @author Myoungkwon Hwang
 */
@Entity
@Table(name = Department.TABLE_NAME)
public class Department extends MappedDepartment implements __DomainEntity<Integer> {

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MANAGER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MANAGER = "manager";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOCATION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION = "location";

    /**
     * The name of the attribute which maps the {@link Employee employee}s of this department. The value is {@value}.
     *
     * @see Employee#ATTRIBUTE_NAME_DEPARTMENT
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEES = "employees";

    /**
     * The name of the attribute which maps the past job assignments within this department. The value is {@value}.
     *
     * @see JobHistory#ATTRIBUTE_NAME_DEPARTMENT
     */
    public static final String ATTRIBUTE_NAME_JOB_HISTORIES = "jobHistories";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Department() {
        super();
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

    // -------------------------------------------------------------------------------------------------------- location

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOCATION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOCATION} attribute.
     */
    @Nullable
    public Location getLocation() {
        return location;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOCATION} attribute with the specified value.
     *
     * @param location new value for {@value #ATTRIBUTE_NAME_LOCATION} attribute.
     */
    public void setLocation(@Nullable final Location location) {
        this.location = location;
    }

    // ------------------------------------------------------------------------------------------------------- employees
    List<Employee> getEmployees() {
        return employees;
    }

    void setEmployees(final List<Employee> employees) {
        this.employees = employees;
    }

    // ---------------------------------------------------------------------------------------------------- jobHistories

    /**
     * Returns the past job assignments within this department.
     *
     * @return the past job assignments within this department.
     */
    List<JobHistory> getJobHistories() {
        return jobHistories;
    }

    /**
     * Replaces the past job assignments within this department.
     *
     * @param jobHistories new past job assignments within this department.
     */
    void setJobHistories(final List<JobHistory> jobHistories) {
        this.jobHistories = jobHistories;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_MANAGER_ID,
                nullable = COLUMN_NULLABLE_MANAGER_ID,
                insertable = true,
                updatable = true
    )
    private Employee manager;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LOCATION_ID,
                nullable = COLUMN_NULLABLE_LOCATION_ID,
                insertable = true,
                updatable = true
    )
    private Location location;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = Employee.ATTRIBUTE_NAME_DEPARTMENT,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Employee> employees;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = JobHistory.ATTRIBUTE_NAME_DEPARTMENT,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull JobHistory> jobHistories;
}
