package com.github.jinahya.oracle.sample.schemas.co.mapped;

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

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;

/**
 * An abstract builder class for {@link _MappedCoEntity}s.
 *
 * @param <SELF>   self type parameter
 * @param <TARGET> entity type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public abstract class _MappedCoEntityBuilder<
        SELF extends _MappedCoEntityBuilder<SELF, TARGET>,
        TARGET extends _MappedCoEntity<?>
        >
        extends __MappedEntityBuilder<SELF, TARGET> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for building instances of the specified entity class.
     *
     * @param targetClass the entity class to build.
     * @see #targetClass
     */
    protected _MappedCoEntityBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }
}
