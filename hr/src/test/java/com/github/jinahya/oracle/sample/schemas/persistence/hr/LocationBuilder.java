package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedCountry;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedLocationBuilder;

import java.util.Optional;

public class LocationBuilder extends MappedLocationBuilder<LocationBuilder, Location> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    LocationBuilder() {
        super(Location.class);
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
    @Deprecated(forRemoval = true)
    @Override
    public String countryId() {
//        return super.countryId();
        return Optional.ofNullable(country()).map(MappedCountry::getCountryId).orElse(null);
    }

    @Deprecated(forRemoval = true)
    @Override
    protected LocationBuilder countryId(final String countryId) {
//        return super.countryId(countryId);
        throw new UnsupportedOperationException("not supported; use country(Country)");
    }

    // --------------------------------------------------------------------------------------------------------- country
    public Country country() {
        return country;
    }

    public LocationBuilder country(final Country country) {
        this.country = country;
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Country country;
}
