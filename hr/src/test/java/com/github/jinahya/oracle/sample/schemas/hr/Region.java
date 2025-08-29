package com.github.jinahya.oracle.sample.schemas.hr;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = MappedRegion.TABLE_NAME)
class Region extends MappedRegion {

    static MappedRegionBuilder<?, Region> builder() {
        return new RegionBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Region() {
        super();
    }

    Region(final RegionBuilder builder) {
        super(builder);
    }
}
