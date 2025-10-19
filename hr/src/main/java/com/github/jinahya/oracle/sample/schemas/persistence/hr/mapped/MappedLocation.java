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
 * An abstract mapped superclass for mapping {@value MappedLocation#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedLocation extends _MappedHrEntity<Integer> {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "LOCATIONS";

    // ----------------------------------------------------------------------------------------------------- LOCATION_ID
    public static final String COLUMN_NAME_LOCATION_ID = "LOCATION_ID";

    public static final int COLUMN_PRECISION_LOCATION_ID = 4;

    public static final int COLUMN_MIN_LOCATION_ID = -9999;

    public static final int COLUMN_MAX_LOCATION_ID = +9999;

    public static final String ATTRIBUTE_NAME_LOCATION_ID = "locationId";

    public static final int MIN_LOCATION_ID = COLUMN_MIN_LOCATION_ID;

    public static final int MAX_LOCATION_ID = COLUMN_MAX_LOCATION_ID;

    // -------------------------------------------------------------------------------------------------- STREET_ADDRESS
    public static final String COLUMN_NAME_STREET_ADDRESS = "STREET_ADDRESS";

    public static final int COLUMN_LENGTH_STREET_ADDRESS = 40;

    public static final String ATTRIBUTE_NAME_STREET_ADDRESS = "streetAddress";

    public static final int SIZE_MAX_STREET_ADDRESS = COLUMN_LENGTH_STREET_ADDRESS;

    // ----------------------------------------------------------------------------------------------------- POSTAL_CODE
    public static final String COLUMN_NAME_POSTAL_CODE = "POSTAL_CODE";

    public static final int COLUMN_LENGTH_POSTAL_CODE = 12;

    public static final String ATTRIBUTE_NAME_POSTAL_CODE = "postalCode";

    public static final int SIZE_MAX_POSTAL_CODE = COLUMN_LENGTH_POSTAL_CODE;

    // ------------------------------------------------------------------------------------------------------------ CITY
    public static final String COLUMN_NAME_CITY = "CITY";

    public static final int COLUMN_LENGTH_CITY = 30;

    public static final String ATTRIBUTE_NAME_CITY = "city";

    public static final int SIZE_MAX_CITY = COLUMN_LENGTH_CITY;

    // -------------------------------------------------------------------------------------------------- STATE_PROVINCE
    public static final String COLUMN_NAME_STATE_PROVINCE = "STATE_PROVINCE";

    public static final int COLUMN_LENGTH_STREET_PROVINCE = 25;

    public static final String ATTRIBUTE_STATE_PROVINCE = "stateProvince";

    public static final int SIZE_MAX_STREET_PROVINCE = COLUMN_LENGTH_STREET_PROVINCE;

    // ------------------------------------------------------------------------------------------------------ COUNTRY_ID
    public static final String COLUMN_NAME_COUNTRY_ID = "COUNTRY_ID";

    public static final int COLUMN_LENGTH_COUNTRY_ID = 2;

    static {
        assert COLUMN_LENGTH_COUNTRY_ID == MappedCountry.COLUMN_LENGTH_COUNTRY_ID;
    }

    public static final String ATTRIBUTE_NAME_COUNTRY_ID = "countryId";

    public static final int SIZE_MAX_COUNTRY_ID = COLUMN_LENGTH_COUNTRY_ID;

    public static final String ATTRIBUTE_NAME_COUNTRY = "country";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedLocation() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder to build from.
     */
    protected MappedLocation(final MappedLocationBuilder<?, ?> builder) {
        super();
        locationId = builder.locationId();
        streetAddress = builder.streetAddress();
        postalCode = builder.postalCode();
        city = builder.city();
        stateProvince = builder.stateProvince();
        countryId = builder.countryId();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "locationId=" + locationId +
               ",streetAddress='" + streetAddress +
               ",postalCode=" + postalCode +
               ",city=" + city +
               ",stateProvince=" + stateProvince +
               ",countryId=" + countryId +
               '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof MappedLocation that)) {
            return false;
        }
        return Objects.equals(locationId, that.locationId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(locationId);
    }

    // ------------------------------------------------------------------------------------------------------ locationId

    /**
     * Returns current value of {@link MappedLocation_#locationId locationId} attribute.
     *
     * @return the current value of {@link MappedLocation_#locationId locationId} attribute.
     */
    @Nonnull
    public Integer getLocationId() {
        return locationId;
    }

    /**
     * Replaces current value of {@link MappedLocation_#locationId locationId} attribute with specified value.
     *
     * @param locationId new value for the {@link MappedLocation_#locationId locationId} attribute.
     */
    void setLocationId(@Nonnull final Integer locationId) {
        this.locationId = locationId;
    }

    // --------------------------------------------------------------------------------------------------- streetAddress
    @Nullable
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(@Nullable final String streetAddress) {
        this.streetAddress = streetAddress;
    }

    // ------------------------------------------------------------------------------------------------------ postalCode
    @Nullable
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(@Nullable final String postalCode) {
        this.postalCode = postalCode;
    }

    // ------------------------------------------------------------------------------------------------------------ city
    @Nonnull
    public String getCity() {
        return city;
    }

    public void setCity(@Nonnull final String city) {
        this.city = city;
    }

    // --------------------------------------------------------------------------------------------------- stateProvince
    @Nullable
    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(@Nullable final String stateProvince) {
        this.stateProvince = stateProvince;
    }

    // ------------------------------------------------------------------------------------------------------- countryId
    @Nullable
    public String getCountryId() {
        return countryId;
    }

    protected void setCountryId(@Nullable final String countryId) {
        this.countryId = countryId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Max(COLUMN_MAX_LOCATION_ID)
    @Min(COLUMN_MIN_LOCATION_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_LOCATION_ID, nullable = false, insertable = true, updatable = false,
            precision = COLUMN_PRECISION_LOCATION_ID)
    private Integer locationId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = SIZE_MAX_STREET_ADDRESS)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_STREET_ADDRESS, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_STREET_ADDRESS)
    private String streetAddress;

    @Nullable
    @Size(max = SIZE_MAX_POSTAL_CODE)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_POSTAL_CODE, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_POSTAL_CODE)
    private String postalCode;

    @Nonnull
    @Size(max = SIZE_MAX_CITY)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_CITY, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_CITY)
    private String city;

    @Nullable
    @Size(max = SIZE_MAX_STREET_PROVINCE)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_STATE_PROVINCE, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_STREET_PROVINCE)
    private String stateProvince;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = SIZE_MAX_COUNTRY_ID)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_COUNTRY_ID)
    private String countryId;
}
