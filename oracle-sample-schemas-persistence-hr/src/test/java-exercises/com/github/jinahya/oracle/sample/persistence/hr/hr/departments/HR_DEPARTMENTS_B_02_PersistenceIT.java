package com.github.jinahya.oracle.sample.persistence.hr.hr.departments;

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

import com.github.jinahya.oracle.sample.persistence.hr.Department;
import com.github.jinahya.oracle.sample.persistence.hr.Department_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_DEPARTMENTS_B_02} of {@code src/test/sql-exercises/DEPARTMENTS/01_basic.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_DEPARTMENTS_B_02: Departments without a manager")
@Slf4j
class HR_DEPARTMENTS_B_02_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT d
                                        FROM Department d
                                        WHERE d.manager IS NULL
                                        ORDER BY d.departmentId ASC""",
                                Department.class
                        )
                        .getResultList()
        );
        list.forEach(d -> log.debug("{}", d));
        assertThat(list)
                .extracting(Department::getDepartmentId, Department::getDepartmentName)
                .containsExactly(
                        tuple(120, "Treasury"),
                        tuple(130, "Corporate Tax"),
                        tuple(140, "Control And Credit"),
                        tuple(150, "Shareholder Services"),
                        tuple(160, "Benefits"),
                        tuple(170, "Manufacturing"),
                        tuple(180, "Construction"),
                        tuple(190, "Contracting"),
                        tuple(200, "Operations"),
                        tuple(210, "IT Support"),
                        tuple(220, "NOC"),
                        tuple(230, "IT Helpdesk"),
                        tuple(240, "Government Sales"),
                        tuple(250, "Retail Sales"),
                        tuple(260, "Recruiting"),
                        tuple(270, "Payroll"));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Department.class);
            final var d = cq.from(Department.class);
            cq.select(d)
                    .where(cb.isNull(d.get(Department_.manager)))
                    .orderBy(cb.asc(d.get(Department_.departmentId)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(d -> log.debug("{}", d));
        assertThat(list)
                .extracting(Department::getDepartmentId, Department::getDepartmentName)
                .containsExactly(
                        tuple(120, "Treasury"),
                        tuple(130, "Corporate Tax"),
                        tuple(140, "Control And Credit"),
                        tuple(150, "Shareholder Services"),
                        tuple(160, "Benefits"),
                        tuple(170, "Manufacturing"),
                        tuple(180, "Construction"),
                        tuple(190, "Contracting"),
                        tuple(200, "Operations"),
                        tuple(210, "IT Support"),
                        tuple(220, "NOC"),
                        tuple(230, "IT Helpdesk"),
                        tuple(240, "Government Sales"),
                        tuple(250, "Retail Sales"),
                        tuple(260, "Recruiting"),
                        tuple(270, "Payroll"));
    }

}
