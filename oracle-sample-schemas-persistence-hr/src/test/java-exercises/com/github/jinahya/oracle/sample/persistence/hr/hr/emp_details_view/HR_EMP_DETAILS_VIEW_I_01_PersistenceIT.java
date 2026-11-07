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

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_EMP_DETAILS_VIEW_I_01} of
 * {@code src/test/sql-exercises/EMP_DETAILS_VIEW/02_intermediate.sql} with JPQL and with the
 * Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_EMP_DETAILS_VIEW_I_01: Payroll by country")
@Slf4j
class HR_EMP_DETAILS_VIEW_I_01_PersistenceIT {

    // The view has no entity for a region or a country, only their names, so the queries group by
    // those columns and select them, as the SQL does.

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT v.regionName, v.countryName, COUNT(v), SUM(v.salary)
                                        FROM EmpDetailsView v
                                        GROUP BY v.regionName, v.countryName
                                        ORDER BY v.regionName ASC, SUM(v.salary) DESC""",
                                Object[].class
                        )
                        .getResultList()
        );
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> r[0], r -> r[1], r -> ((Number) r[2]).longValue(),
                            r -> ((Number) r[3]).longValue())
                .containsExactly(
                        tuple("Americas", "United States of America", 68L, 344416L),
                        tuple("Americas", "Canada", 2L, 19000L),
                        tuple("Europe", "United Kingdom of Great Britain and Northern Ireland",
                              35L, 311000L),
                        tuple("Europe", "Germany", 1L, 10000L));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var v = cq.from(EmpDetailsView.class);
            final var regionName = v.get(EmpDetailsView_.regionName);
            final var countryName = v.get(EmpDetailsView_.countryName);
            final var salaries = cb.sum(v.get(EmpDetailsView_.salary));
            cq.select(cb.array(regionName, countryName, cb.count(v), salaries))
                    .groupBy(regionName, countryName)
                    .orderBy(cb.asc(regionName), cb.desc(salaries));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> r[0], r -> r[1], r -> ((Number) r[2]).longValue(),
                            r -> ((Number) r[3]).longValue())
                .containsExactly(
                        tuple("Americas", "United States of America", 68L, 344416L),
                        tuple("Americas", "Canada", 2L, 19000L),
                        tuple("Europe", "United Kingdom of Great Britain and Northern Ireland",
                              35L, 311000L),
                        tuple("Europe", "Germany", 1L, 10000L));
    }

}
