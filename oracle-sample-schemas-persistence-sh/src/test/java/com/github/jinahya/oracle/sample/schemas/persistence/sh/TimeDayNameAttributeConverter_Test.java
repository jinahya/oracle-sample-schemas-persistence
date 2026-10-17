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
import org.junit.jupiter.params.provider.EnumSource;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * A class for testing {@link TimeDayNameAttributeConverter}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class TimeDayNameAttributeConverter_Test {

    @Test
    void convertToDatabaseColumn_Null_Null() {
        assertThat(new TimeDayNameAttributeConverter().convertToDatabaseColumn(null)).isNull();
    }

    @ParameterizedTest
    @EnumSource(DayOfWeek.class)
    void convertToDatabaseColumn_FullEnglishName_(final DayOfWeek attribute) {
        final var dbData = new TimeDayNameAttributeConverter().convertToDatabaseColumn(attribute);
        assertThat(dbData)
                .isNotNull()
                .hasSizeBetween(6, 9) // the TIMES.DAY_NAME column is VARCHAR2(9)
                .isEqualToIgnoringCase(attribute.name());
        assertThat(Character.isUpperCase(dbData.charAt(0))).isTrue();
        assertThat(dbData.substring(1)).isLowerCase();
    }

    @Test
    void convertToEntityAttribute_Null_Null() {
        assertThat(new TimeDayNameAttributeConverter().convertToEntityAttribute(null)).isNull();
    }

    @ParameterizedTest
    @EnumSource(DayOfWeek.class)
    void convertToEntityAttribute_RoundTrip_(final DayOfWeek attribute) {
        final var converter = new TimeDayNameAttributeConverter();
        assertThat(converter.convertToEntityAttribute(converter.convertToDatabaseColumn(attribute)))
                .isSameAs(attribute);
    }

    @Test
    void convertToEntityAttribute_IllegalArgumentException_NotADayName() {
        assertThatThrownBy(() -> new TimeDayNameAttributeConverter().convertToEntityAttribute("Someday"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
