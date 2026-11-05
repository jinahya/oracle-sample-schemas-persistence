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

import com.github.jinahya.oracle.sample.persistence.co.mapped.ProductDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.github.jinahya.oracle.sample.persistence.co.__PersistenceIT_Utils.applyEntityManagerFactory;
import static com.github.jinahya.oracle.sample.persistence.co.__PersistenceIT_Utils.applyEntityManagerInTransactionAndRollback_;
import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManager;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Verifies the mappings of {@link Product} against the installed {@code CO} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Product_PersistenceIT
        extends _Entity_PersistenceIT<Product, Long> {

    private static BigDecimal unitPriceMin;

    private static BigDecimal unitPriceMax;

    @BeforeAll
    void __() {
        applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
            ProductPersistenceUtils.applyMinUnitPriceAndMaxUnitPrice(entityManager, (m, x) -> {
                unitPriceMin = m;
                unitPriceMax = x;
                return null;
            });
            return null;
        }));
    }

    // ---------------------------------------------------------------------------------------------
    Product_PersistenceIT() {
        super(Product.class, Long.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    void randomizedEntityInstancePersisted__(final EntityManager entityManager,
                                             final Product entityInstance) {
        super.randomizedEntityInstancePersisted__(entityManager, entityInstance);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    protected void randomEntityInstanceSelected__(final EntityManager entityManager,
                                                  final Product entityInstance) {
        super.randomEntityInstanceSelected__(entityManager, entityInstance);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Returns two unit prices of the installed table, the lower one first: those at the first and
     * the third quartile of the installed, distinct, non-{@code null} unit prices.
     *
     * @implNote Selects the unit prices, not the entities, so nothing is left managed.
     *         Assumes the installed table has at least two distinct unit prices; the test is
     *         aborted, not failed, if it has not.
     */
    private BigDecimal[] unitPriceRange() {
        return applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
            final var unitPrices = entityManager
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
        }));
    }

    @Nested
    class SelectListByUnitPriceGreaterThanEqualAndUnitPriceLessThanOrderByUnitPricesAsc_Test {

        private void verify(final List<Product> found, final BigDecimal unitPriceMinInclusive,
                            final BigDecimal unitPriceMaxExclusive) {
            assertThat(found)
                    .as("the products whose %s is in [%s, %s), ordered by %s",
                        Product_.unitPrice.getName(),
                        unitPriceMinInclusive, unitPriceMaxExclusive, Product_.unitPrice.getName())
                    .isNotEmpty()
                    .extracting(Product::getUnitPrice)
                    .isSorted()
                    .allSatisfy(v -> assertThat(v)
                            .isGreaterThanOrEqualTo(unitPriceMinInclusive)
                            .isLessThan(unitPriceMaxExclusive));
        }

        @DisplayName(
                "the named query selects the installed products in a half-open unit price range")
        @Test
        void __NamedQuery() {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var range = unitPriceRange();
                final var found = entityManager
                        .createNamedQuery(
                                "Product" +
                                ".selectListByUnitPriceGreaterThanEqualAndUnitPriceLessThanOrderByUnitPricesAsc",
                                targetClass
                        )
                        .setParameter("unitPriceMinInclusive", range[0])
                        .setParameter("unitPriceMaxExclusive", range[1])
                        .getResultList();
                verify(found, range[0], range[1]);
                return null;
            }));
        }

        @DisplayName(
                "a query-language query selects the installed products in a half-open unit price " +
                "range")
        @Test
        void __QueryLanguage() {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var range = unitPriceRange();
                final var found = entityManager
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
                return null;
            }));
        }

        @DisplayName(
                "a criteria query selects the installed products in a half-open unit price range")
        @Test
        void __CriteriaApi() {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var range = unitPriceRange();
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
                return null;
            }));
        }
    }

    @Nested
    class SelectListByUnitPriceBetweenOrderByUnitPricesAsc_Test {

        private void verify(final List<Product> found, final BigDecimal unitPriceAfter,
                            final BigDecimal unitPriceBefore) {
            assertThat(found)
                    .as("the products whose %s is in [%s, %s], ordered by %s",
                        Product_.unitPrice.getName(),
                        unitPriceAfter, unitPriceBefore, Product_.unitPrice.getName())
                    .isNotEmpty()
                    .extracting(Product::getUnitPrice)
                    .isSorted()
                    .allSatisfy(v -> assertThat(v).isBetween(unitPriceAfter, unitPriceBefore));
        }

        @DisplayName("the named query selects the installed products in a closed unit price range")
        @Test
        void __NamedQuery() {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var range = unitPriceRange();
                final var found = entityManager
                        .createNamedQuery(
                                "Product.selectListByUnitPriceBetweenOrderByUnitPricesAsc",
                                targetClass)
                        .setParameter("unitPriceAfter", range[0])
                        .setParameter("unitPriceBefore", range[1])
                        .getResultList();
                verify(found, range[0], range[1]);
                return null;
            }));
        }

        @DisplayName(
                "a query-language query selects the installed products in a closed unit price " +
                "range")
        @Test
        void __QueryLanguage() {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var range = unitPriceRange();
                final var found = entityManager
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
                return null;
            }));
        }

        @DisplayName("a criteria query selects the installed products in a closed unit price range")
        @Test
        void __CriteriaApi() {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var range = unitPriceRange();
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.where(builder.between(root.get(Product_.unitPrice), range[0], range[1]));
                query.orderBy(builder.asc(root.get(Product_.unitPrice)));
                final var found = entityManager.createQuery(query).getResultList();
                verify(found, range[0], range[1]);
                return null;
            }));
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Assumes that the specified factory's provider can take an entity graph which names a basic
     * attribute: EclipseLink takes one as a fetch group, which needs a fetch group manager, and
     * has one only for a class it wove (it fails with {@code EclipseLink-6114} otherwise).
     *
     * @param entityManagerFactory the factory.
     */
    private void assumeEntityGraphOfBasicAttribute(final EntityManagerFactory entityManagerFactory) {
        assumeTrue(
                !entityManagerFactory.getClass().getName().startsWith("org.eclipse.persistence.")
                || __Object_Test.isWovenByEclipseLink(targetClass),
                "EclipseLink takes an entity graph only for a woven class; run it woven with " +
                "-Pjakarta-ee-11-eclipselink,jakarta-ee-11-eclipselink-weaving"
        );
    }

    // ---------------------------------------------------------------------------------------------
    @DisplayName("PRODUCT_DETAILS")
    @Nested
    class ProductDetailsTest {

        @DisplayName(
                "the named entity graph loads the lazy productDetails, a ProductDetails document")
        @Test
        void __() throws IOException {
            final Long id;
            {
                final var optional = selectRandomEntityId_();
                assumeThat(optional).isNotEmpty();
                id = optional.get();
            }
            final var found = applyEntityManagerInTransactionAndRollback_(emf -> em -> {
                assumeEntityGraphOfBasicAttribute(emf);
                // a JOIN FETCH takes an association only; a lazy basic attribute is fetched with
                // an entity graph which names it
                final var properties = Map.<String, Object>of(
                        "jakarta.persistence.fetchgraph",
                        em.getEntityGraph(Product.ENTITY_GRAPH_NAME_PRODUCT_DETAILS)
                );
                final var product = em.find(targetClass, id, properties);
                assertThat(product).isNotNull();
                assertThat(emf.getPersistenceUnitUtil()
                                   .isLoaded(product, Product_.productDetails.getName())
                ).isTrue();
                return product;
            });
            final var productDetails = found.getProductDetails();
            assumeTrue(productDetails != null, "no product details for " + found);
            {
                final var jsonProcessing = ___JsonProcessing_TestUtils.readObject(productDetails);
                // the document holds the properties of a ProductDetails, and none else
                assertThat(jsonProcessing.keySet())
                        .isSubsetOf("colour", "gender", "brand", "description", "sizes", "reviews");
            }
            {
                final var jackson =
                        ___Jackson_TestUtils.readValue(productDetails, ProductDetails.class);
                assertThat(jackson).isNotNull();
            }
            {
                final var jsonBinding =
                        ___JsonBinding_TestUtils.fromJson(productDetails, ProductDetails.class);
                assertThat(jsonBinding).isNotNull();
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
    @DisplayName("PRODUCT_IMAGE")
    @Nested
    class ProductImageTest {

        @DisplayName("the named entity graph loads the lazy productImage")
        @Test
        void __() {
            final Long id;
            {
                final var optional = selectRandomEntityId_();
                assumeThat(optional).isNotEmpty();
                id = optional.get();
            }
            final var found = applyEntityManagerInTransactionAndRollback_(emf -> em -> {
                assumeEntityGraphOfBasicAttribute(emf);
                // a JOIN FETCH takes an association only; a lazy basic attribute is fetched with
                // an entity graph which names it
                final var properties = Map.<String, Object>of(
                        "jakarta.persistence.fetchgraph",
                        em.getEntityGraph(Product.ENTITY_GRAPH_NAME_PRODUCT_IMAGE)
                );
                final var product = em.find(targetClass, id, properties);
                assertThat(product).isNotNull();
                assertThat(emf.getPersistenceUnitUtil()
                                   .isLoaded(product, Product_.productImage.getName())
                ).isTrue();
                return product;
            });
            // the installed rows carry no image, so this part runs only on a row which has one
            final var productImage = found.getProductImage();
            assumeTrue(productImage != null, "no product image for " + found);
            assertThat(productImage).isNotEmpty();
            assertThat(found.getImageMimeType()).isNotBlank();
        }
    }
}
