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

import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;

/**
 * A class for testing {@link CalMonthSalesMvUtils}, a nested class per method.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class CalMonthSalesMvUtils_Test {

    /** Sets the named field of the specified instance reflectively, for an entity which exposes no setter for it. */
    private static <T> T withField(final T instance, final String name, final Object value) {
        try {
            final var field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(instance, value);
            return instance;
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    private static CalMonthSalesMv instanceOf(final String calendarMonthDesc) {
        return withField(new CalMonthSalesMv(), CalMonthSalesMv.ATTRIBUTE_NAME_CALENDAR_MONTH_DESC, calendarMonthDesc);
    }

    // ----------------------------------------------------------------------------------------------- calendarMonthDesc

    @DisplayName("getCalendarMonthDescAsMapped(calMonthSalesMv, mapper)")
    @Nested
    class GetCalendarMonthDescAsMapped_Test {

        @Test
        void _NullPointerException_NullEntity() {
            assertThatThrownBy(() -> CalMonthSalesMvUtils.getCalendarMonthDescAsMapped(null, Function.identity()))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullPointerException_NullMapper() {
            assertThatThrownBy(() -> CalMonthSalesMvUtils.getCalendarMonthDescAsMapped(instanceOf("2019-01"), null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullAttribute() {
            assertThat(CalMonthSalesMvUtils.<Object>getCalendarMonthDescAsMapped(instanceOf(null),
                                                                                 v -> fail("mapper called")))
                    .isNull();
        }

        @Test
        void _Mapped_() {
            assertThat(CalMonthSalesMvUtils.getCalendarMonthDescAsMapped(instanceOf("2019-01"), String::length))
                    .isEqualTo(7);
        }
    }

    @DisplayName("getCalendarMonthDescAsYearMonth(calMonthSalesMv)")
    @Nested
    class GetCalendarMonthDescAsYearMonth_Test {

        @Test
        void _NullPointerException_NullEntity() {
            assertThatThrownBy(() -> CalMonthSalesMvUtils.getCalendarMonthDescAsYearMonth(null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullAttribute() {
            assertThat(CalMonthSalesMvUtils.getCalendarMonthDescAsYearMonth(instanceOf(null))).isNull();
        }

        @Test
        void _Parsed_() {
            assertThat(CalMonthSalesMvUtils.getCalendarMonthDescAsYearMonth(instanceOf("2019-01")))
                    .isEqualTo(YearMonth.of(2019, 1));
        }

        @Test
        void _DateTimeParseException_NotParsable() {
            assertThatThrownBy(() -> CalMonthSalesMvUtils.getCalendarMonthDescAsYearMonth(instanceOf("2019-Q1")))
                    .isInstanceOf(DateTimeParseException.class);
        }
    }
}
