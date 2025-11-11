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

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Persister;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class _MappedHrEntity_Persister<
        ENTITY extends _MappedHrEntity<ID>,
        ID
        >
        extends __MappedEntity_Persister<ENTITY, ID> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected _MappedHrEntity_Persister(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull EntityManager entityManager, @Nonnull ENTITY entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
