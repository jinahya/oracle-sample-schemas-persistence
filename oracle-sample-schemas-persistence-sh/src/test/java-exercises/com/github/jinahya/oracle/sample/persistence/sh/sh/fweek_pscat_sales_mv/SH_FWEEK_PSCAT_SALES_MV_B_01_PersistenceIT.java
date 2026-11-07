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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Answers {@code SH_FWEEK_PSCAT_SALES_MV_B_01} of
 * {@code src/test/sql-exercises/FWEEK_PSCAT_SALES_MV/01_basic.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_FWEEK_PSCAT_SALES_MV_B_01: One week's sales by subcategory")
@Slf4j
class SH_FWEEK_PSCAT_SALES_MV_B_01_PersistenceIT {

    private static final LocalDateTime WEEK_ENDING_DAY = LocalDate.of(2021, 1, 17).atStartOfDay();

    private static final Long CHANNEL_ID = 4L; // Internet

    private static void verify(final List<FweekPscatSalesMv> list) {
        list.forEach(f -> log.debug("{}", f));
        assertThat(list)
                .extracting(f -> f.getId_().getProdSubcategory(), f -> f.getId_().getPromoId(),
                            f -> f.getDollars().doubleValue())
                .containsExactly(
                        tuple("Tennis Racquet", 999, 1096.9),
                        tuple("Cricket Clothing", 999, 418.04),
                        tuple("Cricket", 999, 126.36),
                        tuple("Baseballs", 999, 26.06));
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
                                        SELECT f
                                        FROM FweekPscatSalesMv f
                                        WHERE f.id_.weekEndingDay = :weekEndingDay
                                          AND f.id_.channelId = :channelId
                                        ORDER BY f.dollars DESC NULLS LAST,
                                                 f.id_.prodSubcategory""",
                                FweekPscatSalesMv.class
                        )
                        .setParameter("weekEndingDay", WEEK_ENDING_DAY)
                        .setParameter("channelId", CHANNEL_ID)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(FweekPscatSalesMv.class);
            final var f = cq.from(FweekPscatSalesMv.class);
            final var id = f.get(FweekPscatSalesMv_.id_);
            cq.select(f)
                    .where(cb.equal(id.get(FweekPscatSalesMvId_.weekEndingDay),
                                    cb.parameter(LocalDateTime.class, "weekEndingDay")),
                           cb.equal(id.get(FweekPscatSalesMvId_.channelId),
                                    cb.parameter(Long.class, "channelId")))
                    .orderBy(cb.desc(f.get(FweekPscatSalesMv_.dollars), Nulls.LAST),
                             cb.asc(id.get(FweekPscatSalesMvId_.prodSubcategory)));
            return em.createQuery(cq)
                    .setParameter("weekEndingDay", WEEK_ENDING_DAY)
                    .setParameter("channelId", CHANNEL_ID)
                    .getResultList();
        });
        verify(list);
    }
}
