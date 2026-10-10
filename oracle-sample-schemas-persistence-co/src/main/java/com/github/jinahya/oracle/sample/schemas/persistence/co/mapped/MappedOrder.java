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
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * An entity class for mapping the {@value MappedOrder#TABLE_NAME} table.
 * <p>
 * The {@value MappedOrder#COLUMN_NAME_CUSTOMER_ID} and {@value MappedOrder#COLUMN_NAME_STORE_ID} columns are mapped
 * read-only ({@code insertable = false, updatable = false}), with a {@code protected} getter and no setter, for
 * {@link #toString()} and for queries. How the relationship behind each is mapped -- fetch type, cascade, whether there
 * is an association at all -- is the extending entity's decision, so the extending entity also owns their writable
 * mapping, by an association's {@link jakarta.persistence.JoinColumn @JoinColumn} or by an
 * {@link jakarta.persistence.AttributeOverride @AttributeOverride}. <strong>An extending entity which maps neither
 * never writes them.</strong> Being read-only, they are populated by a load or a refresh only.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedOrder implements __MappedDomainEntity<Long> {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ORDERS";

    // -------------------------------------------------------------------------------------------------------- ORDER_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_ORDER_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ORDER_ID = "ORDER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    // ------------------------------------------------------------------------------------------------------- ORDER_TMS

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ORDER_TMS = "ORDER_TMS";

    /**
     * The fractional seconds precision of the {@value #COLUMN_NAME_ORDER_TMS} column. The value is {@value}.
     */
    public static final int FRACTIONAL_SECONDS_PRECISION_ORDER_TMS = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_TMS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_TMS = "orderTms";

    // ----------------------------------------------------------------------------------------------------- CUSTOMER_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // ---------------------------------------------------------------------------------------------------- ORDER_STATUS

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ORDER_STATUS = "ORDER_STATUS";

    /**
     * The length of the {@value #COLUMN_NAME_ORDER_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ORDER_STATUS = 10;

    /**
     * A value of the {@value #COLUMN_NAME_ORDER_STATUS} column, for an order which has been cancelled. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_CANCELLED = "CANCELLED";

    /**
     * A value of the {@value #COLUMN_NAME_ORDER_STATUS} column, for an order which has been completed. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_COMPLETE = "COMPLETE";

    /**
     * A value of the {@value #COLUMN_NAME_ORDER_STATUS} column, for an order which is still open. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_OPEN = "OPEN";

    /**
     * A value of the {@value #COLUMN_NAME_ORDER_STATUS} column, for an order which has been paid for. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_PAID = "PAID";

    /**
     * A value of the {@value #COLUMN_NAME_ORDER_STATUS} column, for an order which has been refunded. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_REFUNDED = "REFUNDED";

    /**
     * A value of the {@value #COLUMN_NAME_ORDER_STATUS} column, for an order which has been shipped. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_SHIPPED = "SHIPPED";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_STATUS = "orderStatus";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_ORDER_STATUS = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_ORDER_STATUS = COLUMN_LENGTH_ORDER_STATUS;

    /**
     * An enum for the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public enum OrderStatus {

        /**
         * A constant for the {@value MappedOrder#COLUMN_VALUE_ORDER_STATUS_OPEN} value, for an order which is still
         * open.
         */
        OPEN,

        /**
         * A constant for the {@value MappedOrder#COLUMN_VALUE_ORDER_STATUS_CANCELLED} value, for an order which has
         * been cancelled.
         */
        CANCELLED,

        /**
         * A constant for the {@value MappedOrder#COLUMN_VALUE_ORDER_STATUS_PAID} value, for an order which has been
         * paid for.
         */
        PAID,

        /**
         * A constant for the {@value MappedOrder#COLUMN_VALUE_ORDER_STATUS_REFUNDED} value, for an order which has been
         * refunded.
         */
        REFUNDED,

        /**
         * A constant for the {@value MappedOrder#COLUMN_VALUE_ORDER_STATUS_SHIPPED} value, for an order which has been
         * shipped.
         */
        SHIPPED,

        /**
         * A constant for the {@value MappedOrder#COLUMN_VALUE_ORDER_STATUS_COMPLETE} value, for an order which has been
         * completed.
         */
        COMPLETE;
    }

    // -------------------------------------------------------------------------------------------------------- STORE_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_STORE_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_ID = "storeId";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrder() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final String toString() {
        return super.toString() + '{' +
               "orderId=" + orderId +
               ",orderTms=" + orderTms +
               ",customerId=" + customerId +
               ",orderStatus=" + orderStatus +
               ",storeId=" + storeId +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the generated {@code @Id} alone, the other instance's read through its getter, which is
     * what makes the comparison correct when the other instance is still a lazy proxy. An instance whose {@code @Id} is
     * still {@code null} -- one not yet persisted -- equals itself only.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedOrder that)) {
            return false;
        }
        final var orderId = getOrderId();
        return orderId != null && orderId.equals(that.getOrderId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is constant so that it does not change when the generated {@code @Id} is assigned on persist,
     * which would lose an instance already held in a hash-based collection. It is {@code MappedOrder}'s rather than
     * {@link #getClass()}'s, because a lazy proxy's class is a generated subclass and must hash alike to the instance
     * it stands for.
     */
    @Override
    public final int hashCode() {
        return MappedOrder.class.hashCode();
    }

    // --------------------------------------------------------------------------------------------------------- orderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute with the specified value.
     *
     * @param orderId new value for {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    protected void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }

    // -------------------------------------------------------------------------------------------------------- orderTms

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute.
     */
    @Nonnull
    public LocalDateTime getOrderTms() {
        return orderTms;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute with the specified value.
     *
     * @param orderTms new value for {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute.
     */
    public void setOrderTms(@Nonnull final LocalDateTime orderTms) {
        this.orderTms = orderTms;
    }

    // ------------------------------------------------------------------------------------------------------ customerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute, which is {@code null} until this
     * instance is loaded.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     * @apiNote This method is {@code protected}, not package-private, so that a lazy proxy, which is a subclass in
     * another package, can override it.
     */
    @Nullable
    protected Long getCustomerId() {
        return customerId;
    }

    // ----------------------------------------------------------------------------------------------------- orderStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     */
    @Nonnull
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute with the specified value.
     *
     * @param orderStatus new value for {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     */
    public void setOrderStatus(@Nonnull final OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    // --------------------------------------------------------------------------------------------------------- storeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute, which is {@code null} until this instance
     * is loaded.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     * @apiNote This method is {@code protected}, not package-private, so that a lazy proxy, which is a subclass in
     * another package, can override it.
     */
    @Nullable
    protected Long getStoreId() {
        return storeId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // no @NotNull: database-generated identity; the value is null when the provider validates at pre-persist
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_ORDER_ID,
            nullable = false,
            // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
            // insertable=false on a @GeneratedValue @Id -- it treats the mapping as read-only and
            // fails descriptor initialisation with EclipseLink-46/EclipseLink-41. Verified on 5.0.1.
            insertable = true,
            updatable = false
    )
    private Long orderId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_TMS, nullable = false, insertable = true, updatable = false)
    private LocalDateTime orderTms;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    // no @NotNull: read-only duplicate of a column the extending entity writes; null until a load
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = false, updatable = false)
    private Long customerId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_STATUS, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_ORDER_STATUS)
    private OrderStatus orderStatus;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    // no @NotNull: read-only duplicate of a column the extending entity writes; null until a load
    @Column(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = false, updatable = false)
    private Long storeId;
}
