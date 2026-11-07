package com.github.jinahya.oracle.sample.persistence.sh.sh.promotions;

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

import com.github.jinahya.oracle.sample.persistence.sh.Promotion;
import com.github.jinahya.oracle.sample.persistence.sh.Promotion_;
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
 * Answers {@code SH_PROMOTIONS_B_01} of {@code src/test/sql-exercises/PROMOTIONS/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_PROMOTIONS_B_01: Promotions running on a day")
@Slf4j
class SH_PROMOTIONS_B_01_PersistenceIT {

    private static final LocalDateTime DAY = LocalDate.of(2021, 2, 6).atStartOfDay();

    private static void verify(final List<Promotion> list) {
        list.forEach(p -> log.debug("{}", p));
        assertThat(list)
                .extracting(Promotion::getPromoId, Promotion::getPromoName,
                            Promotion::getPromoCategory, p -> p.getPromoBeginDate().toLocalDate(),
                            p -> p.getPromoEndDate().toLocalDate())
                .containsExactly(
                        tuple(191, "flyer promotion #23-191", "flyer",
                              LocalDate.of(2021, 2, 1), LocalDate.of(2021, 3, 1)),
                        tuple(59, "post promotion #20-59", "post",
                              LocalDate.of(2021, 2, 2), LocalDate.of(2021, 3, 2)),
                        tuple(45, "newspaper promotion #27-45", "newspaper",
                              LocalDate.of(2021, 2, 3), LocalDate.of(2021, 3, 3)),
                        tuple(457, "magazine promotion #18-457", "magazine",
                              LocalDate.of(2021, 2, 6), LocalDate.of(2021, 3, 6)));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT p
                                        FROM Promotion p
                                        WHERE :day BETWEEN p.promoBeginDate AND p.promoEndDate
                                        ORDER BY p.promoEndDate, p.promoName""",
                                Promotion.class
                        )
                        .setParameter("day", DAY)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Promotion.class);
            final var p = cq.from(Promotion.class);
            cq.select(p)
                    .where(cb.between(cb.parameter(LocalDateTime.class, "day"),
                                      p.get(Promotion_.promoBeginDate),
                                      p.get(Promotion_.promoEndDate)))
                    .orderBy(cb.asc(p.get(Promotion_.promoEndDate)),
                             cb.asc(p.get(Promotion_.promoName)));
            return em.createQuery(cq)
                    .setParameter("day", DAY)
                    .getResultList();
        });
        verify(list);
    }
}
