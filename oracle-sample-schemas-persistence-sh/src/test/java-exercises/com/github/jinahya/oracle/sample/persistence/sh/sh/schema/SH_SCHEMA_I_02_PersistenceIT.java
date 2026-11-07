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

import com.github.jinahya.oracle.sample.persistence.sh.Product;
import com.github.jinahya.oracle.sample.persistence.sh.Product_;
import com.github.jinahya.oracle.sample.persistence.sh.Sales;
import com.github.jinahya.oracle.sample.persistence.sh.Sales_;
import com.github.jinahya.oracle.sample.persistence.sh.Time_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_SCHEMA_I_02} of {@code src/test/sql-exercises/__/02_intermediate.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_SCHEMA_I_02: Ten best sellers")
@Slf4j
class SH_SCHEMA_I_02_PersistenceIT {

    private static final Integer YEAR = 2021;

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Product) r[0]).getProdId(), r -> ((Product) r[0]).getProdName(),
                            r -> ((Number) r[1]).longValue(), r -> ((Number) r[2]).doubleValue())
                .containsExactly(
                        tuple(31, "Fiber Tape", 7568L, 70407.09),
                        tuple(30, "Linseed Oil", 7072L, 73685.03),
                        tuple(48, "Indoor Cricket Ball", 6669L, 83580.7),
                        tuple(120, "Cricket Peak Cap", 6417L, 242031.17),
                        tuple(40, "Team shirt", 6210L, 297004.8),
                        tuple(33, "Baseball Is Life Cap", 6135L, 278984.61),
                        tuple(119, "Cricket Bails - Junior", 5632L, 42805.3),
                        tuple(25, "5 Point Batting Tee", 5140L, 596100.2),
                        tuple(130, "Pro Maple Youth Bat", 5032L, 486858.69),
                        tuple(23, "Plastic Cricket Bat", 5027L, 115571.34));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT p,
                                               SUM(s.quantitySold) AS units,
                                               SUM(s.amountSold) AS revenue
                                        FROM Sales s
                                                 JOIN s.product p
                                                 JOIN s.time t
                                        WHERE t.calendarYear = :year
                                        GROUP BY p
                                        ORDER BY units DESC, p.prodId""",
                                Object[].class
                        )
                        .setParameter("year", YEAR)
                        .setMaxResults(10)
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
            final var p = s.join(Sales_.product);
            final var t = s.join(Sales_.time);
            final var units = cb.sum(s.get(Sales_.quantitySold));
            cq.select(cb.array(p, units, cb.sum(s.get(Sales_.amountSold))))
                    .where(cb.equal(t.get(Time_.calendarYear), cb.parameter(Integer.class, "year")))
                    .groupBy(p)
                    .orderBy(cb.desc(units), cb.asc(p.get(Product_.prodId)));
            return em.createQuery(cq)
                    .setParameter("year", YEAR)
                    .setMaxResults(10)
                    .getResultList();
        });
        verify(list);
    }
}
