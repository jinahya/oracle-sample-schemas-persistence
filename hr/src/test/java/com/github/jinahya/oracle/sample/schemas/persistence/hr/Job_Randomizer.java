package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJob;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_Randomizer;
import jakarta.annotation.Nonnull;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.concurrent.ThreadLocalRandom;

class Job_Randomizer extends __MappedEntity_Randomizer<Job, String> {

    Job_Randomizer() {
        super(Job.class, String.class);
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
    public Job get() {
        final var value = super.get();
        {
            if (ThreadLocalRandom.current().nextBoolean()) {
                value.setMinSalary(null);
            }
            if (ThreadLocalRandom.current().nextBoolean()) {
                value.setMaxSalary(null);
            }
        }
        {
            if (value.getMaxSalary() != null && value.getMaxSalary() == 1) {
                value.setMaxSalary(
                        ThreadLocalRandom.current().nextInt(
                                99999,
                                MappedJob.MAX_MAX_SALARY
                        ) + 1
                );
            }
            if (value.getMinSalary() != null &&
                value.getMaxSalary() != null &&
                value.getMinSalary() > value.getMaxSalary()) {
                value.setMinSalary(
                        ThreadLocalRandom.current().nextInt(
                                0,
                                value.getMaxSalary() + 1
                        )
                );
            }
            assert value.getMinSalary() == null
                   || value.getMaxSalary() == null
                   || value.getMinSalary() <= value.getMaxSalary();
        }
        return value;
    }
}
