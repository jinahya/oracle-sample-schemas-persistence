package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Optional;

@Entity(name = "")
@Table(
        catalog = "",
        schema = "",
        name = "LOCATIONS",
        options = "",
        comment = "",
        indexes = {
                @Index(
                        name = "",
                        columnList = "CITY",
                        unique = false
                ),
                @Index(
                        name = "",
                        columnList = "COUNTRY_ID",
                        unique = false
                ),
                @Index(
                        name = "",
                        columnList = "STATE_PROVINCE",
                        unique = false
                )
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "",
                        columnNames = {"LOCATION_ID"}
                )
        },
        check = {
                @CheckConstraint(
                        name = "",
                        constraint = "CITY IS NOT NULL",
                        options = ""
                )
        }
)
public class Location extends __MappedEntity<Integer> {

    protected Location() {
        super();
    }

    @Override
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Location that)) {
            return false;
        }
        return Objects.equals(getLocationId(), that.getLocationId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getLocationId());
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Nullable
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(@Nullable String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @Nullable
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(@Nullable String postalCode) {
        this.postalCode = postalCode;
    }

    @Nonnull
    public String getCity() {
        return city;
    }

    public void setCity(@Nonnull String city) {
        this.city = city;
    }

    @Nullable
    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(@Nullable String stateProvince) {
        this.stateProvince = stateProvince;
    }

    @Nullable
    public String getCountryId() {
        return countryId;
    }

    void setCountryId(@Nullable final String countryId) {
        this.countryId = countryId;
    }

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

    @Max(+9999)
    @Min(-9999)
    @NotNull
    @Id
//    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = "LOCATION_ID", nullable = false, insertable = true, updatable = true, precision = 4, scale = 0)
    private Integer locationId;

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
//    @NotNull
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = "STATE_PROVINCE", nullable = true, insertable = true, updatable = true, length = 25)
    private String stateProvince;

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