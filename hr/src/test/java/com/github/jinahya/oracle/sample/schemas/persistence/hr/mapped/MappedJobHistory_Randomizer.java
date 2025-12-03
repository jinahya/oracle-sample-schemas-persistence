package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId;
import com.github.jinahya.persistence.mapped.test.___RandomizerUtils;
import jakarta.annotation.Nonnull;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

public class MappedJobHistory_Randomizer<ENTITY extends MappedJobHistory>
        extends _MappedHrEntity_Randomizer<ENTITY, JobHistoryId> {

    protected MappedJobHistory_Randomizer(final Class<ENTITY> entityClass, final String... excludedFields) {
        super(entityClass, JobHistoryId.class,
              ___RandomizerUtils.mergeExcludedFields(excludedFields,
                                                     MappedJobHistory.ATTRIBUTE_NAME_EMPLOYEE,
                                                     MappedJobHistory.ATTRIBUTE_NAME_JOB,
                                                     MappedJobHistory.ATTRIBUTE_NAME_DEPARTMENT
              )
        );
    }

    protected MappedJobHistory_Randomizer(final Class<ENTITY> entityClass, final Iterable<String> excludedFields) {
        super(entityClass, JobHistoryId.class, moreExcludedFields(
                excludedFields,
                MappedJobHistory.ATTRIBUTE_NAME_EMPLOYEE,
                MappedJobHistory.ATTRIBUTE_NAME_JOB,
                MappedJobHistory.ATTRIBUTE_NAME_DEPARTMENT
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
    public ENTITY get() {
        final var value = super.get();
        return value;
    }
}
