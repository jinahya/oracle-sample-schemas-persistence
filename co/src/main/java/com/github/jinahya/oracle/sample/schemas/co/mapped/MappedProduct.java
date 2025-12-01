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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@MappedSuperclass
public abstract class MappedProduct extends _MappedCoEntity<Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PRODUCTS";

    // ------------------------------------------------------------------------------------------ PRODUCT_ID / productId

    /**
     * The name of the table column to which the {@link MappedProduct_#productId productId} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // -------------------------------------------------------------------------------------- PRODUCT_NAME / productName
    public static final String COLUMN_NAME_PRODUCT_NAME = "PRODUCT_NAME";

    public static final int COLUMN_LENGTH_PRODUCT_NAME = 255;

    public static final String ATTRIBUTE_NAME_PRODUCT_NAME = "productName";

    public static final int SIZE_MAX_PRODUCT_NAME = COLUMN_LENGTH_PRODUCT_NAME;

    // ------------------------------------------------------------------------------------------ UNIT_PRICE / unitPrice
    public static final String COLUMN_NAME_UNIT_PRICE = "UNIT_PRICE";

    public static final int COLUMN_PRECISION_UNIT_PRICE = 10;

    public static final int COLUMN_SCALE_UNIT_PRICE = 2;

    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    public static final String DECIMAL_MAX_UNIT_PRICE = "+99999999.99";

    public static final String DECIMAL_MIN_UNIT_PRICE = "-99999999.99";

    // -------------------------------------------------------------------------------- PRODUCT_DETAILS / productDetails
    public static final String COLUMN_NAME_PRODUCT_DETAILS = "PRODUCT_DETAILS";

    public static final String ATTRIBUTE_NAME_PRODUCT_DETAILS = "productDetails";

    // ------------------------------------------------------------------------------------ PRODUCT_IMAGE / productImage
    public static final String COLUMN_NAME_PRODUCT_IMAGE = "PRODUCT_IMAGE";

    public static final String ATTRIBUTE_NAME_PRODUCT_IMAGE = "productImage";

    // --------------------------------------------------------------------------------- IMAGE_MIME_TYPE / imageMimeType
    public static final String COLUMN_NAME_IMAGE_MIME_TYPE = "IMAGE_MIME_TYPE";

    public static final int COLUMN_LENGTH_IMAGE_MIME_TYPE = 512;

    public static final String ATTRIBUTE_NAME_IMAGE_MIME_TYPE = "imageMimeType";

    public static final int SIZE_MAX_IMAGE_MIME_TYPE = COLUMN_LENGTH_IMAGE_MIME_TYPE;

    // ---------------------------------------------------------------------------------- IMAGE_FILENAME / imageFilename
    public static final String COLUMN_NAME_IMAGE_FILENAME = "IMAGE_FILENAME";

    public static final int COLUMN_LENGTH_IMAGE_FILENAME = 512;

    public static final String ATTRIBUTE_NAME_IMAGE_FILENAME = "imageFilename";

    public static final int SIZE_MAX_IMAGE_FILENAME = COLUMN_LENGTH_IMAGE_FILENAME;

    // ------------------------------------------------------------------------------------ IMAGE_CHARSET / imageCharset
    public static final String COLUMN_NAME_IMAGE_CHARSET = "IMAGE_CHARSET";

    public static final int COLUMN_LENGTH_IMAGE_CHARSET = 512;

    public static final String ATTRIBUTE_NAME_IMAGE_CHARSET = "imageCharset";

    public static final int SIZE_MAX_IMAGE_CHARSET = COLUMN_LENGTH_IMAGE_CHARSET;

    // --------------------------------------------------------------------------- IMAGE_LAST_UPDATED / imageLastUpdated
    public static final String COLUMN_NAME_IMAGE_LAST_UPDATED = "IMAGE_LAST_UPDATED";

    public static final String ATTRIBUTE_NAME_IMAGE_LAST_UPDATED = "imageLastUpdated";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProduct() {
        super();
    }

    protected MappedProduct(@Nonnull final MappedProductBuilder<?, ?> builder) {
        super(builder);
        productName = builder.productName();
        unitPrice = builder.unitPrice();
        productDetails = builder.productDetails();
        productImage = builder.productImage();
        imageMimeType = builder.imageMimeType();
        imageFilename = builder.imageFilename();
        imageCharset = builder.imageCharset();
        imageLastUpdated = builder.imageLastUpdated();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "productId=" + productId +
               ",productName=" + productName +
               ",unitPrice=" + unitPrice +
//                ",productDetails=" + Objects.hashCode(productDetails) + // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//                ",productImage=" + Objects.hashCode(productImage) + // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
               ",imageMimeType=" + imageMimeType +
               ",imageFilename=" + imageFilename +
               ",imageCharset=" + imageCharset +
               ",imageLastUpdated=" + imageLastUpdated +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedProduct that)) {
            return false;
        }
        return Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getProductId());
    }

    // ------------------------------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@link MappedProduct_#productId productId} attribute.
     *
     * @return current value of the {@link MappedProduct_#productId productId} attribute.
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@link MappedProduct_#productId productId} attribute with specified value.
     *
     * @param productId new value for the {@link MappedProduct_#productId productId} attribute.
     */
    protected void setProductId(final Long productId) {
        this.productId = productId;
    }

    // ----------------------------------------------------------------------------------------------------- productName
    @Nonnull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@Nonnull final String productName) {
        this.productName = productName;
    }

    // ------------------------------------------------------------------------------------------------------- unitPrice
    @Nullable
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(@Nullable final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // -------------------------------------------------------------------------------------------------- productDetails
    @Nullable
    public byte[] getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(@Nullable final byte[] productDetails) {
        this.productDetails = productDetails;
    }

    public <R> R getProductDetailsAsMapped(final Function<? super byte[], ? extends R> mapper) {
        return Optional.ofNullable(getProductDetails())
                .map(Objects.requireNonNull(mapper, "mapper is null"))
                .orElse(null);
    }

    public <T> void setProductDetailsFromMapped(final T productDetails,
                                                final Function<? super T, ? extends byte[]> mapper) {
        setProductDetails(
                Optional.ofNullable(productDetails)
                        .map(Objects.requireNonNull(mapper, "mapper is null"))
                        .orElse(null)
        );
    }

    // ---------------------------------------------------------------------------------------------------- productImage
    @Nullable
    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(@Nullable final byte[] productImage) {
        this.productImage = productImage;
    }

    // --------------------------------------------------------------------------------------------------- imageMimeType
    @Nullable
    public String getImageMimeType() {
        return imageMimeType;
    }

    public void setImageMimeType(@Nullable final String imageMimeType) {
        this.imageMimeType = imageMimeType;
    }

    // --------------------------------------------------------------------------------------------------- imageFilename
    @Nullable
    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(@Nullable final String imageFilename) {
        this.imageFilename = imageFilename;
    }

    // ---------------------------------------------------------------------------------------------------- imageCharset
    @Nullable
    public String getImageCharset() {
        return imageCharset;
    }

    public void setImageCharset(@Nullable final String imageCharset) {
        this.imageCharset = imageCharset;
    }

    // ------------------------------------------------------------------------------------------------ imageLastUpdated
    @Nullable
    public LocalDate getImageLastUpdated() {
        return imageLastUpdated;
    }

    public void setImageLastUpdated(@Nullable final LocalDate imageLastUpdated) {
        this.imageLastUpdated = imageLastUpdated;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false,
            /* insertable = false,*/ insertable = true, // EclipseLInk
            updatable = false
    )
    private Long productId;

    @Nonnull
    @Size(max = SIZE_MAX_PRODUCT_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_PRODUCT_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PRODUCT_NAME
    )
    private String productName;

    @Nullable
    @DecimalMax(value = DECIMAL_MAX_UNIT_PRICE, inclusive = true)
    @DecimalMin(value = DECIMAL_MIN_UNIT_PRICE, inclusive = true)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_UNIT_PRICE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_UNIT_PRICE,
            scale = COLUMN_SCALE_UNIT_PRICE
    )
    private BigDecimal unitPrice;

    @Nullable
    @Lob
    @Basic(optional = true, fetch = FetchType.LAZY)
    @Column(name = COLUMN_NAME_PRODUCT_DETAILS, nullable = true, insertable = true, updatable = true)
    private byte[] productDetails;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Lob
    @Basic(optional = true, fetch = FetchType.LAZY)
    @Column(name = COLUMN_NAME_PRODUCT_IMAGE, nullable = true, insertable = true, updatable = true)
    private byte[] productImage;

    @Nullable
    @Size(max = SIZE_MAX_IMAGE_MIME_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_IMAGE_MIME_TYPE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_IMAGE_MIME_TYPE
    )
    private String imageMimeType;

    @Nullable
    @Size(max = SIZE_MAX_IMAGE_FILENAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_IMAGE_FILENAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_IMAGE_FILENAME
    )
    private String imageFilename;

    @Nullable
    @Size(max = SIZE_MAX_IMAGE_CHARSET)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_IMAGE_CHARSET,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_IMAGE_CHARSET
    )
    private String imageCharset;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_IMAGE_LAST_UPDATED, insertable = true, updatable = true)
    private LocalDate imageLastUpdated;
}
