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

import com.github.jinahya.oracle.sample.persistence.co.OrderItem;
import com.github.jinahya.oracle.sample.persistence.co.OrderItem_;
import com.github.jinahya.oracle.sample.persistence.co.Order_;
import com.github.jinahya.oracle.sample.persistence.co.Store_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_SCHEMA_I_02} of {@code src/test/sql-exercises/__/02_intermediate.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_SCHEMA_I_02: Stores that pass a revenue target")
@Slf4j
class CO_SCHEMA_I_02_PersistenceIT {

    // Store maps LOGO, a BLOB, and Oracle cannot group by a BLOB (ORA-22848), so the query groups
    // by the store's key and name, as the SQL does, rather than by the Store.

    private static final MappedOrder.OrderStatus STATUS = MappedOrder.OrderStatus.COMPLETE;

    private static final BigDecimal TARGET = new BigDecimal("5000");

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT s.storeId, s.storeName, SUM(oi.unitPrice * oi.quantity) AS revenue
                                        FROM OrderItem oi
                                                 JOIN oi.order o
                                                 JOIN o.store s
                                        WHERE o.orderStatus = :status
                                        GROUP BY s.storeId, s.storeName
                                        HAVING SUM(oi.unitPrice * oi.quantity) > :target
                                        ORDER BY revenue DESC""",
                                Object[].class
                        )
                        .setParameter("status", STATUS)
                        .setParameter("target", TARGET)
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
            final var s = o.join(Order_.store);
            final var storeId = s.get(Store_.storeId);
            final var storeName = s.get(Store_.storeName);
            final var revenue = cb.sum(cb.<Number>prod(oi.get(OrderItem_.unitPrice),
                                                       oi.get(OrderItem_.quantity)));
            cq.select(cb.array(storeId, storeName, revenue))
                    .where(cb.equal(o.get(Order_.orderStatus), STATUS))
                    .groupBy(storeId, storeName)
                    .having(cb.gt(revenue, TARGET))
                    .orderBy(cb.desc(revenue));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Number) r[0]).longValue(), r -> r[1],
                            r -> ((Number) r[2]).doubleValue())
                .containsExactly(
                        tuple(1L, "Online", 211107.78),
                        tuple(15L, "New Dehli", 5291.76));
    }
}
