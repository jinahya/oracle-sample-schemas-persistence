package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_Randomizer;
import jakarta.annotation.Nonnull;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

class Employee_Randomizer extends _MappedHrEntity_Randomizer<Employee, Integer> {

    Employee_Randomizer() {
        super(Employee.class, Integer.class,
              MappedEmployee.ATTRIBUTE_NAME_JOB_ID,
              MappedEmployee.ATTRIBUTE_NAME_JOB,
              MappedEmployee.ATTRIBUTE_NAME_MANAGER_ID,
              MappedEmployee.ATTRIBUTE_NAME_MANAGER,
              MappedEmployee.ATTRIBUTE_NAME_DEPARTMENT_ID,
              MappedEmployee.ATTRIBUTE_NAME_DEPARTMENT,
              MappedEmployee.ATTRIBUTE_NAME_SUBORDINATES
        );
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
    public Employee get() {
        return super.get();
    }
}
