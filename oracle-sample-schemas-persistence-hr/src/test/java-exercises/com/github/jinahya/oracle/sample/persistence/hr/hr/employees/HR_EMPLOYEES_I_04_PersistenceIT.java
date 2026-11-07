package com.github.jinahya.oracle.sample.persistence.hr.hr.employees;

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
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_EMPLOYEES_I_04} of {@code src/test/sql-exercises/EMPLOYEES/02_intermediate.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_EMPLOYEES_I_04: Earning more than the boss")
@Slf4j
class HR_EMPLOYEES_I_04_PersistenceIT {

    // The SQL has no ORDER BY, so neither do the queries, and the rows are compared in any order.

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT e, m
                                        FROM Employee e
                                                 JOIN e.manager m
                                        WHERE e.salary > m.salary""",
                                Object[].class
                        )
                        .getResultList()
        );
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Employee) r[0]).getEmployeeId(),
                            r -> ((Employee) r[0]).getLastName(),
                            r -> ((Employee) r[0]).getSalary().intValue(),
                            r -> ((Employee) r[1]).getEmployeeId(),
                            r -> ((Employee) r[1]).getLastName(),
                            r -> ((Employee) r[1]).getSalary().intValue())
                .containsExactlyInAnyOrder(
                        tuple(168, "Ozer", 11500, 148, "Cambrault", 11000),
                        tuple(174, "Abel", 11000, 149, "Zlotkey", 10500));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var e = cq.from(Employee.class);
            final var m = e.join(Employee_.manager);
            cq.select(cb.array(e, m))
                    .where(cb.gt(e.get(Employee_.salary), m.get(Employee_.salary)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Employee) r[0]).getEmployeeId(),
                            r -> ((Employee) r[0]).getLastName(),
                            r -> ((Employee) r[0]).getSalary().intValue(),
                            r -> ((Employee) r[1]).getEmployeeId(),
                            r -> ((Employee) r[1]).getLastName(),
                            r -> ((Employee) r[1]).getSalary().intValue())
                .containsExactlyInAnyOrder(
                        tuple(168, "Ozer", 11500, 148, "Cambrault", 11000),
                        tuple(174, "Abel", 11000, 149, "Zlotkey", 10500));
    }

}
