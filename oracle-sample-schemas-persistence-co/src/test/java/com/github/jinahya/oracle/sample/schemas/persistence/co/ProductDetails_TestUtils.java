package com.github.jinahya.oracle.sample.schemas.persistence.co;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Utilities for testing the {@link ProductDetails} class.
 * <p>
 * The methods read an instance back from a stream, or from a byte array, and write one out again, as JSON.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Slf4j
final class ProductDetails_TestUtils {

    /**
     * Reads a new {@link ProductDetails} instance from the specified input stream, using the specified object mapper.
     *
     * @param stream the input stream from which a JSON document is read; must not be {@code null}.
     * @param mapper the object mapper with which the JSON document is bound; must not be {@code null}.
     * @return a new {@link ProductDetails} instance bound from the content of the {@code stream}.
     * @throws IOException if the {@code stream} fails to be read, or its content is not a JSON document which the
     *                     {@code mapper} can bind to {@link ProductDetails}.
     */
    static ProductDetails from(final InputStream stream, final ObjectMapper mapper) throws IOException {
        Objects.requireNonNull(stream, "stream is null");
        Objects.requireNonNull(mapper, "mapper is null");
        return mapper.readValue(stream, ProductDetails.class);
    }

    /**
     * Reads a new {@link ProductDetails} instance from the specified array of bytes, using the specified object
     * mapper.
     *
     * @param bytes  the array of bytes whose content is read as a JSON document; must not be {@code null}.
     * @param mapper the object mapper with which the JSON document is bound; must not be {@code null}.
     * @return a new {@link ProductDetails} instance bound from the content of the {@code bytes}.
     * @throws IOException if the content of the {@code bytes} is not a JSON document which the {@code mapper} can bind
     *                     to {@link ProductDetails}.
     */
    static ProductDetails from(final byte[] bytes, final ObjectMapper mapper) throws IOException {
        Objects.requireNonNull(bytes, "bytes is null");
        return from(new ByteArrayInputStream(bytes), mapper);
    }

    /**
     * Writes the specified {@link ProductDetails} instance as a JSON document, using the specified object mapper.
     *
     * @param value  the instance to write; must not be {@code null}.
     * @param mapper the object mapper with which the instance is written; must not be {@code null}.
     * @return an array of bytes containing the JSON document written from the {@code value}.
     * @throws IOException if the {@code mapper} fails to write the {@code value}.
     */
    public static byte[] toBytes(final ProductDetails value, final ObjectMapper mapper) throws IOException {
        Objects.requireNonNull(value, "value is null");
        Objects.requireNonNull(mapper, "mapper is null");
        return mapper.writeValueAsBytes(value);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ProductDetails_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
