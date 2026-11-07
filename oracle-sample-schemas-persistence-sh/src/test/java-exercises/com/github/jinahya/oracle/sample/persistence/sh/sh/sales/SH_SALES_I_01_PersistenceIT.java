package com.github.jinahya.oracle.sample.persistence.sh.sh.sales;

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
import com.github.jinahya.oracle.sample.persistence.sh.Sales;
import com.github.jinahya.oracle.sample.persistence.sh.Sales_;
import com.github.jinahya.oracle.sample.persistence.sh.Time_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_SALES_I_01} of {@code src/test/sql-exercises/SALES/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_SALES_I_01: Top spenders of a period")
@Slf4j
class SH_SALES_I_01_PersistenceIT {

    private static final LocalDateTime FROM_DAY = LocalDate.of(2021, 1, 1).atStartOfDay();

    private static final LocalDateTime TO_DAY = LocalDate.of(2021, 3, 31).atStartOfDay();

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Customer) r[0]).getCustId(), r -> ((Number) r[1]).longValue(),
                            r -> ((Number) r[2]).longValue(), r -> ((Number) r[3]).doubleValue())
                .containsExactly(
                        tuple(5544L, 151L, 151L, 25711.11),
                        tuple(8092L, 155L, 155L, 25265.84),
                        tuple(31946L, 91L, 91L, 24030.75),
                        tuple(3264L, 149L, 149L, 23566.77),
                        tuple(818L, 128L, 128L, 23543.21),
                        tuple(16122L, 142L, 142L, 23460.97),
                        tuple(11932L, 179L, 179L, 22979.75),
                        tuple(3485L, 126L, 126L, 22304.16),
                        tuple(30086L, 147L, 147L, 22299.33),
                        tuple(11718L, 158L, 158L, 22142.64));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT cu,
                                               COUNT(s),
                                               SUM(s.quantitySold),
                                               SUM(s.amountSold) AS spent
                                        FROM Sales s
                                                 JOIN s.customer cu
                                        WHERE s.time.timeId BETWEEN :fromDay AND :toDay
                                        GROUP BY cu
                                        ORDER BY spent DESC, cu.custId""",
                                Object[].class
                        )
                        .setParameter("fromDay", FROM_DAY)
                        .setParameter("toDay", TO_DAY)
                        .setMaxResults(10)
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
            final var cu = s.join(Sales_.customer);
            final var spent = cb.sum(s.get(Sales_.amountSold));
            cq.select(cb.array(cu, cb.count(s), cb.sum(s.get(Sales_.quantitySold)), spent))
                    .where(cb.between(s.get(Sales_.time).get(Time_.timeId),
                                      cb.parameter(LocalDateTime.class, "fromDay"),
                                      cb.parameter(LocalDateTime.class, "toDay")))
                    .groupBy(cu)
                    .orderBy(cb.desc(spent), cb.asc(cu.get(Customer_.custId)));
            return em.createQuery(cq)
                    .setParameter("fromDay", FROM_DAY)
                    .setParameter("toDay", TO_DAY)
                    .setMaxResults(10)
                    .getResultList();
        });
        verify(list);
    }
}
