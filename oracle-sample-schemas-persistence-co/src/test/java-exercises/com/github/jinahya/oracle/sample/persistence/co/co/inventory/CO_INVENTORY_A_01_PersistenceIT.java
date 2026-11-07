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
import com.github.jinahya.oracle.sample.persistence.co.Store;
import com.github.jinahya.oracle.sample.persistence.co.Store_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code CO_INVENTORY_A_01} of {@code src/test/sql-exercises/INVENTORY/03_advanced.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_INVENTORY_A_01: Stores that carry everything another store carries")
@Slf4j
class CO_INVENTORY_A_01_PersistenceIT {

    // The SQL takes DISTINCT store ids from INVENTORY. The query selects the Store instead, from
    // STORES, which needs no DISTINCT; a DISTINCT over a Store would compare its LOGO, a BLOB,
    // which Oracle refuses (ORA-22848).

    private static final long STORE_ID = 20L;

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT s
                                        FROM Store s
                                        WHERE s.storeId <> :storeId
                                          AND NOT EXISTS (SELECT r
                                                          FROM Inventory r
                                                          WHERE r.store.storeId = :storeId
                                                            AND NOT EXISTS (SELECT x
                                                                            FROM Inventory x
                                                                            WHERE x.store = s
                                                                              AND x.product = r.product))
                                        ORDER BY s.storeId""",
                                Store.class
                        )
                        .setParameter("storeId", STORE_ID)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Store.class);
            final var s = cq.from(Store.class);
            final var sq = cq.subquery(Inventory.class);
            final var r = sq.from(Inventory.class);
            final var ssq = sq.subquery(Inventory.class);
            final var x = ssq.from(Inventory.class);
            ssq.select(x)
                    .where(cb.equal(x.get(Inventory_.store), s),
                           cb.equal(x.get(Inventory_.product), r.get(Inventory_.product)));
            sq.select(r)
                    .where(cb.equal(r.get(Inventory_.store).get(Store_.storeId), STORE_ID),
                           cb.not(cb.exists(ssq)));
            cq.select(s)
                    .where(cb.notEqual(s.get(Store_.storeId), STORE_ID),
                           cb.not(cb.exists(sq)))
                    .orderBy(cb.asc(s.get(Store_.storeId)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Store> list) {
        list.forEach(s -> log.debug("{}", s));
        assertThat(list)
                .extracting(Store::getStoreId)
                .containsExactly(1L, 4L, 9L, 10L, 14L, 19L);
    }
}
