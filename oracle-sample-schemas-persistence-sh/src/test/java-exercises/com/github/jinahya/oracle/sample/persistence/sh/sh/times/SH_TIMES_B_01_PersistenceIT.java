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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_TIMES_B_01} of {@code src/test/sql-exercises/TIMES/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_TIMES_B_01: Which fiscal period is a day in?")
@Slf4j
class SH_TIMES_B_01_PersistenceIT {

    // a day of calendar 2020 which falls in fiscal 2021
    private static final LocalDateTime DAY = LocalDate.of(2020, 12, 28).atStartOfDay();

    private static void verify(final List<Time> list) {
        list.forEach(t -> log.debug("{}", t));
        assertThat(list)
                .extracting(Time::getTimeId, Time::getFiscalYear, Time::getFiscalQuarterDesc,
                            Time::getFiscalMonthDesc, Time::getCalendarQuarterDesc,
                            Time::getCalendarMonthDesc)
                .containsExactly(
                        tuple(DAY, 2021, "2021-01", "2021-01", "2020-04", "2020-12"));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT t
                                        FROM Time t
                                        WHERE t.timeId = :day""",
                                Time.class
                        )
                        .setParameter("day", DAY)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Time.class);
            final var t = cq.from(Time.class);
            cq.select(t)
                    .where(cb.equal(t.get(Time_.timeId), cb.parameter(LocalDateTime.class, "day")));
            return em.createQuery(cq)
                    .setParameter("day", DAY)
                    .getResultList();
        });
        verify(list);
    }
}
