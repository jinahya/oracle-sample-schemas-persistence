package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory_PersistenceIT;
import com.github.jinahya.persistence.mapped.test.__Disable_PersistEntityInstance_Test;
import jakarta.annotation.Nonnull;

//@org.junit.jupiter.api.Disabled
@__Disable_PersistEntityInstance_Test
class JobHistoryWithEmbeddedId_PersistenceIT
        extends MappedJobHistory_PersistenceIT<JobHistoryWithEmbeddedId> {

    JobHistoryWithEmbeddedId_PersistenceIT() {
        super(JobHistoryWithEmbeddedId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected void selectRandomEntityInstance(@Nonnull final JobHistoryWithEmbeddedId entityInstance) {
        super.selectRandomEntityInstance(entityInstance);
    }
}
