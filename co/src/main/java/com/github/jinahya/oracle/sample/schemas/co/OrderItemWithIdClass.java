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

import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
     * The name of the database table to which this entity is mapped. The value is {@value}.
     */
    public static final String TABLE_NAME = "ORDER_ITEMS";

    // -------------------------------------------------------------------------------------------------------- ORDER_ID

    /**
     * The name of the table column to which the {@code orderId} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_ID = "ORDER_ID";

    /**
     * The name of the attribute which maps the {@code ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    /**
     * The name of the attribute which maps the {@code ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER = "order";

    // ---------------------------------------------------------------------------------------------------- LINE_ITEM_ID

    /**
     * The name of the table column to which the {@code lineItemId} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_LINE_ITEM_ID = "LINE_ITEM_ID";

    /**
     * The name of the attribute which maps the {@code LINE_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_ITEM_ID = "lineItemId";

    // -------------------------------------------------------------------------------- PRODUCT_ID / productId / product

    /**
     * The name of the table column to which the {@code productId} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    /**
     * The name of the attribute which maps the {@code PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    /**
     * The name of the attribute which maps the {@code PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    // ------------------------------------------------------------------------------------------------------ UNIT_PRICE

    /**
     * The name of the table column to which the {@code unitPrice} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE = "UNIT_PRICE";

    /**
     * The precision of the {@code UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNIT_PRICE = 10;

    /**
     * The scale of the {@code UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNIT_PRICE = 2;

    /**
     * The minimum value of the {@code unitPrice} attribute. The value is {@value}.
     */
    public static final String DECIMAL_MIN_UNIT_PRICE = "00000000.00";

    /**
     * The maximum value of the {@code unitPrice} attribute. The value is {@value}.
     */
    public static final String DECIMAL_MAX_UNIT_PRICE = "99999999.99";

    // -------------------------------------------------------------------------------------------------------- QUANTITY

    /**
     * The name of the table column to which the {@code quantity} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "QUANTITY";

    // ----------------------------------------------------------------------------- SHIPMENT_ID / shipmentId / shipment

    /**
     * The name of the table column to which the {@code shipmentId} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIPMENT_ID = "SHIPMENT_ID";

    /**
     * The name of the attribute which maps the {@code SHIPMENT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIPMENT_ID = "shipmentId";

    /**
     * The name of the attribute which maps the {@code SHIPMENT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIPMENT = "shipment";

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
        return super.toString() + '{' +
               "orderId=" + orderId +
               ",lineItemId=" + lineItemId +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof OrderItemWithIdClass that)) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    /**
     * Indicates whether the {@code unitPrice} attribute is non-negative.
     *
     * @return {@code true} if the {@code unitPrice} attribute is non-negative; {@code false} otherwise.
     */
    protected boolean isUniPricesNonNegative() {
        if (unitPrice == null) {
            return true;
        }
        return unitPrice.signum() >= 1;
    }

    /**
     * Indicates whether the {@code quantity} attribute is non-negative.
     *
     * @return {@code true} if the {@code quantity} attribute is non-negative; {@code false} otherwise.
     */
    protected boolean isQuantityNonNegative() {
        if (quantity == null) {
            return true;
        }
        return quantity >= 0;
    }

    //    @AssertTrue(message = "shipment.customer should be equal to the order.customer")
    // 일단 DB 상으로는 다 맞다.
    private boolean isShipmentCustomerEqualToOrderCustomer() {
//        if (shipment == null) {
//            return true;
//        }
//        final var shipmentCustomer = shipment.getCustomer();
//        if (shipmentCustomer == null) {
//            return true;
//        }
//        if (order == null) {
//            return true;
//        }
//        final var orderCustomer = order.getCustomer();
//        if (orderCustomer == null) {
//            return true;
//        }
//        return Objects.equals(shipmentCustomer, orderCustomer);
        return false;
    }

    //    @AssertTrue(message = "shipment.store should be equal to the order.store")
    // 일단 DB 상으로는 다 맞다.
    private boolean isShipmentStoreEqualToOrderStore() {
//        if (shipment == null) {
//            return true;
//        }
//        final var shipmentStore = shipment.getStore();
//        if (shipmentStore == null) {
//            return true;
//        }
//        if (order == null) {
//            return true;
//        }
//        final var orderStore = order.getStore();
//        if (orderStore == null) {
//            return true;
//        }
//        return Objects.equals(shipmentStore, orderStore);
        return true;
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER} attribute with the specified value.
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
     * Returns current value of {@code lineItemId} attribute.
     *
     * @return current value of {@code lineItemId} attribute.
     */
    @Nonnull
    public Long getLineItemId() {
        return lineItemId;
    }

    /**
     * Replaces current value of {@code lineItemId} attribute with the specified value.
     *
     * @param lineItemId new value for {@code lineItemId} attribute.
     */
    protected void setLineItemId(@Nonnull final Long lineItemId) {
        this.lineItemId = lineItemId;
    }

    // ------------------------------------------------------------------------------------------------------- productId
    @Nonnull
    Long getProductId() {
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
        setProductId(
                Optional.ofNullable(this.product)
                        .map(Product::getProductId)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------- unitPrice

    /**
     * Returns current value of {@code unitPrice} attribute.
     *
     * @return current value of {@code unitPrice} attribute.
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Replaces current value of {@code unitPrice} attribute with the specified value.
     *
     * @param unitPrice new value for {@code unitPrice} attribute.
     */
    protected void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // -------------------------------------------------------------------------------------------------------- quantity

    /**
     * Returns current value of {@code quantity} attribute.
     *
     * @return current value of {@code quantity} attribute.
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * Replaces current value of {@code quantity} attribute with the specified value.
     *
     * @param quantity new value for {@code quantity} attribute.
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
    @jakarta.annotation.Nullable
    public Long getShipmentId() {
        return shipmentId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute with the specified value.
     *
     * @param shipmentId new value for {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute.
     */
    protected void setShipmentId(@jakarta.annotation.Nullable final Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    // -------------------------------------------------------------------------------------------------------- shipment

    /**
     * Returns the shipment which carries this order item.
     *
     * @return the shipment which carries this order item; {@code null} when not yet shipped.
     */
    @jakarta.annotation.Nullable
    public Shipment getShipment() {
        return shipment;
    }

    /**
     * Replaces the shipment which carries this order item.
     *
     * @param shipment new shipment which carries this order item.
     */
    public void setShipment(@jakarta.annotation.Nullable final Shipment shipment) {
        this.shipment = shipment;
        setShipmentId(
                Optional.ofNullable(this.shipment)
                        .map(Shipment::getShipmentId)
                        .orElse(null)
        );
    }

    /**
     * Returns the total price of this order item which is {@code unitPrice} attribute multiplied by {@code quantity}
     * attribute.
     *
     * @param mc a math context to use.
     * @return the total price of this item.
     */
    @Transient
    public BigDecimal getTotalPrice(@jakarta.annotation.Nullable final MathContext mc) {
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
    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns current value of {@code id} attribute.
     *
     * @return current value of {@code id} attribute.
     */
    public OrderItemId getId() {
        return new OrderItemId(getOrderId(), getLineItemId());
    }

    /**
     * Replaces current value of {@code id} attribute with the specified value.
     *
     * @param id new value for {@code id} attribute.
     */
    protected void setId(final OrderItemId id) {
        setOrderId(
                Optional.ofNullable(id)
                        .map(OrderItemId::getOrderId)
                        .orElse(null)
        );
        setLineItemId(
                Optional.ofNullable(id)
                        .map(OrderItemId::getLineItemId)
                        .orElse(null)
        );
    }

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
    @JoinColumn(name = COLUMN_NAME_ORDER_ID, referencedColumnName = COLUMN_NAME_ORDER_ID, nullable = false,
                insertable = false, updatable = false)
    private Order order;

    @Nonnull
    @NotNull
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LINE_ITEM_ID, nullable = false, insertable = true, updatable = false)
    private Long lineItemId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = true, updatable = false)
    private Long productId;

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = Product.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Product product;

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

    @jakarta.annotation.Nullable
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_SHIPMENT_ID, nullable = true, insertable = true, updatable = true)
    private Long shipmentId;

    @jakarta.annotation.Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SHIPMENT_ID,
                referencedColumnName = Shipment.COLUMN_NAME_SHIPMENT_ID,
                nullable = true,
                insertable = false,
                updatable = false
    )
    private Shipment shipment;
}
