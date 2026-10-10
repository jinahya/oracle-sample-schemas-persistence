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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedRegion#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedRegion {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "REGIONS";

    // ------------------------------------------------------------------------------------------------------- REGION_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_REGION_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_REGION_ID = "REGION_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REGION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION_ID = "regionId";

    // ----------------------------------------------------------------------------------------------------- REGION_NAME

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_REGION_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_REGION_NAME = "REGION_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_REGION_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REGION_NAME = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REGION_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION_NAME = "regionName";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_REGION_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_REGION_NAME = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_REGION_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_REGION_NAME = COLUMN_LENGTH_REGION_NAME;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedRegion() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final String toString() {
        return super.toString() + '{' +
               "regionId=" + regionId +
               ",regionName=" + regionName +
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
        if (!(obj instanceof MappedRegion that)) {
            return false;
        }
        return Objects.equals(getRegionId(), that.getRegionId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getRegionId());
    }

    // -------------------------------------------------------------------------------------------------------- regionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REGION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REGION_ID} attribute.
     */
    @Nonnull
    public Long getRegionId() {
        return regionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REGION_ID} attribute with the specified value.
     *
     * @param regionId new value for {@value #ATTRIBUTE_NAME_REGION_ID} attribute.
     */
    protected void setRegionId(@Nonnull final Long regionId) {
        this.regionId = regionId;
    }

    // ------------------------------------------------------------------------------------------------------ regionName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REGION_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REGION_NAME} attribute.
     */
    @Nullable
    public String getRegionName() {
        return regionName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REGION_NAME} attribute with the specified value.
     *
     * @param regionName new value for {@value #ATTRIBUTE_NAME_REGION_NAME} attribute.
     */
    public void setRegionName(@Nullable final String regionName) {
        this.regionName = regionName;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_REGION_ID, nullable = false, insertable = true, updatable = false)
    private Long regionId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(min = SIZE_MIN_REGION_NAME, max = SIZE_MAX_REGION_NAME)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_REGION_NAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_REGION_NAME
    )
    private String regionName;
}
