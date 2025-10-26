package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Optional;

@NamedQuery(
        name = "Location.select_WhereCountryIdEqualStateProvinceEqualCityEqual_OrderByStreetAddressAsc",
        query = """
                SELECT e
                FROM Location e
                WHERE e.countryId = :countryId
                  AND e.stateProvince = :stateProvince
                  AND e.city = :city
                ORDER BY e.streetAddress ASC"""
)
@NamedQuery(
        name = "Location.select_WhereCountryIdEqualCityEqual_OrderByStreetAddressAsc",
        query = """
                SELECT e
                FROM Location e
                WHERE e.countryId = :countryId
                  AND e.city = :city
                ORDER BY e.streetAddress ASC"""
)
@NamedQuery(
        name = "Location.select_WhereCountryIdEqualStateProvinceEqual_OrderByStreetAddressAsc",
        query = """
                SELECT e
                FROM Location e
                WHERE e.countryId = :countryId
                  AND e.stateProvince = :stateProvince
                ORDER BY e.streetAddress ASC"""
)
@NamedQuery(
        name = "Location.select_WhereCountryIdEqual_OrderByLocationIdAsc",
        query = """
                SELECT e
                FROM Location e
                WHERE e.countryId = :countryId
                ORDER BY e.locationId ASC"""
)
@NamedQuery(
        name = "Location.select_OrderByLocationIdAsc",
        query = """
                SELECT e
                FROM Location e
                ORDER BY e.locationId ASC"""
)
@Entity
@Table(name = "LOCATIONS")
public class Location extends __MappedEntity<Integer> {

    public static final String ATTRIBUTE_NAME_COUNTRY = "country";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Location() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    public String toString() {
        return "Location{" +
               "locationId=" + locationId +
               ", streetAddress='" + streetAddress + '\'' +
               ", postalCode='" + postalCode + '\'' +
               ", city='" + city + '\'' +
               ", stateProvince='" + stateProvince + '\'' +
               ", countryId='" + countryId + '\'' +
               '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Location that)) {
            return false;
        }
        return Objects.equals(getLocationId(), that.getLocationId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getLocationId());
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    // ------------------------------------------------------------------------------------------------ super.locationId
    public Integer getLocationId() {
        return locationId;
    }

    protected void setLocationId(final Integer locationId) {
        this.locationId = locationId;
    }

    // --------------------------------------------------------------------------------------------- super.streetAddress
    @Nullable
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(@Nullable final String streetAddress) {
        this.streetAddress = streetAddress;
    }

    // ------------------------------------------------------------------------------------------------ super.postalCode
    @Nullable
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(@Nullable final String postalCode) {
        this.postalCode = postalCode;
    }

    // ------------------------------------------------------------------------------------------------------ super.city
    @Nonnull
    public String getCity() {
        return city;
    }

    public void setCity(@Nonnull final String city) {
        this.city = city;
    }

    // --------------------------------------------------------------------------------------------- super.stateProvince
    @Nullable
    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(@Nullable final String stateProvince) {
        this.stateProvince = stateProvince;
    }

    // ------------------------------------------------------------------------------------------------- super.countryId
    @Nullable
    public String getCountryId() {
        return countryId;
    }

    protected void setCountryId(@Nullable final String countryId) {
        this.countryId = countryId;
    }

    // --------------------------------------------------------------------------------------------------------- country
    @Nullable
    public Country getCountry() {
        return country;
    }

    public void setCountry(@Nullable final Country country) {
        this.country = country;
        setCountryId(
                Optional.ofNullable(this.country)
                        .map(Country::getCountryId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Max(+9999)
    @Min(-9999)
    @NotNull
    @Id
    @Column(name = "LOCATION_ID", nullable = false, insertable = true, updatable = false, precision = 4, scale = 0)
    private Integer locationId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(min = 0, max = 40)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = "STREET_ADDRESS", nullable = true, insertable = true, updatable = true, length = 40)
    private String streetAddress;

    @Nullable
    @Size(min = 0, max = 12)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = "POSTAL_CODE", nullable = true, insertable = true, updatable = true, length = 12)
    private String postalCode;

    @Nonnull
    @Size(min = 0, max = 30)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = "CITY", nullable = false, insertable = true, updatable = true, length = 30)
    private String city;

    @Nullable
    @Size(min = 0, max = 25)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = "STATE_PROVINCE", nullable = true, insertable = true, updatable = true, length = 25)
    private String stateProvince;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(min = 2, max = 2)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = "COUNTRY_ID", nullable = true, insertable = true, updatable = true, length = 2)
    private String countryId;

    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID", nullable = true, insertable = false, updatable = false)
    private Country country;
}
