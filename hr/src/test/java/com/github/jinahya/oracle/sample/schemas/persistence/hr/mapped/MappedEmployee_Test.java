package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedEmployee_Test<ENTITY extends MappedEmployee> extends _MappedHrEntity_Test<ENTITY, Integer> {

    protected MappedEmployee_Test(final Class<ENTITY> entityClass) {
        super(entityClass, Integer.class);
    }
}
