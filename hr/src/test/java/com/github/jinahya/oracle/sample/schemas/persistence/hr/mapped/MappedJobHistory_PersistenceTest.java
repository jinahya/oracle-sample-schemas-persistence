package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId;
import com.github.jinahya.persistence.mapped.test.__Disable_PersistEntityInstance_Test;

@__Disable_PersistEntityInstance_Test
public abstract class MappedJobHistory_PersistenceTest<ENTITY extends MappedJobHistory>
        extends _MappedHrEntity_PersistenceTest<ENTITY, JobHistoryId> {

    protected MappedJobHistory_PersistenceTest(final Class<ENTITY> entityClass) {
        super(entityClass, JobHistoryId.class);
    }
}
