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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A class for testing the {@link Job_TestUtils} class.
 * <p>
 * Each nested class covers one of the salary factory methods, and asserts that the value it returns stays within the
 * range {@link Job} accepts for the corresponding attribute.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Job_TestUtilsTest {

    /**
     * A class for testing {@link Job_TestUtils#newRandomPositiveMinSalary()}.
     * <p>
     * The test asserts that the generated value lies between {@code 1} and {@link Job#ATTRIBUTE_MAX_MIN_SALARY}, both
     * inclusive.
     */
    @DisplayName("newRandomPositiveMinSalary()")
    @Nested
    class NewRandomPositiveMinSalary_Test {

        @Test
        void __() {
            final var minSalary = Job_TestUtils.newRandomPositiveMinSalary();
            assertThat(minSalary).isBetween(1, Job.ATTRIBUTE_MAX_MIN_SALARY);
        }
    }

    /**
     * A class for testing {@link Job_TestUtils#newRandomPositiveMaxSalary(Integer)}.
     * <p>
     * One test passes the lowest accepted {@code minSalary}, {@code 1}, and asserts that the generated value is
     * positive and does not exceed {@link Job#ATTRIBUTE_MAX_MIN_SALARY}; the other passes
     * {@link Job#ATTRIBUTE_MAX_MIN_SALARY} itself, which leaves a single candidate, and asserts that the generated
     * value is exactly that.
     */
    @DisplayName("newRandomPositiveMaxSalary(minSalary)")
    @Nested
    class NewRandomPositiveMaxSalary_Test {

        @Test
        void __1() {
            final var maxSalary = Job_TestUtils.newRandomPositiveMaxSalary(1);
            assertThat(maxSalary)
                    .isPositive()
                    .isLessThanOrEqualTo(Job.ATTRIBUTE_MAX_MIN_SALARY);
        }

        @Test
        void _ATTRIBUTE_MAX_MIN_SALARY_ATTRIBUTE_MAX_MIN_SALARY() {
            final var maxSalary = Job_TestUtils.newRandomPositiveMaxSalary(Job.ATTRIBUTE_MAX_MIN_SALARY);
            assertThat(maxSalary)
                    .isEqualTo(Job.ATTRIBUTE_MAX_MIN_SALARY);
        }
    }
}
