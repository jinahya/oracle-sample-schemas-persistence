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
import jakarta.persistence.criteria.Nulls;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_EMPLOYEES_B_02} of {@code src/test/sql-exercises/EMPLOYEES/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_EMPLOYEES_B_02: A department's staff list")
@Slf4j
class HR_EMPLOYEES_B_02_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT e
                                        FROM Employee e
                                        WHERE e.department.departmentId = :departmentId
                                        ORDER BY e.lastName ASC, e.firstName ASC NULLS FIRST""",
                                Employee.class
                        )
                        .setParameter("departmentId", 60)
                        .getResultList()
        );
        list.forEach(e -> log.debug("{}", e));
        assertThat(list)
                .extracting(Employee::getEmployeeId, Employee::getFirstName, Employee::getLastName)
                .containsExactly(
                        tuple(106, "Valli", "Jackson"),
                        tuple(103, "Alexander", "James"),
                        tuple(104, "Bruce", "Miller"),
                        tuple(107, "Diana", "Nguyen"),
                        tuple(105, "David", "Williams"));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Employee.class);
            final var e = cq.from(Employee.class);
            cq.select(e)
                    .where(cb.equal(e.get(Employee_.department).get(Department_.departmentId),
                                    cb.parameter(Integer.class, "departmentId")))
                    .orderBy(cb.asc(e.get(Employee_.lastName)),
                             cb.asc(e.get(Employee_.firstName), Nulls.FIRST));
            return em.createQuery(cq)
                    .setParameter("departmentId", 60)
                    .getResultList();
        });
        list.forEach(e -> log.debug("{}", e));
        assertThat(list)
                .extracting(Employee::getEmployeeId, Employee::getFirstName, Employee::getLastName)
                .containsExactly(
                        tuple(106, "Valli", "Jackson"),
                        tuple(103, "Alexander", "James"),
                        tuple(104, "Bruce", "Miller"),
                        tuple(107, "Diana", "Nguyen"),
                        tuple(105, "David", "Williams"));
    }

}
