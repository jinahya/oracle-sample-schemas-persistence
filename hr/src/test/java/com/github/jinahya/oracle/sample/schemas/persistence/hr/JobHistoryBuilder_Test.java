package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntityBuilder_Test;

class JobHistoryBuilder_Test extends _MappedHrEntityBuilder_Test<JobHistoryBuilder, JobHistory, JobHistoryId> {

    JobHistoryBuilder_Test() {
        super(JobHistoryBuilder.class, JobHistory.class, JobHistoryId.class);
    }
}
