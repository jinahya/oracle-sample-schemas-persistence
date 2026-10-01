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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.List;

/**
 * A randomizer which produces randomized {@link Employee} instances.
 * <p>
 * The {@code job}, {@code manager}, {@code department}, {@code subordinates}, {@code managedDepartments} and
 * {@code jobHistories} associations are excluded from randomization; {@link Employee_Persister} supplies the
 * {@code job} and the {@code department}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Employee_Randomizer extends PodamObjectRandomizer<Employee> {

    Employee_Randomizer() {
        super(Employee.class, List.of(
                Employee.ATTRIBUTE_NAME_JOB,
                Employee.ATTRIBUTE_NAME_MANAGER,
                Employee.ATTRIBUTE_NAME_DEPARTMENT,
                Employee.ATTRIBUTE_NAME_SUBORDINATES,
                Employee.ATTRIBUTE_NAME_MANAGED_DEPARTMENTS,
                Employee.ATTRIBUTE_NAME_JOB_HISTORIES
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

    @Override
    public Employee get() {
        return super.get();
    }
}
