package com.github.jinahya.oracle.sample.schemas.co;

/*-
 * #%L
 * co
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

/**
 * Constants shared by the entity classes of the {@code CO} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
final class _Constants {
    // -------------------------------------------------------------------------------------------------------- LATITUDE

    /**
     * The minimum value of the {@code latitude} attribute. The value is {@value}.
     */
    static final String DECIMAL_MIN_LATITUDE = "-90.000000";

    /**
     * The maximum value of the {@code latitude} attribute. The value is {@value}.
     */
    static final String DECIMAL_MAX_LATITUDE = "+90.000000";

    // ------------------------------------------------------------------------------------------------------- LONGITUDE

    /**
     * The minimum value of the {@code longitude} attribute. The value is {@value}.
     */
    static final String DECIMAL_MIN_LONGITUDE = "-180.000000";

    /**
     * The maximum value of the {@code longitude} attribute. The value is {@value}.
     */
    static final String DECIMAL_MAX_LONGITUDE = "+180.000000";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    private _Constants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
