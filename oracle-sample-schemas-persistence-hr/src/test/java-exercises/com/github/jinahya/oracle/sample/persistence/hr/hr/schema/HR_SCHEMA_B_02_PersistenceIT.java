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
import com.github.jinahya.oracle.sample.persistence.hr.Job;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_SCHEMA_B_02} of {@code src/test/sql-exercises/__/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_SCHEMA_B_02: An employee's job title")
@Slf4j
class HR_SCHEMA_B_02_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT e, j
                                        FROM Employee e
                                                 JOIN e.job j
                                        WHERE e.employeeId = :employeeId""",
                                Object[].class
                        )
                        .setParameter("employeeId", 100)
                        .getResultList()
        );
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Employee) r[0]).getEmployeeId(),
                            r -> ((Employee) r[0]).getFirstName(),
                            r -> ((Employee) r[0]).getLastName(),
                            r -> ((Job) r[1]).getJobTitle())
                .containsExactly(tuple(100, "Steven", "King", "President"));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var e = cq.from(Employee.class);
            final var j = e.join(Employee_.job);
            cq.select(cb.array(e, j))
                    .where(cb.equal(e.get(Employee_.employeeId),
                                    cb.parameter(Integer.class, "employeeId")));
            return em.createQuery(cq)
                    .setParameter("employeeId", 100)
                    .getResultList();
        });
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Employee) r[0]).getEmployeeId(),
                            r -> ((Employee) r[0]).getFirstName(),
                            r -> ((Employee) r[0]).getLastName(),
                            r -> ((Job) r[1]).getJobTitle())
                .containsExactly(tuple(100, "Steven", "King", "President"));
    }

}
