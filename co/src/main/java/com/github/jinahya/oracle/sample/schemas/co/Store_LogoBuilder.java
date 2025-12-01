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

import com.github.jinahya.oracle.sample.schemas.co.mapped._MappedCoBinaryBuilder;

import java.time.LocalDate;

public class Store_LogoBuilder extends _MappedCoBinaryBuilder<Store_LogoBuilder, Store_Logo> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    Store_LogoBuilder() {
        super(Store_Logo.class);
    }

    // ------------------------------------------------------------------------------------------------------------ logo
    public byte[] logo() {
        return super.bytes();
    }

    public Store_LogoBuilder logo(final byte[] logo) {
        return super.bytes(logo);
    }

    // ---------------------------------------------------------------------------------------------------- logoMimeType
    public String logoMimeType() {
        return super.mimeType();
    }

    public Store_LogoBuilder logoMimeType(final String logoMimeType) {
        return super.mimeType(logoMimeType);
    }

    // ---------------------------------------------------------------------------------------------------- logoFilename
    public String logoFilename() {
        return super.filename();
    }

    public Store_LogoBuilder logoFilename(final String logoFilename) {
        return super.filename(logoFilename);
    }

    // ----------------------------------------------------------------------------------------------------- logoCharset
    public String logoCharset() {
        return super.charset();
    }

    public Store_LogoBuilder logoCharset(final String logoCharset) {
        return super.charset(logoCharset);
    }

    // ------------------------------------------------------------------------------------------------- logoLastUpdated
    public LocalDate logoLastUpdated() {
        return super.lastUpdated();
    }

    public Store_LogoBuilder logoLastUpdated(final LocalDate logoLastUpdated) {
        return super.lastUpdated(logoLastUpdated);
    }
}
