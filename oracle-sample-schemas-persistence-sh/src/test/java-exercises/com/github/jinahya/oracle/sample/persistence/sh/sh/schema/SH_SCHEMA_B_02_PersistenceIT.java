package com.github.jinahya.oracle.sample.persistence.sh.sh.schema;

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
 * Answers {@code SH_SCHEMA_B_02} of {@code src/test/sql-exercises/__/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_SCHEMA_B_02: Customers with their country, in one round trip")
@Slf4j
class SH_SCHEMA_B_02_PersistenceIT {

    private static final String CITY = "Tokyo";

    // runs after the entity manager is closed, so each country has to be fetched with its customer
    private static void verify(final List<Customer> list) {
        list.forEach(c -> log.debug("{}", c));
        assertThat(list)
                .extracting(Customer::getCustId, Customer::getCustFirstName,
                            Customer::getCustLastName, c -> c.getCountry().getCountryId(),
                            c -> c.getCountry().getCountryName(),
                            c -> c.getCountry().getCountryRegion())
                .containsExactly(
                        tuple(6430L, "Oriena", "Baley", 52782L, "Japan", "Asia"),
                        tuple(23737L, "Beulah", "Burgess", 52782L, "Japan", "Asia"),
                        tuple(44524L, "Carey", "Colven", 52782L, "Japan", "Asia"),
                        tuple(47431L, "Lucinda", "Fernandez", 52782L, "Japan", "Asia"),
                        tuple(40350L, "Ruby", "Ferris", 52782L, "Japan", "Asia"),
                        tuple(27197L, "Xylon", "Grier", 52782L, "Japan", "Asia"),
                        tuple(25121L, "Persephone", "Haske", 52782L, "Japan", "Asia"),
                        tuple(26505L, "Diamond", "Justice", 52782L, "Japan", "Asia"),
                        tuple(9893L, "Macklin", "Lin", 52782L, "Japan", "Asia"),
                        tuple(34812L, "Marshal", "Longerbeam", 52782L, "Japan", "Asia"),
                        tuple(27889L, "Rosanna", "Rill", 52782L, "Japan", "Asia"),
                        tuple(50893L, "Honey", "Sager", 52782L, "Japan", "Asia"),
                        tuple(30680L, "Norma", "Stokley", 52782L, "Japan", "Asia"),
                        tuple(20966L, "Wyman", "Tazelar", 52782L, "Japan", "Asia"),
                        tuple(17506L, "Bertram", "Thomas", 52782L, "Japan", "Asia"),
                        tuple(10584L, "Gerard", "Titus", 52782L, "Japan", "Asia"));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT cu
                                        FROM Customer cu
                                                 JOIN FETCH cu.country
                                        WHERE cu.custCity = :city
                                        ORDER BY cu.custLastName, cu.custFirstName""",
                                Customer.class
                        )
                        .setParameter("city", CITY)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Customer.class);
            final var cu = cq.from(Customer.class);
            cu.fetch(Customer_.country);
            cq.select(cu)
                    .where(cb.equal(cu.get(Customer_.custCity), cb.parameter(String.class, "city")))
                    .orderBy(cb.asc(cu.get(Customer_.custLastName)),
                             cb.asc(cu.get(Customer_.custFirstName)));
            return em.createQuery(cq)
                    .setParameter("city", CITY)
                    .getResultList();
        });
        verify(list);
    }
}
