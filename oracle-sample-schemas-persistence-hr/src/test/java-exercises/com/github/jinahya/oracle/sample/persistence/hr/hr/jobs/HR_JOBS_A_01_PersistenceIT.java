package com.github.jinahya.oracle.sample.persistence.hr.hr.jobs;

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
import com.github.jinahya.oracle.sample.persistence.hr.Job_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_JOBS_A_01} of {@code src/test/sql-exercises/JOBS/03_advanced.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_JOBS_A_01: Bands inside another band")
@Slf4j
class HR_JOBS_A_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT i, o
                                        FROM Job i,
                                             Job o
                                        WHERE o <> i
                                          AND o.minSalary <= i.minSalary
                                          AND i.maxSalary <= o.maxSalary
                                        ORDER BY i.jobId ASC, o.jobId ASC""",
                                Object[].class
                        )
                        .getResultList()
        );
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Job) r[0]).getJobId(),
                            r -> ((Job) r[0]).getMinSalary(),
                            r -> ((Job) r[0]).getMaxSalary(),
                            r -> ((Job) r[1]).getJobId(),
                            r -> ((Job) r[1]).getMinSalary(),
                            r -> ((Job) r[1]).getMaxSalary())
                .containsExactly(
                        tuple("AC_ACCOUNT", 4200, 9000, "FI_ACCOUNT", 4200, 9000),
                        tuple("AC_ACCOUNT", 4200, 9000, "HR_REP", 4000, 9000),
                        tuple("AC_ACCOUNT", 4200, 9000, "IT_PROG", 4000, 10000),
                        tuple("AC_ACCOUNT", 4200, 9000, "MK_REP", 4000, 9000),
                        tuple("AC_MGR", 8200, 16000, "FI_MGR", 8200, 16000),
                        tuple("FI_ACCOUNT", 4200, 9000, "AC_ACCOUNT", 4200, 9000),
                        tuple("FI_ACCOUNT", 4200, 9000, "HR_REP", 4000, 9000),
                        tuple("FI_ACCOUNT", 4200, 9000, "IT_PROG", 4000, 10000),
                        tuple("FI_ACCOUNT", 4200, 9000, "MK_REP", 4000, 9000),
                        tuple("FI_MGR", 8200, 16000, "AC_MGR", 8200, 16000),
                        tuple("HR_REP", 4000, 9000, "IT_PROG", 4000, 10000),
                        tuple("HR_REP", 4000, 9000, "MK_REP", 4000, 9000),
                        tuple("MK_MAN", 9000, 15000, "AC_MGR", 8200, 16000),
                        tuple("MK_MAN", 9000, 15000, "FI_MGR", 8200, 16000),
                        tuple("MK_MAN", 9000, 15000, "PU_MAN", 8000, 15000),
                        tuple("MK_REP", 4000, 9000, "HR_REP", 4000, 9000),
                        tuple("MK_REP", 4000, 9000, "IT_PROG", 4000, 10000),
                        tuple("PU_CLERK", 2500, 5500, "SH_CLERK", 2500, 5500),
                        tuple("SH_CLERK", 2500, 5500, "PU_CLERK", 2500, 5500),
                        tuple("ST_MAN", 5500, 8500, "AC_ACCOUNT", 4200, 9000),
                        tuple("ST_MAN", 5500, 8500, "FI_ACCOUNT", 4200, 9000),
                        tuple("ST_MAN", 5500, 8500, "HR_REP", 4000, 9000),
                        tuple("ST_MAN", 5500, 8500, "IT_PROG", 4000, 10000),
                        tuple("ST_MAN", 5500, 8500, "MK_REP", 4000, 9000),
                        tuple("ST_MAN", 5500, 8500, "PR_REP", 4500, 10500));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var i = cq.from(Job.class);
            final var o = cq.from(Job.class);
            cq.select(cb.array(i, o))
                    .where(cb.notEqual(o, i),
                           cb.le(o.get(Job_.minSalary), i.get(Job_.minSalary)),
                           cb.le(i.get(Job_.maxSalary), o.get(Job_.maxSalary)))
                    .orderBy(cb.asc(i.get(Job_.jobId)), cb.asc(o.get(Job_.jobId)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Job) r[0]).getJobId(),
                            r -> ((Job) r[0]).getMinSalary(),
                            r -> ((Job) r[0]).getMaxSalary(),
                            r -> ((Job) r[1]).getJobId(),
                            r -> ((Job) r[1]).getMinSalary(),
                            r -> ((Job) r[1]).getMaxSalary())
                .containsExactly(
                        tuple("AC_ACCOUNT", 4200, 9000, "FI_ACCOUNT", 4200, 9000),
                        tuple("AC_ACCOUNT", 4200, 9000, "HR_REP", 4000, 9000),
                        tuple("AC_ACCOUNT", 4200, 9000, "IT_PROG", 4000, 10000),
                        tuple("AC_ACCOUNT", 4200, 9000, "MK_REP", 4000, 9000),
                        tuple("AC_MGR", 8200, 16000, "FI_MGR", 8200, 16000),
                        tuple("FI_ACCOUNT", 4200, 9000, "AC_ACCOUNT", 4200, 9000),
                        tuple("FI_ACCOUNT", 4200, 9000, "HR_REP", 4000, 9000),
                        tuple("FI_ACCOUNT", 4200, 9000, "IT_PROG", 4000, 10000),
                        tuple("FI_ACCOUNT", 4200, 9000, "MK_REP", 4000, 9000),
                        tuple("FI_MGR", 8200, 16000, "AC_MGR", 8200, 16000),
                        tuple("HR_REP", 4000, 9000, "IT_PROG", 4000, 10000),
                        tuple("HR_REP", 4000, 9000, "MK_REP", 4000, 9000),
                        tuple("MK_MAN", 9000, 15000, "AC_MGR", 8200, 16000),
                        tuple("MK_MAN", 9000, 15000, "FI_MGR", 8200, 16000),
                        tuple("MK_MAN", 9000, 15000, "PU_MAN", 8000, 15000),
                        tuple("MK_REP", 4000, 9000, "HR_REP", 4000, 9000),
                        tuple("MK_REP", 4000, 9000, "IT_PROG", 4000, 10000),
                        tuple("PU_CLERK", 2500, 5500, "SH_CLERK", 2500, 5500),
                        tuple("SH_CLERK", 2500, 5500, "PU_CLERK", 2500, 5500),
                        tuple("ST_MAN", 5500, 8500, "AC_ACCOUNT", 4200, 9000),
                        tuple("ST_MAN", 5500, 8500, "FI_ACCOUNT", 4200, 9000),
                        tuple("ST_MAN", 5500, 8500, "HR_REP", 4000, 9000),
                        tuple("ST_MAN", 5500, 8500, "IT_PROG", 4000, 10000),
                        tuple("ST_MAN", 5500, 8500, "MK_REP", 4000, 9000),
                        tuple("ST_MAN", 5500, 8500, "PR_REP", 4500, 10500));
    }

}
