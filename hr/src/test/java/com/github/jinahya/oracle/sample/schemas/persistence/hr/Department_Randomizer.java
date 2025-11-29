package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartment;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_Randomizer;
import jakarta.annotation.Nonnull;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.List;

class Department_Randomizer extends _MappedHrEntity_Randomizer<Department, Integer> {

    Department_Randomizer() {
        super(Department.class, Integer.class, List.of(
                MappedDepartment.ATTRIBUTE_NAME_LOCATION_ID,
                MappedDepartment.ATTRIBUTE_NAME_LOCATION,
                MappedDepartment.ATTRIBUTE_NAME_MANAGER_ID,
                MappedDepartment.ATTRIBUTE_NAME_MANAGER,
                MappedDepartment.ATTRIBUTE_NAME_EMPLOYEES
        ));
    }

    @Nonnull
    @Override
    protected DataProviderStrategy getDataProviderStrategy() {
        return super.getDataProviderStrategy();
    }

    @Nonnull
    @Override
    protected PodamFactory getPodamFactory() {
        return super.getPodamFactory();
    }

    @Nonnull
    @Override
    protected ClassInfoStrategy getClassInfoStrategy() {
        return super.getClassInfoStrategy();
    }

    @Nonnull
    @Override
    public Department get() {
        final var department = super.get();
        return department;
    }
}
