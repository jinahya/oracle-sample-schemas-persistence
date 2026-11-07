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
import com.github.jinahya.oracle.sample.persistence.co.Product_;
import com.github.jinahya.oracle.sample.persistence.co.Store_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_INVENTORY_B_02} of {@code src/test/sql-exercises/INVENTORY/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_INVENTORY_B_02: Where a product is running lowest")
@Slf4j
class CO_INVENTORY_B_02_PersistenceIT {

    private static final long PRODUCT_ID = 1L;

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT i
                                        FROM Inventory i
                                        WHERE i.product.productId = :productId
                                        ORDER BY i.productInventory, i.store.storeId""",
                                Inventory.class
                        )
                        .setParameter("productId", PRODUCT_ID)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Inventory.class);
            final var i = cq.from(Inventory.class);
            cq.select(i)
                    .where(cb.equal(i.get(Inventory_.product).get(Product_.productId), PRODUCT_ID))
                    .orderBy(cb.asc(i.get(Inventory_.productInventory)),
                             cb.asc(i.get(Inventory_.store).get(Store_.storeId)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Inventory> list) {
        list.forEach(i -> log.debug("{}", i));
        assertThat(list)
                .extracting(Inventory::getStoreId, Inventory::getProductInventory)
                .containsExactly(
                        tuple(3L, 1L),
                        tuple(8L, 2L),
                        tuple(11L, 2L),
                        tuple(20L, 2L),
                        tuple(1L, 3L),
                        tuple(4L, 3L),
                        tuple(7L, 3L),
                        tuple(19L, 3L),
                        tuple(5L, 4L),
                        tuple(13L, 4L),
                        tuple(21L, 5L),
                        tuple(12L, 6L),
                        tuple(17L, 6L),
                        tuple(18L, 6L),
                        tuple(22L, 7L),
                        tuple(2L, 9L),
                        tuple(15L, 9L),
                        tuple(9L, 10L),
                        tuple(10L, 10L),
                        tuple(16L, 10L),
                        tuple(6L, 13L),
                        tuple(23L, 15L),
                        tuple(14L, 16L));
    }
}
