package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedLocationBuilder;

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
        return super.countryId();
    }

    @Deprecated(forRemoval = true)
    @Override
    public LocationBuilder countryId(final String countryId) {
        return super.countryId(countryId);
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
