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

import com.github.jinahya.oracle.sample.persistence.hr.JobHistory;
import com.github.jinahya.oracle.sample.persistence.hr.JobHistory_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_JOB_HISTORY_B_02} of {@code src/test/sql-exercises/JOB_HISTORY/01_basic.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_JOB_HISTORY_B_02: Positions closed in a period")
@Slf4j
class HR_JOB_HISTORY_B_02_PersistenceIT {

    // END_DATE has a time of day, so the period runs from the start of :fromDate up to, not
    // including, the start of the day after :toDate, as the SQL's TRUNC(:toDate) + 1 does.
    private static final LocalDate FROM_DATE = LocalDate.parse("2016-01-01");

    private static final LocalDate TO_DATE = LocalDate.parse("2016-12-31");

    @Test
    void __QueryLanguage() {
        // JobHistory's getEmployeeId() is not public; the id is read through the employee, while
        // the entity manager is open
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT h
                                        FROM JobHistory h
                                        WHERE h.endDate >= :fromDate
                                          AND h.endDate < :dayAfterToDate
                                        ORDER BY h.endDate ASC,
                                                 h.employeeId ASC,
                                                 h.startDate ASC""",
                                JobHistory.class
                        )
                        .setParameter("fromDate", FROM_DATE.atStartOfDay())
                        .setParameter("dayAfterToDate", TO_DATE.plusDays(1L).atStartOfDay())
                        .getResultList()
                        .stream()
                        .map(h -> tuple(h.getEmployee().getEmployeeId(), h.getStartDate(),
                                        h.getEndDate(), h.getJobId(), h.getDepartmentId()))
                        .toList()
        );
        list.forEach(t -> log.debug("{}", t));
        assertThat(list)
                .containsExactly(
                        tuple(102, LocalDateTime.parse("2011-01-13T00:00:00"),
                              LocalDateTime.parse("2016-07-24T00:00:00"), "IT_PROG", 60),
                        tuple(176, LocalDateTime.parse("2016-03-24T00:00:00"),
                              LocalDateTime.parse("2016-12-31T00:00:00"), "SA_REP", 80),
                        tuple(200, LocalDateTime.parse("2012-07-01T00:00:00"),
                              LocalDateTime.parse("2016-12-31T00:00:00"), "AC_ACCOUNT", 90));
    }

    @Test
    void __CriteriaApi() {
        // JobHistory's getEmployeeId() is not public; the id is read through the employee, while
        // the entity manager is open
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(JobHistory.class);
            final var h = cq.from(JobHistory.class);
            cq.select(h)
                    .where(cb.greaterThanOrEqualTo(h.get(JobHistory_.endDate),
                                                   cb.parameter(LocalDateTime.class, "fromDate")),
                           cb.lessThan(h.get(JobHistory_.endDate),
                                       cb.parameter(LocalDateTime.class, "dayAfterToDate")))
                    .orderBy(cb.asc(h.get(JobHistory_.endDate)),
                             cb.asc(h.get(JobHistory_.employeeId)),
                             cb.asc(h.get(JobHistory_.startDate)));
            return em.createQuery(cq)
                    .setParameter("fromDate", FROM_DATE.atStartOfDay())
                    .setParameter("dayAfterToDate", TO_DATE.plusDays(1L).atStartOfDay())
                    .getResultList()
                    .stream()
                    .map(r -> tuple(r.getEmployee().getEmployeeId(), r.getStartDate(),
                                    r.getEndDate(), r.getJobId(), r.getDepartmentId()))
                    .toList();
        });
        list.forEach(t -> log.debug("{}", t));
        assertThat(list)
                .containsExactly(
                        tuple(102, LocalDateTime.parse("2011-01-13T00:00:00"),
                              LocalDateTime.parse("2016-07-24T00:00:00"), "IT_PROG", 60),
                        tuple(176, LocalDateTime.parse("2016-03-24T00:00:00"),
                              LocalDateTime.parse("2016-12-31T00:00:00"), "SA_REP", 80),
                        tuple(200, LocalDateTime.parse("2012-07-01T00:00:00"),
                              LocalDateTime.parse("2016-12-31T00:00:00"), "AC_ACCOUNT", 90));
    }

}
