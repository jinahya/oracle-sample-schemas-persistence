package com.github.jinahya.oracle.sample.persistence.sh.sh.supplementary_demographics;

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

import com.github.jinahya.oracle.sample.persistence.sh.SupplementaryDemographics;
import com.github.jinahya.oracle.sample.persistence.sh.SupplementaryDemographics_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_SUPPLEMENTARY_DEMOGRAPHICS_I_01} of
 * {@code src/test/sql-exercises/SUPPLEMENTARY_DEMOGRAPHICS/02_intermediate.sql} with JPQL and with
 * the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_SUPPLEMENTARY_DEMOGRAPHICS_I_01: Card take-up by household size")
@Slf4j
class SH_SUPPLEMENTARY_DEMOGRAPHICS_I_01_PersistenceIT {

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> r[0], r -> ((Number) r[1]).longValue(),
                            r -> ((Number) r[2]).longValue())
                .containsExactly(
                        tuple("1", 692L, 11L),
                        tuple("2", 1149L, 109L),
                        tuple("3", 1787L, 814L),
                        tuple("4-5", 219L, 107L),
                        tuple("6-8", 148L, 2L),
                        tuple("9+", 505L, 29L));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT d.householdSize, COUNT(d), SUM(d.affinityCard)
                                        FROM SupplementaryDemographics d
                                        GROUP BY d.householdSize
                                        ORDER BY d.householdSize""",
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
            final var d = cq.from(SupplementaryDemographics.class);
            final var householdSize = d.get(SupplementaryDemographics_.householdSize);
            cq.select(cb.array(householdSize, cb.count(d),
                               cb.sum(d.get(SupplementaryDemographics_.affinityCard))))
                    .groupBy(householdSize)
                    .orderBy(cb.asc(householdSize));
            return em.createQuery(cq)
                    .getResultList();
        });
        verify(list);
    }
}
