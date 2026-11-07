package com.github.jinahya.oracle.sample.persistence.sh.sh.times;

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

import com.github.jinahya.oracle.sample.persistence.sh.Time;
import com.github.jinahya.oracle.sample.persistence.sh.Time_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_TIMES_I_01} of {@code src/test/sql-exercises/TIMES/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_TIMES_I_01: Fiscal month boundaries")
@Slf4j
class SH_TIMES_I_01_PersistenceIT {

    private static final Integer FISCAL_YEAR = 2021;

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> r[0], r -> ((LocalDateTime) r[1]).toLocalDate(),
                            r -> ((LocalDateTime) r[2]).toLocalDate(),
                            r -> ((Number) r[3]).longValue())
                .containsExactly(
                        tuple("2021-01", LocalDate.of(2020, 12, 27), LocalDate.of(2021, 1, 30),
                              35L),
                        tuple("2021-02", LocalDate.of(2021, 1, 31), LocalDate.of(2021, 2, 27), 28L),
                        tuple("2021-03", LocalDate.of(2021, 2, 28), LocalDate.of(2021, 3, 26), 27L),
                        tuple("2021-04", LocalDate.of(2021, 3, 27), LocalDate.of(2021, 4, 30), 35L),
                        tuple("2021-05", LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 28), 28L),
                        tuple("2021-06", LocalDate.of(2021, 5, 29), LocalDate.of(2021, 6, 25), 28L),
                        tuple("2021-07", LocalDate.of(2021, 6, 26), LocalDate.of(2021, 7, 30), 35L),
                        tuple("2021-08", LocalDate.of(2021, 7, 31), LocalDate.of(2021, 8, 27), 28L),
                        tuple("2021-09", LocalDate.of(2021, 8, 28), LocalDate.of(2021, 9, 24), 28L),
                        tuple("2021-10", LocalDate.of(2021, 9, 25), LocalDate.of(2021, 10, 29),
                              35L),
                        tuple("2021-11", LocalDate.of(2021, 10, 30), LocalDate.of(2021, 11, 26),
                              28L),
                        tuple("2021-12", LocalDate.of(2021, 11, 27), LocalDate.of(2021, 12, 31),
                              35L));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT t.fiscalMonthDesc,
                                               MIN(t.timeId),
                                               MAX(t.timeId),
                                               COUNT(t)
                                        FROM Time t
                                        WHERE t.fiscalYear = :fiscalYear
                                        GROUP BY t.fiscalMonthDesc
                                        ORDER BY t.fiscalMonthDesc""",
                                Object[].class
                        )
                        .setParameter("fiscalYear", FISCAL_YEAR)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var t = cq.from(Time.class);
            final var fiscalMonthDesc = t.get(Time_.fiscalMonthDesc);
            cq.select(cb.array(fiscalMonthDesc, cb.least(t.get(Time_.timeId)),
                               cb.greatest(t.get(Time_.timeId)), cb.count(t)))
                    .where(cb.equal(t.get(Time_.fiscalYear),
                                    cb.parameter(Integer.class, "fiscalYear")))
                    .groupBy(fiscalMonthDesc)
                    .orderBy(cb.asc(fiscalMonthDesc));
            return em.createQuery(cq)
                    .setParameter("fiscalYear", FISCAL_YEAR)
                    .getResultList();
        });
        verify(list);
    }
}
