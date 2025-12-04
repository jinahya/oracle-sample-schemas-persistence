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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJob;

import java.util.concurrent.ThreadLocalRandom;

final class Job_TestUtils {

    static long newRandomPositiveSalary(final long maxInclusive) {
        if (maxInclusive <= 1) {
            throw new IllegalArgumentException("maxInclusive(" + maxInclusive + ") <= 1");
        }
        return ThreadLocalRandom.current().nextLong(1L, maxInclusive + 1L);
    }

    static long newRandomPositiveMinSalary() {
        return newRandomPositiveSalary(MappedJob.ATTRIBUTE_MAX_MIN_SALARY);
    }

    static long newRandomPositiveMaxSalary(final long minSalary) {
        if (minSalary <= 1) {
            throw new IllegalArgumentException("minSalary(" + minSalary + ") <= 1");
        }
        return newRandomPositiveSalary(MappedJob.ATTRIBUTE_MAX_MIN_SALARY - minSalary) + minSalary;
    }

    private Job_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
