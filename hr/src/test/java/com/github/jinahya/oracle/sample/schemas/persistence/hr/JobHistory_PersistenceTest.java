package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceTest;
import com.github.jinahya.persistence.mapped.test.__Disable_PersistEntityInstance_Test;

@__Disable_PersistEntityInstance_Test
class JobHistory_PersistenceTest extends _MappedHrEntity_PersistenceTest<JobHistory, JobHistoryId> {

    JobHistory_PersistenceTest() {
        super(JobHistory.class, JobHistoryId.class);
    }
}
