package com.github.jinahya.oracle.sample.persistence.co.co.schema;

/*-
 * #%L
 * co
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

import com.github.jinahya.oracle.sample.persistence.co.Inventory;
import com.github.jinahya.oracle.sample.persistence.co.Inventory_;
import com.github.jinahya.oracle.sample.persistence.co.Product;
import com.github.jinahya.oracle.sample.persistence.co.Product_;
import com.github.jinahya.oracle.sample.persistence.co.Store;
import com.github.jinahya.oracle.sample.persistence.co.Store_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_SCHEMA_B_03} of {@code src/test/sql-exercises/__/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_SCHEMA_B_03: Running low")
@Slf4j
class CO_SCHEMA_B_03_PersistenceIT {

    private static final long THRESHOLD = 1L;

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT i, s, p
                                        FROM Inventory i
                                                 JOIN i.store s
                                                 JOIN i.product p
                                        WHERE i.productInventory < :threshold
                                        ORDER BY i.productInventory, s.storeName, p.productName""",
                                Object[].class
                        )
                        .setParameter("threshold", THRESHOLD)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var i = cq.from(Inventory.class);
            final var s = i.join(Inventory_.store);
            final var p = i.join(Inventory_.product);
            cq.select(cb.array(i, s, p))
                    .where(cb.lt(i.get(Inventory_.productInventory), THRESHOLD))
                    .orderBy(cb.asc(i.get(Inventory_.productInventory)),
                             cb.asc(s.get(Store_.storeName)),
                             cb.asc(p.get(Product_.productName)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Object[]> list) {
        list.stream().limit(10).forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list).hasSize(30);
        assertThat(list)
                .extracting(r -> ((Store) r[1]).getStoreName(),
                            r -> ((Product) r[2]).getProductName(),
                            r -> ((Inventory) r[0]).getProductInventory())
                .startsWith(
                        tuple("Bejing", "Men's Coat (Red)", 0L),
                        tuple("Berlin", "Women's Socks (Grey)", 0L),
                        tuple("Bucharest", "Girl's Pyjamas (Black)", 0L),
                        tuple("Buenos Aires", "Women's Jeans (Brown)", 0L),
                        tuple("Chicago", "Women's Jeans (Red)", 0L),
                        tuple("Lagos", "Men's Shorts (Black)", 0L));
    }
}
