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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_JOB_HISTORY_B_01} of {@code src/test/sql-exercises/JOB_HISTORY/01_basic.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_JOB_HISTORY_B_01: One employee's earlier positions")
@Slf4j
class HR_JOB_HISTORY_B_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT h
                                        FROM JobHistory h
                                        WHERE h.employeeId = :employeeId
                                        ORDER BY h.startDate ASC""",
                                JobHistory.class
                        )
                        .setParameter("employeeId", 101)
                        .getResultList()
        );
        list.forEach(h -> log.debug("{}", h));
        assertThat(list)
                .extracting(JobHistory::getStartDate, JobHistory::getEndDate, JobHistory::getJobId,
                            JobHistory::getDepartmentId)
                .containsExactly(
                        tuple(LocalDateTime.parse("2007-09-21T00:00:00"),
                              LocalDateTime.parse("2011-10-27T00:00:00"), "AC_ACCOUNT", 110),
                        tuple(LocalDateTime.parse("2011-10-28T00:00:00"),
                              LocalDateTime.parse("2015-03-15T00:00:00"), "AC_MGR", 110));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(JobHistory.class);
            final var h = cq.from(JobHistory.class);
            cq.select(h)
                    .where(cb.equal(h.get(JobHistory_.employeeId),
                                    cb.parameter(Integer.class, "employeeId")))
                    .orderBy(cb.asc(h.get(JobHistory_.startDate)));
            return em.createQuery(cq)
                    .setParameter("employeeId", 101)
                    .getResultList();
        });
        list.forEach(h -> log.debug("{}", h));
        assertThat(list)
                .extracting(JobHistory::getStartDate, JobHistory::getEndDate, JobHistory::getJobId,
                            JobHistory::getDepartmentId)
                .containsExactly(
                        tuple(LocalDateTime.parse("2007-09-21T00:00:00"),
                              LocalDateTime.parse("2011-10-27T00:00:00"), "AC_ACCOUNT", 110),
                        tuple(LocalDateTime.parse("2011-10-28T00:00:00"),
                              LocalDateTime.parse("2015-03-15T00:00:00"), "AC_MGR", 110));
    }

}
