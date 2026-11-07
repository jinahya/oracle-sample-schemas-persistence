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
import com.github.jinahya.oracle.sample.persistence.hr.Location_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_DEPARTMENTS_B_01} of {@code src/test/sql-exercises/DEPARTMENTS/01_basic.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_DEPARTMENTS_B_01: Departments in a building")
@Slf4j
class HR_DEPARTMENTS_B_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT d
                                        FROM Department d
                                        WHERE d.location.locationId = :locationId
                                        ORDER BY d.departmentId ASC""",
                                Department.class
                        )
                        .setParameter("locationId", 1700)
                        .getResultList()
        );
        list.forEach(d -> log.debug("{}", d));
        assertThat(list)
                .extracting(Department::getDepartmentId, Department::getDepartmentName,
                            Department::getManagerId, Department::getLocationId)
                .containsExactly(
                        tuple(10, "Administration", 200, 1700),
                        tuple(30, "Purchasing", 114, 1700),
                        tuple(90, "Executive", 100, 1700),
                        tuple(100, "Finance", 108, 1700),
                        tuple(110, "Accounting", 205, 1700),
                        tuple(120, "Treasury", null, 1700),
                        tuple(130, "Corporate Tax", null, 1700),
                        tuple(140, "Control And Credit", null, 1700),
                        tuple(150, "Shareholder Services", null, 1700),
                        tuple(160, "Benefits", null, 1700),
                        tuple(170, "Manufacturing", null, 1700),
                        tuple(180, "Construction", null, 1700),
                        tuple(190, "Contracting", null, 1700),
                        tuple(200, "Operations", null, 1700),
                        tuple(210, "IT Support", null, 1700),
                        tuple(220, "NOC", null, 1700),
                        tuple(230, "IT Helpdesk", null, 1700),
                        tuple(240, "Government Sales", null, 1700),
                        tuple(250, "Retail Sales", null, 1700),
                        tuple(260, "Recruiting", null, 1700),
                        tuple(270, "Payroll", null, 1700));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Department.class);
            final var d = cq.from(Department.class);
            cq.select(d)
                    .where(cb.equal(d.get(Department_.location).get(Location_.locationId),
                                    cb.parameter(Integer.class, "locationId")))
                    .orderBy(cb.asc(d.get(Department_.departmentId)));
            return em.createQuery(cq)
                    .setParameter("locationId", 1700)
                    .getResultList();
        });
        list.forEach(d -> log.debug("{}", d));
        assertThat(list)
                .extracting(Department::getDepartmentId, Department::getDepartmentName,
                            Department::getManagerId, Department::getLocationId)
                .containsExactly(
                        tuple(10, "Administration", 200, 1700),
                        tuple(30, "Purchasing", 114, 1700),
                        tuple(90, "Executive", 100, 1700),
                        tuple(100, "Finance", 108, 1700),
                        tuple(110, "Accounting", 205, 1700),
                        tuple(120, "Treasury", null, 1700),
                        tuple(130, "Corporate Tax", null, 1700),
                        tuple(140, "Control And Credit", null, 1700),
                        tuple(150, "Shareholder Services", null, 1700),
                        tuple(160, "Benefits", null, 1700),
                        tuple(170, "Manufacturing", null, 1700),
                        tuple(180, "Construction", null, 1700),
                        tuple(190, "Contracting", null, 1700),
                        tuple(200, "Operations", null, 1700),
                        tuple(210, "IT Support", null, 1700),
                        tuple(220, "NOC", null, 1700),
                        tuple(230, "IT Helpdesk", null, 1700),
                        tuple(240, "Government Sales", null, 1700),
                        tuple(250, "Retail Sales", null, 1700),
                        tuple(260, "Recruiting", null, 1700),
                        tuple(270, "Payroll", null, 1700));
    }

}
