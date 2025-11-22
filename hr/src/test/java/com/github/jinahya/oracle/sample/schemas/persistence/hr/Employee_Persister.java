package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_Persister;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_Randomizer;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.concurrent.ThreadLocalRandom;

class Employee_Persister extends _MappedHrEntity_Persister<Employee, Integer> {

    Employee_Persister() {
        super(Employee.class, Integer.class);
    }

    @Override
    public void persist(final @Nonnull EntityManager entityManager, final @Nonnull Employee entityInstance) {
        entityInstance.setManager(
                ThreadLocalRandom.current().nextBoolean()
                ? null
                : newPersistedInstanceOf(entityManager, Employee.class)
        );
        entityInstance.setDepartment(
                ThreadLocalRandom.current().nextBoolean()
                ? null
                : newPersistedInstanceOf(entityManager, Department.class)
        );
        super.persist(entityManager, entityInstance);
    }
}