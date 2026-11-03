package com.github.jinahya.oracle.sample.persistence.co.mapped;

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
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import java.util.Optional;

/**
 * A mapped superclass for mapping the {@value MappedOrderItem#TABLE_NAME} table.
 * <p>
 * The composite primary key of the table is an {@link jakarta.persistence.EmbeddedId @EmbeddedId} of a subclass of
 * {@link MappedOrderItemId}, {@code T}. The extending entity declares the attribute, and returns it from
 * {@link #getId()}, which {@link #equals(Object)}, {@link #hashCode()} and {@link #toString()} read: an
 * {@code @EmbeddedId} of a type variable, declared here, is what EclipseLink does not resolve. The
 * {@value #COLUMN_NAME_ORDER_ID} column lives in the identifier; an extending entity which maps the order writes it
 * through {@link jakarta.persistence.MapsId @MapsId}.
 * <p>
 * The {@value #COLUMN_NAME_PRODUCT_ID} and {@value #COLUMN_NAME_SHIPMENT_ID} columns are mapped read-only
 * ({@code insertable = false, updatable = false}), with a public getter and a {@code protected} setter, for
 * {@link #toString()} and for queries. How the relationship behind each is mapped -- fetch type, cascade, whether there
 * is an association at all -- is the extending entity's decision, so the extending entity also owns their writable
 * mapping, by an association's {@link jakarta.persistence.JoinColumn @JoinColumn}. <strong>An extending entity which
 * maps none never writes them.</strong> Being read-only, they are populated by a load or a refresh, or by the extending
 * entity, which sets them through their {@code protected} setters in step with the associations which write the
 * columns.
 *
 * @param <T> the type of the identifier.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedOrderItem<T extends MappedOrderItemId> {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ORDER_ITEMS";

    // -------------------------------------------------------------------------------------------------------- ORDER_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_PATH_ID_ORDER_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ORDER_ID = "ORDER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_ORDER_ID = "id.orderId";

    // ---------------------------------------------------------------------------------------------------- LINE_ITEM_ID

    /**
     * The name of the table column to which the {@value MappedOrderItemId#ATTRIBUTE_NAME_LINE_ITEM_ID} attribute of
     * {@link MappedOrderItemId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_LINE_ITEM_ID = "LINE_ITEM_ID";

    // ------------------------------------------------------------------------------------ ORDER_ID / LINE_ITEM_ID / id

    /**
     * The name of the attribute which maps both the {@value #COLUMN_NAME_ORDER_ID} and the
     * {@value #COLUMN_NAME_LINE_ITEM_ID} columns, as an {@link jakarta.persistence.EmbeddedId @EmbeddedId}. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    // ------------------------------------------------------------------------------------------------------ PRODUCT_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column, read-only. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // ------------------------------------------------------------------------------------------------------ UNIT_PRICE

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute. The value is {@value}.
     */
    public static final String DECIMAL_MIN_UNIT_PRICE = "-99999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute. The value is {@value}.
     */
    public static final String DECIMAL_MAX_UNIT_PRICE = "+99999999.99";

    // -------------------------------------------------------------------------------------------------------- QUANTITY

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_QUANTITY} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "QUANTITY";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

    // ----------------------------------------------------------------------------------------------------- SHIPMENT_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_SHIPMENT_ID = "SHIPMENT_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIPMENT_ID} column, read-only. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIPMENT_ID = "shipmentId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrderItem() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + getId() +
               ", productId=" + productId +
               ", unitPrice=" + unitPrice +
               ", quantity=" + quantity +
               ", shipmentId=" + shipmentId +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the {@code @Id} alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedOrderItem<?> that)) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    public abstract T getId();

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ID} attribute with the specified value.
     *
     * @param id new value for {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    protected abstract void setId(T id);

    // --------------------------------------------------------------------------------------------------------- orderId

    /**
     * Returns current value of {@value #ATTRIBUTE_PATH_ID_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_PATH_ID_ORDER_ID} attribute; {@code null} when
     * {@value #ATTRIBUTE_NAME_ID} attribute is {@code null}.
     */
    @Transient
    public Long getOrderId() {
        return Optional.ofNullable(getId()).map(MappedOrderItemId::getOrderId).orElse(null);
    }

    // ------------------------------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    @Nonnull
    public Long getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified value.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(@Nonnull final Long productId) {
        this.productId = productId;
    }

    // ------------------------------------------------------------------------------------------------------- unitPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute with the specified value.
     *
     * @param unitPrice new value for {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // -------------------------------------------------------------------------------------------------------- quantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute with the specified value.
     *
     * @param quantity new value for {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public void setQuantity(final Long quantity) {
        this.quantity = quantity;
    }

    // ------------------------------------------------------------------------------------------------------ shipmentId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute.
     */
    @Nullable
    public Long getShipmentId() {
        return shipmentId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute with the specified value.
     *
     * @param shipmentId new value for {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute.
     */
    protected void setShipmentId(@Nullable final Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = false, updatable = false)
    private Long productId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @DecimalMax(DECIMAL_MAX_UNIT_PRICE)
    @DecimalMin(DECIMAL_MIN_UNIT_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE,
            nullable = false,
            insertable = true,
            updatable = false, // @@?
            precision = COLUMN_PRECISION_UNIT_PRICE,
            scale = COLUMN_SCALE_UNIT_PRICE
    )
    private BigDecimal unitPrice;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY, nullable = false, insertable = true, updatable = true)
    private Long quantity;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SHIPMENT_ID, nullable = true, insertable = false, updatable = false)
    private Long shipmentId;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the total price of this order item which is {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute multiplied by
     * {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     *
     * @param mc a math context to use; {@code null} for an unlimited precision.
     * @return the total price of this item.
     * @throws IllegalStateException if either {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute or
     *                               {@value #ATTRIBUTE_NAME_QUANTITY} attribute is {@code null}.
     */
    @Transient
    public BigDecimal getTotalPrice(@Nullable final MathContext mc) {
        if (unitPrice == null) {
            throw new IllegalStateException("unitPrice is null");
        }
        if (quantity == null) {
            throw new IllegalStateException("quantity is null");
        }
        if (mc == null) {
            return unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
        return unitPrice.multiply(BigDecimal.valueOf(quantity), mc);
    }
}
