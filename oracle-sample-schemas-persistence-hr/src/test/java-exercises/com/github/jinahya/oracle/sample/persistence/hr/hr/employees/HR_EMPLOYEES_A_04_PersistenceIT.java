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

import com.github.jinahya.oracle.sample.persistence.hr.Department_;
import com.github.jinahya.oracle.sample.persistence.hr.Employee;
import com.github.jinahya.oracle.sample.persistence.hr.Employee_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_EMPLOYEES_A_04} of {@code src/test/sql-exercises/EMPLOYEES/03_advanced.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_EMPLOYEES_A_04: Out-earning a whole department")
@Slf4j
class HR_EMPLOYEES_A_04_PersistenceIT {

    // ORDER BY SALARY DESC leaves the order of equal salaries to the database, so the rows are
    // compared in any order, and their salaries are checked to be in descending order.

    @Test
    void __QueryLanguage() {
        final var all = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT e
                                        FROM Employee e
                                        WHERE e.salary > ALL (
                                            SELECT x.salary
                                            FROM Employee x
                                            WHERE x.department.departmentId = :departmentId)
                                        ORDER BY e.salary DESC""",
                                Employee.class
                        )
                        .setParameter("departmentId", 20)
                        .getResultList()
        );
        all.forEach(e -> log.debug("all: {}", e));
        assertThat(all)
                .extracting(Employee::getSalary)
                .isSortedAccordingTo(Comparator.reverseOrder());
        assertThat(all)
                .extracting(Employee::getEmployeeId, Employee::getLastName,
                            e -> e.getSalary().intValue())
                .containsExactlyInAnyOrder(
                        tuple(100, "King", 24000),
                        tuple(102, "Garcia", 17000),
                        tuple(101, "Yang", 17000),
                        tuple(145, "Singh", 14000),
                        tuple(146, "Partners", 13500));
        final var any = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT e
                                        FROM Employee e
                                        WHERE e.salary > ANY (
                                            SELECT x.salary
                                            FROM Employee x
                                            WHERE x.department.departmentId = :departmentId)
                                        ORDER BY e.salary DESC""",
                                Employee.class
                        )
                        .setParameter("departmentId", 20)
                        .getResultList()
        );
        any.forEach(e -> log.debug("any: {}", e));
        assertThat(any)
                .extracting(Employee::getSalary)
                .isSortedAccordingTo(Comparator.reverseOrder());
        assertThat(any)
                .extracting(Employee::getEmployeeId, Employee::getLastName,
                            e -> e.getSalary().intValue())
                .containsExactlyInAnyOrder(
                        tuple(100, "King", 24000),
                        tuple(101, "Yang", 17000),
                        tuple(102, "Garcia", 17000),
                        tuple(145, "Singh", 14000),
                        tuple(146, "Partners", 13500),
                        tuple(201, "Martinez", 13000),
                        tuple(108, "Gruenberg", 12008),
                        tuple(205, "Higgins", 12008),
                        tuple(147, "Errazuriz", 12000),
                        tuple(168, "Ozer", 11500),
                        tuple(114, "Li", 11000),
                        tuple(148, "Cambrault", 11000),
                        tuple(174, "Abel", 11000),
                        tuple(149, "Zlotkey", 10500),
                        tuple(162, "Vishney", 10500),
                        tuple(156, "King", 10000),
                        tuple(150, "Tucker", 10000),
                        tuple(204, "Brown", 10000),
                        tuple(169, "Bloom", 10000),
                        tuple(170, "Fox", 9600),
                        tuple(163, "Greene", 9500),
                        tuple(151, "Bernstein", 9500),
                        tuple(157, "Sully", 9500),
                        tuple(152, "Hall", 9000),
                        tuple(103, "James", 9000),
                        tuple(158, "McEwen", 9000),
                        tuple(109, "Faviet", 9000),
                        tuple(175, "Hutton", 8800),
                        tuple(176, "Taylor", 8600),
                        tuple(177, "Livingston", 8400),
                        tuple(206, "Gietz", 8300),
                        tuple(121, "Fripp", 8200),
                        tuple(110, "Chen", 8200),
                        tuple(120, "Weiss", 8000),
                        tuple(159, "Smith", 8000),
                        tuple(153, "Olsen", 8000),
                        tuple(122, "Kaufling", 7900),
                        tuple(112, "Urman", 7800),
                        tuple(111, "Sciarra", 7700),
                        tuple(160, "Doran", 7500),
                        tuple(154, "Cambrault", 7500),
                        tuple(171, "Smith", 7400),
                        tuple(172, "Bates", 7300),
                        tuple(164, "Marvins", 7200),
                        tuple(155, "Tuvault", 7000),
                        tuple(178, "Grant", 7000),
                        tuple(161, "Sewall", 7000),
                        tuple(113, "Popp", 6900),
                        tuple(165, "Lee", 6800),
                        tuple(203, "Jacobs", 6500),
                        tuple(123, "Vollman", 6500),
                        tuple(166, "Ande", 6400),
                        tuple(179, "Johnson", 6200),
                        tuple(167, "Banda", 6200),
                        tuple(173, "Kumar", 6100));
    }

    @Test
    void __CriteriaApi() {
        final var all = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Employee.class);
            final var e = cq.from(Employee.class);
            final var sq = cq.subquery(BigDecimal.class);
            final var x = sq.from(Employee.class);
            sq.select(x.get(Employee_.salary))
                    .where(cb.equal(x.get(Employee_.department).get(Department_.departmentId),
                                    cb.parameter(Integer.class, "departmentId")));
            cq.select(e)
                    .where(cb.gt(e.get(Employee_.salary), cb.all(sq)))
                    .orderBy(cb.desc(e.get(Employee_.salary)));
            return em.createQuery(cq)
                    .setParameter("departmentId", 20)
                    .getResultList();
        });
        all.forEach(e -> log.debug("all: {}", e));
        assertThat(all)
                .extracting(Employee::getSalary)
                .isSortedAccordingTo(Comparator.reverseOrder());
        assertThat(all)
                .extracting(Employee::getEmployeeId, Employee::getLastName,
                            e -> e.getSalary().intValue())
                .containsExactlyInAnyOrder(
                        tuple(100, "King", 24000),
                        tuple(102, "Garcia", 17000),
                        tuple(101, "Yang", 17000),
                        tuple(145, "Singh", 14000),
                        tuple(146, "Partners", 13500));
        final var any = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Employee.class);
            final var e = cq.from(Employee.class);
            final var sq = cq.subquery(BigDecimal.class);
            final var x = sq.from(Employee.class);
            sq.select(x.get(Employee_.salary))
                    .where(cb.equal(x.get(Employee_.department).get(Department_.departmentId),
                                    cb.parameter(Integer.class, "departmentId")));
            cq.select(e)
                    .where(cb.gt(e.get(Employee_.salary), cb.any(sq)))
                    .orderBy(cb.desc(e.get(Employee_.salary)));
            return em.createQuery(cq)
                    .setParameter("departmentId", 20)
                    .getResultList();
        });
        any.forEach(e -> log.debug("any: {}", e));
        assertThat(any)
                .extracting(Employee::getSalary)
                .isSortedAccordingTo(Comparator.reverseOrder());
        assertThat(any)
                .extracting(Employee::getEmployeeId, Employee::getLastName,
                            e -> e.getSalary().intValue())
                .containsExactlyInAnyOrder(
                        tuple(100, "King", 24000),
                        tuple(101, "Yang", 17000),
                        tuple(102, "Garcia", 17000),
                        tuple(145, "Singh", 14000),
                        tuple(146, "Partners", 13500),
                        tuple(201, "Martinez", 13000),
                        tuple(108, "Gruenberg", 12008),
                        tuple(205, "Higgins", 12008),
                        tuple(147, "Errazuriz", 12000),
                        tuple(168, "Ozer", 11500),
                        tuple(114, "Li", 11000),
                        tuple(148, "Cambrault", 11000),
                        tuple(174, "Abel", 11000),
                        tuple(149, "Zlotkey", 10500),
                        tuple(162, "Vishney", 10500),
                        tuple(156, "King", 10000),
                        tuple(150, "Tucker", 10000),
                        tuple(204, "Brown", 10000),
                        tuple(169, "Bloom", 10000),
                        tuple(170, "Fox", 9600),
                        tuple(163, "Greene", 9500),
                        tuple(151, "Bernstein", 9500),
                        tuple(157, "Sully", 9500),
                        tuple(152, "Hall", 9000),
                        tuple(103, "James", 9000),
                        tuple(158, "McEwen", 9000),
                        tuple(109, "Faviet", 9000),
                        tuple(175, "Hutton", 8800),
                        tuple(176, "Taylor", 8600),
                        tuple(177, "Livingston", 8400),
                        tuple(206, "Gietz", 8300),
                        tuple(121, "Fripp", 8200),
                        tuple(110, "Chen", 8200),
                        tuple(120, "Weiss", 8000),
                        tuple(159, "Smith", 8000),
                        tuple(153, "Olsen", 8000),
                        tuple(122, "Kaufling", 7900),
                        tuple(112, "Urman", 7800),
                        tuple(111, "Sciarra", 7700),
                        tuple(160, "Doran", 7500),
                        tuple(154, "Cambrault", 7500),
                        tuple(171, "Smith", 7400),
                        tuple(172, "Bates", 7300),
                        tuple(164, "Marvins", 7200),
                        tuple(155, "Tuvault", 7000),
                        tuple(178, "Grant", 7000),
                        tuple(161, "Sewall", 7000),
                        tuple(113, "Popp", 6900),
                        tuple(165, "Lee", 6800),
                        tuple(203, "Jacobs", 6500),
                        tuple(123, "Vollman", 6500),
                        tuple(166, "Ande", 6400),
                        tuple(179, "Johnson", 6200),
                        tuple(167, "Banda", 6200),
                        tuple(173, "Kumar", 6100));
    }

}
