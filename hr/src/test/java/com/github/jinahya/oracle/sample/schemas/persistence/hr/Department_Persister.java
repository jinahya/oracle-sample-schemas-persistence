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

import com.github.jinahya.persistence.test.util.__Persister;
import com.github.jinahya.persistence.test.util.__PersisterUtils;
import jakarta.persistence.EntityManager;

class Department_Persister extends __Persister<Department> {

    Department_Persister() {
        super(Department.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public Department apply(final EntityManager entityManager, final Department entityInstance) {
        entityInstance.setLocation(__PersisterUtils.newPersistedInstanceOf(entityManager, Location.class));
        return super.apply(entityManager, entityInstance);
    }
}
