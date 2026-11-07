package com.github.jinahya.oracle.sample.persistence.sh.sh.customers;

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

import com.github.jinahya.oracle.sample.persistence.sh.Customer;
import com.github.jinahya.oracle.sample.persistence.sh.Customer_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_CUSTOMERS_I_01} of {@code src/test/sql-exercises/CUSTOMERS/02_intermediate.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_CUSTOMERS_I_01: Email addresses shared by several customers")
@Slf4j
class SH_CUSTOMERS_I_01_PersistenceIT {

    private static void verify(final List<Object[]> list) {
        list.stream().limit(10L).forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list).hasSize(701);
        assertThat(list)
                .extracting(r -> r[0], r -> ((Number) r[1]).longValue())
                .startsWith(
                        tuple("Ardena@company.example.com", 79L),
                        tuple("Barber@company.example.com", 79L),
                        tuple("Barkley@company.example.com", 79L),
                        tuple("Beiers@company.example.com", 79L),
                        tuple("Brooks@company.example.com", 79L));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT c.custEmail, COUNT(c) AS customers
                                        FROM Customer c
                                        WHERE c.custEmail IS NOT NULL
                                        GROUP BY c.custEmail
                                        HAVING COUNT(c) > 1
                                        ORDER BY customers DESC, c.custEmail""",
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
            final var c = cq.from(Customer.class);
            final var email = c.get(Customer_.custEmail);
            final var customers = cb.count(c);
            cq.select(cb.array(email, customers))
                    .where(cb.isNotNull(email))
                    .groupBy(email)
                    .having(cb.gt(customers, 1L))
                    .orderBy(cb.desc(customers), cb.asc(email));
            return em.createQuery(cq)
                    .getResultList();
        });
        verify(list);
    }
}
