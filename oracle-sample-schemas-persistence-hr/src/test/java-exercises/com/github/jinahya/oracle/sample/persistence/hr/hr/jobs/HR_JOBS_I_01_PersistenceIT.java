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
 * Answers {@code HR_JOBS_I_01} of {@code src/test/sql-exercises/JOBS/02_intermediate.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_JOBS_I_01: Widest pay bands")
@Slf4j
class HR_JOBS_I_01_PersistenceIT {

    // Both salaries are Integers, and Integer / Integer truncates in JPQL; '* 1.0' keeps the
    // fraction of the ratio, as Oracle's '/' does.

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT j,
                                               j.maxSalary - j.minSalary,
                                               ROUND(j.maxSalary * 1.0 / j.minSalary, 2)
                                        FROM Job j
                                        WHERE j.maxSalary >= 2 * j.minSalary
                                        ORDER BY ROUND(j.maxSalary * 1.0 / j.minSalary, 2) DESC,
                                                 j.jobId ASC""",
                                Object[].class
                        )
                        .getResultList()
        );
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Job) r[0]).getJobId(), r -> ((Job) r[0]).getJobTitle(),
                            r -> ((Number) r[1]).intValue(), r -> ((Number) r[2]).doubleValue())
                .containsExactly(
                        tuple("IT_PROG", "Programmer", 6000, 2.5d),
                        tuple("ST_CLERK", "Stock Clerk", 2992, 2.49d),
                        tuple("PR_REP", "Public Relations Representative", 6000, 2.33d),
                        tuple("HR_REP", "Human Resources Representative", 5000, 2.25d),
                        tuple("MK_REP", "Marketing Representative", 5000, 2.25d),
                        tuple("PU_CLERK", "Purchasing Clerk", 3000, 2.2d),
                        tuple("SH_CLERK", "Shipping Clerk", 3000, 2.2d),
                        tuple("AC_ACCOUNT", "Public Accountant", 4800, 2.14d),
                        tuple("FI_ACCOUNT", "Accountant", 4800, 2.14d),
                        tuple("SA_MAN", "Sales Manager", 10080, 2.01d),
                        tuple("AD_ASST", "Administration Assistant", 3000, 2.0d),
                        tuple("AD_VP", "Administration Vice President", 15000, 2.0d),
                        tuple("SA_REP", "Sales Representative", 6008, 2.0d));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var j = cq.from(Job.class);
            final var minSalary = j.get(Job_.minSalary);
            final var maxSalary = j.get(Job_.maxSalary);
            final var ratio = cb.round(cb.quot(cb.prod(maxSalary, 1.0d), minSalary), 2);
            cq.select(cb.array(j, cb.diff(maxSalary, minSalary), ratio))
                    .where(cb.ge(maxSalary, cb.prod(2, minSalary)))
                    .orderBy(cb.desc(ratio), cb.asc(j.get(Job_.jobId)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Job) r[0]).getJobId(), r -> ((Job) r[0]).getJobTitle(),
                            r -> ((Number) r[1]).intValue(), r -> ((Number) r[2]).doubleValue())
                .containsExactly(
                        tuple("IT_PROG", "Programmer", 6000, 2.5d),
                        tuple("ST_CLERK", "Stock Clerk", 2992, 2.49d),
                        tuple("PR_REP", "Public Relations Representative", 6000, 2.33d),
                        tuple("HR_REP", "Human Resources Representative", 5000, 2.25d),
                        tuple("MK_REP", "Marketing Representative", 5000, 2.25d),
                        tuple("PU_CLERK", "Purchasing Clerk", 3000, 2.2d),
                        tuple("SH_CLERK", "Shipping Clerk", 3000, 2.2d),
                        tuple("AC_ACCOUNT", "Public Accountant", 4800, 2.14d),
                        tuple("FI_ACCOUNT", "Accountant", 4800, 2.14d),
                        tuple("SA_MAN", "Sales Manager", 10080, 2.01d),
                        tuple("AD_ASST", "Administration Assistant", 3000, 2.0d),
                        tuple("AD_VP", "Administration Vice President", 15000, 2.0d),
                        tuple("SA_REP", "Sales Representative", 6008, 2.0d));
    }

}
