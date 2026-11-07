package com.github.jinahya.oracle.sample.persistence.co.co.shipments;

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

import com.github.jinahya.oracle.sample.persistence.co.Shipment;
import com.github.jinahya.oracle.sample.persistence.co.Shipment_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedShipment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_SHIPMENTS_A_01} of {@code src/test/sql-exercises/SHIPMENTS/03_advanced.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_SHIPMENTS_A_01: Shipments that could travel together")
@Slf4j
class CO_SHIPMENTS_A_01_PersistenceIT {

    // the attribute goes through a converter, so the status is bound as a parameter
    private static final MappedShipment.ShipmentStatus CREATED =
            MappedShipment.ShipmentStatus.CREATED;

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT a, b
                                        FROM Shipment a
                                                 JOIN Shipment b ON b.store = a.store
                                                                AND b.customer = a.customer
                                                                AND b.deliveryAddress = a.deliveryAddress
                                                                AND b.shipmentId > a.shipmentId
                                        WHERE a.shipmentStatus = :created
                                          AND b.shipmentStatus = :created
                                        ORDER BY a.shipmentId, b.shipmentId""",
                                Object[].class
                        )
                        .setParameter("created", CREATED)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var a = cq.from(Shipment.class);
            // A second root, with the join's conditions in WHERE, rather than an entity join:
            // EclipseLink 5.0 refuses From.join(Class, JoinType) between two entities with no
            // attribute from one to the other ("the target class is missing an attribute of
            // source type"), which Hibernate ORM takes.
            final var b = cq.from(Shipment.class);
            final var created = cb.parameter(MappedShipment.ShipmentStatus.class);
            cq.select(cb.array(a, b))
                    .where(cb.equal(b.get(Shipment_.store), a.get(Shipment_.store)),
                           cb.equal(b.get(Shipment_.customer), a.get(Shipment_.customer)),
                           cb.equal(b.get(Shipment_.deliveryAddress),
                                    a.get(Shipment_.deliveryAddress)),
                           cb.gt(b.get(Shipment_.shipmentId), a.get(Shipment_.shipmentId)),
                           cb.equal(a.get(Shipment_.shipmentStatus), created),
                           cb.equal(b.get(Shipment_.shipmentStatus), created))
                    .orderBy(cb.asc(a.get(Shipment_.shipmentId)),
                             cb.asc(b.get(Shipment_.shipmentId)));
            return em.createQuery(cq)
                    .setParameter(created, CREATED)
                    .getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Shipment) r[0]).getShipmentId(),
                            r -> ((Shipment) r[1]).getShipmentId(),
                            r -> ((Shipment) r[0]).getStoreId(),
                            r -> ((Shipment) r[0]).getCustomerId(),
                            r -> ((Shipment) r[0]).getDeliveryAddress())
                .containsExactly(
                        tuple(1848L, 2021L, 1L, 347L, "Shell Rock, IA 50670 USA"),
                        tuple(2022L, 2023L, 1L, 309L, "Saint Francis, WI 53235 USA"),
                        tuple(2025L, 2026L, 1L, 354L, "Stockdale, TX 78160 USA"));
    }
}
