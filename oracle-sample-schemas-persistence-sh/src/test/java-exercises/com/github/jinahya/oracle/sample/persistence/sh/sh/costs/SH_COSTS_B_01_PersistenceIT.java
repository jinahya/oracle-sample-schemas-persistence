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
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_COSTS_B_01} of {@code src/test/sql-exercises/COSTS/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_COSTS_B_01: Prices below cost")
@Slf4j
class SH_COSTS_B_01_PersistenceIT {

    private static final LocalDateTime FROM_DAY = LocalDate.of(2021, 5, 1).atStartOfDay();

    private static final LocalDateTime TO_DAY = LocalDate.of(2021, 6, 30).atStartOfDay();

    // the four @Id attributes of Cost are protected, so the key is read through its associations,
    // while the entity manager is still open, since they are lazy
    private static void verify(final List<Cost> list) {
        list.forEach(c -> log.debug("{}", c));
        assertThat(list)
                .extracting(c -> c.getTime().getTimeId().toLocalDate(),
                            c -> c.getProduct().getProdId(),
                            c -> c.getChannel().getChannelId(),
                            c -> c.getPromotion().getPromoId(),
                            c -> c.getUnitCost().doubleValue(),
                            c -> c.getUnitPrice().doubleValue())
                .containsExactly(
                        tuple(LocalDate.of(2021, 5, 3), 31, 4L, 999, 9.38, 9.35),
                        tuple(LocalDate.of(2021, 5, 7), 31, 4L, 999, 9.55, 9.38),
                        tuple(LocalDate.of(2021, 5, 23), 31, 4L, 999, 9.55, 9.38),
                        tuple(LocalDate.of(2021, 5, 26), 31, 4L, 999, 9.38, 9.35),
                        tuple(LocalDate.of(2021, 6, 3), 31, 4L, 999, 9.38, 9.35),
                        tuple(LocalDate.of(2021, 6, 25), 22, 4L, 999, 26.55, 26.07));
    }

    @Test
    void __QueryLanguage() {
        __Exercises_TestUtils.apply(em -> {
            final var list = em.createQuery(
                            """
                                    SELECT c
                                    FROM Cost c
                                    WHERE c.timeId BETWEEN :fromDay AND :toDay
                                      AND c.unitPrice < c.unitCost
                                    ORDER BY c.timeId, c.prodId, c.channelId, c.promoId""",
                            Cost.class
                    )
                    .setParameter("fromDay", FROM_DAY)
                    .setParameter("toDay", TO_DAY)
                    .getResultList();
            verify(list);
            return list;
        });
    }

    @Test
    void __CriteriaApi() {
        __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Cost.class);
            final var c = cq.from(Cost.class);
            cq.select(c)
                    .where(cb.between(c.get(Cost_.timeId),
                                      cb.parameter(LocalDateTime.class, "fromDay"),
                                      cb.parameter(LocalDateTime.class, "toDay")),
                           cb.lt(c.get(Cost_.unitPrice), c.get(Cost_.unitCost)))
                    .orderBy(cb.asc(c.get(Cost_.timeId)),
                             cb.asc(c.get(Cost_.prodId)),
                             cb.asc(c.get(Cost_.channelId)),
                             cb.asc(c.get(Cost_.promoId)));
            final var list = em.createQuery(cq)
                    .setParameter("fromDay", FROM_DAY)
                    .setParameter("toDay", TO_DAY)
                    .getResultList();
            verify(list);
            return list;
        });
    }
}
