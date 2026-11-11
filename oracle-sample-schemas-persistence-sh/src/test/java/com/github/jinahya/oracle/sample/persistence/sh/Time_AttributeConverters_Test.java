package com.github.jinahya.oracle.sample.persistence.sh;

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

import com.github.jinahya.persistence.more.converter.javatime.DayOfWeekConverter;
import com.github.jinahya.persistence.more.converter.javatime.MonthConverter;
import com.github.jinahya.persistence.more.converter.javatime.YearMonthConverter;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests the converters of {@code jinahya-persistence-more} which the {@code sh-test} and
 * {@code sh-it} units list for the text columns of the {@value Time#TABLE_NAME} table, against the
 * form those columns hold.
 */
class Time_AttributeConverters_Test {

    @DisplayName("DAY_NAME <-> DayOfWeek")
    @Nested
    class OfDayOfWeek_Test {

        private final DayOfWeekConverter.OfString converter = new DayOfWeekConverter.OfString();

        @Test
        void convertToDatabaseColumn_Null_Null() {
            assertThat(converter.convertToDatabaseColumn(null)).isNull();
        }

        @ParameterizedTest
        @EnumSource(DayOfWeek.class)
        void convertToDatabaseColumn_FullEnglishName_(final DayOfWeek attribute) {
            final var dbData = converter.convertToDatabaseColumn(attribute);
            assertThat(dbData)
                    .isNotNull()
                    .hasSizeBetween(6, 9) // the TIMES.DAY_NAME column is VARCHAR2(9)
                    .isEqualToIgnoringCase(attribute.name());
            assertThat(Character.isUpperCase(dbData.charAt(0))).isTrue();
            assertThat(dbData.substring(1)).isLowerCase();
        }

        @Test
        void convertToEntityAttribute_Null_Null() {
            assertThat(converter.convertToEntityAttribute(null)).isNull();
        }

        @ParameterizedTest
        @EnumSource(DayOfWeek.class)
        void convertToEntityAttribute_RoundTrip_(final DayOfWeek attribute) {
            final var dbData = converter.convertToDatabaseColumn(attribute);
            assertThat(converter.convertToEntityAttribute(dbData)).isSameAs(attribute);
        }

        @Test
        void convertToEntityAttribute_DateTimeParseException_NotADayName() {
            assertThatThrownBy(() -> converter.convertToEntityAttribute("Someday"))
                    .isInstanceOf(DateTimeParseException.class);
        }
    }

    @DisplayName("CALENDAR_MONTH_NAME, FISCAL_MONTH_NAME <-> Month")
    @Nested
    class OfMonth_Test {

        private final MonthConverter.OfString converter = new MonthConverter.OfString();

        @Test
        void convertToDatabaseColumn_Null_Null() {
            assertThat(converter.convertToDatabaseColumn(null)).isNull();
        }

        @ParameterizedTest
        @EnumSource(Month.class)
        void convertToDatabaseColumn_FullEnglishName_(final Month attribute) {
            final var dbData = converter.convertToDatabaseColumn(attribute);
            assertThat(dbData)
                    .isNotNull()
                    // the TIMES.CALENDAR_MONTH_NAME and FISCAL_MONTH_NAME columns are VARCHAR2(9)
                    .hasSizeBetween(3, 9)
                    .isEqualToIgnoringCase(attribute.name());
            assertThat(Character.isUpperCase(dbData.charAt(0))).isTrue();
            assertThat(dbData.substring(1)).isLowerCase();
        }

        @Test
        void convertToEntityAttribute_Null_Null() {
            assertThat(converter.convertToEntityAttribute(null)).isNull();
        }

        @ParameterizedTest
        @EnumSource(Month.class)
        void convertToEntityAttribute_RoundTrip_(final Month attribute) {
            final var dbData = converter.convertToDatabaseColumn(attribute);
            assertThat(converter.convertToEntityAttribute(dbData)).isSameAs(attribute);
        }

        @Test
        void convertToEntityAttribute_DateTimeParseException_NotAMonthName() {
            assertThatThrownBy(() -> converter.convertToEntityAttribute("Smarch"))
                    .isInstanceOf(DateTimeParseException.class);
        }
    }

    @DisplayName("CALENDAR_MONTH_DESC, FISCAL_MONTH_DESC <-> YearMonth")
    @Nested
    class OfYearMonth_Test {

        private final YearMonthConverter.OfString converter = new YearMonthConverter.OfString();

        @Test
        void convertToDatabaseColumn_Null_Null() {
            assertThat(converter.convertToDatabaseColumn(null)).isNull();
        }

        @Test
        void convertToDatabaseColumn_IsoText_() {
            assertThat(converter.convertToDatabaseColumn(YearMonth.of(2019, 1)))
                    .isEqualTo("2019-01")
                    // the TIMES.CALENDAR_MONTH_DESC and FISCAL_MONTH_DESC columns are VARCHAR2(8)
                    .hasSize(7);
        }

        @Test
        void convertToEntityAttribute_Null_Null() {
            assertThat(converter.convertToEntityAttribute(null)).isNull();
        }

        @ParameterizedTest
        @ValueSource(strings = {"2019-01", "2023-12", "2024-01"}) // the range the shipped data has
        void convertToEntityAttribute_RoundTrip_(final String dbData) {
            final var attribute = converter.convertToEntityAttribute(dbData);
            assertThat(converter.convertToDatabaseColumn(attribute)).isEqualTo(dbData);
        }

        @ParameterizedTest
        @ValueSource(strings = {"2019-1", "201901", "2019-13", "2019-Q1", "January 2019"})
        void convertToEntityAttribute_DateTimeParseException_NotIsoText(final String dbData) {
            assertThatThrownBy(() -> converter.convertToEntityAttribute(dbData))
                    .isInstanceOf(DateTimeParseException.class);
        }
    }
}
