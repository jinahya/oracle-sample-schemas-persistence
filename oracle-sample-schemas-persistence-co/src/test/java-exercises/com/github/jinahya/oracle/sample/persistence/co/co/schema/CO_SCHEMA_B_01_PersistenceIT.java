package com.github.jinahya.oracle.sample.persistence.co.co.schema;

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

import com.github.jinahya.oracle.sample.persistence.co.Customer;
import com.github.jinahya.oracle.sample.persistence.co.Order;
import com.github.jinahya.oracle.sample.persistence.co.Order_;
import com.github.jinahya.oracle.sample.persistence.co.Store;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code CO_SCHEMA_B_01} of {@code src/test/sql-exercises/__/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_SCHEMA_B_01: Who placed an order, and where")
@Slf4j
class CO_SCHEMA_B_01_PersistenceIT {

    private static final long ORDER_ID = 1L;

    @Test
    void __QueryLanguage() {
        final var result = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT o, c, s
                                        FROM Order o
                                                 JOIN o.customer c
                                                 JOIN o.store s
                                        WHERE o.orderId = :orderId""",
                                Object[].class
                        )
                        .setParameter("orderId", ORDER_ID)
                        .getSingleResult()
        );
        verify(result);
    }

    @Test
    void __CriteriaApi() {
        final var result = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var o = cq.from(Order.class);
            final var c = o.join(Order_.customer);
            final var s = o.join(Order_.store);
            cq.select(cb.array(o, c, s))
                    .where(cb.equal(o.get(Order_.orderId), ORDER_ID));
            return em.createQuery(cq).getSingleResult();
        });
        verify(result);
    }

    private static void verify(final Object[] result) {
        log.debug("{}", Arrays.toString(result));
        final var order = (Order) result[0];
        final var customer = (Customer) result[1];
        final var store = (Store) result[2];
        assertThat(order.getOrderId()).isEqualTo(ORDER_ID);
        assertThat(order.getOrderTms()).isEqualTo(LocalDateTime.parse("2021-02-04T13:20:22.245677"));
        assertThat(order.getOrderStatus()).isEqualTo(MappedOrder.OrderStatus.CANCELLED);
        assertThat(customer.getFullName()).isEqualTo("Gary Jenkins");
        assertThat(customer.getEmailAddress()).isEqualTo("gary.jenkins@internalmail");
        assertThat(store.getStoreName()).isEqualTo("Online");
    }
}
