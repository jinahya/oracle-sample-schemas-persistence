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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_Persister;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
class Employee_Persister extends _MappedHrEntity_Persister<Employee, Integer> {

    Employee_Persister() {
        super(Employee.class, Integer.class);
    }

    @Override
    public void persist(final @Nonnull EntityManager entityManager, final @Nonnull Employee entityInstance) {
        entityInstance.setJob(
                newPersistedInstanceOf(entityManager, Job.class)
        );
        entityInstance.setManager(
                ThreadLocalRandom.current().nextBoolean()
                ? null
                : newPersistedInstanceOf(entityManager, Employee.class)
        );
        entityInstance.setDepartment(
                ThreadLocalRandom.current().nextBoolean()
                ? null
                : newPersistedInstanceOf(entityManager, Department.class)
        );
        if (true) {
            entityManager.flush(); // fuck eclipselink, or fuck myself.
        }
        super.persist(entityManager, entityInstance);
    }
}
