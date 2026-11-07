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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_EMPLOYEES_I_01} of {@code src/test/sql-exercises/EMPLOYEES/02_intermediate.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_EMPLOYEES_I_01: Above the company average")
@Slf4j
class HR_EMPLOYEES_I_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT e
                                        FROM Employee e
                                        WHERE e.salary > (SELECT AVG(x.salary) FROM Employee x)
                                        ORDER BY e.salary DESC, e.employeeId ASC""",
                                Employee.class
                        )
                        .getResultList()
        );
        list.forEach(e -> log.debug("{}", e));
        assertThat(list)
                .extracting(Employee::getEmployeeId, Employee::getFirstName, Employee::getLastName,
                            e -> e.getSalary().intValue())
                .containsExactly(
                        tuple(100, "Steven", "King", 24000),
                        tuple(101, "Neena", "Yang", 17000),
                        tuple(102, "Lex", "Garcia", 17000),
                        tuple(145, "John", "Singh", 14000),
                        tuple(146, "Karen", "Partners", 13500),
                        tuple(201, "Michael", "Martinez", 13000),
                        tuple(108, "Nancy", "Gruenberg", 12008),
                        tuple(205, "Shelley", "Higgins", 12008),
                        tuple(147, "Alberto", "Errazuriz", 12000),
                        tuple(168, "Lisa", "Ozer", 11500),
                        tuple(114, "Den", "Li", 11000),
                        tuple(148, "Gerald", "Cambrault", 11000),
                        tuple(174, "Ellen", "Abel", 11000),
                        tuple(149, "Eleni", "Zlotkey", 10500),
                        tuple(162, "Clara", "Vishney", 10500),
                        tuple(150, "Sean", "Tucker", 10000),
                        tuple(156, "Janette", "King", 10000),
                        tuple(169, "Harrison", "Bloom", 10000),
                        tuple(204, "Hermann", "Brown", 10000),
                        tuple(170, "Tayler", "Fox", 9600),
                        tuple(151, "David", "Bernstein", 9500),
                        tuple(157, "Patrick", "Sully", 9500),
                        tuple(163, "Danielle", "Greene", 9500),
                        tuple(103, "Alexander", "James", 9000),
                        tuple(109, "Daniel", "Faviet", 9000),
                        tuple(152, "Peter", "Hall", 9000),
                        tuple(158, "Allan", "McEwen", 9000),
                        tuple(175, "Alyssa", "Hutton", 8800),
                        tuple(176, "Jonathon", "Taylor", 8600),
                        tuple(177, "Jack", "Livingston", 8400),
                        tuple(206, "William", "Gietz", 8300),
                        tuple(110, "John", "Chen", 8200),
                        tuple(121, "Adam", "Fripp", 8200),
                        tuple(120, "Matthew", "Weiss", 8000),
                        tuple(153, "Christopher", "Olsen", 8000),
                        tuple(159, "Lindsey", "Smith", 8000),
                        tuple(122, "Payam", "Kaufling", 7900),
                        tuple(112, "Jose Manuel", "Urman", 7800),
                        tuple(111, "Ismael", "Sciarra", 7700),
                        tuple(154, "Nanette", "Cambrault", 7500),
                        tuple(160, "Louise", "Doran", 7500),
                        tuple(171, "William", "Smith", 7400),
                        tuple(172, "Elizabeth", "Bates", 7300),
                        tuple(164, "Mattea", "Marvins", 7200),
                        tuple(155, "Oliver", "Tuvault", 7000),
                        tuple(161, "Sarath", "Sewall", 7000),
                        tuple(178, "Kimberely", "Grant", 7000),
                        tuple(113, "Luis", "Popp", 6900),
                        tuple(165, "David", "Lee", 6800),
                        tuple(123, "Shanta", "Vollman", 6500),
                        tuple(203, "Susan", "Jacobs", 6500));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Employee.class);
            final var e = cq.from(Employee.class);
            final var sq = cq.subquery(Double.class);
            final var x = sq.from(Employee.class);
            sq.select(cb.avg(x.get(Employee_.salary)));
            cq.select(e)
                    .where(cb.gt(e.get(Employee_.salary), sq))
                    .orderBy(cb.desc(e.get(Employee_.salary)),
                             cb.asc(e.get(Employee_.employeeId)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(e -> log.debug("{}", e));
        assertThat(list)
                .extracting(Employee::getEmployeeId, Employee::getFirstName, Employee::getLastName,
                            e -> e.getSalary().intValue())
                .containsExactly(
                        tuple(100, "Steven", "King", 24000),
                        tuple(101, "Neena", "Yang", 17000),
                        tuple(102, "Lex", "Garcia", 17000),
                        tuple(145, "John", "Singh", 14000),
                        tuple(146, "Karen", "Partners", 13500),
                        tuple(201, "Michael", "Martinez", 13000),
                        tuple(108, "Nancy", "Gruenberg", 12008),
                        tuple(205, "Shelley", "Higgins", 12008),
                        tuple(147, "Alberto", "Errazuriz", 12000),
                        tuple(168, "Lisa", "Ozer", 11500),
                        tuple(114, "Den", "Li", 11000),
                        tuple(148, "Gerald", "Cambrault", 11000),
                        tuple(174, "Ellen", "Abel", 11000),
                        tuple(149, "Eleni", "Zlotkey", 10500),
                        tuple(162, "Clara", "Vishney", 10500),
                        tuple(150, "Sean", "Tucker", 10000),
                        tuple(156, "Janette", "King", 10000),
                        tuple(169, "Harrison", "Bloom", 10000),
                        tuple(204, "Hermann", "Brown", 10000),
                        tuple(170, "Tayler", "Fox", 9600),
                        tuple(151, "David", "Bernstein", 9500),
                        tuple(157, "Patrick", "Sully", 9500),
                        tuple(163, "Danielle", "Greene", 9500),
                        tuple(103, "Alexander", "James", 9000),
                        tuple(109, "Daniel", "Faviet", 9000),
                        tuple(152, "Peter", "Hall", 9000),
                        tuple(158, "Allan", "McEwen", 9000),
                        tuple(175, "Alyssa", "Hutton", 8800),
                        tuple(176, "Jonathon", "Taylor", 8600),
                        tuple(177, "Jack", "Livingston", 8400),
                        tuple(206, "William", "Gietz", 8300),
                        tuple(110, "John", "Chen", 8200),
                        tuple(121, "Adam", "Fripp", 8200),
                        tuple(120, "Matthew", "Weiss", 8000),
                        tuple(153, "Christopher", "Olsen", 8000),
                        tuple(159, "Lindsey", "Smith", 8000),
                        tuple(122, "Payam", "Kaufling", 7900),
                        tuple(112, "Jose Manuel", "Urman", 7800),
                        tuple(111, "Ismael", "Sciarra", 7700),
                        tuple(154, "Nanette", "Cambrault", 7500),
                        tuple(160, "Louise", "Doran", 7500),
                        tuple(171, "William", "Smith", 7400),
                        tuple(172, "Elizabeth", "Bates", 7300),
                        tuple(164, "Mattea", "Marvins", 7200),
                        tuple(155, "Oliver", "Tuvault", 7000),
                        tuple(161, "Sarath", "Sewall", 7000),
                        tuple(178, "Kimberely", "Grant", 7000),
                        tuple(113, "Luis", "Popp", 6900),
                        tuple(165, "David", "Lee", 6800),
                        tuple(123, "Shanta", "Vollman", 6500),
                        tuple(203, "Susan", "Jacobs", 6500));
    }

}
