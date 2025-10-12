package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Randomizer;
import jakarta.annotation.Nonnull;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

public abstract class _MappedShEntity_Randomizer<ENTITY extends _MappedShEntity<ID>, ID>
        extends __MappedEntity_Randomizer<ENTITY, ID> {

    protected _MappedShEntity_Randomizer(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected DataProviderStrategy getDataProviderStrategy() {
        return super.getDataProviderStrategy();
    }

    @Nonnull
    @Override
    protected PodamFactory getPodamFactory() {
        return super.getPodamFactory();
    }

    @Nonnull
    @Override
    protected ClassInfoStrategy getClassInfoStrategy() {
        return super.getClassInfoStrategy();
    }

    @Nonnull
    @Override
    public ENTITY get() {
        return super.get();
    }
}
