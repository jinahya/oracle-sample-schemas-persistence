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

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceTest;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersisterUtils;
import jakarta.annotation.Nonnull;

import java.util.Objects;

/**
 * An abstract class for unit-testing persistence of a specific subclass of the {@link _MappedHrEntity} class.
 *
 * @param <ENTITY> entity type parameter
 * @param <ID>     id type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class _MappedHrEntity_PersistenceTest<ENTITY extends _MappedHrEntity<ID>, ID>
        extends __MappedEntity_PersistenceTest<ENTITY, ID> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for testing specified entity class.
     *
     * @param entityClass the entity class to test.
     * @param idClass     the id class of the {@code entityClass}.
     */
    protected _MappedHrEntity_PersistenceTest(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // ------------------------------------------------------------------------------- super.(entityManager|entityClass)

    /**
     * Returns a new persisted instance of the specified entity class.
     *
     * @param entityClass the entity class whose instance is to be returned.
     * @param <T>         entity type parameter
     * @return a new persisted instance of the specified entity class.
     * @see #newPersistedEntityInstance()
     */
    protected final <T extends _MappedHrEntity<?>> T newPersistedEntityInstanceOf(@Nonnull final Class<T> entityClass) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        return applyEntityManager(em -> {
            return __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, entityClass);
        });
    }

    /**
     * Returns a new persisted instance of the {@link #entityClass}.
     *
     * @return a new persisted instance of the {@link #entityClass}.
     * @see #newPersistedEntityInstanceOf(Class)
     */
    protected final ENTITY newPersistedEntityInstance() {
        return newPersistedEntityInstanceOf(entityClass);
    }
}
