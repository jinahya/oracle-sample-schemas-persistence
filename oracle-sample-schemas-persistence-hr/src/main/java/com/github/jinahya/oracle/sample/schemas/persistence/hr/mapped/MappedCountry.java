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
 * A mapped superclass which holds the mappings of the {@value MappedCountry#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedCountry {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "COUNTRIES";

    // ------------------------------------------------------------------------------------------------------ COUNTRY_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_ID = "COUNTRY_ID";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_ID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_ID = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_ID = "countryId";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_COUNTRY_ID = COLUMN_LENGTH_COUNTRY_ID;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_COUNTRY_ID = COLUMN_LENGTH_COUNTRY_ID;

    // ---------------------------------------------------------------------------------------------------- COUNTRY_NAME

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_NAME = "COUNTRY_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_NAME = 60;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_NAME = "countryName";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_COUNTRY_NAME = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_COUNTRY_NAME = COLUMN_LENGTH_COUNTRY_NAME;

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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCountry() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public final String toString() {
        return super.toString() + '{' +
               "countryId=" + countryId +
               ",countryName=" + countryName +
               ",regionId=" + regionId +
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
        if (!(obj instanceof MappedCountry that)) {
            return false;
        }
        return Objects.equals(getCountryId(), that.getCountryId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getCountryId());
    }

    // ------------------------------------------------------------------------------------------------------- countryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     */
    @Nonnull
    public String getCountryId() {
        return countryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute with the specified value.
     *
     * @param countryId new value for {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     */
    protected void setCountryId(@Nonnull final String countryId) {
        this.countryId = countryId;
    }

    // ----------------------------------------------------------------------------------------------------- countryName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute.
     */
    @Nullable
    public String getCountryName() {
        return countryName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute with the specified value.
     *
     * @param countryName new value for {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute.
     */
    public void setCountryName(@Nullable final String countryName) {
        this.countryName = countryName;
    }

    // -------------------------------------------------------------------------------------------------------- regionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REGION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REGION_ID} attribute.
     */
    @Nullable
    public Long getRegionId() {
        return regionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REGION_ID} attribute with the specified value.
     *
     * @param regionId new value for {@value #ATTRIBUTE_NAME_REGION_ID} attribute.
     */
    protected void setRegionId(@Nullable final Long regionId) {
        this.regionId = regionId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(min = SIZE_MIN_COUNTRY_ID, max = SIZE_MAX_COUNTRY_ID)
    @NotNull
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COUNTRY_ID,
            nullable = false,
            insertable = true,
            updatable = false,
            length = COLUMN_LENGTH_COUNTRY_ID
    )
    private String countryId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(min = SIZE_MIN_COUNTRY_NAME, max = SIZE_MAX_COUNTRY_NAME)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_COUNTRY_NAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COUNTRY_NAME
    )
    private String countryName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_REGION_ID, nullable = true, insertable = true, updatable = true)
    private Long regionId;
}
