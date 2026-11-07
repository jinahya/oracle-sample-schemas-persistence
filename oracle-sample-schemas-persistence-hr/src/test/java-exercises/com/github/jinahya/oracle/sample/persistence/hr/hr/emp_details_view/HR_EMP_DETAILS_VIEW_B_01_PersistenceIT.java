package com.github.jinahya.oracle.sample.persistence.hr.hr.emp_details_view;

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

import com.github.jinahya.oracle.sample.persistence.hr.EmpDetailsView;
import com.github.jinahya.oracle.sample.persistence.hr.EmpDetailsView_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_EMP_DETAILS_VIEW_B_01} of
 * {@code src/test/sql-exercises/EMP_DETAILS_VIEW/01_basic.sql} with JPQL and with the Criteria API,
 * or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_EMP_DETAILS_VIEW_B_01: Staff on a site")
@Slf4j
class HR_EMP_DETAILS_VIEW_B_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT v
                                        FROM EmpDetailsView v
                                        WHERE v.city = :city
                                        ORDER BY v.lastName ASC, v.firstName ASC""",
                                EmpDetailsView.class
                        )
                        .setParameter("city", "Seattle")
                        .getResultList()
        );
        list.forEach(v -> log.debug("{}", v));
        assertThat(list)
                .extracting(EmpDetailsView::getEmployeeId, EmpDetailsView::getFirstName,
                            EmpDetailsView::getLastName, EmpDetailsView::getJobTitle,
                            EmpDetailsView::getDepartmentName)
                .containsExactly(
                        tuple(116, "Shelli", "Baida", "Purchasing Clerk", "Purchasing"),
                        tuple(110, "John", "Chen", "Accountant", "Finance"),
                        tuple(119, "Karen", "Colmenares", "Purchasing Clerk", "Purchasing"),
                        tuple(109, "Daniel", "Faviet", "Accountant", "Finance"),
                        tuple(102, "Lex", "Garcia", "Administration Vice President", "Executive"),
                        tuple(206, "William", "Gietz", "Public Accountant", "Accounting"),
                        tuple(108, "Nancy", "Gruenberg", "Finance Manager", "Finance"),
                        tuple(205, "Shelley", "Higgins", "Accounting Manager", "Accounting"),
                        tuple(118, "Guy", "Himuro", "Purchasing Clerk", "Purchasing"),
                        tuple(115, "Alexander", "Khoo", "Purchasing Clerk", "Purchasing"),
                        tuple(100, "Steven", "King", "President", "Executive"),
                        tuple(114, "Den", "Li", "Purchasing Manager", "Purchasing"),
                        tuple(113, "Luis", "Popp", "Accountant", "Finance"),
                        tuple(111, "Ismael", "Sciarra", "Accountant", "Finance"),
                        tuple(117, "Sigal", "Tobias", "Purchasing Clerk", "Purchasing"),
                        tuple(112, "Jose Manuel", "Urman", "Accountant", "Finance"),
                        tuple(200, "Jennifer", "Whalen", "Administration Assistant",
                              "Administration"),
                        tuple(101, "Neena", "Yang", "Administration Vice President", "Executive"));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(EmpDetailsView.class);
            final var v = cq.from(EmpDetailsView.class);
            cq.select(v)
                    .where(cb.equal(v.get(EmpDetailsView_.city),
                                    cb.parameter(String.class, "city")))
                    .orderBy(cb.asc(v.get(EmpDetailsView_.lastName)),
                             cb.asc(v.get(EmpDetailsView_.firstName)));
            return em.createQuery(cq)
                    .setParameter("city", "Seattle")
                    .getResultList();
        });
        list.forEach(v -> log.debug("{}", v));
        assertThat(list)
                .extracting(EmpDetailsView::getEmployeeId, EmpDetailsView::getFirstName,
                            EmpDetailsView::getLastName, EmpDetailsView::getJobTitle,
                            EmpDetailsView::getDepartmentName)
                .containsExactly(
                        tuple(116, "Shelli", "Baida", "Purchasing Clerk", "Purchasing"),
                        tuple(110, "John", "Chen", "Accountant", "Finance"),
                        tuple(119, "Karen", "Colmenares", "Purchasing Clerk", "Purchasing"),
                        tuple(109, "Daniel", "Faviet", "Accountant", "Finance"),
                        tuple(102, "Lex", "Garcia", "Administration Vice President", "Executive"),
                        tuple(206, "William", "Gietz", "Public Accountant", "Accounting"),
                        tuple(108, "Nancy", "Gruenberg", "Finance Manager", "Finance"),
                        tuple(205, "Shelley", "Higgins", "Accounting Manager", "Accounting"),
                        tuple(118, "Guy", "Himuro", "Purchasing Clerk", "Purchasing"),
                        tuple(115, "Alexander", "Khoo", "Purchasing Clerk", "Purchasing"),
                        tuple(100, "Steven", "King", "President", "Executive"),
                        tuple(114, "Den", "Li", "Purchasing Manager", "Purchasing"),
                        tuple(113, "Luis", "Popp", "Accountant", "Finance"),
                        tuple(111, "Ismael", "Sciarra", "Accountant", "Finance"),
                        tuple(117, "Sigal", "Tobias", "Purchasing Clerk", "Purchasing"),
                        tuple(112, "Jose Manuel", "Urman", "Accountant", "Finance"),
                        tuple(200, "Jennifer", "Whalen", "Administration Assistant",
                              "Administration"),
                        tuple(101, "Neena", "Yang", "Administration Vice President", "Executive"));
    }

}
