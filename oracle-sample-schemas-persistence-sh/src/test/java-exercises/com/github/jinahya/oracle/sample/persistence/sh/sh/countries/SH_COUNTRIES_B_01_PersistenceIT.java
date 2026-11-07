package com.github.jinahya.oracle.sample.persistence.sh.sh.countries;

/*-
 * #%L
 * sh
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

import com.github.jinahya.oracle.sample.persistence.sh.Country;
import com.github.jinahya.oracle.sample.persistence.sh.Country_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_COUNTRIES_B_01} of {@code src/test/sql-exercises/COUNTRIES/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_COUNTRIES_B_01: Countries of a sales region")
@Slf4j
class SH_COUNTRIES_B_01_PersistenceIT {

    private static final String REGION = "Europe";

    private static void verify(final List<Country> list) {
        list.forEach(c -> log.debug("{}", c));
        assertThat(list)
                .extracting(Country::getCountrySubregion, Country::getCountryIsoCode,
                            Country::getCountryName)
                .containsExactly(
                        tuple("Eastern Europe", "HU", "Hungary"),
                        tuple("Eastern Europe", "PL", "Poland"),
                        tuple("Eastern Europe", "RO", "Romania"),
                        tuple("Western Europe", "DK", "Denmark"),
                        tuple("Western Europe", "FR", "France"),
                        tuple("Western Europe", "DE", "Germany"),
                        tuple("Western Europe", "IE", "Ireland"),
                        tuple("Western Europe", "IT", "Italy"),
                        tuple("Western Europe", "NL", "Netherlands"),
                        tuple("Western Europe", "ES", "Spain"),
                        tuple("Western Europe", "SE", "Sweden"),
                        tuple("Western Europe", "CH", "Switzerland"),
                        tuple("Western Europe", "TR", "Turkey"),
                        tuple("Western Europe", "GB", "United Kingdom"));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT c
                                        FROM Country c
                                        WHERE c.countryRegion = :region
                                        ORDER BY c.countrySubregion, c.countryName""",
                                Country.class
                        )
                        .setParameter("region", REGION)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Country.class);
            final var c = cq.from(Country.class);
            cq.select(c)
                    .where(cb.equal(c.get(Country_.countryRegion),
                                    cb.parameter(String.class, "region")))
                    .orderBy(cb.asc(c.get(Country_.countrySubregion)),
                             cb.asc(c.get(Country_.countryName)));
            return em.createQuery(cq)
                    .setParameter("region", REGION)
                    .getResultList();
        });
        verify(list);
    }
}
