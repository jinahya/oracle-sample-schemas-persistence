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
 * Answers the exercises of {@code src/test/sql-exercises/EMPLOYEES/03_advanced.sql}; a nested class per exercise.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class Employees_Exercises_03_Advanced_PersistenceIT {

    @DisplayName("HR_EMPLOYEES_A_01: Top earner of each department")
    @Nested
    class HR_EMPLOYEES_A_01_Test {
    }

    @DisplayName("HR_EMPLOYEES_A_02: Three best-paid per department")
    @Nested
    class HR_EMPLOYEES_A_02_Test {
    }

    @DisplayName("HR_EMPLOYEES_A_03: Everyone under a manager")
    @Nested
    class HR_EMPLOYEES_A_03_Test {
    }

    @DisplayName("HR_EMPLOYEES_A_04: Out-earning a whole department")
    @Nested
    class HR_EMPLOYEES_A_04_Test {
    }

    @DisplayName("HR_EMPLOYEES_A_05: Work anniversaries")
    @Nested
    class HR_EMPLOYEES_A_05_Test {
    }

    @DisplayName("HR_EMPLOYEES_A_06: Badges and e-mail migration")
    @Nested
    class HR_EMPLOYEES_A_06_Test {
    }

    @DisplayName("HR_EMPLOYEES_A_07: Pay against the department average")
    @Nested
    class HR_EMPLOYEES_A_07_Test {
    }
}
