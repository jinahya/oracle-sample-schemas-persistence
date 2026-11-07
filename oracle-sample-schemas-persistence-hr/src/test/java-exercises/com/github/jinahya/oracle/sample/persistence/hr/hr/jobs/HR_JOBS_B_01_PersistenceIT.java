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
import jakarta.persistence.criteria.Nulls;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_JOBS_B_01} of {@code src/test/sql-exercises/JOBS/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_JOBS_B_01: Salary bands, unset values flagged")
@Slf4j
class HR_JOBS_B_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var byMinSalary = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT j
                                        FROM Job j
                                        ORDER BY j.minSalary ASC NULLS FIRST, j.jobId ASC""",
                                Job.class
                        )
                        .getResultList()
        );
        byMinSalary.forEach(j -> log.debug("by min salary: {}", j));
        assertThat(byMinSalary)
                .extracting(Job::getJobId, Job::getMinSalary, Job::getMaxSalary)
                .containsExactly(
                        tuple("ST_CLERK", 2008, 5000),
                        tuple("PU_CLERK", 2500, 5500),
                        tuple("SH_CLERK", 2500, 5500),
                        tuple("AD_ASST", 3000, 6000),
                        tuple("HR_REP", 4000, 9000),
                        tuple("IT_PROG", 4000, 10000),
                        tuple("MK_REP", 4000, 9000),
                        tuple("AC_ACCOUNT", 4200, 9000),
                        tuple("FI_ACCOUNT", 4200, 9000),
                        tuple("PR_REP", 4500, 10500),
                        tuple("ST_MAN", 5500, 8500),
                        tuple("SA_REP", 6000, 12008),
                        tuple("PU_MAN", 8000, 15000),
                        tuple("AC_MGR", 8200, 16000),
                        tuple("FI_MGR", 8200, 16000),
                        tuple("MK_MAN", 9000, 15000),
                        tuple("SA_MAN", 10000, 20080),
                        tuple("AD_VP", 15000, 30000),
                        tuple("AD_PRES", 20080, 40000));
        final var byMaxSalary = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT j
                                        FROM Job j
                                        ORDER BY j.maxSalary DESC NULLS LAST, j.jobId ASC""",
                                Job.class
                        )
                        .getResultList()
        );
        byMaxSalary.forEach(j -> log.debug("by max salary: {}", j));
        assertThat(byMaxSalary)
                .extracting(Job::getJobId, Job::getMinSalary, Job::getMaxSalary)
                .containsExactly(
                        tuple("AD_PRES", 20080, 40000),
                        tuple("AD_VP", 15000, 30000),
                        tuple("SA_MAN", 10000, 20080),
                        tuple("AC_MGR", 8200, 16000),
                        tuple("FI_MGR", 8200, 16000),
                        tuple("MK_MAN", 9000, 15000),
                        tuple("PU_MAN", 8000, 15000),
                        tuple("SA_REP", 6000, 12008),
                        tuple("PR_REP", 4500, 10500),
                        tuple("IT_PROG", 4000, 10000),
                        tuple("AC_ACCOUNT", 4200, 9000),
                        tuple("FI_ACCOUNT", 4200, 9000),
                        tuple("HR_REP", 4000, 9000),
                        tuple("MK_REP", 4000, 9000),
                        tuple("ST_MAN", 5500, 8500),
                        tuple("AD_ASST", 3000, 6000),
                        tuple("PU_CLERK", 2500, 5500),
                        tuple("SH_CLERK", 2500, 5500),
                        tuple("ST_CLERK", 2008, 5000));
    }

    @Test
    void __CriteriaApi() {
        final var byMinSalary = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Job.class);
            final var j = cq.from(Job.class);
            cq.select(j)
                    .orderBy(cb.asc(j.get(Job_.minSalary), Nulls.FIRST),
                             cb.asc(j.get(Job_.jobId)));
            return em.createQuery(cq)
                    .getResultList();
        });
        byMinSalary.forEach(j -> log.debug("by min salary: {}", j));
        assertThat(byMinSalary)
                .extracting(Job::getJobId, Job::getMinSalary, Job::getMaxSalary)
                .containsExactly(
                        tuple("ST_CLERK", 2008, 5000),
                        tuple("PU_CLERK", 2500, 5500),
                        tuple("SH_CLERK", 2500, 5500),
                        tuple("AD_ASST", 3000, 6000),
                        tuple("HR_REP", 4000, 9000),
                        tuple("IT_PROG", 4000, 10000),
                        tuple("MK_REP", 4000, 9000),
                        tuple("AC_ACCOUNT", 4200, 9000),
                        tuple("FI_ACCOUNT", 4200, 9000),
                        tuple("PR_REP", 4500, 10500),
                        tuple("ST_MAN", 5500, 8500),
                        tuple("SA_REP", 6000, 12008),
                        tuple("PU_MAN", 8000, 15000),
                        tuple("AC_MGR", 8200, 16000),
                        tuple("FI_MGR", 8200, 16000),
                        tuple("MK_MAN", 9000, 15000),
                        tuple("SA_MAN", 10000, 20080),
                        tuple("AD_VP", 15000, 30000),
                        tuple("AD_PRES", 20080, 40000));
        final var byMaxSalary = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Job.class);
            final var j = cq.from(Job.class);
            cq.select(j)
                    .orderBy(cb.desc(j.get(Job_.maxSalary), Nulls.LAST),
                             cb.asc(j.get(Job_.jobId)));
            return em.createQuery(cq)
                    .getResultList();
        });
        byMaxSalary.forEach(j -> log.debug("by max salary: {}", j));
        assertThat(byMaxSalary)
                .extracting(Job::getJobId, Job::getMinSalary, Job::getMaxSalary)
                .containsExactly(
                        tuple("AD_PRES", 20080, 40000),
                        tuple("AD_VP", 15000, 30000),
                        tuple("SA_MAN", 10000, 20080),
                        tuple("AC_MGR", 8200, 16000),
                        tuple("FI_MGR", 8200, 16000),
                        tuple("MK_MAN", 9000, 15000),
                        tuple("PU_MAN", 8000, 15000),
                        tuple("SA_REP", 6000, 12008),
                        tuple("PR_REP", 4500, 10500),
                        tuple("IT_PROG", 4000, 10000),
                        tuple("AC_ACCOUNT", 4200, 9000),
                        tuple("FI_ACCOUNT", 4200, 9000),
                        tuple("HR_REP", 4000, 9000),
                        tuple("MK_REP", 4000, 9000),
                        tuple("ST_MAN", 5500, 8500),
                        tuple("AD_ASST", 3000, 6000),
                        tuple("PU_CLERK", 2500, 5500),
                        tuple("SH_CLERK", 2500, 5500),
                        tuple("ST_CLERK", 2008, 5000));
    }

}
