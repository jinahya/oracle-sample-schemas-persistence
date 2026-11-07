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

import com.github.jinahya.oracle.sample.persistence.co.Customer;
import com.github.jinahya.oracle.sample.persistence.co.Customer_;
import com.github.jinahya.oracle.sample.persistence.co.Order;
import com.github.jinahya.oracle.sample.persistence.co.Order_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code CO_SCHEMA_A_01} of {@code src/test/sql-exercises/__/03_advanced.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_SCHEMA_A_01: Customers who never ordered")
@Slf4j
class CO_SCHEMA_A_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT c
                                        FROM Customer c
                                        WHERE NOT EXISTS (SELECT o FROM Order o WHERE o.customer = c)
                                        ORDER BY c.fullName""",
                                Customer.class
                        )
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
            final var sq = cq.subquery(Order.class);
            final var o = sq.from(Order.class);
            sq.select(o)
                    .where(cb.equal(o.get(Order_.customer), c));
            cq.select(c)
                    .where(cb.not(cb.exists(sq)))
                    .orderBy(cb.asc(c.get(Customer_.fullName)));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    // every customer of the installed data has placed an order
    private static void verify(final List<Customer> list) {
        list.forEach(c -> log.debug("{}", c));
        assertThat(list).isEmpty();
    }
}
