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
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping the {@value Store#TABLE_NAME} table.
 * <p>
 * The {@value #ATTRIBUTE_NAME_STORE_NAME} attribute is unique, and is the natural key on which {@link #equals(Object)}
 * and {@link #hashCode()} are based; the {@code Store.selectSingleByStoreName} named query selects the single store of
 * a given {@code storeName}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(name = "Store.selectSingleByStoreName",
            query = """
                    SELECT e
                    FROM Store AS e
                    WHERE e.storeName = :storeName""")
@Entity
@Table(name = Store.TABLE_NAME)
public class Store implements __DomainEntity<Long> {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "STORES";

    // ---------------------------------------------------------------------------------------------- STORE_ID / storeId

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_STORE_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_ID = "storeId";

    // ------------------------------------------------------------------------------------------------------ STORE_NAME

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_STORE_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_STORE_NAME = "STORE_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_STORE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STORE_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_NAME = "storeName";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_STORE_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_STORE_NAME = 0;

    // 1?

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_STORE_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_STORE_NAME = COLUMN_LENGTH_STORE_NAME;

    // ----------------------------------------------------------------------------------------------------- WEB_ADDRESS

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_WEB_ADDRESS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_WEB_ADDRESS = "WEB_ADDRESS";

    /**
     * The length of the {@value #COLUMN_NAME_WEB_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WEB_ADDRESS = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEB_ADDRESS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEB_ADDRESS = "webAddress";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_WEB_ADDRESS} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_WEB_ADDRESS = 0;

    // 1?

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_WEB_ADDRESS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_WEB_ADDRESS = COLUMN_LENGTH_WEB_ADDRESS;

    // ------------------------------------------------------------------------------------------------ PHYSICAL_ADDRESS

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PHYSICAL_ADDRESS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PHYSICAL_ADDRESS = "PHYSICAL_ADDRESS";

    /**
     * The length of the {@value #COLUMN_NAME_PHYSICAL_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PHYSICAL_ADDRESS = 512;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PHYSICAL_ADDRESS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHYSICAL_ADDRESS = "physicalAddress";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_PHYSICAL_ADDRESS} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_PHYSICAL_ADDRESS = 0;

    // 1?

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PHYSICAL_ADDRESS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PHYSICAL_ADDRESS = COLUMN_LENGTH_PHYSICAL_ADDRESS;

    // -------------------------------------------------------------------------------------------------------- LATITUDE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_LATITUDE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LATITUDE = "LATITUDE";

    /**
     * The precision of the {@value #COLUMN_NAME_LATITUDE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_LATITUDE = 9;

    /**
     * The scale of the {@value #COLUMN_NAME_LATITUDE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_LATITUDE = 6;

    /**
     * The minimum value of the {@value #COLUMN_NAME_LATITUDE} column. The value is {@value}.
     */
    public static final String COLUMN_MIN_LATITUDE = "-999.999999";

    /**
     * The maximum value of the {@value #COLUMN_NAME_LATITUDE} column. The value is {@value}.
     */
    public static final String COLUMN_MAX_LATITUDE = "+999.999999";

    /**
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_LATITUDE} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_LATITUDE = COLUMN_PRECISION_LATITUDE - COLUMN_SCALE_LATITUDE;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_LATITUDE} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_LATITUDE = COLUMN_SCALE_LATITUDE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LATITUDE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LATITUDE = "latitude";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LATITUDE} attribute, as a decimal string.
     * <p>
     * The attribute takes geographic degrees, {@code -90} to {@code +90}, which is narrower than what the
     * {@value #COLUMN_NAME_LATITUDE} column can hold, {@value #COLUMN_MIN_LATITUDE} to {@value #COLUMN_MAX_LATITUDE}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_LATITUDE = __DomainConstants.DECIMAL_MIN_LATITUDE;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LATITUDE} attribute, as a decimal string.
     *
     * @see #ATTRIBUTE_DECIMAL_MIN_LATITUDE
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_LATITUDE = __DomainConstants.DECIMAL_MAX_LATITUDE;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LATITUDE} attribute.
     */
    public static final BigDecimal ATTRIBUTE_MIN_LATITUDE = new BigDecimal(ATTRIBUTE_DECIMAL_MIN_LATITUDE);

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LATITUDE} attribute.
     */
    public static final BigDecimal ATTRIBUTE_MAX_LATITUDE = new BigDecimal(ATTRIBUTE_DECIMAL_MAX_LATITUDE);

    // ------------------------------------------------------------------------------------------------------- LONGITUDE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_LONGITUDE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LONGITUDE = "LONGITUDE";

    /**
     * The precision of the {@value #COLUMN_NAME_LONGITUDE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_LONGITUDE = 9;

    /**
     * The scale of the {@value #COLUMN_NAME_LONGITUDE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_LONGITUDE = 6;

    /**
     * The minimum value of the {@value #COLUMN_NAME_LONGITUDE} column. The value is {@value}.
     */
    public static final String COLUMN_MIN_LONGITUDE = "-999.999999";

    /**
     * The maximum value of the {@value #COLUMN_NAME_LONGITUDE} column. The value is {@value}.
     */
    public static final String COLUMN_MAX_LONGITUDE = "+999.999999";

    /**
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_LONGITUDE} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_LONGITUDE = COLUMN_PRECISION_LONGITUDE - COLUMN_SCALE_LONGITUDE;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_LONGITUDE} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_LONGITUDE = COLUMN_SCALE_LONGITUDE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LONGITUDE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LONGITUDE = "longitude";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LONGITUDE} attribute, as a decimal string.
     * <p>
     * The attribute takes geographic degrees, {@code -180} to {@code +180}, which is narrower than what the
     * {@value #COLUMN_NAME_LONGITUDE} column can hold, {@value #COLUMN_MIN_LONGITUDE} to
     * {@value #COLUMN_MAX_LONGITUDE}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_LONGITUDE = __DomainConstants.DECIMAL_MIN_LONGITUDE;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LONGITUDE} attribute, as a decimal string.
     *
     * @see #ATTRIBUTE_DECIMAL_MIN_LONGITUDE
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_LONGITUDE = __DomainConstants.DECIMAL_MAX_LONGITUDE;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LONGITUDE} attribute.
     */
    public static final BigDecimal ATTRIBUTE_MIN_LONGITUDE = new BigDecimal(ATTRIBUTE_DECIMAL_MIN_LONGITUDE);

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LONGITUDE} attribute.
     */
    public static final BigDecimal ATTRIBUTE_MAX_LONGITUDE = new BigDecimal(ATTRIBUTE_DECIMAL_MAX_LONGITUDE);

    // ------------------------------------------------------------------------------------------------------------ LOGO

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_LOGO} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOGO = "LOGO";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOGO} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOGO = "logo";

    // -------------------------------------------------------------------------------------------------- LOGO_MIME_TYPE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_LOGO_MIME_TYPE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LOGO_MIME_TYPE = "LOGO_MIME_TYPE";

    /**
     * The length of the {@value #COLUMN_NAME_LOGO_MIME_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_LOGO_MIME_TYPE = 512;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOGO_MIME_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOGO_MIME_TYPE = "logoMimeType";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_LOGO_MIME_TYPE} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_LOGO_MIME_TYPE = 0;

    // 1?

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_LOGO_MIME_TYPE} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_LOGO_MIME_TYPE = COLUMN_LENGTH_LOGO_MIME_TYPE;

    // --------------------------------------------------------------------------------------------------- LOGO_FILENAME

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_LOGO_FILENAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LOGO_FILENAME = "LOGO_FILENAME";

    /**
     * The length of the {@value #COLUMN_NAME_LOGO_FILENAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_LOGO_FILENAME = 512;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOGO_FILENAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOGO_FILENAME = "logoFilename";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_LOGO_FILENAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_LOGO_FILENAME = 0;

    // 1?

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_LOGO_FILENAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_LOGO_FILENAME = COLUMN_LENGTH_LOGO_FILENAME;

    // ---------------------------------------------------------------------------------------------------- LOGO_CHARSET

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_LOGO_CHARSET} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LOGO_CHARSET = "LOGO_CHARSET";

    /**
     * The length of the {@value #COLUMN_NAME_LOGO_CHARSET} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_LOGO_CHARSET = 512;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOGO_CHARSET} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOGO_CHARSET = "logoCharset";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_LOGO_CHARSET} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_LOGO_CHARSET = 0;

    // 1?

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_LOGO_CHARSET} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_LOGO_CHARSET = COLUMN_LENGTH_LOGO_CHARSET;

    // ----------------------------------------------------------------------------------------------- LOGO_LAST_UPDATED

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_LOGO_LAST_UPDATED} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LOGO_LAST_UPDATED = "LOGO_LAST_UPDATED";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOGO_LAST_UPDATED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOGO_LAST_UPDATED = "logoLastUpdated";

    // ---------------------------------------------------------------------------------------------------------- ORDERS

    /**
     * The name of the attribute which maps the orders placed at this store. The value is {@value}.
     *
     * @see Order#ATTRIBUTE_NAME_STORE
     */
    public static final String ATTRIBUTE_NAME_ORDERS = "orders";

    // ------------------------------------------------------------------------------------------------------- SHIPMENTS

    /**
     * The name of the attribute which maps the shipments dispatched from this store. The value is {@value}.
     *
     * @see Shipment#ATTRIBUTE_NAME_STORE
     */
    public static final String ATTRIBUTE_NAME_SHIPMENTS = "shipments";

    // ----------------------------------------------------------------------------------------------------- INVENTORIES

    /**
     * The name of the attribute which maps the inventories held by this store. The value is {@value}.
     *
     * @see Inventory#ATTRIBUTE_NAME_STORE
     */
    public static final String ATTRIBUTE_NAME_INVENTORIES = "inventories";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Store() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "storeId=" + storeId +
               ",storeName=" + storeName +
               ",webAddress=" + webAddress +
               ",physicalAddress=" + physicalAddress +
               ",latitude=" + latitude +
               ",longitude=" + longitude +
               ",logoMimeType=" + logoMimeType +
               ",logoFilename=" + logoFilename +
               ",logoCharset=" + logoCharset +
               ",logoLastUpdated=" + logoLastUpdated +
//               ",orders=" + orders +
//               ",shipments=" + shipments +
//               ",inventories=" + inventories +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by {@value #ATTRIBUTE_NAME_STORE_NAME}, which the table declares unique.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Store that)) {
            return false;
        }
        return Objects.equals(getStoreName(), that.getStoreName());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over {@value #ATTRIBUTE_NAME_STORE_NAME}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getStoreName());
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    /**
     * Indicates whether either the {@value #ATTRIBUTE_NAME_WEB_ADDRESS} attribute or the
     * {@value #ATTRIBUTE_NAME_PHYSICAL_ADDRESS} attribute is non-{@code null}, which mirrors the table's
     * {@code STORE_AT_LEAST_ONE_ADDRESS_C} constraint.
     * {@snippet lang = "sql":
     * CHECK (web_address IS NOT NULL OR physical_address IS NOT NULL)
     *}
     *
     * @return {@code true} if either address is non-{@code null}; {@code false} otherwise.
     */
    @AssertTrue(message = "either webAddress or physicalAddress must be non-null")
    private boolean isEitherWebAddressOrPhysicalAddressNonnull() {
        return webAddress != null || physicalAddress != null;
    }

    // --------------------------------------------------------------------------------------------------------- storeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute with the specified value.
     *
     * @param storeId new value for {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     */
    protected void setStoreId(final Long storeId) {
        this.storeId = storeId;
    }

    // ------------------------------------------------------------------------------------------------------- storeName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE_NAME} attribute.
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE_NAME} attribute with the specified value.
     *
     * @param storeName new value for {@value #ATTRIBUTE_NAME_STORE_NAME} attribute.
     */
    public void setStoreName(final String storeName) {
        this.storeName = storeName;
    }

    // ------------------------------------------------------------------------------------------------------ webAddress

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEB_ADDRESS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WEB_ADDRESS} attribute.
     */
    @Nullable
    public String getWebAddress() {
        return webAddress;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEB_ADDRESS} attribute with the specified value.
     *
     * @param webAddress new value for {@value #ATTRIBUTE_NAME_WEB_ADDRESS} attribute.
     */
    public void setWebAddress(@Nullable final String webAddress) {
        this.webAddress = webAddress;
    }

    // ------------------------------------------------------------------------------------------------- physicalAddress

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PHYSICAL_ADDRESS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PHYSICAL_ADDRESS} attribute.
     */
    @Nullable
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PHYSICAL_ADDRESS} attribute with the specified value.
     *
     * @param physicalAddress new value for {@value #ATTRIBUTE_NAME_PHYSICAL_ADDRESS} attribute.
     */
    public void setPhysicalAddress(@Nullable final String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    // -------------------------------------------------------------------------------------------------------- latitude

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LATITUDE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LATITUDE} attribute.
     */
    @Nullable
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LATITUDE} attribute with the specified value.
     *
     * @param latitude new value for {@value #ATTRIBUTE_NAME_LATITUDE} attribute.
     */
    public void setLatitude(@Nullable final BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns current value of {@link #getLatitude() latitude} attribute as a {@code double} value.
     *
     * @return current value of {@link #getLatitude() latitude} attribute as a {@code double} value; {@code null} when
     * the attribute is {@code null}.
     * @see #getLatitude()
     * @see BigDecimal#doubleValue()
     */
    @Nullable
    @Transient
    public Double getLatitudeAsDouble() {
        return Optional.ofNullable(getLatitude())
                .map(BigDecimal::doubleValue)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LATITUDE} attribute with the specified {@code double} value,
     * optionally rescaled to the scale of the {@value #COLUMN_NAME_LATITUDE} column.
     *
     * @param latitude     new value for {@link #setLatitude(BigDecimal) latitude} attribute; may be {@code null}.
     * @param roundingMode the rounding mode with which the value is rescaled to {@value #COLUMN_SCALE_LATITUDE}
     *                     fraction digits; {@code null} to keep the value unscaled.
     * @see BigDecimal#valueOf(double)
     * @see BigDecimal#setScale(int, RoundingMode)
     * @see #setLatitude(BigDecimal)
     */
    public void setLatitudeFromDouble(@Nullable final Double latitude,
                                      @Nullable final RoundingMode roundingMode) {
        setLatitude(
                Optional.ofNullable(latitude)
                        .map(BigDecimal::valueOf)
                        .map(v -> roundingMode == null ? v : v.setScale(COLUMN_SCALE_LATITUDE, roundingMode))
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------- longitude

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LONGITUDE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LONGITUDE} attribute.
     */
    @Nullable
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LONGITUDE} attribute with the specified value.
     *
     * @param longitude new value for {@value #ATTRIBUTE_NAME_LONGITUDE} attribute.
     */
    public void setLongitude(@Nullable final BigDecimal longitude) {
        this.longitude = longitude;
    }

    // ------------------------------------------------------------------------------------------------------------ logo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOGO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOGO} attribute.
     */
    @Nullable
    public byte[] getLogo() {
        return logo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOGO} attribute with the specified value.
     *
     * @param logo new value for {@value #ATTRIBUTE_NAME_LOGO} attribute.
     */
    public void setLogo(@Nullable final byte[] logo) {
        this.logo = logo;
    }

    // ---------------------------------------------------------------------------------------------------- logoMimeType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOGO_MIME_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOGO_MIME_TYPE} attribute.
     */
    @Nullable
    public String getLogoMimeType() {
        return logoMimeType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOGO_MIME_TYPE} attribute with the specified value.
     *
     * @param logoMimeType new value for {@value #ATTRIBUTE_NAME_LOGO_MIME_TYPE} attribute.
     */
    public void setLogoMimeType(@Nullable final String logoMimeType) {
        this.logoMimeType = logoMimeType;
    }

    // ---------------------------------------------------------------------------------------------------- logoFilename

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOGO_FILENAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOGO_FILENAME} attribute.
     */
    @Nullable
    public String getLogoFilename() {
        return logoFilename;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOGO_FILENAME} attribute with the specified value.
     *
     * @param logoFilename new value for {@value #ATTRIBUTE_NAME_LOGO_FILENAME} attribute.
     */
    public void setLogoFilename(@Nullable final String logoFilename) {
        this.logoFilename = logoFilename;
    }

    // ----------------------------------------------------------------------------------------------------- logoCharset

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOGO_CHARSET} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOGO_CHARSET} attribute.
     */
    @Nullable
    public String getLogoCharset() {
        return logoCharset;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOGO_CHARSET} attribute with the specified value.
     *
     * @param logoCharset new value for {@value #ATTRIBUTE_NAME_LOGO_CHARSET} attribute.
     */
    public void setLogoCharset(@Nullable final String logoCharset) {
        this.logoCharset = logoCharset;
    }

    // ------------------------------------------------------------------------------------------------- logoLastUpdated

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOGO_LAST_UPDATED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOGO_LAST_UPDATED} attribute.
     */
    @Nullable
    public LocalDateTime getLogoLastUpdated() {
        return logoLastUpdated;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOGO_LAST_UPDATED} attribute with the specified value.
     *
     * @param logoLastUpdated new value for {@value #ATTRIBUTE_NAME_LOGO_LAST_UPDATED} attribute.
     */
    public void setLogoLastUpdated(@Nullable final LocalDateTime logoLastUpdated) {
        this.logoLastUpdated = logoLastUpdated;
    }

    // ---------------------------------------------------------------------------------------------------------- orders

    /**
     * Returns the orders placed at this store.
     *
     * @return the orders placed at this store.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Replaces the orders placed at this store.
     *
     * @param orders new orders placed at this store.
     */
    public void setOrders(final List<Order> orders) {
        this.orders = orders;
    }

    // ------------------------------------------------------------------------------------------------------- shipments

    /**
     * Returns the shipments dispatched from this store.
     *
     * @return the shipments dispatched from this store.
     */
    public List<Shipment> getShipments() {
        return shipments;
    }

    /**
     * Replaces the shipments dispatched from this store.
     *
     * @param shipments new shipments dispatched from this store.
     */
    public void setShipments(final List<Shipment> shipments) {
        this.shipments = shipments;
    }

    // ----------------------------------------------------------------------------------------------------- inventories

    /**
     * Returns the inventories held by this store.
     *
     * @return the inventories held by this store.
     */
    public List<Inventory> getInventories() {
        return inventories;
    }

    /**
     * Replaces the inventories held by this store.
     *
     * @param inventories new inventories held by this store.
     */
    public void setInventories(final List<Inventory> inventories) {
        this.inventories = inventories;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
//    // ------------------------------------------------------------------------------------------------------------ logo
//    @jakarta.annotation.Nullable
//    public Store_Logo getLogo() {
//        return logo;
//    }
//
//    public void setLogo(@jakarta.annotation.Nullable final Store_Logo logo) {
//        this.logo = logo;
//    }
    // no @NotNull: database-generated identity; the value is null when the provider validates at pre-persist
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_STORE_ID, nullable = false,
            // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
            // insertable=false on a @GeneratedValue @Id -- it treats the mapping as read-only and
            // fails descriptor initialisation with EclipseLink-46/EclipseLink-41. Verified on 5.0.1.
            insertable = true,
            updatable = false)
    private Long storeId;

    @Size(min = SIZE_MIN_STORE_NAME, max = SIZE_MAX_STORE_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STORE_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_STORE_NAME,
            unique = true
    )
    private String storeName;

    @Nullable
    @Size(max = SIZE_MAX_WEB_ADDRESS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WEB_ADDRESS,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_WEB_ADDRESS
    )
    private String webAddress;

    @Nullable
    @Size(max = SIZE_MAX_PHYSICAL_ADDRESS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PHYSICAL_ADDRESS,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PHYSICAL_ADDRESS
    )
    private String physicalAddress;

    @Nullable
    // TODO: remove; not constrained by the DDL -- STORES.LATITUDE is NUMBER(9,6) with no check; -90..+90 is a domain range
//    @DecimalMax(value = __DomainConstants.DECIMAL_MAX_LATITUDE, inclusive = true)
    // TODO: remove; not constrained by the DDL -- STORES.LATITUDE is NUMBER(9,6) with no check; -90..+90 is a domain range
//    @DecimalMin(value = __DomainConstants.DECIMAL_MIN_LATITUDE, inclusive = true)
    @DecimalMax(value = COLUMN_MAX_LATITUDE, inclusive = true)
    @DecimalMin(value = COLUMN_MIN_LATITUDE, inclusive = true)
    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_LATITUDE, fraction = ATTRIBUTE_DIGITS_FRACTION_LATITUDE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LATITUDE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_LATITUDE,
            scale = COLUMN_SCALE_LATITUDE
    )
    private BigDecimal latitude;

    @Nullable
    // TODO: remove; not constrained by the DDL -- STORES.LONGITUDE is NUMBER(9,6) with no check; -180..+180 is a domain range
//    @DecimalMax(value = __DomainConstants.DECIMAL_MAX_LONGITUDE, inclusive = true)
    // TODO: remove; not constrained by the DDL -- STORES.LONGITUDE is NUMBER(9,6) with no check; -180..+180 is a domain range
//    @DecimalMin(value = __DomainConstants.DECIMAL_MIN_LONGITUDE, inclusive = true)
    @DecimalMax(value = COLUMN_MAX_LONGITUDE, inclusive = true)
    @DecimalMin(value = COLUMN_MIN_LONGITUDE, inclusive = true)
    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_LONGITUDE, fraction = ATTRIBUTE_DIGITS_FRACTION_LONGITUDE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LONGITUDE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_LONGITUDE,
            scale = COLUMN_SCALE_LONGITUDE
    )
    private BigDecimal longitude;

    @Nullable
    @Lob
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_LOGO, nullable = true, insertable = true, updatable = true)
    private byte[] logo;

    @Nullable
    @Size(min = SIZE_MIN_LOGO_MIME_TYPE, max = SIZE_MAX_LOGO_MIME_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOGO_MIME_TYPE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_LOGO_MIME_TYPE
    )
    private String logoMimeType;

    @Nullable
    @Size(min = SIZE_MIN_LOGO_FILENAME, max = SIZE_MAX_LOGO_FILENAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOGO_FILENAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_LOGO_FILENAME
    )
    private String logoFilename;

    @Nullable
    @Size(min = SIZE_MIN_LOGO_CHARSET, max = SIZE_MAX_LOGO_CHARSET)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOGO_CHARSET,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_LOGO_CHARSET
    )
    private String logoCharset;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOGO_LAST_UPDATED, nullable = true, insertable = true, updatable = true)
    private LocalDateTime logoLastUpdated;

    @OneToMany(mappedBy = Order.ATTRIBUTE_NAME_STORE,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Order> orders;

    @OneToMany(mappedBy = Shipment.ATTRIBUTE_NAME_STORE,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Shipment> shipments;

    @OneToMany(mappedBy = Inventory.ATTRIBUTE_NAME_STORE,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Inventory> inventories;
}
