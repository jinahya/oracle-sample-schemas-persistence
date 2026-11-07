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
 * Answers {@code HR_EMPLOYEES_I_05} of {@code src/test/sql-exercises/EMPLOYEES/02_intermediate.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_EMPLOYEES_I_05: Span of control")
@Slf4j
class HR_EMPLOYEES_I_05_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT m, COUNT(s)
                                        FROM Employee m
                                                 JOIN m.subordinates s
                                        GROUP BY m
                                        ORDER BY COUNT(s) DESC, m.employeeId ASC""",
                                Object[].class
                        )
                        .getResultList()
        );
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Employee) r[0]).getEmployeeId(),
                            r -> ((Employee) r[0]).getFirstName(),
                            r -> ((Employee) r[0]).getLastName(),
                            r -> ((Number) r[1]).longValue())
                .containsExactly(
                        tuple(100, "Steven", "King", 14L),
                        tuple(120, "Matthew", "Weiss", 8L),
                        tuple(121, "Adam", "Fripp", 8L),
                        tuple(122, "Payam", "Kaufling", 8L),
                        tuple(123, "Shanta", "Vollman", 8L),
                        tuple(124, "Kevin", "Mourgos", 8L),
                        tuple(145, "John", "Singh", 6L),
                        tuple(146, "Karen", "Partners", 6L),
                        tuple(147, "Alberto", "Errazuriz", 6L),
                        tuple(148, "Gerald", "Cambrault", 6L),
                        tuple(149, "Eleni", "Zlotkey", 6L),
                        tuple(101, "Neena", "Yang", 5L),
                        tuple(108, "Nancy", "Gruenberg", 5L),
                        tuple(114, "Den", "Li", 5L),
                        tuple(103, "Alexander", "James", 4L),
                        tuple(102, "Lex", "Garcia", 1L),
                        tuple(201, "Michael", "Martinez", 1L),
                        tuple(205, "Shelley", "Higgins", 1L));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var m = cq.from(Employee.class);
            final var s = m.join(Employee_.subordinates);
            cq.select(cb.array(m, cb.count(s)))
                    .groupBy(m)
                    .orderBy(cb.desc(cb.count(s)), cb.asc(m.get(Employee_.employeeId)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Employee) r[0]).getEmployeeId(),
                            r -> ((Employee) r[0]).getFirstName(),
                            r -> ((Employee) r[0]).getLastName(),
                            r -> ((Number) r[1]).longValue())
                .containsExactly(
                        tuple(100, "Steven", "King", 14L),
                        tuple(120, "Matthew", "Weiss", 8L),
                        tuple(121, "Adam", "Fripp", 8L),
                        tuple(122, "Payam", "Kaufling", 8L),
                        tuple(123, "Shanta", "Vollman", 8L),
                        tuple(124, "Kevin", "Mourgos", 8L),
                        tuple(145, "John", "Singh", 6L),
                        tuple(146, "Karen", "Partners", 6L),
                        tuple(147, "Alberto", "Errazuriz", 6L),
                        tuple(148, "Gerald", "Cambrault", 6L),
                        tuple(149, "Eleni", "Zlotkey", 6L),
                        tuple(101, "Neena", "Yang", 5L),
                        tuple(108, "Nancy", "Gruenberg", 5L),
                        tuple(114, "Den", "Li", 5L),
                        tuple(103, "Alexander", "James", 4L),
                        tuple(102, "Lex", "Garcia", 1L),
                        tuple(201, "Michael", "Martinez", 1L),
                        tuple(205, "Shelley", "Higgins", 1L));
    }

}
