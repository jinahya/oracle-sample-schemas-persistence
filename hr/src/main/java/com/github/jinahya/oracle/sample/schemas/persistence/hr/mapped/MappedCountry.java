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
 * An abstract mapped-superclass for mapping the {@value MappedCountry#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedCountry extends _MappedHrEntity<String> {

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

    public static final int COLUMN_LENGTH_COUNTRY_ID = 2;

    public static final String ATTRIBUTE_NAME_COUNTRY_ID = "countryId";

    public static final int ATTRIBUTE_SIZE_MIN_COUNTRY_ID = COLUMN_LENGTH_COUNTRY_ID;

    public static final int ATTRIBUTE_SIZE_MAX_COUNTRY_ID = COLUMN_LENGTH_COUNTRY_ID;

    // ---------------------------------------------------------------------------------------------------- COUNTRY_NAME
    public static final String COLUMN_NAME_COUNTRY_NAME = "COUNTRY_NAME";

    public static final int COLUMN_LENGTH_COUNTRY_NAME = 60;

    public static final String ATTRIBUTE_NAME_COUNTRY_NAME = "countryName";

    public static final int ATTRIBUTE_SIZE_NIN_COUNTRY_NAME = 0;

    public static final int ATTRIBUTE_SIZE_MAX_COUNTRY_NAME = COLUMN_LENGTH_COUNTRY_NAME;

    // ------------------------------------------------------------------------------------------------------- REGION_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_REGION_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_REGION_ID = "REGION_ID";

    /**
     * The name of the entity attribute from which the {@value #COLUMN_NAME_REGION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION_ID = "regionId";

    public static final String ATTRIBUTE_NAME_REGION = "region";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String ATTRIBUTE_NAME_LOCATIONS = "locations";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCountry() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder.
     */
    protected MappedCountry(final MappedCountryBuilder<?, ?> builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "countryId=" + countryId +
               ",countryName=" + countryName +
               ",regionId=" + regionId +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedCountry that)) {
            return false;
        }
        return Objects.equals(getCountryId(), that.getCountryId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getCountryId());
    }

    // ------------------------------------------------------------------------------------------------------- countryId

    /**
     * Returns current value of {@link MappedCountry_#countryId countryId} attribute.
     *
     * @return the current value of {@link MappedCountry_#countryId countryId} attribute.
     */
    @Nonnull
    public String getCountryId() {
        return countryId;
    }

    /**
     * Replaces current value of {@link MappedCountry_#countryId countryId} attribute with the specified value.
     *
     * @param countryId new value for the {@link MappedCountry_#countryId countryId} attribute.
     */
    protected void setCountryId(@Nonnull final String countryId) {
        this.countryId = countryId;
    }

    // ----------------------------------------------------------------------------------------------------- countryName

    /**
     * Returns current value of {@link MappedCountry_#countryName countryName} attribute.
     *
     * @return the current value of {@link MappedCountry_#countryName countryName} attribute.
     */
    @Nullable
    public String getCountryName() {
        return countryName;
    }

    /**
     * Replaces current value of {@link MappedCountry_#countryName countryName} attribute with the specified value.
     *
     * @param countryName new value for the {@link MappedCountry_#countryName countryName} attribute.
     */
    public void setCountryName(@Nullable final String countryName) {
        this.countryName = countryName;
    }

    // -------------------------------------------------------------------------------------------------------- regionId
    @Nullable
    public Long getRegionId() {
        return regionId;
    }

    protected void setRegionId(@Nullable final Long regionId) {
        this.regionId = regionId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(min = ATTRIBUTE_SIZE_MIN_COUNTRY_ID, max = ATTRIBUTE_SIZE_MAX_COUNTRY_ID)
    @NotNull
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = false, insertable = true, updatable = false,
            length = COLUMN_LENGTH_COUNTRY_ID)
    private String countryId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(min = ATTRIBUTE_SIZE_NIN_COUNTRY_NAME, max = ATTRIBUTE_SIZE_MAX_COUNTRY_NAME)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_COUNTRY_NAME, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_COUNTRY_NAME)
    private String countryName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_REGION_ID, nullable = true, insertable = true, updatable = true)
    private Long regionId;
}
