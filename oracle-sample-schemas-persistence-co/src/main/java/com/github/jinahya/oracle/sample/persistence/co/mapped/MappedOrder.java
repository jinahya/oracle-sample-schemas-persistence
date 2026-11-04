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
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * A mapped superclass for mapping the {@value MappedOrder#TABLE_NAME} table.
 * <p>
 * The {@value #COLUMN_NAME_CUSTOMER_ID} and {@value #COLUMN_NAME_STORE_ID} columns are mapped
 * read-only ({@code insertable = false, updatable = false}), with a public getter and a
 * {@code protected} setter, for {@link #toString()} and for queries. How the relationship behind
 * each is mapped -- fetch type, cascade, whether there is an association at all -- is the extending
 * entity's decision, so the extending entity also owns their writable mapping, by an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}. <strong>An extending entity which maps none
 * never writes them.</strong> Being read-only, they are populated by a load or a refresh, or by the
 * extending entity, which sets them through their {@code protected} setters in step with the
 * associations which write the columns.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedOrder {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ORDERS";

    // -------------------------------------------------------------------------------------------------------- ORDER_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_ORDER_ID} attribute maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_ID = "ORDER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    // ------------------------------------------------------------------------------------------------------- ORDER_TMS

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_TMS = "ORDER_TMS";

    /**
     * The fractional seconds precision of the {@value #COLUMN_NAME_ORDER_TMS} column. The value is
     * {@value}.
     */
    public static final int FRACTIONAL_SECONDS_PRECISION_ORDER_TMS = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_TMS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_TMS = "orderTms";

    // ----------------------------------------------------------------------------------------------------- CUSTOMER_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute
     * maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column, read-only.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // ---------------------------------------------------------------------------------------------------- ORDER_STATUS

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute
     * maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_STATUS = "ORDER_STATUS";

    /**
     * The length of the {@value #COLUMN_NAME_ORDER_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ORDER_STATUS = 10;

    /**
     * A value of the {@value #COLUMN_NAME_ORDER_STATUS} column, for an order which has been
     * cancelled. The value is {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_CANCELLED = "CANCELLED";

    /**
     * A value of the {@value #COLUMN_NAME_ORDER_STATUS} column, for an order which has been
     * completed. The value is {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_COMPLETE = "COMPLETE";

    /**
     * A value of the {@value #COLUMN_NAME_ORDER_STATUS} column, for an order which is still open.
     * The value is {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_OPEN = "OPEN";

    /**
     * A value of the {@value #COLUMN_NAME_ORDER_STATUS} column, for an order which has been paid
     * for. The value is {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_PAID = "PAID";

    /**
     * A value of the {@value #COLUMN_NAME_ORDER_STATUS} column, for an order which has been
     * refunded. The value is {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_REFUNDED = "REFUNDED";

    /**
     * A value of the {@value #COLUMN_NAME_ORDER_STATUS} column, for an order which has been
     * shipped. The value is {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_SHIPPED = "SHIPPED";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_STATUS} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_STATUS = "orderStatus";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute. The value is
     * {@value}.
     */
    public static final int SIZE_MIN_ORDER_STATUS = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute. The value is
     * {@value}.
     */
    public static final int SIZE_MAX_ORDER_STATUS = COLUMN_LENGTH_ORDER_STATUS;

    /**
     * An enum for the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public enum OrderStatus {

        /**
         * A constant for the {@value MappedOrder#COLUMN_VALUE_ORDER_STATUS_OPEN} value, for an
         * order which is still open.
         */
        OPEN,

        /**
         * A constant for the {@value MappedOrder#COLUMN_VALUE_ORDER_STATUS_CANCELLED} value, for an
         * order which has been cancelled.
         */
        // 취소?
        CANCELLED,

        /**
         * A constant for the {@value MappedOrder#COLUMN_VALUE_ORDER_STATUS_PAID} value, for an
         * order which has been paid for.
         */
        // 지불됨?
        PAID,

        /**
         * A constant for the {@value MappedOrder#COLUMN_VALUE_ORDER_STATUS_REFUNDED} value, for an
         * order which has been refunded.
         */
        // (지불) 반환딤?
        REFUNDED,

        /**
         * A constant for the {@value MappedOrder#COLUMN_VALUE_ORDER_STATUS_SHIPPED} value, for an
         * order which has been shipped.
         */
        // 출고됨?
        SHIPPED,

        /**
         * A constant for the {@value MappedOrder#COLUMN_VALUE_ORDER_STATUS_COMPLETE} value, for an
         * order which has been completed.
         */
        // 완료?
        COMPLETE;
    }

    // -------------------------------------------------------------------------------------------------------- STORE_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_STORE_ID} attribute maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_ID} column, read-only.
     * The value is {@value}.
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
    public String toString() {
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
     * @implSpec Equality is by the generated {@code @Id} alone, the other instance's read
     *         through its getter, which is what makes the comparison correct when the other
     *         instance is still a lazy proxy. An instance whose {@code @Id} is still {@code null}
     *         -- one not yet persisted -- equals itself only.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedOrder that)) {
            return false;
        }
        return orderId != null && orderId.equals(that.getOrderId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is constant -- the class's -- so that it does not change when the
     *         generated {@code @Id} is assigned on persist, which would lose an instance already
     *         held in a hash-based collection.
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute with the specified
     * value.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute with the specified
     * value.
     *
     * @param orderTms new value for {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute.
     */
    public void setOrderTms(@Nonnull final LocalDateTime orderTms) {
        this.orderTms = orderTms;
    }

    /**
     * Returns current value of the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute, mapped by the
     * specified function.
     *
     * @param <R>    the type of the mapped value.
     * @param mapper the function to apply to current value of the
     *               {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute.
     * @return the mapped value; {@code null} when the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute
     *         is {@code null}.
     * @throws NullPointerException if {@code mapper} is {@code null} while the
     *                              {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute is not
     *                              {@code null}.
     */
    public <R> R getOrderTmsAsMapped(final Function<? super LocalDateTime, ? extends R> mapper) {
        return Optional.ofNullable(getOrderTms())
                .map(v -> Objects.requireNonNull(mapper, "mapper is null").apply(v))
                .orElse(null);
    }

    /**
     * Replaces current value of the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute with the specified
     * value, mapped by the specified function.
     *
     * @param <T>      the type of the specified value.
     * @param orderTms the value to map and set.
     * @param mapper   the function which maps the specified value.
     * @throws NullPointerException if {@code mapper} is {@code null} while {@code orderTms} is not
     *                              {@code null}.
     */
    public <T> void setOrderTmsFromMapped(final T orderTms,
                                          final Function<? super T, LocalDateTime> mapper) {
        setOrderTms(
                Optional.ofNullable(orderTms)
                        .map(v -> Objects.requireNonNull(mapper, "mapper is null").apply(v))
                        .orElse(null)
        );
    }

    /**
     * Returns current value of the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute, at the specified
     * zone.
     *
     * @param zone the zone to apply.
     * @return a zoned date-time; {@code null} when the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute
     *         is {@code null}.
     * @throws NullPointerException if {@code zone} is {@code null} while the
     *                              {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute is not
     *                              {@code null}.
     */
    public ZonedDateTime getOrderTmsAsZonedDatetime(final ZoneId zone) {
        return getOrderTmsAsMapped(
                v -> v.atZone(Objects.requireNonNull(zone, "zone is null"))
        );
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute with the local
     * date-time of the specified zoned date-time.
     *
     * @param orderTms the zoned date-time whose local date-time is set; may be {@code null}.
     */
    @Transient
    public void setOrderTmsFromZonedDateTime(final ZonedDateTime orderTms) {
        setOrderTmsFromMapped(orderTms, ZonedDateTime::toLocalDateTime);
    }

    /**
     * Returns current value of the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute, at the specified
     * offset.
     *
     * @param offset the offset to apply.
     * @return an offset date-time; {@code null} when the {@value #ATTRIBUTE_NAME_ORDER_TMS}
     *         attribute is {@code null}.
     * @throws NullPointerException if {@code offset} is {@code null} while the
     *                              {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute is not
     *                              {@code null}.
     */
    public OffsetDateTime getOrderTmsAsOffsetDatetime(final ZoneOffset offset) {
        return getOrderTmsAsMapped(
                v -> v.atOffset(Objects.requireNonNull(offset, "offset is null"))
        );
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute with the local
     * date-time of the specified offset date-time.
     *
     * @param orderTms the offset date-time whose local date-time is set; may be {@code null}.
     */
    @Transient
    public void setOrderTmsFromOffsetDateTime(final OffsetDateTime orderTms) {
        setOrderTmsFromMapped(
                orderTms,
                OffsetDateTime::toLocalDateTime
        );
    }

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute as an
     * {@link Instant instant} at specified zone.
     *
     * @param zone the zone.
     * @return {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute as an {@link Instant instant} at
     *         {@code zone}; {@code null} when the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute is
     *         {@code null}.
     * @throws NullPointerException if {@code zone} is {@code null} while the
     *                              {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute is not
     *                              {@code null}.
     */
    public Instant getOrderTmsAsInstant(final ZoneId zone) {
        return Optional.ofNullable(getOrderTmsAsZonedDatetime(zone))
                .map(ZonedDateTime::toInstant)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute with the specified
     * instant at specified zone.
     *
     * @param instant the instant; may be {@code null}.
     * @param zone    the zone.
     * @throws NullPointerException if {@code zone} is {@code null} while {@code instant} is not
     *                              {@code null}.
     */
    public void setOrderTmsFromInstant(final Instant instant, final ZoneId zone) {
        setOrderTmsFromZonedDateTime(
                Optional.ofNullable(instant)
                        .map(v -> v.atZone(Objects.requireNonNull(zone, "zone is null")))
                        .orElse(null)
        );
    }

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute as an
     * {@link Instant instant} at specified offset.
     *
     * @param offset the offset.
     * @return {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute as an {@link Instant instant} at
     *         {@code offset}; {@code null} when the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute is
     *         {@code null}.
     * @throws NullPointerException if {@code offset} is {@code null} while the
     *                              {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute is not
     *                              {@code null}.
     */
    public Instant getOrderTmsAsInstant(final ZoneOffset offset) {
        return Optional.ofNullable(getOrderTmsAsOffsetDatetime(offset))
                .map(OffsetDateTime::toInstant)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute with the specified
     * instant at specified offset.
     *
     * @param orderTms the instant; may be {@code null}.
     * @param offset   the offset.
     * @throws NullPointerException if {@code offset} is {@code null} while {@code orderTms} is not
     *                              {@code null}.
     */
    public void setOrderTmsFromInstant(final Instant orderTms, final ZoneOffset offset) {
        setOrderTmsFromOffsetDateTime(
                Optional.ofNullable(orderTms)
                        .map(v -> v.atOffset(Objects.requireNonNull(offset, "offset is null")))
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------ customerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    @Nonnull
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified
     * value.
     *
     * @param customerId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected void setCustomerId(@Nonnull final Long customerId) {
        this.customerId = customerId;
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute with the specified
     * value.
     *
     * @param orderStatus new value for {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     */
    public void setOrderStatus(@Nonnull final OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    // --------------------------------------------------------------------------------------------------------- storeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     */
    @Nonnull
    public Long getStoreId() {
        return storeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute with the specified
     * value.
     *
     * @param storeId new value for {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     */
    protected void setStoreId(@Nonnull final Long storeId) {
        this.storeId = storeId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // no @NotNull: database-generated identity; the value is null when the provider validates at
    // pre-persist
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_ORDER_ID,
            nullable = false,
            // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
            // insertable=false on a @GeneratedValue @Id -- it treats the mapping as read-only and
            // fails descriptor initialisation with EclipseLink-46/EclipseLink-41. Verified on 5
            // .0.1.
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
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Nonnull
    @NotNull
    @Basic(optional = false)
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
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = false, updatable = false)
    private Long storeId;
}
