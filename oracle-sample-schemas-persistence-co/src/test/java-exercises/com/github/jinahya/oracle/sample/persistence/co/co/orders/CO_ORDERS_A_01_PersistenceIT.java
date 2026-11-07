package com.github.jinahya.oracle.sample.persistence.co.co.orders;

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

import com.github.jinahya.oracle.sample.persistence.co.Customer;
import com.github.jinahya.oracle.sample.persistence.co.Order;
import com.github.jinahya.oracle.sample.persistence.co.Order_;
import com.github.jinahya.oracle.sample.persistence.co.Store_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code CO_ORDERS_A_01} of {@code src/test/sql-exercises/ORDERS/03_advanced.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_ORDERS_A_01: Customers of two stores, or of one only")
@Slf4j
class CO_ORDERS_A_01_PersistenceIT {

    private static final long STORE_A = 1L;

    private static final long STORE_B = 2L;

    // (a) the customers of both stores
    private static final List<Long> BOTH = List.of(
            2L, 31L, 60L, 89L, 118L, 147L, 176L, 205L, 234L, 263L, 292L, 321L, 350L, 379L
    );

    private static List<Customer> queryLanguage(final String operator) {
        return __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT c FROM Order o JOIN o.customer c WHERE o.store.storeId = :storeA
                                        %s
                                        SELECT c FROM Order o JOIN o.customer c WHERE o.store.storeId = :storeB"""
                                        .formatted(operator),
                                Customer.class
                        )
                        .setParameter("storeA", STORE_A)
                        .setParameter("storeB", STORE_B)
                        .getResultList()
        );
    }

    @Test
    void __QueryLanguage() {
        verify(queryLanguage("INTERSECT"), queryLanguage("EXCEPT"), queryLanguage("UNION"));
    }

    private static CriteriaQuery<Customer> customersOf(final EntityManager em, final long storeId) {
        final var cb = em.getCriteriaBuilder();
        final var cq = cb.createQuery(Customer.class);
        final var o = cq.from(Order.class);
        return cq.select(o.join(Order_.customer))
                .where(cb.equal(o.get(Order_.store).get(Store_.storeId), storeId));
    }

    @Test
    void __CriteriaApi() {
        final var intersect = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            return em.createQuery(cb.intersect(customersOf(em, STORE_A), customersOf(em, STORE_B)))
                    .getResultList();
        });
        final var except = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            return em.createQuery(cb.except(customersOf(em, STORE_A), customersOf(em, STORE_B)))
                    .getResultList();
        });
        final var union = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            return em.createQuery(cb.union(customersOf(em, STORE_A), customersOf(em, STORE_B)))
                    .getResultList();
        });
        verify(intersect, except, union);
    }

    // a set operation without ALL removes duplicates, and the exercise asks for no order
    private static void verify(final List<Customer> intersect, final List<Customer> except,
                               final List<Customer> union) {
        log.debug("intersect: {}", intersect.size());
        log.debug("except: {}", except.size());
        log.debug("union: {}", union.size());
        assertThat(intersect)
                .extracting(Customer::getCustomerId)
                .containsExactlyInAnyOrderElementsOf(BOTH);
        assertThat(except)
                .hasSize(378)
                .extracting(Customer::getCustomerId)
                .doesNotHaveDuplicates()
                .doesNotContainAnyElementsOf(BOTH);
        assertThat(union)
                .hasSize(392)
                .extracting(Customer::getCustomerId)
                .doesNotHaveDuplicates()
                .containsAll(BOTH);
    }
}
