package com.github.jinahya.oracle.sample.persistence.hr.hr.locations;

/*-
 * #%L
 * hr
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

import com.github.jinahya.oracle.sample.persistence.hr.Location;
import com.github.jinahya.oracle.sample.persistence.hr.Location_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_LOCATIONS_B_02} of {@code src/test/sql-exercises/LOCATIONS/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_LOCATIONS_B_02: Sites without a postal code")
@Slf4j
class HR_LOCATIONS_B_02_PersistenceIT {

    // The SQL has no ORDER BY, so neither do the queries, and the rows are compared in any order.

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT l
                                        FROM Location l
                                        WHERE l.postalCode IS NULL""",
                                Location.class
                        )
                        .getResultList()
        );
        list.forEach(l -> log.debug("{}", l));
        assertThat(list)
                .extracting(Location::getLocationId, Location::getStreetAddress, Location::getCity,
                            Location::getCountryId)
                .containsExactlyInAnyOrder(
                        tuple(2400, "8204 Arthur St", "London", "GB"));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Location.class);
            final var l = cq.from(Location.class);
            cq.select(l)
                    .where(cb.isNull(l.get(Location_.postalCode)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(l -> log.debug("{}", l));
        assertThat(list)
                .extracting(Location::getLocationId, Location::getStreetAddress, Location::getCity,
                            Location::getCountryId)
                .containsExactlyInAnyOrder(
                        tuple(2400, "8204 Arthur St", "London", "GB"));
    }

}
