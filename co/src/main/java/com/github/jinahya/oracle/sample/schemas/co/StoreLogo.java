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

import jakarta.annotation.Nullable;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;

/**
 * An embeddable class for mapping the logo columns of the {@value Store#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @apiNote The state is inherited from {@link _Binary}, which names no column; the overrides below bind each inherited
 * attribute to its {@value Store#TABLE_NAME} column, so embedding needs no further mapping.
 */
@Embeddable
@AttributeOverride(
        name = _Binary.ATTRIBUTE_NAME_BYTES,
        column = @Column(
                name = Store.COLUMN_NAME_LOGO,
                nullable = true,
                insertable = true,
                updatable = true
        )
)
@AttributeOverride(
        name = _Binary.ATTRIBUTE_NAME_MIME_TYPE,
        column = @Column(
                name = Store.COLUMN_NAME_LOGO_MIME_TYPE,
                nullable = true,
                insertable = true,
                updatable = true,
                length = Store.COLUMN_LENGTH_LOGO_MIME_TYPE
        )
)
@AttributeOverride(
        name = _Binary.ATTRIBUTE_NAME_FILENAME,
        column = @Column(
                name = Store.COLUMN_NAME_LOGO_FILENAME,
                nullable = true,
                insertable = true,
                updatable = true,
                length = Store.COLUMN_LENGTH_LOGO_FILENAME
        )
)
@AttributeOverride(
        name = _Binary.ATTRIBUTE_NAME_CHARSET,
        column = @Column(
                name = Store.COLUMN_NAME_LOGO_CHARSET,
                nullable = true,
                insertable = true,
                updatable = true,
                length = Store.COLUMN_LENGTH_LOGO_CHARSET
        )
)
@AttributeOverride(
        name = _Binary.ATTRIBUTE_NAME_LAST_UPDATED,
        column = @Column(
                name = Store.COLUMN_NAME_LOGO_LAST_UPDATED,
                nullable = true,
                insertable = true,
                updatable = true
        )
)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class StoreLogo extends _Binary {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected StoreLogo() {
        super();
    }

    // ------------------------------------------------------------------------------------------------------------ LOGO

    /**
     * Returns current value of {@code logo} attribute.
     *
     * @return current value of {@code logo} attribute.
     */
    @Nullable
    public byte[] getLogo() {
        return this.getBytes();
    }

    void setLogo(@Nullable final byte[] logo) {
        this.setBytes(logo);
    }

    // -------------------------------------------------------------------------------------------------- LOGO_MIME_TYPE

    /**
     * Returns current value of {@code logoMimeType} attribute.
     *
     * @return current value of {@code logoMimeType} attribute.
     */
    @Nullable
    public String getLogoMimeType() {
        return this.getMimeType();
    }

    void setLogoMimeType(@Nullable final String logoMimeType) {
        this.setMimeType(logoMimeType);
    }

    // --------------------------------------------------------------------------------------------------- LOGO_FILENAME

    /**
     * Returns current value of {@code logoFilename} attribute.
     *
     * @return current value of {@code logoFilename} attribute.
     */
    @Nullable
    public String getLogoFilename() {
        return this.getFilename();
    }

    void setLogoFilename(@Nullable final String logoFilename) {
        this.setFilename(logoFilename);
    }

    // ---------------------------------------------------------------------------------------------------- LOGO_CHARSET

    /**
     * Returns current value of {@code logoCharset} attribute.
     *
     * @return current value of {@code logoCharset} attribute.
     */
    @Nullable
    public String getLogoCharset() {
        return this.getCharset();
    }

    void setLogoCharset(@Nullable final String logoCharset) {
        this.setCharset(logoCharset);
    }

    // ----------------------------------------------------------------------------------------------- LOGO_LAST_UPDATED

    /**
     * Returns current value of {@code logoLastUpdated} attribute.
     *
     * @return current value of {@code logoLastUpdated} attribute.
     */
    @Nullable
    protected LocalDate getLogoLastUpdated() {
        return this.getLastUpdated();
    }

    void setLogoLastUpdated(@Nullable final LocalDate logoLastUpdated) {
        this.setLastUpdated(logoLastUpdated);
    }
}
