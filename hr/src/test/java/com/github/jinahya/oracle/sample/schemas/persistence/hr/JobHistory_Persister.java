package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_Persister;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersisterUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

import java.util.concurrent.ThreadLocalRandom;

class JobHistory_Persister extends _MappedHrEntity_Persister<JobHistory, JobHistoryId> {

    JobHistory_Persister() {
        super(JobHistory.class, JobHistoryId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final JobHistory entityInstance) {
        // TODO: set employee
        entityInstance.setJob(
                __MappedEntity_PersisterUtils.newPersistedInstanceOf(entityManager, Job.class)
        );
        entityInstance.setDepartment(
                ThreadLocalRandom.current().nextBoolean()
                ? null
                : __MappedEntity_PersisterUtils.newPersistedInstanceOf(entityManager, Department.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
