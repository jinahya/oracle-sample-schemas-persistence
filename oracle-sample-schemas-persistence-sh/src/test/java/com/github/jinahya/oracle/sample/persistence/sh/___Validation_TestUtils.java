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

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Holds a validator factory, created once per JVM and closed when it exits, and the helpers which
 * validate an object with it.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public final class ___Validation_TestUtils {

    /**
     * Holds the validator factory, which the class loads on first use.
     */
    private static final class Holder {

        /**
         * The validator factory, closed by a shutdown hook.
         */
        private static final ValidatorFactory FACTORY;

        static {
            FACTORY = Validation.buildDefaultValidatorFactory();
            Runtime.getRuntime().addShutdownHook(new Thread(FACTORY::close));
        }

        private Holder() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Applies the specified function to the validator factory, which the function must not close.
     *
     * @param function the function to apply.
     * @param <R>      the type of the result.
     * @return the result of the {@code function}.
     */
    private static <R> R applyValidatorFactory(
            final Function<? super ValidatorFactory, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return function.apply(Holder.FACTORY);
    }

    /**
     * Applies the specified function to a validator of the validator factory.
     *
     * @param function the function to apply.
     * @param <R>      the type of the result.
     * @return the result of the {@code function}.
     */
    public static <R> R applyValidator(final Function<? super Validator, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return applyValidatorFactory(f -> function.apply(f.getValidator()));
    }

    /**
     * Validates the specified object against the specified groups.
     *
     * @param object the object to validate.
     * @param groups the groups to validate against; none for the default group.
     * @param <T>    the type of the object.
     * @return the constraint violations of the {@code object}; empty when there is none.
     */
    public static <T> Set<ConstraintViolation<T>> validate(
            final T object, final Class<?>... groups) {
        Objects.requireNonNull(object, "object is null");
        Objects.requireNonNull(groups, "groups is null");
        return applyValidator(v -> v.validate(object, groups));
    }

    /**
     * Validates the specified object against the specified groups, and returns it when it is
     * valid.
     *
     * @param object the object to validate.
     * @param groups the groups to validate against; none for the default group.
     * @param <T>    the type of the object.
     * @return the {@code object}.
     * @throws AssertionError when the {@code object} has any constraint violation.
     */
    public static <T> T assertValid(
            final T object, final Class<?>... groups) {
        Objects.requireNonNull(object, "object is null");
        Objects.requireNonNull(groups, "groups is null");
        assertThat(validate(object, groups))
                .as("the constraint violations of %s", object)
                .isEmpty();
        return object;
    }

    // ---------------------------------------------------------------------------------------------

    private ___Validation_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
