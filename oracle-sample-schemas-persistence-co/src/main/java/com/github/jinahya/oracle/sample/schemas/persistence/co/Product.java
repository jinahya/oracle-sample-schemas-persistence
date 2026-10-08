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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * An entity class for mapping the {@value Product#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(
        name = "Product.selectListByUnitPriceGreaterThanEqualAndUnitPriceLessThanOrderByUnitPricesAsc",
        query = """
                SELECT e
                FROM Product e
                WHERE e.unitPrice >= :unitPriceMinInclusive AND e.unitPrice < :unitPriceMaxExclusive
                ORDER BY e.unitPrice ASC"""
)
@NamedQuery(
        name = "Product.selectListByUnitPriceBetweenOrderByUnitPricesAsc",
        query = """
                SELECT e
                FROM Product e
                WHERE e.unitPrice BETWEEN :unitPriceAfter AND :unitPriceBefore
                ORDER BY e.unitPrice ASC"""
)
@NamedQuery(
        name = "Product.selectListOrderByProductIdAscProductIdGt",
        query = """
                SELECT e
                FROM Product e
                WHERE e.productId > :productIdMinExclusive
                ORDER BY e.productId ASC"""
)
@NamedQuery(
        name = "Product.selectListOrderByProductIdAsc",
        query = """
                SELECT e
                FROM Product e
                ORDER BY e.productId ASC"""
)
@Entity
@Table(name = Product.TABLE_NAME)
public class Product implements __DomainEntity<Long> {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PRODUCTS";

    // ------------------------------------------------------------------------------------------ PRODUCT_ID / productId

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // -------------------------------------------------------------------------------------- PRODUCT_NAME / productName

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_NAME = "PRODUCT_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_NAME = "productName";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PRODUCT_NAME = COLUMN_LENGTH_PRODUCT_NAME;

    // ------------------------------------------------------------------------------------------ UNIT_PRICE / unitPrice

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE = "UNIT_PRICE";

    /**
     * The precision of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNIT_PRICE = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNIT_PRICE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute. The value is {@value}.
     */
    public static final String DECIMAL_MAX_UNIT_PRICE = "+99999999.99";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute. The value is {@value}.
     */
    public static final String DECIMAL_MIN_UNIT_PRICE = "-99999999.99";

    // -------------------------------------------------------------------------------- PRODUCT_DETAILS / productDetails

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PRODUCT_DETAILS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_DETAILS = "PRODUCT_DETAILS";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_DETAILS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_DETAILS = "productDetails";

    // ------------------------------------------------------------------------------------ PRODUCT_IMAGE / productImage

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PRODUCT_IMAGE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_IMAGE = "PRODUCT_IMAGE";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_IMAGE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_IMAGE = "productImage";

    // --------------------------------------------------------------------------------- IMAGE_MIME_TYPE / imageMimeType

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_IMAGE_MIME_TYPE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_IMAGE_MIME_TYPE = "IMAGE_MIME_TYPE";

    /**
     * The length of the {@value #COLUMN_NAME_IMAGE_MIME_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_IMAGE_MIME_TYPE = 512;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IMAGE_MIME_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IMAGE_MIME_TYPE = "imageMimeType";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_IMAGE_MIME_TYPE} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_IMAGE_MIME_TYPE = COLUMN_LENGTH_IMAGE_MIME_TYPE;

    // ---------------------------------------------------------------------------------- IMAGE_FILENAME / imageFilename

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_IMAGE_FILENAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_IMAGE_FILENAME = "IMAGE_FILENAME";

    /**
     * The length of the {@value #COLUMN_NAME_IMAGE_FILENAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_IMAGE_FILENAME = 512;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IMAGE_FILENAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IMAGE_FILENAME = "imageFilename";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_IMAGE_FILENAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_IMAGE_FILENAME = COLUMN_LENGTH_IMAGE_FILENAME;

    // ------------------------------------------------------------------------------------ IMAGE_CHARSET / imageCharset

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_IMAGE_CHARSET} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_IMAGE_CHARSET = "IMAGE_CHARSET";

    /**
     * The length of the {@value #COLUMN_NAME_IMAGE_CHARSET} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_IMAGE_CHARSET = 512;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IMAGE_CHARSET} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IMAGE_CHARSET = "imageCharset";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_IMAGE_CHARSET} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_IMAGE_CHARSET = COLUMN_LENGTH_IMAGE_CHARSET;

    // --------------------------------------------------------------------------- IMAGE_LAST_UPDATED / imageLastUpdated

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_IMAGE_LAST_UPDATED} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_IMAGE_LAST_UPDATED = "IMAGE_LAST_UPDATED";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IMAGE_LAST_UPDATED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IMAGE_LAST_UPDATED = "imageLastUpdated";

    // ----------------------------------------------------------------------------------------------------- ORDER_ITEMS

    /**
     * The name of the attribute which maps the order items which order this product. The value is {@value}.
     *
     * @see OrderItem#ATTRIBUTE_NAME_PRODUCT
     */
    public static final String ATTRIBUTE_NAME_ORDER_ITEMS = "orderItems";

    // ----------------------------------------------------------------------------------------------------- INVENTORIES

    /**
     * The name of the attribute which maps the inventories which hold this product. The value is {@value}.
     *
     * @see Inventory#ATTRIBUTE_NAME_PRODUCT
     */
    public static final String ATTRIBUTE_NAME_INVENTORIES = "inventories";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Product() {
        super();
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
//               ",orderItems=" + orderItems +
//               ",inventories=" + inventories +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the generated {@code @Id} alone, the other instance's read through its getter, which is
     * what makes the comparison correct when the other instance is still a lazy proxy. An instance whose {@code @Id} is
     * still {@code null} -- one not yet persisted -- equals itself only.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Product that)) {
            return false;
        }
        return productId != null && productId.equals(that.getProductId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is constant -- the class's -- so that it does not change when the generated {@code @Id} is
     * assigned on persist, which would lose an instance already held in a hash-based collection.
     */
    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    // ------------------------------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified value.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(final Long productId) {
        this.productId = productId;
    }

    // ----------------------------------------------------------------------------------------------------- productName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute.
     */
    @Nonnull
    public String getProductName() {
        return productName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute with the specified value.
     *
     * @param productName new value for {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute.
     */
    public void setProductName(@Nonnull final String productName) {
        this.productName = productName;
    }

    // ------------------------------------------------------------------------------------------------------- unitPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    @Nullable
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute with the specified value.
     *
     * @param unitPrice new value for {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public void setUnitPrice(@Nullable final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // -------------------------------------------------------------------------------------------------- productDetails

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_DETAILS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_DETAILS} attribute.
     */
    @Nullable
    public byte[] getProductDetails() {
        return productDetails;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_DETAILS} attribute with the specified value.
     *
     * @param productDetails new value for {@value #ATTRIBUTE_NAME_PRODUCT_DETAILS} attribute.
     */
    public void setProductDetails(@Nullable final byte[] productDetails) {
        this.productDetails = productDetails;
    }

    /**
     * Returns current value of the {@value #ATTRIBUTE_NAME_PRODUCT_DETAILS} attribute, mapped by the specified
     * function.
     *
     * @param <R>    the type of the mapped value.
     * @param mapper the function to apply to current value of the {@value #ATTRIBUTE_NAME_PRODUCT_DETAILS} attribute.
     * @return the mapped value; {@code null} when the {@value #ATTRIBUTE_NAME_PRODUCT_DETAILS} attribute is
     * {@code null}.
     * @throws NullPointerException if {@code mapper} is {@code null}.
     */
    public <R> R getProductDetailsAsMapped(final Function<? super byte[], ? extends R> mapper) {
        return Optional.ofNullable(getProductDetails())
                .map(Objects.requireNonNull(mapper, "mapper is null"))
                .orElse(null);
    }

    /**
     * Replaces current value of the {@value #ATTRIBUTE_NAME_PRODUCT_DETAILS} attribute with the specified value, mapped
     * by the specified function.
     *
     * @param <T>            the type of the specified value.
     * @param productDetails the value to map and set.
     * @param mapper         the function which maps the specified value.
     * @throws NullPointerException if {@code mapper} is {@code null}.
     */
    public <T> void setProductDetailsFromMapped(final T productDetails,
                                                final Function<? super T, ? extends byte[]> mapper) {
        setProductDetails(
                Optional.ofNullable(productDetails)
                        .map(Objects.requireNonNull(mapper, "mapper is null"))
                        .orElse(null)
        );
    }

    // ---------------------------------------------------------------------------------------------------- productImage

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_IMAGE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_IMAGE} attribute.
     */
    @Nullable
    public byte[] getProductImage() {
        return productImage;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_IMAGE} attribute with the specified value.
     *
     * @param productImage new value for {@value #ATTRIBUTE_NAME_PRODUCT_IMAGE} attribute.
     */
    public void setProductImage(@Nullable final byte[] productImage) {
        this.productImage = productImage;
    }

    // --------------------------------------------------------------------------------------------------- imageMimeType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IMAGE_MIME_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IMAGE_MIME_TYPE} attribute.
     */
    @Nullable
    public String getImageMimeType() {
        return imageMimeType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IMAGE_MIME_TYPE} attribute with the specified value.
     *
     * @param imageMimeType new value for {@value #ATTRIBUTE_NAME_IMAGE_MIME_TYPE} attribute.
     */
    public void setImageMimeType(@Nullable final String imageMimeType) {
        this.imageMimeType = imageMimeType;
    }

    // --------------------------------------------------------------------------------------------------- imageFilename

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IMAGE_FILENAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IMAGE_FILENAME} attribute.
     */
    @Nullable
    public String getImageFilename() {
        return imageFilename;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IMAGE_FILENAME} attribute with the specified value.
     *
     * @param imageFilename new value for {@value #ATTRIBUTE_NAME_IMAGE_FILENAME} attribute.
     */
    public void setImageFilename(@Nullable final String imageFilename) {
        this.imageFilename = imageFilename;
    }

    // ---------------------------------------------------------------------------------------------------- imageCharset

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IMAGE_CHARSET} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IMAGE_CHARSET} attribute.
     */
    @Nullable
    public String getImageCharset() {
        return imageCharset;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IMAGE_CHARSET} attribute with the specified value.
     *
     * @param imageCharset new value for {@value #ATTRIBUTE_NAME_IMAGE_CHARSET} attribute.
     */
    public void setImageCharset(@Nullable final String imageCharset) {
        this.imageCharset = imageCharset;
    }

    // ------------------------------------------------------------------------------------------------ imageLastUpdated

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IMAGE_LAST_UPDATED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IMAGE_LAST_UPDATED} attribute.
     */
    @Nullable
    public LocalDateTime getImageLastUpdated() {
        return imageLastUpdated;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IMAGE_LAST_UPDATED} attribute with the specified value.
     *
     * @param imageLastUpdated new value for {@value #ATTRIBUTE_NAME_IMAGE_LAST_UPDATED} attribute.
     */
    public void setImageLastUpdated(@Nullable final LocalDateTime imageLastUpdated) {
        this.imageLastUpdated = imageLastUpdated;
    }

    // ------------------------------------------------------------------------------------------------------ orderItems

    /**
     * Returns the order items which order this product.
     *
     * @return the order items which order this product.
     */
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * Replaces the order items which order this product.
     *
     * @param orderItems new order items which order this product.
     */
    public void setOrderItems(final List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    // ----------------------------------------------------------------------------------------------------- inventories

    /**
     * Returns the inventories which hold this product.
     *
     * @return the inventories which hold this product.
     */
    public List<Inventory> getInventories() {
        return inventories;
    }

    /**
     * Replaces the inventories which hold this product.
     *
     * @param inventories new inventories which hold this product.
     */
    public void setInventories(final List<Inventory> inventories) {
        this.inventories = inventories;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
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
    private LocalDateTime imageLastUpdated;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = OrderItem.ATTRIBUTE_NAME_PRODUCT,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull OrderItem> orderItems;

    @OneToMany(mappedBy = Inventory.ATTRIBUTE_NAME_PRODUCT,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Inventory> inventories;
}
