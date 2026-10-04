package com.github.jinahya.oracle.sample.schemas.persistence.co.mapped;

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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * A class which holds the mappings of the {@value MappedStoreOrder#TABLE_NAME} view.
 * <p>
 * The {@code GROUPING SETS} key {@code (STORE_NAME, ORDER_STATUS)} has no duplicates, but both columns are {@code NULL}
 * in the subtotal and grand-total rows, and an {@code @Id} may not be null. So this is not a
 * {@link jakarta.persistence.MappedSuperclass @MappedSuperclass}: the columns the view projects are written out here,
 * and every one of them is read-only.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class MappedStoreOrder {

    /**
     * The name of the database view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "STORE_ORDERS";

    // ----------------------------------------------------------------------------------------------------------- TOTAL

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_TOTAL} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL = "TOTAL";

    /**
     * The length of the {@value #COLUMN_NAME_TOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TOTAL = 12;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_TOTAL} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_TOTAL = COLUMN_LENGTH_TOTAL;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL = "total";

    // ------------------------------------------------------------------------------------------------------ STORE_NAME

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_STORE_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_STORE_NAME = "STORE_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_STORE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STORE_NAME = 255;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_STORE_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_STORE_NAME = COLUMN_LENGTH_STORE_NAME;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_NAME = "storeName";

    // --------------------------------------------------------------------------------------------------------- ADDRESS

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_ADDRESS} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDRESS = "ADDRESS";

    /**
     * The length of the {@value #COLUMN_NAME_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDRESS = 512;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_ADDRESS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_ADDRESS = COLUMN_LENGTH_ADDRESS;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDRESS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDRESS = "address";

    // -------------------------------------------------------------------------------------------------------- LATITUDE

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_LATITUDE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LATITUDE = "LATITUDE";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LATITUDE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LATITUDE = "latitude";

    // ------------------------------------------------------------------------------------------------------- LONGITUDE

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_LONGITUDE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LONGITUDE = "LONGITUDE";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LONGITUDE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LONGITUDE = "longitude";

    // ---------------------------------------------------------------------------------------------------- ORDER_STATUS

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ORDER_STATUS = "ORDER_STATUS";

    /**
     * The length of the {@value #COLUMN_NAME_ORDER_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ORDER_STATUS = 10;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_ORDER_STATUS = COLUMN_LENGTH_ORDER_STATUS;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_STATUS = "orderStatus";

    // ----------------------------------------------------------------------------------------------------- ORDER_COUNT

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ORDER_COUNT = "ORDER_COUNT";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_COUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_COUNT = "orderCount";

    // ----------------------------------------------------------------------------------------------------- TOTAL_SALES

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_SALES = "TOTAL_SALES";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_SALES = "totalSales";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedStoreOrder() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "total=" + total +
               ",storeName=" + storeName +
               ",address=" + address +
               ",latitude=" + latitude +
               ",longitude=" + longitude +
               ",orderStatus=" + orderStatus +
               ",orderCount=" + orderCount +
               ",totalSales=" + totalSales +
               '}';
    }

    // ----------------------------------------------------------------------------------------------------------- total

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL} attribute.
     */
    public String getTotal() {
        return total;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL} attribute with the specified value.
     *
     * @param total new value for {@value #ATTRIBUTE_NAME_TOTAL} attribute.
     */
    public void setTotal(final String total) {
        this.total = total;
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

    // --------------------------------------------------------------------------------------------------------- address

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDRESS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDRESS} attribute.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDRESS} attribute with the specified value.
     *
     * @param address new value for {@value #ATTRIBUTE_NAME_ADDRESS} attribute.
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    // -------------------------------------------------------------------------------------------------------- latitude

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LATITUDE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LATITUDE} attribute.
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LATITUDE} attribute with the specified value.
     *
     * @param latitude new value for {@value #ATTRIBUTE_NAME_LATITUDE} attribute.
     */
    public void setLatitude(final BigDecimal latitude) {
        this.latitude = latitude;
    }

    // ------------------------------------------------------------------------------------------------------- longitude

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LONGITUDE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LONGITUDE} attribute.
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LONGITUDE} attribute with the specified value.
     *
     * @param longitude new value for {@value #ATTRIBUTE_NAME_LONGITUDE} attribute.
     */
    public void setLongitude(final BigDecimal longitude) {
        this.longitude = longitude;
    }

    // ----------------------------------------------------------------------------------------------------- orderStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute with the specified value.
     *
     * @param orderStatus new value for {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     */
    public void setOrderStatus(final String orderStatus) {
        this.orderStatus = orderStatus;
    }

    // ------------------------------------------------------------------------------------------------------ orderCount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     */
    public Long getOrderCount() {
        return orderCount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute with the specified value.
     *
     * @param orderCount new value for {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     */
    public void setOrderCount(final Long orderCount) {
        this.orderCount = orderCount;
    }

    // ------------------------------------------------------------------------------------------------------ totalSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute.
     */
    public BigDecimal getTotalSales() {
        return totalSales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute with the specified value.
     *
     * @param totalSales new value for {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute.
     */
    public void setTotalSales(final BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = SIZE_MAX_TOTAL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_TOTAL)
    private String total;

    @Size(max = SIZE_MAX_STORE_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STORE_NAME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_STORE_NAME)
    private String storeName;

    @Size(max = SIZE_MAX_ADDRESS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ADDRESS,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ADDRESS)
    private String address;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LATITUDE, nullable = true, insertable = false, updatable = false)
    private BigDecimal latitude;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LONGITUDE, nullable = true, insertable = false, updatable = false)
    private BigDecimal longitude;

    @Size(max = SIZE_MAX_ORDER_STATUS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDER_STATUS,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ORDER_STATUS)
    private String orderStatus;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDER_COUNT, nullable = true, insertable = false, updatable = false)
    private Long orderCount;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_SALES, nullable = true, insertable = false, updatable = false)
    private BigDecimal totalSales;
}
