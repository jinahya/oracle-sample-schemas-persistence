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

import com.github.jinahya.oracle.sample.persistence.hr.Country;
import com.github.jinahya.oracle.sample.persistence.hr.Country_;
import com.github.jinahya.oracle.sample.persistence.hr.Location;
import com.github.jinahya.oracle.sample.persistence.hr.Location_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_LOCATIONS_I_01} of {@code src/test/sql-exercises/LOCATIONS/02_intermediate.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_LOCATIONS_I_01: Sites per country")
@Slf4j
class HR_LOCATIONS_I_01_PersistenceIT {

    // The inner join drops a location without a country, which the SQL would group under a null
    // COUNTRY_ID; none is without one in the installed data.

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT c, COUNT(l)
                                        FROM Location l
                                                 JOIN l.country c
                                        GROUP BY c
                                        ORDER BY COUNT(l) DESC, c.countryId ASC""",
                                Object[].class
                        )
                        .getResultList()
        );
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Country) r[0]).getCountryId(), r -> ((Number) r[1]).longValue())
                .containsExactly(
                        tuple("US", 4L),
                        tuple("GB", 3L),
                        tuple("CA", 2L),
                        tuple("CH", 2L),
                        tuple("IT", 2L),
                        tuple("JP", 2L),
                        tuple("AU", 1L),
                        tuple("BR", 1L),
                        tuple("CN", 1L),
                        tuple("DE", 1L),
                        tuple("IN", 1L),
                        tuple("MX", 1L),
                        tuple("NL", 1L),
                        tuple("SG", 1L));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var l = cq.from(Location.class);
            final var c = l.join(Location_.country);
            cq.select(cb.array(c, cb.count(l)))
                    .groupBy(c)
                    .orderBy(cb.desc(cb.count(l)), cb.asc(c.get(Country_.countryId)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Country) r[0]).getCountryId(), r -> ((Number) r[1]).longValue())
                .containsExactly(
                        tuple("US", 4L),
                        tuple("GB", 3L),
                        tuple("CA", 2L),
                        tuple("CH", 2L),
                        tuple("IT", 2L),
                        tuple("JP", 2L),
                        tuple("AU", 1L),
                        tuple("BR", 1L),
                        tuple("CN", 1L),
                        tuple("DE", 1L),
                        tuple("IN", 1L),
                        tuple("MX", 1L),
                        tuple("NL", 1L),
                        tuple("SG", 1L));
    }

}
