package com.github.jinahya.oracle.sample.schemas.co;

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