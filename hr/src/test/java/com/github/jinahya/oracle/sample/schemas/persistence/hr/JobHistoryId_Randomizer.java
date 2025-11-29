package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHr_Randomizer;
import jakarta.annotation.Nonnull;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.List;

class JobHistoryId_Randomizer extends _MappedHr_Randomizer<JobHistoryId> {

    JobHistoryId_Randomizer() {
        super(JobHistoryId.class, List.of(
                MappedJobHistory.ATTRIBUTE_NAME_EMPLOYEE
        ));
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
    public JobHistoryId get() {
        final var value = super.get();
        return value;
    }
}
