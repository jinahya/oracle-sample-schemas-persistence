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
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_PRODUCTS_I_01} of {@code src/test/sql-exercises/PRODUCTS/02_intermediate.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_PRODUCTS_I_01: Products priced above average")
@Slf4j
class CO_PRODUCTS_I_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT p
                                        FROM Product p
                                        WHERE p.unitPrice > (SELECT AVG(p2.unitPrice) FROM Product p2)
                                        ORDER BY p.unitPrice DESC""",
                                Product.class
                        )
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
            final var sq = cq.subquery(Double.class);
            final var p2 = sq.from(Product.class);
            sq.select(cb.avg(p2.get(Product_.unitPrice)));
            cq.select(p)
                    .where(cb.gt(p.get(Product_.unitPrice), sq))
                    .orderBy(cb.desc(p.get(Product_.unitPrice)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Product> list) {
        list.forEach(p -> log.debug("{}", p));
        assertThat(list)
                .extracting(Product::getProductId, Product::getProductName,
                            p -> p.getUnitPrice().doubleValue())
                .containsExactly(
                        tuple(35L, "Girl's Dress (Red)", 49.12),
                        tuple(25L, "Girl's Jeans (Grey)", 48.75),
                        tuple(23L, "Men's Pyjamas (Blue)", 48.39),
                        tuple(3L, "Boy's Sweater (Green)", 44.17),
                        tuple(4L, "Boy's Trousers (White)", 43.71),
                        tuple(26L, "Girl's Hoodie (White)", 39.91),
                        tuple(16L, "Women's Socks (Grey)", 39.89),
                        tuple(21L, "Girl's Pyjamas (White)", 39.78),
                        tuple(43L, "Boy's Trousers (Black)", 39.32),
                        tuple(46L, "Girl's Trousers (Red)", 39.16),
                        tuple(20L, "Girl's Shorts (Green)", 38.34),
                        tuple(5L, "Girl's Shorts (Red)", 38.28),
                        tuple(29L, "Boy's Shirt (Black)", 37.34),
                        tuple(32L, "Women's Jacket (Blue)", 37.0),
                        tuple(39L, "Boy's Trousers (Blue)", 34.06),
                        tuple(44L, "Women's Coat (Black)", 31.68),
                        tuple(10L, "Women's Skirt (Red)", 30.69),
                        tuple(1L, "Boy's Shirt (White)", 29.55),
                        tuple(36L, "Women's Trousers (Blue)", 29.51),
                        tuple(9L, "Women's Jeans (Brown)", 29.49),
                        tuple(30L, "Women's Pyjamas (Grey)", 28.59),
                        tuple(19L, "Men's Coat (Red)", 28.21),
                        tuple(45L, "Men's Jeans (Grey)", 27.64));
    }
}
