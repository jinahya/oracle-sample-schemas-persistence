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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_PROFITS_B_01} of {@code src/test/sql-exercises/PROFITS/01_basic.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_PROFITS_B_01: Loss-making sales")
@Slf4j
class SH_PROFITS_B_01_PersistenceIT {

    private static final LocalDateTime FROM_DAY = LocalDate.of(2021, 6, 1).atStartOfDay();

    private static final LocalDateTime TO_DAY = LocalDate.of(2021, 6, 30).atStartOfDay();

    private static void verify(final List<Profit> list) {
        list.forEach(p -> log.debug("{}", p));
        assertThat(list)
                .extracting(p -> p.getTimeId().toLocalDate(), Profit::getProdId, Profit::getCustId,
                            Profit::getChannelId, Profit::getPromoId, Profit::getQuantitySold,
                            p -> p.getAmountSold().doubleValue(),
                            p -> p.getTotalCost().doubleValue())
                .containsExactly(
                        tuple(LocalDate.of(2021, 6, 3), 31, 5742L, 4L, 999, 1, 9.35, 9.38),
                        tuple(LocalDate.of(2021, 6, 3), 31, 8633L, 4L, 999, 1, 9.35, 9.38),
                        tuple(LocalDate.of(2021, 6, 25), 22, 2002L, 4L, 999, 1, 26.07, 26.55),
                        tuple(LocalDate.of(2021, 6, 25), 22, 18322L, 4L, 999, 1, 26.07, 26.55));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT pr
                                        FROM Profit pr
                                        WHERE pr.timeId BETWEEN :fromDay AND :toDay
                                          AND pr.amountSold < pr.totalCost
                                        ORDER BY pr.timeId, pr.prodId, pr.custId, pr.channelId,
                                                 pr.promoId""",
                                Profit.class
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
            final var cq = cb.createQuery(Profit.class);
            final var pr = cq.from(Profit.class);
            cq.select(pr)
                    .where(cb.between(pr.get(Profit_.timeId),
                                      cb.parameter(LocalDateTime.class, "fromDay"),
                                      cb.parameter(LocalDateTime.class, "toDay")),
                           cb.lt(pr.get(Profit_.amountSold), pr.get(Profit_.totalCost)))
                    .orderBy(cb.asc(pr.get(Profit_.timeId)),
                             cb.asc(pr.get(Profit_.prodId)),
                             cb.asc(pr.get(Profit_.custId)),
                             cb.asc(pr.get(Profit_.channelId)),
                             cb.asc(pr.get(Profit_.promoId)));
            return em.createQuery(cq)
                    .setParameter("fromDay", FROM_DAY)
                    .setParameter("toDay", TO_DAY)
                    .getResultList();
        });
        verify(list);
    }
}
