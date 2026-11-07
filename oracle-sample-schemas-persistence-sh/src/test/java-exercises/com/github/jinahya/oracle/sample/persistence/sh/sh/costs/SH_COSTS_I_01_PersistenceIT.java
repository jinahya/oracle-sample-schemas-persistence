package com.github.jinahya.oracle.sample.persistence.sh.sh.costs;

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

import com.github.jinahya.oracle.sample.persistence.sh.Cost;
import com.github.jinahya.oracle.sample.persistence.sh.Cost_;
import com.github.jinahya.oracle.sample.persistence.sh.Product;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils.rounded;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_COSTS_I_01} of {@code src/test/sql-exercises/COSTS/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_COSTS_I_01: Average unit margin per product")
@Slf4j
class SH_COSTS_I_01_PersistenceIT {

    private static final LocalDateTime FROM_DAY = LocalDate.of(2021, 1, 1).atStartOfDay();

    private static final LocalDateTime TO_DAY = LocalDate.of(2021, 1, 31).atStartOfDay();

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list).hasSize(69);
        // the thinnest three, and the widest
        assertThat(list)
                .extracting(r -> ((Product) r[0]).getProdId(), r -> rounded(r[1], 6),
                            r -> rounded(r[2], 6), r -> rounded(r[3], 6))
                .startsWith(
                        tuple(31, 8.476765, 9.457941, 0.981176),
                        tuple(119, 6.458974, 7.816410, 1.357436),
                        tuple(30, 9.213125, 10.588750, 1.375625))
                .endsWith(
                        tuple(18, 1200.101667, 1586.067222, 385.965556));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT p,
                                               AVG(c.unitCost),
                                               AVG(c.unitPrice),
                                               AVG(c.unitPrice - c.unitCost) AS avgMargin
                                        FROM Cost c
                                                 JOIN c.product p
                                        WHERE c.timeId BETWEEN :fromDay AND :toDay
                                        GROUP BY p
                                        ORDER BY avgMargin""",
                                Object[].class
                        )
                        .setParameter("fromDay", FROM_DAY)
                        .setParameter("toDay", TO_DAY)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var c = cq.from(Cost.class);
            final var p = c.join(Cost_.product);
            final var avgMargin = cb.avg(cb.diff(c.get(Cost_.unitPrice), c.get(Cost_.unitCost)));
            cq.select(cb.array(p, cb.avg(c.get(Cost_.unitCost)), cb.avg(c.get(Cost_.unitPrice)),
                               avgMargin))
                    .where(cb.between(c.get(Cost_.timeId),
                                      cb.parameter(LocalDateTime.class, "fromDay"),
                                      cb.parameter(LocalDateTime.class, "toDay")))
                    .groupBy(p)
                    .orderBy(cb.asc(avgMargin));
            return em.createQuery(cq)
                    .setParameter("fromDay", FROM_DAY)
                    .setParameter("toDay", TO_DAY)
                    .getResultList();
        });
        verify(list);
    }
}
