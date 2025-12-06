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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MappedJob_TestUtilsTest {

    @DisplayName("newRandomPositiveMinSalary()")
    @Nested
    class NewRandomPositiveMinSalary_Test {

        @Test
        void __() {
            final var minSalary = MappedJob_TestUtils.newRandomPositiveMinSalary();
            assertThat(minSalary).isBetween(1, MappedJob.ATTRIBUTE_MAX_MIN_SALARY);
        }
    }

    @DisplayName("newRandomPositiveMaxSalary(minSalary)")
    @Nested
    class NewRandomPositiveMaxSalary_Test {

        @Test
        void __1() {
            final var maxSalary = MappedJob_TestUtils.newRandomPositiveMaxSalary(1);
            assertThat(maxSalary)
                    .isPositive()
                    .isLessThanOrEqualTo(MappedJob.ATTRIBUTE_MAX_MIN_SALARY);
        }

        @Test
        void _ATTRIBUTE_MAX_MIN_SALARY_ATTRIBUTE_MAX_MIN_SALARY() {
            final var maxSalary = MappedJob_TestUtils.newRandomPositiveMaxSalary(MappedJob.ATTRIBUTE_MAX_MIN_SALARY);
            assertThat(maxSalary)
                    .isEqualTo(MappedJob.ATTRIBUTE_MAX_MIN_SALARY);
        }
    }
}
