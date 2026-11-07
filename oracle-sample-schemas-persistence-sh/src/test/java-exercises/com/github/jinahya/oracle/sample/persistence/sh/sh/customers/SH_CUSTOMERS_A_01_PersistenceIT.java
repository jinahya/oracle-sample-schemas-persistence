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
 * Answers {@code SH_CUSTOMERS_A_01} of {@code src/test/sql-exercises/CUSTOMERS/03_advanced.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_CUSTOMERS_A_01: Credit limits above the band average")
@Slf4j
class SH_CUSTOMERS_A_01_PersistenceIT {

    private static final String CITY = "Tokyo";

    private static void verify(final List<Customer> list) {
        list.forEach(c -> log.debug("{}", c));
        assertThat(list)
                .extracting(Customer::getCustId, Customer::getCustLastName,
                            Customer::getCustIncomeLevel, Customer::getCustCreditLimit)
                .containsExactly(
                        tuple(27889L, "Rill", "J: 190,000 - 249,999", 15000L),
                        tuple(44524L, "Colven", "H: 150,000 - 169,999", 9000L),
                        tuple(50893L, "Sager", "G: 130,000 - 149,999", 7000L),
                        tuple(40350L, "Ferris", "F: 110,000 - 129,999", 10000L),
                        tuple(17506L, "Thomas", "C: 50,000 - 69,999", 7000L),
                        tuple(9893L, "Lin", "B: 30,000 - 49,999", 3000L));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT cu
                                        FROM Customer cu
                                        WHERE cu.custCity = :city
                                          AND cu.custCreditLimit > (SELECT AVG(cu2.custCreditLimit)
                                                                    FROM Customer cu2
                                                                    WHERE cu2.custIncomeLevel
                                                                              = cu.custIncomeLevel)
                                        ORDER BY cu.custIncomeLevel DESC,
                                                 cu.custCreditLimit DESC""",
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
            final var sq = cq.subquery(Double.class);
            final var cu2 = sq.from(Customer.class);
            sq.select(cb.avg(cu2.get(Customer_.custCreditLimit)))
                    .where(cb.equal(cu2.get(Customer_.custIncomeLevel),
                                    cu.get(Customer_.custIncomeLevel)));
            cq.select(cu)
                    .where(cb.equal(cu.get(Customer_.custCity),
                                    cb.parameter(String.class, "city")),
                           cb.gt(cu.get(Customer_.custCreditLimit), sq))
                    .orderBy(cb.desc(cu.get(Customer_.custIncomeLevel)),
                             cb.desc(cu.get(Customer_.custCreditLimit)));
            return em.createQuery(cq)
                    .setParameter("city", CITY)
                    .getResultList();
        });
        verify(list);
    }
}
