package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId;
import com.github.jinahya.persistence.mapped.test.__Disable_PersistEntityInstance_Test;
import org.glassfish.jaxb.core.v2.model.core.ID;

@__Disable_PersistEntityInstance_Test
public abstract class MappedJobHistory_PersistenceTest<ENTITY extends MappedJobHistory<ID>, ID extends MappedJobHistoryId>
        extends _MappedHrEntity_PersistenceTest<ENTITY, ID> {

    protected MappedJobHistory_PersistenceTest(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }
}
