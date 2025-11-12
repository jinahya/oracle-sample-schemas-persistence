package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory_PersistenceIT;
import com.github.jinahya.persistence.mapped.test.__Disable_PersistEntityInstance_Test;

//@org.junit.jupiter.api.Disabled
@__Disable_PersistEntityInstance_Test
class JobHistoryWithIdClass_PersistenceIT extends MappedJobHistory_PersistenceIT<JobHistoryWithIdClass> {

    JobHistoryWithIdClass_PersistenceIT() {
        super(JobHistoryWithIdClass.class);
    }
}
