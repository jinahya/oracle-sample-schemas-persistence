package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistoryBuilder;

@Deprecated(forRemoval = true)
class JobHistoryBuilder extends MappedJobHistoryBuilder<JobHistoryBuilder, JobHistory, JobHistoryId> {

    JobHistoryBuilder() {
        super(JobHistory.class);
    }

    // ------------------------------------------------------------------------------------------------------------- job
    public Job job() {
        return job;
    }

    public JobHistoryBuilder gob(final Job job) {
        this.job = job;
        return this;
    }

    // ------------------------------------------------------------------------------------------------------ department
    public Department department() {
        return department;
    }

    public JobHistoryBuilder department(final Department department) {
        this.department = department;
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Job job;

    private Department department;
}
