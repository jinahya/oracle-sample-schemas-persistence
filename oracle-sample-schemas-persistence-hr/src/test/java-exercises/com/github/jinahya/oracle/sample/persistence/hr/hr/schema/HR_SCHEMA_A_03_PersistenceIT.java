package com.github.jinahya.oracle.sample.persistence.hr.hr.schema;

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
import com.github.jinahya.oracle.sample.persistence.hr.JobHistory_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_SCHEMA_A_03} of {@code src/test/sql-exercises/__/03_advanced.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_SCHEMA_A_03: Back in an earlier job")
@Slf4j
class HR_SCHEMA_A_03_PersistenceIT {

    // An employee whose current job is the job of one of their own earlier positions is in the
    // INTERSECT of the SQL; the join finds the same pairs, and DISTINCT keeps an employee who held
    // the job more than once to one row.

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT DISTINCT e
                                        FROM Employee e
                                                 JOIN e.jobHistories h
                                        WHERE h.job = e.job
                                        ORDER BY e.employeeId ASC""",
                                Employee.class
                        )
                        .getResultList()
        );
        list.forEach(e -> log.debug("{}", e));
        assertThat(list)
                .extracting(Employee::getEmployeeId, Employee::getJobId)
                .containsExactly(
                        tuple(176, "SA_REP"),
                        tuple(200, "AD_ASST"));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Employee.class);
            final var e = cq.from(Employee.class);
            final var h = e.join(Employee_.jobHistories);
            cq.select(e)
                    .distinct(true)
                    .where(cb.equal(h.get(JobHistory_.job), e.get(Employee_.job)))
                    .orderBy(cb.asc(e.get(Employee_.employeeId)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(e -> log.debug("{}", e));
        assertThat(list)
                .extracting(Employee::getEmployeeId, Employee::getJobId)
                .containsExactly(
                        tuple(176, "SA_REP"),
                        tuple(200, "AD_ASST"));
    }

}
