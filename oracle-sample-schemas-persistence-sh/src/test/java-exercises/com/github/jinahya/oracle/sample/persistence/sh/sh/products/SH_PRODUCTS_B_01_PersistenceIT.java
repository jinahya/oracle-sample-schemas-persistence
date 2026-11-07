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
 * Answers {@code SH_PRODUCTS_B_01} of {@code src/test/sql-exercises/PRODUCTS/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_PRODUCTS_B_01: Products in a price band")
@Slf4j
class SH_PRODUCTS_B_01_PersistenceIT {

    private static final String CATEGORY = "Tennis";

    private static final BigDecimal MIN_PRICE = BigDecimal.valueOf(20L);

    private static final BigDecimal MAX_PRICE = BigDecimal.valueOf(100L);

    private static void verify(final List<Product> list) {
        list.forEach(p -> log.debug("{}", p));
        assertThat(list)
                .extracting(Product::getProdId, Product::getProdName, Product::getProdSubcategory,
                            p -> p.getProdListPrice().doubleValue(),
                            p -> p.getProdMinPrice().doubleValue())
                .containsExactly(
                        tuple(140, "Tennis Strings Natural Gut", "Tennis Strings", 29.99, 29.99),
                        tuple(141, "Tennis Strings Synthetic Gut", "Tennis Strings", 29.99, 29.99),
                        tuple(148, "Tennis Balls 12 Pack", "Tennis Balls", 20.99, 20.99));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT p
                                        FROM Product p
                                        WHERE p.prodCategory = :category
                                          AND p.prodListPrice BETWEEN :minPrice AND :maxPrice
                                        ORDER BY p.prodListPrice DESC, p.prodName""",
                                Product.class
                        )
                        .setParameter("category", CATEGORY)
                        .setParameter("minPrice", MIN_PRICE)
                        .setParameter("maxPrice", MAX_PRICE)
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
            cq.select(p)
                    .where(cb.equal(p.get(Product_.prodCategory),
                                    cb.parameter(String.class, "category")),
                           cb.between(p.get(Product_.prodListPrice),
                                      cb.parameter(BigDecimal.class, "minPrice"),
                                      cb.parameter(BigDecimal.class, "maxPrice")))
                    .orderBy(cb.desc(p.get(Product_.prodListPrice)),
                             cb.asc(p.get(Product_.prodName)));
            return em.createQuery(cq)
                    .setParameter("category", CATEGORY)
                    .setParameter("minPrice", MIN_PRICE)
                    .setParameter("maxPrice", MAX_PRICE)
                    .getResultList();
        });
        verify(list);
    }
}
