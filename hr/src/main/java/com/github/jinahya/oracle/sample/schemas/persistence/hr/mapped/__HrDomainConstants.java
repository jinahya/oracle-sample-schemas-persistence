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

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public final class __HrDomainConstants {

    // ------------------------------------------------------------------------------------------- routines / SECURE_DML
    static final String ROUTINE_SECURE_DML_LOCAL_TIME_MIN_TEXT = "08:00";

    public static final LocalTime ROUTINE_SECURE_DML_LOCAL_TIME_MIN =
            LocalTime.parse(ROUTINE_SECURE_DML_LOCAL_TIME_MIN_TEXT);

    static final String ROUTINE_SECURE_DML_LOCAL_TIME_MAX_TEXT = "18:00";

    public static final LocalTime ROUTINE_SECURE_DML_LOCAL_TIME_MAX =
            LocalTime.parse(ROUTINE_SECURE_DML_LOCAL_TIME_MAX_TEXT);

    public static final List<DayOfWeek> ROUTINE_SECURE_DML_DAY_OF_WEEK_LIST =
            List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

    public static final int ROUTINE_SECURE_DML_APPLICATION_ERROR_CODE = -20205;

    // -----------------------------------------------------------------------------------------------------------------
    private __HrDomainConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
