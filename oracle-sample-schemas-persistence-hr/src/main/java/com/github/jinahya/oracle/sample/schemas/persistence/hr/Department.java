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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

/**
 * An entity class for mapping the {@value Department#TABLE_NAME} table.
 *
 * @author Myoungkwon Hwang
 */
@Entity
@Table(name = Department.TABLE_NAME)
public class Department {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "DEPARTMENTS";

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
    public static final int ATTRIBUTE_MIN_DEPARTMENT_ID = COLUMN_MIN_DEPARTMENT_ID;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_DEPARTMENT_ID = COLUMN_MAX_DEPARTMENT_ID;

    // ------------------------------------------------------------------------------------------------- DEPARTMENT_NAME

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DEPARTMENT_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DEPARTMENT_NAME = "DEPARTMENT_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_DEPARTMENT_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DEPARTMENT_NAME = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEPARTMENT_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENT_NAME = "departmentName";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_DEPARTMENT_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_DEPARTMENT_NAME = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_DEPARTMENT_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_DEPARTMENT_NAME = COLUMN_LENGTH_DEPARTMENT_NAME;

    // ------------------------------------------------------------------------------------------------------ MANAGER_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_MANAGER} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_MANAGER_ID = "MANAGER_ID";

    /**
     * Whether the {@value #COLUMN_NAME_MANAGER_ID} column is nullable. The value is {@value}.
     */
    public static final boolean COLUMN_NULLABLE_MANAGER_ID = true;

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
     * The minimum value of the identifier of the {@value #ATTRIBUTE_NAME_MANAGER} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_MANAGER_ID = COLUMN_MIN_MANAGER_ID;

    /**
     * The maximum value of the identifier of the {@value #ATTRIBUTE_NAME_MANAGER} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_MANAGER_ID = COLUMN_MAX_MANAGER_ID;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MANAGER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MANAGER = "manager";

    // ----------------------------------------------------------------------------------------------------- LOCATION_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_LOCATION} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_ID = "LOCATION_ID";

    /**
     * Whether the {@value #COLUMN_NAME_LOCATION_ID} column is nullable. The value is {@value}.
     */
    public static final boolean COLUMN_NULLABLE_LOCATION_ID = true;

    /**
     * The precision of the {@value #COLUMN_NAME_LOCATION_ID} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_LOCATION_ID = 4;

    /**
     * The scale of the {@value #COLUMN_NAME_LOCATION_ID} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_LOCATION_ID = 0;

    /**
     * The minimum value of the {@value #COLUMN_NAME_LOCATION_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_LOCATION_ID = -9999;

    /**
     * The maximum value of the {@value #COLUMN_NAME_LOCATION_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_LOCATION_ID = +9999;

    /**
     * The minimum value of the identifier of the {@value #ATTRIBUTE_NAME_LOCATION} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_LOCATION_ID = COLUMN_MIN_LOCATION_ID;

    /**
     * The maximum value of the identifier of the {@value #ATTRIBUTE_NAME_LOCATION} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_LOCATION_ID = COLUMN_MAX_LOCATION_ID;

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

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "id=" + departmentId
               + ",departmentName=" + departmentName
//               + ",manager=" + manager
//               + ",location=" + location
//               + ",employees=" + employees
//               + ",jobHistories=" + jobHistories
               + "}";
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
        if (!(obj instanceof Department that)) {
            return false;
        }
        return Objects.equals(getDepartmentId(), that.getDepartmentId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getDepartmentId());
    }

    // ---------------------------------------------------------------------------------------------------- departmentId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     */
    @Nonnull
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute with the specified value.
     *
     * @param departmentId new value for {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     */
    protected void setDepartmentId(@Nonnull final Integer departmentId) {
        this.departmentId = departmentId;
    }

    // -------------------------------------------------------------------------------------------------- departmentName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_NAME} attribute.
     */
    @Nonnull
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_NAME} attribute with the specified value.
     *
     * @param departmentName new value for {@value #ATTRIBUTE_NAME_DEPARTMENT_NAME} attribute.
     */
    public void setDepartmentName(@Nonnull final String departmentName) {
        this.departmentName = departmentName;
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
    @Nonnull
    @Max(ATTRIBUTE_MAX_DEPARTMENT_ID)
    @Min(ATTRIBUTE_MIN_DEPARTMENT_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_DEPARTMENT_ID,
            nullable = false,
            insertable = true,
            updatable = false,
            precision = COLUMN_PRECISION_DEPARTMENT_ID,
            scale = COLUMN_SCALE_DEPARTMENT_ID
    )
    private Integer departmentId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(min = SIZE_MIN_DEPARTMENT_NAME, max = SIZE_MAX_DEPARTMENT_NAME)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_DEPARTMENT_NAME, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_DEPARTMENT_NAME)
    private String departmentName;

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
