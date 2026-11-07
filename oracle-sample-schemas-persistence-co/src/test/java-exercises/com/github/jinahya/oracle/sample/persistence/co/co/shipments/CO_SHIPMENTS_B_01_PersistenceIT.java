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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_SHIPMENTS_B_01} of {@code src/test/sql-exercises/SHIPMENTS/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_SHIPMENTS_B_01: Shipments on the road")
@Slf4j
class CO_SHIPMENTS_B_01_PersistenceIT {

    // The attribute goes through a converter, which writes IN_TRANSIT as 'IN-TRANSIT'; the status
    // is bound as a parameter, which the converter converts.
    private static final MappedShipment.ShipmentStatus SHIPMENT_STATUS =
            MappedShipment.ShipmentStatus.IN_TRANSIT;

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT s
                                        FROM Shipment s
                                        WHERE s.shipmentStatus = :shipmentStatus
                                        ORDER BY s.shipmentId""",
                                Shipment.class
                        )
                        .setParameter("shipmentStatus", SHIPMENT_STATUS)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Shipment.class);
            final var s = cq.from(Shipment.class);
            final var shipmentStatus = cb.parameter(MappedShipment.ShipmentStatus.class);
            cq.select(s)
                    .where(cb.equal(s.get(Shipment_.shipmentStatus), shipmentStatus))
                    .orderBy(cb.asc(s.get(Shipment_.shipmentId)));
            return em.createQuery(cq)
                    .setParameter(shipmentStatus, SHIPMENT_STATUS)
                    .getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Shipment> list) {
        list.stream().limit(10).forEach(s -> log.debug("{}", s));
        assertThat(list).hasSize(61);
        assertThat(list)
                .extracting(Shipment::getShipmentId, Shipment::getStoreId,
                            Shipment::getCustomerId, Shipment::getDeliveryAddress)
                .startsWith(
                        tuple(1310L, 1L, 176L, "Inverness, MS 38753 USA"),
                        tuple(1927L, 1L, 177L, "Dawson, ND 58428 USA"),
                        tuple(1928L, 1L, 257L, "Neon, KY 41840 USA"),
                        tuple(1929L, 1L, 140L, "Orlando, FL 32811 USA"),
                        tuple(1930L, 1L, 140L, "Orlando, FL 32811 USA"),
                        tuple(1931L, 1L, 321L, "Baltimore, MD 21223 USA"));
        assertThat(list)
                .extracting(Shipment::getShipmentStatus)
                .containsOnly(SHIPMENT_STATUS);
    }
}
