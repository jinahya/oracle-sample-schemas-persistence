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
 * An abstract mapped-superclass for mapping the {@value MappedRegion#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedRegion extends _MappedHrEntity<Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "REGIONS";

    // -------------------------------------------------------------------------------------------- REGION_ID / regionId

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_REGION_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_REGION_ID = "REGION_ID";

    /**
     * The name of the entity attribute from which the {@value #COLUMN_NAME_REGION_ID} column maps. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION_ID = "regionId";

    // ---------------------------------------------------------------------------------------- REGION_NAME / regionName
    public static final String COLUMN_NAME_REGION_NAME = "REGION_NAME";

    public static final int COLUMN_LENGTH_REGION_NAME = 25;

    public static final String ATTRIBUTE_NAME_REGION_NAME = "regionName";

    public static final int SIZE_MAX_REGION_NAME = COLUMN_LENGTH_REGION_NAME;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedRegion() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder from which a new instance is built.
     */
    protected MappedRegion(final MappedRegionBuilder<?, ?> builder) {
        super();
        regionId = builder.regionId();
        regionName = builder.regionName();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "regionId=" + regionId +
               ",regionName=" + regionName +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedRegion that)) {
            return false;
        }
        return Objects.equals(getRegionId(), that.getRegionId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getRegionId());
    }

    // -------------------------------------------------------------------------------------------------------- regionId

    /**
     * Returns current value of {@link MappedRegion_#regionId regionId} attribute.
     *
     * @return the current value of {@link MappedRegion_#regionId regionId} attribute.
     */
    @Nonnull
    public Long getRegionId() {
        return regionId;
    }

    /**
     * Replaces current value of {@link MappedRegion_#regionId regionId} attribute with specified value.
     *
     * @param regionId new value for the {@link MappedRegion_#regionId regionId} attribute.
     */
    void setRegionId(@Nonnull final Long regionId) {
        this.regionId = regionId;
    }

    // ------------------------------------------------------------------------------------------------------ regionName
    @Nullable
    public String getRegionName() {
        return regionName;
    }

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
    @Size(max = SIZE_MAX_REGION_NAME)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_REGION_NAME, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_REGION_NAME)
    private String regionName;
}
