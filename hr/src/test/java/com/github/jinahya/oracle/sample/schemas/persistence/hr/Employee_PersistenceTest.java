package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceTest;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Employee_PersistenceTest extends _MappedHrEntity_PersistenceTest<Employee, Integer> {

    Employee_PersistenceTest() {
        super(Employee.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected void __persistEntityInstance(final EntityManager entityManager, final Employee persisted) {
        super.__persistEntityInstance(entityManager, persisted);
    }

    @Override
    protected void __persistEntityInstance(final Employee persisted) {
        super.__persistEntityInstance(persisted);
    }
}
