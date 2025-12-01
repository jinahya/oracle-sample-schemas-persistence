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

import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedProduct;
import com.github.jinahya.oracle.sample.schemas.co.mapped._MappedCoBinary;
import jakarta.annotation.Nullable;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
@AttributeOverride(name = _MappedCoBinary.COLUMN_NAME_BYTES,
                   column = @Column(name = MappedProduct.COLUMN_NAME_PRODUCT_IMAGE)
)
@AttributeOverride(name = _MappedCoBinary.COLUMN_NAME_MIME_TYPE,
                   column = @Column(name = MappedProduct.COLUMN_NAME_IMAGE_MIME_TYPE)
)
@AttributeOverride(name = _MappedCoBinary.COLUMN_NAME_FILENAME,
                   column = @Column(name = MappedProduct.COLUMN_NAME_IMAGE_FILENAME)
)
@AttributeOverride(name = _MappedCoBinary.COLUMN_NAME_CHARSET,
                   column = @Column(name = MappedProduct.COLUMN_NAME_IMAGE_CHARSET)
)
@AttributeOverride(name = _MappedCoBinary.COLUMN_NAME_LAST_UPDATED,
                   column = @Column(name = MappedProduct.COLUMN_NAME_IMAGE_LAST_UPDATED)
)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class Product_Image extends _MappedCoBinary {

    // -------------------------------------------------------------------------------------------------------- BUILDERS
    public static Product_ImageBuilder builder() {
        return new Product_ImageBuilder();
    }

    // ------------------------------------------------------------------------------------------- STATIC_FACTORY_METHOD

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Product_Image() {
        super();
    }

    Product_Image(final Product_ImageBuilder builder) {
        super(builder);
    }

    // --------------------------------------------------------------------------------------------------- PRODUCT_IMAGE
    @Nullable
    public byte[] getProductImage() {
        return super.getBytes();
    }

    void setProductImage(@Nullable final byte[] productImage) {
        super.setBytes(productImage);
    }

    // ------------------------------------------------------------------------------------------------- IMAGE_MIME_TYPE
    @Nullable
    public String getImageMimeType() {
        return super.getMimeType();
    }

    void setImageMimeType(@Nullable final String imageMimeType) {
        super.setMimeType(imageMimeType);
    }

    // -------------------------------------------------------------------------------------------------- IMAGE_FILENAME
    @Nullable
    public String getImageFilename() {
        return super.getFilename();
    }

    void setImageFilename(@Nullable final String imageFilename) {
        super.setFilename(imageFilename);
    }

    // --------------------------------------------------------------------------------------------------- IMAGE_CHARSET
    @Nullable
    public String getImageCharset() {
        return super.getCharset();
    }

    void setImageCharset(@Nullable final String imageCharset) {
        super.setCharset(imageCharset);
    }

    // ---------------------------------------------------------------------------------------------- IMAGE_LAST_UPDATED
    @Nullable
    protected LocalDate getImageLastUpdated() {
        return super.getLastUpdated();
    }

    void setImageLastUpdated(@Nullable final LocalDate imageLastUpdated) {
        super.setLastUpdated(imageLastUpdated);
    }
}
