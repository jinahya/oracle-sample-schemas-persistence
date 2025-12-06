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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_Randomizer;
import jakarta.annotation.Nonnull;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.List;

class Employee_Randomizer extends _MappedHrEntity_Randomizer<Employee, Integer> {

    Employee_Randomizer() {
        super(Employee.class, Integer.class, List.of(
                MappedEmployee.ATTRIBUTE_NAME_JOB_ID,
                MappedEmployee.ATTRIBUTE_NAME_JOB,
                MappedEmployee.ATTRIBUTE_NAME_MANAGER_ID,
                MappedEmployee.ATTRIBUTE_NAME_MANAGER,
                MappedEmployee.ATTRIBUTE_NAME_DEPARTMENT_ID,
                MappedEmployee.ATTRIBUTE_NAME_DEPARTMENT,
                MappedEmployee.ATTRIBUTE_NAME_SUBORDINATES
        ));
    }

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
    public Employee get() {
        return super.get();
    }
}
