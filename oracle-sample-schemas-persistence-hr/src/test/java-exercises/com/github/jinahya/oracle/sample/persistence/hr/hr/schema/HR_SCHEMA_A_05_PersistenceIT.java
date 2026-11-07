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
import com.github.jinahya.oracle.sample.persistence.hr.Employee;
import com.github.jinahya.oracle.sample.persistence.hr.Employee_;
import com.github.jinahya.oracle.sample.persistence.hr.Location_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Nulls;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_SCHEMA_A_05} of {@code src/test/sql-exercises/__/03_advanced.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_SCHEMA_A_05: Department roster in one round trip")
@Slf4j
class HR_SCHEMA_A_05_PersistenceIT {

    // One query loads each department of the location with each of its employees, the empty
    // departments with a null employee; Department's getEmployees() is not public, so the pairs
    // are selected, rather than the departments with their employees fetched.

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT d, e
                                        FROM Department d
                                                 LEFT JOIN d.employees e
                                        WHERE d.location.locationId = :locationId
                                        ORDER BY d.departmentId ASC,
                                                 e.lastName ASC,
                                                 e.firstName ASC NULLS FIRST,
                                                 e.employeeId ASC""",
                                Object[].class
                        )
                        .setParameter("locationId", 1700)
                        .getResultList()
        );
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Department) r[0]).getDepartmentId(),
                            r -> ((Department) r[0]).getDepartmentName(),
                            r -> Optional.ofNullable((Employee) r[1])
                                    .map(Employee::getEmployeeId)
                                    .orElse(null),
                            r -> Optional.ofNullable((Employee) r[1])
                                    .map(Employee::getFirstName)
                                    .orElse(null),
                            r -> Optional.ofNullable((Employee) r[1])
                                    .map(Employee::getLastName)
                                    .orElse(null),
                            r -> Optional.ofNullable((Employee) r[1])
                                    .map(Employee::getJobId)
                                    .orElse(null))
                .containsExactly(
                        tuple(10, "Administration", 200, "Jennifer", "Whalen", "AD_ASST"),
                        tuple(30, "Purchasing", 116, "Shelli", "Baida", "PU_CLERK"),
                        tuple(30, "Purchasing", 119, "Karen", "Colmenares", "PU_CLERK"),
                        tuple(30, "Purchasing", 118, "Guy", "Himuro", "PU_CLERK"),
                        tuple(30, "Purchasing", 115, "Alexander", "Khoo", "PU_CLERK"),
                        tuple(30, "Purchasing", 114, "Den", "Li", "PU_MAN"),
                        tuple(30, "Purchasing", 117, "Sigal", "Tobias", "PU_CLERK"),
                        tuple(90, "Executive", 102, "Lex", "Garcia", "AD_VP"),
                        tuple(90, "Executive", 100, "Steven", "King", "AD_PRES"),
                        tuple(90, "Executive", 101, "Neena", "Yang", "AD_VP"),
                        tuple(100, "Finance", 110, "John", "Chen", "FI_ACCOUNT"),
                        tuple(100, "Finance", 109, "Daniel", "Faviet", "FI_ACCOUNT"),
                        tuple(100, "Finance", 108, "Nancy", "Gruenberg", "FI_MGR"),
                        tuple(100, "Finance", 113, "Luis", "Popp", "FI_ACCOUNT"),
                        tuple(100, "Finance", 111, "Ismael", "Sciarra", "FI_ACCOUNT"),
                        tuple(100, "Finance", 112, "Jose Manuel", "Urman", "FI_ACCOUNT"),
                        tuple(110, "Accounting", 206, "William", "Gietz", "AC_ACCOUNT"),
                        tuple(110, "Accounting", 205, "Shelley", "Higgins", "AC_MGR"),
                        tuple(120, "Treasury", null, null, null, null),
                        tuple(130, "Corporate Tax", null, null, null, null),
                        tuple(140, "Control And Credit", null, null, null, null),
                        tuple(150, "Shareholder Services", null, null, null, null),
                        tuple(160, "Benefits", null, null, null, null),
                        tuple(170, "Manufacturing", null, null, null, null),
                        tuple(180, "Construction", null, null, null, null),
                        tuple(190, "Contracting", null, null, null, null),
                        tuple(200, "Operations", null, null, null, null),
                        tuple(210, "IT Support", null, null, null, null),
                        tuple(220, "NOC", null, null, null, null),
                        tuple(230, "IT Helpdesk", null, null, null, null),
                        tuple(240, "Government Sales", null, null, null, null),
                        tuple(250, "Retail Sales", null, null, null, null),
                        tuple(260, "Recruiting", null, null, null, null),
                        tuple(270, "Payroll", null, null, null, null));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var d = cq.from(Department.class);
            final var e = d.join(Department_.employees, JoinType.LEFT);
            cq.select(cb.array(d, e))
                    .where(cb.equal(d.get(Department_.location).get(Location_.locationId),
                                    cb.parameter(Integer.class, "locationId")))
                    .orderBy(cb.asc(d.get(Department_.departmentId)),
                             cb.asc(e.get(Employee_.lastName)),
                             cb.asc(e.get(Employee_.firstName), Nulls.FIRST),
                             cb.asc(e.get(Employee_.employeeId)));
            return em.createQuery(cq)
                    .setParameter("locationId", 1700)
                    .getResultList();
        });
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Department) r[0]).getDepartmentId(),
                            r -> ((Department) r[0]).getDepartmentName(),
                            r -> Optional.ofNullable((Employee) r[1])
                                    .map(Employee::getEmployeeId)
                                    .orElse(null),
                            r -> Optional.ofNullable((Employee) r[1])
                                    .map(Employee::getFirstName)
                                    .orElse(null),
                            r -> Optional.ofNullable((Employee) r[1])
                                    .map(Employee::getLastName)
                                    .orElse(null),
                            r -> Optional.ofNullable((Employee) r[1])
                                    .map(Employee::getJobId)
                                    .orElse(null))
                .containsExactly(
                        tuple(10, "Administration", 200, "Jennifer", "Whalen", "AD_ASST"),
                        tuple(30, "Purchasing", 116, "Shelli", "Baida", "PU_CLERK"),
                        tuple(30, "Purchasing", 119, "Karen", "Colmenares", "PU_CLERK"),
                        tuple(30, "Purchasing", 118, "Guy", "Himuro", "PU_CLERK"),
                        tuple(30, "Purchasing", 115, "Alexander", "Khoo", "PU_CLERK"),
                        tuple(30, "Purchasing", 114, "Den", "Li", "PU_MAN"),
                        tuple(30, "Purchasing", 117, "Sigal", "Tobias", "PU_CLERK"),
                        tuple(90, "Executive", 102, "Lex", "Garcia", "AD_VP"),
                        tuple(90, "Executive", 100, "Steven", "King", "AD_PRES"),
                        tuple(90, "Executive", 101, "Neena", "Yang", "AD_VP"),
                        tuple(100, "Finance", 110, "John", "Chen", "FI_ACCOUNT"),
                        tuple(100, "Finance", 109, "Daniel", "Faviet", "FI_ACCOUNT"),
                        tuple(100, "Finance", 108, "Nancy", "Gruenberg", "FI_MGR"),
                        tuple(100, "Finance", 113, "Luis", "Popp", "FI_ACCOUNT"),
                        tuple(100, "Finance", 111, "Ismael", "Sciarra", "FI_ACCOUNT"),
                        tuple(100, "Finance", 112, "Jose Manuel", "Urman", "FI_ACCOUNT"),
                        tuple(110, "Accounting", 206, "William", "Gietz", "AC_ACCOUNT"),
                        tuple(110, "Accounting", 205, "Shelley", "Higgins", "AC_MGR"),
                        tuple(120, "Treasury", null, null, null, null),
                        tuple(130, "Corporate Tax", null, null, null, null),
                        tuple(140, "Control And Credit", null, null, null, null),
                        tuple(150, "Shareholder Services", null, null, null, null),
                        tuple(160, "Benefits", null, null, null, null),
                        tuple(170, "Manufacturing", null, null, null, null),
                        tuple(180, "Construction", null, null, null, null),
                        tuple(190, "Contracting", null, null, null, null),
                        tuple(200, "Operations", null, null, null, null),
                        tuple(210, "IT Support", null, null, null, null),
                        tuple(220, "NOC", null, null, null, null),
                        tuple(230, "IT Helpdesk", null, null, null, null),
                        tuple(240, "Government Sales", null, null, null, null),
                        tuple(250, "Retail Sales", null, null, null, null),
                        tuple(260, "Recruiting", null, null, null, null),
                        tuple(270, "Payroll", null, null, null, null));
    }

}
