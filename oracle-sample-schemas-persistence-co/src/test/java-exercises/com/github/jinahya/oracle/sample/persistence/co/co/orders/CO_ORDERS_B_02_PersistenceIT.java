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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_ORDERS_B_02} of {@code src/test/sql-exercises/ORDERS/01_basic.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_ORDERS_B_02: Orders that went wrong")
@Slf4j
class CO_ORDERS_B_02_PersistenceIT {

    // an enum attribute is compared with enum values, never with strings
    private static final List<MappedOrder.OrderStatus> STATUSES = List.of(
            MappedOrder.OrderStatus.CANCELLED,
            MappedOrder.OrderStatus.REFUNDED
    );

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT o
                                        FROM Order o
                                        WHERE o.orderStatus IN :statuses
                                        ORDER BY o.orderTms DESC""",
                                Order.class
                        )
                        .setParameter("statuses", STATUSES)
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
            cq.select(o)
                    .where(o.get(Order_.orderStatus).in(STATUSES))
                    .orderBy(cb.desc(o.get(Order_.orderTms)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Order> list) {
        list.stream().limit(10).forEach(o -> log.debug("{}", o));
        assertThat(list).hasSize(58);
        assertThat(list)
                .extracting(Order::getOrderId, Order::getOrderTms, Order::getOrderStatus,
                            Order::getCustomerId, Order::getStoreId)
                .startsWith(
                        tuple(1938L, LocalDateTime.parse("2022-04-07T18:31:50.146426"),
                              MappedOrder.OrderStatus.CANCELLED, 370L, 22L),
                        tuple(1935L, LocalDateTime.parse("2022-04-06T04:30:16.153051"),
                              MappedOrder.OrderStatus.CANCELLED, 370L, 22L),
                        tuple(1934L, LocalDateTime.parse("2022-04-06T00:19:35.005649"),
                              MappedOrder.OrderStatus.CANCELLED, 370L, 22L),
                        tuple(1929L, LocalDateTime.parse("2022-04-04T20:14:44.084942"),
                              MappedOrder.OrderStatus.REFUNDED, 165L, 20L),
                        tuple(1858L, LocalDateTime.parse("2022-03-19T00:17:30.802910"),
                              MappedOrder.OrderStatus.REFUNDED, 165L, 20L),
                        tuple(1839L, LocalDateTime.parse("2022-03-15T09:28:08.961711"),
                              MappedOrder.OrderStatus.CANCELLED, 353L, 1L));
    }
}
