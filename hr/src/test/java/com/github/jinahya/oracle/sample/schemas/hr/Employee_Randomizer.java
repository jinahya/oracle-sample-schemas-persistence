package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.concurrent.ThreadLocalRandom;

class Employee_Randomizer extends __MappedEntity_Randomizer<Employee> {

    Employee_Randomizer() {
        super(Employee.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy dataProviderStrategy() {
        return super.dataProviderStrategy();
    }

    @Override
    protected ClassInfoStrategy classInfoStrategy() {
        return super.classInfoStrategy();
    }

    @Override
    protected PodamFactory podamFactory() {
        return super.podamFactory();
    }

    @Override
    protected Employee manufacturePojo() {
        final var employee = super.manufacturePojo();
        employee.setSalary(
                ThreadLocalRandom.current().nextBoolean()
                ? null
                : Employee_TestUtils.randomSalary()
        );
        employee.setCommissionPct(
                ThreadLocalRandom.current().nextBoolean()
                ? null
                : Employee_TestUtils.randomCommissionPct()
        );
        return employee;
    }
}