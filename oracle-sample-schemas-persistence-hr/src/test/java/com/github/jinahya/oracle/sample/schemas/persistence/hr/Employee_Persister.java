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

import com.github.jinahya.persistence.test.util.AbstractEntityPersister;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import jakarta.persistence.EntityManager;

/**
 * A persister which persists {@link Employee} instances.
 * <p>
 * Each instance is given a newly persisted {@link Job} and a newly persisted {@link Department} first, so that its
 * {@code job} -- which is not nullable -- and its {@code department} refer to rows which are already in the database.
 * The {@code manager} is left {@code null}, which the column allows.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Employee_Persister extends AbstractEntityPersister<Employee> {

    Employee_Persister() {
        super(Employee.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public Employee apply(final EntityManager entityManager, final Employee entityInstance) {
        entityInstance.setJob(EntityPersisterUtils.newPersistedInstanceOf(entityManager, Job.class));
        entityInstance.setDepartment(
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, Department.class));
        return super.apply(entityManager, entityInstance);
    }
}
