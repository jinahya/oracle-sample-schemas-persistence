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

import com.github.jinahya.oracle.sample.persistence.sh.Channel;
import com.github.jinahya.oracle.sample.persistence.sh.Customer_;
import com.github.jinahya.oracle.sample.persistence.sh.Product;
import com.github.jinahya.oracle.sample.persistence.sh.Product_;
import com.github.jinahya.oracle.sample.persistence.sh.Sales;
import com.github.jinahya.oracle.sample.persistence.sh.Sales_;
import com.github.jinahya.oracle.sample.persistence.sh.Time_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_SCHEMA_B_01} of {@code src/test/sql-exercises/__/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_SCHEMA_B_01: A customer's purchase history")
@Slf4j
class SH_SCHEMA_B_01_PersistenceIT {

    private static final Long CUST_ID = 3507L;

    // the day is the sale's own key column, read through its identifier
    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Sales) r[0]).getId_().getTimeId().toLocalDate(),
                            r -> ((Product) r[1]).getProdName(),
                            r -> ((Channel) r[2]).getChannelDesc(),
                            r -> ((Sales) r[0]).getQuantitySold(),
                            r -> ((Sales) r[0]).getAmountSold().doubleValue())
                .containsExactly(
                        tuple(LocalDate.of(2021, 12, 11), "Lithium Electric Golf Caddy",
                              "Direct Sales", 1, 1451.73),
                        tuple(LocalDate.of(2021, 12, 11), "Pitching Machine and Batting Cage Combo",
                              "Direct Sales", 1, 1107.4),
                        tuple(LocalDate.of(2021, 12, 11), "Right-Handed Graphite Shaft Iron Set",
                              "Direct Sales", 1, 930.59),
                        tuple(LocalDate.of(2021, 12, 11), "Soccer Goal - Official",
                              "Direct Sales", 1, 1146.49));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT s, p, ch
                                        FROM Sales s
                                                 JOIN s.product p
                                                 JOIN s.channel ch
                                        WHERE s.customer.custId = :custId
                                        ORDER BY s.time.timeId DESC, p.prodName""",
                                Object[].class
                        )
                        .setParameter("custId", CUST_ID)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var s = cq.from(Sales.class);
            final var p = s.join(Sales_.product);
            final var ch = s.join(Sales_.channel);
            cq.select(cb.array(s, p, ch))
                    .where(cb.equal(s.get(Sales_.customer).get(Customer_.custId),
                                    cb.parameter(Long.class, "custId")))
                    .orderBy(cb.desc(s.get(Sales_.time).get(Time_.timeId)),
                             cb.asc(p.get(Product_.prodName)));
            return em.createQuery(cq)
                    .setParameter("custId", CUST_ID)
                    .getResultList();
        });
        verify(list);
    }
}
