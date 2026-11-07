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
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_SCHEMA_B_01} of {@code src/test/sql-exercises/__/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_SCHEMA_B_01: Who manages a department")
@Slf4j
class HR_SCHEMA_B_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT d, m
                                        FROM Department d
                                                 JOIN d.manager m
                                        WHERE d.departmentId = :departmentId""",
                                Object[].class
                        )
                        .setParameter("departmentId", 60)
                        .getResultList()
        );
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Department) r[0]).getDepartmentName(),
                            r -> ((Employee) r[1]).getEmployeeId(),
                            r -> ((Employee) r[1]).getFirstName(),
                            r -> ((Employee) r[1]).getLastName(),
                            r -> ((Employee) r[1]).getEmail())
                .containsExactly(tuple("IT", 103, "Alexander", "James", "AJAMES"));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var d = cq.from(Department.class);
            final var m = d.join(Department_.manager);
            cq.select(cb.array(d, m))
                    .where(cb.equal(d.get(Department_.departmentId),
                                    cb.parameter(Integer.class, "departmentId")));
            return em.createQuery(cq)
                    .setParameter("departmentId", 60)
                    .getResultList();
        });
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Department) r[0]).getDepartmentName(),
                            r -> ((Employee) r[1]).getEmployeeId(),
                            r -> ((Employee) r[1]).getFirstName(),
                            r -> ((Employee) r[1]).getLastName(),
                            r -> ((Employee) r[1]).getEmail())
                .containsExactly(tuple("IT", 103, "Alexander", "James", "AJAMES"));
    }

}
