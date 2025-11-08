package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartmentBuilder;
import jakarta.annotation.Nullable;

import java.util.Optional;

public class DepartmentBuilder extends MappedDepartmentBuilder<DepartmentBuilder, Department> {

    protected DepartmentBuilder() {
        super(Department.class);
    }

    @Nullable
    public Location location() {
        return location;
    }

    public DepartmentBuilder location(@Nullable final Location location) {
        this.location = location;
        return this;
    }

    private Location location;
}
