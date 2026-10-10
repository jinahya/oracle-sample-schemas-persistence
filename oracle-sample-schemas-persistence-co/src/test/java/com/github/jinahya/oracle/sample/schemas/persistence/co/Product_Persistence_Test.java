package com.github.jinahya.oracle.sample.schemas.persistence.co;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

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
 * Verifies the mappings of {@link Product} against the schema generated into the in-memory database.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Product_Persistence_Test extends _DomainEntity_Persistence_Test<Product, Long> {

    private static BigDecimal unitPriceMin;

    private static BigDecimal unitPriceMax;

    @BeforeAll
    void __() {
        ProductPersistenceUtils.applyMinUnitPriceAndMaxUnitPrice(entityManager(), (m, x) -> {
            unitPriceMin = m;
            unitPriceMax = x;
            return null;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    Product_Persistence_Test() {
        super(Product.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The minimum unit price of the range the nested tests select, which is the unit price of a persisted product.
     */
    private static final BigDecimal UNIT_PRICE_MIN = new BigDecimal("2.00");

    /**
     * The maximum unit price of the range the nested tests select, which is the unit price of a persisted product.
     */
    private static final BigDecimal UNIT_PRICE_MAX = new BigDecimal("4.00");

    /**
     * Persists five products, whose unit prices are {@code 1.00} through {@code 5.00}, in descending order.
     *
     * @param em an entity manager in a transaction.
     * @return a list of the persisted products.
     * @implNote Persists in descending order of unit price, so a query's ordering is not merely the insertion order.
     */
    private static List<Product> persistProducts(final EntityManager em) {
        final var randomizer = new Product_Randomizer();
        return IntStream.rangeClosed(1, 5)
                .map(i -> 6 - i)
                .mapToObj(i -> {
                    final var product = randomizer.get();
                    product.setUnitPrice(BigDecimal.valueOf(i * 100L, 2));
                    em.persist(product);
                    return product;
                })
                .toList();
    }

    @Nested
    class SelectListByUnitPriceGreaterThanEqualAndUnitPriceLessThanOrderByUnitPricesAsc_Test {

        private void verify(final List<Product> persisted, final List<Product> found) {
            assertThat(found)
                    .as("the products whose %s is in [%s, %s), ordered by %s", Product_.unitPrice.getName(),
                        UNIT_PRICE_MIN, UNIT_PRICE_MAX, Product_.unitPrice.getName())
                    .containsAll(persisted.stream()
                                         .filter(v -> v.getUnitPrice().compareTo(UNIT_PRICE_MIN) >= 0)
                                         .filter(v -> v.getUnitPrice().compareTo(UNIT_PRICE_MAX) < 0)
                                         .toList())
                    .extracting(Product::getUnitPrice)
                    .isSorted()
                    .allSatisfy(v -> assertThat(v)
                            .isGreaterThanOrEqualTo(UNIT_PRICE_MIN)
                            .isLessThan(UNIT_PRICE_MAX));
        }

        @DisplayName("the named query selects the products just persisted, in a half-open unit price range")
        @Test
        void __NamedQuery() {
            applyEntityManagerInTransactionAndRollback(em -> {
                final var persisted = persistProducts(em);
                final var found = em
                        .createNamedQuery(
                                "Product.selectListByUnitPriceGreaterThanEqualAndUnitPriceLessThanOrderByUnitPricesAsc",
                                targetClass
                        )
                        .setParameter("unitPriceMinInclusive", UNIT_PRICE_MIN)
                        .setParameter("unitPriceMaxExclusive", UNIT_PRICE_MAX)
                        .getResultList();
                verify(persisted, found);
                return found;
            });
        }

        @DisplayName("a query-language query selects the products just persisted, in a half-open unit price range")
        @Test
        void __QueryLanguage() {
            applyEntityManagerInTransactionAndRollback(em -> {
                final var persisted = persistProducts(em);
                final var found = em
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Product e
                                        WHERE e.unitPrice >= :unitPriceMinInclusive AND e.unitPrice < :unitPriceMaxExclusive
                                        ORDER BY e.unitPrice ASC""",
                                targetClass
                        )
                        .setParameter("unitPriceMinInclusive", UNIT_PRICE_MIN)
                        .setParameter("unitPriceMaxExclusive", UNIT_PRICE_MAX)
                        .getResultList();
                verify(persisted, found);
                return found;
            });
        }

        @DisplayName("a criteria query selects the products just persisted, in a half-open unit price range")
        @Test
        void __CriteriaApi() {
            applyEntityManagerInTransactionAndRollback(em -> {
                final var persisted = persistProducts(em);
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.where(
                        builder.greaterThanOrEqualTo(root.get(Product_.unitPrice), UNIT_PRICE_MIN),
                        builder.lessThan(root.get(Product_.unitPrice), UNIT_PRICE_MAX)
                );
                query.orderBy(builder.asc(root.get(Product_.unitPrice)));
                final var found = em.createQuery(query).getResultList();
                verify(persisted, found);
                return found;
            });
        }
    }

    @Nested
    class SelectListByUnitPriceBetweenOrderByUnitPricesAsc_Test {

        private void verify(final List<Product> persisted, final List<Product> found) {
            assertThat(found)
                    .as("the products whose %s is in [%s, %s], ordered by %s", Product_.unitPrice.getName(),
                        UNIT_PRICE_MIN, UNIT_PRICE_MAX, Product_.unitPrice.getName())
                    .containsAll(persisted.stream()
                                         .filter(v -> v.getUnitPrice().compareTo(UNIT_PRICE_MIN) >= 0)
                                         .filter(v -> v.getUnitPrice().compareTo(UNIT_PRICE_MAX) <= 0)
                                         .toList())
                    .extracting(Product::getUnitPrice)
                    .isSorted()
                    .allSatisfy(v -> assertThat(v).isBetween(UNIT_PRICE_MIN, UNIT_PRICE_MAX));
        }

        @DisplayName("the named query selects the products just persisted, in a closed unit price range")
        @Test
        void __NamedQuery() {
            applyEntityManagerInTransactionAndRollback(em -> {
                final var persisted = persistProducts(em);
                final var found = em
                        .createNamedQuery("Product.selectListByUnitPriceBetweenOrderByUnitPricesAsc", targetClass)
                        .setParameter("unitPriceAfter", UNIT_PRICE_MIN)
                        .setParameter("unitPriceBefore", UNIT_PRICE_MAX)
                        .getResultList();
                verify(persisted, found);
                return found;
            });
        }

        @DisplayName("a query-language query selects the products just persisted, in a closed unit price range")
        @Test
        void __QueryLanguage() {
            applyEntityManagerInTransactionAndRollback(em -> {
                final var persisted = persistProducts(em);
                final var found = em
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Product e
                                        WHERE e.unitPrice BETWEEN :unitPriceAfter AND :unitPriceBefore
                                        ORDER BY e.unitPrice ASC""",
                                targetClass
                        )
                        .setParameter("unitPriceAfter", UNIT_PRICE_MIN)
                        .setParameter("unitPriceBefore", UNIT_PRICE_MAX)
                        .getResultList();
                verify(persisted, found);
                return found;
            });
        }

        @DisplayName("a criteria query selects the products just persisted, in a closed unit price range")
        @Test
        void __CriteriaApi() {
            applyEntityManagerInTransactionAndRollback(em -> {
                final var persisted = persistProducts(em);
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.where(builder.between(root.get(Product_.unitPrice), UNIT_PRICE_MIN, UNIT_PRICE_MAX));
                query.orderBy(builder.asc(root.get(Product_.unitPrice)));
                final var found = em.createQuery(query).getResultList();
                verify(persisted, found);
                return found;
            });
        }
    }
}
