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
import com.github.jinahya.oracle.sample.persistence.co.Product;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import com.github.jinahya.oracle.sample.persistence.co.mapped.OrderItemId_;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_SCHEMA_B_02} of {@code src/test/sql-exercises/__/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_SCHEMA_B_02: Print a receipt")
@Slf4j
class CO_SCHEMA_B_02_PersistenceIT {

    private static final long ORDER_ID = 4L;

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT oi, p, oi.unitPrice * oi.quantity
                                        FROM OrderItem oi
                                                 JOIN oi.product p
                                        WHERE oi.id_.orderId = :orderId
                                        ORDER BY oi.id_.lineItemId""",
                                Object[].class
                        )
                        .setParameter("orderId", ORDER_ID)
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
            final var p = oi.join(OrderItem_.product);
            final var id = oi.get(OrderItem_.id_);
            cq.select(cb.array(oi, p, cb.prod(oi.get(OrderItem_.unitPrice),
                                              oi.get(OrderItem_.quantity))))
                    .where(cb.equal(id.get(OrderItemId_.orderId), ORDER_ID))
                    .orderBy(cb.asc(id.get(OrderItemId_.lineItemId)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((OrderItem) r[0]).getId_().getLineItemId(),
                            r -> ((Product) r[1]).getProductName(),
                            r -> ((OrderItem) r[0]).getUnitPrice().doubleValue(),
                            r -> ((OrderItem) r[0]).getQuantity(),
                            r -> ((Number) r[2]).doubleValue())
                .containsExactly(
                        tuple(1L, "Girl's Shorts (Green)", 28.21, 2L, 56.42),
                        tuple(2L, "Girl's Pyjamas (Red)", 22.98, 4L, 91.92),
                        tuple(3L, "Girl's Trousers (Red)", 39.16, 4L, 156.64));
    }
}
