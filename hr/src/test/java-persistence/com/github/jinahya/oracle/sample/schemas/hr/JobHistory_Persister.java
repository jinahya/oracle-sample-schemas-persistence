package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister_Utils;
import jakarta.persistence.EntityManager;

class JobHistory_Persister extends __MappedEntity_Persister<JobHistory> {

    JobHistory_Persister() {
        super(JobHistory.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected JobHistory persist(final EntityManager entityManager, final JobHistory entityInstance) {
        entityInstance.setEmployee(
                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Employee.class, entityManager).orElseThrow()
        );
        entityInstance.setJob(
                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Job.class, entityManager).orElseThrow()
        );
        entityInstance.setDepartment(
                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Department.class, entityManager).orElseThrow()
        );
        return super.persist(entityManager, entityInstance);
    }
}