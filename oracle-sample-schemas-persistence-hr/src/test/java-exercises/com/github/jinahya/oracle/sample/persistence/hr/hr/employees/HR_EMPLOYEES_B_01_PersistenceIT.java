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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code HR_EMPLOYEES_B_01} of {@code src/test/sql-exercises/EMPLOYEES/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_EMPLOYEES_B_01: Look up a colleague by e-mail")
@Slf4j
class HR_EMPLOYEES_B_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var employee = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT e
                                        FROM Employee e
                                        WHERE e.email = :email""",
                                Employee.class
                        )
                        .setParameter("email", "SKING")
                        .getSingleResult()
        );
        log.debug("{}", employee);
        assertThat(employee)
                .extracting(Employee::getEmployeeId, Employee::getFirstName, Employee::getLastName,
                            Employee::getEmail, Employee::getPhoneNumber, Employee::getHireDate,
                            Employee::getJobId, e -> e.getSalary().intValue(),
                            Employee::getCommissionPct, Employee::getManagerId,
                            Employee::getDepartmentId)
                .containsExactly(
                        100, "Steven", "King",
                        "SKING", "1.515.555.0100", LocalDateTime.parse("2013-06-17T00:00:00"),
                        "AD_PRES", 24000,
                        null, null,
                        90);
    }

    @Test
    void __CriteriaApi() {
        final var employee = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Employee.class);
            final var e = cq.from(Employee.class);
            cq.select(e)
                    .where(cb.equal(e.get(Employee_.email), cb.parameter(String.class, "email")));
            return em.createQuery(cq)
                    .setParameter("email", "SKING")
                    .getSingleResult();
        });
        log.debug("{}", employee);
        assertThat(employee)
                .extracting(Employee::getEmployeeId, Employee::getFirstName, Employee::getLastName,
                            Employee::getEmail, Employee::getPhoneNumber, Employee::getHireDate,
                            Employee::getJobId, e -> e.getSalary().intValue(),
                            Employee::getCommissionPct, Employee::getManagerId,
                            Employee::getDepartmentId)
                .containsExactly(
                        100, "Steven", "King",
                        "SKING", "1.515.555.0100", LocalDateTime.parse("2013-06-17T00:00:00"),
                        "AD_PRES", 24000,
                        null, null,
                        90);
    }

}
