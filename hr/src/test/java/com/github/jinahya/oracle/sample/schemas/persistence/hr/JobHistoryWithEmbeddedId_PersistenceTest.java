package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory_PersistenceTest;
import com.github.jinahya.persistence.mapped.test.__Disable_PersistEntityInstance_Test;
import org.junit.jupiter.api.Disabled;

@Disabled
@__Disable_PersistEntityInstance_Test
class JobHistoryWithEmbeddedId_PersistenceTest
        extends MappedJobHistory_PersistenceTest<JobHistoryWithEmbeddedId, JobHistoryId> {

    JobHistoryWithEmbeddedId_PersistenceTest() {
        super(JobHistoryWithEmbeddedId.class, JobHistoryId.class);
    }
}
