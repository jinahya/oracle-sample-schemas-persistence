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

import jakarta.annotation.Nullable;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;

/**
 * An embeddable class for mapping the logo columns of the {@value Store#TABLE_NAME} table.
 * <p>
 * The accessors of this class are public, and named after the columns, so that code which takes this value from
 * its {@link Store} reads and changes it here. They delegate to the protected accessors of {@link _Binary}, which
 * hold the state; a change is persisted with the {@link Store} which embeds this value.
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
     * Returns a copy of current value of {@value Store#ATTRIBUTE_NAME_LOGO} attribute.
     *
     * @return a copy of current value of {@value Store#ATTRIBUTE_NAME_LOGO} attribute; {@code null} when it is
     * {@code null}.
     */
    @Nullable
    public byte[] getLogo() {
        return getBytes();
    }

    /**
     * Replaces current value of {@value Store#ATTRIBUTE_NAME_LOGO} attribute with a copy of the specified value.
     *
     * @param logo new value for {@value Store#ATTRIBUTE_NAME_LOGO} attribute; copied, not kept.
     */
    public void setLogo(@Nullable final byte[] logo) {
        setBytes(logo);
    }

    // -------------------------------------------------------------------------------------------------- LOGO_MIME_TYPE

    /**
     * Returns current value of {@value Store#ATTRIBUTE_NAME_LOGO_MIME_TYPE} attribute.
     *
     * @return current value of {@value Store#ATTRIBUTE_NAME_LOGO_MIME_TYPE} attribute.
     */
    @Nullable
    public String getLogoMimeType() {
        return getMimeType();
    }

    /**
     * Replaces current value of {@value Store#ATTRIBUTE_NAME_LOGO_MIME_TYPE} attribute with the specified value.
     *
     * @param logoMimeType new value for {@value Store#ATTRIBUTE_NAME_LOGO_MIME_TYPE} attribute.
     */
    public void setLogoMimeType(@Nullable final String logoMimeType) {
        setMimeType(logoMimeType);
    }

    // --------------------------------------------------------------------------------------------------- LOGO_FILENAME

    /**
     * Returns current value of {@value Store#ATTRIBUTE_NAME_LOGO_FILENAME} attribute.
     *
     * @return current value of {@value Store#ATTRIBUTE_NAME_LOGO_FILENAME} attribute.
     */
    @Nullable
    public String getLogoFilename() {
        return getFilename();
    }

    /**
     * Replaces current value of {@value Store#ATTRIBUTE_NAME_LOGO_FILENAME} attribute with the specified value.
     *
     * @param logoFilename new value for {@value Store#ATTRIBUTE_NAME_LOGO_FILENAME} attribute.
     */
    public void setLogoFilename(@Nullable final String logoFilename) {
        setFilename(logoFilename);
    }

    // ---------------------------------------------------------------------------------------------------- LOGO_CHARSET

    /**
     * Returns current value of {@value Store#ATTRIBUTE_NAME_LOGO_CHARSET} attribute.
     *
     * @return current value of {@value Store#ATTRIBUTE_NAME_LOGO_CHARSET} attribute.
     */
    @Nullable
    public String getLogoCharset() {
        return getCharset();
    }

    /**
     * Replaces current value of {@value Store#ATTRIBUTE_NAME_LOGO_CHARSET} attribute with the specified value.
     *
     * @param logoCharset new value for {@value Store#ATTRIBUTE_NAME_LOGO_CHARSET} attribute.
     */
    public void setLogoCharset(@Nullable final String logoCharset) {
        setCharset(logoCharset);
    }

    // ----------------------------------------------------------------------------------------------- LOGO_LAST_UPDATED

    /**
     * Returns current value of {@value Store#ATTRIBUTE_NAME_LOGO_LAST_UPDATED} attribute.
     *
     * @return current value of {@value Store#ATTRIBUTE_NAME_LOGO_LAST_UPDATED} attribute.
     */
    @Nullable
    public LocalDate getLogoLastUpdated() {
        return getLastUpdated();
    }

    /**
     * Replaces current value of {@value Store#ATTRIBUTE_NAME_LOGO_LAST_UPDATED} attribute with the specified value.
     *
     * @param logoLastUpdated new value for {@value Store#ATTRIBUTE_NAME_LOGO_LAST_UPDATED} attribute.
     */
    public void setLogoLastUpdated(@Nullable final LocalDate logoLastUpdated) {
        setLastUpdated(logoLastUpdated);
    }
}
