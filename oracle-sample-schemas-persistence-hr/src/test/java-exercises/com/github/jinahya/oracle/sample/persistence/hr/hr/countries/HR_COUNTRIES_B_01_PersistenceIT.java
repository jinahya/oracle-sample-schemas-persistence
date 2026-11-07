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
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code HR_COUNTRIES_B_01} of {@code src/test/sql-exercises/COUNTRIES/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_COUNTRIES_B_01: Country by code")
@Slf4j
class HR_COUNTRIES_B_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var country = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT c
                                        FROM Country c
                                        WHERE c.countryId = :countryId""",
                                Country.class
                        )
                        .setParameter("countryId", "US")
                        .getSingleResult()
        );
        log.debug("{}", country);
        assertThat(country.getCountryName()).isEqualTo("United States of America");
    }

    @Test
    void __CriteriaApi() {
        final var country = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Country.class);
            final var c = cq.from(Country.class);
            cq.select(c)
                    .where(cb.equal(c.get(Country_.countryId),
                                    cb.parameter(String.class, "countryId")));
            return em.createQuery(cq)
                    .setParameter("countryId", "US")
                    .getSingleResult();
        });
        log.debug("{}", country);
        assertThat(country.getCountryName()).isEqualTo("United States of America");
    }

}
