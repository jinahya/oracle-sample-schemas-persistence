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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_CUSTOMERS_B_01} of {@code src/test/sql-exercises/CUSTOMERS/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_CUSTOMERS_B_01: Look up a customer by surname")
@Slf4j
class SH_CUSTOMERS_B_01_PersistenceIT {

    // in another case than the names', on purpose
    private static final String PREFIX = "abR";

    private static void verify(final List<Customer> list) {
        list.forEach(c -> log.debug("{}", c));
        assertThat(list)
                .extracting(Customer::getCustId, Customer::getCustFirstName,
                            Customer::getCustLastName, Customer::getCustCity,
                            Customer::getCustEmail)
                .containsExactly(
                        tuple(1061L, "Adelbert", "Abramowitz", "Enschede",
                              "a.abramowitz@company2.example.com"),
                        tuple(849L, "Abner", "Abrams", "Aix-les-Bains",
                              "abner.abrams@company2.example.com"),
                        tuple(209L, "Regina", "Abrams", "Henagar",
                              "regina.abrams@company2.example.com"));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT c
                                        FROM Customer c
                                        WHERE UPPER(c.custLastName) LIKE UPPER(:prefix || '%')
                                        ORDER BY c.custLastName, c.custFirstName, c.custId""",
                                Customer.class
                        )
                        .setParameter("prefix", PREFIX)
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
                    .where(cb.like(cb.upper(c.get(Customer_.custLastName)),
                                   cb.upper(cb.concat(cb.parameter(String.class, "prefix"),
                                                      "%"))))
                    .orderBy(cb.asc(c.get(Customer_.custLastName)),
                             cb.asc(c.get(Customer_.custFirstName)),
                             cb.asc(c.get(Customer_.custId)));
            return em.createQuery(cq)
                    .setParameter("prefix", PREFIX)
                    .getResultList();
        });
        verify(list);
    }
}
