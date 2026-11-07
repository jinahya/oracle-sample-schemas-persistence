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

import com.github.jinahya.oracle.sample.persistence.sh.Channel;
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
 * Answers {@code SH_SCHEMA_B_03} of {@code src/test/sql-exercises/__/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_SCHEMA_B_03: Revenue by channel")
@Slf4j
class SH_SCHEMA_B_03_PersistenceIT {

    private static final Integer YEAR = 2021;

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Channel) r[0]).getChannelDesc(),
                            r -> ((Number) r[1]).longValue(), r -> ((Number) r[2]).doubleValue())
                .containsExactly(
                        tuple("Direct Sales", 141896L, 14257425.85),
                        tuple("Partners", 77924L, 7626104.01),
                        tuple("Internet", 12826L, 1881976.76));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT ch,
                                               SUM(s.quantitySold) AS units,
                                               SUM(s.amountSold) AS revenue
                                        FROM Sales s
                                                 JOIN s.channel ch
                                                 JOIN s.time t
                                        WHERE t.calendarYear = :year
                                        GROUP BY ch
                                        ORDER BY revenue DESC""",
                                Object[].class
                        )
                        .setParameter("year", YEAR)
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
            final var ch = s.join(Sales_.channel);
            final var t = s.join(Sales_.time);
            final var revenue = cb.sum(s.get(Sales_.amountSold));
            cq.select(cb.array(ch, cb.sum(s.get(Sales_.quantitySold)), revenue))
                    .where(cb.equal(t.get(Time_.calendarYear), cb.parameter(Integer.class, "year")))
                    .groupBy(ch)
                    .orderBy(cb.desc(revenue));
            return em.createQuery(cq)
                    .setParameter("year", YEAR)
                    .getResultList();
        });
        verify(list);
    }
}
