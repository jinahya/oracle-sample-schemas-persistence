package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;
import jakarta.annotation.Nonnull;

import java.util.Optional;

public class CountryBuilder extends __MappedEntityBuilder<CountryBuilder, Country> {

    // -----------------------------------------------------------------------------------------------------------------
    CountryBuilder() {
        super(Country.class);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ----------------------------------------------------------------------------------------------------------- super
    @Nonnull
    @Override
    public Country build() {
        return new Country(this);
    }

    // ------------------------------------------------------------------------------------------------------- countryId
    public String countryId() {
        return countryId;
    }

    public CountryBuilder countryId(final String countryId) {
        this.countryId = countryId;
        return this;
    }

    // ----------------------------------------------------------------------------------------------------- countryName
    public String countryName() {
        return countryName;
    }

    public CountryBuilder countryName(final String countryName) {
        this.countryName = countryName;
        return this;
    }

    // -------------------------------------------------------------------------------------------------------- regionId
    @Deprecated(forRemoval = true)
    public Long regionId() {
        return regionId;
    }

    @Deprecated(forRemoval = true)
    public CountryBuilder regionId(final Long regionId) {
        this.regionId = regionId;
        return this;
    }

    // ---------------------------------------------------------------------------------------------------------- region
    public Region getRegion() {
        return region;
    }

    public CountryBuilder setRegion(final Region region) {
        this.region = region;
        return regionId(
                Optional.ofNullable(this.region)
                        .map(Region::getRegionId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String countryId;

    private String countryName;

    @Deprecated(forRemoval = true)
    private Long regionId;

    private Region region;
}
