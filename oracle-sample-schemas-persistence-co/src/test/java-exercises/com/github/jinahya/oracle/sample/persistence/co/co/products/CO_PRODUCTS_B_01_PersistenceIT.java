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

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_PRODUCTS_B_01} of {@code src/test/sql-exercises/PRODUCTS/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_PRODUCTS_B_01: Gift ideas within a budget")
@Slf4j
class CO_PRODUCTS_B_01_PersistenceIT {

    private static final BigDecimal MIN_PRICE = new BigDecimal("10");

    private static final BigDecimal MAX_PRICE = new BigDecimal("15");

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT p
                                        FROM Product p
                                        WHERE p.unitPrice BETWEEN :minPrice AND :maxPrice
                                        ORDER BY p.unitPrice, p.productName""",
                                Product.class
                        )
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
            final var unitPrice = p.get(Product_.unitPrice);
            cq.select(p)
                    .where(cb.between(unitPrice, MIN_PRICE, MAX_PRICE))
                    .orderBy(cb.asc(unitPrice), cb.asc(p.get(Product_.productName)));
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
                        tuple(41L, "Women's Dress (Black)", 10.11),
                        tuple(27L, "Boy's Coat (Blue)", 10.24),
                        tuple(22L, "Men's Shorts (Black)", 10.33),
                        tuple(11L, "Boy's Shorts (Blue)", 10.48),
                        tuple(38L, "Girl's Pyjamas (Red)", 11.0),
                        tuple(12L, "Boy's Socks (White)", 12.64),
                        tuple(15L, "Girl's Coat (Blue)", 13.09),
                        tuple(14L, "Women's Skirt (Brown)", 13.97),
                        tuple(18L, "Women's Jacket (Black)", 14.34));
    }
}
