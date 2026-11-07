package com.github.jinahya.oracle.sample.persistence.co.co.customer_order_products;

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

import com.github.jinahya.oracle.sample.persistence.co.CustomerOrderProduct;
import com.github.jinahya.oracle.sample.persistence.co.CustomerOrderProduct_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_CUSTOMER_ORDER_PRODUCTS_B_02} of
 * {@code src/test/sql-exercises/CUSTOMER_ORDER_PRODUCTS/01_basic.sql} with JPQL and with the
 * Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_CUSTOMER_ORDER_PRODUCTS_B_02: Review a customer's completed baskets")
@Slf4j
class CO_CUSTOMER_ORDER_PRODUCTS_B_02_PersistenceIT {

    private static final long CUSTOMER_ID = 1L;

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT v
                                        FROM CustomerOrderProduct v
                                        WHERE v.customerId = :customerId
                                          AND v.orderStatus = 'COMPLETE'
                                        ORDER BY v.orderTms DESC, v.orderId DESC""",
                                CustomerOrderProduct.class
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
            final var cq = cb.createQuery(CustomerOrderProduct.class);
            final var v = cq.from(CustomerOrderProduct.class);
            cq.select(v)
                    .where(cb.equal(v.get(CustomerOrderProduct_.customerId), CUSTOMER_ID),
                           // the view's orderStatus is a String, not an enum
                           cb.equal(v.get(CustomerOrderProduct_.orderStatus), "COMPLETE"))
                    .orderBy(cb.desc(v.get(CustomerOrderProduct_.orderTms)),
                             cb.desc(v.get(CustomerOrderProduct_.orderId)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<CustomerOrderProduct> list) {
        list.forEach(v -> log.debug("{}", v));
        assertThat(list)
                .extracting(CustomerOrderProduct::getOrderId,
                            CustomerOrderProduct::getOrderTms,
                            v -> v.getOrderTotal().doubleValue(),
                            CustomerOrderProduct::getItems)
                .containsExactly(
                        tuple(1491L, LocalDateTime.parse("2022-01-16T08:12:31.926232"), 20.96,
                              "Boy's Socks (White)"),
                        tuple(1390L, LocalDateTime.parse("2021-12-31T15:47:26.170374"), 83.76,
                              "Boy's Socks (White), Men's Hoodie (Red), Women's Jeans (Brown)"),
                        tuple(201L, LocalDateTime.parse("2021-04-29T11:48:01.437203"), 155.6,
                              "Men's Coat (Red), Women's Trousers (Blue)"),
                        tuple(182L, LocalDateTime.parse("2021-04-26T03:56:02.680569"), 97.74,
                              "Men's Pyjamas (Blue), Girl's Shorts (Green)"),
                        tuple(159L, LocalDateTime.parse("2021-04-20T12:51:57.254596"), 39.16,
                              "Boy's Coat (Brown)"));
    }
}
