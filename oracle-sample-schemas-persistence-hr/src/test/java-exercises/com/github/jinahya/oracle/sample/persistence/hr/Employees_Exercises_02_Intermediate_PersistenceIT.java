package com.github.jinahya.oracle.sample.persistence.hr;

/*-
 * #%L
 * hr
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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

/**
 * Answers the exercises of {@code src/test/sql-exercises/EMPLOYEES/02_intermediate.sql}; a nested class per exercise.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class Employees_Exercises_02_Intermediate_PersistenceIT {

    @DisplayName("HR_EMPLOYEES_I_01: Above the company average")
    @Nested
    class HR_EMPLOYEES_I_01_Test {
    }

    @DisplayName("HR_EMPLOYEES_I_02: Total monthly pay including commission")
    @Nested
    class HR_EMPLOYEES_I_02_Test {
    }

    @DisplayName("HR_EMPLOYEES_I_03: Headcount per pay grade")
    @Nested
    class HR_EMPLOYEES_I_03_Test {
    }

    @DisplayName("HR_EMPLOYEES_I_04: Earning more than the boss")
    @Nested
    class HR_EMPLOYEES_I_04_Test {
    }

    @DisplayName("HR_EMPLOYEES_I_05: Span of control")
    @Nested
    class HR_EMPLOYEES_I_05_Test {
    }

    @DisplayName("HR_EMPLOYEES_I_06: Skip-level meetings")
    @Nested
    class HR_EMPLOYEES_I_06_Test {
    }
}
