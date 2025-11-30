package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

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

public final class __HrDomainUtils {

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
     * Checks whether the {@code SECURE_DML} routine will raise an application
     * error({@value __HrDomainConstants#ROUTINE_SECURE_DML_APPLICATION_ERROR_CODE}) for given temporal values.
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
     * The routine will raise an application-error when the current time is {@code NOT BETWEEN}
     * {@value __HrDomainConstants#ROUTINE_SECURE_DML_LOCAL_TIME_MIN_TEXT} and
     * {@value __HrDomainConstants#ROUTINE_SECURE_DML_LOCAL_TIME_MAX_TEXT} {@code OR} current weekday is either
     * {@link DayOfWeek#SATURDAY} or {@link DayOfWeek#SUNDAY}
     *
     * @param time    base time; should not be between
     *                {@value __HrDomainConstants#ROUTINE_SECURE_DML_LOCAL_TIME_MIN_TEXT} and
     *                {@value __HrDomainConstants#ROUTINE_SECURE_DML_LOCAL_TIME_MAX_TEXT} when the {@code weekday}
     *                parameter does not meet the described requirements.
     * @param weekday the base weekday of week; should be either {@link DayOfWeek#SATURDAY} or {@link DayOfWeek#SUNDAY}
     *                when the {@code time} parameter does meet the described requirements.
     * @return {@code true} if the {@code SECURE_DML} routine will raise an application error for the {@code time} and
     * the {@code weekday}, {@code false} otherwise
     */
    public static boolean ROUTINE_SECURE_DML_RAISE_APPLICATION_ERROR(final @Nonnull LocalTime time,
                                                                     final @Nonnull DayOfWeek weekday) {
        Objects.requireNonNull(time, "time is null");
        Objects.requireNonNull(weekday, "weekday is null");
        return (time.isBefore(__HrDomainConstants.ROUTINE_SECURE_DML_LOCAL_TIME_MIN)
                && time.isAfter(__HrDomainConstants.ROUTINE_SECURE_DML_LOCAL_TIME_MAX))
               ||
               __HrDomainConstants.ROUTINE_SECURE_DML_DAY_OF_WEEK_LIST.contains(weekday);
    }

    public static boolean ROUTINE_SECURE_DML_RAISE_APPLICATION_ERROR(final @Nonnull TemporalAccessor temporal) {
        Objects.requireNonNull(temporal, "temporal is null");
        return ROUTINE_SECURE_DML_RAISE_APPLICATION_ERROR(
                LocalTime.from(temporal), // DateTimeException
                DayOfWeek.from(temporal) // DateTimeException
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private __HrDomainUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
