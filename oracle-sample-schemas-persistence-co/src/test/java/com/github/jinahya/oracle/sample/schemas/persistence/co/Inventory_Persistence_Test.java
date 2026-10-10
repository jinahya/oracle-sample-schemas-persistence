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
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the mappings of {@link Inventory} against the schema generated into the in-memory database.
 * <p>
 * Each persisted {@link Inventory} is given a store and a product of its own, so a query by either selects it alone.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Inventory_Persistence_Test extends _DomainEntity_Persistence_Test<Inventory, Long> {

    // -----------------------------------------------------------------------------------------------------------------
    Inventory_Persistence_Test() {
        super(Inventory.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@code Inventory.selectOneByStoreAndProduct}, which answers exercise {@code CO-INVENTORY-B-01}.
     */
    @Nested
    class SelectOneByStoreAndProduct_Test {

        @DisplayName("the named query selects the inventory just persisted")
        @Test
        void __NamedQuery() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createNamedQuery("Inventory.selectOneByStoreAndProduct", targetClass)
                        .setParameter("store", v.getStore())
                        .setParameter("product", v.getProduct())
                        .getSingleResult();
                assertThat(found)
                        .as("the inventory selected by its store and its product")
                        .isSameAs(v);
                return found;
            });
        }

        @DisplayName("a query-language query selects the inventory just persisted")
        @Test
        void __QueryLanguage() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Inventory AS e
                                        WHERE e.store = :store AND e.product = :product""",
                                targetClass
                        )
                        .setParameter("store", v.getStore())
                        .setParameter("product", v.getProduct())
                        .getSingleResult();
                assertThat(found)
                        .as("the inventory selected by its store and its product")
                        .isSameAs(v);
                return found;
            });
        }

        @DisplayName("a criteria query selects the inventory just persisted")
        @Test
        void __CriteriaApi() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.where(
                        builder.equal(root.get(Inventory_.store), v.getStore()),
                        builder.equal(root.get(Inventory_.product), v.getProduct())
                );
                final var found = em.createQuery(query).getSingleResult();
                assertThat(found)
                        .as("the inventory selected by its store and its product")
                        .isSameAs(v);
                return found;
            });
        }
    }

    /**
     * Tests {@code Inventory.selectOneByProductOrderByProductInventoryAsc}, which answers exercise
     * {@code CO-INVENTORY-B-02}.
     */
    @Nested
    class SelectOneByProductOrderByProductInventoryAsc_Test {

        @DisplayName("the named query selects the inventory just persisted, by its product")
        @Test
        void __NamedQuery() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createNamedQuery("Inventory.selectOneByProductOrderByProductInventoryAsc", targetClass)
                        .setParameter("product", v.getProduct())
                        .getResultList();
                assertThat(found)
                        .as("the inventories of the product")
                        .containsExactly(v);
                return found;
            });
        }

        @DisplayName("a query-language query selects the inventory just persisted, by its product")
        @Test
        void __QueryLanguage() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Inventory AS e
                                        WHERE e.product = :product
                                        ORDER BY e.productInventory ASC""",
                                targetClass
                        )
                        .setParameter("product", v.getProduct())
                        .getResultList();
                assertThat(found)
                        .as("the inventories of the product")
                        .containsExactly(v);
                return found;
            });
        }

        @DisplayName("a criteria query selects the inventory just persisted, by its product")
        @Test
        void __CriteriaApi() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.where(builder.equal(root.get(Inventory_.product), v.getProduct()));
                query.orderBy(builder.asc(root.get(Inventory_.productInventory)));
                final var found = em.createQuery(query).getResultList();
                assertThat(found)
                        .as("the inventories of the product")
                        .containsExactly(v);
                return found;
            });
        }
    }

    /**
     * Tests {@code Inventory.selectListByStoreOrderByProductInventoryAsc}, which answers exercise
     * {@code CO-INVENTORY-B-03}.
     */
    @Nested
    class SelectListByStoreOrderByProductInventoryAsc_Test {

        @DisplayName("the named query selects the inventory just persisted, by its store")
        @Test
        void __NamedQuery() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createNamedQuery("Inventory.selectListByStoreOrderByProductInventoryAsc", targetClass)
                        .setParameter("store", v.getStore())
                        .getResultList();
                assertThat(found)
                        .as("the inventories of the store")
                        .containsExactly(v);
                return found;
            });
        }

        @DisplayName("a query-language query selects the inventory just persisted, by its store")
        @Test
        void __QueryLanguage() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Inventory AS e
                                        WHERE e.store = :store
                                        ORDER BY e.productInventory ASC""",
                                targetClass
                        )
                        .setParameter("store", v.getStore())
                        .getResultList();
                assertThat(found)
                        .as("the inventories of the store")
                        .containsExactly(v);
                return found;
            });
        }

        @DisplayName("a criteria query selects the inventory just persisted, by its store")
        @Test
        void __CriteriaApi() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.where(builder.equal(root.get(Inventory_.store), v.getStore()));
                query.orderBy(builder.asc(root.get(Inventory_.productInventory)));
                final var found = em.createQuery(query).getResultList();
                assertThat(found)
                        .as("the inventories of the store")
                        .containsExactly(v);
                return found;
            });
        }
    }
}
