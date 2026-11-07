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

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_PRODUCT_ORDERS_I_01} of
 * {@code src/test/sql-exercises/PRODUCT_ORDERS/02_intermediate.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_PRODUCT_ORDERS_I_01: Sales per product, whatever happened to the order")
@Slf4j
class CO_PRODUCT_ORDERS_I_01_PersistenceIT {

    // The view maps a product by its name alone, a part of the view's key, with no association to
    // Product, so the query groups by the name and selects it.

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT v.id_.productName, SUM(v.orderCount), SUM(v.totalSales)
                                        FROM ProductOrder v
                                        GROUP BY v.id_.productName
                                        ORDER BY SUM(v.totalSales) DESC""",
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
            final var v = cq.from(ProductOrder.class);
            final var productName = v.get(ProductOrder_.id_).get(ProductOrderId_.productName);
            final var sales = cb.sum(v.get(ProductOrder_.totalSales));
            cq.select(cb.array(productName, cb.sum(v.get(ProductOrder_.orderCount)), sales))
                    .groupBy(productName)
                    .orderBy(cb.desc(sales));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Object[]> list) {
        list.stream().limit(10).forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list).hasSize(45);
        assertThat(list)
                .extracting(r -> r[0], r -> ((Number) r[1]).longValue(),
                            r -> ((Number) r[2]).doubleValue())
                .startsWith(
                        tuple("Girl's Trousers (Red)", 148L, 15794.76),
                        tuple("Girl's Hoodie (White)", 94L, 14283.75),
                        tuple("Women's Trousers (Blue)", 82L, 12181.76),
                        tuple("Boy's Coat (Blue)", 94L, 11214.71),
                        tuple("Girl's Pyjamas (White)", 89L, 11118.6),
                        tuple("Women's Coat (Black)", 89L, 11088.24));
    }
}
