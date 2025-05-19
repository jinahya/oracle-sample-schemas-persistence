package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.time.Period;

class JobHistory_Randomizer extends __MappedEntity_Randomizer<JobHistory> {

    JobHistory_Randomizer() {
        super(JobHistory.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected DataProviderStrategy dataProviderStrategy() {
        return super.dataProviderStrategy();
    }

    @Override
    protected ClassInfoStrategy classInfoStrategy() {
        return super.classInfoStrategy();
    }

    @Override
    protected PodamFactory podamFactory() {
        return super.podamFactory();
    }

    @Override
    protected JobHistory manufacturePojo() {
        final var jobHistory = super.manufacturePojo();
        {
            assert jobHistory.getId() != null;
            assert jobHistory.getId().getStartDate() != null;
            assert jobHistory.getEndDate() != null;
            if (!jobHistory.getId().getStartDate().isBefore(jobHistory.getEndDate())) {
                jobHistory.getId().setStartDate(jobHistory.getEndDate().minus(Period.ofDays(1)));
            }
            assert jobHistory.getId().getStartDate().isBefore(jobHistory.getEndDate());
        }
        return jobHistory;
    }
}