package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartmentBuilder;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedLocation;

import java.util.Optional;

class DepartmentBuilder extends MappedDepartmentBuilder<DepartmentBuilder, Department> {

    DepartmentBuilder() {
        super(Department.class);
    }

    // --------------------------------------------------------------------------------------------------------- manager
    public Employee getManager() {
        return manager;
    }

    public DepartmentBuilder setManager(Employee manager) {
        this.manager = manager;
        return managerId(
                Optional.of(this.manager)
                        .map(MappedEmployee::getEmployeeId)
                        .orElse(null)
        );
    }

    // -------------------------------------------------------------------------------------------------------- location
    public Location location() {
        return location;
    }

    public DepartmentBuilder location(final Location location) {
        this.location = location;
        return locationId(
                Optional.ofNullable(this.location)
                        .map(MappedLocation::getLocationId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Employee manager;

    private Location location;
}
