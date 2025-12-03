package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartmentBuilder;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedLocation;

import java.util.Optional;

class DepartmentBuilder extends MappedDepartmentBuilder<DepartmentBuilder, Department> {

    DepartmentBuilder() {
        super(Department.class);
    }

    // ------------------------------------------------------------------------------------------------ super.locationId
    @Override
    public Integer locationId() {
//        return super.locationId();
        return Optional.ofNullable(location()).map(MappedLocation::getLocationId).orElse(null);
    }

    @Override
    protected DepartmentBuilder locationId(final Integer locationId) {
//        return super.locationId(locationId);
        throw new UnsupportedOperationException("not supported; use location(Location)");
    }

    // -------------------------------------------------------------------------------------------------------- location
    public Location location() {
        return location;
    }

    public DepartmentBuilder location(final Location location) {
        this.location = location;
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Location location;
}
