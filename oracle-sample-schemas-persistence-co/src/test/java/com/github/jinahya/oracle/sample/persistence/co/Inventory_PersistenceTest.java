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

import static com.github.jinahya.oracle.sample.persistence.co.__PersistenceTest_Utils.applyEntityManagerFactory;
import static com.github.jinahya.persistence.test.util.EntityPersisterUtils.newPersistedInstanceOf;
import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManager;
import static com.github.jinahya.persistence.util.JinahyaEntityManagerUtils.applyInTransactionAndRollback;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the mappings of {@link Inventory} against the schema generated into the in-memory
 * database.
 * <p>
 * Each persisted {@link Inventory} is given a store and a product of its own, so a query by either
 * selects it alone.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Inventory_PersistenceTest
        extends _Entity_PersistenceTest<Inventory> {

    // ---------------------------------------------------------------------------------------------
    Inventory_PersistenceTest() {
        super(Inventory.class);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Tests {@code Inventory.selectOneByStoreAndProduct}, which answers exercise
     * {@code CO_INVENTORY_B_01}.
     */
    @Nested
    class SelectOneByStoreAndProduct_Test {

        @DisplayName("the named query selects the inventory just persisted")
        @Test
        void __NamedQuery() {
            applyEntityManagerFactory(
                    emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
                        final var v = newPersistedInstanceOf(em, targetClass);
                        final var found = em
                                .createNamedQuery("Inventory.selectOneByStoreAndProduct",
                                                  targetClass)
                                .setParameter("store", v.getStore())
                                .setParameter("product", v.getProduct())
                                .getSingleResult();
                        assertThat(found)
                                .as("the inventory selected by its store and its product")
                                .isSameAs(v);
                        return found;
                    })));
        }

        @DisplayName("a query-language query selects the inventory just persisted")
        @Test
        void __QueryLanguage() {
            applyEntityManagerFactory(
                    emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
                        final var v = newPersistedInstanceOf(em, targetClass);
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
                    })));
        }

        @DisplayName("a criteria query selects the inventory just persisted")
        @Test
        void __CriteriaApi() {
            applyEntityManagerFactory(
                    emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
                        final var v = newPersistedInstanceOf(em, targetClass);
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
                    })));
        }
    }

    /**
     * Tests {@code Inventory.selectOneByProductOrderByProductInventoryAsc}, which answers exercise
     * {@code CO_INVENTORY_B_02}.
     */
    @Nested
    class SelectOneByProductOrderByProductInventoryAsc_Test {

        @DisplayName("the named query selects the inventory just persisted, by its product")
        @Test
        void __NamedQuery() {
            applyEntityManagerFactory(
                    emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
                        final var v = newPersistedInstanceOf(em, targetClass);
                        final var found = em
                                .createNamedQuery(
                                        "Inventory.selectOneByProductOrderByProductInventoryAsc",
                                        targetClass)
                                .setParameter("product", v.getProduct())
                                .getResultList();
                        assertThat(found)
                                .as("the inventories of the product")
                                .containsExactly(v);
                        return found;
                    })));
        }

        @DisplayName("a query-language query selects the inventory just persisted, by its product")
        @Test
        void __QueryLanguage() {
            applyEntityManagerFactory(
                    emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
                        final var v = newPersistedInstanceOf(em, targetClass);
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
                    })));
        }

        @DisplayName("a criteria query selects the inventory just persisted, by its product")
        @Test
        void __CriteriaApi() {
            applyEntityManagerFactory(
                    emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
                        final var v = newPersistedInstanceOf(em, targetClass);
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
                    })));
        }
    }

    /**
     * Tests {@code Inventory.selectListUnsoldByStoreOrderByProductInventoryDesc}, which answers
     * exercise {@code CO_SCHEMA_A_16}.
     * <p>
     * The persisted inventory has a store and a product of its own, and no order item sells the
     * product at the store, so it is unsold stock as soon as it holds any.
     */
    @Nested
    class SelectListUnsoldByStoreOrderByProductInventoryDesc_Test {

        @DisplayName(
                "the named query selects the inventory just persisted, never sold at its store")
        @Test
        void __NamedQuery() {
            applyEntityManagerFactory(
                    emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
                        final var v = newPersistedInstanceOf(em, targetClass);
                        v.setProductInventory(1L);
                        final var found = em
                                .createNamedQuery(
                                        "Inventory" +
                                        ".selectListUnsoldByStoreOrderByProductInventoryDesc",
                                        targetClass)
                                .setParameter("store", v.getStore())
                                .getResultList();
                        assertThat(found)
                                .as("the unsold inventories of the store")
                                .containsExactly(v);
                        return found;
                    })));
        }

        @DisplayName(
                "a query-language query selects the inventory just persisted, never sold at its " +
                "store")
        @Test
        void __QueryLanguage() {
            applyEntityManagerFactory(
                    emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
                        final var v = newPersistedInstanceOf(em, targetClass);
                        v.setProductInventory(1L);
                        final var found = em
                                .createQuery(
                                        """
                                                SELECT e
                                                FROM Inventory AS e
                                                WHERE e.store = :store
                                                  AND e.productInventory > 0
                                                  AND NOT EXISTS (SELECT oi
                                                                  FROM OrderItem oi
                                                                  WHERE oi.product = e.product
                                                                    AND oi.order.store = e.store)
                                                ORDER BY e.productInventory DESC, e.product.productName ASC""",
                                        targetClass
                                )
                                .setParameter("store", v.getStore())
                                .getResultList();
                        assertThat(found)
                                .as("the unsold inventories of the store")
                                .containsExactly(v);
                        return found;
                    })));
        }

        @DisplayName(
                "a criteria query selects the inventory just persisted, never sold at its store")
        @Test
        void __CriteriaApi() {
            applyEntityManagerFactory(
                    emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
                        final var v = newPersistedInstanceOf(em, targetClass);
                        v.setProductInventory(1L);
                        final var builder = em.getCriteriaBuilder();
                        final var query = builder.createQuery(targetClass);
                        final var root = query.from(targetClass);
                        final var subquery = query.subquery(OrderItem.class);
                        final var item = subquery.from(OrderItem.class);
                        subquery.select(item).where(
                                builder.equal(item.get(OrderItem_.product),
                                              root.get(Inventory_.product)),
                                builder.equal(item.get(OrderItem_.order).get(Order_.store),
                                              root.get(Inventory_.store))
                        );
                        query.select(root);
                        query.where(
                                builder.equal(root.get(Inventory_.store), v.getStore()),
                                builder.gt(root.get(Inventory_.productInventory), 0L),
                                builder.not(builder.exists(subquery))
                        );
                        query.orderBy(
                                builder.desc(root.get(Inventory_.productInventory)),
                                builder.asc(root.get(Inventory_.product).get(Product_.productName))
                        );
                        final var found = em.createQuery(query).getResultList();
                        assertThat(found)
                                .as("the unsold inventories of the store")
                                .containsExactly(v);
                        return found;
                    })));
        }

        @DisplayName("the named query skips the inventory just persisted, once it holds no stock")
        @Test
        void __NamedQuery_NoStock() {
            applyEntityManagerFactory(
                    emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
                        final var v = newPersistedInstanceOf(em, targetClass);
                        v.setProductInventory(0L);
                        final var found = em
                                .createNamedQuery(
                                        "Inventory" +
                                        ".selectListUnsoldByStoreOrderByProductInventoryDesc",
                                        targetClass)
                                .setParameter("store", v.getStore())
                                .getResultList();
                        assertThat(found)
                                .as("the unsold inventories of the store")
                                .isEmpty();
                        return found;
                    })));
        }
    }
}
