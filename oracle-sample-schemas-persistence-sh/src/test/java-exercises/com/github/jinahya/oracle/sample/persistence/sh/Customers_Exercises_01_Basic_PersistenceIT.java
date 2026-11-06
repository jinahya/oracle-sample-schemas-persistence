package com.github.jinahya.oracle.sample.persistence.sh;

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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

/**
 * Answers the exercises of {@code src/test/sql-exercises/CUSTOMERS/01_basic.sql}; a nested class per exercise.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class Customers_Exercises_01_Basic_PersistenceIT {

    @DisplayName("SH_CUSTOMERS_B_01: Look up a customer by surname")
    @Nested
    class SH_CUSTOMERS_B_01_Test {
    }

    @DisplayName("SH_CUSTOMERS_B_02: Customers in selected income bands")
    @Nested
    class SH_CUSTOMERS_B_02_Test {
    }

    @DisplayName("SH_CUSTOMERS_B_03: Customers without a marital status")
    @Nested
    class SH_CUSTOMERS_B_03_Test {
    }

    @DisplayName("SH_CUSTOMERS_B_04: Income bands, unknowns last")
    @Nested
    class SH_CUSTOMERS_B_04_Test {
    }

    @DisplayName("SH_CUSTOMERS_B_05: Customer records in effect at a time")
    @Nested
    class SH_CUSTOMERS_B_05_Test {
    }
}
