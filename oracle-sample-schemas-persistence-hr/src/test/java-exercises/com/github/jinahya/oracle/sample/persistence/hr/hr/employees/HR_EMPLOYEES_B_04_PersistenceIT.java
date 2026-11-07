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
 * Answers {@code HR_EMPLOYEES_B_04} of {@code src/test/sql-exercises/EMPLOYEES/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_EMPLOYEES_B_04: Who is on commission")
@Slf4j
class HR_EMPLOYEES_B_04_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT e
                                        FROM Employee e
                                        WHERE e.commissionPct IS NOT NULL
                                        ORDER BY e.commissionPct DESC, e.lastName ASC""",
                                Employee.class
                        )
                        .getResultList()
        );
        list.forEach(e -> log.debug("{}", e));
        assertThat(list)
                .extracting(Employee::getEmployeeId, Employee::getFirstName, Employee::getLastName,
                            e -> e.getCommissionPct().doubleValue())
                .containsExactly(
                        tuple(145, "John", "Singh", 0.4d),
                        tuple(156, "Janette", "King", 0.35d),
                        tuple(158, "Allan", "McEwen", 0.35d),
                        tuple(157, "Patrick", "Sully", 0.35d),
                        tuple(174, "Ellen", "Abel", 0.3d),
                        tuple(148, "Gerald", "Cambrault", 0.3d),
                        tuple(160, "Louise", "Doran", 0.3d),
                        tuple(147, "Alberto", "Errazuriz", 0.3d),
                        tuple(146, "Karen", "Partners", 0.3d),
                        tuple(159, "Lindsey", "Smith", 0.3d),
                        tuple(150, "Sean", "Tucker", 0.3d),
                        tuple(151, "David", "Bernstein", 0.25d),
                        tuple(152, "Peter", "Hall", 0.25d),
                        tuple(175, "Alyssa", "Hutton", 0.25d),
                        tuple(168, "Lisa", "Ozer", 0.25d),
                        tuple(161, "Sarath", "Sewall", 0.25d),
                        tuple(162, "Clara", "Vishney", 0.25d),
                        tuple(169, "Harrison", "Bloom", 0.2d),
                        tuple(154, "Nanette", "Cambrault", 0.2d),
                        tuple(170, "Tayler", "Fox", 0.2d),
                        tuple(177, "Jack", "Livingston", 0.2d),
                        tuple(153, "Christopher", "Olsen", 0.2d),
                        tuple(176, "Jonathon", "Taylor", 0.2d),
                        tuple(149, "Eleni", "Zlotkey", 0.2d),
                        tuple(172, "Elizabeth", "Bates", 0.15d),
                        tuple(178, "Kimberely", "Grant", 0.15d),
                        tuple(163, "Danielle", "Greene", 0.15d),
                        tuple(171, "William", "Smith", 0.15d),
                        tuple(155, "Oliver", "Tuvault", 0.15d),
                        tuple(166, "Sundar", "Ande", 0.1d),
                        tuple(167, "Amit", "Banda", 0.1d),
                        tuple(179, "Charles", "Johnson", 0.1d),
                        tuple(173, "Sundita", "Kumar", 0.1d),
                        tuple(165, "David", "Lee", 0.1d),
                        tuple(164, "Mattea", "Marvins", 0.1d));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Employee.class);
            final var e = cq.from(Employee.class);
            cq.select(e)
                    .where(cb.isNotNull(e.get(Employee_.commissionPct)))
                    .orderBy(cb.desc(e.get(Employee_.commissionPct)),
                             cb.asc(e.get(Employee_.lastName)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(e -> log.debug("{}", e));
        assertThat(list)
                .extracting(Employee::getEmployeeId, Employee::getFirstName, Employee::getLastName,
                            e -> e.getCommissionPct().doubleValue())
                .containsExactly(
                        tuple(145, "John", "Singh", 0.4d),
                        tuple(156, "Janette", "King", 0.35d),
                        tuple(158, "Allan", "McEwen", 0.35d),
                        tuple(157, "Patrick", "Sully", 0.35d),
                        tuple(174, "Ellen", "Abel", 0.3d),
                        tuple(148, "Gerald", "Cambrault", 0.3d),
                        tuple(160, "Louise", "Doran", 0.3d),
                        tuple(147, "Alberto", "Errazuriz", 0.3d),
                        tuple(146, "Karen", "Partners", 0.3d),
                        tuple(159, "Lindsey", "Smith", 0.3d),
                        tuple(150, "Sean", "Tucker", 0.3d),
                        tuple(151, "David", "Bernstein", 0.25d),
                        tuple(152, "Peter", "Hall", 0.25d),
                        tuple(175, "Alyssa", "Hutton", 0.25d),
                        tuple(168, "Lisa", "Ozer", 0.25d),
                        tuple(161, "Sarath", "Sewall", 0.25d),
                        tuple(162, "Clara", "Vishney", 0.25d),
                        tuple(169, "Harrison", "Bloom", 0.2d),
                        tuple(154, "Nanette", "Cambrault", 0.2d),
                        tuple(170, "Tayler", "Fox", 0.2d),
                        tuple(177, "Jack", "Livingston", 0.2d),
                        tuple(153, "Christopher", "Olsen", 0.2d),
                        tuple(176, "Jonathon", "Taylor", 0.2d),
                        tuple(149, "Eleni", "Zlotkey", 0.2d),
                        tuple(172, "Elizabeth", "Bates", 0.15d),
                        tuple(178, "Kimberely", "Grant", 0.15d),
                        tuple(163, "Danielle", "Greene", 0.15d),
                        tuple(171, "William", "Smith", 0.15d),
                        tuple(155, "Oliver", "Tuvault", 0.15d),
                        tuple(166, "Sundar", "Ande", 0.1d),
                        tuple(167, "Amit", "Banda", 0.1d),
                        tuple(179, "Charles", "Johnson", 0.1d),
                        tuple(173, "Sundita", "Kumar", 0.1d),
                        tuple(165, "David", "Lee", 0.1d),
                        tuple(164, "Mattea", "Marvins", 0.1d));
    }

}
