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

import java.time.YearMonth;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Utilities for {@link CalMonthSalesMv}: accessors deriving a value from its
 * {@value CalMonthSalesMv#ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute, kept out of the entity so that they are not
 * bean properties of it. There are no writers: the attribute is the entity's {@code @Id}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see CalMonthSalesMv
 */
public final class CalMonthSalesMvUtils {

    // ----------------------------------------------------------------------------------------------- calendarMonthDesc

    /**
     * Returns current value of the {@value CalMonthSalesMv#ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute of the
     * specified entity, mapped by the specified function.
     *
     * @param <R>             the type of the mapped value.
     * @param calMonthSalesMv the entity.
     * @param mapper          the function to apply to current value of the
     *                        {@value CalMonthSalesMv#ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute.
     * @return the mapped value; {@code null} when the attribute is {@code null}.
     * @throws NullPointerException if {@code calMonthSalesMv} or {@code mapper} is {@code null}.
     */
    public static <R> R getCalendarMonthDescAsMapped(final CalMonthSalesMv calMonthSalesMv,
                                                     final Function<? super String, ? extends R> mapper) {
        Objects.requireNonNull(calMonthSalesMv, "calMonthSalesMv is null");
        Objects.requireNonNull(mapper, "mapper is null");
        return Optional.ofNullable(calMonthSalesMv.getCalendarMonthDesc()).map(mapper).orElse(null);
    }

    /**
     * Returns current value of the {@value CalMonthSalesMv#ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute of the
     * specified entity, as a {@link YearMonth}.
     *
     * @param calMonthSalesMv the entity.
     * @return a year-month parsed from current value of the attribute; {@code null} when the attribute is
     * {@code null}.
     * @throws NullPointerException                   if {@code calMonthSalesMv} is {@code null}.
     * @throws java.time.format.DateTimeParseException if the attribute is not in the {@code uuuu-MM} form
     *                                                 {@link YearMonth#parse(CharSequence)} accepts.
     * @see #getCalendarMonthDescAsMapped(CalMonthSalesMv, Function)
     */
    public static YearMonth getCalendarMonthDescAsYearMonth(final CalMonthSalesMv calMonthSalesMv) {
        return getCalendarMonthDescAsMapped(calMonthSalesMv, YearMonth::parse);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    private CalMonthSalesMvUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
