package com.github.jinahya.oracle.sample.persistence.sh.sh.fweek_pscat_sales_mv;

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

import com.github.jinahya.oracle.sample.persistence.sh.FweekPscatSalesMv;
import com.github.jinahya.oracle.sample.persistence.sh.FweekPscatSalesMv_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import com.github.jinahya.oracle.sample.persistence.sh.mapped.FweekPscatSalesMvId_;
import jakarta.persistence.criteria.Nulls;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Answers {@code SH_FWEEK_PSCAT_SALES_MV_I_01} of
 * {@code src/test/sql-exercises/FWEEK_PSCAT_SALES_MV/02_intermediate.sql} with JPQL and with the
 * Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_FWEEK_PSCAT_SALES_MV_I_01: Subcategory revenue over a period")
@Slf4j
class SH_FWEEK_PSCAT_SALES_MV_I_01_PersistenceIT {

    private static final Long CHANNEL_ID = 4L; // Internet

    private static final LocalDateTime FROM_DAY = LocalDate.of(2021, 1, 1).atStartOfDay();

    private static final LocalDateTime TO_DAY = LocalDate.of(2021, 3, 31).atStartOfDay();

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> r[0], r -> ((Number) r[1]).doubleValue())
                .containsExactly(
                        tuple("Training Aids and Equipment", 49875.67),
                        tuple("Soccer / Football", 32860.89),
                        tuple("Tennis Racquet", 31815.51),
                        tuple("Cricket Clothing", 16281.02),
                        tuple("Cricket", 7544.69),
                        tuple("Soccer Clothing", 5310.28),
                        tuple("Batting Tees", 5247.63),
                        tuple("Gloves and Mitts", 4572.27),
                        tuple("Baseballs", 2564.55),
                        tuple("Cricket Bat", 2259.7),
                        tuple("Golf Bags and Carts", 1216.53),
                        tuple("Cricket Fan Gear", 1022.07),
                        tuple("Bats", 616.02),
                        tuple("Baseball Clothing", 385.46),
                        tuple("Tennis Racquet Grip", 239.7));
    }

    @BeforeEach
    void assumeRefreshed() {
        // the installer leaves the materialized view empty until it is refreshed
        final var count = __Exercises_TestUtils.apply(
                em -> em.createQuery("SELECT COUNT(f) FROM FweekPscatSalesMv f", Long.class)
                        .getSingleResult()
        );
        assumeTrue(count > 0L, "FWEEK_PSCAT_SALES_MV holds no row; refresh it first");
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT f.id_.prodSubcategory, SUM(f.dollars) AS revenue
                                        FROM FweekPscatSalesMv f
                                        WHERE f.id_.channelId = :channelId
                                          AND f.id_.weekEndingDay BETWEEN :fromDay AND :toDay
                                        GROUP BY f.id_.prodSubcategory
                                        ORDER BY revenue DESC NULLS LAST""",
                                Object[].class
                        )
                        .setParameter("channelId", CHANNEL_ID)
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
            final var f = cq.from(FweekPscatSalesMv.class);
            final var id = f.get(FweekPscatSalesMv_.id_);
            final var subcategory = id.get(FweekPscatSalesMvId_.prodSubcategory);
            final var revenue = cb.sum(f.get(FweekPscatSalesMv_.dollars));
            cq.select(cb.array(subcategory, revenue))
                    .where(cb.equal(id.get(FweekPscatSalesMvId_.channelId),
                                    cb.parameter(Long.class, "channelId")),
                           cb.between(id.get(FweekPscatSalesMvId_.weekEndingDay),
                                      cb.parameter(LocalDateTime.class, "fromDay"),
                                      cb.parameter(LocalDateTime.class, "toDay")))
                    .groupBy(subcategory)
                    .orderBy(cb.desc(revenue, Nulls.LAST));
            return em.createQuery(cq)
                    .setParameter("channelId", CHANNEL_ID)
                    .setParameter("fromDay", FROM_DAY)
                    .setParameter("toDay", TO_DAY)
                    .getResultList();
        });
        verify(list);
    }
}
