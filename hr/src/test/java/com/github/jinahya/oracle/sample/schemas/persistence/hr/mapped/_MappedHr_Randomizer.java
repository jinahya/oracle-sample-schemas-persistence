package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.persistence.mapped.test.__Mapped_Randomizer;
import jakarta.annotation.Nonnull;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

public class _MappedHr_Randomizer<MAPPED extends _MappedHr> extends __Mapped_Randomizer<MAPPED> {

    @Deprecated(forRemoval = true)
    protected _MappedHr_Randomizer(final Class<MAPPED> targetClass, final String... excludedFields) {
        super(targetClass, excludedFields);
    }

    protected _MappedHr_Randomizer(final Class<MAPPED> targetClass, final Iterable<String> excludedFields) {
        super(targetClass, excludedFields);
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
    public MAPPED get() {
        return super.get();
    }
}
