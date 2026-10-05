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
 * An embeddable class for mapping the image columns of the {@value Product#TABLE_NAME} table.
 * <p>
 * The accessors of this class are public, and named after the columns, so that code which takes this value from its
 * {@link Product} reads and changes it here. They delegate to the protected accessors of {@link _Binary}, which hold
 * the state; a change is persisted with the {@link Product} which embeds this value.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @apiNote The state is inherited from {@link _Binary}, which names no column; the overrides below bind each inherited
 * attribute to its {@value Product#TABLE_NAME} column, so embedding needs no further mapping.
 */
@Embeddable
@AttributeOverride(
        name = _Binary.ATTRIBUTE_NAME_BYTES,
        column = @Column(
                name = Product.COLUMN_NAME_PRODUCT_IMAGE,
                nullable = true,
                insertable = true,
                updatable = true
        )
)
@AttributeOverride(
        name = _Binary.ATTRIBUTE_NAME_MIME_TYPE,
        column = @Column(
                name = Product.COLUMN_NAME_IMAGE_MIME_TYPE,
                nullable = true,
                insertable = true,
                updatable = true,
                length = Product.COLUMN_LENGTH_IMAGE_MIME_TYPE
        )
)
@AttributeOverride(
        name = _Binary.ATTRIBUTE_NAME_FILENAME,
        column = @Column(
                name = Product.COLUMN_NAME_IMAGE_FILENAME,
                nullable = true,
                insertable = true,
                updatable = true,
                length = Product.COLUMN_LENGTH_IMAGE_FILENAME
        )
)
@AttributeOverride(
        name = _Binary.ATTRIBUTE_NAME_CHARSET,
        column = @Column(
                name = Product.COLUMN_NAME_IMAGE_CHARSET,
                nullable = true,
                insertable = true,
                updatable = true,
                length = Product.COLUMN_LENGTH_IMAGE_CHARSET
        )
)
@AttributeOverride(
        name = _Binary.ATTRIBUTE_NAME_LAST_UPDATED,
        column = @Column(
                name = Product.COLUMN_NAME_IMAGE_LAST_UPDATED,
                nullable = true,
                insertable = true,
                updatable = true
        )
)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class ProductImage extends _Binary {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductImage() {
        super();
    }

    // --------------------------------------------------------------------------------------------------- PRODUCT_IMAGE

    /**
     * Returns a copy of current value of {@value Product#ATTRIBUTE_NAME_PRODUCT_IMAGE} attribute.
     *
     * @return a copy of current value of {@value Product#ATTRIBUTE_NAME_PRODUCT_IMAGE} attribute; {@code null} when it
     * is {@code null}.
     */
    @Nullable
    public byte[] getProductImage() {
        return getBytes();
    }

    /**
     * Replaces current value of {@value Product#ATTRIBUTE_NAME_PRODUCT_IMAGE} attribute with a copy of the specified
     * value.
     *
     * @param productImage new value for {@value Product#ATTRIBUTE_NAME_PRODUCT_IMAGE} attribute; copied, not kept.
     */
    public void setProductImage(@Nullable final byte[] productImage) {
        setBytes(productImage);
    }

    // ------------------------------------------------------------------------------------------------- IMAGE_MIME_TYPE

    /**
     * Returns current value of {@value Product#ATTRIBUTE_NAME_IMAGE_MIME_TYPE} attribute.
     *
     * @return current value of {@value Product#ATTRIBUTE_NAME_IMAGE_MIME_TYPE} attribute.
     */
    @Nullable
    public String getImageMimeType() {
        return getMimeType();
    }

    /**
     * Replaces current value of {@value Product#ATTRIBUTE_NAME_IMAGE_MIME_TYPE} attribute with the specified value.
     *
     * @param imageMimeType new value for {@value Product#ATTRIBUTE_NAME_IMAGE_MIME_TYPE} attribute.
     */
    public void setImageMimeType(@Nullable final String imageMimeType) {
        setMimeType(imageMimeType);
    }

    // -------------------------------------------------------------------------------------------------- IMAGE_FILENAME

    /**
     * Returns current value of {@value Product#ATTRIBUTE_NAME_IMAGE_FILENAME} attribute.
     *
     * @return current value of {@value Product#ATTRIBUTE_NAME_IMAGE_FILENAME} attribute.
     */
    @Nullable
    public String getImageFilename() {
        return getFilename();
    }

    /**
     * Replaces current value of {@value Product#ATTRIBUTE_NAME_IMAGE_FILENAME} attribute with the specified value.
     *
     * @param imageFilename new value for {@value Product#ATTRIBUTE_NAME_IMAGE_FILENAME} attribute.
     */
    public void setImageFilename(@Nullable final String imageFilename) {
        setFilename(imageFilename);
    }

    // --------------------------------------------------------------------------------------------------- IMAGE_CHARSET

    /**
     * Returns current value of {@value Product#ATTRIBUTE_NAME_IMAGE_CHARSET} attribute.
     *
     * @return current value of {@value Product#ATTRIBUTE_NAME_IMAGE_CHARSET} attribute.
     */
    @Nullable
    public String getImageCharset() {
        return getCharset();
    }

    /**
     * Replaces current value of {@value Product#ATTRIBUTE_NAME_IMAGE_CHARSET} attribute with the specified value.
     *
     * @param imageCharset new value for {@value Product#ATTRIBUTE_NAME_IMAGE_CHARSET} attribute.
     */
    public void setImageCharset(@Nullable final String imageCharset) {
        setCharset(imageCharset);
    }

    // ---------------------------------------------------------------------------------------------- IMAGE_LAST_UPDATED

    /**
     * Returns current value of {@value Product#ATTRIBUTE_NAME_IMAGE_LAST_UPDATED} attribute.
     *
     * @return current value of {@value Product#ATTRIBUTE_NAME_IMAGE_LAST_UPDATED} attribute.
     */
    @Nullable
    public LocalDate getImageLastUpdated() {
        return getLastUpdated();
    }

    /**
     * Replaces current value of {@value Product#ATTRIBUTE_NAME_IMAGE_LAST_UPDATED} attribute with the specified value.
     *
     * @param imageLastUpdated new value for {@value Product#ATTRIBUTE_NAME_IMAGE_LAST_UPDATED} attribute.
     */
    public void setImageLastUpdated(@Nullable final LocalDate imageLastUpdated) {
        setLastUpdated(imageLastUpdated);
    }
}
