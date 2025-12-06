package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId;
import com.github.jinahya.persistence.mapped.test.__Disable_PersistEntityInstance_Test;

@__Disable_PersistEntityInstance_Test
public abstract class MappedJobHistory_PersistenceTest<ENTITY extends MappedJobHistory>
        extends _MappedHrEntity_PersistenceTest<ENTITY, JobHistoryId> {

    protected MappedJobHistory_PersistenceTest(final Class<ENTITY> entityClass) {
        super(entityClass, JobHistoryId.class);
    }
}
