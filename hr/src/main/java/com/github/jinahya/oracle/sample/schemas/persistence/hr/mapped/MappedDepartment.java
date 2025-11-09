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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * An abstract mapped-superclass for mapping the {@value MappedDepartment#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedDepartment extends _MappedHrEntity<Integer> {

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
     * The name of the entity attribute from which the {@value #COLUMN_NAME_DEPARTMENT_ID} column maps. The value is
     * {@value}.
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
    public static final String COLUMN_NAME_DEPARTMENT_NAME = "DEPARTMENT_NAME";

    public static final int COLUMN_LENGTH_DEPARTMENT_NAME = 30;

    public static final String ATTRIBUTE_NAME_DEPARTMENT_NAME = "departmentName";

    public static final int ATTRIBUTE_SIZE_MIN_DEPARTMENT_NAME = 0;

    public static final int ATTRIBUTE_SIZE_MAX_DEPARTMENT_NAME = COLUMN_LENGTH_DEPARTMENT_NAME;

    // ------------------------------------------------------------------------------------------------------ MANAGER_ID
    public static final String COLUMN_NAME_MANAGER_ID = "MANAGER_ID";

    public static final int COLUMN_PRECISION_MANAGER_ID = 6;

    public static final int COLUMN_SCALE_MANAGER_ID = 0;

    public static final int COLUMN_MIN_MANAGER_ID = -999999;

    public static final int COLUMN_MAX_MANAGER_ID = +999999;

    public static final String ATTRIBUTE_NAME_MANAGER_ID = "managerId";

    public static final int ATTRIBUTE_MIN_MANAGER_ID = COLUMN_MIN_MANAGER_ID;

    public static final int ATTRIBUTE_MAX_MANAGER_ID = COLUMN_MAX_MANAGER_ID;

    public static final String ATTRIBUTE_NAME_MANAGER = "manager";

    // ----------------------------------------------------------------------------------------------------- LOCATION_ID
    public static final String COLUMN_NAME_LOCATION_ID = "LOCATION_ID";

    public static final int COLUMN_PRECISION_LOCATION_ID = 4;

    public static final int COLUMN_SCALE_LOCATION_ID = 0;

    public static final int COLUMN_MIN_LOCATION_ID = -9999;

    public static final int COLUMN_MAX_LOCATION_ID = +9999;

    public static final String ATTRIBUTE_NAME_LOCATION_ID = "locationId";

    public static final int ATTRIBUTE_MIN_LOCATION_ID = COLUMN_MIN_LOCATION_ID;

    public static final int ATTRIBUTE_MAX_LOCATION_ID = COLUMN_MAX_LOCATION_ID;

    public static final String ATTRIBUTE_NAME_LOCATION = "location";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedDepartment() {
        super();
    }

    protected MappedDepartment(final MappedDepartmentBuilder<?, ?> builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + "{"
               + "id=" + departmentId
               + ",departmentName=" + departmentName
               + ",managerId=" + managerId
               + ",locationId=" + locationId
               + "}";
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    // ---------------------------------------------------------------------------------------------------- departmentId

    /**
     * Returns the current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     *
     * @return the current value of the {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     */
    @Nonnull
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Replaces the current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute with the specified value.
     *
     * @param departmentId the new value for the {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     */
    protected void setDepartmentId(@Nonnull final Integer departmentId) {
        this.departmentId = departmentId;
    }

    // -------------------------------------------------------------------------------------------------- departmentName
    @Nonnull
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(@Nonnull final String departmentName) {
        this.departmentName = departmentName;
    }

    // ------------------------------------------------------------------------------------------------------- managerId
    @Nullable
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(@Nullable final Integer managerId) {
        this.managerId = managerId;
    }

    // ------------------------------------------------------------------------------------------------------ locationId
    @Nullable
    public Integer getLocationId() {
        return locationId;
    }

    protected void setLocationId(@Nullable final Integer locationId) {
        this.locationId = locationId;
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
    @Size(min = ATTRIBUTE_SIZE_MIN_DEPARTMENT_NAME, max = ATTRIBUTE_SIZE_MAX_DEPARTMENT_NAME)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_DEPARTMENT_NAME, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_DEPARTMENT_NAME)
    private String departmentName;

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
    @Max(ATTRIBUTE_MAX_LOCATION_ID)
    @Min(ATTRIBUTE_MIN_LOCATION_ID)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(
            name = COLUMN_NAME_LOCATION_ID,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_LOCATION_ID,
            scale = COLUMN_SCALE_LOCATION_ID
    )
    private Integer locationId;
}
