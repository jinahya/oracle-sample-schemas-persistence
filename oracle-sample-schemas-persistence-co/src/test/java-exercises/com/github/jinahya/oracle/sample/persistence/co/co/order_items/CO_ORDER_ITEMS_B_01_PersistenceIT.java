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

import com.github.jinahya.oracle.sample.persistence.co.OrderItem;
import com.github.jinahya.oracle.sample.persistence.co.OrderItem_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import com.github.jinahya.oracle.sample.persistence.co.mapped.OrderItemId_;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_ORDER_ITEMS_B_01} of {@code src/test/sql-exercises/ORDER_ITEMS/01_basic.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_ORDER_ITEMS_B_01: Bulk lines")
@Slf4j
class CO_ORDER_ITEMS_B_01_PersistenceIT {

    private static final long MIN_QUANTITY = 5L;

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT oi
                                        FROM OrderItem oi
                                        WHERE oi.quantity >= :minQuantity
                                        ORDER BY oi.quantity DESC, oi.id_.orderId, oi.id_.lineItemId""",
                                OrderItem.class
                        )
                        .setParameter("minQuantity", MIN_QUANTITY)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(OrderItem.class);
            final var oi = cq.from(OrderItem.class);
            final var id = oi.get(OrderItem_.id_);
            cq.select(oi)
                    .where(cb.ge(oi.get(OrderItem_.quantity), MIN_QUANTITY))
                    .orderBy(cb.desc(oi.get(OrderItem_.quantity)),
                             cb.asc(id.get(OrderItemId_.orderId)),
                             cb.asc(id.get(OrderItemId_.lineItemId)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<OrderItem> list) {
        list.stream().limit(10).forEach(oi -> log.debug("{}", oi));
        assertThat(list).hasSize(510);
        assertThat(list)
                .extracting(oi -> oi.getId_().getOrderId(),
                            oi -> oi.getId_().getLineItemId(),
                            OrderItem::getProductId,
                            oi -> oi.getUnitPrice().doubleValue(),
                            OrderItem::getQuantity)
                .startsWith(
                        tuple(2L, 2L, 32L, 5.65, 5L),
                        tuple(3L, 1L, 41L, 8.66, 5L),
                        tuple(9L, 1L, 15L, 13.97, 5L),
                        tuple(10L, 2L, 34L, 23.32, 5L),
                        tuple(16L, 1L, 41L, 8.66, 5L),
                        tuple(22L, 2L, 30L, 37.34, 5L));
    }
}
