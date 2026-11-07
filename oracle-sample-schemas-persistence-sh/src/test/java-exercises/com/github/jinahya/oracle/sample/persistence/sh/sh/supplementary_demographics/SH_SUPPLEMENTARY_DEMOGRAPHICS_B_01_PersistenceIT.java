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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_SUPPLEMENTARY_DEMOGRAPHICS_B_01} of
 * {@code src/test/sql-exercises/SUPPLEMENTARY_DEMOGRAPHICS/01_basic.sql} with JPQL and with the
 * Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_SUPPLEMENTARY_DEMOGRAPHICS_B_01: Card holders in an occupation")
@Slf4j
class SH_SUPPLEMENTARY_DEMOGRAPHICS_B_01_PersistenceIT {

    private static final String OCCUPATION = "Farming";

    private static void verify(final List<SupplementaryDemographics> list) {
        list.forEach(d -> log.debug("{}", d));
        assertThat(list)
                .extracting(SupplementaryDemographics::getCustId,
                            SupplementaryDemographics::getEducation,
                            SupplementaryDemographics::getHouseholdSize,
                            SupplementaryDemographics::getYrsResidence)
                .containsExactly(
                        tuple(100149L, "11th", "3", 6L),
                        tuple(100497L, "HS-grad", "3", 7L),
                        tuple(100532L, "HS-grad", "3", 6L),
                        tuple(101373L, "< Bach.", "3", 5L),
                        tuple(101769L, "HS-grad", "3", 4L),
                        tuple(101777L, "< Bach.", "2", 8L),
                        tuple(102123L, "HS-grad", "3", 5L),
                        tuple(102133L, "HS-grad", "3", 9L),
                        tuple(102271L, "< Bach.", "3", 4L),
                        tuple(102730L, "< Bach.", "3", 4L),
                        tuple(102852L, "< Bach.", "3", 5L),
                        tuple(103189L, "11th", "3", 6L),
                        tuple(103277L, "HS-grad", "3", 7L),
                        tuple(103327L, "HS-grad", "3", 6L),
                        tuple(104461L, "< Bach.", "3", 5L));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT d
                                        FROM SupplementaryDemographics d
                                        WHERE d.occupation = :occupation
                                          AND d.affinityCard = 1
                                        ORDER BY d.custId""",
                                SupplementaryDemographics.class
                        )
                        .setParameter("occupation", OCCUPATION)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(SupplementaryDemographics.class);
            final var d = cq.from(SupplementaryDemographics.class);
            cq.select(d)
                    .where(cb.equal(d.get(SupplementaryDemographics_.occupation),
                                    cb.parameter(String.class, "occupation")),
                           cb.equal(d.get(SupplementaryDemographics_.affinityCard), 1L))
                    .orderBy(cb.asc(d.get(SupplementaryDemographics_.custId)));
            return em.createQuery(cq)
                    .setParameter("occupation", OCCUPATION)
                    .getResultList();
        });
        verify(list);
    }
}
