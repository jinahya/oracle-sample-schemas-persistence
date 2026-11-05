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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

/**
 * Holds an object mapper, created once per JVM, and the helpers which read and write a JSON
 * document with it.
 * <p>
 * The mapper is left as {@link ObjectMapper#ObjectMapper()} configures it, so reading a document
 * which has a property the type does not fails.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public final class ___Jackson_TestUtils {

    /**
     * Holds the object mapper, which the class loads on first use.
     */
    private static final class Holder {

        /**
         * The object mapper; it is safe to share once configured, and is not configured again.
         */
        private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

        private Holder() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Applies the specified function to the object mapper, which the function must not configure.
     *
     * @param function the function to apply.
     * @param <R>      the type of the result.
     * @return the result of the {@code function}.
     */
    public static <R> R applyObjectMapper(
            final Function<? super ObjectMapper, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return function.apply(Holder.OBJECT_MAPPER);
    }

    /**
     * Reads a value of the specified type from the specified JSON document.
     *
     * @param bytes the JSON document.
     * @param type  the type of the value.
     * @param <T>   the type of the value.
     * @return the value read.
     * @throws IOException when the {@code bytes} are not a JSON document which binds to the
     *                     {@code type}.
     */
    public static <T> T readValue(final byte[] bytes, final Class<T> type) throws IOException {
        Objects.requireNonNull(bytes, "bytes is null");
        Objects.requireNonNull(type, "type is null");
        return Holder.OBJECT_MAPPER.readValue(bytes, type);
    }

    /**
     * Writes the specified value as a JSON document.
     *
     * @param value the value to write.
     * @return the JSON document written.
     * @throws IOException when the {@code value} fails to be written.
     */
    public static byte[] writeValueAsBytes(final Object value) throws IOException {
        Objects.requireNonNull(value, "value is null");
        return Holder.OBJECT_MAPPER.writeValueAsBytes(value);
    }

    // ---------------------------------------------------------------------------------------------

    private ___Jackson_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
