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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code SH_PROMOTIONS_B_02} of {@code src/test/sql-exercises/PROMOTIONS/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_PROMOTIONS_B_02: Kinds of promotion")
@Slf4j
class SH_PROMOTIONS_B_02_PersistenceIT {

    private static void verify(final List<String> list) {
        list.forEach(c -> log.debug("{}", c));
        assertThat(list)
                .containsExactly("NO PROMOTION", "TV", "ad news", "flyer", "internet", "magazine",
                                 "newspaper", "post", "radio");
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT DISTINCT p.promoCategory
                                        FROM Promotion p
                                        ORDER BY p.promoCategory""",
                                String.class
                        )
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(String.class);
            final var p = cq.from(Promotion.class);
            cq.select(p.get(Promotion_.promoCategory))
                    .distinct(true)
                    .orderBy(cb.asc(p.get(Promotion_.promoCategory)));
            return em.createQuery(cq)
                    .getResultList();
        });
        verify(list);
    }
}
