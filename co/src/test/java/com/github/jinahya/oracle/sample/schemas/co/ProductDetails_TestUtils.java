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

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Slf4j
final class ProductDetails_TestUtils {

    static ProductDetails from(final InputStream stream, final ObjectMapper mapper) throws IOException {
        Objects.requireNonNull(stream, "stream is null");
        Objects.requireNonNull(mapper, "mapper is null");
        return mapper.readValue(stream, ProductDetails.class);
    }

    static ProductDetails from(final byte[] bytes, final ObjectMapper mapper) throws IOException {
        Objects.requireNonNull(bytes, "bytes is null");
        return from(new ByteArrayInputStream(bytes), mapper);
    }

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
