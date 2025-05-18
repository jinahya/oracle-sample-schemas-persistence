package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister_Utils;
import jakarta.persistence.EntityManager;

import java.util.concurrent.ThreadLocalRandom;

class Department_Persister extends __MappedEntity_Persister<Department> {

    Department_Persister() {
        super(Department.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected Department persist(EntityManager entityManager, Department entityInstance) {
        entityInstance.setManager(null);
        entityInstance.setLocation(
                ThreadLocalRandom.current().nextBoolean()
                ? __MappedEntity_Persister_Utils.newPersistedInstanceOf(Location.class, entityManager).orElseThrow() :
                null
        );
        return super.persist(entityManager, entityInstance);
    }
}