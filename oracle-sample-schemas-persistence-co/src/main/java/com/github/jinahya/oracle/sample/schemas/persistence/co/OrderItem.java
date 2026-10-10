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
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping the {@value OrderItem#TABLE_NAME} table, whose composite primary key is mapped with an
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = OrderItem.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               OrderItem.COLUMN_NAME_ORDER_ID,
                               OrderItem.COLUMN_NAME_PRODUCT_ID,
                       }
               )
       }
)
public class OrderItem implements __DomainEntity<OrderItemId> {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ORDER_ITEMS";

    // -------------------------------------------------------------------------------------------------------- ORDER_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_ORDER} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_ID = "ORDER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_ORDER_ID = "id.orderId";

    /**
     * The name of the {@link jakarta.persistence.ManyToOne @ManyToOne} association which maps the
     * {@value #COLUMN_NAME_ORDER_ID} column. The association is {@link jakarta.persistence.MapsId @MapsId} to the
     * {@value #ATTRIBUTE_NAME_ID_ORDER_ID} attribute, so it is the association which writes the column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER = "order";

    // ---------------------------------------------------------------------------------------------------- LINE_ITEM_ID

    /**
     * The name of the table column to which the {@value OrderItemId#ATTRIBUTE_NAME_LINE_ITEM_ID} attribute of
     * {@link OrderItemId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_LINE_ITEM_ID = "LINE_ITEM_ID";

    // ------------------------------------------------------------------------------------ ORDER_ID / LINE_ITEM_ID / id

    /**
     * The name of the attribute which maps both the {@value #COLUMN_NAME_ORDER_ID} and the
     * {@value #COLUMN_NAME_LINE_ITEM_ID} columns, as an {@link jakarta.persistence.EmbeddedId @EmbeddedId}. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

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

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected OrderItem() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + id +
//               ", order=" + order +
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
     * @implSpec Equality is by the {@code @Id} alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof OrderItem that)) {
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
    public OrderItemId getId() {
        return id;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ID} attribute with the specified value.
     *
     * @param id new value for {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    protected void setId(final OrderItemId id) {
        this.id = id;
    }

    // --------------------------------------------------------------------------------------------------------- orderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID_ORDER_ID} attribute; {@code null} when
     * {@value #ATTRIBUTE_NAME_ID} attribute is {@code null}.
     */
    @Transient
    public Long getOrderId() {
        return Optional.ofNullable(getId()).map(OrderItemId::getOrderId).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ID_ORDER_ID} attribute with the specified value, creating the
     * {@value #ATTRIBUTE_NAME_ID} attribute first when it is {@code null}.
     *
     * @param orderId new value for {@value #ATTRIBUTE_NAME_ID_ORDER_ID} attribute.
     */
    protected void setOrderId(final Long orderId) {
        Optional.ofNullable(getId())
                .orElseGet(() -> {
                    setId(new OrderItemId());
                    return getId();
                })
                .setOrderId(orderId);
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
     * {@value #ATTRIBUTE_NAME_ID_ORDER_ID} attribute with the specified value's identifier.
     *
     * @param order new value for {@value #ATTRIBUTE_NAME_ORDER} attribute.
     */
    protected void setOrder(@Nonnull final Order order) {
        this.order = order;
        setOrderId(
                Optional.ofNullable(this.order).map(Order::getOrderId).orElse(null)
        );
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

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @EmbeddedId
    private OrderItemId id;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    // the association owns ORDER_ID, and the provider copies the order's id into the embedded id; @MapsId is what
    // ties the two together, so the column is written from here rather than from a mirroring attribute
    @MapsId(OrderItemId.ATTRIBUTE_NAME_ORDER_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ORDER_ID, referencedColumnName = Order.COLUMN_NAME_ORDER_ID, nullable = false)
    private Order order;

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
    // TODO: remove; restates NUMBER(p,s) -- @Digits states the precision
//    @DecimalMax(DECIMAL_MAX_UNIT_PRICE)
    // TODO: remove; not constrained by the DDL -- ORDER_ITEMS.UNIT_PRICE is NUMBER(10,2) with no check; the lower bound of 0 is narrower than the column
//    @DecimalMin(DECIMAL_MIN_UNIT_PRICE)
    @Digits(integer = COLUMN_PRECISION_UNIT_PRICE - COLUMN_SCALE_UNIT_PRICE, fraction = COLUMN_SCALE_UNIT_PRICE)
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
