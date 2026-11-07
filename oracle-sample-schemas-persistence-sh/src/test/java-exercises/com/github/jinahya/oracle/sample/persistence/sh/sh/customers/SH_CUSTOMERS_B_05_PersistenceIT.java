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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_CUSTOMERS_B_05} of {@code src/test/sql-exercises/CUSTOMERS/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_CUSTOMERS_B_05: Customer records in effect at a time")
@Slf4j
class SH_CUSTOMERS_B_05_PersistenceIT {

    private static final String CITY = "Tokyo";

    private static final LocalDateTime AS_OF = LocalDate.of(2021, 6, 30).atStartOfDay();

    // every record of the installed data is in effect from 2019-01-01, with no end
    private static void verify(final List<Customer> list) {
        list.forEach(c -> log.debug("{}", c));
        final var from = LocalDate.of(2019, 1, 1).atStartOfDay();
        assertThat(list)
                .extracting(Customer::getCustId, Customer::getCustFirstName,
                            Customer::getCustLastName, Customer::getCustEffFrom,
                            Customer::getCustEffTo, Customer::getCustValid)
                .containsExactly(
                        tuple(6430L, "Oriena", "Baley", from, null, "A"),
                        tuple(23737L, "Beulah", "Burgess", from, null, "A"),
                        tuple(44524L, "Carey", "Colven", from, null, "I"),
                        tuple(47431L, "Lucinda", "Fernandez", from, null, "I"),
                        tuple(40350L, "Ruby", "Ferris", from, null, "I"),
                        tuple(27197L, "Xylon", "Grier", from, null, "A"),
                        tuple(25121L, "Persephone", "Haske", from, null, "A"),
                        tuple(26505L, "Diamond", "Justice", from, null, "A"),
                        tuple(9893L, "Macklin", "Lin", from, null, "A"),
                        tuple(34812L, "Marshal", "Longerbeam", from, null, "I"),
                        tuple(27889L, "Rosanna", "Rill", from, null, "A"),
                        tuple(50893L, "Honey", "Sager", from, null, "I"),
                        tuple(30680L, "Norma", "Stokley", from, null, "I"),
                        tuple(20966L, "Wyman", "Tazelar", from, null, "A"),
                        tuple(17506L, "Bertram", "Thomas", from, null, "A"),
                        tuple(10584L, "Gerard", "Titus", from, null, "A"));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT c
                                        FROM Customer c
                                        WHERE c.custCity = :city
                                          AND (c.custEffFrom IS NULL OR c.custEffFrom <= :asOf)
                                          AND (c.custEffTo IS NULL OR :asOf < c.custEffTo)
                                        ORDER BY c.custLastName, c.custFirstName, c.custId""",
                                Customer.class
                        )
                        .setParameter("city", CITY)
                        .setParameter("asOf", AS_OF)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var asOf = cb.parameter(LocalDateTime.class, "asOf");
            final var cq = cb.createQuery(Customer.class);
            final var c = cq.from(Customer.class);
            final var effFrom = c.get(Customer_.custEffFrom);
            final var effTo = c.get(Customer_.custEffTo);
            cq.select(c)
                    .where(cb.equal(c.get(Customer_.custCity), cb.parameter(String.class, "city")),
                           cb.or(cb.isNull(effFrom), cb.lessThanOrEqualTo(effFrom, asOf)),
                           cb.or(cb.isNull(effTo), cb.lessThan(asOf, effTo)))
                    .orderBy(cb.asc(c.get(Customer_.custLastName)),
                             cb.asc(c.get(Customer_.custFirstName)),
                             cb.asc(c.get(Customer_.custId)));
            return em.createQuery(cq)
                    .setParameter("city", CITY)
                    .setParameter("asOf", AS_OF)
                    .getResultList();
        });
        verify(list);
    }
}
