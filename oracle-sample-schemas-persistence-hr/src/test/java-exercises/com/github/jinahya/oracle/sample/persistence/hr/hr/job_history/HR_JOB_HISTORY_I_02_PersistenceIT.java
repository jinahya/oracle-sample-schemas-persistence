package com.github.jinahya.oracle.sample.persistence.hr.hr.job_history;

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

import com.github.jinahya.oracle.sample.persistence.hr.Employee;
import com.github.jinahya.oracle.sample.persistence.hr.Employee_;
import com.github.jinahya.oracle.sample.persistence.hr.JobHistory;
import com.github.jinahya.oracle.sample.persistence.hr.JobHistory_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_JOB_HISTORY_I_02} of
 * {@code src/test/sql-exercises/JOB_HISTORY/02_intermediate.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_JOB_HISTORY_I_02: Moved more than once")
@Slf4j
class HR_JOB_HISTORY_I_02_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT e, COUNT(h), MIN(h.startDate), MAX(h.endDate)
                                        FROM JobHistory h
                                                 JOIN h.employee e
                                        GROUP BY e
                                        HAVING COUNT(h) > 1
                                        ORDER BY e.employeeId ASC""",
                                Object[].class
                        )
                        .getResultList()
        );
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Employee) r[0]).getEmployeeId(),
                            r -> ((Number) r[1]).longValue(), r -> r[2], r -> r[3])
                .containsExactly(
                        tuple(101, 2L, LocalDateTime.parse("2007-09-21T00:00:00"),
                              LocalDateTime.parse("2015-03-15T00:00:00")),
                        tuple(176, 2L, LocalDateTime.parse("2016-03-24T00:00:00"),
                              LocalDateTime.parse("2017-12-31T00:00:00")),
                        tuple(200, 2L, LocalDateTime.parse("2005-09-17T00:00:00"),
                              LocalDateTime.parse("2016-12-31T00:00:00")));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var h = cq.from(JobHistory.class);
            final var e = h.join(JobHistory_.employee);
            cq.select(cb.array(e, cb.count(h), cb.least(h.get(JobHistory_.startDate)),
                               cb.greatest(h.get(JobHistory_.endDate))))
                    .groupBy(e)
                    .having(cb.gt(cb.count(h), 1))
                    .orderBy(cb.asc(e.get(Employee_.employeeId)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Employee) r[0]).getEmployeeId(),
                            r -> ((Number) r[1]).longValue(), r -> r[2], r -> r[3])
                .containsExactly(
                        tuple(101, 2L, LocalDateTime.parse("2007-09-21T00:00:00"),
                              LocalDateTime.parse("2015-03-15T00:00:00")),
                        tuple(176, 2L, LocalDateTime.parse("2016-03-24T00:00:00"),
                              LocalDateTime.parse("2017-12-31T00:00:00")),
                        tuple(200, 2L, LocalDateTime.parse("2005-09-17T00:00:00"),
                              LocalDateTime.parse("2016-12-31T00:00:00")));
    }

}
