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

import java.util.concurrent.ThreadLocalRandom;

public final class MappedJob_TestUtils {

    public static int newRandomPositiveMinSalary() {
        return ThreadLocalRandom.current().nextInt(MappedJob.ATTRIBUTE_MAX_MIN_SALARY) + 1;
    }

    public static int newRandomPositiveMaxSalary(final Integer minSalary) {
        if (minSalary != null && minSalary < 1) {
            throw new IllegalArgumentException("minSalary(" + minSalary + ") < 1");
        }
        if (minSalary != null && minSalary > MappedJob.ATTRIBUTE_MAX_MIN_SALARY) {
            throw new IllegalArgumentException("minSalary(" + minSalary + ") > " + MappedJob.ATTRIBUTE_MAX_MIN_SALARY);
        }
        if (minSalary == null) {
            return newRandomPositiveMinSalary();
        }
        return ThreadLocalRandom.current().nextInt(MappedJob.ATTRIBUTE_MAX_MIN_SALARY - minSalary + 1) + minSalary;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private MappedJob_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
