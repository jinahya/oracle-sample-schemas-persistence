package com.github.jinahya.oracle.sample.persistence.sh.sh.schema;

/*-
 * #%L
 * sh
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

import com.github.jinahya.oracle.sample.persistence.sh.Customer;
import com.github.jinahya.oracle.sample.persistence.sh.Sales;
import com.github.jinahya.oracle.sample.persistence.sh.Sales_;
import com.github.jinahya.oracle.sample.persistence.sh.Time_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code SH_SCHEMA_A_02} of {@code src/test/sql-exercises/__/03_advanced.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_SCHEMA_A_02: Lapsed customers")
@Slf4j
class SH_SCHEMA_A_02_PersistenceIT {

    private static final Integer PREVIOUS_YEAR = 2020;

    private static final Integer CURRENT_YEAR = 2021;

    // a set operation has no order; the customers are checked by their number and by the
    // smallest and the largest of their ids
    private static void verify(final List<Customer> list) {
        list.stream().limit(10L).forEach(c -> log.debug("{}", c));
        assertThat(list).hasSize(1269);
        final var ids = list.stream().map(Customer::getCustId).sorted().toList();
        assertThat(ids).doesNotHaveDuplicates();
        assertThat(ids).startsWith(3L, 8L, 11L, 12L, 15L).endsWith(49443L);
    }

    private static CriteriaQuery<Customer> customersOf(final CriteriaBuilder cb,
                                                       final String yearParameterName) {
        final var cq = cb.createQuery(Customer.class);
        final var s = cq.from(Sales.class);
        cq.select(s.join(Sales_.customer))
                .where(cb.equal(s.join(Sales_.time).get(Time_.calendarYear),
                                cb.parameter(Integer.class, yearParameterName)));
        return cq;
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT cu
                                        FROM Sales s
                                                 JOIN s.customer cu
                                                 JOIN s.time t
                                        WHERE t.calendarYear = :previousYear
                                        EXCEPT
                                        SELECT cu2
                                        FROM Sales s2
                                                 JOIN s2.customer cu2
                                                 JOIN s2.time t2
                                        WHERE t2.calendarYear = :currentYear""",
                                Customer.class
                        )
                        .setParameter("previousYear", PREVIOUS_YEAR)
                        .setParameter("currentYear", CURRENT_YEAR)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cs = cb.except(customersOf(cb, "previousYear"),
                                     customersOf(cb, "currentYear"));
            return em.createQuery(cs)
                    .setParameter("previousYear", PREVIOUS_YEAR)
                    .setParameter("currentYear", CURRENT_YEAR)
                    .getResultList();
        });
        verify(list);
    }
}
