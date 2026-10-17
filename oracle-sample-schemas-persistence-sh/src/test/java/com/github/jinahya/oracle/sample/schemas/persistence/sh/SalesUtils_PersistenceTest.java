package com.github.jinahya.oracle.sample.schemas.persistence.sh;

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

import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static com.github.jinahya.oracle.sample.schemas.persistence.sh._Entity_Persistence_Test_Producer.__TestPU;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * A class for testing the aggregate methods of {@link SalesUtils} against the {@code sh-test} unit, a nested class per
 * method.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@AddBeanClasses(_Entity_Persistence_Test_Producer.class)
@EnableAutoWeld
class SalesUtils_PersistenceTest {

    private static List<Sales> selectAll(final EntityManager entityManager) {
        return entityManager.createQuery("SELECT e FROM Sales e", Sales.class).getResultList();
    }

    /**
     * Persists three random rows, flushes and clears, so that what is loaded afterwards is the rows as stored:
     * {@code AMOUNT_SOLD} is {@code NUMBER(10,2)}, and the column rounds what the randomizer gives.
     */
    private static void persistThreeAndClear(final EntityManager entityManager) {
        IntStream.range(0, 3).forEach(i -> EntityPersisterUtils.newPersistedInstanceOf(entityManager, Sales.class));
        entityManager.flush();
        entityManager.clear();
    }

    private <R> R inRolledBackTransaction(final Function<? super EntityManager, ? extends R> function) {
        return ___Persistence_TestUtils.applyInTransactionAndRollback(entityManager, function);
    }

    // ----------------------------------------------------------------------------------------- quantitySold/statistics

    @DisplayName("getQuantitySoldStatistics(entityManager, where)")
    @Nested
    class GetQuantitySoldStatistics_Test {

        @Test
        void _Empty_NoneSelected() {
            inRolledBackTransaction(em -> {
                final var statistics = SalesUtils.getQuantitySoldStatistics(em, (b, r) -> b.disjunction());
                assertThat(statistics.getCount()).isZero();
                assertThat(statistics.getSum()).isZero();
                return null;
            });
        }

        @Test
        void _MatchesLoadedRows_() {
            inRolledBackTransaction(em -> {
                persistThreeAndClear(em);
                final var expected = selectAll(em).stream().mapToLong(Sales::getQuantitySold).summaryStatistics();
                final var actual = SalesUtils.getQuantitySoldStatistics(em, null);
                assertThat(actual.getCount()).isEqualTo(expected.getCount()).isGreaterThanOrEqualTo(3L);
                assertThat(actual.getSum()).isEqualTo(expected.getSum());
                assertThat(actual.getMin()).isEqualTo(expected.getMin());
                assertThat(actual.getMax()).isEqualTo(expected.getMax());
                assertThat(actual.getAverage()).isEqualTo(expected.getAverage());
                return null;
            });
        }

        @Test
        void _Restricted_ByProdId() {
            inRolledBackTransaction(em -> {
                persistThreeAndClear(em);
                final var all = selectAll(em);
                final var prodId = SalesUtils.getIdProdId(all.get(0));
                final var expected = all.stream()
                        .filter(s -> prodId.equals(SalesUtils.getIdProdId(s)))
                        .mapToLong(Sales::getQuantitySold)
                        .summaryStatistics();
                final var actual = SalesUtils.getQuantitySoldStatistics(
                        em, (b, r) -> b.equal(r.get(Sales_.id).get(SalesId_.prodId), prodId));
                assertThat(actual.getCount()).isEqualTo(expected.getCount()).isPositive();
                assertThat(actual.getSum()).isEqualTo(expected.getSum());
                return null;
            });
        }
    }

    // ------------------------------------------------------------------------------------------- amountSold/statistics

    @DisplayName("getAmountSoldStatistics(entityManager, where)")
    @Nested
    class GetAmountSoldStatistics_Test {

        @Test
        void _Empty_NoneSelected() {
            inRolledBackTransaction(em -> {
                final var statistics = SalesUtils.getAmountSoldStatistics(em, (b, r) -> b.disjunction());
                assertThat(statistics.count()).isZero();
                assertThat(statistics.sum()).isEqualByComparingTo(BigDecimal.ZERO);
                assertThat(statistics.min()).isNull();
                assertThat(statistics.max()).isNull();
                assertThat(statistics.average(MathContext.DECIMAL64)).isEqualByComparingTo(BigDecimal.ZERO);
                return null;
            });
        }

        @Test
        void _MatchesLoadedRows_() {
            inRolledBackTransaction(em -> {
                persistThreeAndClear(em);
                final var all = selectAll(em);
                final var sum = all.stream().map(Sales::getAmountSold).reduce(BigDecimal.ZERO, BigDecimal::add);
                final var min = all.stream().map(Sales::getAmountSold).min(BigDecimal::compareTo).orElseThrow();
                final var max = all.stream().map(Sales::getAmountSold).max(BigDecimal::compareTo).orElseThrow();
                final var actual = SalesUtils.getAmountSoldStatistics(em, null);
                assertThat(actual.count()).isEqualTo(all.size()).isGreaterThanOrEqualTo(3L);
                assertThat(actual.sum()).isEqualByComparingTo(sum);
                assertThat(actual.min()).isEqualByComparingTo(min);
                assertThat(actual.max()).isEqualByComparingTo(max);
                assertThat(actual.average(MathContext.DECIMAL64))
                        .isEqualByComparingTo(sum.divide(BigDecimal.valueOf(all.size()), MathContext.DECIMAL64));
                return null;
            });
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__TestPU
    @Inject
    private EntityManager entityManager;
}
