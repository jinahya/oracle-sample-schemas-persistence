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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_CUSTOMER_ORDER_PRODUCTS_I_01} of
 * {@code src/test/sql-exercises/CUSTOMER_ORDER_PRODUCTS/02_intermediate.sql} with JPQL and with the
 * Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_CUSTOMER_ORDER_PRODUCTS_I_01: Average basket per customer")
@Slf4j
class CO_CUSTOMER_ORDER_PRODUCTS_I_01_PersistenceIT {

    // The view maps no association to Customer; the customer's key and name are columns of the
    // view itself, so the query groups by them and selects them.

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT v.customerId, v.fullName, COUNT(v), SUM(v.orderTotal),
                                               AVG(v.orderTotal)
                                        FROM CustomerOrderProduct v
                                        WHERE v.orderStatus = 'COMPLETE'
                                        GROUP BY v.customerId, v.fullName
                                        ORDER BY SUM(v.orderTotal) DESC, v.fullName""",
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
            final var v = cq.from(CustomerOrderProduct.class);
            final var customerId = v.get(CustomerOrderProduct_.customerId);
            final var fullName = v.get(CustomerOrderProduct_.fullName);
            final var orderTotal = v.get(CustomerOrderProduct_.orderTotal);
            cq.select(cb.array(customerId, fullName, cb.count(v), cb.sum(orderTotal),
                               cb.avg(orderTotal)))
                    .where(cb.equal(v.get(CustomerOrderProduct_.orderStatus), "COMPLETE"))
                    .groupBy(customerId, fullName)
                    .orderBy(cb.desc(cb.sum(orderTotal)), cb.asc(fullName));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Object[]> list) {
        list.stream().limit(10).forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list).hasSize(392);
        // the SQL rounds the average to two places; the query leaves it to the reader
        assertThat(list)
                .extracting(r -> ((Number) r[0]).longValue(),
                            r -> r[1],
                            r -> ((Number) r[2]).longValue(),
                            r -> ((Number) r[3]).doubleValue(),
                            r -> new BigDecimal(r[4].toString()).setScale(2, RoundingMode.HALF_UP)
                                    .doubleValue())
                .startsWith(
                        tuple(118L, "Cori Ablin", 8L, 1964.77, 245.6),
                        tuple(166L, "Kristina Livshits", 11L, 1873.83, 170.35),
                        tuple(14L, "Elizabeth Martinez", 9L, 1753.27, 194.81),
                        tuple(58L, "Shamira Jones", 11L, 1727.09, 157.01),
                        tuple(49L, "Kaija Crawford", 10L, 1654.98, 165.5),
                        tuple(3L, "Gary Jenkins", 9L, 1556.06, 172.9));
    }
}
