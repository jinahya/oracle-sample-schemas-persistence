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

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * A converter between {@link DayOfWeek} and the {@value Time#COLUMN_NAME_DAY_NAME} column of the
 * {@value Time#TABLE_NAME} table, which holds the full English name of the day, {@code Monday} for
 * {@link DayOfWeek#MONDAY}.
 * <p>
 * Nothing applies this converter yet. {@link Time#getDayName()} keeps the column as a {@code String}, and
 * {@link TimeUtils#getDayNameAsDayOfWeek(Time)} derives the {@link DayOfWeek} on demand. Mapping the attribute as a
 * {@link DayOfWeek} with this converter, through {@link jakarta.persistence.Convert @Convert}, is left as an exercise;
 * see the issue linked below for what else has to change with it.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Time#getDayName()
 * @see TimeUtils#getDayNameAsDayOfWeek(Time)
 * @see TimeUtils#setDayNameFromDayOfWeek(Time, DayOfWeek)
 * @see <a href="https://github.com/jinahya/oracle-sample-schemas-persistence/issues/6">#6 Map TIMES.DAY_NAME as
 * DayOfWeek with TimeDayNameAttributeConverter</a>
 */
@Converter(autoApply = false)
public class TimeDayNameAttributeConverter implements AttributeConverter<DayOfWeek, String> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public TimeDayNameAttributeConverter() {
        super();
    }

    // ---------------------------------------------------------------------- jakarta.persistence.AttributeConverter

    /**
     * {@inheritDoc}
     *
     * @param attribute {@inheritDoc}
     * @return the full English name of {@code attribute}, {@code Monday} for {@link DayOfWeek#MONDAY}; {@code null}
     * when {@code attribute} is {@code null}.
     * @see DayOfWeek#getDisplayName(TextStyle, Locale)
     */
    @Override
    public String convertToDatabaseColumn(final DayOfWeek attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
    }

    /**
     * {@inheritDoc}
     *
     * @param dbData {@inheritDoc}
     * @return the day of the week whose {@link DayOfWeek#name() name} equals {@code dbData}, ignoring case;
     * {@code null} when {@code dbData} is {@code null}.
     * @throws IllegalArgumentException if {@code dbData} is not the name of a {@link DayOfWeek} constant.
     */
    @Override
    public DayOfWeek convertToEntityAttribute(final String dbData) {
        if (dbData == null) {
            return null;
        }
        // the same locale the other direction formats with; a bare toUpperCase() would follow the default locale,
        // and under Turkish 'Friday' becomes 'FRİDAY'
        return DayOfWeek.valueOf(dbData.toUpperCase(Locale.ENGLISH));
    }
}
