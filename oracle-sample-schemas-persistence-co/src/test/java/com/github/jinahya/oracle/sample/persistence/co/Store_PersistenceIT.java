package com.github.jinahya.oracle.sample.persistence.co;

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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static com.github.jinahya.oracle.sample.persistence.co.__PersistenceIT_Utils.applyEntityManagerFactory;
import static com.github.jinahya.oracle.sample.persistence.co.__PersistenceIT_Utils.applyEntityManagerInTransactionAndRollback_;
import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManager;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * Verifies the mappings of {@link Store} against the installed {@code CO} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Store_PersistenceIT
        extends _Entity_PersistenceIT<Store, Long> {

    // ---------------------------------------------------------------------------------------------
    Store_PersistenceIT() {
        super(Store.class, Long.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class SelectSingleByStoreName_Test {

        /**
         * Returns some store names of the installed table.
         *
         * @implNote Selects the store names, not the entities, so nothing is left managed.
         */
        Stream<String> storeNames() {
            return applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                return entityManager
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
            }));
        }

        @DisplayName("a query-language query selects the installed store")
        @MethodSource("storeNames")
        @ParameterizedTest
        void __QueryLanguage(final String storeName) {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var found = entityManager
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
                return null;
            }));
        }

        @DisplayName("a criteria query selects the installed store")
        @MethodSource("storeNames")
        @ParameterizedTest
        void __CriteriaApi(final String storeName) {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
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
                return null;
            }));
        }
    }

    // ---------------------------------------------------------------------------------------------
    @DisplayName("LOGO")
    @Nested
    class LogoTest {

        @DisplayName("a logo written reads back equal")
        @Test
        void __RoundTrip() {
            final Long id;
            {
                final var optional = selectRandomEntityId_();
                assumeThat(optional).isNotEmpty();
                id = optional.get();
            }
            final var bytes = new byte[1024];
            ThreadLocalRandom.current().nextBytes(bytes);
            applyEntityManagerInTransactionAndRollback_(emf -> em -> {
                final var store = em.find(targetClass, id);
                store.setLogo(bytes);
                store.setLogoMimeType("application/octet-stream");
                em.flush();
                em.clear();
                final var read = em.find(targetClass, id);
                assertThat(read.getLogo()).isEqualTo(bytes);
                assertThat(read.getLogoMimeType()).isEqualTo("application/octet-stream");
                return null;
            });
        }
    }
}
