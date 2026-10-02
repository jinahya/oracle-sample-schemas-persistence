package com.github.jinahya.oracle.sample.schemas.persistence.co;

/*-
 * #%L
 * co
 * %%
 * Copyright (C) 2024 - 2026 Jinahya, Inc.
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
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * An id class for the {@link ProductOrderWithEmbeddedId} and {@link ProductOrderWithIdClass} entity classes.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class ProductOrderId {

    /**
     * The name of the database view whose primary key this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = ProductOrderWithEmbeddedId.TABLE_NAME;

    // ---------------------------------------------------------------------------------------------------- PRODUCT_NAME

    /**
     * The name of the attribute which maps the {@value ProductOrderWithEmbeddedId#COLUMN_NAME_PRODUCT_NAME} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_NAME = "productName";

    // ---------------------------------------------------------------------------------------------------- ORDER_STATUS

    /**
     * The name of the attribute which maps the {@value ProductOrderWithEmbeddedId#COLUMN_NAME_ORDER_STATUS} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_STATUS = "orderStatus";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with the specified column values.
     *
     * @param productName the {@value ProductOrderWithEmbeddedId#COLUMN_NAME_PRODUCT_NAME} column value.
     * @param orderStatus the {@value ProductOrderWithEmbeddedId#COLUMN_NAME_ORDER_STATUS} column value.
     * @return a new instance with the specified column values.
     */
    public static ProductOrderId of(final String productName, final String orderStatus) {
        final var instance = new ProductOrderId();
        instance.setProductName(productName);
        instance.setOrderStatus(orderStatus);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public ProductOrderId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "productName=" + productName +
               ",orderStatus=" + orderStatus +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof ProductOrderId that)) {
            return false;
        }
        return Objects.equals(productName, that.productName)
               && Objects.equals(orderStatus, that.orderStatus);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(productName, orderStatus);
    }

    // ----------------------------------------------------------------------------------------------------- productName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute with the specified value.
     *
     * @param productName new value for {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute.
     */
    public void setProductName(final String productName) {
        this.productName = productName;
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

    // ---------------------------------------------------------------------------------------------------------------- 

    @Size(max = ProductOrderWithEmbeddedId.SIZE_MAX_PRODUCT_NAME)
    @Basic(optional = false)
    @Column(name = ProductOrderWithEmbeddedId.COLUMN_NAME_PRODUCT_NAME,
            nullable = false,
            insertable = true,
            updatable = false,
            length = ProductOrderWithEmbeddedId.COLUMN_LENGTH_PRODUCT_NAME)
    private String productName;

    @Size(max = ProductOrderWithEmbeddedId.SIZE_MAX_ORDER_STATUS)
    @Basic(optional = false)
    @Column(name = ProductOrderWithEmbeddedId.COLUMN_NAME_ORDER_STATUS,
            nullable = false,
            insertable = true,
            updatable = false,
            length = ProductOrderWithEmbeddedId.COLUMN_LENGTH_ORDER_STATUS)
    private String orderStatus;
}
