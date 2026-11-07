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
import jakarta.persistence.criteria.JoinType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_SCHEMA_I_01} of {@code src/test/sql-exercises/__/02_intermediate.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_SCHEMA_I_01: Headcount per department")
@Slf4j
class HR_SCHEMA_I_01_PersistenceIT {

    // COUNT(e), not COUNT(d): a department with nobody still makes one row of the outer join, with
    // a null employee, which COUNT(e) does not count.

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT d, COUNT(e)
                                        FROM Department d
                                                 LEFT JOIN d.employees e
                                        GROUP BY d
                                        ORDER BY COUNT(e) DESC, d.departmentId ASC""",
                                Object[].class
                        )
                        .getResultList()
        );
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Department) r[0]).getDepartmentId(),
                            r -> ((Department) r[0]).getDepartmentName(),
                            r -> ((Number) r[1]).longValue())
                .containsExactly(
                        tuple(50, "Shipping", 45L),
                        tuple(80, "Sales", 34L),
                        tuple(30, "Purchasing", 6L),
                        tuple(100, "Finance", 6L),
                        tuple(60, "IT", 5L),
                        tuple(90, "Executive", 3L),
                        tuple(20, "Marketing", 2L),
                        tuple(110, "Accounting", 2L),
                        tuple(10, "Administration", 1L),
                        tuple(40, "Human Resources", 1L),
                        tuple(70, "Public Relations", 1L),
                        tuple(120, "Treasury", 0L),
                        tuple(130, "Corporate Tax", 0L),
                        tuple(140, "Control And Credit", 0L),
                        tuple(150, "Shareholder Services", 0L),
                        tuple(160, "Benefits", 0L),
                        tuple(170, "Manufacturing", 0L),
                        tuple(180, "Construction", 0L),
                        tuple(190, "Contracting", 0L),
                        tuple(200, "Operations", 0L),
                        tuple(210, "IT Support", 0L),
                        tuple(220, "NOC", 0L),
                        tuple(230, "IT Helpdesk", 0L),
                        tuple(240, "Government Sales", 0L),
                        tuple(250, "Retail Sales", 0L),
                        tuple(260, "Recruiting", 0L),
                        tuple(270, "Payroll", 0L));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var d = cq.from(Department.class);
            final var e = d.join(Department_.employees, JoinType.LEFT);
            cq.select(cb.array(d, cb.count(e)))
                    .groupBy(d)
                    .orderBy(cb.desc(cb.count(e)), cb.asc(d.get(Department_.departmentId)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Department) r[0]).getDepartmentId(),
                            r -> ((Department) r[0]).getDepartmentName(),
                            r -> ((Number) r[1]).longValue())
                .containsExactly(
                        tuple(50, "Shipping", 45L),
                        tuple(80, "Sales", 34L),
                        tuple(30, "Purchasing", 6L),
                        tuple(100, "Finance", 6L),
                        tuple(60, "IT", 5L),
                        tuple(90, "Executive", 3L),
                        tuple(20, "Marketing", 2L),
                        tuple(110, "Accounting", 2L),
                        tuple(10, "Administration", 1L),
                        tuple(40, "Human Resources", 1L),
                        tuple(70, "Public Relations", 1L),
                        tuple(120, "Treasury", 0L),
                        tuple(130, "Corporate Tax", 0L),
                        tuple(140, "Control And Credit", 0L),
                        tuple(150, "Shareholder Services", 0L),
                        tuple(160, "Benefits", 0L),
                        tuple(170, "Manufacturing", 0L),
                        tuple(180, "Construction", 0L),
                        tuple(190, "Contracting", 0L),
                        tuple(200, "Operations", 0L),
                        tuple(210, "IT Support", 0L),
                        tuple(220, "NOC", 0L),
                        tuple(230, "IT Helpdesk", 0L),
                        tuple(240, "Government Sales", 0L),
                        tuple(250, "Retail Sales", 0L),
                        tuple(260, "Recruiting", 0L),
                        tuple(270, "Payroll", 0L));
    }

}
