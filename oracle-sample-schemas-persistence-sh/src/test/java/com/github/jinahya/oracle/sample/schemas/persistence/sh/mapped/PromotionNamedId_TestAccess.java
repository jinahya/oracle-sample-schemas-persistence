package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

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

/**
 * Opens the package-private members of {@link PromotionNamedId} to the tests in the entities' package, which used to
 * share the class's package.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public final class PromotionNamedId_TestAccess {

    /**
     * The value of {@link PromotionNamedId#ATTRIBUTE_NAME_ID}.
     */
    public static final String ATTRIBUTE_NAME_ID = PromotionNamedId.ATTRIBUTE_NAME_ID;

    // -------------------------------------------------------------------------------------------------- STATIC_METHODS

    /**
     * Returns the name of the specified instance.
     *
     * @param instance the instance.
     * @return the name of {@code instance}.
     */
    public static String getName(final PromotionNamedId<?> instance) {
        return instance.getName();
    }

    /**
     * Returns the id of the specified instance.
     *
     * @param <N>      the type of the id.
     * @param instance the instance.
     * @return the id of {@code instance}.
     */
    public static <N extends Number> N getId(final PromotionNamedId<N> instance) {
        return instance.getId();
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    private PromotionNamedId_TestAccess() {
        throw new AssertionError("instantiation is not allowed");
    }
}
