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
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedDepartment#TABLE_NAME} table.
 * <p>
 * The {@value MappedDepartment#COLUMN_NAME_MANAGER_ID} and {@value MappedDepartment#COLUMN_NAME_LOCATION_ID} columns
 * are mapped read-only ({@code insertable = false, updatable = false}), with a {@code protected} getter and no setter,
 * for {@link #toString()} and for queries. How the relationship behind each is mapped -- fetch type, cascade, whether
 * there is an association at all -- is the extending entity's decision, so the extending entity also owns their
 * writable mapping, by an association's {@link jakarta.persistence.JoinColumn @JoinColumn} or by an
 * {@link jakarta.persistence.AttributeOverride @AttributeOverride}. <strong>An extending entity which maps neither
 * never writes them.</strong> Being read-only, they are populated by a load or a refresh only.
 *
 * @author Myoungkwon Hwang
 */
@MappedSuperclass
public abstract class MappedDepartment implements __MappedDomainEntity<Integer> {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
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
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_DEPARTMENT_ID = COLUMN_PRECISION_DEPARTMENT_ID;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_DEPARTMENT_ID = 0;

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
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute maps. The value is
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
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_MANAGER_ID = COLUMN_PRECISION_MANAGER_ID;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_MANAGER_ID = 0;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MANAGER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MANAGER_ID = "managerId";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_MANAGER_ID = COLUMN_MIN_MANAGER_ID;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_MANAGER_ID = COLUMN_MAX_MANAGER_ID;

    // ----------------------------------------------------------------------------------------------------- LOCATION_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute maps. The value is
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
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_LOCATION_ID = COLUMN_PRECISION_LOCATION_ID;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_LOCATION_ID = 0;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOCATION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION_ID = "locationId";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_LOCATION_ID = COLUMN_MIN_LOCATION_ID;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_LOCATION_ID = COLUMN_MAX_LOCATION_ID;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedDepartment() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final String toString() {
        return super.toString() + '{' +
               "departmentId=" + departmentId +
               ",departmentName=" + departmentName +
               ",managerId=" + managerId +
               ",locationId=" + locationId +
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
        if (!(obj instanceof MappedDepartment that)) {
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

    // ------------------------------------------------------------------------------------------------------ locationId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute, which is {@code null} until this
     * instance is loaded.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     * @apiNote This method is {@code protected}, not package-private, so that a lazy proxy, which is a subclass in
     * another package, can override it.
     */
    @Nullable
    protected Integer getLocationId() {
        return locationId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Max(ATTRIBUTE_MAX_DEPARTMENT_ID)
    @Min(ATTRIBUTE_MIN_DEPARTMENT_ID)
    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_DEPARTMENT_ID, fraction = ATTRIBUTE_DIGITS_FRACTION_DEPARTMENT_ID)
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
    @Column(name = COLUMN_NAME_DEPARTMENT_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DEPARTMENT_NAME
    )
    private String departmentName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_MANAGER_ID)
    @Min(ATTRIBUTE_MIN_MANAGER_ID)
    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_MANAGER_ID, fraction = ATTRIBUTE_DIGITS_FRACTION_MANAGER_ID)
    @Column(name = COLUMN_NAME_MANAGER_ID,
            nullable = COLUMN_NULLABLE_MANAGER_ID,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_MANAGER_ID,
            scale = COLUMN_SCALE_MANAGER_ID
    )
    private Integer managerId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_LOCATION_ID)
    @Min(ATTRIBUTE_MIN_LOCATION_ID)
    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_LOCATION_ID, fraction = ATTRIBUTE_DIGITS_FRACTION_LOCATION_ID)
    @Column(name = COLUMN_NAME_LOCATION_ID,
            nullable = COLUMN_NULLABLE_LOCATION_ID,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_LOCATION_ID,
            scale = COLUMN_SCALE_LOCATION_ID
    )
    private Integer locationId;
}
