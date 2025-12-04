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

import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@MappedSuperclass
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class _MappedCoBinary extends _MappedCo {

    // --------------------------------------------------------------------------------------------------- BYTES / bytes
    public static final String COLUMN_NAME_BYTES = "BYTES";

    // -------------------------------------------------------------------------------------------- MIME_TYPE / mimeType
    public static final String COLUMN_NAME_MIME_TYPE = "MIME_TYPE";

    // --------------------------------------------------------------------------------------------- FILENAME / filename
    public static final String COLUMN_NAME_FILENAME = "FILENAME";

    // ----------------------------------------------------------------------------------------------- CHARSET / charset
    public static final String COLUMN_NAME_CHARSET = "CHARSET";

    // -------------------------------------------------------------------------------------- LAST_UPDATED / lastUpdated
    public static final String COLUMN_NAME_LAST_UPDATED = "LAST_UPDATED";

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedCoBinary() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder to build from.
     */
    protected _MappedCoBinary(final _MappedCoBinaryBuilder<?, ?> builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
//                "bytes=" + Arrays.toString(bytes) +
               "mimeType=" + mimeType +
               ",filename=" + filename +
               ",charset=" + charset +
               ",lastUpdated=" + lastUpdated +
               '}';
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    // ----------------------------------------------------------------------------------------------------------- bytes
    @Nullable
    protected byte[] getBytes() {
        return Optional.ofNullable(bytes)
                .map(v -> Arrays.copyOf(v, v.length))
                .orElse(null);
    }

    protected void setBytes(@Nullable final byte[] bytes) {
        this.bytes = Optional.ofNullable(bytes)
                .map(v -> Arrays.copyOf(v, v.length))
                .orElse(null);
    }

    // -------------------------------------------------------------------------------------------------------- mimeType
    @Nullable
    protected String getMimeType() {
        return mimeType;
    }

    protected void setMimeType(@Nullable final String mimeType) {
        this.mimeType = mimeType;
    }

    // -------------------------------------------------------------------------------------------------------- filename
    @Nullable
    protected String getFilename() {
        return filename;
    }

    protected void setFilename(@Nullable final String filename) {
        this.filename = filename;
    }

    // --------------------------------------------------------------------------------------------------------- charset
    @Nullable
    protected String getCharset() {
        return charset;
    }

    protected void setCharset(@Nullable final String charset) {
        this.charset = charset;
    }

    // ----------------------------------------------------------------------------------------------------- lastUpdated
    @Nullable
    protected LocalDate getLastUpdated() {
        return lastUpdated;
    }

    protected void setLastUpdated(@Nullable final LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Lob
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(
            name = COLUMN_NAME_BYTES,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private byte[] bytes;

    @Nullable
    @Basic(optional = true)
    @Column(
            name = COLUMN_NAME_MIME_TYPE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String mimeType;

    @Nullable
    @Basic(optional = true)
    @Column(
            name = COLUMN_NAME_FILENAME,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String filename;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CHARSET,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String charset;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LAST_UPDATED,
            insertable = true,
            updatable = true
    )
    private LocalDate lastUpdated;
}
