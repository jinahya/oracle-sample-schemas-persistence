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

import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedStore;
import com.github.jinahya.oracle.sample.schemas.co.mapped._MappedCoBinary;
import jakarta.annotation.Nullable;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
@AttributeOverride(name = _MappedCoBinary.COLUMN_NAME_BYTES,
                   column = @Column(name = MappedStore.COLUMN_NAME_LOGO)
)
@AttributeOverride(name = _MappedCoBinary.COLUMN_NAME_MIME_TYPE,
                   column = @Column(name = MappedStore.COLUMN_NAME_LOGO_MIME_TYPE)
)
@AttributeOverride(name = _MappedCoBinary.COLUMN_NAME_FILENAME,
                   column = @Column(name = MappedStore.COLUMN_NAME_LOGO_FILENAME)
)
@AttributeOverride(name = _MappedCoBinary.COLUMN_NAME_CHARSET,
                   column = @Column(name = MappedStore.COLUMN_NAME_LOGO_CHARSET)
)
@AttributeOverride(name = _MappedCoBinary.COLUMN_NAME_LAST_UPDATED,
                   column = @Column(name = MappedStore.COLUMN_NAME_LOGO_LAST_UPDATED)
)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class Store_Logo extends _MappedCoBinary {

    // -------------------------------------------------------------------------------------------------------- BUILDERS
    public static Store_LogoBuilder builder() {
        return new Store_LogoBuilder();
    }

    // ------------------------------------------------------------------------------------------- STATIC_FACTORY_METHOD

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Store_Logo() {
        super();
    }

    Store_Logo(final Store_LogoBuilder builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------------------ LOGO
    @Nullable
    public byte[] getLogo() {
        return getBytes();
    }

    void setLogo(@Nullable final byte[] logo) {
        super.setBytes(logo);
    }

    // -------------------------------------------------------------------------------------------------- LOGO_MIME_TYPE
    @Nullable
    public String getLogoMimeType() {
        return super.getMimeType();
    }

    void setLogoMimeType(@Nullable final String logoMimeType) {
        super.setMimeType(logoMimeType);
    }

    // --------------------------------------------------------------------------------------------------- LOGO_FILENAME
    @Nullable
    public String getLogoFilename() {
        return super.getFilename();
    }

    void setLogoFilename(@Nullable final String logoFilename) {
        super.setFilename(logoFilename);
    }

    // ---------------------------------------------------------------------------------------------------- LOGO_CHARSET
    @Nullable
    public String getLogoCharset() {
        return super.getCharset();
    }

    void setLogoCharset(@Nullable final String logoCharset) {
        super.setCharset(logoCharset);
    }

    // ----------------------------------------------------------------------------------------------- LOGO_LAST_UPDATED
    @Nullable
    protected LocalDate getLogoLastUpdated() {
        return super.getLastUpdated();
    }

    void setLogoLastUpdated(@Nullable final LocalDate logoLastUpdated) {
        super.setLastUpdated(logoLastUpdated);
    }
}
