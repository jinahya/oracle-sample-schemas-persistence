package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceIT;
import com.github.jinahya.persistence.mapped.test.__Disable_PersistEntityInstance_Test;

@__Disable_PersistEntityInstance_Test
class JobHistory_PersistenceIT extends _MappedHrEntity_PersistenceIT<JobHistory, JobHistoryId> {

    JobHistory_PersistenceIT() {
        super(JobHistory.class, JobHistoryId.class);
    }
}
