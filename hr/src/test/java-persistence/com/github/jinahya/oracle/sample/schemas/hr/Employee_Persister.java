package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister_Utils;
import jakarta.persistence.EntityManager;

import java.util.concurrent.ThreadLocalRandom;

class Employee_Persister extends __MappedEntity_Persister<Employee> {

    Employee_Persister() {
        super(Employee.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected Employee persist(EntityManager entityManager, Employee entityInstance) {
        entityInstance.setJob(
                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Job.class, entityManager).orElseThrow()
        );
        entityInstance.setManager(
                ThreadLocalRandom.current().nextBoolean()
                ? __MappedEntity_Persister_Utils.newPersistedInstanceOf(Employee.class, entityManager).orElseThrow()
                : null
        );
        entityInstance.setDepartment(
                ThreadLocalRandom.current().nextBoolean()
                ? __MappedEntity_Persister_Utils.newPersistedInstanceOf(Department.class, entityManager).orElseThrow()
                : null
        );
        return super.persist(entityManager, entityInstance);
    }
}