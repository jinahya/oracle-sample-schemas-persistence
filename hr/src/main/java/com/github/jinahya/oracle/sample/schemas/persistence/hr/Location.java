package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedLocation;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

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
@Table(name = MappedLocation.TABLE_NAME)
public class Location extends MappedLocation {

    public static final String ATTRIBUTE_NAME_COUNTRY = "country";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // -------------------------------------------------------------------------------------------------------- BUILDERS
    public static LocationBuilder builder() {
        return new LocationBuilder();
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Location() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder to build from.
     */
    private Location(final LocationBuilder builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    // ------------------------------------------------------------------------------------------------ super.locationId

    // --------------------------------------------------------------------------------------------- super.streetAddress

    // ------------------------------------------------------------------------------------------------ super.postalCode

    // ------------------------------------------------------------------------------------------------------ super.city

    // --------------------------------------------------------------------------------------------- super.stateProvince

    // ------------------------------------------------------------------------------------------------- super.countryId

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
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = MappedLocation.COLUMN_NAME_COUNTRY_ID, nullable = true, insertable = false, updatable = false)
    private Country country;
}
