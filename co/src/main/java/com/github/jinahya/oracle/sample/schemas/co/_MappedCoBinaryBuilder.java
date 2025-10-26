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

import java.time.LocalDate;

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

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedCoBinaryBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ----------------------------------------------------------------------------------------------------------- bytes
    public byte[] bytes() {
        return bytes;
    }

    public SELF bytes(final byte[] bytes) {
        this.bytes = bytes;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- mimeType
    public String mimeType() {
        return mimeType;
    }

    public SELF mimeType(final String mimeType) {
        this.mimeType = mimeType;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- filename
    public String filename() {
        return filename;
    }

    public SELF filename(final String filename) {
        this.filename = filename;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------------- charset
    public String charset() {
        return charset;
    }

    public SELF charset(final String charset) {
        this.charset = charset;
        return (SELF) this;
    }

    // ----------------------------------------------------------------------------------------------------- lastUpdated
    public LocalDate lastUpdated() {
        return lastUpdated;
    }

    public SELF lastUpdated(final LocalDate lastUpdated) {
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
