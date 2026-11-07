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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_PRODUCT_ORDERS_B_01} of
 * {@code src/test/sql-exercises/PRODUCT_ORDERS/01_basic.sql} with JPQL and with the Criteria API,
 * or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_PRODUCT_ORDERS_B_01: Which products get refunded?")
@Slf4j
class CO_PRODUCT_ORDERS_B_01_PersistenceIT {

    // the view's orderStatus is a String, not an enum
    private static final String ORDER_STATUS = "REFUNDED";

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT v
                                        FROM ProductOrder v
                                        WHERE v.id_.orderStatus = :orderStatus
                                        ORDER BY v.totalSales DESC""",
                                ProductOrder.class
                        )
                        .setParameter("orderStatus", ORDER_STATUS)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(ProductOrder.class);
            final var v = cq.from(ProductOrder.class);
            cq.select(v)
                    .where(cb.equal(v.get(ProductOrder_.id_).get(ProductOrderId_.orderStatus),
                                    ORDER_STATUS))
                    .orderBy(cb.desc(v.get(ProductOrder_.totalSales)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<ProductOrder> list) {
        list.forEach(v -> log.debug("{}", v));
        assertThat(list).hasSize(26);
        // the 11th and the 12th rows tie on their value, so the order is checked up to the 10th
        assertThat(list)
                .extracting(ProductOrder::getProductName, v -> v.getTotalSales().doubleValue(),
                            ProductOrder::getOrderCount)
                .startsWith(
                        tuple("Boy's Coat (Blue)", 319.28, 3L),
                        tuple("Boy's Coat (Brown)", 234.96, 5L),
                        tuple("Men's Jeans (Grey)", 221.76, 2L),
                        tuple("Women's Sweater (Brown)", 199.45, 2L),
                        tuple("Boy's Socks (Grey)", 191.4, 1L),
                        tuple("Men's Shorts (Black)", 159.12, 1L),
                        tuple("Girl's Pyjamas (White)", 153.36, 1L),
                        tuple("Boy's Socks (Black)", 153.28, 2L),
                        tuple("Boy's Sweater (Red)", 145.17, 1L),
                        tuple("Girl's Pyjamas (Black)", 136.24, 1L));
    }
}
