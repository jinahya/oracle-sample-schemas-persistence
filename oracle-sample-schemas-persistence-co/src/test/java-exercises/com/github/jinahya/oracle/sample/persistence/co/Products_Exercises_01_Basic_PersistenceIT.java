package com.github.jinahya.oracle.sample.persistence.co;

/*-
 * #%L
 * co
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
 * Answers the exercises of {@code src/test/sql-exercises/PRODUCTS/01_basic.sql}; a nested class per exercise.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class Products_Exercises_01_Basic_PersistenceIT {

    @DisplayName("CO_PRODUCTS_B_01: Gift ideas within a budget")
    @Nested
    class CO_PRODUCTS_B_01_Test {
    }

    @DisplayName("CO_PRODUCTS_B_02: Price list, most expensive first")
    @Nested
    class CO_PRODUCTS_B_02_Test {
    }

    @DisplayName("CO_PRODUCTS_B_03: Products still waiting for a price")
    @Nested
    class CO_PRODUCTS_B_03_Test {
    }

    @DisplayName("CO_PRODUCTS_B_04: Products missing a fresh picture")
    @Nested
    class CO_PRODUCTS_B_04_Test {
    }
}
