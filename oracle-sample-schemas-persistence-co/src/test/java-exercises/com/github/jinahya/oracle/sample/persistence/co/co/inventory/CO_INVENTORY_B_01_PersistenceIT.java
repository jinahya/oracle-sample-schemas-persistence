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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code CO_INVENTORY_B_01} of {@code src/test/sql-exercises/INVENTORY/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_INVENTORY_B_01: How many are on the shelf?")
@Slf4j
class CO_INVENTORY_B_01_PersistenceIT {

    private static final long STORE_ID = 20L;

    private static final long PRODUCT_ID = 1L;

    @Test
    void __QueryLanguage() {
        final var inventory = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT i
                                        FROM Inventory i
                                        WHERE i.store.storeId = :storeId
                                          AND i.product.productId = :productId""",
                                Inventory.class
                        )
                        .setParameter("storeId", STORE_ID)
                        .setParameter("productId", PRODUCT_ID)
                        .getSingleResult()
        );
        verify(inventory);
    }

    @Test
    void __CriteriaApi() {
        final var inventory = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Inventory.class);
            final var i = cq.from(Inventory.class);
            cq.select(i)
                    .where(cb.equal(i.get(Inventory_.store).get(Store_.storeId), STORE_ID),
                           cb.equal(i.get(Inventory_.product).get(Product_.productId), PRODUCT_ID));
            return em.createQuery(cq).getSingleResult();
        });
        verify(inventory);
    }

    private static void verify(final Inventory inventory) {
        log.debug("inventory: {}", inventory);
        assertThat(inventory.getStoreId()).isEqualTo(STORE_ID);
        assertThat(inventory.getProductId()).isEqualTo(PRODUCT_ID);
        assertThat(inventory.getProductInventory()).isEqualTo(2L);
    }
}
