package com.github.jinahya.oracle.sample.schemas.co.mapped;

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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@SuppressWarnings({
        "unchecked",
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public abstract class _MappedCoBinaryBuilder<
        SELF extends _MappedCoBinaryBuilder<SELF, TARGET>,
        TARGET extends _MappedCoBinary
        >
        extends _MappedCoBuilder<SELF, TARGET> {

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static <BUILDER extends _MappedCoBinaryBuilder<BUILDER, ?>>
    BUILDER from(@Nonnull final Supplier<? extends BUILDER> instantiator, @Nonnull final Path path,
                 @Nullable String mimeType, @Nullable String filename, @Nullable final String charset,
                 @Nullable LocalDate lastUpdated)
            throws IOException {
        final BUILDER instance =
                Objects.requireNonNull(
                        Objects.requireNonNull(instantiator, "instantiator is null").get(),
                        "null instantiated from " + instantiator
                );
        if (!Files.isRegularFile(Objects.requireNonNull(path, "path is null"))) {
            throw new IllegalArgumentException("not a regular file: " + path);
        }
        // ------------------------------------------------------------------------------------------------------- bytes
        instance.bytes(Files.readAllBytes(path));
        // ---------------------------------------------------------------------------------------------------- mimeType
        if (mimeType == null) {
            mimeType = Files.probeContentType(path);
        }
        instance.mimeType(mimeType);
        // ---------------------------------------------------------------------------------------------------- filename
        if (filename == null) {
            filename = path.getFileName().toString();
        }
        instance.filename(filename);
        // ----------------------------------------------------------------------------------------------------- charset
        instance.charset(charset);
        // -------------------------------------------------------------------------------------------------- latUpdated
        if (lastUpdated == null) {
            lastUpdated = LocalDate.now();
        }
        instance.lastUpdated(lastUpdated);
        // -------------------------------------------------------------------------------------------------------------
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedCoBinaryBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ----------------------------------------------------------------------------------------------------------- bytes
    protected byte[] bytes() {
        return bytes;
    }

    protected SELF bytes(final byte[] bytes) {
        this.bytes = Optional.ofNullable(bytes)
                .map(v -> Arrays.copyOf(v, v.length))
                .orElse(null);
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- mimeType
    protected String mimeType() {
        return mimeType;
    }

    protected SELF mimeType(final String mimeType) {
        this.mimeType = mimeType;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- filename
    protected String filename() {
        return filename;
    }

    protected SELF filename(final String filename) {
        this.filename = filename;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------------- charset
    protected String charset() {
        return charset;
    }

    protected SELF charset(final String charset) {
        this.charset = charset;
        return (SELF) this;
    }

    // ----------------------------------------------------------------------------------------------------- lastUpdated
    protected LocalDate lastUpdated() {
        return lastUpdated;
    }

    protected SELF lastUpdated(final LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private byte[] bytes;

    private String mimeType;

    private String filename;

    private String charset;

    private LocalDate lastUpdated;
}
