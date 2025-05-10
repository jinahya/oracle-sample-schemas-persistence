package com.github.jinahya.oracle.sample.schemas;

import uk.co.jemos.podam.api.PodamFactory;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class _MappedEntity_Randomizer<ENTITY extends _MappedEntity<ENTITY>>
        extends __MappedEntity_Randomizer<ENTITY> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for generating randomized instance of specified entity class.
     *
     * @param entityClass the entity class.
     */
    _MappedEntity_Randomizer(final Class<ENTITY> entityClass) {
        super(entityClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected PodamFactory factory() {
        return super.factory();
    }

    @Override
    protected ENTITY get() {
        return super.get();
    }

    @Override
    Class<? super ENTITY> superclassDeclaring(String attributeName) {
        return super.superclassDeclaring(attributeName);
    }
}