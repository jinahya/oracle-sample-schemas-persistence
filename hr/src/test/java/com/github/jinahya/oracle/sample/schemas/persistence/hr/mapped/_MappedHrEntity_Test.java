package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class _MappedHrEntity_Test<
        ENTITY extends _MappedHrEntity<ID>,
        ID
        >
        extends __MappedEntity_Test<ENTITY, ID> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected _MappedHrEntity_Test(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }
}
