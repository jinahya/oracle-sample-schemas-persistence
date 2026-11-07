package com.github.jinahya.oracle.sample.persistence.co.co.product_orders;

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

import com.github.jinahya.oracle.sample.persistence.co.ProductOrder;
import com.github.jinahya.oracle.sample.persistence.co.ProductOrder_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import com.github.jinahya.oracle.sample.persistence.co.mapped.ProductOrderId_;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_PRODUCT_ORDERS_A_01} of
 * {@code src/test/sql-exercises/PRODUCT_ORDERS/03_advanced.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_PRODUCT_ORDERS_A_01: Products that are often cancelled or refunded")
@Slf4j
class CO_PRODUCT_ORDERS_A_01_PersistenceIT {

    // The view maps a product by its name alone, a part of the view's key, with no association to
    // Product, so the query groups by the name and selects it. The query orders by the percentage,
    // rounded as the SQL rounds it, and the test works it out from the two counts.

    // a whole number, which keeps the comparison in whole numbers
    private static final long PERCENT = 5L;

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT v.id_.productName,
                                               SUM(v.orderCount),
                                               SUM(CASE WHEN v.id_.orderStatus IN ('CANCELLED', 'REFUNDED')
                                                        THEN v.orderCount ELSE 0 END)
                                        FROM ProductOrder v
                                        GROUP BY v.id_.productName
                                        HAVING SUM(CASE WHEN v.id_.orderStatus IN ('CANCELLED', 'REFUNDED')
                                                        THEN v.orderCount ELSE 0 END)
                                                   * 100 > :percent * SUM(v.orderCount)
                                        ORDER BY ROUND(SUM(CASE WHEN v.id_.orderStatus IN ('CANCELLED', 'REFUNDED')
                                                                THEN v.orderCount ELSE 0 END)
                                                           * 100.0 / SUM(v.orderCount), 1) DESC,
                                                 v.id_.productName""",
                                Object[].class
                        )
                        .setParameter("percent", PERCENT)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var v = cq.from(ProductOrder.class);
            final var id = v.get(ProductOrder_.id_);
            final var productName = id.get(ProductOrderId_.productName);
            final var orderCount = v.get(ProductOrder_.orderCount);
            final var orders = cb.sum(orderCount);
            final var lostOrders = cb.sum(
                    cb.<Long>selectCase()
                            .when(id.get(ProductOrderId_.orderStatus).in("CANCELLED", "REFUNDED"),
                                  orderCount)
                            .otherwise(0L));
            cq.select(cb.array(productName, orders, lostOrders))
                    .groupBy(productName)
                    .having(cb.gt(cb.prod(lostOrders, 100L), cb.prod(PERCENT, orders)))
                    .orderBy(cb.desc(cb.round(cb.quot(cb.prod(lostOrders, 100.0d), orders), 1)),
                             cb.asc(productName));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static double lostPct(final Object[] r) {
        return BigDecimal.valueOf(((Number) r[2]).longValue() * 100L)
                .divide(BigDecimal.valueOf(((Number) r[1]).longValue()), 1, RoundingMode.HALF_UP)
                .doubleValue();
    }

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> r[0], r -> ((Number) r[1]).longValue(),
                            r -> ((Number) r[2]).longValue(), r -> lostPct(r))
                .containsExactly(
                        tuple("Boy's Coat (Brown)", 72L, 8L, 11.1),
                        tuple("Boy's Jeans (Black)", 83L, 5L, 6.0),
                        tuple("Girl's Jeans (Grey)", 89L, 5L, 5.6),
                        tuple("Girl's Pyjamas (Red)", 93L, 5L, 5.4),
                        tuple("Girl's Shorts (Green)", 77L, 4L, 5.2),
                        tuple("Women's Sweater (Brown)", 78L, 4L, 5.1));
    }
}
