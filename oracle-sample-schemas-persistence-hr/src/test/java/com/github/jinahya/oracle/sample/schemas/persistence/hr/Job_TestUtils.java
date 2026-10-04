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

import java.util.concurrent.ThreadLocalRandom;

/**
 * Utilities for testing the {@link Job} entity class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class Job_TestUtils {

    /**
     * Returns a new random positive value for the {@code minSalary} attribute.
     *
     * @return a new random positive value for the {@code minSalary} attribute.
     */
    public static int newRandomPositiveMinSalary() {
        return ThreadLocalRandom.current().nextInt(Job.ATTRIBUTE_MAX_MIN_SALARY) + 1;
    }

    /**
     * Returns a new random positive value for the {@code maxSalary} attribute, which is greater than or equal to the
     * specified value of the {@code minSalary} attribute.
     *
     * @param minSalary the value of the {@code minSalary} attribute; may be {@code null}.
     * @return a new random positive value for the {@code maxSalary} attribute.
     * @throws IllegalArgumentException if {@code minSalary} is less than {@code 1}, or greater than
     *                                  {@link Job#ATTRIBUTE_MAX_MIN_SALARY}.
     */
    public static int newRandomPositiveMaxSalary(final Integer minSalary) {
        if (minSalary != null && minSalary < 1) {
            throw new IllegalArgumentException("minSalary(" + minSalary + ") < 1");
        }
        if (minSalary != null && minSalary > Job.ATTRIBUTE_MAX_MIN_SALARY) {
            throw new IllegalArgumentException("minSalary(" + minSalary + ") > " + Job.ATTRIBUTE_MAX_MIN_SALARY);
        }
        if (minSalary == null) {
            return newRandomPositiveMinSalary();
        }
        return ThreadLocalRandom.current().nextInt(Job.ATTRIBUTE_MAX_MIN_SALARY - minSalary + 1) + minSalary;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Job_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
