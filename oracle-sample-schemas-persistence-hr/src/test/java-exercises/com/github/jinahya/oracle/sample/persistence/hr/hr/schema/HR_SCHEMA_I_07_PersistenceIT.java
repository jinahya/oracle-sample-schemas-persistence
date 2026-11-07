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

import com.github.jinahya.oracle.sample.persistence.hr.Department;
import com.github.jinahya.oracle.sample.persistence.hr.Department_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_SCHEMA_I_07} of {@code src/test/sql-exercises/__/02_intermediate.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_SCHEMA_I_07: Departments with nobody in them")
@Slf4j
class HR_SCHEMA_I_07_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT d
                                        FROM Department d
                                        WHERE d.employees IS EMPTY
                                        ORDER BY d.departmentId ASC""",
                                Department.class
                        )
                        .getResultList()
        );
        list.forEach(d -> log.debug("{}", d));
        assertThat(list)
                .extracting(Department::getDepartmentId, Department::getDepartmentName,
                            Department::getManagerId)
                .containsExactly(
                        tuple(120, "Treasury", null),
                        tuple(130, "Corporate Tax", null),
                        tuple(140, "Control And Credit", null),
                        tuple(150, "Shareholder Services", null),
                        tuple(160, "Benefits", null),
                        tuple(170, "Manufacturing", null),
                        tuple(180, "Construction", null),
                        tuple(190, "Contracting", null),
                        tuple(200, "Operations", null),
                        tuple(210, "IT Support", null),
                        tuple(220, "NOC", null),
                        tuple(230, "IT Helpdesk", null),
                        tuple(240, "Government Sales", null),
                        tuple(250, "Retail Sales", null),
                        tuple(260, "Recruiting", null),
                        tuple(270, "Payroll", null));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Department.class);
            final var d = cq.from(Department.class);
            cq.select(d)
                    .where(cb.isEmpty(d.get(Department_.employees)))
                    .orderBy(cb.asc(d.get(Department_.departmentId)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(d -> log.debug("{}", d));
        assertThat(list)
                .extracting(Department::getDepartmentId, Department::getDepartmentName,
                            Department::getManagerId)
                .containsExactly(
                        tuple(120, "Treasury", null),
                        tuple(130, "Corporate Tax", null),
                        tuple(140, "Control And Credit", null),
                        tuple(150, "Shareholder Services", null),
                        tuple(160, "Benefits", null),
                        tuple(170, "Manufacturing", null),
                        tuple(180, "Construction", null),
                        tuple(190, "Contracting", null),
                        tuple(200, "Operations", null),
                        tuple(210, "IT Support", null),
                        tuple(220, "NOC", null),
                        tuple(230, "IT Helpdesk", null),
                        tuple(240, "Government Sales", null),
                        tuple(250, "Retail Sales", null),
                        tuple(260, "Recruiting", null),
                        tuple(270, "Payroll", null));
    }

}
