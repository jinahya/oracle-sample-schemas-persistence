package com.github.jinahya.oracle.sample.persistence.co.co.stores;

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

import com.github.jinahya.oracle.sample.persistence.co.Store;
import com.github.jinahya.oracle.sample.persistence.co.Store_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code CO_STORES_B_01} of {@code src/test/sql-exercises/STORES/01_basic.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_STORES_B_01: One contact line per store")
@Slf4j
class CO_STORES_B_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT s, COALESCE(s.webAddress, s.physicalAddress)
                                        FROM Store s
                                        ORDER BY s.storeName""",
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
            final var s = cq.from(Store.class);
            cq.select(cb.array(s, cb.coalesce(s.get(Store_.webAddress),
                                              s.get(Store_.physicalAddress))))
                    .orderBy(cb.asc(s.get(Store_.storeName)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Store) r[0]).getStoreId())
                .containsExactly(21L, 8L, 7L, 19L, 5L, 11L, 12L, 6L, 10L, 20L, 14L, 15L, 4L, 1L,
                                 17L, 2L, 3L, 16L, 18L, 23L, 22L, 9L, 13L);
        // a check constraint keeps at least one of the two addresses, so none is null
        assertThat(list).allSatisfy(r -> {
            final var store = (Store) r[0];
            assertThat(r[1])
                    .isNotNull()
                    .isEqualTo(Optional.ofNullable(store.getWebAddress())
                                       .orElseGet(store::getPhysicalAddress));
        });
        assertThat(list)
                .filteredOn(r -> ((Store) r[0]).getStoreId() == 1L)
                .singleElement()
                .satisfies(r -> assertThat(r[1]).isEqualTo("https://www.example.com"));
        assertThat(list)
                .filteredOn(r -> ((Store) r[0]).getStoreId() == 9L)
                .singleElement()
                .satisfies(r -> assertThat(r[1]).isEqualTo(
                        "Hertogswetering 163-167, 3543 AS Utrecht, Netherlands"));
    }
}
