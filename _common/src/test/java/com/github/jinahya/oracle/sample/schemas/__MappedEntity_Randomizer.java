package com.github.jinahya.oracle.sample.schemas;

import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.HashMap;
import java.util.Map;

//abstract class __MappedEntity_Randomizer<T extends __MappedEntity<T, ?>> {
abstract class __MappedEntity_Randomizer<ENTITY extends __MappedEntity<ENTITY, ?>> extends ___Base_Randomizer<ENTITY> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for generating randomized instance of specified entity class.
     *
     * @param entityClass the entity class.
     */
    __MappedEntity_Randomizer(final Class<ENTITY> entityClass) {
        super(entityClass);
        this.entityClass = valueClass;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy strategy() {
        return super.strategy();
    }

    @Override
    protected PodamFactory factory() {
        return super.factory();
    }

    @Override
    protected ENTITY get() {
        return super.get();
    }

    // ----------------------------------------------------------------------------------------------------- entityClass
    Class<? super ENTITY> superclassDeclaring(final String attributeName) {
        return declaringClasses.computeIfAbsent(attributeName, k -> {
            for (Class<? super ENTITY> c = valueClass; c != null; c = c.getSuperclass()) {
                try {
                    c.getDeclaredField(k);
                    return c;
                } catch (final NoSuchFieldException nsfe) {
                    continue;
                }
            }
            throw new IllegalArgumentException("no declaring class for '" + attributeName + "'");
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<ENTITY> entityClass;

    private final Map<String, Class<? super ENTITY>> declaringClasses = new HashMap<>();
}