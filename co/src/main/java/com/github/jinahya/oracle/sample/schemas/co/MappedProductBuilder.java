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

import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedProductBuilder<
        SELF extends MappedProductBuilder<SELF, PRODUCT>,
        PRODUCT extends MappedProduct
        >
        extends _MappedCoEntityBuilder<SELF, PRODUCT> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedProductBuilder(final Class<PRODUCT> entityClass) {
        super(entityClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ----------------------------------------------------------------------------------------------------- productName
    public String productName() {
        return productName;
    }

    public SELF productName(final String productName) {
        this.productName = productName;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- unitPrice
    public BigDecimal unitPrice() {
        return unitPrice;
    }

    public SELF unitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------- productDetails
    public byte[] productDetails() {
        return productDetails;
    }

    public SELF productDetails(final byte[] productDetails) {
        this.productDetails = productDetails;
        return (SELF) this;
    }

    // ---------------------------------------------------------------------------------------------------- productImage

    public byte[] productImage() {
        return productImage;
    }

    public SELF productImage(final byte[] productImage) {
        this.productImage = productImage;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------- imageMimeType
    public String imageMimeType() {
        return imageMimeType;
    }

    public SELF imageMimeType(final String imageMimeType) {
        this.imageMimeType = imageMimeType;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------- imageFilename
    public String imageFilename() {
        return imageFilename;
    }

    public SELF imageFilename(final String imageFilename) {
        this.imageFilename = imageFilename;
        return (SELF) this;
    }

    // ---------------------------------------------------------------------------------------------------- imageCharset
    public String imageCharset() {
        return imageCharset;
    }

    public SELF imageCharset(final String imageCharset) {
        this.imageCharset = imageCharset;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------ imageLastUpdated
    public LocalDate imageLastUpdated() {
        return imageLastUpdated;
    }

    public SELF imageLastUpdated(final LocalDate imageLastUpdated) {
        this.imageLastUpdated = imageLastUpdated;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String productName;

    private BigDecimal unitPrice;

    private byte[] productDetails;

    // -----------------------------------------------------------------------------------------------------------------
    private byte[] productImage;

    private String imageMimeType;

    private String imageFilename;

    private String imageCharset;

    private LocalDate imageLastUpdated;
}
