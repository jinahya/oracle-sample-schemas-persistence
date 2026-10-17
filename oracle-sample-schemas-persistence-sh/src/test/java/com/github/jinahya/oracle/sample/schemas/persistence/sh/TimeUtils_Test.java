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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;

/**
 * A class for testing {@link TimeUtils}, a nested class per method.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class TimeUtils_Test {

    /**
     * Returns a new instance whose five text attributes under test all hold the specified value; the other attributes
     * are whatever the randomizer gave.
     */
    private static Time timeOf(final String value) {
        final var time = new Time_Randomizer().get();
        time.setDayName(value);
        time.setCalendarMonthDesc(value);
        time.setFiscalMonthDesc(value);
        time.setCalendarMonthName(value);
        time.setFiscalMonthName(value);
        return time;
    }

    // --------------------------------------------------------------------------------------------------------- dayName

    @DisplayName("getDayNameAsMapped(time, mapper)")
    @Nested
    class GetDayNameAsMapped_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.getDayNameAsMapped(null, Function.identity()))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullPointerException_NullMapper() {
            assertThatThrownBy(() -> TimeUtils.getDayNameAsMapped(timeOf("Monday"), null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullAttribute() {
            assertThat(TimeUtils.<Object>getDayNameAsMapped(timeOf(null), v -> fail("mapper called"))).isNull();
        }

        @Test
        void _Mapped_() {
            assertThat(TimeUtils.getDayNameAsMapped(timeOf("Monday"), String::length)).isEqualTo("Monday".length());
        }
    }

    @DisplayName("setDayNameFromMapped(time, dayName, mapper)")
    @Nested
    class SetDayNameFromMapped_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.setDayNameFromMapped(null, "Monday", Function.identity()))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullPointerException_NullMapper() {
            assertThatThrownBy(() -> TimeUtils.setDayNameFromMapped(timeOf(null), "Monday", null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullAttribute_NullValue() {
            final var time = timeOf("Monday");
            TimeUtils.setDayNameFromMapped(time, null, v -> fail("mapper called"));
            assertThat(time.getDayName()).isNull();
        }

        @Test
        void _Set_() {
            final var time = timeOf(null);
            TimeUtils.setDayNameFromMapped(time, DayOfWeek.MONDAY, Object::toString);
            assertThat(time.getDayName()).isEqualTo(DayOfWeek.MONDAY.toString());
        }
    }

    @DisplayName("getDayNameAsDayOfWeek(time)")
    @Nested
    class GetDayNameAsDayOfWeek_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.getDayNameAsDayOfWeek(null)).isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullAttribute() {
            assertThat(TimeUtils.getDayNameAsDayOfWeek(timeOf(null))).isNull();
        }

        @Test
        void _Parsed_() {
            assertThat(TimeUtils.getDayNameAsDayOfWeek(timeOf("Monday"))).isEqualTo(DayOfWeek.MONDAY);
        }

        @Test
        void _IllegalArgumentException_NotParsable() {
            assertThatThrownBy(() -> TimeUtils.getDayNameAsDayOfWeek(timeOf("Smarch")))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("setDayNameFromDayOfWeek(time, dayOfWeek)")
    @Nested
    class SetDayNameFromDayOfWeek_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.setDayNameFromDayOfWeek(null, DayOfWeek.MONDAY))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullAttribute_NullValue() {
            final var time = timeOf("Monday");
            TimeUtils.setDayNameFromDayOfWeek(time, null);
            assertThat(time.getDayName()).isNull();
        }

        @Test
        void _ColumnForm_() {
            final var time = timeOf(null);
            TimeUtils.setDayNameFromDayOfWeek(time, DayOfWeek.MONDAY);
            assertThat(time.getDayName()).isEqualTo("Monday");
        }

        @ParameterizedTest
        @EnumSource(DayOfWeek.class)
        void _RoundTrip_(final DayOfWeek value) {
            final var time = timeOf(null);
            TimeUtils.setDayNameFromDayOfWeek(time, value);
            assertThat(TimeUtils.getDayNameAsDayOfWeek(time)).isSameAs(value);
        }
    }

    // ----------------------------------------------------------------------------------------------- calendarMonthDesc

    @DisplayName("getCalendarMonthDescAsMapped(time, mapper)")
    @Nested
    class GetCalendarMonthDescAsMapped_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.getCalendarMonthDescAsMapped(null, Function.identity()))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullPointerException_NullMapper() {
            assertThatThrownBy(() -> TimeUtils.getCalendarMonthDescAsMapped(timeOf("2019-01"), null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullAttribute() {
            assertThat(TimeUtils.<Object>getCalendarMonthDescAsMapped(timeOf(null), v -> fail("mapper called")))
                    .isNull();
        }

        @Test
        void _Mapped_() {
            assertThat(TimeUtils.getCalendarMonthDescAsMapped(timeOf("2019-01"), String::length))
                    .isEqualTo("2019-01".length());
        }
    }

    @DisplayName("setCalendarMonthDescFromMapped(time, calendarMonthDesc, mapper)")
    @Nested
    class SetCalendarMonthDescFromMapped_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.setCalendarMonthDescFromMapped(null, "2019-01", Function.identity()))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullPointerException_NullMapper() {
            assertThatThrownBy(() -> TimeUtils.setCalendarMonthDescFromMapped(timeOf(null), "2019-01", null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullAttribute_NullValue() {
            final var time = timeOf("2019-01");
            TimeUtils.setCalendarMonthDescFromMapped(time, null, v -> fail("mapper called"));
            assertThat(time.getCalendarMonthDesc()).isNull();
        }

        @Test
        void _Set_() {
            final var time = timeOf(null);
            TimeUtils.setCalendarMonthDescFromMapped(time, YearMonth.of(2019, 1), Object::toString);
            assertThat(time.getCalendarMonthDesc()).isEqualTo(YearMonth.of(2019, 1).toString());
        }
    }

    @DisplayName("getCalendarMonthDescAsYearMonth(time)")
    @Nested
    class GetCalendarMonthDescAsYearMonth_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.getCalendarMonthDescAsYearMonth(null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullAttribute() {
            assertThat(TimeUtils.getCalendarMonthDescAsYearMonth(timeOf(null))).isNull();
        }

        @Test
        void _Parsed_() {
            assertThat(TimeUtils.getCalendarMonthDescAsYearMonth(timeOf("2019-01"))).isEqualTo(YearMonth.of(2019, 1));
        }

        @Test
        void _DateTimeParseException_NotParsable() {
            assertThatThrownBy(() -> TimeUtils.getCalendarMonthDescAsYearMonth(timeOf("2019-Q1")))
                    .isInstanceOf(DateTimeParseException.class);
        }
    }

    @DisplayName("setCalendarMonthDescFromYearMonth(time, yearMonth)")
    @Nested
    class SetCalendarMonthDescFromYearMonth_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.setCalendarMonthDescFromYearMonth(null, YearMonth.of(2019, 1)))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullAttribute_NullValue() {
            final var time = timeOf("2019-01");
            TimeUtils.setCalendarMonthDescFromYearMonth(time, null);
            assertThat(time.getCalendarMonthDesc()).isNull();
        }

        @Test
        void _ColumnForm_() {
            final var time = timeOf(null);
            TimeUtils.setCalendarMonthDescFromYearMonth(time, YearMonth.of(2019, 1));
            assertThat(time.getCalendarMonthDesc()).isEqualTo("2019-01");
        }

        @ParameterizedTest
        @ValueSource(strings = {"2019-01", "2023-12", "2024-01"})
        void _RoundTrip_(final String text) {
            final var time = timeOf(null);
            TimeUtils.setCalendarMonthDescFromYearMonth(time, YearMonth.parse(text));
            assertThat(TimeUtils.getCalendarMonthDescAsYearMonth(time)).isEqualTo(YearMonth.parse(text));
            assertThat(time.getCalendarMonthDesc()).isEqualTo(text);
        }
    }

    // ------------------------------------------------------------------------------------------------- fiscalMonthDesc

    @DisplayName("getFiscalMonthDescAsMapped(time, mapper)")
    @Nested
    class GetFiscalMonthDescAsMapped_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.getFiscalMonthDescAsMapped(null, Function.identity()))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullPointerException_NullMapper() {
            assertThatThrownBy(() -> TimeUtils.getFiscalMonthDescAsMapped(timeOf("2024-01"), null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullAttribute() {
            assertThat(TimeUtils.<Object>getFiscalMonthDescAsMapped(timeOf(null), v -> fail("mapper called"))).isNull();
        }

        @Test
        void _Mapped_() {
            assertThat(TimeUtils.getFiscalMonthDescAsMapped(timeOf("2024-01"), String::length))
                    .isEqualTo("2024-01".length());
        }
    }

    @DisplayName("setFiscalMonthDescFromMapped(time, fiscalMonthDesc, mapper)")
    @Nested
    class SetFiscalMonthDescFromMapped_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.setFiscalMonthDescFromMapped(null, "2024-01", Function.identity()))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullPointerException_NullMapper() {
            assertThatThrownBy(() -> TimeUtils.setFiscalMonthDescFromMapped(timeOf(null), "2024-01", null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullAttribute_NullValue() {
            final var time = timeOf("2024-01");
            TimeUtils.setFiscalMonthDescFromMapped(time, null, v -> fail("mapper called"));
            assertThat(time.getFiscalMonthDesc()).isNull();
        }

        @Test
        void _Set_() {
            final var time = timeOf(null);
            TimeUtils.setFiscalMonthDescFromMapped(time, YearMonth.of(2024, 1), Object::toString);
            assertThat(time.getFiscalMonthDesc()).isEqualTo(YearMonth.of(2024, 1).toString());
        }
    }

    @DisplayName("getFiscalMonthDescAsYearMonth(time)")
    @Nested
    class GetFiscalMonthDescAsYearMonth_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.getFiscalMonthDescAsYearMonth(null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullAttribute() {
            assertThat(TimeUtils.getFiscalMonthDescAsYearMonth(timeOf(null))).isNull();
        }

        @Test
        void _Parsed_() {
            assertThat(TimeUtils.getFiscalMonthDescAsYearMonth(timeOf("2024-01"))).isEqualTo(YearMonth.of(2024, 1));
        }

        @Test
        void _DateTimeParseException_NotParsable() {
            assertThatThrownBy(() -> TimeUtils.getFiscalMonthDescAsYearMonth(timeOf("2019-Q1")))
                    .isInstanceOf(DateTimeParseException.class);
        }
    }

    @DisplayName("setFiscalMonthDescFromYearMonth(time, yearMonth)")
    @Nested
    class SetFiscalMonthDescFromYearMonth_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.setFiscalMonthDescFromYearMonth(null, YearMonth.of(2024, 1)))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullAttribute_NullValue() {
            final var time = timeOf("2024-01");
            TimeUtils.setFiscalMonthDescFromYearMonth(time, null);
            assertThat(time.getFiscalMonthDesc()).isNull();
        }

        @Test
        void _ColumnForm_() {
            final var time = timeOf(null);
            TimeUtils.setFiscalMonthDescFromYearMonth(time, YearMonth.of(2024, 1));
            assertThat(time.getFiscalMonthDesc()).isEqualTo("2024-01");
        }

        @ParameterizedTest
        @ValueSource(strings = {"2019-01", "2023-12", "2024-01"})
        void _RoundTrip_(final String text) {
            final var time = timeOf(null);
            TimeUtils.setFiscalMonthDescFromYearMonth(time, YearMonth.parse(text));
            assertThat(TimeUtils.getFiscalMonthDescAsYearMonth(time)).isEqualTo(YearMonth.parse(text));
            assertThat(time.getFiscalMonthDesc()).isEqualTo(text);
        }
    }

    // ----------------------------------------------------------------------------------------------- calendarMonthName

    @DisplayName("getCalendarMonthNameAsMapped(time, mapper)")
    @Nested
    class GetCalendarMonthNameAsMapped_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.getCalendarMonthNameAsMapped(null, Function.identity()))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullPointerException_NullMapper() {
            assertThatThrownBy(() -> TimeUtils.getCalendarMonthNameAsMapped(timeOf("January"), null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullAttribute() {
            assertThat(TimeUtils.<Object>getCalendarMonthNameAsMapped(timeOf(null), v -> fail("mapper called")))
                    .isNull();
        }

        @Test
        void _Mapped_() {
            assertThat(TimeUtils.getCalendarMonthNameAsMapped(timeOf("January"), String::length))
                    .isEqualTo("January".length());
        }
    }

    @DisplayName("setCalendarMonthNameFromMapped(time, calendarMonthName, mapper)")
    @Nested
    class SetCalendarMonthNameFromMapped_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.setCalendarMonthNameFromMapped(null, "January", Function.identity()))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullPointerException_NullMapper() {
            assertThatThrownBy(() -> TimeUtils.setCalendarMonthNameFromMapped(timeOf(null), "January", null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullAttribute_NullValue() {
            final var time = timeOf("January");
            TimeUtils.setCalendarMonthNameFromMapped(time, null, v -> fail("mapper called"));
            assertThat(time.getCalendarMonthName()).isNull();
        }

        @Test
        void _Set_() {
            final var time = timeOf(null);
            TimeUtils.setCalendarMonthNameFromMapped(time, Month.JANUARY, Object::toString);
            assertThat(time.getCalendarMonthName()).isEqualTo(Month.JANUARY.toString());
        }
    }

    @DisplayName("getCalendarMonthNameAsMonth(time)")
    @Nested
    class GetCalendarMonthNameAsMonth_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.getCalendarMonthNameAsMonth(null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullAttribute() {
            assertThat(TimeUtils.getCalendarMonthNameAsMonth(timeOf(null))).isNull();
        }

        @Test
        void _Parsed_() {
            assertThat(TimeUtils.getCalendarMonthNameAsMonth(timeOf("January"))).isEqualTo(Month.JANUARY);
        }

        @Test
        void _IllegalArgumentException_NotParsable() {
            assertThatThrownBy(() -> TimeUtils.getCalendarMonthNameAsMonth(timeOf("Smarch")))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("setCalendarMonthNameFromMonth(time, month)")
    @Nested
    class SetCalendarMonthNameFromMonth_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.setCalendarMonthNameFromMonth(null, Month.JANUARY))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullAttribute_NullValue() {
            final var time = timeOf("January");
            TimeUtils.setCalendarMonthNameFromMonth(time, null);
            assertThat(time.getCalendarMonthName()).isNull();
        }

        @Test
        void _ColumnForm_() {
            final var time = timeOf(null);
            TimeUtils.setCalendarMonthNameFromMonth(time, Month.JANUARY);
            assertThat(time.getCalendarMonthName()).isEqualTo("January");
        }

        @ParameterizedTest
        @EnumSource(Month.class)
        void _RoundTrip_(final Month value) {
            final var time = timeOf(null);
            TimeUtils.setCalendarMonthNameFromMonth(time, value);
            assertThat(TimeUtils.getCalendarMonthNameAsMonth(time)).isSameAs(value);
        }
    }

    // ------------------------------------------------------------------------------------------------- fiscalMonthName

    @DisplayName("getFiscalMonthNameAsMapped(time, mapper)")
    @Nested
    class GetFiscalMonthNameAsMapped_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.getFiscalMonthNameAsMapped(null, Function.identity()))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullPointerException_NullMapper() {
            assertThatThrownBy(() -> TimeUtils.getFiscalMonthNameAsMapped(timeOf("September"), null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullAttribute() {
            assertThat(TimeUtils.<Object>getFiscalMonthNameAsMapped(timeOf(null), v -> fail("mapper called"))).isNull();
        }

        @Test
        void _Mapped_() {
            assertThat(TimeUtils.getFiscalMonthNameAsMapped(timeOf("September"), String::length))
                    .isEqualTo("September".length());
        }
    }

    @DisplayName("setFiscalMonthNameFromMapped(time, fiscalMonthName, mapper)")
    @Nested
    class SetFiscalMonthNameFromMapped_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.setFiscalMonthNameFromMapped(null, "September", Function.identity()))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullPointerException_NullMapper() {
            assertThatThrownBy(() -> TimeUtils.setFiscalMonthNameFromMapped(timeOf(null), "September", null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullAttribute_NullValue() {
            final var time = timeOf("September");
            TimeUtils.setFiscalMonthNameFromMapped(time, null, v -> fail("mapper called"));
            assertThat(time.getFiscalMonthName()).isNull();
        }

        @Test
        void _Set_() {
            final var time = timeOf(null);
            TimeUtils.setFiscalMonthNameFromMapped(time, Month.SEPTEMBER, Object::toString);
            assertThat(time.getFiscalMonthName()).isEqualTo(Month.SEPTEMBER.toString());
        }
    }

    @DisplayName("getFiscalMonthNameAsMonth(time)")
    @Nested
    class GetFiscalMonthNameAsMonth_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.getFiscalMonthNameAsMonth(null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullAttribute() {
            assertThat(TimeUtils.getFiscalMonthNameAsMonth(timeOf(null))).isNull();
        }

        @Test
        void _Parsed_() {
            assertThat(TimeUtils.getFiscalMonthNameAsMonth(timeOf("September"))).isEqualTo(Month.SEPTEMBER);
        }

        @Test
        void _IllegalArgumentException_NotParsable() {
            assertThatThrownBy(() -> TimeUtils.getFiscalMonthNameAsMonth(timeOf("Smarch")))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("setFiscalMonthNameFromMonth(time, month)")
    @Nested
    class SetFiscalMonthNameFromMonth_Test {

        @Test
        void _NullPointerException_NullTime() {
            assertThatThrownBy(() -> TimeUtils.setFiscalMonthNameFromMonth(null, Month.SEPTEMBER))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _NullAttribute_NullValue() {
            final var time = timeOf("September");
            TimeUtils.setFiscalMonthNameFromMonth(time, null);
            assertThat(time.getFiscalMonthName()).isNull();
        }

        @Test
        void _ColumnForm_() {
            final var time = timeOf(null);
            TimeUtils.setFiscalMonthNameFromMonth(time, Month.SEPTEMBER);
            assertThat(time.getFiscalMonthName()).isEqualTo("September");
        }

        @ParameterizedTest
        @EnumSource(Month.class)
        void _RoundTrip_(final Month value) {
            final var time = timeOf(null);
            TimeUtils.setFiscalMonthNameFromMonth(time, value);
            assertThat(TimeUtils.getFiscalMonthNameAsMonth(time)).isSameAs(value);
        }
    }
}
