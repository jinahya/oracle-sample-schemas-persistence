package com.github.jinahya.oracle.sample.schemas;

import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class _MappedEntity_Randomizer<ENTITY extends _MappedEntity<ENTITY>>
        extends __MappedEntity_Randomizer<ENTITY> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for generating randomized instance of specified entity class.
     *
     * @param entityClass             the entity class.
     * @param attributeNamesToExclude attribute names to exclude from randomization.
     */
    protected _MappedEntity_Randomizer(final Class<ENTITY> entityClass, final String... attributeNamesToExclude) {
        super(entityClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy dataProviderStrategy() {
        return super.dataProviderStrategy();
    }

    @Override
    protected PodamFactory podamFactory() {
        return super.podamFactory();
    }

    @Override
    protected ClassInfoStrategy classInfoStrategy() {
        return super.classInfoStrategy();
    }

    @Override
    protected ENTITY manufacturePojo() {
        return super.manufacturePojo();
    }
}