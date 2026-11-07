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
import com.github.jinahya.oracle.sample.persistence.hr.Location;
import com.github.jinahya.oracle.sample.persistence.hr.Location_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_DEPARTMENTS_I_01} of
 * {@code src/test/sql-exercises/DEPARTMENTS/02_intermediate.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_DEPARTMENTS_I_01: Departments per site")
@Slf4j
class HR_DEPARTMENTS_I_01_PersistenceIT {

    // The inner join drops a department without a location, which the SQL would group under a
    // null LOCATION_ID; none is without one in the installed data.

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT l, COUNT(d)
                                        FROM Department d
                                                 JOIN d.location l
                                        GROUP BY l
                                        ORDER BY COUNT(d) DESC, l.locationId ASC""",
                                Object[].class
                        )
                        .getResultList()
        );
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Location) r[0]).getLocationId(),
                            r -> ((Number) r[1]).longValue())
                .containsExactly(
                        tuple(1700, 21L),
                        tuple(1400, 1L),
                        tuple(1500, 1L),
                        tuple(1800, 1L),
                        tuple(2400, 1L),
                        tuple(2500, 1L),
                        tuple(2700, 1L));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var d = cq.from(Department.class);
            final var l = d.join(Department_.location);
            cq.select(cb.array(l, cb.count(d)))
                    .groupBy(l)
                    .orderBy(cb.desc(cb.count(d)), cb.asc(l.get(Location_.locationId)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Location) r[0]).getLocationId(),
                            r -> ((Number) r[1]).longValue())
                .containsExactly(
                        tuple(1700, 21L),
                        tuple(1400, 1L),
                        tuple(1500, 1L),
                        tuple(1800, 1L),
                        tuple(2400, 1L),
                        tuple(2500, 1L),
                        tuple(2700, 1L));
    }

}
