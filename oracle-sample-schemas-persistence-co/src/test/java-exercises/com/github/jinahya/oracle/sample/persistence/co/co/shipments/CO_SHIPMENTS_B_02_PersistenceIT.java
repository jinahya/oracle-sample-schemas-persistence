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
 * Answers {@code CO_SHIPMENTS_B_02} of {@code src/test/sql-exercises/SHIPMENTS/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_SHIPMENTS_B_02: Shipments to a town")
@Slf4j
class CO_SHIPMENTS_B_02_PersistenceIT {

    private static final String TOWN = "Jobstown";

    // a JPQL LIKE pattern is a literal or a parameter, so the pattern is built here
    private static final String PATTERN = '%' + TOWN + '%';

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT s
                                        FROM Shipment s
                                        WHERE s.deliveryAddress LIKE :pattern
                                        ORDER BY s.shipmentId""",
                                Shipment.class
                        )
                        .setParameter("pattern", PATTERN)
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
            cq.select(s)
                    .where(cb.like(s.get(Shipment_.deliveryAddress), PATTERN))
                    .orderBy(cb.asc(s.get(Shipment_.shipmentId)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Shipment> list) {
        list.forEach(s -> log.debug("{}", s));
        final var address = "Jobstown, NJ 08041 USA";
        final var delivered = MappedShipment.ShipmentStatus.DELIVERED;
        assertThat(list)
                .extracting(Shipment::getShipmentId, Shipment::getDeliveryAddress,
                            Shipment::getShipmentStatus)
                .containsExactly(
                        tuple(1L, address, delivered),
                        tuple(3L, address, delivered),
                        tuple(542L, address, delivered),
                        tuple(543L, address, delivered),
                        tuple(1018L, address, delivered),
                        tuple(1019L, address, delivered),
                        tuple(1212L, address, delivered),
                        tuple(1609L, address, delivered),
                        tuple(1610L, address, delivered));
    }
}
