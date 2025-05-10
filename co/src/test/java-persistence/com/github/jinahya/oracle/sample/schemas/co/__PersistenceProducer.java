package com.github.jinahya.oracle.sample.schemas.co;

/*-
 * #%L
 * mysql-employees-persistece
 * %%
 * Copyright (C) 2024 Jinahya, Inc.
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

import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class __PersistenceProducer {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the persistence unit. The value is {@value}.
     */
    private static final String PERSISTENCE_UNIT_NAME = "employeesPU";

    // -----------------------------------------------------------------------------------------------------------------
    @PersistenceContext
    @Produces
    EntityManagerFactory produceEntityManagerFactory() {
        final var entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        log.debug("producing entityManageFactory: {}", entityManagerFactory);
        return entityManagerFactory;
    }

    void disposeEntityManagerFactory(@Disposes final EntityManagerFactory entityManagerFactory) {
        log.debug("disposing entityManagerFactory: {}", entityManagerFactory);
        entityManagerFactory.close();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @PersistenceUnit
    @Produces
    EntityManager produceEntityManager(final EntityManagerFactory entityManagerFactory) {
        final var entityManager = entityManagerFactory.createEntityManager();
        log.debug("producing entityManager: {}", entityManager);
        return entityManager;
    }

    void disposeEntityManager(@Disposes final EntityManager entityManager) {
        log.debug("disposing entityManager: {}", entityManager);
        entityManager.close();
    }
}
