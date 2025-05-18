package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.util.Optional;

/**
 * An entity class for mapping {@value Location#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = Location.TABLE_NAME)
public class Location extends __MappedEntity<Location, Integer> {

    @Serial
    private static final long serialVersionUID = -4694523644917310264L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "LOCATIONS";

    // ----------------------------------------------------------------------------------------------------- LOCATION_ID
    public static final String COLUMN_NAME_LOCATION_ID = "LOCATION_ID";

    public static final int COLUMN_PRECISION_LOCATION_ID = 4;

    public static final int COLUMN_VALUE_MAX_LOCATION_ID = 0x270F; // 9999

    public static final int COLUMN_VALUE_MIN_LOCATION_ID = 0x0000;

    // -------------------------------------------------------------------------------------------------- STREET_ADDRESS
    public static final String COLUMN_NAME_STREET_ADDRESS = "STREET_ADDRESS";

    public static final int COLUMN_LENGTH_STREET_ADDRESS = 40;

    public static final int SIZE_MAX_STREET_ADDRESS = COLUMN_LENGTH_STREET_ADDRESS;

    // ----------------------------------------------------------------------------------------------------- POSTAL_CODE
    public static final String COLUMN_NAME_POSTAL_CODE = "POSTAL_CODE";

    public static final int COLUMN_LENGTH_POSTAL_CODE = 12;

    public static final int SIZE_MAX_POSTAL_CODE = COLUMN_LENGTH_POSTAL_CODE;

    // ------------------------------------------------------------------------------------------------------------ CITY
    public static final String COLUMN_NAME_CITY = "CITY";

    public static final int COLUMN_LENGTH_CITY = 30;

    public static final int SIZE_MAX_CITY = COLUMN_LENGTH_CITY;

    // -------------------------------------------------------------------------------------------------- STATE_PROVINCE
    public static final String COLUMN_NAME_STATE_PROVINCE = "STATE_PROVINCE";

    public static final int COLUMN_LENGTH_STREET_PROVINCE = 25;

    public static final int SIZE_MAX_STREET_PROVINCE = COLUMN_LENGTH_STREET_PROVINCE;

    // ----------------------------------------------------------------------------------------------------- LOCATION_ID
    public static final String COLUMN_NAME_COUNTRY_ID = "COUNTRY_ID";

    public static final int COLUMN_LENGTH_COUNTRY_ID = 2;

    public static final int SIZE_MAX_COUNTRY_ID = COLUMN_LENGTH_COUNTRY_ID;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Location() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
                "locationId=" + locationId +
                ",streetAddress='" + streetAddress +
                ",postalCode=" + postalCode +
                ",city=" + city + '\'' +
                ",stateProvince=" + stateProvince +
                ",countryId=" + countryId +
//                ",country=" + country +
                '}';
    }

    // ------------------------------------------------------------------------------------------------------ super._id_
    @Override
    protected final Integer _id_() {
        return getLocationId();
    }

    @Override
    protected final void _id_(final Integer _id_) {
        setLocationId(_id_);
    }

    // ------------------------------------------------------------------------------------------------------ locationId
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(final Integer locationId) {
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
    @Nonnull
    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(@Nonnull final String stateProvince) {
        this.stateProvince = stateProvince;
    }

    // ------------------------------------------------------------------------------------------------------- countryId
    @Deprecated(forRemoval = true)
    @Nonnull
    protected String getCountryId() {
        return countryId;
    }

    @Deprecated(forRemoval = true)
    protected void setCountryId(@Nonnull final String countryId) {
        this.countryId = countryId;
    }

    // --------------------------------------------------------------------------------------------------------- country
    @Nonnull
    public Country getCountry() {
        return country;
    }

    public void setCountry(@Nonnull final Country country) {
        this.country = country;
        setCountryId(
                Optional.ofNullable(this.country)
                        .map(Country::getCountryId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @jakarta.annotation.Nonnull
    @Max(COLUMN_VALUE_MAX_LOCATION_ID)
    @Min(COLUMN_VALUE_MIN_LOCATION_ID)
    @Id
    @Column(name = COLUMN_NAME_LOCATION_ID, nullable = false, insertable = true, updatable = false,
            precision = COLUMN_PRECISION_LOCATION_ID)
    private Integer locationId;

    @jakarta.annotation.Nullable
    @Size(max = SIZE_MAX_STREET_ADDRESS)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_STREET_ADDRESS, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_STREET_ADDRESS)
    private String streetAddress;

    @jakarta.annotation.Nullable
    @Size(max = SIZE_MAX_POSTAL_CODE)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_POSTAL_CODE, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_POSTAL_CODE)
    private String postalCode;

    @jakarta.annotation.Nonnull
    @Size(max = SIZE_MAX_CITY)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_CITY, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_CITY)
    private String city;

    @jakarta.annotation.Nonnull
    @Size(max = SIZE_MAX_STREET_PROVINCE)
    @NotNull
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_STATE_PROVINCE, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_STREET_PROVINCE)
    private String stateProvince;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    @jakarta.annotation.Nonnull
    @Size(max = SIZE_MAX_COUNTRY_ID)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_COUNTRY_ID)
    private String countryId;

    @jakarta.annotation.Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_COUNTRY_ID, nullable = false, insertable = false, updatable = false)
    private Country country;
}