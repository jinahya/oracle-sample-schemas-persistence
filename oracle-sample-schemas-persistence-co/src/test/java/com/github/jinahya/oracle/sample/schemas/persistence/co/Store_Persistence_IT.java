package com.github.jinahya.oracle.sample.schemas.persistence.co;

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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the mappings of {@link Store} against the installed {@code CO} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Store_Persistence_IT extends _DomainEntity_Persistence_IT<Store, Long> {

    // -----------------------------------------------------------------------------------------------------------------
    Store_Persistence_IT() {
        super(Store.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class SelectSingleByStoreName_Test {

        /**
         * Returns some store names of the installed table.
         *
         * @implNote Selects the store names, not the entities, so nothing is left managed.
         */
        Stream<String> storeNames() {
            return entityManager()
                    .createQuery(
                            """
                                    SELECT e.storeName
                                    FROM Store e
                                    ORDER BY e.storeId ASC""",
                            String.class
                    )
                    .setMaxResults(5)
                    .getResultList()
                    .stream();
        }

        @DisplayName("the named query selects the installed store")
        @MethodSource("storeNames")
        @ParameterizedTest
        void __NamedQuery(final String storeName) {
            final var found = entityManager()
                    .createNamedQuery("Store.selectSingleByStoreName", targetClass)
                    .setParameter("storeName", storeName)
                    .getSingleResult();
            assertThat(found)
                    .as("the store selected by %s", storeName)
                    .extracting(Store::getStoreName)
                    .isEqualTo(storeName);
        }

        @DisplayName("a query-language query selects the installed store")
        @MethodSource("storeNames")
        @ParameterizedTest
        void __QueryLanguage(final String storeName) {
            final var found = entityManager()
                    .createQuery(
                            """
                                    SELECT e
                                    FROM Store AS e
                                    WHERE e.storeName = :storeName""",
                            targetClass
                    )
                    .setParameter("storeName", storeName)
                    .getSingleResult();
            assertThat(found)
                    .as("the store selected by %s", storeName)
                    .extracting(Store::getStoreName)
                    .isEqualTo(storeName);
        }

        @DisplayName("a criteria query selects the installed store")
        @MethodSource("storeNames")
        @ParameterizedTest
        void __CriteriaApi(final String storeName) {
            final var entityManager = entityManager();
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(targetClass);
            final var root = query.from(targetClass);
            query.select(root);
            query.where(builder.equal(root.get(Store_.storeName), storeName));
            final var found = entityManager.createQuery(query).getSingleResult();
            assertThat(found)
                    .as("the store selected by %s", storeName)
                    .extracting(Store::getStoreName)
                    .isEqualTo(storeName);
        }
    }
}
