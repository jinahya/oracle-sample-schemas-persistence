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

import java.util.Arrays;
import java.util.List;

import static com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils.rounded;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_PROMOTIONS_I_01} of
 * {@code src/test/sql-exercises/PROMOTIONS/02_intermediate.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_PROMOTIONS_I_01: Promotion spend by category")
@Slf4j
class SH_PROMOTIONS_I_01_PersistenceIT {

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> r[0], r -> ((Number) r[1]).longValue(),
                            r -> ((Number) r[2]).doubleValue(), r -> rounded(r[3], 6))
                .containsExactly(
                        tuple("newspaper", 110L, 5680500.0, 51640.909091),
                        tuple("TV", 115L, 5320000.0, 46260.869565),
                        tuple("internet", 85L, 4256200.0, 50072.941176),
                        tuple("magazine", 65L, 3402700.0, 52349.230769),
                        tuple("post", 65L, 3313100.0, 50970.769231),
                        tuple("flyer", 40L, 2163400.0, 54085.0),
                        tuple("radio", 20L, 1095000.0, 54750.0),
                        tuple("ad news", 2L, 0.0, 0.0));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT p.promoCategory,
                                               COUNT(p),
                                               SUM(p.promoCost) AS totalCost,
                                               AVG(p.promoCost)
                                        FROM Promotion p
                                        WHERE p.promoCategory <> 'NO PROMOTION'
                                        GROUP BY p.promoCategory
                                        ORDER BY totalCost DESC""",
                                Object[].class
                        )
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var p = cq.from(Promotion.class);
            final var category = p.get(Promotion_.promoCategory);
            final var totalCost = cb.sum(p.get(Promotion_.promoCost));
            cq.select(cb.array(category, cb.count(p), totalCost,
                               cb.avg(p.get(Promotion_.promoCost))))
                    .where(cb.notEqual(category, "NO PROMOTION"))
                    .groupBy(category)
                    .orderBy(cb.desc(totalCost));
            return em.createQuery(cq)
                    .getResultList();
        });
        verify(list);
    }
}
