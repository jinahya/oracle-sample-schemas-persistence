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

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * A converter between {@link Month} and the {@value Time#COLUMN_NAME_CALENDAR_MONTH_NAME} and
 * {@value Time#COLUMN_NAME_FISCAL_MONTH_NAME} columns of the {@value Time#TABLE_NAME} table, which hold the full
 * English name of the month, {@code January} for {@link Month#JANUARY}.
 * <p>
 * Nothing applies this converter yet. {@link Time#getCalendarMonthName()} and {@link Time#getFiscalMonthName()} keep
 * the columns as {@code String}s, and {@link TimeUtils#getCalendarMonthNameAsMonth(Time)} and
 * {@link TimeUtils#getFiscalMonthNameAsMonth(Time)} derive the {@link Month} on demand. Mapping the attributes as a
 * {@link Month} with this converter, through {@link jakarta.persistence.Convert @Convert}, is left as an exercise; see
 * the issue linked below for what else has to change with it.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Time#getCalendarMonthName()
 * @see Time#getFiscalMonthName()
 * @see TimeUtils#getCalendarMonthNameAsMonth(Time)
 * @see TimeUtils#getFiscalMonthNameAsMonth(Time)
 * @see TimeDayNameAttributeConverter
 * @see <a href="https://github.com/jinahya/oracle-sample-schemas-persistence/issues/7">
 * #7 Map TIMES.CALENDAR_MONTH_NAME and FISCAL_MONTH_NAME as
 * Month with TimeMonthNameAttributeConverter</a>
 */
@Converter(autoApply = false)
public class TimeMonthNameAttributeConverter implements AttributeConverter<Month, String> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public TimeMonthNameAttributeConverter() {
        super();
    }

    // ---------------------------------------------------------------------- jakarta.persistence.AttributeConverter

    /**
     * {@inheritDoc}
     *
     * @param attribute {@inheritDoc}
     * @return the full English name of {@code attribute}, {@code January} for {@link Month#JANUARY}; {@code null} when
     * {@code attribute} is {@code null}.
     * @see Month#getDisplayName(TextStyle, Locale)
     */
    @Override
    public String convertToDatabaseColumn(final Month attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
    }

    /**
     * {@inheritDoc}
     *
     * @param dbData {@inheritDoc}
     * @return the month whose {@link Month#name() name} equals {@code dbData}, ignoring case; {@code null} when
     * {@code dbData} is {@code null}.
     * @throws IllegalArgumentException if {@code dbData} is not the name of a {@link Month} constant.
     */
    @Override
    public Month convertToEntityAttribute(final String dbData) {
        if (dbData == null) {
            return null;
        }
        // the same locale the other direction formats with; see TimeDayNameAttributeConverter
        return Month.valueOf(dbData.toUpperCase(Locale.ENGLISH));
    }
}
