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

import java.time.DayOfWeek;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Utilities for {@link Time}: accessors deriving a value from, or writing a value into, an attribute of the entity,
 * kept out of the entity so that they are not bean properties of it.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Time
 */
public final class TimeUtils {

    // --------------------------------------------------------------------------------------------------------- dayName

    /**
     * Returns current value of the {@value Time#ATTRIBUTE_NAME_DAY_NAME} attribute of the specified entity, mapped by
     * the specified function.
     *
     * @param <R>    the type of the mapped value.
     * @param time   the entity.
     * @param mapper the function to apply to current value of the {@value Time#ATTRIBUTE_NAME_DAY_NAME} attribute.
     * @return the mapped value; {@code null} when the {@value Time#ATTRIBUTE_NAME_DAY_NAME} attribute is {@code null}.
     * @throws NullPointerException if {@code time} or {@code mapper} is {@code null}.
     */
    public static <R> R getDayNameAsMapped(final Time time,
                                           final Function<? super String, ? extends R> mapper) {
        Objects.requireNonNull(time, "time is null");
        Objects.requireNonNull(mapper, "mapper is null");
        return Optional.ofNullable(time.getDayName()).map(mapper).orElse(null);
    }

    /**
     * Replaces current value of the {@value Time#ATTRIBUTE_NAME_DAY_NAME} attribute of the specified entity with the
     * specified value, mapped by the specified function.
     *
     * @param <T>     the type of the value to map.
     * @param time    the entity.
     * @param dayName the value to map; {@code null} sets the {@value Time#ATTRIBUTE_NAME_DAY_NAME} attribute to
     *                {@code null} without calling {@code mapper}.
     * @param mapper  the function to apply to {@code dayName}.
     * @throws NullPointerException if {@code time} or {@code mapper} is {@code null}.
     */
    public static <T> void setDayNameFromMapped(final Time time, final T dayName,
                                                final Function<? super T, ? extends String> mapper) {
        Objects.requireNonNull(time, "time is null");
        Objects.requireNonNull(mapper, "mapper is null");
        time.setDayName(Optional.ofNullable(dayName).map(mapper).orElse(null));
    }

    /**
     * Returns current value of the {@value Time#ATTRIBUTE_NAME_DAY_NAME} attribute of the specified entity, as a
     * {@link DayOfWeek}.
     *
     * @param time the entity.
     * @return the day of the week whose {@link DayOfWeek#name() name} equals current value of the
     * {@value Time#ATTRIBUTE_NAME_DAY_NAME} attribute, ignoring case; {@code null} when the attribute is {@code null}.
     * @throws NullPointerException     if {@code time} is {@code null}.
     * @throws IllegalArgumentException if the attribute is not the name of a {@link DayOfWeek} constant, such as
     *                                  {@code Monday}.
     * @see #getDayNameAsMapped(Time, Function)
     * @see #setDayNameFromDayOfWeek(Time, DayOfWeek)
     */
    public static DayOfWeek getDayNameAsDayOfWeek(final Time time) {
        return getDayNameAsMapped(time, v -> DayOfWeek.valueOf(v.toUpperCase(Locale.ENGLISH)));
    }

    /**
     * Replaces current value of the {@value Time#ATTRIBUTE_NAME_DAY_NAME} attribute of the specified entity with the
     * full English name of the specified day of the week, {@code Monday} for {@link DayOfWeek#MONDAY}, which is the
     * form the {@value Time#COLUMN_NAME_DAY_NAME} column holds.
     *
     * @param time      the entity.
     * @param dayOfWeek the day of the week; {@code null} sets the {@value Time#ATTRIBUTE_NAME_DAY_NAME} attribute to
     *                  {@code null}.
     * @throws NullPointerException if {@code time} is {@code null}.
     * @see DayOfWeek#getDisplayName(TextStyle, Locale)
     * @see #getDayNameAsDayOfWeek(Time)
     */
    public static void setDayNameFromDayOfWeek(final Time time, final DayOfWeek dayOfWeek) {
        setDayNameFromMapped(time, dayOfWeek, v -> v.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH));
    }

    // ----------------------------------------------------------------------------------------------- calendarMonthDesc

    /**
     * Returns current value of the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute of the specified entity,
     * mapped by the specified function.
     *
     * @param <R>    the type of the mapped value.
     * @param time   the entity.
     * @param mapper the function to apply to current value of the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_DESC}
     *               attribute.
     * @return the mapped value; {@code null} when the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute is
     *         {@code null}.
     * @throws NullPointerException if {@code time} or {@code mapper} is {@code null}.
     */
    public static <R> R getCalendarMonthDescAsMapped(final Time time,
                                                     final Function<? super String, ? extends R> mapper) {
        Objects.requireNonNull(time, "time is null");
        Objects.requireNonNull(mapper, "mapper is null");
        return Optional.ofNullable(time.getCalendarMonthDesc()).map(mapper).orElse(null);
    }

    /**
     * Replaces current value of the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute of the specified entity
     * with the specified value, mapped by the specified function.
     *
     * @param <T>               the type of the value to map.
     * @param time              the entity.
     * @param calendarMonthDesc the value to map; {@code null} sets the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_DESC}
     *                          attribute to {@code null} without calling {@code mapper}.
     * @param mapper            the function to apply to {@code calendarMonthDesc}.
     * @throws NullPointerException if {@code time} or {@code mapper} is {@code null}.
     */
    public static <T> void setCalendarMonthDescFromMapped(final Time time, final T calendarMonthDesc,
                                                          final Function<? super T, ? extends String> mapper) {
        Objects.requireNonNull(time, "time is null");
        Objects.requireNonNull(mapper, "mapper is null");
        time.setCalendarMonthDesc(Optional.ofNullable(calendarMonthDesc).map(mapper).orElse(null));
    }

    /**
     * Returns current value of the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute of the specified entity,
     * as a {@link YearMonth}.
     *
     * @param time the entity.
     * @return a year-month parsed from current value of the attribute; {@code null} when the attribute is {@code null}.
     * @throws NullPointerException                    if {@code time} is {@code null}.
     * @throws java.time.format.DateTimeParseException if the attribute is not in the {@code uuuu-MM} form
     *                                                 {@link YearMonth#parse(CharSequence)} accepts.
     * @see #getCalendarMonthDescAsMapped(Time, Function)
     * @see #setCalendarMonthDescFromYearMonth(Time, YearMonth)
     */
    public static YearMonth getCalendarMonthDescAsYearMonth(final Time time) {
        return getCalendarMonthDescAsMapped(time, YearMonth::parse);
    }

    /**
     * Replaces current value of the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute of the specified entity
     * with the {@code uuuu-MM} text of the specified year-month, {@code 2019-01} for January 2019, which is the form
     * the {@value Time#COLUMN_NAME_CALENDAR_MONTH_DESC} column holds.
     *
     * @param time      the entity.
     * @param yearMonth the year-month; {@code null} sets the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute
     *                  to {@code null}.
     * @throws NullPointerException if {@code time} is {@code null}.
     * @see YearMonth#parse(CharSequence)
     * @see #getCalendarMonthDescAsYearMonth(Time)
     */
    public static void setCalendarMonthDescFromYearMonth(final Time time, final YearMonth yearMonth) {
        setCalendarMonthDescFromMapped(time, yearMonth, YearMonth::toString);
    }

    // ------------------------------------------------------------------------------------------------- fiscalMonthDesc

    /**
     * Returns current value of the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_DESC} attribute of the specified entity,
     * mapped by the specified function.
     *
     * @param <R>    the type of the mapped value.
     * @param time   the entity.
     * @param mapper the function to apply to current value of the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_DESC}
     *               attribute.
     * @return the mapped value; {@code null} when the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_DESC} attribute is
     *         {@code null}.
     * @throws NullPointerException if {@code time} or {@code mapper} is {@code null}.
     */
    public static <R> R getFiscalMonthDescAsMapped(final Time time,
                                                   final Function<? super String, ? extends R> mapper) {
        Objects.requireNonNull(time, "time is null");
        Objects.requireNonNull(mapper, "mapper is null");
        return Optional.ofNullable(time.getFiscalMonthDesc()).map(mapper).orElse(null);
    }

    /**
     * Replaces current value of the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_DESC} attribute of the specified entity
     * with the specified value, mapped by the specified function.
     *
     * @param <T>             the type of the value to map.
     * @param time            the entity.
     * @param fiscalMonthDesc the value to map; {@code null} sets the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_DESC}
     *                        attribute to {@code null} without calling {@code mapper}.
     * @param mapper          the function to apply to {@code fiscalMonthDesc}.
     * @throws NullPointerException if {@code time} or {@code mapper} is {@code null}.
     */
    public static <T> void setFiscalMonthDescFromMapped(final Time time, final T fiscalMonthDesc,
                                                        final Function<? super T, ? extends String> mapper) {
        Objects.requireNonNull(time, "time is null");
        Objects.requireNonNull(mapper, "mapper is null");
        time.setFiscalMonthDesc(Optional.ofNullable(fiscalMonthDesc).map(mapper).orElse(null));
    }

    /**
     * Returns current value of the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_DESC} attribute of the specified entity, as
     * a {@link YearMonth}.
     *
     * @param time the entity.
     * @return a year-month parsed from current value of the attribute; {@code null} when the attribute is {@code null}.
     * @throws NullPointerException                    if {@code time} is {@code null}.
     * @throws java.time.format.DateTimeParseException if the attribute is not in the {@code uuuu-MM} form
     *                                                 {@link YearMonth#parse(CharSequence)} accepts.
     * @see #getFiscalMonthDescAsMapped(Time, Function)
     * @see #setFiscalMonthDescFromYearMonth(Time, YearMonth)
     */
    public static YearMonth getFiscalMonthDescAsYearMonth(final Time time) {
        return getFiscalMonthDescAsMapped(time, YearMonth::parse);
    }

    /**
     * Replaces current value of the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_DESC} attribute of the specified entity
     * with the {@code uuuu-MM} text of the specified year-month, {@code 2019-01} for January 2019, which is the form
     * the {@value Time#COLUMN_NAME_FISCAL_MONTH_DESC} column holds.
     *
     * @param time      the entity.
     * @param yearMonth the year-month; {@code null} sets the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_DESC} attribute
     *                  to {@code null}.
     * @throws NullPointerException if {@code time} is {@code null}.
     * @see YearMonth#parse(CharSequence)
     * @see #getFiscalMonthDescAsYearMonth(Time)
     */
    public static void setFiscalMonthDescFromYearMonth(final Time time, final YearMonth yearMonth) {
        setFiscalMonthDescFromMapped(time, yearMonth, YearMonth::toString);
    }

    // ----------------------------------------------------------------------------------------------- calendarMonthName

    /**
     * Returns current value of the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_NAME} attribute of the specified entity,
     * mapped by the specified function.
     *
     * @param <R>    the type of the mapped value.
     * @param time   the entity.
     * @param mapper the function to apply to current value of the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_NAME}
     *               attribute.
     * @return the mapped value; {@code null} when the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_NAME} attribute is
     *         {@code null}.
     * @throws NullPointerException if {@code time} or {@code mapper} is {@code null}.
     */
    public static <R> R getCalendarMonthNameAsMapped(final Time time,
                                                     final Function<? super String, ? extends R> mapper) {
        Objects.requireNonNull(time, "time is null");
        Objects.requireNonNull(mapper, "mapper is null");
        return Optional.ofNullable(time.getCalendarMonthName()).map(mapper).orElse(null);
    }

    /**
     * Replaces current value of the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_NAME} attribute of the specified entity
     * with the specified value, mapped by the specified function.
     *
     * @param <T>               the type of the value to map.
     * @param time              the entity.
     * @param calendarMonthName the value to map; {@code null} sets the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_NAME}
     *                          attribute to {@code null} without calling {@code mapper}.
     * @param mapper            the function to apply to {@code calendarMonthName}.
     * @throws NullPointerException if {@code time} or {@code mapper} is {@code null}.
     */
    public static <T> void setCalendarMonthNameFromMapped(final Time time, final T calendarMonthName,
                                                          final Function<? super T, ? extends String> mapper) {
        Objects.requireNonNull(time, "time is null");
        Objects.requireNonNull(mapper, "mapper is null");
        time.setCalendarMonthName(Optional.ofNullable(calendarMonthName).map(mapper).orElse(null));
    }

    /**
     * Returns current value of the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_NAME} attribute of the specified entity,
     * as a {@link Month}.
     *
     * @param time the entity.
     * @return the month whose {@link Month#name() name} equals current value of the attribute, ignoring case;
     *         {@code null} when the attribute is {@code null}.
     * @throws NullPointerException     if {@code time} is {@code null}.
     * @throws IllegalArgumentException if the attribute is not the name of a {@link Month} constant, such as
     *                                  {@code January}.
     * @see #getCalendarMonthNameAsMapped(Time, Function)
     * @see #setCalendarMonthNameFromMonth(Time, Month)
     */
    public static Month getCalendarMonthNameAsMonth(final Time time) {
        return getCalendarMonthNameAsMapped(time, v -> Month.valueOf(v.toUpperCase(Locale.ENGLISH)));
    }

    /**
     * Replaces current value of the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_NAME} attribute of the specified entity
     * with the full English name of the specified month, {@code January} for {@link Month#JANUARY}, which is the form
     * the {@value Time#COLUMN_NAME_CALENDAR_MONTH_NAME} column holds.
     *
     * @param time  the entity.
     * @param month the month; {@code null} sets the {@value Time#ATTRIBUTE_NAME_CALENDAR_MONTH_NAME} attribute to
     *              {@code null}.
     * @throws NullPointerException if {@code time} is {@code null}.
     * @see Month#getDisplayName(TextStyle, Locale)
     * @see #getCalendarMonthNameAsMonth(Time)
     */
    public static void setCalendarMonthNameFromMonth(final Time time, final Month month) {
        setCalendarMonthNameFromMapped(time, month, v -> v.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH));
    }

    // ------------------------------------------------------------------------------------------------- fiscalMonthName

    /**
     * Returns current value of the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_NAME} attribute of the specified entity,
     * mapped by the specified function.
     *
     * @param <R>    the type of the mapped value.
     * @param time   the entity.
     * @param mapper the function to apply to current value of the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_NAME}
     *               attribute.
     * @return the mapped value; {@code null} when the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_NAME} attribute is
     *         {@code null}.
     * @throws NullPointerException if {@code time} or {@code mapper} is {@code null}.
     */
    public static <R> R getFiscalMonthNameAsMapped(final Time time,
                                                   final Function<? super String, ? extends R> mapper) {
        Objects.requireNonNull(time, "time is null");
        Objects.requireNonNull(mapper, "mapper is null");
        return Optional.ofNullable(time.getFiscalMonthName()).map(mapper).orElse(null);
    }

    /**
     * Replaces current value of the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_NAME} attribute of the specified entity
     * with the specified value, mapped by the specified function.
     *
     * @param <T>             the type of the value to map.
     * @param time            the entity.
     * @param fiscalMonthName the value to map; {@code null} sets the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_NAME}
     *                        attribute to {@code null} without calling {@code mapper}.
     * @param mapper          the function to apply to {@code fiscalMonthName}.
     * @throws NullPointerException if {@code time} or {@code mapper} is {@code null}.
     */
    public static <T> void setFiscalMonthNameFromMapped(final Time time, final T fiscalMonthName,
                                                        final Function<? super T, ? extends String> mapper) {
        Objects.requireNonNull(time, "time is null");
        Objects.requireNonNull(mapper, "mapper is null");
        time.setFiscalMonthName(Optional.ofNullable(fiscalMonthName).map(mapper).orElse(null));
    }

    /**
     * Returns current value of the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_NAME} attribute of the specified entity, as
     * a {@link Month}.
     *
     * @param time the entity.
     * @return the month whose {@link Month#name() name} equals current value of the attribute, ignoring case;
     *         {@code null} when the attribute is {@code null}.
     * @throws NullPointerException     if {@code time} is {@code null}.
     * @throws IllegalArgumentException if the attribute is not the name of a {@link Month} constant, such as
     *                                  {@code January}.
     * @see #getFiscalMonthNameAsMapped(Time, Function)
     * @see #setFiscalMonthNameFromMonth(Time, Month)
     */
    public static Month getFiscalMonthNameAsMonth(final Time time) {
        return getFiscalMonthNameAsMapped(time, v -> Month.valueOf(v.toUpperCase(Locale.ENGLISH)));
    }

    /**
     * Replaces current value of the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_NAME} attribute of the specified entity
     * with the full English name of the specified month, {@code January} for {@link Month#JANUARY}, which is the form
     * the {@value Time#COLUMN_NAME_FISCAL_MONTH_NAME} column holds.
     *
     * @param time  the entity.
     * @param month the month; {@code null} sets the {@value Time#ATTRIBUTE_NAME_FISCAL_MONTH_NAME} attribute to
     *              {@code null}.
     * @throws NullPointerException if {@code time} is {@code null}.
     * @see Month#getDisplayName(TextStyle, Locale)
     * @see #getFiscalMonthNameAsMonth(Time)
     */
    public static void setFiscalMonthNameFromMonth(final Time time, final Month month) {
        setFiscalMonthNameFromMapped(time, month, v -> v.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH));
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    private TimeUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
