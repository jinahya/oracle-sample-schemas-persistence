package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_Persister;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersisterUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

import java.util.concurrent.ThreadLocalRandom;

class Department_Persister extends _MappedHrEntity_Persister<Department, Integer> {

    Department_Persister() {
        super(Department.class, Integer.class);
    }

    @Override
    public void persist(@Nonnull EntityManager entityManager, @Nonnull Department entityInstance) {
        entityInstance.setManagerId(null);
        entityInstance.setLocation(
                ThreadLocalRandom.current().nextBoolean()
                ? null
                : __MappedEntity_PersisterUtils.newPersistedInstanceOf(entityManager, Location.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
