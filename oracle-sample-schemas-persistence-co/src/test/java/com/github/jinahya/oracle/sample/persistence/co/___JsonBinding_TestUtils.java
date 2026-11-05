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

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.function.Function;

/**
 * Holds a {@link Jsonb}, created once per JVM and closed when it exits, and the helpers which read
 * and write a JSON document with it.
 */
@Slf4j
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public final class ___JsonBinding_TestUtils {

    /**
     * Holds the {@link Jsonb}, which the class loads on first use.
     */
    private static final class Holder {

        /**
         * The {@link Jsonb}, closed by a shutdown hook.
         */
        private static final Jsonb JSONB;

        static {
            JSONB = JsonbBuilder.create();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    JSONB.close();
                } catch (final Exception e) {
                    // nothing to do, the virtual machine is shutting down
                }
            }));
        }

        private Holder() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Applies the specified function to the {@link Jsonb}, which the function must not close.
     *
     * @param function the function to apply.
     * @param <R>      the type of the result.
     * @return the result of the {@code function}.
     */
    public static <R> R applyJsonb(final Function<? super Jsonb, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return function.apply(Holder.JSONB);
    }

    /**
     * Reads a value of the specified type from the specified JSON document.
     *
     * @param bytes the JSON document.
     * @param type  the type of the value.
     * @param <T>   the type of the value.
     * @return the value read.
     * @throws jakarta.json.bind.JsonbException when the {@code bytes} are not a JSON document which
     *                                          binds to the {@code type}.
     */
    public static <T> T fromJson(final byte[] bytes, final Class<T> type) {
        Objects.requireNonNull(bytes, "bytes is null");
        Objects.requireNonNull(type, "type is null");
        return applyJsonb(b -> b.fromJson(new ByteArrayInputStream(bytes), type));
    }

    /**
     * Writes the specified value as a JSON document.
     *
     * @param value the value to write.
     * @return the JSON document written, in {@code UTF-8}.
     * @throws jakarta.json.bind.JsonbException when the {@code value} fails to be written.
     */
    public static byte[] toJson(final Object value) {
        Objects.requireNonNull(value, "value is null");
        return applyJsonb(b -> {
            final var stream = new ByteArrayOutputStream();
            b.toJson(value, stream);
            return stream.toByteArray();
        });
    }

    // ---------------------------------------------------------------------------------------------

    private ___JsonBinding_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
