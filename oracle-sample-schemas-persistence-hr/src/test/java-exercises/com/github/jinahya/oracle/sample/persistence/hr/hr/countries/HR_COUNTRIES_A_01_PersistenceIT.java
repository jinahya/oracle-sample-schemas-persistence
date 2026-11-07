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
import com.github.jinahya.oracle.sample.persistence.hr.Region;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_COUNTRIES_A_01} of {@code src/test/sql-exercises/COUNTRIES/03_advanced.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_COUNTRIES_A_01: Regions above the average size")
@Slf4j
class HR_COUNTRIES_A_01_PersistenceIT {

    // COUNT gives a Long, and Long / Long truncates in JPQL; '* 1.0' keeps the fraction of the
    // average, as Oracle's '/' does.

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT r, COUNT(c)
                                        FROM Country c
                                                 JOIN c.region r
                                        GROUP BY r
                                        HAVING COUNT(c) > (SELECT COUNT(x) * 1.0
                                                                      / COUNT(DISTINCT x.region)
                                                           FROM Country x)
                                        ORDER BY COUNT(c) DESC""",
                                Object[].class
                        )
                        .getResultList()
        );
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Region) r[0]).getRegionId(), r -> ((Number) r[1]).longValue())
                .containsExactly(
                        tuple(10L, 8L),
                        tuple(30L, 7L));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var c = cq.from(Country.class);
            final var r = c.join(Country_.region);
            final var sq = cq.subquery(Number.class);
            final var x = sq.from(Country.class);
            sq.select(cb.quot(cb.prod(cb.count(x), 1.0d),
                              cb.countDistinct(x.get(Country_.region))));
            cq.select(cb.array(r, cb.count(c)))
                    .groupBy(r)
                    .having(cb.gt(cb.count(c), sq))
                    .orderBy(cb.desc(cb.count(c)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Region) r[0]).getRegionId(), r -> ((Number) r[1]).longValue())
                .containsExactly(
                        tuple(10L, 8L),
                        tuple(30L, 7L));
    }

}
