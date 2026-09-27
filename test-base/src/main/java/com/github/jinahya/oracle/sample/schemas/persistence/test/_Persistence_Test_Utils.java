package com.github.jinahya.oracle.sample.schemas.persistence.test;

/*-
 * #%L
 * test-base
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

import java.util.function.Function;

/**
 * The persistence unit shared by the tests.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @apiNote Every method here is {@link __Persistence___Utils} bound to {@value #PERSISTENCE_UNIT_NAME}; the shared
 * class holds the logic, this one holds the persistence unit.
 */
public final class _Persistence_Test_Utils {

    /**
     * The name of the persistence unit the tests run against. The value is {@value}.
     */
    public static final String PERSISTENCE_UNIT_NAME = _Persistence_Test_Producer.PERSISTENCE_UNIT_NAME;

    /**
     * Returns the entity manager factory, creating it on first use.
     *
     * @return the entity manager factory.
     */
    public static EntityManagerFactory entityManagerFactory() {
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
    public static <R> R applyEntityManagerInRolledBackTransaction(
            final Function<? super EntityManager, ? extends R> function) {
        return __Persistence___Utils.applyEntityManagerInRolledBackTransaction(PERSISTENCE_UNIT_NAME, function);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private _Persistence_Test_Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
