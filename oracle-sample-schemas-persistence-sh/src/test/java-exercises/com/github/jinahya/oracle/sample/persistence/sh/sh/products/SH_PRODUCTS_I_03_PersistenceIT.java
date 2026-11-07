package com.github.jinahya.oracle.sample.persistence.sh.sh.products;

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

import com.github.jinahya.oracle.sample.persistence.sh.Product;
import com.github.jinahya.oracle.sample.persistence.sh.Product_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_PRODUCTS_I_03} of {@code src/test/sql-exercises/PRODUCTS/02_intermediate.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_PRODUCTS_I_03: Supplier coverage of the catalogue")
@Slf4j
class SH_PRODUCTS_I_03_PersistenceIT {

    // every product of the installed data comes from the one supplier
    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Number) r[0]).intValue(), r -> ((Number) r[1]).longValue(),
                            r -> ((Number) r[2]).longValue(), r -> ((Number) r[3]).doubleValue(),
                            r -> ((Number) r[4]).doubleValue())
                .containsExactly(
                        tuple(1, 72L, 5L, 6.99, 1299.99));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT p.supplierId,
                                               COUNT(p) AS products,
                                               COUNT(DISTINCT p.prodCategoryId),
                                               MIN(p.prodListPrice),
                                               MAX(p.prodListPrice)
                                        FROM Product p
                                        GROUP BY p.supplierId
                                        ORDER BY products DESC, p.supplierId""",
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
            final var p = cq.from(Product.class);
            final var supplierId = p.get(Product_.supplierId);
            final var products = cb.count(p);
            cq.select(cb.array(supplierId,
                               products,
                               cb.countDistinct(p.get(Product_.prodCategoryId)),
                               cb.min(p.get(Product_.prodListPrice)),
                               cb.max(p.get(Product_.prodListPrice))))
                    .groupBy(supplierId)
                    .orderBy(cb.desc(products), cb.asc(supplierId));
            return em.createQuery(cq)
                    .getResultList();
        });
        verify(list);
    }
}
