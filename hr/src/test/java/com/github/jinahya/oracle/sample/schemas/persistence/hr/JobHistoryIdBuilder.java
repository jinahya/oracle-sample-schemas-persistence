package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistoryIdBuilder;

public class JobHistoryIdBuilder extends MappedJobHistoryIdBuilder<JobHistoryIdBuilder, JobHistoryId> {

    JobHistoryIdBuilder() {
        super(JobHistoryId.class);
    }
}
