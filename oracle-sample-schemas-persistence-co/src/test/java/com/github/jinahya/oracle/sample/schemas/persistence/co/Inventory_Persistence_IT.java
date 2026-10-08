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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the mappings of {@link Inventory} against the installed {@code CO} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Inventory_Persistence_IT extends _Persistence_IT<Inventory> {

    // -----------------------------------------------------------------------------------------------------------------
    Inventory_Persistence_IT() {
        super(Inventory.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@code Inventory.selectOneByStoreAndProduct}, which answers exercise {@code CO-INVENTORY-B-01}.
     */
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class SelectOneByStoreAndProduct_Test {

        /**
         * Returns some inventory ids of the installed table.
         *
         * @implNote Selects the ids, not the entities, so nothing is left managed.
         */
        Stream<Long> inventoryIds() {
            return entityManager()
                    .createQuery(
                            """
                                    SELECT e.inventoryId
                                    FROM Inventory e
                                    ORDER BY e.inventoryId ASC""",
                            Long.class
                    )
                    .setMaxResults(5)
                    .getResultList()
                    .stream();
        }

        @DisplayName("the named query selects the installed inventory")
        @MethodSource("inventoryIds")
        @ParameterizedTest
        void __NamedQuery(final Long inventoryId) {
            final var expected = entityManager().find(targetClass, inventoryId);
            final var found = entityManager()
                    .createNamedQuery("Inventory.selectOneByStoreAndProduct", targetClass)
                    .setParameter("store", expected.getStore())
                    .setParameter("product", expected.getProduct())
                    .getSingleResult();
            assertThat(found)
                    .as("the inventory selected by the store and the product of %s", inventoryId)
                    .isSameAs(expected);
        }

        @DisplayName("a query-language query selects the installed inventory")
        @MethodSource("inventoryIds")
        @ParameterizedTest
        void __QueryLanguage(final Long inventoryId) {
            final var expected = entityManager().find(targetClass, inventoryId);
            final var found = entityManager()
                    .createQuery(
                            """
                                    SELECT e
                                    FROM Inventory AS e
                                    WHERE e.store = :store AND e.product = :product""",
                            targetClass
                    )
                    .setParameter("store", expected.getStore())
                    .setParameter("product", expected.getProduct())
                    .getSingleResult();
            assertThat(found)
                    .as("the inventory selected by the store and the product of %s", inventoryId)
                    .isSameAs(expected);
        }

        @DisplayName("a criteria query selects the installed inventory")
        @MethodSource("inventoryIds")
        @ParameterizedTest
        void __CriteriaApi(final Long inventoryId) {
            final var entityManager = entityManager();
            final var expected = entityManager.find(targetClass, inventoryId);
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(targetClass);
            final var root = query.from(targetClass);
            query.select(root);
            query.where(
                    builder.equal(root.get(Inventory_.store), expected.getStore()),
                    builder.equal(root.get(Inventory_.product), expected.getProduct())
            );
            final var found = entityManager.createQuery(query).getSingleResult();
            assertThat(found)
                    .as("the inventory selected by the store and the product of %s", inventoryId)
                    .isSameAs(expected);
        }
    }

    /**
     * Tests {@code Inventory.selectOneByProductOrderByProductInventoryAsc}, which answers exercise
     * {@code CO-INVENTORY-B-02}.
     */
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class SelectOneByProductOrderByProductInventoryAsc_Test {

        /**
         * Returns some ids of the products the installed table holds stock of.
         */
        Stream<Long> productIds() {
            return entityManager()
                    .createQuery(
                            """
                                    SELECT DISTINCT e.product.productId
                                    FROM Inventory e
                                    ORDER BY e.product.productId ASC""",
                            Long.class
                    )
                    .setMaxResults(5)
                    .getResultList()
                    .stream();
        }

        private void verify(final Long productId, final List<Inventory> found) {
            final var count = entityManager()
                    .createQuery(
                            """
                                    SELECT COUNT(e)
                                    FROM Inventory e
                                    WHERE e.product.productId = :productId""",
                            Long.class
                    )
                    .setParameter("productId", productId)
                    .getSingleResult();
            assertThat(found)
                    .as("the inventories of product %s", productId)
                    .hasSize(count.intValue())
                    .allSatisfy(i -> assertThat(i.getProduct().getProductId()).isEqualTo(productId))
                    .isSortedAccordingTo(Comparator.comparing(Inventory::getProductInventory));
        }

        @DisplayName("the named query selects the product's inventories, lowest stock first")
        @MethodSource("productIds")
        @ParameterizedTest
        void __NamedQuery(final Long productId) {
            final var found = entityManager()
                    .createNamedQuery("Inventory.selectOneByProductOrderByProductInventoryAsc", targetClass)
                    .setParameter("product",
                                  entityManager().getReference(Product.class, productId))
                    .getResultList();
            verify(productId, found);
        }

        @DisplayName("a query-language query selects the product's inventories, lowest stock first")
        @MethodSource("productIds")
        @ParameterizedTest
        void __QueryLanguage(final Long productId) {
            final var found = entityManager()
                    .createQuery(
                            """
                                    SELECT e
                                    FROM Inventory AS e
                                    WHERE e.product = :product
                                    ORDER BY e.productInventory ASC""",
                            targetClass
                    )
                    .setParameter("product",
                                  entityManager().getReference(Product.class, productId))
                    .getResultList();
            verify(productId, found);
        }

        @DisplayName("a criteria query selects the product's inventories, lowest stock first")
        @MethodSource("productIds")
        @ParameterizedTest
        void __CriteriaApi(final Long productId) {
            final var entityManager = entityManager();
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(targetClass);
            final var root = query.from(targetClass);
            query.select(root);
            query.where(builder.equal(root.get(Inventory_.product),
                                      entityManager.getReference(Product.class, productId)));
            query.orderBy(builder.asc(root.get(Inventory_.productInventory)));
            final var found = entityManager.createQuery(query).getResultList();
            verify(productId, found);
        }
    }

    /**
     * Tests {@code Inventory.selectListByStoreOrderByProductInventoryAsc}, which answers exercise
     * {@code CO-INVENTORY-B-03}.
     */
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class SelectListByStoreOrderByProductInventoryAsc_Test {

        /**
         * Returns some ids of the stores the installed table holds stock at.
         */
        Stream<Long> storeIds() {
            return entityManager()
                    .createQuery(
                            """
                                    SELECT DISTINCT e.store.storeId
                                    FROM Inventory e
                                    ORDER BY e.store.storeId ASC""",
                            Long.class
                    )
                    .setMaxResults(5)
                    .getResultList()
                    .stream();
        }

        private void verify(final Long storeId, final List<Inventory> found) {
            final var count = entityManager()
                    .createQuery(
                            """
                                    SELECT COUNT(e)
                                    FROM Inventory e
                                    WHERE e.store.storeId = :storeId""",
                            Long.class
                    )
                    .setParameter("storeId", storeId)
                    .getSingleResult();
            assertThat(found)
                    .as("the inventories of store %s", storeId)
                    .hasSize(count.intValue())
                    .allSatisfy(i -> assertThat(i.getStore().getStoreId()).isEqualTo(storeId))
                    .isSortedAccordingTo(Comparator.comparing(Inventory::getProductInventory));
        }

        @DisplayName("the named query selects the store's inventories, lowest stock first")
        @MethodSource("storeIds")
        @ParameterizedTest
        void __NamedQuery(final Long storeId) {
            final var found = entityManager()
                    .createNamedQuery("Inventory.selectListByStoreOrderByProductInventoryAsc", targetClass)
                    .setParameter("store", entityManager().getReference(Store.class, storeId))
                    .getResultList();
            verify(storeId, found);
        }

        @DisplayName("a query-language query selects the store's inventories, lowest stock first")
        @MethodSource("storeIds")
        @ParameterizedTest
        void __QueryLanguage(final Long storeId) {
            final var found = entityManager()
                    .createQuery(
                            """
                                    SELECT e
                                    FROM Inventory AS e
                                    WHERE e.store = :store
                                    ORDER BY e.productInventory ASC""",
                            targetClass
                    )
                    .setParameter("store", entityManager().getReference(Store.class, storeId))
                    .getResultList();
            verify(storeId, found);
        }

        @DisplayName("a criteria query selects the store's inventories, lowest stock first")
        @MethodSource("storeIds")
        @ParameterizedTest
        void __CriteriaApi(final Long storeId) {
            final var entityManager = entityManager();
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(targetClass);
            final var root = query.from(targetClass);
            query.select(root);
            query.where(builder.equal(root.get(Inventory_.store), entityManager.getReference(Store.class, storeId)));
            query.orderBy(builder.asc(root.get(Inventory_.productInventory)));
            final var found = entityManager.createQuery(query).getResultList();
            verify(storeId, found);
        }
    }
}
