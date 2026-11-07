package com.github.jinahya.oracle.sample.persistence.hr.hr.countries;

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
 * Answers {@code HR_COUNTRIES_B_02} of {@code src/test/sql-exercises/COUNTRIES/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_COUNTRIES_B_02: Countries of a region")
@Slf4j
class HR_COUNTRIES_B_02_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT c
                                        FROM Country c
                                        WHERE c.region.regionId = :regionId
                                        ORDER BY c.countryName ASC""",
                                Country.class
                        )
                        .setParameter("regionId", 20L)
                        .getResultList()
        );
        list.forEach(c -> log.debug("{}", c));
        assertThat(list)
                .extracting(Country::getCountryId, Country::getCountryName)
                .containsExactly(
                        tuple("AR", "Argentina"),
                        tuple("BR", "Brazil"),
                        tuple("CA", "Canada"),
                        tuple("MX", "Mexico"),
                        tuple("US", "United States of America"));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Country.class);
            final var c = cq.from(Country.class);
            cq.select(c)
                    .where(cb.equal(c.get(Country_.region).get(Region_.regionId),
                                    cb.parameter(Long.class, "regionId")))
                    .orderBy(cb.asc(c.get(Country_.countryName)));
            return em.createQuery(cq)
                    .setParameter("regionId", 20L)
                    .getResultList();
        });
        list.forEach(c -> log.debug("{}", c));
        assertThat(list)
                .extracting(Country::getCountryId, Country::getCountryName)
                .containsExactly(
                        tuple("AR", "Argentina"),
                        tuple("BR", "Brazil"),
                        tuple("CA", "Canada"),
                        tuple("MX", "Mexico"),
                        tuple("US", "United States of America"));
    }

}
