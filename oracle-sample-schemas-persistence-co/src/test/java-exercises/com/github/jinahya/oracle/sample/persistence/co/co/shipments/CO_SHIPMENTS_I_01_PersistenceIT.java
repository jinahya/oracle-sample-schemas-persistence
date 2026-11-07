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
import com.github.jinahya.oracle.sample.persistence.co.Store_;
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
 * Answers {@code CO_SHIPMENTS_I_01} of {@code src/test/sql-exercises/SHIPMENTS/02_intermediate.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_SHIPMENTS_I_01: Open shipments per store")
@Slf4j
class CO_SHIPMENTS_I_01_PersistenceIT {

    // Store maps LOGO, a BLOB, and Oracle cannot group by a BLOB (ORA-22848), so the query groups
    // by the store's key, the foreign key itself, rather than by the Store.

    // the attribute goes through a converter, so the status is bound as a parameter
    private static final MappedShipment.ShipmentStatus DELIVERED =
            MappedShipment.ShipmentStatus.DELIVERED;

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT s.store.storeId, s.shipmentStatus, COUNT(s)
                                        FROM Shipment s
                                        WHERE s.shipmentStatus <> :delivered
                                        GROUP BY s.store.storeId, s.shipmentStatus
                                        ORDER BY s.store.storeId, s.shipmentStatus""",
                                Object[].class
                        )
                        .setParameter("delivered", DELIVERED)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var s = cq.from(Shipment.class);
            final var storeId = s.get(Shipment_.store).get(Store_.storeId);
            final var shipmentStatus = s.get(Shipment_.shipmentStatus);
            final var delivered = cb.parameter(MappedShipment.ShipmentStatus.class);
            cq.select(cb.array(storeId, shipmentStatus, cb.count(s)))
                    .where(cb.notEqual(shipmentStatus, delivered))
                    .groupBy(storeId, shipmentStatus)
                    .orderBy(cb.asc(storeId), cb.asc(shipmentStatus));
            return em.createQuery(cq)
                    .setParameter(delivered, DELIVERED)
                    .getResultList();
        });
        verify(list);
    }

    // the order is that of the column's text: CREATED, IN-TRANSIT, SHIPPED
    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Number) r[0]).longValue(), r -> r[1],
                            r -> ((Number) r[2]).longValue())
                .containsExactly(
                        tuple(1L, MappedShipment.ShipmentStatus.CREATED, 8L),
                        tuple(1L, MappedShipment.ShipmentStatus.IN_TRANSIT, 61L),
                        tuple(1L, MappedShipment.ShipmentStatus.SHIPPED, 27L));
    }
}
