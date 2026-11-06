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
 * Answers the exercises of {@code src/test/sql-exercises/EMP_DETAILS_VIEW/03_advanced.sql}; a nested class per exercise.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class EmpDetailsView_Exercises_03_Advanced_PersistenceIT {

    @DisplayName("HR_EMP_DETAILS_VIEW_A_01: Best-paid in each region")
    @Nested
    class HR_EMP_DETAILS_VIEW_A_01_Test {
    }

    @DisplayName("HR_EMP_DETAILS_VIEW_A_02: Payroll with subtotals")
    @Nested
    class HR_EMP_DETAILS_VIEW_A_02_Test {
    }
}
