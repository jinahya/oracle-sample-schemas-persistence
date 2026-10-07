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
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping the {@value OrderItemWithIdClass#TABLE_NAME} table, whose composite primary key is mapped
 * with an {@link jakarta.persistence.IdClass @IdClass}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see OrderItemWithEmbeddedId
 */
@Entity
@IdClass(OrderItemId.class)
@Table(name = OrderItemWithIdClass.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               OrderItemWithIdClass.COLUMN_NAME_ORDER_ID,
                               OrderItemWithIdClass.COLUMN_NAME_PRODUCT_ID,
                       }
               )
       }
)
public class OrderItemWithIdClass {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ORDER_ITEMS";

    // -------------------------------------------------------------------------------------------------------- ORDER_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_ORDER_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ORDER_ID = "ORDER_ID";

    /**
     * The name of the {@link jakarta.persistence.Id @Id} attribute which maps, and writes, the
     * {@value #COLUMN_NAME_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    /**
     * The name of the {@link jakarta.persistence.ManyToOne @ManyToOne} association which joins on the
     * {@value #COLUMN_NAME_ORDER_ID} column. The association is read-only; the column is written through the
     * {@value #ATTRIBUTE_NAME_ORDER_ID} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER = "order";

    // ---------------------------------------------------------------------------------------------------- LINE_ITEM_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_LINE_ITEM_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LINE_ITEM_ID = "LINE_ITEM_ID";

    /**
     * The name of the {@link jakarta.persistence.Id @Id} attribute which maps the {@value #COLUMN_NAME_LINE_ITEM_ID}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_ITEM_ID = "lineItemId";

    // -------------------------------------------------------------------------------------------- PRODUCT_ID / product

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PRODUCT} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    /**
     * The name of the {@link jakarta.persistence.ManyToOne @ManyToOne} association which joins on the
     * {@value #COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

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
    public static final String DECIMAL_MIN_UNIT_PRICE = "00000000.00";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute. The value is {@value}.
     */
    public static final String DECIMAL_MAX_UNIT_PRICE = "99999999.99";

    // -------------------------------------------------------------------------------------------------------- QUANTITY

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_QUANTITY} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "QUANTITY";

    // ------------------------------------------------------------------------------------------ SHIPMENT_ID / shipment

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_SHIPMENT} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_SHIPMENT_ID = "SHIPMENT_ID";

    /**
     * The name of the {@link jakarta.persistence.ManyToOne @ManyToOne} association which joins on the
     * {@value #COLUMN_NAME_SHIPMENT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIPMENT = "shipment";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";
    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected OrderItemWithIdClass() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return "OrderItemWithIdClass{" +
               "orderId=" + orderId +
//               ", order=" + order +
               ", lineItemId=" + lineItemId +
//               ", product=" + product +
               ", unitPrice=" + unitPrice +
               ", quantity=" + quantity +
//               ", shipment=" + shipment +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the two {@code @Id} attributes, {@value #ATTRIBUTE_NAME_ORDER_ID} and
     * {@value #ATTRIBUTE_NAME_LINE_ITEM_ID}, alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof OrderItemWithIdClass that)) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the two {@code @Id} attributes, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a new {@link OrderItemId} holding current values of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute and
     * {@value #ATTRIBUTE_NAME_LINE_ITEM_ID} attribute.
     *
     * @return a new {@link OrderItemId} holding current values of the two {@code @Id} attributes.
     */
    @Transient
    protected OrderItemId getId() {
        return OrderItemId.of(getOrderId(), getLineItemId());
    }

    // --------------------------------------------------------------------------------------------------------- orderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    @Nonnull
    public Long getOrderId() {
        return orderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute with the specified value.
     *
     * @param orderId new value for {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    protected void setOrderId(@Nonnull final Long orderId) {
        this.orderId = orderId;
    }

    // ----------------------------------------------------------------------------------------------------------- order

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER} attribute.
     */
    @Nonnull
    public Order getOrder() {
        return order;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER} attribute with the specified value, and current value of
     * {@value #ATTRIBUTE_NAME_ORDER_ID} attribute with the specified value's identifier.
     *
     * @param order new value for {@value #ATTRIBUTE_NAME_ORDER} attribute.
     */
    protected void setOrder(@Nonnull final Order order) {
        this.order = order;
        setOrderId(
                Optional.ofNullable(this.order)
                        .map(Order::getOrderId)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------ lineItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LINE_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LINE_ITEM_ID} attribute.
     */
    @Nonnull
    public Long getLineItemId() {
        return lineItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LINE_ITEM_ID} attribute with the specified value.
     *
     * @param lineItemId new value for {@value #ATTRIBUTE_NAME_LINE_ITEM_ID} attribute.
     */
    protected void setLineItemId(@Nonnull final Long lineItemId) {
        this.lineItemId = lineItemId;
    }

    // --------------------------------------------------------------------------------------------------------- product

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     */
    @Nonnull
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute with the specified value.
     *
     * @param product new value for {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     */
    protected void setProduct(@Nonnull final Product product) {
        this.product = product;
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
    protected void setUnitPrice(final BigDecimal unitPrice) {
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

    // -------------------------------------------------------------------------------------------------------- shipment

    /**
     * Returns the shipment which carries this order item.
     *
     * @return the shipment which carries this order item; {@code null} when not yet shipped.
     */
    @Nullable
    public Shipment getShipment() {
        return shipment;
    }

    /**
     * Replaces the shipment which carries this order item.
     *
     * @param shipment new shipment which carries this order item; {@code null} for an item not yet shipped.
     */
    public void setShipment(@Nullable final Shipment shipment) {
        this.shipment = shipment;
    }

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

//    @Override
//    public final boolean equals(final Object obj) {
//        if (!(obj instanceof OrderItemWithIdClass that)) {
//            return false;
//        }
//        return Objects.equals(orderId, that.orderId)
//               && Objects.equals(lineItemId, that.lineItemId);
//    }
//
//    @Override
//    public final int hashCode() {
//        return Objects.hash(orderId, lineItemId);
//    }
//    // -------------------------------------------------------------------------------------------------------------- id
//
//    /**
//     * Returns a new {@link OrderItemId} holding current values of the identifying attributes.
//     *
//     * @return a new {@link OrderItemId} holding current values of the identifying attributes.
//     */
//    public OrderItemId getId() {
//        return OrderItemId.of(getOrderId(), getLineItemId());
//    }
//
//    /**
//     * Replaces current values of the identifying attributes with those of the specified identifier.
//     *
//     * @param id the identifier whose values are applied; may be {@code null}, which clears every identifying
//     *           attribute.
//     */
//    protected void setId(final OrderItemId id) {
//        setOrderId(
//                Optional.ofNullable(id)
//                        .map(OrderItemId::getOrderId)
//                        .orElse(null)
//        );
//        setLineItemId(
//                Optional.ofNullable(id)
//                        .map(OrderItemId::getLineItemId)
//                        .orElse(null)
//        );
//    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_ID, nullable = false, insertable = true, updatable = false)
    private Long orderId;

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ORDER_ID,
                referencedColumnName = COLUMN_NAME_ORDER_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Order order;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LINE_ITEM_ID, nullable = false, insertable = true, updatable = false)
    private Long lineItemId;

    // -----------------------------------------------------------------------------------------------------------------

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = Product.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private Product product;

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

    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SHIPMENT_ID,
                referencedColumnName = Shipment.COLUMN_NAME_SHIPMENT_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Shipment shipment;
}
