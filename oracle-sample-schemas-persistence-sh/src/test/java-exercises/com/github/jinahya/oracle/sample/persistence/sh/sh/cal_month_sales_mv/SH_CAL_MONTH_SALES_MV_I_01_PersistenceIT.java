package com.github.jinahya.oracle.sample.persistence.sh.sh.cal_month_sales_mv;

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

import com.github.jinahya.oracle.sample.persistence.sh.CalMonthSalesMv;
import com.github.jinahya.oracle.sample.persistence.sh.CalMonthSalesMv_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Answers {@code SH_CAL_MONTH_SALES_MV_I_01} of
 * {@code src/test/sql-exercises/CAL_MONTH_SALES_MV/02_intermediate.sql} with JPQL and with the
 * Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_CAL_MONTH_SALES_MV_I_01: Months above the average")
@Slf4j
class SH_CAL_MONTH_SALES_MV_I_01_PersistenceIT {

    private static final String FROM_MONTH = "2021-01";

    private static final String TO_MONTH = "2021-12";

    private static void verify(final List<CalMonthSalesMv> list) {
        list.forEach(m -> log.debug("{}", m));
        assertThat(list)
                .extracting(CalMonthSalesMv::getCalendarMonthDesc,
                            m -> m.getDollars().doubleValue())
                .containsExactly(
                        tuple("2021-10", 2164612.21),
                        tuple("2021-02", 2118618.97),
                        tuple("2021-08", 2112255.79),
                        tuple("2021-09", 2112220.68),
                        tuple("2021-12", 2062690.94),
                        tuple("2021-11", 2060343.77),
                        tuple("2021-01", 2006378.46));
    }

    @BeforeEach
    void assumeRefreshed() {
        // the installer leaves the materialized view empty until it is refreshed
        final var count = __Exercises_TestUtils.apply(
                em -> em.createQuery("SELECT COUNT(m) FROM CalMonthSalesMv m", Long.class)
                        .getSingleResult()
        );
        assumeTrue(count > 0L, "CAL_MONTH_SALES_MV holds no row; refresh it first");
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT m
                                        FROM CalMonthSalesMv m
                                        WHERE m.calendarMonthDesc BETWEEN :fromMonth AND :toMonth
                                          AND m.dollars > (SELECT AVG(m2.dollars)
                                                           FROM CalMonthSalesMv m2
                                                           WHERE m2.calendarMonthDesc
                                                                 BETWEEN :fromMonth AND :toMonth)
                                        ORDER BY m.dollars DESC""",
                                CalMonthSalesMv.class
                        )
                        .setParameter("fromMonth", FROM_MONTH)
                        .setParameter("toMonth", TO_MONTH)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var fromMonth = cb.parameter(String.class, "fromMonth");
            final var toMonth = cb.parameter(String.class, "toMonth");
            final var cq = cb.createQuery(CalMonthSalesMv.class);
            final var m = cq.from(CalMonthSalesMv.class);
            final var sq = cq.subquery(Double.class);
            final var m2 = sq.from(CalMonthSalesMv.class);
            sq.select(cb.avg(m2.get(CalMonthSalesMv_.dollars)))
                    .where(cb.between(m2.get(CalMonthSalesMv_.calendarMonthDesc), fromMonth,
                                      toMonth));
            cq.select(m)
                    .where(cb.between(m.get(CalMonthSalesMv_.calendarMonthDesc), fromMonth,
                                      toMonth),
                           cb.gt(m.get(CalMonthSalesMv_.dollars), sq))
                    .orderBy(cb.desc(m.get(CalMonthSalesMv_.dollars)));
            return em.createQuery(cq)
                    .setParameter("fromMonth", FROM_MONTH)
                    .setParameter("toMonth", TO_MONTH)
                    .getResultList();
        });
        verify(list);
    }
}
