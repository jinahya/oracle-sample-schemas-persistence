package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntityBuilder_Test;

class JobBuilder_Test extends _MappedHrEntityBuilder_Test<JobBuilder, Job, String> {

    JobBuilder_Test() {
        super(JobBuilder.class, Job.class, String.class);
    }
}
