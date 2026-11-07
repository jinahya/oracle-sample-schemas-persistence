package com.github.jinahya.oracle.sample.persistence.co.co.orders;

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
import com.github.jinahya.oracle.sample.persistence.co.Order_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_ORDERS_I_01} of {@code src/test/sql-exercises/ORDERS/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_ORDERS_I_01: Where orders stand")
@Slf4j
class CO_ORDERS_I_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT o.orderStatus, COUNT(o), MIN(o.orderTms), MAX(o.orderTms)
                                        FROM Order o
                                        GROUP BY o.orderStatus
                                        ORDER BY COUNT(o) DESC""",
                                Object[].class
                        )
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var o = cq.from(Order.class);
            final var orderStatus = o.get(Order_.orderStatus);
            final var orderTms = o.get(Order_.orderTms);
            cq.select(cb.array(orderStatus, cb.count(o), cb.least(orderTms),
                               cb.greatest(orderTms)))
                    .groupBy(orderStatus)
                    .orderBy(cb.desc(cb.count(o)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> r[0], r -> ((Number) r[1]).longValue(),
                            r -> (LocalDateTime) r[2], r -> (LocalDateTime) r[3])
                .containsExactly(
                        tuple(MappedOrder.OrderStatus.COMPLETE, 1892L,
                              LocalDateTime.parse("2021-02-08T20:58:10.472721"),
                              LocalDateTime.parse("2022-04-12T21:41:06.642641")),
                        tuple(MappedOrder.OrderStatus.CANCELLED, 35L,
                              LocalDateTime.parse("2021-02-04T13:20:22.245677"),
                              LocalDateTime.parse("2022-04-07T18:31:50.146426")),
                        tuple(MappedOrder.OrderStatus.REFUNDED, 23L,
                              LocalDateTime.parse("2021-05-17T19:30:01.678181"),
                              LocalDateTime.parse("2022-04-04T20:14:44.084942")));
    }
}
