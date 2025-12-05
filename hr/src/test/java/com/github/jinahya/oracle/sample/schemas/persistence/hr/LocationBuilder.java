package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedCountry;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedLocationBuilder;

import java.util.Optional;

class LocationBuilder extends MappedLocationBuilder<LocationBuilder, Location> {

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

    // --------------------------------------------------------------------------------------------------------- country
    public Country country() {
        return country;
    }

    public LocationBuilder country(final Country country) {
        this.country = country;
        return countryId(
                Optional.ofNullable(this.country)
                        .map(MappedCountry::getCountryId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Country country;
}
