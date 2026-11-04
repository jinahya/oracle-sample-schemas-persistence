package com.github.jinahya.oracle.sample.persistence.sh;

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

import java.util.Objects;
import java.util.function.BiFunction;

import static com.github.jinahya.persistence.test.util.JinahyaEntityManagerTestUtils.selectRandomEntity;
import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManagerInTransactionAndRollback;

/**
 * Helpers, over the jinahya library, for tests which need an entity manager.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S1133" // Deprecated code should be removed
})
final class ___Persistence_TestUtils {

    static <T, R> R selectRandomEntityInstance(
            final EntityManagerFactory entityManagerFactory,
            final Class<T> entityClass,
            final BiFunction<? super EntityManager, ? super T, ? extends R> function) {
        Objects.requireNonNull(entityManagerFactory, "entityManagerFactory is null");
        Objects.requireNonNull(entityClass, "entityClass is null");
        Objects.requireNonNull(function, "function is null");
        return applyEntityManagerInTransactionAndRollback(
                entityManagerFactory,
                em -> selectRandomEntity(em, entityClass)
                        .map(v -> function.apply(em, v))
                        .orElse(null)
        );
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS
    private ___Persistence_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}

