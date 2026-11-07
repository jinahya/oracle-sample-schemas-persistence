package com.github.jinahya.oracle.sample.persistence.co.co.customers;

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
import com.github.jinahya.oracle.sample.persistence.co.Customer_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code CO_CUSTOMERS_B_02} of {@code src/test/sql-exercises/CUSTOMERS/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_CUSTOMERS_B_02: Search customers by the start of their name")
@Slf4j
class CO_CUSTOMERS_B_02_PersistenceIT {

    private static final String PREFIX = "ja";

    // a JPQL LIKE pattern is a literal or a parameter, so the pattern is built here
    private static final String PATTERN = PREFIX.toUpperCase(Locale.ROOT) + '%';

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT c
                                        FROM Customer c
                                        WHERE UPPER(c.fullName) LIKE :pattern
                                        ORDER BY c.fullName""",
                                Customer.class
                        )
                        .setParameter("pattern", PATTERN)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Customer.class);
            final var c = cq.from(Customer.class);
            cq.select(c)
                    .where(cb.like(cb.upper(c.get(Customer_.fullName)), PATTERN))
                    .orderBy(cb.asc(c.get(Customer_.fullName)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    private static void verify(final List<Customer> list) {
        list.forEach(c -> log.debug("{}", c));
        assertThat(list)
                .extracting(Customer::getCustomerId, Customer::getFullName)
                .containsExactly(
                        tuple(38L, "Jack Brooks"),
                        tuple(178L, "Jacklyn Clippard"),
                        tuple(175L, "Jacklyn Niskala"),
                        tuple(260L, "Jacques Bartash"),
                        tuple(153L, "Jaimee Gammons"),
                        tuple(283L, "James Smith"),
                        tuple(279L, "James Williams"),
                        tuple(280L, "James Wilson"),
                        tuple(245L, "Jamison Scrobola"),
                        tuple(164L, "Janey Fornell"),
                        tuple(163L, "Jannie Thibideau"));
    }
}
