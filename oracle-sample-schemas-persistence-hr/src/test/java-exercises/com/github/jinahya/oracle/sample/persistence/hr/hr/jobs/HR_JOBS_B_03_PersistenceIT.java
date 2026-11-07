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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_JOBS_B_03} of {@code src/test/sql-exercises/JOBS/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_JOBS_B_03: Jobs an offer fits")
@Slf4j
class HR_JOBS_B_03_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT j
                                        FROM Job j
                                        WHERE :salary BETWEEN j.minSalary AND j.maxSalary
                                        ORDER BY j.minSalary ASC, j.jobId ASC""",
                                Job.class
                        )
                        .setParameter("salary", 10000)
                        .getResultList()
        );
        list.forEach(j -> log.debug("{}", j));
        assertThat(list)
                .extracting(Job::getJobId, Job::getJobTitle, Job::getMinSalary, Job::getMaxSalary)
                .containsExactly(
                        tuple("IT_PROG", "Programmer", 4000, 10000),
                        tuple("PR_REP", "Public Relations Representative", 4500, 10500),
                        tuple("SA_REP", "Sales Representative", 6000, 12008),
                        tuple("PU_MAN", "Purchasing Manager", 8000, 15000),
                        tuple("AC_MGR", "Accounting Manager", 8200, 16000),
                        tuple("FI_MGR", "Finance Manager", 8200, 16000),
                        tuple("MK_MAN", "Marketing Manager", 9000, 15000),
                        tuple("SA_MAN", "Sales Manager", 10000, 20080));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Job.class);
            final var j = cq.from(Job.class);
            cq.select(j)
                    .where(cb.between(cb.parameter(Integer.class, "salary"),
                                      j.get(Job_.minSalary), j.get(Job_.maxSalary)))
                    .orderBy(cb.asc(j.get(Job_.minSalary)), cb.asc(j.get(Job_.jobId)));
            return em.createQuery(cq)
                    .setParameter("salary", 10000)
                    .getResultList();
        });
        list.forEach(j -> log.debug("{}", j));
        assertThat(list)
                .extracting(Job::getJobId, Job::getJobTitle, Job::getMinSalary, Job::getMaxSalary)
                .containsExactly(
                        tuple("IT_PROG", "Programmer", 4000, 10000),
                        tuple("PR_REP", "Public Relations Representative", 4500, 10500),
                        tuple("SA_REP", "Sales Representative", 6000, 12008),
                        tuple("PU_MAN", "Purchasing Manager", 8000, 15000),
                        tuple("AC_MGR", "Accounting Manager", 8200, 16000),
                        tuple("FI_MGR", "Finance Manager", 8200, 16000),
                        tuple("MK_MAN", "Marketing Manager", 9000, 15000),
                        tuple("SA_MAN", "Sales Manager", 10000, 20080));
    }

}
