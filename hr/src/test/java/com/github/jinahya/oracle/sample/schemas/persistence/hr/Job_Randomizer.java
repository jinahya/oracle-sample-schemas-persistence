package com.github.jinahya.oracle.sample.schemas.persistence.hr;

/*-
 * #%L
 * hr
 * %%
 * Copyright (C) 2024 - 2026 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.persistence.test.util.__Randomizer;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Job_Randomizer extends __Randomizer.___OfPodam<Job> {

    Job_Randomizer() {
        super(Job.class, List.of(
                Job.ATTRIBUTE_NAME_EMPLOYEES,
                Job.ATTRIBUTE_NAME_JOB_HISTORIES
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy getDataProviderStrategy() {
        return super.getDataProviderStrategy();
    }

    @Override
    protected ClassInfoStrategy getClassInfoStrategy() {
        return super.getClassInfoStrategy();
    }

    @Override
    protected PodamFactory getPodamFactory() {
        return super.getPodamFactory();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The two salaries are assigned here rather than left to PODAM, which draws them independently:
     * {@link Job} asserts that both are positive and that the minimum does not exceed the maximum, so an independent
     * pair fails validation roughly half of the time.
     */
    @Override
    public Job get() {
        final var instance = super.get();
        final var minSalary = ThreadLocalRandom.current().nextInt(1, Job.ATTRIBUTE_MAX_MIN_SALARY);
        instance.setMinSalary(minSalary);
        instance.setMaxSalary(ThreadLocalRandom.current().nextInt(minSalary, Job.ATTRIBUTE_MAX_MAX_SALARY));
        return instance;
    }
}
