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

import java.time.YearMonth;

/**
 * A converter between {@link YearMonth} and the {@value Time#COLUMN_NAME_CALENDAR_MONTH_DESC} and
 * {@value Time#COLUMN_NAME_FISCAL_MONTH_DESC} columns of the {@value Time#TABLE_NAME} table, which hold the month as
 * {@code uuuu-MM} text, {@code 2019-01} for January 2019 -- the ISO form {@link YearMonth#parse(CharSequence)} reads
 * and {@link YearMonth#toString()} writes.
 * <p>
 * Nothing applies this converter yet. {@link Time#getCalendarMonthDesc()} and {@link Time#getFiscalMonthDesc()} keep
 * the columns as {@code String}s, and {@link TimeUtils#getCalendarMonthDescAsYearMonth(Time)} and
 * {@link TimeUtils#getFiscalMonthDescAsYearMonth(Time)} derive the {@link YearMonth} on demand. Mapping the attributes
 * as a {@link YearMonth} with this converter, through {@link jakarta.persistence.Convert @Convert}, is left as an
 * exercise; see the issue linked below for what else has to change with it.
 * <p>
 * {@link CalMonthSalesMv#getCalendarMonthDesc()} maps the same text, but it is that entity's {@code @Id}, which the
 * specification excludes from attribute conversion; see the Javadoc of that field.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Time#getCalendarMonthDesc()
 * @see Time#getFiscalMonthDesc()
 * @see TimeUtils#getCalendarMonthDescAsYearMonth(Time)
 * @see TimeUtils#getFiscalMonthDescAsYearMonth(Time)
 * @see TimeDayNameAttributeConverter
 * @see <a href="https://github.com/jinahya/oracle-sample-schemas-persistence/issues/8">
 * #8 Map TIMES.CALENDAR_MONTH_DESC and FISCAL_MONTH_DESC as
 * YearMonth with TimeMonthDescAttributeConverter</a>
 */
@Converter(autoApply = false)
public class TimeMonthDescAttributeConverter implements AttributeConverter<YearMonth, String> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public TimeMonthDescAttributeConverter() {
        super();
    }

    // ---------------------------------------------------------------------- jakarta.persistence.AttributeConverter

    /**
     * {@inheritDoc}
     *
     * @param attribute {@inheritDoc}
     * @return {@code attribute} as {@code uuuu-MM} text, {@code 2019-01} for January 2019; {@code null} when
     * {@code attribute} is {@code null}.
     * @see YearMonth#toString()
     */
    @Override
    public String convertToDatabaseColumn(final YearMonth attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.toString();
    }

    /**
     * {@inheritDoc}
     *
     * @param dbData {@inheritDoc}
     * @return the year-month {@code dbData} denotes; {@code null} when {@code dbData} is {@code null}.
     * @throws java.time.format.DateTimeParseException if {@code dbData} is not in the {@code uuuu-MM} form
     *                                                 {@link YearMonth#parse(CharSequence)} accepts.
     */
    @Override
    public YearMonth convertToEntityAttribute(final String dbData) {
        if (dbData == null) {
            return null;
        }
        return YearMonth.parse(dbData);
    }
}
