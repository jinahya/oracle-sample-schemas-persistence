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
import com.github.jinahya.oracle.sample.persistence.co.Shipment_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import com.github.jinahya.oracle.sample.persistence.co.mapped.OrderItemId_;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_SCHEMA_A_12} of {@code src/test/sql-exercises/__/03_advanced.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_SCHEMA_A_12: Packing slips in one round trip")
@Slf4j
class CO_SCHEMA_A_12_PersistenceIT {

    private static final long SHIPMENT_ID = 3L;

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var items = em.createQuery(
                            """
                                    SELECT oi
                                    FROM OrderItem oi
                                             JOIN FETCH oi.product
                                             JOIN FETCH oi.shipment
                                    WHERE oi.shipment.shipmentId = :shipmentId
                                    ORDER BY oi.id_.orderId, oi.id_.lineItemId""",
                            OrderItem.class
                    )
                    .setParameter("shipmentId", SHIPMENT_ID)
                    .getResultList();
            assertFetched(em, items);
            return items;
        });
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(OrderItem.class);
            final var oi = cq.from(OrderItem.class);
            oi.fetch(OrderItem_.product);
            oi.fetch(OrderItem_.shipment);
            final var id = oi.get(OrderItem_.id_);
            cq.select(oi)
                    .where(cb.equal(oi.get(OrderItem_.shipment).get(Shipment_.shipmentId),
                                    SHIPMENT_ID))
                    .orderBy(cb.asc(id.get(OrderItemId_.orderId)),
                             cb.asc(id.get(OrderItemId_.lineItemId)));
            final var items = em.createQuery(cq).getResultList();
            assertFetched(em, items);
            return items;
        });
        verify(list);
    }

    // the product and the shipment of each line are loaded with the line, in the same query
    private static void assertFetched(final EntityManager em, final List<OrderItem> items) {
        final var util = em.getEntityManagerFactory().getPersistenceUnitUtil();
        assertThat(items).allSatisfy(oi -> {
            assertThat(util.isLoaded(oi, OrderItem.ATTRIBUTE_NAME_PRODUCT)).isTrue();
            assertThat(util.isLoaded(oi, OrderItem.ATTRIBUTE_NAME_SHIPMENT)).isTrue();
        });
    }

    private static void verify(final List<OrderItem> list) {
        list.forEach(oi -> log.debug("{}", oi));
        assertThat(list)
                .extracting(oi -> oi.getId_().getOrderId(),
                            oi -> oi.getId_().getLineItemId(),
                            oi -> oi.getProduct().getProductName(),
                            OrderItem::getQuantity,
                            oi -> oi.getShipment().getDeliveryAddress())
                .containsExactly(
                        tuple(4L, 1L, "Girl's Shorts (Green)", 2L, "Jobstown, NJ 08041 USA"),
                        tuple(4L, 2L, "Girl's Pyjamas (Red)", 4L, "Jobstown, NJ 08041 USA"),
                        tuple(4L, 3L, "Girl's Trousers (Red)", 4L, "Jobstown, NJ 08041 USA"));
    }
}
