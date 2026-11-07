package com.github.jinahya.oracle.sample.persistence.sh.sh.products;

/*-
 * #%L
 * sh
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

import com.github.jinahya.oracle.sample.persistence.sh.Product;
import com.github.jinahya.oracle.sample.persistence.sh.Product_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_PRODUCTS_I_01} of {@code src/test/sql-exercises/PRODUCTS/02_intermediate.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_PRODUCTS_I_01: Products priced above the average")
@Slf4j
class SH_PRODUCTS_I_01_PersistenceIT {

    // the exercise orders by the list price alone, so products of the same price come in any order
    private static void verify(final List<Product> list) {
        list.forEach(p -> log.debug("{}", p));
        assertThat(list)
                .extracting(p -> p.getProdListPrice().doubleValue())
                .isSortedAccordingTo(Comparator.reverseOrder());
        assertThat(list)
                .extracting(Product::getProdId, Product::getProdName, Product::getProdCategory,
                            p -> p.getProdListPrice().doubleValue())
                .containsExactlyInAnyOrder(
                        tuple(18, "Lithium Electric Golf Caddy", "Golf", 1299.99),
                        tuple(17, "Soccer Goal - Official", "Soccer / Football", 1099.99),
                        tuple(15, "Right-Handed Graphite Shaft Iron Set", "Golf", 999.99),
                        tuple(14, "Pitching Machine and Batting Cage Combo", "Baseball", 999.99),
                        tuple(21, "Speed Trainer Bats and Training Program", "Baseball", 899.99),
                        tuple(13, "Soccer Goal - Portable", "Soccer / Football", 899.99),
                        tuple(20, "Match Used Autograph Racquet", "Tennis", 599.99),
                        tuple(29, "Limited Edition Racquet", "Tennis", 499.99),
                        tuple(16, "Sonic Core Graphite Racquet", "Tennis", 299.99),
                        tuple(28, "English Willow Cricket Bat", "Cricket", 199.99),
                        tuple(129, "Pro Maple Bat", "Baseball", 192.99),
                        tuple(26, "Pro Style Batting Tee", "Baseball", 149.99));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT p
                                        FROM Product p
                                        WHERE p.prodListPrice > (SELECT AVG(p2.prodListPrice)
                                                                 FROM Product p2)
                                        ORDER BY p.prodListPrice DESC""",
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
            sq.select(cb.avg(p2.get(Product_.prodListPrice)));
            cq.select(p)
                    .where(cb.gt(p.get(Product_.prodListPrice), sq))
                    .orderBy(cb.desc(p.get(Product_.prodListPrice)));
            return em.createQuery(cq)
                    .getResultList();
        });
        verify(list);
    }
}
