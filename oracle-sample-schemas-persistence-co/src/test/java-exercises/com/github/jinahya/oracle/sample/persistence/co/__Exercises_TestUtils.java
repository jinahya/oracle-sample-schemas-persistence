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

import jakarta.persistence.EntityManager;

import java.util.Objects;
import java.util.function.Function;

/**
 * Helpers for the {@code *_PersistenceIT} classes of {@code src/test/java-exercises}, which answer
 * the exercises of {@code src/test/sql-exercises}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public final class __Exercises_TestUtils {

    /**
     * Applies the specified function to a new entity manager of the {@code co-it} unit, on the
     * installed {@code CO} schema, and returns the result.
     *
     * @param function the function to apply.
     * @param <R>      the type of the result.
     * @return the result of the {@code function}.
     */
    public static <R> R apply(final Function<? super EntityManager, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return __PersistenceIT_Utils.applyEntityManager_(emf -> function);
    }

    // ---------------------------------------------------------------------------------------------

    private __Exercises_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
