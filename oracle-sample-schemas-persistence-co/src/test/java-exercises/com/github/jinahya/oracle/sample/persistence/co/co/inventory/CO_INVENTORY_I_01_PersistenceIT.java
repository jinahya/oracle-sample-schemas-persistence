package com.github.jinahya.oracle.sample.persistence.co.co.inventory;

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
 * Answers {@code CO_INVENTORY_I_01} of {@code src/test/sql-exercises/INVENTORY/02_intermediate.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_INVENTORY_I_01: What each store holds")
@Slf4j
class CO_INVENTORY_I_01_PersistenceIT {

    // Store maps LOGO, a BLOB, and Oracle cannot group by a BLOB (ORA-22848), so the query groups
    // by the store's key, the foreign key itself, rather than by the Store.

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT i.store.storeId, COUNT(i), SUM(i.productInventory),
                                               SUM(CASE WHEN i.productInventory = 0 THEN 1 ELSE 0 END)
                                        FROM Inventory i
                                        GROUP BY i.store.storeId
                                        ORDER BY i.store.storeId""",
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
            final var i = cq.from(Inventory.class);
            final var storeId = i.get(Inventory_.store).get(Store_.storeId);
            final var productInventory = i.get(Inventory_.productInventory);
            cq.select(cb.array(
                            storeId,
                            cb.count(i),
                            cb.sum(productInventory),
                            cb.sum(cb.<Integer>selectCase()
                                           .when(cb.equal(productInventory, 0L), 1)
                                           .otherwise(0))))
                    .groupBy(storeId)
                    .orderBy(cb.asc(storeId));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Number) r[0]).longValue(),
                            r -> ((Number) r[1]).longValue(),
                            r -> ((Number) r[2]).longValue(),
                            r -> ((Number) r[3]).longValue())
                .containsExactly(
                        tuple(1L, 46L, 347L, 0L),
                        tuple(2L, 23L, 133L, 2L),
                        tuple(3L, 22L, 90L, 0L),
                        tuple(4L, 40L, 263L, 2L),
                        tuple(5L, 23L, 175L, 1L),
                        tuple(6L, 1L, 13L, 0L),
                        tuple(7L, 25L, 121L, 1L),
                        tuple(8L, 23L, 129L, 1L),
                        tuple(9L, 40L, 194L, 3L),
                        tuple(10L, 17L, 82L, 4L),
                        tuple(11L, 24L, 135L, 0L),
                        tuple(12L, 20L, 116L, 2L),
                        tuple(13L, 27L, 173L, 1L),
                        tuple(14L, 37L, 249L, 4L),
                        tuple(15L, 25L, 140L, 3L),
                        tuple(16L, 24L, 172L, 0L),
                        tuple(17L, 15L, 111L, 1L),
                        tuple(18L, 22L, 142L, 0L),
                        tuple(19L, 43L, 299L, 1L),
                        tuple(20L, 12L, 54L, 1L),
                        tuple(21L, 21L, 139L, 1L),
                        tuple(22L, 13L, 99L, 0L),
                        tuple(23L, 23L, 134L, 2L));
    }
}
