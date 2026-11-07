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

import com.github.jinahya.oracle.sample.persistence.hr.Job;
import com.github.jinahya.oracle.sample.persistence.hr.JobHistory;
import com.github.jinahya.oracle.sample.persistence.hr.JobHistory_;
import com.github.jinahya.oracle.sample.persistence.hr.Job_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_JOB_HISTORY_I_01} of
 * {@code src/test/sql-exercises/JOB_HISTORY/02_intermediate.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_JOB_HISTORY_I_01: Turnover per job")
@Slf4j
class HR_JOB_HISTORY_I_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT j, COUNT(h), COUNT(DISTINCT h.employeeId)
                                        FROM JobHistory h
                                                 JOIN h.job j
                                        GROUP BY j
                                        ORDER BY COUNT(h) DESC, j.jobId ASC""",
                                Object[].class
                        )
                        .getResultList()
        );
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Job) r[0]).getJobId(), r -> ((Number) r[1]).longValue(),
                            r -> ((Number) r[2]).longValue())
                .containsExactly(
                        tuple("AC_ACCOUNT", 2L, 2L),
                        tuple("ST_CLERK", 2L, 2L),
                        tuple("AC_MGR", 1L, 1L),
                        tuple("AD_ASST", 1L, 1L),
                        tuple("IT_PROG", 1L, 1L),
                        tuple("MK_REP", 1L, 1L),
                        tuple("SA_MAN", 1L, 1L),
                        tuple("SA_REP", 1L, 1L));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var h = cq.from(JobHistory.class);
            final var j = h.join(JobHistory_.job);
            cq.select(cb.array(j, cb.count(h), cb.countDistinct(h.get(JobHistory_.employeeId))))
                    .groupBy(j)
                    .orderBy(cb.desc(cb.count(h)), cb.asc(j.get(Job_.jobId)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Job) r[0]).getJobId(), r -> ((Number) r[1]).longValue(),
                            r -> ((Number) r[2]).longValue())
                .containsExactly(
                        tuple("AC_ACCOUNT", 2L, 2L),
                        tuple("ST_CLERK", 2L, 2L),
                        tuple("AC_MGR", 1L, 1L),
                        tuple("AD_ASST", 1L, 1L),
                        tuple("IT_PROG", 1L, 1L),
                        tuple("MK_REP", 1L, 1L),
                        tuple("SA_MAN", 1L, 1L),
                        tuple("SA_REP", 1L, 1L));
    }

}
