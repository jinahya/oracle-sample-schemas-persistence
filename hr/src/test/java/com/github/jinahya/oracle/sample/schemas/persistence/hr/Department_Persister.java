package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_Persister;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

import java.util.concurrent.ThreadLocalRandom;

class Department_Persister extends _MappedHrEntity_Persister<Department, Integer> {

    Department_Persister() {
        super(Department.class, Integer.class);
    }

    @Override
    public void persist(@Nonnull EntityManager entityManager, @Nonnull Department entityInstance) {
        entityInstance.setManager(
                ThreadLocalRandom.current().nextBoolean()
                ? null
                : newPersistedInstanceOf(entityManager, Employee.class)
        );
        entityInstance.setLocation(
                ThreadLocalRandom.current().nextBoolean()
                ? null
                : newPersistedInstanceOf(entityManager, Location.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
