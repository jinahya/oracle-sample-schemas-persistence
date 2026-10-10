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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedOrderItem#TABLE_NAME} table, except for its
 * identifier.
 * <p>
 * The {@value MappedOrderItem#COLUMN_NAME_PRODUCT_ID} and {@value MappedOrderItem#COLUMN_NAME_SHIPMENT_ID} columns are
 * mapped read-only ({@code insertable = false, updatable = false}), with a {@code protected} getter and no setter, for
 * {@link #toString()} and for queries. How the relationship behind each is mapped -- fetch type, cascade, whether there
 * is an association at all -- is the extending entity's decision, so the extending entity also owns their writable
 * mapping, by an association's {@link jakarta.persistence.JoinColumn @JoinColumn} or by an
 * {@link jakarta.persistence.AttributeOverride @AttributeOverride}. <strong>An extending entity which maps neither
 * never writes them.</strong> Being read-only, they are populated by a load or a refresh only.
 *
 * @param <T> the type of the identifier; a subclass maps it as it chooses, and exposes it through
 *            {@link #getIdValue()}.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedOrderItemId
 */
@MappedSuperclass
public abstract class MappedOrderItem<T extends MappedOrderItemId> implements __MappedDomainEntity<T> {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ORDER_ITEMS";

    // -------------------------------------------------------------------------------------------------------- ORDER_ID

    /**
     * The name of the table column to which the {@value MappedOrderItemId#ATTRIBUTE_NAME_ORDER_ID} attribute of
     * {@link MappedOrderItemId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_ID = "ORDER_ID";

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_ORDER_ID} column, for a subclass whose identifier
     * is an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_ORDER_ID = "id.orderId";

    /**
     * The name of an attribute, which this class does not declare, joining on the {@value #COLUMN_NAME_ORDER_ID}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER = "order";

    // ---------------------------------------------------------------------------------------------------- LINE_ITEM_ID

    /**
     * The name of the table column to which the {@value MappedOrderItemId#ATTRIBUTE_NAME_LINE_ITEM_ID} attribute of
     * {@link MappedOrderItemId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_LINE_ITEM_ID = "LINE_ITEM_ID";

    // ------------------------------------------------------------------------------------ ORDER_ID / LINE_ITEM_ID / id

    /**
     * The name of the identifier attribute, declared by a subclass, which maps both the {@value #COLUMN_NAME_ORDER_ID}
     * and the {@value #COLUMN_NAME_LINE_ITEM_ID} columns. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    // -------------------------------------------------------------------------------- PRODUCT_ID / productId / product

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    /**
     * The name of an attribute, which this class does not declare, joining on the {@value #COLUMN_NAME_PRODUCT_ID}
     * column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

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

    // ----------------------------------------------------------------------------- SHIPMENT_ID / shipmentId / shipment

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_SHIPMENT_ID = "SHIPMENT_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIPMENT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIPMENT_ID = "shipmentId";

    /**
     * The name of an attribute, which this class does not declare, joining on the {@value #COLUMN_NAME_SHIPMENT_ID}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIPMENT = "shipment";

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
               "id=" + getIdValue() +
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
     * @implSpec Equality is by the identifier a subclass exposes through {@link #getIdValue()}, alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedOrderItem<?> that)) {
            return false;
        }
        return Objects.equals(getIdValue(), that.getIdValue());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier a subclass exposes through {@link #getIdValue()}, consistent with
     * {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getIdValue());
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    /**
     * Indicates whether the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute is positive.
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute is {@code null} or positive;
     * {@code false} otherwise.
     */
    protected boolean isUniPricesNonNegative() {
        if (unitPrice == null) {
            return true;
        }
        return unitPrice.signum() >= 1;
    }

    /**
     * Indicates whether the {@value #ATTRIBUTE_NAME_QUANTITY} attribute is non-negative.
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_QUANTITY} attribute is {@code null} or non-negative;
     * {@code false} otherwise.
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

    // --------------------------------------------------------------------------------------------------------- idValue

    /**
     * Returns the identifier of this order item. A subclass implements this with whichever attributes it maps the
     * identifier to; {@link #equals(Object)} and {@link #hashCode()} compare by it.
     *
     * @return the identifier of this order item; {@code null} if it has none yet.
     */
    @Transient
    protected abstract T getIdValue();

    // ------------------------------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute, which is {@code null} until this instance
     * is loaded.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     * @apiNote This method is {@code protected}, not package-private, so that a lazy proxy, which is a subclass in
     * another package, can override it.
     */
    @Nullable
    protected Long getProductId() {
        return productId;
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
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute, which is {@code null} until this
     * instance is loaded.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute; {@code null} when not yet shipped.
     * @apiNote This method is {@code protected}, not package-private, so that a lazy proxy, which is a subclass in
     * another package, can override it.
     */
    @Nullable
    protected Long getShipmentId() {
        return shipmentId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    // no @NotNull: read-only duplicate of a column the extending entity writes; null until a load
    @Column(name = COLUMN_NAME_PRODUCT_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Long productId;

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
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Column(name = COLUMN_NAME_SHIPMENT_ID, nullable = true, insertable = false, updatable = false)
    private Long shipmentId;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the total price of this order item which is {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute multiplied by
     * {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     *
     * @param mc a math context to use; {@code null} for an exact result.
     * @return the total price of this item.
     * @throws IllegalStateException if either {@value #ATTRIBUTE_NAME_UNIT_PRICE} or {@value #ATTRIBUTE_NAME_QUANTITY}
     *                               attribute is {@code null}.
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
