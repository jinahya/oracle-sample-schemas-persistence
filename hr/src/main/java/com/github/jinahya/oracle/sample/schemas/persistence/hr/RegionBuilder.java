package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;
import jakarta.annotation.Nonnull;

public class RegionBuilder extends __MappedEntityBuilder<RegionBuilder, Region> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    RegionBuilder() {
        super(Region.class);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ----------------------------------------------------------------------------------------------------------- super
    @Nonnull
    @Override
    public Region build() {
        return new Region(this);
    }

    // -------------------------------------------------------------------------------------------------------- regionId
    public Long regionId() {
        return regionId;
    }

    public RegionBuilder regionId(final Long regionId) {
        this.regionId = regionId;
        return this;
    }

    // ------------------------------------------------------------------------------------------------------ regionName
    public String regionName() {
        return regionName;
    }

    public RegionBuilder regionName(final String regionName) {
        this.regionName = regionName;
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Long regionId;

    private String regionName;
}
