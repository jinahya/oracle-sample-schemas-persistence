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

import com.github.jinahya.oracle.sample.persistence.hr.Country_;
import com.github.jinahya.oracle.sample.persistence.hr.Location;
import com.github.jinahya.oracle.sample.persistence.hr.Location_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_LOCATIONS_B_01} of {@code src/test/sql-exercises/LOCATIONS/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_LOCATIONS_B_01: Sites in a country")
@Slf4j
class HR_LOCATIONS_B_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT l
                                        FROM Location l
                                        WHERE l.country.countryId = :countryId
                                        ORDER BY l.city ASC""",
                                Location.class
                        )
                        .setParameter("countryId", "US")
                        .getResultList()
        );
        list.forEach(l -> log.debug("{}", l));
        assertThat(list)
                .extracting(Location::getLocationId, Location::getStreetAddress, Location::getCity,
                            Location::getStateProvince)
                .containsExactly(
                        tuple(1700, "2004 Charade Rd", "Seattle", "Washington"),
                        tuple(1600, "2007 Zagora St", "South Brunswick", "New Jersey"),
                        tuple(1500, "2011 Interiors Blvd", "South San Francisco", "California"),
                        tuple(1400, "2014 Jabberwocky Rd", "Southlake", "Texas"));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Location.class);
            final var l = cq.from(Location.class);
            cq.select(l)
                    .where(cb.equal(l.get(Location_.country).get(Country_.countryId),
                                    cb.parameter(String.class, "countryId")))
                    .orderBy(cb.asc(l.get(Location_.city)));
            return em.createQuery(cq)
                    .setParameter("countryId", "US")
                    .getResultList();
        });
        list.forEach(l -> log.debug("{}", l));
        assertThat(list)
                .extracting(Location::getLocationId, Location::getStreetAddress, Location::getCity,
                            Location::getStateProvince)
                .containsExactly(
                        tuple(1700, "2004 Charade Rd", "Seattle", "Washington"),
                        tuple(1600, "2007 Zagora St", "South Brunswick", "New Jersey"),
                        tuple(1500, "2011 Interiors Blvd", "South San Francisco", "California"),
                        tuple(1400, "2014 Jabberwocky Rd", "Southlake", "Texas"));
    }

}
