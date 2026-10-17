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

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.github.jinahya.oracle.sample.schemas.persistence.sh._Entity_Persistence_IT_Producer.__ItPU;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * A class for testing the aggregate methods of {@link SalesUtils} against the {@code sh-it} unit, a nested class per
 * method; it reads the {@value Sales#TABLE_NAME} table as installed and writes nothing. Each statistic is checked
 * against the JPQL aggregate of the same rows.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@AddBeanClasses(_Entity_Persistence_IT_Producer.class)
@EnableAutoWeld
class SalesUtils_PersistenceIT {

    private long countAll() {
        return entityManager.createQuery("SELECT COUNT(e) FROM Sales e", Long.class).getSingleResult();
    }

    private Integer anyProdId() {
        return entityManager
                .createQuery("SELECT e.id.prodId FROM Sales e", Integer.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    @BeforeEach
    void assumeRows() {
        assumeTrue(countAll() > 0L, () -> Sales.TABLE_NAME + " holds no row");
    }

    // ----------------------------------------------------------------------------------------- quantitySold/statistics

    @DisplayName("getQuantitySoldStatistics(entityManager, where)")
    @Nested
    class GetQuantitySoldStatistics_IT {

        @Test
        void _MatchesJpql_All() {
            final var expected = entityManager.createQuery(
                    "SELECT COUNT(e), SUM(e.quantitySold), MIN(e.quantitySold), MAX(e.quantitySold),"
                    + " AVG(e.quantitySold) FROM Sales e", Object[].class).getSingleResult();
            final var actual = SalesUtils.getQuantitySoldStatistics(entityManager, null);
            assertThat(actual.getCount()).isEqualTo(((Number) expected[0]).longValue());
            assertThat(actual.getSum()).isEqualTo(((Number) expected[1]).longValue());
            assertThat(actual.getMin()).isEqualTo(((Number) expected[2]).longValue());
            assertThat(actual.getMax()).isEqualTo(((Number) expected[3]).longValue());
            assertThat(actual.getAverage()).isCloseTo(((Number) expected[4]).doubleValue(), offset(1e-9));
        }

        @Test
        void _MatchesJpql_ByProdId() {
            final var prodId = anyProdId();
            final var expected = entityManager.createQuery(
                    "SELECT COUNT(e), SUM(e.quantitySold) FROM Sales e WHERE e.id.prodId = :prodId", Object[].class)
                    .setParameter("prodId", prodId)
                    .getSingleResult();
            final var actual = SalesUtils.getQuantitySoldStatistics(
                    entityManager, (b, r) -> b.equal(r.get(Sales_.id).get(SalesId_.prodId), prodId));
            assertThat(actual.getCount()).isEqualTo(((Number) expected[0]).longValue()).isPositive();
            assertThat(actual.getSum()).isEqualTo(((Number) expected[1]).longValue());
        }

        @Test
        void _Empty_NoneSelected() {
            final var actual = SalesUtils.getQuantitySoldStatistics(entityManager, (b, r) -> b.disjunction());
            assertThat(actual.getCount()).isZero();
        }
    }

    // ------------------------------------------------------------------------------------------- amountSold/statistics

    @DisplayName("getAmountSoldStatistics(entityManager, where)")
    @Nested
    class GetAmountSoldStatistics_IT {

        @Test
        void _MatchesJpql_All() {
            final var expected = entityManager.createQuery(
                    "SELECT COUNT(e), SUM(e.amountSold), MIN(e.amountSold), MAX(e.amountSold) FROM Sales e",
                    Object[].class).getSingleResult();
            final var actual = SalesUtils.getAmountSoldStatistics(entityManager, null);
            assertThat(actual.count()).isEqualTo(((Number) expected[0]).longValue());
            assertThat(actual.sum()).isEqualByComparingTo(new BigDecimal(expected[1].toString()));
            assertThat(actual.min()).isEqualByComparingTo(new BigDecimal(expected[2].toString()));
            assertThat(actual.max()).isEqualByComparingTo(new BigDecimal(expected[3].toString()));
            assertThat(actual.average(MathContext.DECIMAL64)).isEqualByComparingTo(
                    actual.sum().divide(BigDecimal.valueOf(actual.count()), MathContext.DECIMAL64));
        }

        @Test
        void _Empty_NoneSelected() {
            final var actual = SalesUtils.getAmountSoldStatistics(entityManager, (b, r) -> b.disjunction());
            assertThat(actual.count()).isZero();
            assertThat(actual.sum()).isEqualByComparingTo(BigDecimal.ZERO);
            assertThat(actual.min()).isNull();
            assertThat(actual.max()).isNull();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__ItPU
    @Inject
    private EntityManager entityManager;
}
