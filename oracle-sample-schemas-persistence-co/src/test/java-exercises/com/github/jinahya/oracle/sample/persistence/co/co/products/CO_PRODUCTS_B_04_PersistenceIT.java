package com.github.jinahya.oracle.sample.persistence.co.co.products;

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

import com.github.jinahya.oracle.sample.persistence.co.Product;
import com.github.jinahya.oracle.sample.persistence.co.Product_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import jakarta.persistence.criteria.Nulls;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_PRODUCTS_B_04} of {@code src/test/sql-exercises/PRODUCTS/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_PRODUCTS_B_04: Products missing a fresh picture")
@Slf4j
class CO_PRODUCTS_B_04_PersistenceIT {

    private static final LocalDateTime SINCE = LocalDateTime.parse("2024-01-01T00:00:00");

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT p
                                        FROM Product p
                                        WHERE p.productImage IS NULL
                                           OR p.imageLastUpdated < :since
                                        ORDER BY CASE WHEN p.productImage IS NULL THEN 0 ELSE 1 END,
                                                 p.imageLastUpdated NULLS FIRST, p.productName, p.productId""",
                                Product.class
                        )
                        .setParameter("since", SINCE)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Product.class);
            final var p = cq.from(Product.class);
            final var productImage = p.get(Product_.productImage);
            final var imageLastUpdated = p.get(Product_.imageLastUpdated);
            cq.select(p)
                    .where(cb.or(cb.isNull(productImage),
                                 cb.lessThan(imageLastUpdated, SINCE)))
                    .orderBy(cb.asc(cb.<Integer>selectCase()
                                            .when(cb.isNull(productImage), 0)
                                            .otherwise(1)),
                             cb.asc(imageLastUpdated, Nulls.FIRST),
                             cb.asc(p.get(Product_.productName)),
                             cb.asc(p.get(Product_.productId)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    // no product of the installed data has a picture, so every one of them is listed, by name
    private static void verify(final List<Product> list) {
        list.stream().limit(10).forEach(p -> log.debug("{}", p));
        assertThat(list).hasSize(46);
        assertThat(list)
                .extracting(Product::getProductId, Product::getProductName,
                            Product::getImageFilename, Product::getImageLastUpdated)
                .startsWith(
                        tuple(27L, "Boy's Coat (Blue)", null, null),
                        tuple(8L, "Boy's Coat (Brown)", null, null),
                        tuple(13L, "Boy's Hoodie (Grey)", null, null),
                        tuple(42L, "Boy's Jeans (Black)", null, null),
                        tuple(37L, "Boy's Jeans (Blue)", null, null),
                        tuple(33L, "Boy's Pyjamas (Grey)", null, null));
    }
}
