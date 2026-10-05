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
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

/**
 * A superclass for a binary payload stored alongside its metadata -- the image of the {@value Product#TABLE_NAME}
 * table, the logo of the {@value Store#TABLE_NAME} table, and anything shaped like them.
 * <p>
 * The accessors here are protected and named after the state, not the columns. Each subclass wraps them in public
 * accessors named after its own columns -- {@link ProductImage#getProductImage()}, {@link StoreLogo#getLogo()} -- which
 * are what the code embedding the subclass uses.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @apiNote No column is named here. Each embeddable subclass -- {@link ProductImage}, {@link StoreLogo} -- names all
 * five with {@link jakarta.persistence.AttributeOverride}, two of them below, which replaces the column mapping
 * outright, so anything declared here would never be read.
 * {@snippet lang = "java":
 *         @Embeddable
 *         @AttributeOverride(name = _Binary.ATTRIBUTE_NAME_BYTES,
 *                            column = @Column(name = Product.COLUMN_NAME_PRODUCT_IMAGE))
 *         @AttributeOverride(name = _Binary.ATTRIBUTE_NAME_MIME_TYPE,
 *                            column = @Column(name = Product.COLUMN_NAME_IMAGE_MIME_TYPE,
 *                                             length = Product.COLUMN_LENGTH_IMAGE_MIME_TYPE))
 *         public class ProductImage extends _Binary { ... }
 *}
 */
@MappedSuperclass
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class _Binary {

    /**
     * The name of the attribute which maps the binary payload. The value is {@value}.
     */
    static final String ATTRIBUTE_NAME_BYTES = "bytes";

    /**
     * The name of the attribute which maps the media type of the payload. The value is {@value}.
     */
    static final String ATTRIBUTE_NAME_MIME_TYPE = "mimeType";

    /**
     * The name of the attribute which maps the filename of the payload. The value is {@value}.
     */
    static final String ATTRIBUTE_NAME_FILENAME = "filename";

    /**
     * The name of the attribute which maps the character set of the payload. The value is {@value}.
     */
    static final String ATTRIBUTE_NAME_CHARSET = "charset";

    /**
     * The name of the attribute which maps the date the payload was last updated. The value is {@value}.
     */
    static final String ATTRIBUTE_NAME_LAST_UPDATED = "lastUpdated";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _Binary() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "mimeType=" + mimeType +
               ",filename=" + filename +
               ",charset=" + charset +
               ",lastUpdated=" + lastUpdated +
               '}';
    }

    // ----------------------------------------------------------------------------------------------------------- bytes

    /**
     * Returns a copy of current value of {@value #ATTRIBUTE_NAME_BYTES} attribute.
     *
     * @return a copy of current value of {@value #ATTRIBUTE_NAME_BYTES} attribute; {@code null} when it is
     * {@code null}.
     */
    @Nullable
    protected byte[] getBytes() {
        return Optional.ofNullable(bytes)
                .map(v -> Arrays.copyOf(v, v.length))
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BYTES} attribute with a copy of the specified value.
     *
     * @param bytes new value for {@value #ATTRIBUTE_NAME_BYTES} attribute; copied, not kept.
     */
    protected void setBytes(@Nullable final byte[] bytes) {
        this.bytes = Optional.ofNullable(bytes)
                .map(v -> Arrays.copyOf(v, v.length))
                .orElse(null);
    }

    // -------------------------------------------------------------------------------------------------------- mimeType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MIME_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MIME_TYPE} attribute.
     */
    @Nullable
    protected String getMimeType() {
        return mimeType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MIME_TYPE} attribute with the specified value.
     *
     * @param mimeType new value for {@value #ATTRIBUTE_NAME_MIME_TYPE} attribute.
     */
    protected void setMimeType(@Nullable final String mimeType) {
        this.mimeType = mimeType;
    }

    // -------------------------------------------------------------------------------------------------------- filename

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FILENAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FILENAME} attribute.
     */
    @Nullable
    protected String getFilename() {
        return filename;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FILENAME} attribute with the specified value.
     *
     * @param filename new value for {@value #ATTRIBUTE_NAME_FILENAME} attribute.
     */
    protected void setFilename(@Nullable final String filename) {
        this.filename = filename;
    }

    // --------------------------------------------------------------------------------------------------------- charset

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHARSET} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHARSET} attribute.
     */
    @Nullable
    protected String getCharset() {
        return charset;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHARSET} attribute with the specified value.
     *
     * @param charset new value for {@value #ATTRIBUTE_NAME_CHARSET} attribute.
     */
    protected void setCharset(@Nullable final String charset) {
        this.charset = charset;
    }

    // ----------------------------------------------------------------------------------------------------- lastUpdated

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_UPDATED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_UPDATED} attribute.
     */
    @Nullable
    protected LocalDate getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_UPDATED} attribute with the specified value.
     *
     * @param lastUpdated new value for {@value #ATTRIBUTE_NAME_LAST_UPDATED} attribute.
     */
    protected void setLastUpdated(@Nullable final LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Nullable
    @Lob
    private byte[] bytes;

    @Nullable
    private String mimeType;

    @Nullable
    private String filename;

    @Nullable
    private String charset;

    @Nullable
    private LocalDate lastUpdated;
}
