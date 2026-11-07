package com.github.jinahya.oracle.sample.persistence.sh;

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

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
     * Applies the specified function to a new entity manager of the
     * {@value __PersistenceIT_Utils#PERSISTENCE_UNIT_NAME} unit, on the installed {@code SH}
     * schema, and returns the result. The entity manager is closed when the function returns.
     *
     * @param function the function to apply.
     * @param <R>      the type of the result.
     * @return the result of the {@code function}.
     */
    public static <R> R apply(final Function<? super EntityManager, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return __PersistenceIT_Utils.applyEntityManager_(emf -> function);
    }

    /**
     * Returns the specified number rounded, half up, to the specified scale, as a {@code double}.
     * An average or a ratio carries more digits than a test can spell out, and comes back as a
     * {@link Double} or a {@link BigDecimal}; rounded, either compares equal to a literal of that
     * scale.
     *
     * @param number the number to round.
     * @param scale  the number of digits to keep after the decimal point.
     * @return the {@code number} rounded to the {@code scale}.
     */
    public static double rounded(final Object number, final int scale) {
        Objects.requireNonNull(number, "number is null");
        return new BigDecimal(number.toString()).setScale(scale, RoundingMode.HALF_UP)
                .doubleValue();
    }

    // ---------------------------------------------------------------------------------------------

    private __Exercises_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
