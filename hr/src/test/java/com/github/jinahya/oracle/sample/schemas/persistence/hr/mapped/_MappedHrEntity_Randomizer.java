package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Randomizer;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class _MappedHrEntity_Randomizer<
        ENTITY extends _MappedHrEntity<ID>,
        ID
        >
        extends __MappedEntity_Randomizer<ENTITY, ID> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected _MappedHrEntity_Randomizer(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }
}
