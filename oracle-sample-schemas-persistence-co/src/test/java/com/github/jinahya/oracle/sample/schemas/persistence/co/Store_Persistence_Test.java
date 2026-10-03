package com.github.jinahya.oracle.sample.schemas.persistence.co;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


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

/**
 * Verifies the mappings of {@link Store} against the schema generated into the in-memory database.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Store_Persistence_Test extends _Persistence_Test<Store> {

    // -----------------------------------------------------------------------------------------------------------------
    Store_Persistence_Test() {
        super(Store.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class SelectSingleByStoreName_Test {

        @DisplayName("a query-language query selects the store just persisted")
        @Test
        void __QueryLanguage() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Store AS e
                                        WHERE e.storeName = :storeName""",
                                targetClass
                        )
                        .setParameter(Store_.storeName.getName(), v.getStoreName())
                        .getSingleResult();
                assertThat(found)
                        .as("the store selected by %s", v.getStoreName())
                        .isSameAs(v);
                return found;
            });
        }

        @DisplayName("the named query selects the store just persisted")
        @Test
        void __NamedQuery() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createNamedQuery("Store.selectSingleByStoreName", targetClass)
                        .setParameter(Store_.storeName.getName(), v.getStoreName())
                        .getSingleResult();
                assertThat(found)
                        .as("the store selected by %s", v.getStoreName())
                        .isSameAs(v);
                return found;
            });
        }

        @DisplayName("a criteria query selects the store just persisted")
        @Test
        void __CriteriaApi() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.where(builder.equal(root.get(Store_.storeName), v.getStoreName()));
                final var found = em.createQuery(query).getSingleResult();
                assertThat(found)
                        .as("the store selected by %s", v.getStoreName())
                        .isSameAs(v);
                return found;
            });
        }
    }
}
