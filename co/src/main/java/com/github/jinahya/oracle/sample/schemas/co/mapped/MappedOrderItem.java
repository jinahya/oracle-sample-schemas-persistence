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

import com.github.jinahya.oracle.sample.schemas.co.OrderItemId;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

@MappedSuperclass
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedOrderItem
        extends _MappedCoEntity<OrderItemId> {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "ORDER_ITEMS";

    // -------------------------------------------------------------------------------------------------------- ORDER_ID
    public static final String COLUMN_NAME_ORDER_ID = "ORDER_ID";

    public static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    public static final String ATTRIBUTE_NAME_ORDER = "order";

    // ---------------------------------------------------------------------------------------------------- LINE_ITEM_ID
    public static final String COLUMN_NAME_LINE_ITEM_ID = "LINE_ITEM_ID";

    // -------------------------------------------------------------------------------- PRODUCT_ID / productId / product
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    // ------------------------------------------------------------------------------------------------------ UNIT_PRICE
    public static final String COLUMN_NAME_UNIT_PRICE = "UNIT_PRICE";

    public static final int COLUMN_PRECISION_UNIT_PRICE = 10;

    public static final int COLUMN_SCALE_UNIT_PRICE = 2;

    public static final String DECIMAL_MIN_UNIT_PRICE = "00000000.00";

    public static final String DECIMAL_MAX_UNIT_PRICE = "99999999.99";

    // -------------------------------------------------------------------------------------------------------- QUANTITY
    public static final String COLUMN_NAME_QUANTITY = "QUANTITY";

    // ----------------------------------------------------------------------------- SHIPMENT_ID / shipmentId / shipment
    public static final String COLUMN_NAME_SHIPMENT_ID = "SHIPMENT_ID";

    public static final String ATTRIBUTE_SHIPMENT_ID = "shipmentId";

    public static final String ATTRIBUTE_SHIPMENT = "shipment";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrderItem() {
        super();
    }

    protected MappedOrderItem(final MappedOrderItemBuilder<?, ?> builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               ",productId=" + productId +
               ",unitPrice=" + unitPrice +
               ",quantity=" + quantity +
               ",shipmentId=" + shipmentId +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedOrderItem that)) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation
    protected boolean isUniPricesNonNegative() {
        if (unitPrice == null) {
            return true;
        }
        return unitPrice.signum() >= 1;
    }

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

    // -----------------------------------------------------------------------------------------------------------------
    public abstract OrderItemId getId();

    protected abstract void setId(final OrderItemId id);

    // ------------------------------------------------------------------------------------------------------- productId
    @Nonnull
    Long getProductId() {
        return productId;
    }

    protected void setProductId(@Nonnull final Long productId) {
        this.productId = productId;
    }

    // ------------------------------------------------------------------------------------------------------- unitPrice
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    protected void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // -------------------------------------------------------------------------------------------------------- quantity
    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(final Long quantity) {
        this.quantity = quantity;
    }

    // ------------------------------------------------------------------------------------------------------ shipmentId
    @Nullable
    public Long getShipmentId() {
        return shipmentId;
    }

    protected void setShipmentId(@Nullable final Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = true, updatable = false)
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
    @Nullable
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_SHIPMENT_ID, nullable = true, insertable = true, updatable = true)
    private Long shipmentId;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the total price of this order item which is {@value MappedOrderItem_#UNIT_PRICE} attribute multiplied by
     * {@value MappedOrderItem_#QUANTITY} attribute.
     *
     * @param mc a math context to use.
     * @return the total price of this item.
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
