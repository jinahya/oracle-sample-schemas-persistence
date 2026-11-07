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

import com.github.jinahya.oracle.sample.persistence.co.Order;
import com.github.jinahya.oracle.sample.persistence.co.OrderItem;
import com.github.jinahya.oracle.sample.persistence.co.OrderItem_;
import com.github.jinahya.oracle.sample.persistence.co.Order_;
import com.github.jinahya.oracle.sample.persistence.co.Product;
import com.github.jinahya.oracle.sample.persistence.co.Product_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_SCHEMA_A_09} of {@code src/test/sql-exercises/__/03_advanced.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_SCHEMA_A_09: Orders with a premium line")
@Slf4j
class CO_SCHEMA_A_09_PersistenceIT {

    private static final BigDecimal PRICE = new BigDecimal("49");

    @Test
    void __QueryLanguage() {
        final var orders = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT o
                                        FROM Order o
                                        WHERE :price < ANY (SELECT oi.unitPrice FROM OrderItem oi WHERE oi.order = o)
                                        ORDER BY o.orderId""",
                                Order.class
                        )
                        .setParameter("price", PRICE)
                        .getResultList()
        );
        // > ALL over an empty subquery is true, so products never sold are excluded explicitly
        final var products = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT p
                                        FROM Product p
                                        WHERE p.unitPrice > ALL (SELECT oi.unitPrice FROM OrderItem oi WHERE oi.product = p)
                                          AND p.orderItems IS NOT EMPTY
                                        ORDER BY p.productName""",
                                Product.class
                        )
                        .getResultList()
        );
        verify(orders, products);
    }

    @Test
    void __CriteriaApi() {
        final var orders = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Order.class);
            final var o = cq.from(Order.class);
            final var sq = cq.subquery(BigDecimal.class);
            final var oi = sq.from(OrderItem.class);
            sq.select(oi.get(OrderItem_.unitPrice))
                    .where(cb.equal(oi.get(OrderItem_.order), o));
            final var price = cb.parameter(BigDecimal.class);
            cq.select(o)
                    .where(cb.lessThan(price, cb.any(sq)))
                    .orderBy(cb.asc(o.get(Order_.orderId)));
            return em.createQuery(cq)
                    .setParameter(price, PRICE)
                    .getResultList();
        });
        final var products = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Product.class);
            final var p = cq.from(Product.class);
            final var sq = cq.subquery(BigDecimal.class);
            final var oi = sq.from(OrderItem.class);
            sq.select(oi.get(OrderItem_.unitPrice))
                    .where(cb.equal(oi.get(OrderItem_.product), p));
            cq.select(p)
                    .where(cb.greaterThan(p.get(Product_.unitPrice), cb.all(sq)),
                           cb.isNotEmpty(p.get(Product_.orderItems)))
                    .orderBy(cb.asc(p.get(Product_.productName)));
            return em.createQuery(cq).getResultList();
        });
        verify(orders, products);
    }

    private static void verify(final List<Order> orders, final List<Product> products) {
        orders.stream().limit(10).forEach(o -> log.debug("{}", o));
        products.forEach(p -> log.debug("{}", p));
        assertThat(orders).hasSize(82);
        assertThat(orders)
                .extracting(Order::getOrderId, Order::getOrderTms)
                .startsWith(
                        tuple(7L, LocalDateTime.parse("2021-02-22T00:57:11.011114")),
                        tuple(8L, LocalDateTime.parse("2021-02-22T15:00:52.003836")),
                        tuple(25L, LocalDateTime.parse("2021-03-03T06:39:48.533449")),
                        tuple(28L, LocalDateTime.parse("2021-03-05T00:33:33.690222")),
                        tuple(134L, LocalDateTime.parse("2021-04-12T16:26:47.079442")),
                        tuple(137L, LocalDateTime.parse("2021-04-13T10:16:51.407227")));
        assertThat(products)
                .extracting(Product::getProductId, Product::getProductName,
                            p -> p.getUnitPrice().doubleValue())
                .containsExactly(
                        tuple(8L, "Boy's Coat (Brown)", 21.16),
                        tuple(13L, "Boy's Hoodie (Grey)", 26.14),
                        tuple(42L, "Boy's Jeans (Black)", 16.64),
                        tuple(29L, "Boy's Shirt (Black)", 37.34),
                        tuple(7L, "Boy's Socks (Black)", 19.58),
                        tuple(12L, "Boy's Socks (White)", 12.64),
                        tuple(3L, "Boy's Sweater (Green)", 44.17),
                        tuple(43L, "Boy's Trousers (Black)", 39.32),
                        tuple(39L, "Boy's Trousers (Blue)", 34.06),
                        tuple(35L, "Girl's Dress (Red)", 49.12),
                        tuple(25L, "Girl's Jeans (Grey)", 48.75),
                        tuple(21L, "Girl's Pyjamas (White)", 39.78),
                        tuple(20L, "Girl's Shorts (Green)", 38.34),
                        tuple(19L, "Men's Coat (Red)", 28.21),
                        tuple(28L, "Men's Hoodie (Red)", 24.71),
                        tuple(23L, "Men's Pyjamas (Blue)", 48.39),
                        tuple(41L, "Women's Dress (Black)", 10.11),
                        tuple(32L, "Women's Jacket (Blue)", 37.0),
                        tuple(9L, "Women's Jeans (Brown)", 29.49),
                        tuple(10L, "Women's Skirt (Red)", 30.69),
                        tuple(16L, "Women's Socks (Grey)", 39.89));
    }
}
