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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistoryWithIdClass;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory_Randomizer;
import jakarta.annotation.Nonnull;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.List;

@Deprecated(forRemoval = true)
class JobHistoryWithIdClass_Randomizer extends MappedJobHistory_Randomizer<JobHistoryWithIdClass> {

    JobHistoryWithIdClass_Randomizer() {
        super(JobHistoryWithIdClass.class, List.of(
                MappedJobHistoryWithIdClass.ATTRIBUTE_NAME_EMPLOYEE_ID,
                MappedJobHistoryWithIdClass.ATTRIBUTE_NAME_EMPLOYEE,
                MappedJobHistoryWithIdClass.ATTRIBUTE_NAME_JOB_ID,
                MappedJobHistoryWithIdClass.ATTRIBUTE_NAME_JOB,
                MappedJobHistoryWithIdClass.ATTRIBUTE_NAME_DEPARTMENT_ID,
                MappedJobHistoryWithIdClass.ATTRIBUTE_NAME_DEPARTMENT
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
    public JobHistoryWithIdClass get() {
        return super.get();
    }
}
