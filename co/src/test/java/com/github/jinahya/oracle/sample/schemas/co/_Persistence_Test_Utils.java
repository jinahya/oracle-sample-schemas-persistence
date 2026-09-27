package com.github.jinahya.oracle.sample.schemas.co;

/*-
 * #%L
 * co
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

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * The persistence unit shared by the {@code CO} tests.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @apiNote Every method here is {@link __Persistence___Utils} bound to {@value #PERSISTENCE_UNIT_NAME}; the shared
 * class holds the logic, this one holds the persistence unit.
 */
final class _Persistence_Test_Utils {

    /**
     * The name of the persistence unit the tests run against. The value is {@value}.
     */
    static final String PERSISTENCE_UNIT_NAME = "__testPU";

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
     * test writes is visible to the next one.
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
     * Selects a random instance of the specified entity class using the specified entity manager.
     *
     * @param entityManager the entity manager to select with.
     * @param entityClass   the entity class to select an instance of.
     * @param <T>           entity type parameter.
     * @return a random instance of {@code entityClass}; {@link Optional#empty() empty} when the table is empty.
     */
    static <T> Optional<T> selectRandomEntity(final EntityManager entityManager,
                                              final Class<T> entityClass) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        Objects.requireNonNull(entityClass, "entityClass is null");
        return __Persistence___Utils.selectRandom(entityManager, entityClass);
    }

    /**
     * Selects a random instance of the specified entity class.
     *
     * @param entityClass the entity class to select an instance of.
     * @param <T>         entity type parameter.
     * @return a random instance of {@code entityClass}; {@link Optional#empty() empty} when the table is empty.
     * @apiNote The instance is detached by the time it is returned -- the transaction is rolled back and the entity
     * manager closed -- so reading a lazy association off it throws. Use
     * {@link #selectRandomEntity(EntityManager, Class)} when the instance has to stay managed.
     */
    static <T> Optional<T> selectRandomEntity(final Class<T> entityClass) {
        return __Persistence___Utils.selectRandom(PERSISTENCE_UNIT_NAME, entityClass);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private _Persistence_Test_Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
