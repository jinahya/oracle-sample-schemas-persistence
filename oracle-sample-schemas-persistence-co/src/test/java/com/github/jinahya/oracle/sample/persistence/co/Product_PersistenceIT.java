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
import com.github.jinahya.oracle.sample.persistence.shared._Entity_PersistenceIT;
import com.github.jinahya.persistence.test.util.JinahyaPersistenceProviderTestUtils;
import com.github.jinahya.persistence.test.util.condition.DisabledOnUnwovenEclipseLink;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static com.github.jinahya.oracle.sample.persistence.co.__PersistenceIT_Utils.applyEntityManagerInTransactionAndRollback_;
import static com.github.jinahya.oracle.sample.persistence.co.__PersistenceIT_Utils.applyEntityManager_;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Verifies the mappings of {@link Product} against the installed {@code CO} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Product_PersistenceIT
        extends _Entity_PersistenceIT<Product, Long> {

    // ---------------------------------------------------------------------------------------------
    Product_PersistenceIT() {
        super(Product.class, Long.class, __PersistenceIT_Utils.PERSISTENCE_UNIT_NAME);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    protected void randomizedEntityInstancePersisted__(
            final EntityManagerFactory entityManagerFactory, final EntityManager entityManager,
            final Product entityInstance) {
        super.randomizedEntityInstancePersisted__(entityManagerFactory, entityManager,
                                                  entityInstance);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    protected void randomEntityInstanceSelected__(final EntityManagerFactory entityManagerFactory,
                                                  final EntityManager entityManager,
                                                  final Product entityInstance) {
        super.randomEntityInstanceSelected__(entityManagerFactory, entityManager, entityInstance);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Selects the identifier of a random product, and aborts the test when there is none.
     *
     * @return the identifier of a random product.
     */
    private Long selectRandomEntityIdOrAbort_() {
        final var optional = selectRandomEntityId_();
        assumeThat(optional).isNotEmpty();
        return optional.get();
    }

    /**
     * Returns the messages of the specified throwable and of each of its causes, joined.
     *
     * @param throwable the throwable.
     * @return the messages of the {@code throwable} and its causes.
     */
    private static String messages(final Throwable throwable) {
        final var builder = new StringBuilder();
        for (Throwable t = throwable; t != null; t = t.getCause()) {
            builder.append(t.getMessage()).append('\n');
        }
        return builder.toString();
    }

    // ---------------------------------------------------------------------------------------------
    @DisplayName("PRODUCT_DETAILS")
    @Nested
    class ProductDetailsTest {

        // EclipseLink takes an entity graph which names a basic attribute as a fetch group, which
        // needs a fetch group manager, and has one only for a class it wove (it fails with
        // EclipseLink-6114 otherwise)
        @DisabledOnUnwovenEclipseLink(
                value = "com.github.jinahya.oracle.sample.persistence.co.Product",
                disabledReason = "EclipseLink takes an entity graph only for a woven class; run it" +
                                 " woven with" +
                                 " -Pjakarta-ee-11-eclipselink,jakarta-ee-11-eclipselink-weaving"
        )
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

        @DisplayName(
                "a ProductDetails written through setProductDetailsFromMapped reads back equal")
        @Test
        void __RoundTrip() {
            final var id = selectRandomEntityIdOrAbort_();
            applyEntityManagerInTransactionAndRollback_(emf -> em -> {
                final var product = em.find(targetClass, id);
                final var value = product.getProductDetailsAsMapped(
                        b -> ___JsonBinding_TestUtils.fromJson(b, ProductDetails.class)
                );
                assumeTrue(value != null, "no product details for " + product);
                value.setDescription("written by " + getClass().getSimpleName());
                product.setProductDetailsFromMapped(value, ___JsonBinding_TestUtils::toJson);
                em.flush();
                em.clear();
                final var read = em.find(targetClass, id).getProductDetailsAsMapped(
                        b -> ___JsonBinding_TestUtils.fromJson(b, ProductDetails.class)
                );
                assertThat(read).isEqualTo(value);
                return null;
            });
        }

        @DisplayName("PRODUCTS_JSON_C rejects a PRODUCT_DETAILS which is not JSON")
        @Test
        void __NotJson_CheckConstraint() {
            final var id = selectRandomEntityIdOrAbort_();
            applyEntityManagerInTransactionAndRollback_(emf -> em -> {
                final var product = em.find(targetClass, id);
                product.setProductDetails("not a JSON document".getBytes(StandardCharsets.UTF_8));
                assertThatThrownBy(em::flush)
                        .isInstanceOf(PersistenceException.class)
                        .satisfies(e -> assertThat(messages(e)).contains("PRODUCTS_JSON_C"));
                return null;
            });
        }

        @DisplayName("every installed PRODUCT_DETAILS binds alike with Jackson, JSON-B and JSON-P")
        @Test
        void __EveryRow() throws IOException {
            // the columns, not the entities, so a lazy productDetails is selected all the same
            final var rows = applyEntityManager_(emf -> em -> em.createQuery(
                    """
                            SELECT e.productId, e.productDetails
                            FROM Product e
                            ORDER BY e.productId ASC""",
                    Object[].class
            ).getResultList());
            assumeThat(rows).isNotEmpty();
            for (final var row : rows) {
                final var bytes = (byte[]) row[1];
                if (bytes == null) {
                    continue;
                }
                final var jackson = ___Jackson_TestUtils.readValue(bytes, ProductDetails.class);
                final var jsonBinding =
                        ___JsonBinding_TestUtils.fromJson(bytes, ProductDetails.class);
                assertThat(jsonBinding).as("product %s", row[0]).isEqualTo(jackson);
                final var jsonProcessing = ___JsonProcessing_TestUtils.readObject(bytes);
                assertThat(jsonProcessing.keySet())
                        .as("product %s", row[0])
                        .isSubsetOf("colour", "gender", "brand", "description", "sizes", "reviews");
                if (jsonProcessing.containsKey("reviews")) {
                    for (final var review : jsonProcessing.getJsonArray("reviews")) {
                        assertThat(review.asJsonObject().keySet())
                                .as("a review of product %s", row[0])
                                .isSubsetOf("rating", "review");
                    }
                }
            }
        }

        @DisplayName("without a graph, productDetails is loaded unless EclipseLink wove Product")
        @Test
        void __WithoutEntityGraph() {
            final var id = selectRandomEntityIdOrAbort_();
            final boolean loaded = applyEntityManager_(emf -> em -> {
                final var product = em.find(targetClass, id);
                return emf.getPersistenceUnitUtil()
                        .isLoaded(product, Product_.productDetails.getName());
            });
            // woven, EclipseLink honours LAZY; unwoven, and Hibernate ORM without bytecode
            // enhancement, read the attribute with the entity
            assertThat(loaded).isEqualTo(
                    !JinahyaPersistenceProviderTestUtils.isWovenByEclipseLink(targetClass));
        }
    }

    // ---------------------------------------------------------------------------------------------
    @DisplayName("PRODUCT_IMAGE")
    @Nested
    class ProductImageTest {

        // EclipseLink takes an entity graph which names a basic attribute as a fetch group, which
        // needs a fetch group manager, and has one only for a class it wove (it fails with
        // EclipseLink-6114 otherwise)
        @DisabledOnUnwovenEclipseLink(
                value = "com.github.jinahya.oracle.sample.persistence.co.Product",
                disabledReason = "EclipseLink takes an entity graph only for a woven class; run it" +
                                 " woven with" +
                                 " -Pjakarta-ee-11-eclipselink,jakarta-ee-11-eclipselink-weaving"
        )
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

        @DisplayName("a productImage written reads back equal")
        @Test
        void __RoundTrip() {
            final var id = selectRandomEntityIdOrAbort_();
            final var bytes = new byte[1024];
            ThreadLocalRandom.current().nextBytes(bytes);
            applyEntityManagerInTransactionAndRollback_(emf -> em -> {
                final var product = em.find(targetClass, id);
                product.setProductImage(bytes);
                product.setImageMimeType("application/octet-stream");
                em.flush();
                em.clear();
                final var read = em.find(targetClass, id);
                assertThat(read.getProductImage()).isEqualTo(bytes);
                assertThat(read.getImageMimeType()).isEqualTo("application/octet-stream");
                return null;
            });
        }
    }
}
