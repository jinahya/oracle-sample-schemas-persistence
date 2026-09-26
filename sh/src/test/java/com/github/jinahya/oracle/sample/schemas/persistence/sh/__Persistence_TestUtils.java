package com.github.jinahya.oracle.sample.schemas.persistence.sh;

/*-
 * #%L
 * sh
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
 * The persistence unit shared by the {@code SH} tests.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @apiNote The work is done by {@link __Persistence__Utils}; this only pins the persistence unit.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
final class __Persistence_TestUtils {

    /**
     * The name of the persistence unit the tests run against. The value is {@value}.
     */
    static final String PERSISTENCE_UNIT_NAME = _TestPersistenceProducer.PERSISTENCE_UNIT_NAME;

    /**
     * Returns the entity manager factory, creating it on first use.
     *
     * @return the entity manager factory.
     * @see __Persistence__Utils#entityManagerFactory(String)
     */
    static EntityManagerFactory entityManagerFactory() {
        return __Persistence__Utils.entityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    /**
     * Applies a function to a new entity manager, inside a transaction which is always rolled back, so that nothing a
     * test writes is visible to the next one.
     *
     * @param function the function to apply.
     * @param <R>      result type parameter.
     * @return the result of the {@code function}.
     * @see __Persistence__Utils#applyEntityManagerInRolledBackTransaction(String, Function)
     */
    static <R> R applyEntityManagerInRolledBackTransaction(
            final Function<? super EntityManager, ? extends R> function) {
        return __Persistence__Utils.applyEntityManagerInRolledBackTransaction(PERSISTENCE_UNIT_NAME, function);
    }

    /**
     * Selects a random instance of the specified entity class.
     *
     * @param entityClass the entity class to select an instance of.
     * @param <T>         entity type parameter.
     * @return a random instance of {@code entityClass}; {@link Optional#empty() empty} when the table is empty.
     * @see __Persistence__Utils#selectRandom(String, Class)
     */
    static <T> Optional<T> selectRandomEntity(final Class<T> entityClass) {
        return __Persistence__Utils.selectRandom(PERSISTENCE_UNIT_NAME, entityClass);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private __Persistence_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
