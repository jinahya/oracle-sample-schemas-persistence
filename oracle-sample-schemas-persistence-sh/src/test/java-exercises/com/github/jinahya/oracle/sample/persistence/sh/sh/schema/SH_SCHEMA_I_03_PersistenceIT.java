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

import com.github.jinahya.oracle.sample.persistence.sh.Country;
import com.github.jinahya.oracle.sample.persistence.sh.Customer_;
import com.github.jinahya.oracle.sample.persistence.sh.Sales;
import com.github.jinahya.oracle.sample.persistence.sh.Sales_;
import com.github.jinahya.oracle.sample.persistence.sh.Time_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_SCHEMA_I_03} of {@code src/test/sql-exercises/__/02_intermediate.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_SCHEMA_I_03: Countries above a revenue target")
@Slf4j
class SH_SCHEMA_I_03_PersistenceIT {

    private static final Integer YEAR = 2021;

    private static final BigDecimal THRESHOLD = BigDecimal.valueOf(1_000_000L);

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Country) r[0]).getCountryName(),
                            r -> ((Number) r[1]).doubleValue())
                .containsExactly(
                        tuple("United States of America", 13120594.21),
                        tuple("Germany", 2275388.56),
                        tuple("Japan", 1621977.2),
                        tuple("United Kingdom", 1552223.06),
                        tuple("Italy", 1082405.86));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT co, SUM(s.amountSold) AS revenue
                                        FROM Sales s
                                                 JOIN s.customer cu
                                                 JOIN cu.country co
                                                 JOIN s.time t
                                        WHERE t.calendarYear = :year
                                        GROUP BY co
                                        HAVING SUM(s.amountSold) > :threshold
                                        ORDER BY revenue DESC""",
                                Object[].class
                        )
                        .setParameter("year", YEAR)
                        .setParameter("threshold", THRESHOLD)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var s = cq.from(Sales.class);
            final var co = s.join(Sales_.customer).join(Customer_.country);
            final var t = s.join(Sales_.time);
            final var revenue = cb.sum(s.get(Sales_.amountSold));
            cq.select(cb.array(co, revenue))
                    .where(cb.equal(t.get(Time_.calendarYear), cb.parameter(Integer.class, "year")))
                    .groupBy(co)
                    .having(cb.gt(revenue, cb.parameter(BigDecimal.class, "threshold")))
                    .orderBy(cb.desc(revenue));
            return em.createQuery(cq)
                    .setParameter("year", YEAR)
                    .setParameter("threshold", THRESHOLD)
                    .getResultList();
        });
        verify(list);
    }
}
