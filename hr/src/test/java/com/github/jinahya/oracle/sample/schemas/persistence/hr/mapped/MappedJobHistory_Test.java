package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

public abstract class MappedJobHistory_Test<ENTITY extends MappedJobHistory<ID>, ID extends MappedJobHistoryId>
        extends _MappedHrEntity_Test<ENTITY, ID> {

    protected MappedJobHistory_Test(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }
}
