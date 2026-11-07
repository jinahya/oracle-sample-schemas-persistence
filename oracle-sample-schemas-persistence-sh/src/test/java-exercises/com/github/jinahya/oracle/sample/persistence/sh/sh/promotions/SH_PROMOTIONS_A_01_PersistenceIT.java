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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_PROMOTIONS_A_01} of {@code src/test/sql-exercises/PROMOTIONS/03_advanced.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_PROMOTIONS_A_01: Clashing promotions")
@Slf4j
class SH_PROMOTIONS_A_01_PersistenceIT {

    private static final String PROMO_CATEGORY = "radio";

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        // the subcategory, the first promotion's id and the second one's
        assertThat(list)
                .extracting(r -> ((Promotion) r[0]).getPromoSubcategory(),
                            r -> ((Promotion) r[0]).getPromoId(),
                            r -> ((Promotion) r[1]).getPromoId())
                .containsExactly(
                        tuple("radio commercial", 112, 445),
                        tuple("radio commercial", 334, 501),
                        tuple("radio program sponsorship", 122, 389),
                        tuple("radio program sponsorship", 122, 455),
                        tuple("radio program sponsorship", 389, 455),
                        tuple("radio program sponsorship", 56, 73),
                        tuple("radio program sponsorship", 56, 122),
                        tuple("radio program sponsorship", 56, 389),
                        tuple("radio program sponsorship", 56, 406),
                        tuple("radio program sponsorship", 56, 455),
                        tuple("radio program sponsorship", 73, 406),
                        tuple("radio program sponsorship", 184, 233),
                        tuple("radio program sponsorship", 167, 184),
                        tuple("radio program sponsorship", 167, 233),
                        tuple("radio program sponsorship", 124, 278),
                        tuple("radio program sponsorship", 124, 344),
                        tuple("radio program sponsorship", 124, 511),
                        tuple("radio program sponsorship", 278, 344),
                        tuple("radio program sponsorship", 278, 511),
                        tuple("radio program sponsorship", 344, 511),
                        tuple("radio program sponsorship", 291, 295));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT a, b
                                        FROM Promotion a
                                                 JOIN Promotion b
                                                      ON b.promoSubcategoryId = a.promoSubcategoryId
                                                          AND b.promoId > a.promoId
                                                          AND b.promoBeginDate <= a.promoEndDate
                                                          AND a.promoBeginDate <= b.promoEndDate
                                        WHERE a.promoCategory = :promoCategory
                                        ORDER BY a.promoSubcategory, a.promoBeginDate, a.promoId,
                                                 b.promoId""",
                                Object[].class
                        )
                        .setParameter("promoCategory", PROMO_CATEGORY)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            // a second root, not a.join(Promotion.class): EclipseLink takes an entity join only
            // along an association, and Promotion has none to itself; the join's conditions go
            // to the WHERE clause, which an inner join makes the same
            final var a = cq.from(Promotion.class);
            final var b = cq.from(Promotion.class);
            cq.select(cb.array(a, b))
                    .where(cb.equal(b.get(Promotion_.promoSubcategoryId),
                                    a.get(Promotion_.promoSubcategoryId)),
                           cb.gt(b.get(Promotion_.promoId), a.get(Promotion_.promoId)),
                           cb.lessThanOrEqualTo(b.get(Promotion_.promoBeginDate),
                                                a.get(Promotion_.promoEndDate)),
                           cb.lessThanOrEqualTo(a.get(Promotion_.promoBeginDate),
                                                b.get(Promotion_.promoEndDate)),
                           cb.equal(a.get(Promotion_.promoCategory),
                                    cb.parameter(String.class, "promoCategory")))
                    .orderBy(cb.asc(a.get(Promotion_.promoSubcategory)),
                             cb.asc(a.get(Promotion_.promoBeginDate)),
                             cb.asc(a.get(Promotion_.promoId)),
                             cb.asc(b.get(Promotion_.promoId)));
            return em.createQuery(cq)
                    .setParameter("promoCategory", PROMO_CATEGORY)
                    .getResultList();
        });
        verify(list);
    }
}
