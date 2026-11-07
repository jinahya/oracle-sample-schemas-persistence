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
import com.github.jinahya.oracle.sample.persistence.co.Store_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_ORDERS_B_03} of {@code src/test/sql-exercises/ORDERS/01_basic.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_ORDERS_B_03: A store's orders for a period")
@Slf4j
class CO_ORDERS_B_03_PersistenceIT {

    private static final long STORE_ID = 2L;

    // a half-open period: on or after FROM_TMS, and before TO_TMS
    private static final LocalDateTime FROM_TMS = LocalDateTime.parse("2022-01-01T00:00:00");

    private static final LocalDateTime TO_TMS = LocalDateTime.parse("2022-02-01T00:00:00");

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT o
                                        FROM Order o
                                        WHERE o.store.storeId = :storeId
                                          AND o.orderTms >= :fromTms
                                          AND o.orderTms < :toTms
                                        ORDER BY o.orderTms""",
                                Order.class
                        )
                        .setParameter("storeId", STORE_ID)
                        .setParameter("fromTms", FROM_TMS)
                        .setParameter("toTms", TO_TMS)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Order.class);
            final var o = cq.from(Order.class);
            final var orderTms = o.get(Order_.orderTms);
            cq.select(o)
                    .where(cb.equal(o.get(Order_.store).get(Store_.storeId), STORE_ID),
                           cb.greaterThanOrEqualTo(orderTms, FROM_TMS),
                           cb.lessThan(orderTms, TO_TMS))
                    .orderBy(cb.asc(orderTms));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Order> list) {
        list.forEach(o -> log.debug("{}", o));
        assertThat(list)
                .extracting(Order::getOrderId, Order::getOrderTms, Order::getOrderStatus,
                            Order::getCustomerId)
                .containsExactly(
                        tuple(1408L, LocalDateTime.parse("2022-01-03T09:06:12.113930"),
                              MappedOrder.OrderStatus.COMPLETE, 292L),
                        tuple(1464L, LocalDateTime.parse("2022-01-12T10:47:29.480815"),
                              MappedOrder.OrderStatus.COMPLETE, 118L),
                        tuple(1472L, LocalDateTime.parse("2022-01-13T06:54:37.487333"),
                              MappedOrder.OrderStatus.COMPLETE, 89L),
                        tuple(1481L, LocalDateTime.parse("2022-01-14T12:54:50.897138"),
                              MappedOrder.OrderStatus.REFUNDED, 321L),
                        tuple(1544L, LocalDateTime.parse("2022-01-23T12:28:21.356074"),
                              MappedOrder.OrderStatus.COMPLETE, 234L));
    }
}
