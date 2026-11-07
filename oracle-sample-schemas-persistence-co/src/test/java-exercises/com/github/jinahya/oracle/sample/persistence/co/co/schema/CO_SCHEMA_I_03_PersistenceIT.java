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
import com.github.jinahya.oracle.sample.persistence.co.Product_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_SCHEMA_I_03} of {@code src/test/sql-exercises/__/02_intermediate.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_SCHEMA_I_03: Best sellers")
@Slf4j
class CO_SCHEMA_I_03_PersistenceIT {

    // Product maps PRODUCT_DETAILS and PRODUCT_IMAGE, both BLOBs, and Oracle cannot group by a BLOB
    // (ORA-22848), so the query groups by the product's key and name, as the SQL does, rather than
    // by the Product.

    private static final MappedOrder.OrderStatus STATUS = MappedOrder.OrderStatus.COMPLETE;

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT p.productId, p.productName, SUM(oi.quantity) AS unitsSold
                                        FROM OrderItem oi
                                                 JOIN oi.product p
                                                 JOIN oi.order o
                                        WHERE o.orderStatus = :status
                                        GROUP BY p.productId, p.productName
                                        ORDER BY unitsSold DESC, p.productName""",
                                Object[].class
                        )
                        .setParameter("status", STATUS)
                        .setMaxResults(10)
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
            final var o = oi.join(OrderItem_.order);
            final var productId = p.get(Product_.productId);
            final var productName = p.get(Product_.productName);
            final var unitsSold = cb.sum(oi.get(OrderItem_.quantity));
            cq.select(cb.array(productId, productName, unitsSold))
                    .where(cb.equal(o.get(Order_.orderStatus), STATUS))
                    .groupBy(productId, productName)
                    .orderBy(cb.desc(unitsSold), cb.asc(productName));
            return em.createQuery(cq)
                    .setMaxResults(10)
                    .getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Number) r[0]).longValue(), r -> r[1],
                            r -> ((Number) r[2]).longValue())
                .containsExactly(
                        tuple(46L, "Girl's Trousers (Red)", 464L),
                        tuple(23L, "Men's Pyjamas (Blue)", 314L),
                        tuple(19L, "Men's Coat (Red)", 291L),
                        tuple(13L, "Boy's Hoodie (Grey)", 289L),
                        tuple(12L, "Boy's Socks (White)", 287L),
                        tuple(26L, "Girl's Hoodie (White)", 285L),
                        tuple(29L, "Boy's Shirt (Black)", 283L),
                        tuple(21L, "Girl's Pyjamas (White)", 282L),
                        tuple(32L, "Women's Jacket (Blue)", 279L),
                        tuple(11L, "Boy's Shorts (Blue)", 278L));
    }
}
