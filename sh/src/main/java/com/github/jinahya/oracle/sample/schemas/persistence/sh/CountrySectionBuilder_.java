package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedCountrySectionBuilder_;

@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class CountrySectionBuilder_ extends MappedCountrySectionBuilder_<CountrySectionBuilder_, CountrySection_> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    CountrySectionBuilder_() {
        super(CountrySection_.class);
    }
}
