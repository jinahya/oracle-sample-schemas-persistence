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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.YearMonth;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * A class for testing {@link TimeMonthDescAttributeConverter}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class TimeMonthDescAttributeConverter_Test {

    @Test
    void convertToDatabaseColumn_Null_Null() {
        assertThat(new TimeMonthDescAttributeConverter().convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void convertToDatabaseColumn_IsoText_() {
        assertThat(new TimeMonthDescAttributeConverter().convertToDatabaseColumn(YearMonth.of(2019, 1)))
                .isEqualTo("2019-01")
                .hasSize(7); // the TIMES.CALENDAR_MONTH_DESC and FISCAL_MONTH_DESC columns are VARCHAR2(8)
    }

    @Test
    void convertToEntityAttribute_Null_Null() {
        assertThat(new TimeMonthDescAttributeConverter().convertToEntityAttribute(null)).isNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"2019-01", "2023-12", "2024-01"}) // the range the shipped data holds
    void convertToEntityAttribute_RoundTrip_(final String dbData) {
        final var converter = new TimeMonthDescAttributeConverter();
        assertThat(converter.convertToDatabaseColumn(converter.convertToEntityAttribute(dbData)))
                .isEqualTo(dbData);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2019-1", "201901", "2019-13", "2019-Q1", "January 2019"})
    void convertToEntityAttribute_DateTimeParseException_NotIsoText(final String dbData) {
        assertThatThrownBy(() -> new TimeMonthDescAttributeConverter().convertToEntityAttribute(dbData))
                .isInstanceOf(DateTimeParseException.class);
    }
}
