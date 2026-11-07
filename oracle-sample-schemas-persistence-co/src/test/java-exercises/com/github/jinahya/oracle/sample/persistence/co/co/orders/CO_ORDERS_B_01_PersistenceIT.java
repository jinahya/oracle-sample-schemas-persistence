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

import com.github.jinahya.oracle.sample.persistence.co.Customer_;
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
 * Answers {@code CO_ORDERS_B_01} of {@code src/test/sql-exercises/ORDERS/01_basic.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_ORDERS_B_01: A customer's order history")
@Slf4j
class CO_ORDERS_B_01_PersistenceIT {

    private static final long CUSTOMER_ID = 1L;

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT o
                                        FROM Order o
                                        WHERE o.customer.customerId = :customerId
                                        ORDER BY o.orderTms DESC, o.orderId DESC""",
                                Order.class
                        )
                        .setParameter("customerId", CUSTOMER_ID)
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
                    .where(cb.equal(o.get(Order_.customer).get(Customer_.customerId), CUSTOMER_ID))
                    .orderBy(cb.desc(o.get(Order_.orderTms)), cb.desc(o.get(Order_.orderId)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Order> list) {
        list.forEach(o -> log.debug("{}", o));
        assertThat(list)
                .extracting(Order::getOrderId, Order::getOrderTms, Order::getOrderStatus,
                            Order::getStoreId)
                .containsExactly(
                        tuple(1491L, LocalDateTime.parse("2022-01-16T08:12:31.926232"),
                              MappedOrder.OrderStatus.COMPLETE, 1L),
                        tuple(1390L, LocalDateTime.parse("2021-12-31T15:47:26.170374"),
                              MappedOrder.OrderStatus.COMPLETE, 1L),
                        tuple(201L, LocalDateTime.parse("2021-04-29T11:48:01.437203"),
                              MappedOrder.OrderStatus.COMPLETE, 1L),
                        tuple(182L, LocalDateTime.parse("2021-04-26T03:56:02.680569"),
                              MappedOrder.OrderStatus.COMPLETE, 1L),
                        tuple(159L, LocalDateTime.parse("2021-04-20T12:51:57.254596"),
                              MappedOrder.OrderStatus.COMPLETE, 1L));
    }
}
