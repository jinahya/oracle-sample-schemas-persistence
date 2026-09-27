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
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * An entity class for mapping the {@value Order#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = Order.TABLE_NAME)
public class Order {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ORDERS";

    // -------------------------------------------------------------------------------------------------------- ORDER_ID

    /**
     * The name of the table column to which the {@code orderId} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_ID = "ORDER_ID";

    /**
     * The name of the attribute which maps the {@code ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    // ------------------------------------------------------------------------------------------------------- ORDER_TMS

    /**
     * The name of the table column to which the {@code orderTms} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_TMS = "ORDER_TMS";

    /**
     * The fractional seconds precision of the {@code ORDER_TMS} column. The value is {@value}.
     */
    public static final int FRACTIONAL_SECONDS_PRECISION_ORDER_TMS = 6;

    /**
     * The name of the attribute which maps the {@code ORDER_TMS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_TMS = "orderTms";

    // ----------------------------------------------------------------------------------------------------- CUSTOMER_ID

    /**
     * The name of the table column to which the {@code customerId} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    /**
     * The name of the attribute which maps the {@code CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    // ---------------------------------------------------------------------------------------------------- ORDER_STATUS

    /**
     * The name of the table column to which the {@code orderStatus} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_STATUS = "ORDER_STATUS";

    /**
     * The length of the {@code ORDER_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ORDER_STATUS = 10;

    /**
     * A value of the {@code ORDER_STATUS} column, for an order which has been cancelled. The value is {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_CANCELLED = "CANCELLED";

    /**
     * A value of the {@code ORDER_STATUS} column, for an order which has been completed. The value is {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_COMPLETE = "COMPLETE";

    /**
     * A value of the {@code ORDER_STATUS} column, for an order which is still open. The value is {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_OPEN = "OPEN";

    /**
     * A value of the {@code ORDER_STATUS} column, for an order which has been paid for. The value is {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_PAID = "PAID";

    /**
     * A value of the {@code ORDER_STATUS} column, for an order which has been refunded. The value is {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_REFUNDED = "REFUNDED";

    /**
     * A value of the {@code ORDER_STATUS} column, for an order which has been shipped. The value is {@value}.
     */
    public static final String COLUMN_VALUE_ORDER_STATUS_SHIPPED = "SHIPPED";

    /**
     * The name of the attribute which maps the {@code ORDER_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_STATUS = "orderStatus";

    /**
     * The minimum size of the {@code orderStatus} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_ORDER_STATUS = 0;

    /**
     * The maximum size of the {@code orderStatus} attribute.
     */
    public static final int SIZE_MAX_ORDER_STATUS = COLUMN_LENGTH_ORDER_STATUS;

    // -------------------------------------------------------------------------------------------------------- STORE_ID

    /**
     * The name of the table column to which the {@code storeId} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    /**
     * The name of the attribute which maps the {@code STORE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE = "store";

    /**
     * The name of the attribute which maps the order items of this order. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_ITEMS = "orderItems";

    /**
     * An enum for {@code orderStatus} attribute.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public enum _OrderStatus {

        /// .
        OPEN,

        /// .
        // 취소?
        CANCELLED,

        /// .
        // 지불됨?
        PAID,

        /// .
        // (지불) 반환딤?
        REFUNDED,

        /// .
        // 출고됨?
        SHIPPED,

        /// .
        // 완료?
        COMPLETE;
    }

    /**
     * An {@link AttributeConverter} between an enum constant and its {@link Enum#name() name}.
     *
     * @param <E> enum type parameter
     */
    public abstract static class __OrderStatusConverter<E extends Enum<E>> implements AttributeConverter<E, String> {
        // ------------------------------------------------------------------------------------------------ CONSTRUCTORS

        /**
         * Creates a new instance for the specified enum class.
         *
         * @param enumClass the enum class to convert.
         */
        protected __OrderStatusConverter(final Class<E> enumClass) {
            super();
            this.enumClass = Objects.requireNonNull(enumClass, "enumClass is null");
        }

        @Override
        public String convertToDatabaseColumn(final E attribute) {
            return attribute == null ? null : attribute.name();
        }

        @Override
        public E convertToEntityAttribute(final String dbData) {
            return dbData == null ? null : Enum.valueOf(enumClass, dbData);
        }

        // -------------------------------------------------------------------------------------------------------------

        /**
         * The enum class which this converter converts.
         */
        protected final Class<E> enumClass;
    }

    /**
     * An attribute converter for the {@link _OrderStatus} enum.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    @Converter(autoApply = true)
    public static class _OrderStatusConverter extends __OrderStatusConverter<_OrderStatus> {
        // ------------------------------------------------------------------------------------------------ CONSTRUCTORS

        _OrderStatusConverter() {
            super(_OrderStatus.class);
        }
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Order() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "orderId=" + orderId +
               ",orderTms=" + orderTms +
               ",customer=" + customer +
               ",orderStatus=" + orderStatus +
               ",store=" + store +
               '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Order that)) {
            return false;
        }
        return Objects.equals(getOrderId(), that.getOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getOrderId());
    }

    // --------------------------------------------------------------------------------------------------------- orderId

    /**
     * Returns current value of {@code orderId} attribute.
     *
     * @return current value of {@code orderId} attribute.
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * Replaces current value of {@code orderId} attribute with the specified value.
     *
     * @param orderId new value for {@code orderId} attribute.
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

    /**
     * Returns current value of the {@code orderTms} attribute, mapped by the specified function.
     *
     * @param <R>    the type of the mapped value.
     * @param mapper the function to apply to current value of the {@code orderTms} attribute.
     * @return the mapped value; {@code null} when the {@code orderTms} attribute is {@code null}.
     */
    public <R> R getOrderTmsAsMapped(final Function<? super LocalDateTime, ? extends R> mapper) {
        return Optional.ofNullable(getOrderTms())
                .map(v -> Objects.requireNonNull(mapper, "mapper is null").apply(v))
                .orElse(null);
    }

    /**
     * Replaces current value of the {@code orderTms} attribute with the specified value, mapped by the specified
     * function.
     *
     * @param <T>      the type of the specified value.
     * @param orderTms the value to map and set.
     * @param mapper   the function which maps the specified value.
     */
    public <T> void setOrderTmsFromMapped(final T orderTms, final Function<? super T, LocalDateTime> mapper) {
        setOrderTms(
                Optional.ofNullable(orderTms)
                        .map(v -> Objects.requireNonNull(mapper, "mapper is null").apply(v))
                        .orElse(null)
        );
    }

    /**
     * Returns current value of the {@code orderTms} attribute, at the specified zone.
     *
     * @param zone the zone to apply.
     * @return a zoned date-time; {@code null} when the {@code orderTms} attribute is {@code null}.
     */
    public ZonedDateTime getOrderTmsAsZonedDatetime(final ZoneId zone) {
        return getOrderTmsAsMapped(
                v -> v.atZone(Objects.requireNonNull(zone, "zone is null"))
        );
    }

    /**
     * Replaces current value of {@code orderTms} attribute with the specified value.
     *
     * @param orderTms new value for {@code orderTms} attribute.
     */
    @Transient
    public void setOrderTmsFromZonedDateTime(final ZonedDateTime orderTms) {
        setOrderTmsFromMapped(orderTms, ZonedDateTime::toLocalDateTime);
    }

    /**
     * Returns current value of the {@code orderTms} attribute, at the specified offset.
     *
     * @param offset the offset to apply.
     * @return an offset date-time; {@code null} when the {@code orderTms} attribute is {@code null}.
     */
    public OffsetDateTime getOrderTmsAsOffsetDatetime(final ZoneOffset offset) {
        return getOrderTmsAsMapped(
                v -> v.atOffset(Objects.requireNonNull(offset, "offset is null"))
        );
    }

    /**
     * Replaces current value of {@code orderTms} attribute with the specified value.
     *
     * @param orderTms new value for {@code orderTms} attribute.
     */
    @Transient
    public void setOrderTmsFromOffsetDateTime(final OffsetDateTime orderTms) {
        setOrderTmsFromMapped(
                orderTms,
                OffsetDateTime::toLocalDateTime
        );
    }

    /**
     * Returns current value of {@code orderTms} attribute as an {@link Instant instant} at specified zone.
     *
     * @param zone the zone.
     * @return {@code orderTms} attribute as an {@link Instant instant} at {@code zone}.
     */
    public Instant getOrderTmsAsInstant(final ZoneId zone) {
        return Optional.ofNullable(getOrderTmsAsZonedDatetime(zone))
                .map(ZonedDateTime::toInstant)
                .orElse(null);
    }

    /**
     * Replaces current value of {@code orderTms} attribute with the specified instant at specified zone.
     *
     * @param instant the instant.
     * @param zone    the zone.
     */
    public void setOrderTmsFromInstant(final Instant instant, final ZoneId zone) {
        setOrderTmsFromZonedDateTime(
                Optional.ofNullable(instant)
                        .map(v -> v.atZone(Objects.requireNonNull(zone, "zone is null")))
                        .orElse(null)
        );
    }

    /**
     * Returns current value of {@code orderTms} attribute as an {@link Instant instant} at specified offset.
     *
     * @param offset the offset.
     * @return {@code orderTms} attribute as an {@link Instant instant} at {@code offset}.
     */
    public Instant getOrderTmsAsInstant(final ZoneOffset offset) {
        return Optional.ofNullable(getOrderTmsAsOffsetDatetime(offset))
                .map(OffsetDateTime::toInstant)
                .orElse(null);
    }

    /**
     * Replaces current value of {@code orderTms} attribute with the specified instant at specified offset.
     *
     * @param orderTms the instant.
     * @param offset   the offset.
     */
    public void setOrderTmsFromInstant(final Instant orderTms, final ZoneOffset offset) {
        setOrderTmsFromOffsetDateTime(
                Optional.ofNullable(orderTms)
                        .map(v -> v.atOffset(Objects.requireNonNull(offset, "offset is null")))
                        .orElse(null)
        );
    }

    // -------------------------------------------------------------------------------------------------------- customer

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     */
    @Nonnull
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute with the specified value.
     *
     * @param customer new value for {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     */
    public void setCustomer(@Nonnull final Customer customer) {
        this.customer = customer;
    }

    // ----------------------------------------------------------------------------------------------------- orderStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     */
    @Nonnull
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute with the specified value.
     *
     * @param orderStatus new value for {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     */
    public void setOrderStatus(@Nonnull final String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Returns current value of the {@code orderStatus} attribute, mapped by the specified function.
     *
     * @param <R>    the type of the mapped value.
     * @param mapper the function to apply to current value of the {@code orderStatus} attribute.
     * @return the mapped value; {@code null} when the {@code orderStatus} attribute is {@code null}.
     */
    public <R> R getOrderStatusAsMapped(final Function<? super String, ? extends R> mapper) {
        return Optional.ofNullable(getOrderStatus())
                .map(v -> Objects.requireNonNull(mapper, "mapper is null").apply(v))
                .orElse(null);
    }

    /**
     * Replaces current value of the {@code orderStatus} attribute with the specified value, mapped by the specified
     * function.
     *
     * @param <T>         the type of the specified value.
     * @param orderStatus the value to map and set.
     * @param mapper      the function which maps the specified value.
     */
    public <T> void setOrderStatusFromMapped(final T orderStatus,
                                             final Function<? super T, ? extends CharSequence> mapper) {
        setOrderStatus(
                Optional.ofNullable(orderStatus)
                        .map(v -> Objects.requireNonNull(mapper, "mapper is null").apply(v))
                        .map(CharSequence::toString)
                        .orElse(null)
        );
    }

    /**
     * Returns current value of {@code orderStatus} attribute as an enum value of the specified enum class.
     *
     * @param enumClass the enum class.
     * @param <E>       enum type parameter.
     * @return current value of {@code orderStatus} attribute as an enum value of {@code enumClass}; {@code null} if the
     * attribute value is currently {@code null}.
     * @throws NullPointerException     if {@code enumClass} is {@code null}.
     * @throws IllegalArgumentException when no constant of {@code enumClass} is named by the current value.
     * @see Enum#valueOf(Class, String)
     */
    public <E extends Enum<E>> E getOrderStatusAsEnum(final Class<E> enumClass) {
        Objects.requireNonNull(enumClass, "enumClass is null");
        return getOrderStatusAsMapped(
                v -> Enum.valueOf(enumClass, v)
        );
    }

    /**
     * Replaces current value of {@code orderStatus} attribute with the specified value.
     *
     * @param enumValue new value for {@code orderStatus} attribute.
     */
    @Transient
    public void setOrderStatusFromEnum(final Enum<?> enumValue) {
        setOrderStatusFromMapped(
                enumValue,
                Enum::name
        );
    }

    // ----------------------------------------------------------------------------------------------------------- store

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE} attribute.
     */
    @Nonnull
    public Store getStore() {
        return store;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE} attribute with the specified value.
     *
     * @param stoer new value for {@value #ATTRIBUTE_NAME_STORE} attribute.
     */
    public void setStore(@Nonnull final Store stoer) {
        this.store = stoer;
    }

    /**
     * Returns the total price of this order, summed over its order items.
     *
     * @return the total price of this order.
     */
    public BigDecimal getTotalPrice() {
        if (orderItems == null) {
            throw new IllegalArgumentException("orderItems is null");
        }
        return orderItems.values()
                .stream()
                .map(v -> v.getTotalPrice(null))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Returns the order item, of this order, which orders the specified product.
     *
     * @param product the product whose order item is returned.
     * @return the order item for the {@code product}; {@code null} when this order has none.
     */
    public @jakarta.annotation.Nullable OrderItemWithEmbeddedId getOrderItem(@Nonnull final Product product) {
        Objects.requireNonNull(product, "product is null");
        if (orderItems == null) {
            throw new IllegalStateException("orderItems is null");
        }
        return orderItems.get(product);
    }

    void addOrderItem(@Nonnull final OrderItemWithEmbeddedId orderItem) {
        Objects.requireNonNull(orderItem, "orderItem is null");
        // TODO: check the orderStatus!!!
        final var previous = getOrderItem(orderItem.getProduct());
        if (previous != null) {
            previous.setQuantity(previous.getQuantity() + orderItem.getQuantity());
        } else {
            addOrderItem(orderItem);
        }
    }

    @jakarta.annotation.Nullable
    OrderItemWithEmbeddedId removeOrderItem(@Nonnull final Product product) {
        Objects.requireNonNull(product, "product is null");
        if (orderItems == null) {
            throw new IllegalStateException("orderItems is null");
        }
        // TODO: check the orderStatus!!!
        return orderItems.remove(product);
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
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
    @Nonnull
    @Valid
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true, updatable = false)
    private Customer customer;

    @Nonnull
    @Size(max = SIZE_MAX_ORDER_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_STATUS, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_ORDER_STATUS)
    private String orderStatus;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = true, updatable = false)
    private Store store;

    // -----------------------------------------------------------------------------------------------------------------
    @MapKeyColumn(name = OrderItemWithEmbeddedId.COLUMN_NAME_PRODUCT_ID)
    @OneToMany(mappedBy = OrderItemWithEmbeddedId.ATTRIBUTE_NAME_ORDER,
               fetch = FetchType.LAZY,
               cascade = {
                       // TODO: add, may be all?
               },
               orphanRemoval = true
    )
    private Map<Product, OrderItemWithEmbeddedId> orderItems;
}
