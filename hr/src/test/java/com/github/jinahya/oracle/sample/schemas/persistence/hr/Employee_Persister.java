package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_Persister;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
class Employee_Persister extends _MappedHrEntity_Persister<Employee, Integer> {

    Employee_Persister() {
        super(Employee.class, Integer.class);
    }

    @Override
    public void persist(final @Nonnull EntityManager entityManager, final @Nonnull Employee entityInstance) {
        entityInstance.setJob(
                newPersistedInstanceOf(entityManager, Job.class)
        );
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
        if (true) {
            entityManager.flush(); // fuck eclipselink, or fuck myself.
        }
        super.persist(entityManager, entityInstance);
    }
}
