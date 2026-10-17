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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * A class for testing {@link SalesUtils}, a nested class per method.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class SalesUtils_Test {

    private static Sales salesOf(final Integer quantitySold) {
        final var sales = new Sales_Randomizer().get();
        sales.setQuantitySold(quantitySold);
        return sales;
    }

    private static Sales salesOfAmount(final BigDecimal amountSold) {
        final var sales = new Sales_Randomizer().get();
        sales.setAmountSold(amountSold);
        return sales;
    }

    /**
     * Wraps the specified list as an {@link Iterable} which is not a {@link java.util.Collection}, so that a call
     * resolves to the {@code Iterable} overload.
     */
    private static <T> Iterable<T> iterableOf(final List<T> list) {
        return list::iterator;
    }

    // ---------------------------------------------------------------------------------------------------- quantitySold

    @DisplayName("getTotalQuantitySold(sales)")
    @Nested
    class GetTotalQuantitySold_Test {

        @Test
        void _NullPointerException_Null() {
            assertThatThrownBy(() -> SalesUtils.getTotalQuantitySold((List<Sales>) null))
                    .isInstanceOf(NullPointerException.class);
            assertThatThrownBy(() -> SalesUtils.getTotalQuantitySold((Iterable<Sales>) null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullPointerException_ContainsNull() {
            assertThatThrownBy(() -> SalesUtils.getTotalQuantitySold(Arrays.asList(salesOf(1), null)))
                    .isInstanceOf(NullPointerException.class);
            assertThatThrownBy(() -> SalesUtils.getTotalQuantitySold(iterableOf(Arrays.asList(salesOf(1), null))))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullPointerException_NullAttribute() {
            assertThatThrownBy(() -> SalesUtils.getTotalQuantitySold(List.of(salesOf(1), salesOf(null))))
                    .isInstanceOf(NullPointerException.class);
            assertThatThrownBy(() -> SalesUtils.getTotalQuantitySold(iterableOf(List.of(salesOf(null)))))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Zero_Empty() {
            assertThat(SalesUtils.getTotalQuantitySold(List.of())).isZero();
            assertThat(SalesUtils.getTotalQuantitySold(iterableOf(List.of()))).isZero();
        }

        @Test
        void _Sum_() {
            final var sales = List.of(salesOf(1), salesOf(2), salesOf(3));
            assertThat(SalesUtils.getTotalQuantitySold(sales)).isEqualTo(6L);
            assertThat(SalesUtils.getTotalQuantitySold(iterableOf(sales))).isEqualTo(6L);
        }
    }

    // ------------------------------------------------------------------------------------------------------ amountSold

    @DisplayName("getTotalAmountSold(sales)")
    @Nested
    class GetTotalAmountSold_Test {

        @Test
        void _NullPointerException_Null() {
            assertThatThrownBy(() -> SalesUtils.getTotalAmountSold((List<Sales>) null))
                    .isInstanceOf(NullPointerException.class);
            assertThatThrownBy(() -> SalesUtils.getTotalAmountSold((Iterable<Sales>) null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullPointerException_ContainsNull() {
            final var sales = Arrays.asList(salesOfAmount(BigDecimal.ONE), null);
            assertThatThrownBy(() -> SalesUtils.getTotalAmountSold(sales))
                    .isInstanceOf(NullPointerException.class);
            assertThatThrownBy(() -> SalesUtils.getTotalAmountSold(iterableOf(sales)))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullPointerException_NullAttribute() {
            final var sales = List.of(salesOfAmount(BigDecimal.ONE), salesOfAmount(null));
            assertThatThrownBy(() -> SalesUtils.getTotalAmountSold(sales))
                    .isInstanceOf(NullPointerException.class);
            assertThatThrownBy(() -> SalesUtils.getTotalAmountSold(iterableOf(sales)))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Zero_Empty() {
            assertThat(SalesUtils.getTotalAmountSold(List.of())).isEqualByComparingTo(BigDecimal.ZERO);
            assertThat(SalesUtils.getTotalAmountSold(iterableOf(List.of()))).isEqualByComparingTo(BigDecimal.ZERO);
        }

        @Test
        void _Sum_() {
            final var sales = List.of(salesOfAmount(new BigDecimal("6.40")), salesOfAmount(new BigDecimal("1782.72")));
            assertThat(SalesUtils.getTotalAmountSold(sales)).isEqualByComparingTo(new BigDecimal("1789.12"));
            assertThat(SalesUtils.getTotalAmountSold(iterableOf(sales)))
                    .isEqualByComparingTo(new BigDecimal("1789.12"));
        }
    }
}
