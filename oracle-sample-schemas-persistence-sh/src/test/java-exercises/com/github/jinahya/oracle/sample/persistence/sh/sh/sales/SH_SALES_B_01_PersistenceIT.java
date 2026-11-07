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

import com.github.jinahya.oracle.sample.persistence.sh.Channel_;
import com.github.jinahya.oracle.sample.persistence.sh.Customer_;
import com.github.jinahya.oracle.sample.persistence.sh.Product_;
import com.github.jinahya.oracle.sample.persistence.sh.Promotion_;
import com.github.jinahya.oracle.sample.persistence.sh.Sales;
import com.github.jinahya.oracle.sample.persistence.sh.Sales_;
import com.github.jinahya.oracle.sample.persistence.sh.Time_;
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
 * Answers {@code SH_SALES_B_01} of {@code src/test/sql-exercises/SALES/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_SALES_B_01: A day's takings through one channel")
@Slf4j
class SH_SALES_B_01_PersistenceIT {

    private static final LocalDateTime DAY = LocalDate.of(2021, 6, 5).atStartOfDay();

    private static final Long CHANNEL_ID = 4L; // Internet

    private static void verify(final List<Sales> list) {
        list.forEach(s -> log.debug("{}", s));
        assertThat(list)
                .extracting(s -> s.getId_().getProdId(), s -> s.getId_().getCustId(),
                            s -> s.getId_().getPromoId(), Sales::getQuantitySold,
                            s -> s.getAmountSold().doubleValue())
                .containsExactly(
                        tuple(120, 148L, 999, 1, 7.61),
                        tuple(120, 829L, 999, 1, 7.61),
                        tuple(120, 4193L, 999, 1, 7.61),
                        tuple(120, 7793L, 999, 1, 7.61));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT s
                                        FROM Sales s
                                        WHERE s.time.timeId = :day
                                          AND s.channel.channelId = :channelId
                                        ORDER BY s.amountSold DESC, s.product.prodId,
                                                 s.customer.custId, s.promotion.promoId""",
                                Sales.class
                        )
                        .setParameter("day", DAY)
                        .setParameter("channelId", CHANNEL_ID)
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Sales.class);
            final var s = cq.from(Sales.class);
            cq.select(s)
                    .where(cb.equal(s.get(Sales_.time).get(Time_.timeId),
                                    cb.parameter(LocalDateTime.class, "day")),
                           cb.equal(s.get(Sales_.channel).get(Channel_.channelId),
                                    cb.parameter(Long.class, "channelId")))
                    .orderBy(cb.desc(s.get(Sales_.amountSold)),
                             cb.asc(s.get(Sales_.product).get(Product_.prodId)),
                             cb.asc(s.get(Sales_.customer).get(Customer_.custId)),
                             cb.asc(s.get(Sales_.promotion).get(Promotion_.promoId)));
            return em.createQuery(cq)
                    .setParameter("day", DAY)
                    .setParameter("channelId", CHANNEL_ID)
                    .getResultList();
        });
        verify(list);
    }
}
