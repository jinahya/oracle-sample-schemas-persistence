package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistoryWithEmbeddedId;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory_Randomizer;
import jakarta.annotation.Nonnull;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.List;

@Deprecated(forRemoval = true)
class JobHistoryWithEmbeddedId_Randomizer extends MappedJobHistory_Randomizer<JobHistoryWithEmbeddedId> {

    JobHistoryWithEmbeddedId_Randomizer() {
        super(JobHistoryWithEmbeddedId.class, List.of(
                MappedJobHistoryWithEmbeddedId.ATTRIBUTE_NAME_ID_EMPLOYEE_ID,
                MappedJobHistoryWithEmbeddedId.ATTRIBUTE_NAME_EMPLOYEE,
                MappedJobHistoryWithEmbeddedId.ATTRIBUTE_NAME_JOB_ID,
                MappedJobHistoryWithEmbeddedId.ATTRIBUTE_NAME_JOB,
                MappedJobHistoryWithEmbeddedId.ATTRIBUTE_NAME_DEPARTMENT_ID,
                MappedJobHistoryWithEmbeddedId.ATTRIBUTE_NAME_DEPARTMENT
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
    public JobHistoryWithEmbeddedId get() {
        return super.get();
    }
}
