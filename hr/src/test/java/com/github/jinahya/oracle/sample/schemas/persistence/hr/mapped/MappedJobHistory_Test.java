package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId;

public abstract class MappedJobHistory_Test<ENTITY extends MappedJobHistory>
        extends _MappedHrEntity_Test<ENTITY, JobHistoryId> {

    protected MappedJobHistory_Test(final Class<ENTITY> entityClass) {
        super(entityClass, JobHistoryId.class);
    }
}
