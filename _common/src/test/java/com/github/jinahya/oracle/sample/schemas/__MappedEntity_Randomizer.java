package com.github.jinahya.oracle.sample.schemas;

import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.HashMap;
import java.util.Map;

//abstract class __MappedEntity_Randomizer<T extends __MappedEntity<T, ?>> {
public abstract class __MappedEntity_Randomizer<ENTITY extends __MappedEntity<ENTITY, ?>>
        extends ___Base_Randomizer<ENTITY> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for generating randomized instance of specified entity class.
     *
     * @param entityClass the entity class.
     */
    protected __MappedEntity_Randomizer(final Class<ENTITY> entityClass) {
        super(entityClass);
        this.entityClass = valueClass;
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