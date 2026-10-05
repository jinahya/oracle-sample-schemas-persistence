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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Verifies the mappings of {@link Product} against the installed {@code CO} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Product_Persistence_IT extends _Persistence_IT<Product> {

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
    Product_Persistence_IT() {
        super(Product.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns two unit prices of the installed table, the lower one first: those at the first and the third quartile of
     * the installed, distinct, non-{@code null} unit prices.
     *
     * @implNote Selects the unit prices, not the entities, so nothing is left managed. Assumes the installed table has
     * at least two distinct unit prices; the test is aborted, not failed, if it has not.
     */
    private BigDecimal[] unitPriceRange() {
        final var unitPrices = entityManager()
                .createQuery(
                        """
                                SELECT DISTINCT e.unitPrice
                                FROM Product e
                                WHERE e.unitPrice IS NOT NULL
                                ORDER BY e.unitPrice ASC""",
                        BigDecimal.class
                )
                .getResultList();
        assumeTrue(unitPrices.size() > 1, "no two distinct unit prices to select between");
        return new BigDecimal[]{
                unitPrices.get(unitPrices.size() / 4),
                unitPrices.get(unitPrices.size() * 3 / 4)
        };
    }

    @Nested
    class SelectListByUnitPriceGreaterThanEqualAndUnitPriceLessThanOrderByUnitPricesAsc_Test {

        private void verify(final List<Product> found, final BigDecimal unitPriceMinInclusive,
                            final BigDecimal unitPriceMaxExclusive) {
            assertThat(found)
                    .as("the products whose %s is in [%s, %s), ordered by %s", Product_.unitPrice.getName(),
                        unitPriceMinInclusive, unitPriceMaxExclusive, Product_.unitPrice.getName())
                    .isNotEmpty()
                    .extracting(Product::getUnitPrice)
                    .isSorted()
                    .allSatisfy(v -> assertThat(v)
                            .isGreaterThanOrEqualTo(unitPriceMinInclusive)
                            .isLessThan(unitPriceMaxExclusive));
        }

        @DisplayName("the named query selects the installed products in a half-open unit price range")
        @Test
        void __NamedQuery() {
            final var range = unitPriceRange();
            final var found = entityManager()
                    .createNamedQuery(
                            "Product.selectListByUnitPriceGreaterThanEqualAndUnitPriceLessThanOrderByUnitPricesAsc",
                            targetClass
                    )
                    .setParameter("unitPriceMinInclusive", range[0])
                    .setParameter("unitPriceMaxExclusive", range[1])
                    .getResultList();
            verify(found, range[0], range[1]);
        }

        @DisplayName("a query-language query selects the installed products in a half-open unit price range")
        @Test
        void __QueryLanguage() {
            final var range = unitPriceRange();
            final var found = entityManager()
                    .createQuery(
                            """
                                    SELECT e
                                    FROM Product e
                                    WHERE e.unitPrice >= :unitPriceMinInclusive AND e.unitPrice < :unitPriceMaxExclusive
                                    ORDER BY e.unitPrice ASC""",
                            targetClass
                    )
                    .setParameter("unitPriceMinInclusive", range[0])
                    .setParameter("unitPriceMaxExclusive", range[1])
                    .getResultList();
            verify(found, range[0], range[1]);
        }

        @DisplayName("a criteria query selects the installed products in a half-open unit price range")
        @Test
        void __CriteriaApi() {
            final var range = unitPriceRange();
            final var entityManager = entityManager();
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(targetClass);
            final var root = query.from(targetClass);
            query.select(root);
            query.where(
                    builder.greaterThanOrEqualTo(root.get(Product_.unitPrice), range[0]),
                    builder.lessThan(root.get(Product_.unitPrice), range[1])
            );
            query.orderBy(builder.asc(root.get(Product_.unitPrice)));
            final var found = entityManager.createQuery(query).getResultList();
            verify(found, range[0], range[1]);
        }
    }

    @Nested
    class SelectListByUnitPriceBetweenOrderByUnitPricesAsc_Test {

        private void verify(final List<Product> found, final BigDecimal unitPriceAfter,
                            final BigDecimal unitPriceBefore) {
            assertThat(found)
                    .as("the products whose %s is in [%s, %s], ordered by %s", Product_.unitPrice.getName(),
                        unitPriceAfter, unitPriceBefore, Product_.unitPrice.getName())
                    .isNotEmpty()
                    .extracting(Product::getUnitPrice)
                    .isSorted()
                    .allSatisfy(v -> assertThat(v).isBetween(unitPriceAfter, unitPriceBefore));
        }

        @DisplayName("the named query selects the installed products in a closed unit price range")
        @Test
        void __NamedQuery() {
            final var range = unitPriceRange();
            final var found = entityManager()
                    .createNamedQuery("Product.selectListByUnitPriceBetweenOrderByUnitPricesAsc", targetClass)
                    .setParameter("unitPriceAfter", range[0])
                    .setParameter("unitPriceBefore", range[1])
                    .getResultList();
            verify(found, range[0], range[1]);
        }

        @DisplayName("a query-language query selects the installed products in a closed unit price range")
        @Test
        void __QueryLanguage() {
            final var range = unitPriceRange();
            final var found = entityManager()
                    .createQuery(
                            """
                                    SELECT e
                                    FROM Product e
                                    WHERE e.unitPrice BETWEEN :unitPriceAfter AND :unitPriceBefore
                                    ORDER BY e.unitPrice ASC""",
                            targetClass
                    )
                    .setParameter("unitPriceAfter", range[0])
                    .setParameter("unitPriceBefore", range[1])
                    .getResultList();
            verify(found, range[0], range[1]);
        }

        @DisplayName("a criteria query selects the installed products in a closed unit price range")
        @Test
        void __CriteriaApi() {
            final var range = unitPriceRange();
            final var entityManager = entityManager();
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(targetClass);
            final var root = query.from(targetClass);
            query.select(root);
            query.where(builder.between(root.get(Product_.unitPrice), range[0], range[1]));
            query.orderBy(builder.asc(root.get(Product_.unitPrice)));
            final var found = entityManager.createQuery(query).getResultList();
            verify(found, range[0], range[1]);
        }
    }
}
