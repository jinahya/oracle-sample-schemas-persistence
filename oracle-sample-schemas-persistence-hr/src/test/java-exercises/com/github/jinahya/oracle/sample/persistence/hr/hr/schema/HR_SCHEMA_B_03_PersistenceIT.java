package com.github.jinahya.oracle.sample.persistence.hr.hr.schema;

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
import com.github.jinahya.oracle.sample.persistence.hr.Region_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_SCHEMA_B_03} of {@code src/test/sql-exercises/__/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_SCHEMA_B_03: Countries of a region, by name")
@Slf4j
class HR_SCHEMA_B_03_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT c
                                        FROM Country c
                                                 JOIN c.region r
                                        WHERE r.regionName = :regionName
                                        ORDER BY c.countryName ASC""",
                                Country.class
                        )
                        .setParameter("regionName", "Europe")
                        .getResultList()
        );
        list.forEach(c -> log.debug("{}", c));
        assertThat(list)
                .extracting(Country::getCountryId, Country::getCountryName)
                .containsExactly(
                        tuple("BE", "Belgium"),
                        tuple("DK", "Denmark"),
                        tuple("FR", "France"),
                        tuple("DE", "Germany"),
                        tuple("IT", "Italy"),
                        tuple("NL", "Netherlands"),
                        tuple("CH", "Switzerland"),
                        tuple("GB", "United Kingdom of Great Britain and Northern Ireland"));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Country.class);
            final var c = cq.from(Country.class);
            final var r = c.join(Country_.region);
            cq.select(c)
                    .where(cb.equal(r.get(Region_.regionName),
                                    cb.parameter(String.class, "regionName")))
                    .orderBy(cb.asc(c.get(Country_.countryName)));
            return em.createQuery(cq)
                    .setParameter("regionName", "Europe")
                    .getResultList();
        });
        list.forEach(c -> log.debug("{}", c));
        assertThat(list)
                .extracting(Country::getCountryId, Country::getCountryName)
                .containsExactly(
                        tuple("BE", "Belgium"),
                        tuple("DK", "Denmark"),
                        tuple("FR", "France"),
                        tuple("DE", "Germany"),
                        tuple("IT", "Italy"),
                        tuple("NL", "Netherlands"),
                        tuple("CH", "Switzerland"),
                        tuple("GB", "United Kingdom of Great Britain and Northern Ireland"));
    }

}
