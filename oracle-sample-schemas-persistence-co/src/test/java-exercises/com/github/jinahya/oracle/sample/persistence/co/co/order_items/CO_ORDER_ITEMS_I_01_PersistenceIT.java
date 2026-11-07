package com.github.jinahya.oracle.sample.persistence.co.co.order_items;

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

import com.github.jinahya.oracle.sample.persistence.co.Order;
import com.github.jinahya.oracle.sample.persistence.co.OrderItem;
import com.github.jinahya.oracle.sample.persistence.co.OrderItem_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_ORDER_ITEMS_I_01} of
 * {@code src/test/sql-exercises/ORDER_ITEMS/02_intermediate.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_ORDER_ITEMS_I_01: Orders worth more than an amount")
@Slf4j
class CO_ORDER_ITEMS_I_01_PersistenceIT {

    private static final BigDecimal AMOUNT = new BigDecimal("400");

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT o, COUNT(oi), SUM(oi.quantity), SUM(oi.unitPrice * oi.quantity)
                                        FROM OrderItem oi
                                                 JOIN oi.order o
                                        GROUP BY o
                                        HAVING SUM(oi.unitPrice * oi.quantity) > :amount
                                        ORDER BY SUM(oi.unitPrice * oi.quantity) DESC""",
                                Object[].class
                        )
                        .setParameter("amount", AMOUNT)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var oi = cq.from(OrderItem.class);
            final var o = oi.join(OrderItem_.order);
            final var total = cb.sum(cb.<Number>prod(oi.get(OrderItem_.unitPrice),
                                                     oi.get(OrderItem_.quantity)));
            cq.select(cb.array(o, cb.count(oi), cb.sum(oi.get(OrderItem_.quantity)), total))
                    .groupBy(o)
                    .having(cb.gt(total, AMOUNT))
                    .orderBy(cb.desc(total));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Order) r[0]).getOrderId(),
                            r -> ((Number) r[1]).longValue(),
                            r -> ((Number) r[2]).longValue(),
                            r -> ((Number) r[3]).doubleValue())
                .containsExactly(
                        tuple(918L, 3L, 12L, 529.4),
                        tuple(1509L, 3L, 12L, 517.69),
                        tuple(927L, 3L, 11L, 483.65),
                        tuple(225L, 3L, 13L, 474.97),
                        tuple(847L, 3L, 12L, 470.73),
                        tuple(1052L, 3L, 11L, 464.76),
                        tuple(167L, 3L, 13L, 459.35),
                        tuple(806L, 3L, 11L, 454.6),
                        tuple(1165L, 2L, 10L, 437.0),
                        tuple(624L, 3L, 10L, 435.78),
                        tuple(234L, 3L, 13L, 430.32),
                        tuple(658L, 3L, 11L, 422.74),
                        tuple(701L, 3L, 11L, 415.38),
                        tuple(977L, 3L, 12L, 415.0),
                        tuple(1207L, 3L, 12L, 408.72),
                        tuple(1156L, 3L, 12L, 402.43),
                        tuple(492L, 3L, 11L, 401.49),
                        tuple(1010L, 2L, 9L, 401.07),
                        tuple(1300L, 3L, 14L, 400.03));
    }
}
