package com.github.jinahya.oracle.sample.schemas.persistence.hr;

/*-
 * #%L
 * hr
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJob;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_Randomizer;
import jakarta.annotation.Nonnull;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Job_Randomizer extends _MappedHrEntity_Randomizer<Job, String> {

    Job_Randomizer() {
        super(Job.class, String.class, List.of(
                MappedJob.ATTRIBUTE_NAME_EMPLOYEES
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
    public Job get() {
        final var value = super.get();
        {
            value.setMinSalary(
                    ThreadLocalRandom.current().nextBoolean()
                    ? null
                    : Job_TestUtils.newRandomPositiveMinSalary()
            );
            value.setMaxSalary(
                    ThreadLocalRandom.current().nextBoolean()
                    ? null
                    : value.getMinSalary() == null
                      ? Job_TestUtils.newRandomPositiveMinSalary()
                      : Job_TestUtils.newRandomPositiveMaxSalary(value.getMinSalary())
            );
        }
        return value;
    }
}
