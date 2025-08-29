package com.github.jinahya.oracle.sample.schemas.hr;

class CountryBuilder extends MappedCountryBuilder<CountryBuilder, Country, Region> {

    CountryBuilder() {
        super(Country.class);
    }
}
