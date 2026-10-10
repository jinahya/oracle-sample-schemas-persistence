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
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * A superclass for the identifier of the {@value MappedProductOrder#TABLE_NAME} view -- the pair of the
 * {@value MappedProductOrder#COLUMN_NAME_PRODUCT_NAME} and the {@value MappedProductOrder#COLUMN_NAME_ORDER_STATUS}
 * columns.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedProductOrder
 */
@MappedSuperclass
public abstract class MappedProductOrderId {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the attribute which maps the {@value MappedProductOrder#COLUMN_NAME_PRODUCT_NAME} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_NAME = "productName";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the attribute which maps the {@value MappedProductOrder#COLUMN_NAME_ORDER_STATUS} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_STATUS = "orderStatus";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance of a subclass, with the specified values.
     *
     * @param <T>          the type of the identifier.
     * @param instantiator a supplier of a new, empty instance of {@code T}.
     * @param productName  a value for the {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute.
     * @param orderStatus  a value for the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     * @return the instance supplied by {@code instantiator}, with its attributes set.
     * @throws NullPointerException if {@code instantiator} is {@code null}, or it supplies {@code null}.
     */
    protected static <T extends MappedProductOrderId> T of(final Supplier<? extends T> instantiator,
                                                           final String productName, final String orderStatus) {
        Objects.requireNonNull(instantiator, "instantiator is null");
        final var instance = Objects.requireNonNull(instantiator.get(), "instantiator.get() is null");
        instance.setProductName(productName);
        instance.setOrderStatus(orderStatus);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductOrderId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final String toString() {
        return super.toString() + '{' +
               "productName=" + productName +
               ",orderStatus=" + orderStatus +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by both {@value #ATTRIBUTE_NAME_PRODUCT_NAME} and {@value #ATTRIBUTE_NAME_ORDER_STATUS}.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedProductOrderId that)) {
            return false;
        }
        return Objects.equals(productName, that.productName)
               && Objects.equals(orderStatus, that.orderStatus);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over both {@value #ATTRIBUTE_NAME_PRODUCT_NAME} and {@value #ATTRIBUTE_NAME_ORDER_STATUS},
     * consistent with {@link #equals(Object)}.
     */
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
    protected void setProductName(final String productName) {
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
    protected void setOrderStatus(final String orderStatus) {
        this.orderStatus = orderStatus;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = MappedProductOrder.SIZE_MAX_PRODUCT_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = MappedProductOrder.COLUMN_NAME_PRODUCT_NAME,
            nullable = false,
            insertable = true,
            updatable = false,
            length = MappedProductOrder.COLUMN_LENGTH_PRODUCT_NAME)
    private String productName;

    @Size(max = MappedProductOrder.SIZE_MAX_ORDER_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = MappedProductOrder.COLUMN_NAME_ORDER_STATUS,
            nullable = false,
            insertable = true,
            updatable = false,
            length = MappedProductOrder.COLUMN_LENGTH_ORDER_STATUS)
    private String orderStatus;
}
