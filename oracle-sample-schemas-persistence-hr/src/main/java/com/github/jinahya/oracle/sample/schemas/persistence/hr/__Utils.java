package com.github.jinahya.oracle.sample.schemas.persistence.hr;

/*-
 * #%L
 * hr
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
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

import jakarta.annotation.Nonnull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;

/**
 * Utilities which mirror the logic of the {@code HR} schema's stored routines.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class __Utils {
    // -------------------------------------------------------------------------------------------------- STATIC_METHODS

    // create PROCEDURE secure_dml
    // IS
    // BEGIN
    //   IF TO_CHAR (SYSDATE, 'HH24:MI') NOT BETWEEN '08:00' AND '18:00'
    //         OR TO_CHAR (SYSDATE, 'DY') IN ('SAT', 'SUN') THEN
    // 	RAISE_APPLICATION_ERROR (-20205,
    // 		'You may only make changes during normal office hours');
    //   END IF;
    // END secure_dml;

    /**
     * Checks whether the {@code SECURE_DML} routine would raise an application error
     * ({@value __DomainConstants#ROUTINE_SECURE_DML_APPLICATION_ERROR_CODE}) at the specified time on the specified day of
     * the week.
     * <p>
     * {@snippet id = "SECURE_DML" lang = "sql":
     * create PROCEDURE secure_dml IS
     * BEGIN
     *     IF TO_CHAR(SYSDATE, 'HH24:MI') NOT BETWEEN '08:00' AND '18:00'
     *         OR TO_CHAR(SYSDATE, 'DY') IN ('SAT', 'SUN')
     *     THEN
     *         RAISE_APPLICATION_ERROR(-20205, 'You may only make changes during normal office hours');
     *     END IF;
     * END secure_dml;
     *}
     * </p>
     * <hr/>
     * <p>
     * The routine raises the application error when the current time is {@code NOT BETWEEN}
     * {@value __DomainConstants#ROUTINE_SECURE_DML_LOCAL_TIME_MIN_TEXT} and
     * {@value __DomainConstants#ROUTINE_SECURE_DML_LOCAL_TIME_MAX_TEXT}, {@code OR} the current day of the week is either
     * {@link DayOfWeek#SATURDAY} or {@link DayOfWeek#SUNDAY}.
     *
     * @param time    the time of day to check.
     * @param weekday the day of the week to check.
     * @return {@code true} if the {@code SECURE_DML} routine would raise an application error at the {@code time} on
     * the {@code weekday}; {@code false} otherwise.
     * @throws NullPointerException if either {@code time} or {@code weekday} is {@code null}.
     */
    public static boolean ROUTINE_SECURE_DML_RAISE_APPLICATION_ERROR(final @Nonnull LocalTime time,
                                                                     final @Nonnull DayOfWeek weekday) {
        Objects.requireNonNull(time, "time is null");
        Objects.requireNonNull(weekday, "weekday is null");
        return (time.isBefore(__DomainConstants.ROUTINE_SECURE_DML_LOCAL_TIME_MIN)
                && time.isAfter(__DomainConstants.ROUTINE_SECURE_DML_LOCAL_TIME_MAX))
               ||
               __DomainConstants.ROUTINE_SECURE_DML_DAY_OF_WEEK_LIST.contains(weekday);
    }

    /**
     * Indicates whether the {@code SECURE_DML} routine would raise an application error at the specified temporal.
     *
     * @param temporal the temporal to check, which must yield both a {@link java.time.LocalTime} and a
     *                 {@link java.time.DayOfWeek}.
     * @return {@code true} if the routine would raise an error; {@code false} otherwise.
     * @throws NullPointerException        if {@code temporal} is {@code null}.
     * @throws java.time.DateTimeException if {@code temporal} yields no {@link java.time.LocalTime} or no
     *                                     {@link java.time.DayOfWeek}.
     */
    public static boolean ROUTINE_SECURE_DML_RAISE_APPLICATION_ERROR(final @Nonnull TemporalAccessor temporal) {
        Objects.requireNonNull(temporal, "temporal is null");
        return ROUTINE_SECURE_DML_RAISE_APPLICATION_ERROR(
                LocalTime.from(temporal), // DateTimeException
                DayOfWeek.from(temporal) // DateTimeException
        );
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    private __Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
