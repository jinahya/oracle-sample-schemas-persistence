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
 * Answers the exercises of {@code src/test/sql-exercises/EMPLOYEES/01_basic.sql}; a nested class per exercise.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class Employees_Exercises_01_Basic_PersistenceIT {

    @DisplayName("HR_EMPLOYEES_B_01: Look up a colleague by e-mail")
    @Nested
    class HR_EMPLOYEES_B_01_Test {
    }

    @DisplayName("HR_EMPLOYEES_B_02: A department's staff list")
    @Nested
    class HR_EMPLOYEES_B_02_Test {
    }

    @DisplayName("HR_EMPLOYEES_B_03: Seniority list for a job")
    @Nested
    class HR_EMPLOYEES_B_03_Test {
    }

    @DisplayName("HR_EMPLOYEES_B_04: Who is on commission")
    @Nested
    class HR_EMPLOYEES_B_04_Test {
    }

    @DisplayName("HR_EMPLOYEES_B_05: Staff with no department")
    @Nested
    class HR_EMPLOYEES_B_05_Test {
    }

    @DisplayName("HR_EMPLOYEES_B_06: Hired in a period")
    @Nested
    class HR_EMPLOYEES_B_06_Test {
    }

    @DisplayName("HR_EMPLOYEES_B_07: Name search at reception")
    @Nested
    class HR_EMPLOYEES_B_07_Test {
    }

    @DisplayName("HR_EMPLOYEES_B_08: Staff of several departments")
    @Nested
    class HR_EMPLOYEES_B_08_Test {
    }

    @DisplayName("HR_EMPLOYEES_B_09: Salaries not recorded")
    @Nested
    class HR_EMPLOYEES_B_09_Test {
    }

    @DisplayName("HR_EMPLOYEES_B_10: The newest hires")
    @Nested
    class HR_EMPLOYEES_B_10_Test {
    }
}
