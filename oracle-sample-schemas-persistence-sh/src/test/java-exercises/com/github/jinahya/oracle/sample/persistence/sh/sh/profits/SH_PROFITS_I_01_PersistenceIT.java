package com.github.jinahya.oracle.sample.persistence.sh.sh.profits;

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

import com.github.jinahya.oracle.sample.persistence.sh.Profit;
import com.github.jinahya.oracle.sample.persistence.sh.Profit_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils.rounded;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_PROFITS_I_01} of {@code src/test/sql-exercises/PROFITS/02_intermediate.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_PROFITS_I_01: Thinnest margins")
@Slf4j
class SH_PROFITS_I_01_PersistenceIT {

    private static final LocalDateTime FROM_DAY = LocalDate.of(2021, 1, 1).atStartOfDay();

    private static final LocalDateTime TO_DAY = LocalDate.of(2021, 12, 31).atStartOfDay();

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Number) r[0]).intValue(), r -> ((Number) r[1]).doubleValue(),
                            r -> ((Number) r[2]).doubleValue(), r -> rounded(r[3], 6))
                .containsExactly(
                        tuple(15, 756428.71, 50826.62, 0.067193),
                        tuple(47, 53224.73, 3872.27, 0.072753),
                        tuple(20, 2091346.27, 167775.65, 0.080224),
                        tuple(16, 604389.78, 49397.31, 0.081731),
                        tuple(22, 8264.99, 751.42, 0.090916),
                        tuple(33, 278984.61, 25554.85, 0.091599),
                        tuple(126, 43937.79, 4072.55, 0.092689),
                        tuple(125, 30443.76, 2840.44, 0.093301),
                        tuple(129, 239858.04, 23807.76, 0.099258),
                        tuple(31, 70407.09, 7299.39, 0.103674));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT pr.prodId,
                                               SUM(pr.amountSold) AS revenue,
                                               SUM(pr.amountSold - pr.totalCost) AS margin,
                                               SUM(pr.amountSold - pr.totalCost)
                                                   / NULLIF(SUM(pr.amountSold), 0) AS marginRatio
                                        FROM Profit pr
                                        WHERE pr.timeId BETWEEN :fromDay AND :toDay
                                        GROUP BY pr.prodId
                                        HAVING SUM(pr.amountSold) <> 0
                                        ORDER BY marginRatio, pr.prodId""",
                                Object[].class
                        )
                        .setParameter("fromDay", FROM_DAY)
                        .setParameter("toDay", TO_DAY)
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
            final var pr = cq.from(Profit.class);
            final var prodId = pr.get(Profit_.prodId);
            final var revenue = cb.sum(pr.get(Profit_.amountSold));
            final var margin = cb.sum(cb.diff(pr.get(Profit_.amountSold),
                                              pr.get(Profit_.totalCost)));
            final var marginRatio = cb.quot(margin, cb.nullif(revenue, BigDecimal.ZERO));
            cq.select(cb.array(prodId, revenue, margin, marginRatio))
                    .where(cb.between(pr.get(Profit_.timeId),
                                      cb.parameter(LocalDateTime.class, "fromDay"),
                                      cb.parameter(LocalDateTime.class, "toDay")))
                    .groupBy(prodId)
                    .having(cb.notEqual(revenue, BigDecimal.ZERO))
                    .orderBy(cb.asc(marginRatio), cb.asc(prodId));
            return em.createQuery(cq)
                    .setParameter("fromDay", FROM_DAY)
                    .setParameter("toDay", TO_DAY)
                    .setMaxResults(10)
                    .getResultList();
        });
        verify(list);
    }
}
