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

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_COUNTRIES_I_01} of {@code src/test/sql-exercises/COUNTRIES/02_intermediate.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_COUNTRIES_I_01: Countries per subregion")
@Slf4j
class SH_COUNTRIES_I_01_PersistenceIT {

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> r[0], r -> r[1], r -> ((Number) r[2]).longValue())
                .containsExactly(
                        tuple("Africa", "Africa", 3L),
                        tuple("Americas", "Northern America", 3L),
                        tuple("Americas", "Southern America", 3L),
                        tuple("Asia", "Asia", 6L),
                        tuple("Europe", "Eastern Europe", 3L),
                        tuple("Europe", "Western Europe", 11L),
                        tuple("Middle East", "Middle East", 4L),
                        tuple("Oceania", "Australia", 2L));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT c.countryRegion, c.countrySubregion, COUNT(c)
                                        FROM Country c
                                        GROUP BY c.countryRegion, c.countrySubregion
                                        ORDER BY c.countryRegion, c.countrySubregion""",
                                Object[].class
                        )
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var c = cq.from(Country.class);
            final var region = c.get(Country_.countryRegion);
            final var subregion = c.get(Country_.countrySubregion);
            cq.select(cb.array(region, subregion, cb.count(c)))
                    .groupBy(region, subregion)
                    .orderBy(cb.asc(region), cb.asc(subregion));
            return em.createQuery(cq)
                    .getResultList();
        });
        verify(list);
    }
}
