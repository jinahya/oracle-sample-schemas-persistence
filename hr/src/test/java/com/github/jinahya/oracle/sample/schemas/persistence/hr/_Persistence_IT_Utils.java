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

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.Optional;
import java.util.function.Function;

/**
 * The persistence unit shared by the {@code HR} integration tests.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @apiNote This is the {@value #PERSISTENCE_UNIT_NAME} counterpart of {@link _Persistence_Test_Utils}, for tests which
 * reach the physical database rather than the in-memory one. Unlike {@link _Persistence_IT}, which takes its entity
 * manager from CDI, this opens its own factory, for tests which are not Weld-managed. Every method here is
 * {@link __Persistence___Utils} bound to that persistence unit.
 */
final class _Persistence_IT_Utils {

    /**
     * The name of the persistence unit the integration tests run against. The value is {@value}.
     */
    static final String PERSISTENCE_UNIT_NAME = _Persistence_IT_Producer.PERSISTENCE_UNIT_NAME;

    /**
     * Returns the entity manager factory, creating it on first use.
     *
     * @return the entity manager factory.
     */
    static EntityManagerFactory entityManagerFactory() {
        return __Persistence___Utils.entityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    /**
     * Applies a function to a new entity manager, inside a transaction which is always rolled back, so that nothing a
     * test writes reaches the physical database.
     *
     * @param function the function to apply.
     * @param <R>      result type parameter.
     * @return the result of the {@code function}.
     */
    static <R> R applyEntityManagerInRolledBackTransaction(
            final Function<? super EntityManager, ? extends R> function) {
        return __Persistence___Utils.applyEntityManagerInRolledBackTransaction(PERSISTENCE_UNIT_NAME, function);
    }

    /**
     * Selects a random instance of the specified entity class.
     *
     * @param entityClass the entity class to select an instance of.
     * @param <T>         entity type parameter.
     * @return a random instance of {@code entityClass}; {@link Optional#empty() empty} when the table is empty.
     * @apiNote The instance is detached by the time it is returned -- the transaction is rolled back and the entity
     * manager closed -- so reading a lazy association off it throws. Use
     * {@link __Persistence___Utils#selectRandom(EntityManager, Class)} inside
     * {@link #applyEntityManagerInRolledBackTransaction(Function)} when the instance has to stay managed.
     */
    static <T> Optional<T> selectRandomEntity(final Class<T> entityClass) {
        return __Persistence___Utils.selectRandom(PERSISTENCE_UNIT_NAME, entityClass);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private _Persistence_IT_Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
