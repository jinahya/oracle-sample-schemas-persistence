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

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_PRODUCTS_A_01} of {@code src/test/sql-exercises/PRODUCTS/03_advanced.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_PRODUCTS_A_01: Most expensive product of each subcategory")
@Slf4j
class SH_PRODUCTS_A_01_PersistenceIT {

    private static void verify(final List<Product> list) {
        list.forEach(p -> log.debug("{}", p));
        assertThat(list)
                .extracting(Product::getProdCategory, Product::getProdSubcategory,
                            Product::getProdId, Product::getProdName,
                            p -> p.getProdListPrice().doubleValue())
                .containsExactly(
                        tuple("Baseball", "Baseball Clothing", 24,
                              "Endurance Coolcore 1/2 Zip Pullover", 45.99),
                        tuple("Baseball", "Baseballs", 32, "Bucket of 24 Leather Baseballs", 67.99),
                        tuple("Baseball", "Bats", 129, "Pro Maple Bat", 192.99),
                        tuple("Baseball", "Batting Tees", 26, "Pro Style Batting Tee", 149.99),
                        tuple("Baseball", "Gloves and Mitts", 37, "12.75\" Premium Series Glove",
                              54.99),
                        tuple("Baseball", "Training Aids and Equipment", 14,
                              "Pitching Machine and Batting Cage Combo", 999.99),
                        tuple("Cricket", "Cricket", 113, "Cricket Ball", 22.99),
                        tuple("Cricket", "Cricket Bat", 28, "English Willow Cricket Bat", 199.99),
                        tuple("Cricket", "Cricket Clothing", 123, "Helmet", 49.99),
                        tuple("Cricket", "Cricket Clothing", 126, "Spiked Shoes", 28.99),
                        tuple("Cricket", "Cricket Fan Gear", 40, "Team shirt", 44.99),
                        tuple("Cricket", "Cricket Fan Gear", 41, "Team shirt", 44.99),
                        tuple("Cricket", "Cricket Fan Gear", 42, "Team shirt", 44.99),
                        tuple("Cricket", "Cricket Fan Gear", 43, "Team shirt", 44.99),
                        tuple("Cricket", "Cricket Fan Gear", 44, "Team shirt", 44.99),
                        tuple("Cricket", "Cricket Fan Gear", 45, "Team shirt", 44.99),
                        tuple("Golf", "Golf Bags and Carts", 18, "Lithium Electric Golf Caddy",
                              1299.99),
                        tuple("Golf", "Irons and Iron Sets", 15,
                              "Right-Handed Graphite Shaft Iron Set", 999.99),
                        tuple("Soccer / Football", "Soccer / Football", 13,
                              "Soccer Goal - Portable", 899.99),
                        tuple("Soccer / Football", "Soccer / Football", 17,
                              "Soccer Goal - Official", 1099.99),
                        tuple("Soccer / Football", "Soccer Clothing", 138, "Soccer Cleats - Adult",
                              69.99),
                        tuple("Soccer / Football", "Soccer Equipment", 135, "Gear Bag", 49.99),
                        tuple("Tennis", "Tennis Balls", 148, "Tennis Balls 12 Pack", 20.99),
                        tuple("Tennis", "Tennis Racquet", 16, "Sonic Core Graphite Racquet",
                              299.99),
                        tuple("Tennis", "Tennis Racquet", 20, "Match Used Autograph Racquet",
                              599.99),
                        tuple("Tennis", "Tennis Strings", 140, "Tennis Strings Natural Gut", 29.99),
                        tuple("Tennis", "Tennis Strings", 141, "Tennis Strings Synthetic Gut",
                              29.99));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT p
                                        FROM Product p
                                        WHERE p.prodListPrice = (SELECT MAX(p2.prodListPrice)
                                                                 FROM Product p2
                                                                 WHERE p2.prodSubcategoryId
                                                                           = p.prodSubcategoryId)
                                        ORDER BY p.prodCategory, p.prodSubcategory, p.prodId""",
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
            final var sq = cq.subquery(BigDecimal.class);
            final var p2 = sq.from(Product.class);
            sq.select(cb.max(p2.get(Product_.prodListPrice)))
                    .where(cb.equal(p2.get(Product_.prodSubcategoryId),
                                    p.get(Product_.prodSubcategoryId)));
            cq.select(p)
                    .where(cb.equal(p.get(Product_.prodListPrice), sq))
                    .orderBy(cb.asc(p.get(Product_.prodCategory)),
                             cb.asc(p.get(Product_.prodSubcategory)),
                             cb.asc(p.get(Product_.prodId)));
            return em.createQuery(cq)
                    .getResultList();
        });
        verify(list);
    }
}
