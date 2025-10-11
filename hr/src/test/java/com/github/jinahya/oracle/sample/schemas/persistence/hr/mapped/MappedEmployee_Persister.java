package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedEmployee_Persister<ENTITY extends MappedEmployee>
        extends _MappedHrEntity_Persister<ENTITY, Integer> {

    protected MappedEmployee_Persister(final Class<ENTITY> entityClass) {
        super(entityClass, Integer.class);
    }
}
