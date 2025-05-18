package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister;
import jakarta.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

class Job_Persister extends __MappedEntity_Persister<Job> {

    Job_Persister() {
        super(Job.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected Job persist(final EntityManager entityManager, final Job entityInstance) {
        {
            final var minSalary = entityInstance.getMinSalary();
            final var maxSalary = entityInstance.getMaxSalary();
            if (minSalary != null && maxSalary != null && minSalary > maxSalary) {
                entityInstance.setMinSalary(maxSalary);
            }
        }
        assertThat(entityInstance.getMinSalary()).isLessThanOrEqualTo(entityInstance.getMaxSalary());
        return super.persist(entityManager, entityInstance);
    }
}