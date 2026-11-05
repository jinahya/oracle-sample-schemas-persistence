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
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static com.github.jinahya.oracle.sample.persistence.co.__PersistenceIT_Utils.applyEntityManagerFactory;
import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the mappings of {@link Inventory} against the installed {@code CO} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Inventory_PersistenceIT
        extends _Entity_PersistenceIT<Inventory, Long> {

    // ---------------------------------------------------------------------------------------------
    Inventory_PersistenceIT() {
        super(Inventory.class, Long.class);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Tests {@code Inventory.selectOneByStoreAndProduct}, which answers exercise
     * {@code CO_INVENTORY_B_01}.
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
            return applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                return entityManager
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
            }));
        }

        @DisplayName("a query-language query selects the installed inventory")
        @MethodSource("inventoryIds")
        @ParameterizedTest
        void __QueryLanguage(final Long inventoryId) {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var expected = entityManager.find(targetClass, inventoryId);
                final var found = entityManager
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
                        .as("the inventory selected by the store and the product of %s",
                            inventoryId)
                        .isSameAs(expected);
                return null;
            }));
        }

        @DisplayName("a criteria query selects the installed inventory")
        @MethodSource("inventoryIds")
        @ParameterizedTest
        void __CriteriaApi(final Long inventoryId) {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
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
                        .as("the inventory selected by the store and the product of %s",
                            inventoryId)
                        .isSameAs(expected);
                return null;
            }));
        }
    }

    /**
     * Tests {@code Inventory.selectOneByProductOrderByProductInventoryAsc}, which answers exercise
     * {@code CO_INVENTORY_B_02}.
     */
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class SelectOneByProductOrderByProductInventoryAsc_Test {

        /**
         * Returns some ids of the products the installed table holds stock of.
         */
        Stream<Long> productIds() {
            return applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                return entityManager
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
            }));
        }

        private void verify(final Long productId, final List<Inventory> found) {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var count = entityManager
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
                        .allSatisfy(
                                i -> assertThat(i.getProduct().getProductId()).isEqualTo(productId))
                        .isSortedAccordingTo(Comparator.comparing(Inventory::getProductInventory));
                return null;
            }));
        }

        @DisplayName("a query-language query selects the product's inventories, lowest stock first")
        @MethodSource("productIds")
        @ParameterizedTest
        void __QueryLanguage(final Long productId) {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var found = entityManager
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Inventory AS e
                                        WHERE e.product = :product
                                        ORDER BY e.productInventory ASC""",
                                targetClass
                        )
                        .setParameter("product",
                                      entityManager.getReference(Product.class, productId))
                        .getResultList();
                verify(productId, found);
                return null;
            }));
        }

        @DisplayName("a criteria query selects the product's inventories, lowest stock first")
        @MethodSource("productIds")
        @ParameterizedTest
        void __CriteriaApi(final Long productId) {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.where(builder.equal(root.get(Inventory_.product),
                                          entityManager.getReference(Product.class, productId)));
                query.orderBy(builder.asc(root.get(Inventory_.productInventory)));
                final var found = entityManager.createQuery(query).getResultList();
                verify(productId, found);
                return null;
            }));
        }
    }

    /**
     * Tests {@code Inventory.selectListUnsoldByStoreOrderByProductInventoryDesc}, which answers
     * exercise {@code CO_SCHEMA_A_16}.
     */
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class SelectListUnsoldByStoreOrderByProductInventoryDesc_Test {

        /**
         * Returns some ids of the stores the installed table holds stock at.
         */
        Stream<Long> storeIds() {
            return applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                return entityManager
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
            }));
        }

        private void verify(final Long storeId, final List<Inventory> found) {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                assertThat(found)
                        .as("the unsold inventories of store %s", storeId)
                        .allSatisfy(i -> {
                            assertThat(i.getStore().getStoreId()).isEqualTo(storeId);
                            assertThat(i.getProductInventory()).isPositive();
                            assertThat(entityManager
                                               .createQuery(
                                                       """
                                                               SELECT COUNT(oi)
                                                               FROM OrderItem oi
                                                               WHERE oi.product = :product
                                                                 AND oi.order.store = :store""",
                                                       Long.class
                                               )
                                               .setParameter("product", i.getProduct())
                                               .setParameter("store", i.getStore())
                                               .getSingleResult())
                                    .as("order items selling the product of %s at its store",
                                        i.getInventoryId())
                                    .isZero();
                        })
                        .isSortedAccordingTo(
                                Comparator.comparing(Inventory::getProductInventory).reversed());
                return null;
            }));
        }

        @DisplayName(
                "a query-language query selects the store's stock never sold there, largest first")
        @MethodSource("storeIds")
        @ParameterizedTest
        void __QueryLanguage(final Long storeId) {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var found = entityManager
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
                        .setParameter("store", entityManager.getReference(Store.class, storeId))
                        .getResultList();
                verify(storeId, found);
                return null;
            }));
        }

        @DisplayName("a criteria query selects the store's stock never sold there, largest first")
        @MethodSource("storeIds")
        @ParameterizedTest
        void __CriteriaApi(final Long storeId) {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                final var subquery = query.subquery(OrderItem.class);
                final var item = subquery.from(OrderItem.class);
                subquery.select(item).where(
                        builder.equal(item.get(OrderItem_.product), root.get(Inventory_.product)),
                        builder.equal(item.get(OrderItem_.order).get(Order_.store),
                                      root.get(Inventory_.store))
                );
                query.select(root);
                query.where(
                        builder.equal(root.get(Inventory_.store),
                                      entityManager.getReference(Store.class, storeId)),
                        builder.gt(root.get(Inventory_.productInventory), 0L),
                        builder.not(builder.exists(subquery))
                );
                query.orderBy(
                        builder.desc(root.get(Inventory_.productInventory)),
                        builder.asc(root.get(Inventory_.product).get(Product_.productName))
                );
                final var found = entityManager.createQuery(query).getResultList();
                verify(storeId, found);
                return null;
            }));
        }
    }
}
