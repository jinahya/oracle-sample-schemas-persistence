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

import com.github.jinahya.persistence.mapped.__MappedBuilder;

@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public abstract class _MappedCoBuilder<
        SELF extends _MappedCoBuilder<SELF, TARGET>,
        TARGET extends _MappedCo
        >
        extends __MappedBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedCoBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
}
