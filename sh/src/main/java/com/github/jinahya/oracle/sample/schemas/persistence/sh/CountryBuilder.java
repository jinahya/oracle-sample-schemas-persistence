package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedCountryBuilder;

class CountryBuilder extends MappedCountryBuilder<CountryBuilder, Country> {

    // -----------------------------------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    CountryBuilder() {
        super(Country.class);
    }
}
