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

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReaderFactory;
import jakarta.json.JsonStructure;
import jakarta.json.JsonValue;
import jakarta.json.JsonWriterFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.Objects;

/**
 * Holds a reader factory and a writer factory, created once per JVM, and the helpers which read
 * and write a JSON document with them.
 */
@Slf4j
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public final class ___JsonProcessing_TestUtils {

    /**
     * Holds the factories, which the class loads on first use.
     */
    private static final class Holder {

        /**
         * The reader factory, with no configuration.
         */
        private static final JsonReaderFactory READER_FACTORY = Json.createReaderFactory(Map.of());

        /**
         * The writer factory, with no configuration.
         */
        private static final JsonWriterFactory WRITER_FACTORY = Json.createWriterFactory(Map.of());

        private Holder() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Reads a value from the specified JSON document.
     *
     * @param bytes the JSON document.
     * @return the value read.
     * @throws jakarta.json.JsonException when the {@code bytes} are not a JSON document.
     */
    public static JsonValue readValue(final byte[] bytes) {
        Objects.requireNonNull(bytes, "bytes is null");
        try (var reader = Holder.READER_FACTORY.createReader(new ByteArrayInputStream(bytes))) {
            return reader.readValue();
        }
    }

    /**
     * Reads an object from the specified JSON document.
     *
     * @param bytes the JSON document.
     * @return the object read.
     * @throws jakarta.json.JsonException when the {@code bytes} are not a JSON document.
     * @throws ClassCastException         when the document is not an object.
     */
    public static JsonObject readObject(final byte[] bytes) {
        return readValue(bytes).asJsonObject();
    }

    /**
     * Writes the specified structure as a JSON document.
     *
     * @param value the structure to write.
     * @return the JSON document written, in {@code UTF-8}.
     * @throws jakarta.json.JsonException when the {@code value} fails to be written.
     */
    public static byte[] write(final JsonStructure value) {
        Objects.requireNonNull(value, "value is null");
        final var stream = new ByteArrayOutputStream();
        try (var writer = Holder.WRITER_FACTORY.createWriter(stream)) {
            writer.write(value);
        }
        return stream.toByteArray();
    }

    // ---------------------------------------------------------------------------------------------

    private ___JsonProcessing_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
