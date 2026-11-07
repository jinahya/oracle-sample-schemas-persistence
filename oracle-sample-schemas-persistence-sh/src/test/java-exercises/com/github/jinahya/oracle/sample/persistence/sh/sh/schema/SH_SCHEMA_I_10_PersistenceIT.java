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
import com.github.jinahya.oracle.sample.persistence.sh.Promotion_;
import com.github.jinahya.oracle.sample.persistence.sh.Sales;
import com.github.jinahya.oracle.sample.persistence.sh.Sales_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_SCHEMA_I_10} of {@code src/test/sql-exercises/__/02_intermediate.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_SCHEMA_I_10: Customers who bought on promotion")
@Slf4j
class SH_SCHEMA_I_10_PersistenceIT {

    private static final String CITY = "Altrincham";

    private static void verify(final List<Customer> list) {
        list.forEach(c -> log.debug("{}", c));
        assertThat(list)
                .extracting(Customer::getCustId, Customer::getCustFirstName,
                            Customer::getCustLastName)
                .containsExactly(
                        tuple(2543L, "Evan", "Glidden"),
                        tuple(2554L, "Forrest", "Ingram"),
                        tuple(2545L, "Ezra", "Robinson"));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT cu
                                        FROM Customer cu
                                        WHERE cu.custCity = :city
                                          AND EXISTS (SELECT s
                                                      FROM Sales s
                                                               JOIN s.promotion pr
                                                      WHERE s.customer = cu
                                                        AND pr.promoCategory <> 'NO PROMOTION')
                                        ORDER BY cu.custLastName, cu.custFirstName, cu.custId""",
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
            final var sq = cq.subquery(Sales.class);
            final var s = sq.from(Sales.class);
            sq.select(s)
                    .where(cb.equal(s.get(Sales_.customer), cu),
                           cb.notEqual(s.get(Sales_.promotion).get(Promotion_.promoCategory),
                                       "NO PROMOTION"));
            cq.select(cu)
                    .where(cb.equal(cu.get(Customer_.custCity), cb.parameter(String.class, "city")),
                           cb.exists(sq))
                    .orderBy(cb.asc(cu.get(Customer_.custLastName)),
                             cb.asc(cu.get(Customer_.custFirstName)),
                             cb.asc(cu.get(Customer_.custId)));
            return em.createQuery(cq)
                    .setParameter("city", CITY)
                    .getResultList();
        });
        verify(list);
    }
}
