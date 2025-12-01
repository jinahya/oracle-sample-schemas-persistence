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
import java.util.Objects;

@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public final class _MappedCoBinaryBuilderUtils {

    public static <
            BUILDER extends _MappedCoBinaryBuilder<BUILDER, ?>
            >
    BUILDER setFromFile(@Nonnull final BUILDER instance, @Nonnull final Path path, @Nullable String mimeType,
                        @Nullable String filename, @Nullable final String charset, @Nullable LocalDate lastUpdated)
            throws IOException {
        Objects.requireNonNull(instance, "instance is null");
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
    private _MappedCoBinaryBuilderUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
