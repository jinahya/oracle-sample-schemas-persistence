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

import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedLocation#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedLocation {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "LOCATIONS";

    // ----------------------------------------------------------------------------------------------------- LOCATION_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_ID = "LOCATION_ID";

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

    // -------------------------------------------------------------------------------------------------- STREET_ADDRESS

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_STREET_ADDRESS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_STREET_ADDRESS = "STREET_ADDRESS";

    /**
     * The length of the {@value #COLUMN_NAME_STREET_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STREET_ADDRESS = 40;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STREET_ADDRESS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STREET_ADDRESS = "streetAddress";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_STREET_ADDRESS} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_STREET_ADDRESS = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_STREET_ADDRESS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_STREET_ADDRESS = COLUMN_LENGTH_STREET_ADDRESS;

    // ----------------------------------------------------------------------------------------------------- POSTAL_CODE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_POSTAL_CODE = "POSTAL_CODE";

    /**
     * The length of the {@value #COLUMN_NAME_POSTAL_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_POSTAL_CODE = 12;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_POSTAL_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_POSTAL_CODE = "postalCode";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_POSTAL_CODE = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_POSTAL_CODE = COLUMN_LENGTH_POSTAL_CODE;

    // ------------------------------------------------------------------------------------------------------------ CITY

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CITY} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CITY = "CITY";

    /**
     * The length of the {@value #COLUMN_NAME_CITY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CITY = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CITY = "city";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_CITY} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_CITY = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CITY} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CITY = COLUMN_LENGTH_CITY;

    // -------------------------------------------------------------------------------------------------- STATE_PROVINCE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_STATE_PROVINCE = "STATE_PROVINCE";

    /**
     * The length of the {@value #COLUMN_NAME_STATE_PROVINCE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATE_PROVINCE = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE_PROVINCE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE = "stateProvince";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_STATE_PROVINCE = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_STATE_PROVINCE = COLUMN_LENGTH_STATE_PROVINCE;

    // ------------------------------------------------------------------------------------------------------ COUNTRY_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_ID = "COUNTRY_ID";

    /**
     * Whether the {@value #COLUMN_NAME_COUNTRY_ID} column is nullable. The value is {@value}.
     */
    public static final boolean COLUMN_NULLABLE_COUNTRY_ID = true;

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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedLocation() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "locationId=" + locationId +
               ",streetAddress=" + streetAddress +
               ",postalCode=" + postalCode +
               ",city=" + city +
               ",stateProvince=" + stateProvince +
               ",countryId=" + countryId +
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
        if (!(obj instanceof MappedLocation that)) {
            return false;
        }
        return Objects.equals(getLocationId(), that.getLocationId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getLocationId());
    }

    // ------------------------------------------------------------------------------------------------------ locationId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     */
    @Nonnull
    public Integer getLocationId() {
        return locationId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute with the specified value.
     *
     * @param locationId new value for {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     */
    protected void setLocationId(@Nonnull final Integer locationId) {
        this.locationId = locationId;
    }

    // --------------------------------------------------------------------------------------------------- streetAddress

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STREET_ADDRESS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STREET_ADDRESS} attribute.
     */
    @Nullable
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STREET_ADDRESS} attribute with the specified value.
     *
     * @param streetAddress new value for {@value #ATTRIBUTE_NAME_STREET_ADDRESS} attribute.
     */
    public void setStreetAddress(@Nullable final String streetAddress) {
        this.streetAddress = streetAddress;
    }

    // ------------------------------------------------------------------------------------------------------ postalCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute.
     */
    @Nullable
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute with the specified value.
     *
     * @param postalCode new value for {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute.
     */
    public void setPostalCode(@Nullable final String postalCode) {
        this.postalCode = postalCode;
    }

    // ------------------------------------------------------------------------------------------------------------ city

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CITY} attribute.
     */
    @Nonnull
    public String getCity() {
        return city;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CITY} attribute with the specified value.
     *
     * @param city new value for {@value #ATTRIBUTE_NAME_CITY} attribute.
     */
    public void setCity(@Nonnull final String city) {
        this.city = city;
    }

    // --------------------------------------------------------------------------------------------------- stateProvince

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute.
     */
    @Nullable
    public String getStateProvince() {
        return stateProvince;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute with the specified value.
     *
     * @param stateProvince new value for {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute.
     */
    public void setStateProvince(@Nullable final String stateProvince) {
        this.stateProvince = stateProvince;
    }

    // ------------------------------------------------------------------------------------------------------- countryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     */
    @Nullable
    public String getCountryId() {
        return countryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute with the specified value.
     *
     * @param countryId new value for {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     */
    protected void setCountryId(@Nullable final String countryId) {
        this.countryId = countryId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Max(ATTRIBUTE_MAX_LOCATION_ID)
    @Min(ATTRIBUTE_MIN_LOCATION_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_LOCATION_ID,
            nullable = false,
            insertable = true,
            updatable = false,
            precision = COLUMN_PRECISION_LOCATION_ID,
            scale = COLUMN_SCALE_LOCATION_ID
    )
    private Integer locationId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(min = SIZE_MIN_STREET_ADDRESS, max = SIZE_MAX_STREET_ADDRESS)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_STREET_ADDRESS,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_STREET_ADDRESS
    )
    private String streetAddress;

    @Nullable
    @Size(min = SIZE_MIN_POSTAL_CODE, max = SIZE_MAX_POSTAL_CODE)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_POSTAL_CODE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_POSTAL_CODE
    )
    private String postalCode;

    @Nonnull
    @Size(min = SIZE_MIN_CITY, max = SIZE_MAX_CITY)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_CITY,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CITY
    )
    private String city;

    @Nullable
    @Size(min = SIZE_MIN_STATE_PROVINCE, max = SIZE_MAX_STATE_PROVINCE)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_STATE_PROVINCE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_STATE_PROVINCE
    )
    private String stateProvince;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(min = SIZE_MIN_COUNTRY_ID, max = SIZE_MAX_COUNTRY_ID)
    @Basic(optional = COLUMN_NULLABLE_COUNTRY_ID)
    @Column(name = COLUMN_NAME_COUNTRY_ID,
            nullable = COLUMN_NULLABLE_COUNTRY_ID,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COUNTRY_ID
    )
    private String countryId;
}
