package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import com.github.jinahya.persistence.more.__AttributeEnum;
import com.github.jinahya.persistence.more.__AttributeEnumConverter;
import com.github.jinahya.persistence.more.__AttributeEnumUtils;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Converter;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@MappedSuperclass
public abstract class MappedOrder<
        CUSTOMER extends MappedCustomer,
        STORE extends MappedStore,
        ORDER_ITEM extends MappedOrderItem<?, ?, ?, ?>
        >
        extends __MappedEntity<Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ORDERS";

    // -------------------------------------------------------------------------------------------------------- ORDER_ID

    /**
     * The name of the table column to which the {@link MappedOrder_#orderId orderId} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ORDER_ID = "ORDER_ID";

    // -------------------------------------------------------------------------------------------- ORDER_TMS / orderTms
    public static final String COLUMN_NAME_ORDER_TMS = "ORDER_TMS";

    public static final int FRACTIONAL_SECONDS_PRECISION_ORDER_TMS = 6;

    public static final String ATTRIBUTE_NAME_ORDER_TMS = "orderTms";

    // ----------------------------------------------------------------------------- CUSTOMER_ID / customerId / customer
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    // ---------------------------------------------------------------------------------------------------- ORDER_STATUS
    public static final String COLUMN_NAME_ORDER_STATUS = "ORDER_STATUS";

    public static final int COLUMN_LENGTH_ORDER_STATUS = 10;

    public static final String COLUMN_VALUE_ORDER_STATUS_CANCELLED = "CANCELLED";

    public static final String COLUMN_VALUE_ORDER_STATUS_COMPLETE = "COMPLETE";

    public static final String COLUMN_VALUE_ORDER_STATUS_OPEN = "OPEN";

    public static final String COLUMN_VALUE_ORDER_STATUS_PAID = "PAID";

    public static final String COLUMN_VALUE_ORDER_STATUS_REFUNDED = "REFUNDED";

    public static final String COLUMN_VALUE_ORDER_STATUS_SHIPPED = "SHIPPED";

    public static final String ATTRIBUTE_NAME_ORDER_STATUS = "orderStatus";

    public static final int SIZE_MAX_ORDER_STATUS = COLUMN_LENGTH_ORDER_STATUS;

    public interface __OrderStatus<E extends Enum<E> & __OrderStatus<E>> extends __AttributeEnum.__OfString<E> {

    }

    /**
     * An enum for {@link MappedOrder_#orderStatus orderStatus} attribute.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public enum _OrderStatus implements __OrderStatus<_OrderStatus> {

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

    public abstract static class __OrderStatusConverter<E extends Enum<E> & __OrderStatus<E>>
            extends __AttributeEnumConverter.__OfString<E> {

        __OrderStatusConverter(final Class<E> enumClass) {
            super(enumClass);
        }
    }

    @Converter(autoApply = true)
    public static class _OrderStatusConverter extends __OrderStatusConverter<_OrderStatus> {

        _OrderStatusConverter() {
            super(_OrderStatus.class);
        }
    }

    // -------------------------------------------------------------------------------------- STORE_ID / storeId / store
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    public static final String ATTRIBUTE_NAME_STORE_ID = "storeId";

    public static final String ATTRIBUTE_NAME_STORE = "store";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrder() {
        super();
    }

    protected MappedOrder(final MappedOrderBuilder<?, ?, CUSTOMER, STORE> builder) {
        super();
        orderId = builder.orderId();
        orderTms = builder.orderTms();
        customerId = builder.customerId();
        orderStatus = builder.orderStatus();
        storeId = builder.storeId();
        store = builder.store();
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

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof MappedOrder<?, ?, ?> that)) {
            return false;
        }
        return Objects.equals(getOrderId(), that.getOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getOrderId());
    }

    // --------------------------------------------------------------------------------------------------------- orderId
    public Long getOrderId() {
        return orderId;
    }

    void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }

    // -------------------------------------------------------------------------------------------------------- orderTms
    @Nonnull
    public LocalDateTime getOrderTms() {
        return orderTms;
    }

    public void setOrderTms(@Nonnull final LocalDateTime orderTms) {
        this.orderTms = orderTms;
    }

    public ZonedDateTime getOrderTmsAsZonedDatetime(final ZoneId zone) {
        return Optional.ofNullable(getOrderTms())
                .map(ot -> ot.atZone(Objects.requireNonNull(zone, "zone is null")))
                .orElse(null);
    }

    @Transient
    public void setOrderTmsFromZonedDateTime(final ZonedDateTime zonedDateTime) {
        setOrderTms(
                Optional.ofNullable(zonedDateTime)
                        .map(ZonedDateTime::toLocalDateTime)
                        .orElse(null)
        );
    }

    public OffsetDateTime getOrderTmsAsOffsetDatetime(final ZoneOffset offset) {
        return Optional.ofNullable(getOrderTms())
                .map(ot -> ot.atOffset(Objects.requireNonNull(offset, "offset is null")))
                .orElse(null);
    }

    @Transient
    public void setOrderTmsFromOffsetDateTime(final OffsetDateTime offsetDateTime) {
        setOrderTms(
                Optional.ofNullable(offsetDateTime)
                        .map(OffsetDateTime::toLocalDateTime)
                        .orElse(null)
        );
    }

    /**
     * Returns current value of {@link MappedOrder_#orderTms orderTms} attribute as an {@link Instant instant} at
     * specified zone.
     *
     * @param zone the zone.
     * @return {@link MappedOrder_#orderTms orderTms} attribute as an {@link Instant instant} at {@code zone}.
     */
    public Instant getOrderTmsAsInstant(final ZoneId zone) {
        return Optional.ofNullable(getOrderTmsAsZonedDatetime(zone))
                .map(ZonedDateTime::toInstant)
                .orElse(null);
    }

    /**
     * Replaces current value of {@link MappedOrder_#orderTms orderTms} attribute with specified instant at specified
     * zone.
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

    @Transient
    public Instant getOrderTmsAsInstant(final ZoneOffset offset) {
        return Optional.ofNullable(getOrderTmsAsOffsetDatetime(offset))
                .map(OffsetDateTime::toInstant)
                .orElse(null);
    }

    public void setOrderTmsFromInstant(final Instant instant, final ZoneOffset offset) {
        setOrderTmsFromOffsetDateTime(
                Optional.ofNullable(instant)
                        .map(v -> v.atOffset(Objects.requireNonNull(offset, "offset is null")))
                        .orElse(null)
        );
    }

    // -------------------------------------------------------------------------------------------------------- customer
    @Nonnull
    public CUSTOMER getCustomer() {
        return customer;
    }

    void setCustomer(@Nonnull final CUSTOMER customer) {
        this.customer = customer;
        customerId = Optional.ofNullable(this.customer)
                .map(MappedCustomer::getCustomerId)
                .orElse(null);
    }

    // ----------------------------------------------------------------------------------------------------- orderStatus

    @Nonnull
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(@Nonnull final String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public <E extends Enum<E> & __OrderStatus<E>> E getOrderStatusFrom(final Class<E> enumClass) {
        return Optional.ofNullable(getOrderStatus())
                .map(v -> __AttributeEnumUtils.valueOfAttributeValue(enumClass, v))
                .orElse(null);
    }

    @Transient
    public void setOrderStatusFrom(final _OrderStatus orderStatusEnum) {
        setOrderStatus(
                Optional.ofNullable(orderStatusEnum)
                        .map(__AttributeEnum::attributeValue)
                        .orElse(null)
        );
    }

    // --------------------------------------------------------------------------------------------------------- storeId
    @Nonnull
    public STORE getStore() {
        return store;
    }

    void setStore(@Nonnull final STORE store) {
        this.store = store;
        storeId = Optional.ofNullable(this.store)
                .map(MappedStore::getStoreId)
                .orElse(null);
    }

    // ------------------------------------------------------------------------------------------------------ orderItems

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_ORDER_ID,
            nullable = false,
            insertable = true, // EclipseLink
            updatable = false
    )
    private Long orderId;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_TMS, nullable = false, insertable = true, updatable = false)
    private LocalDateTime orderTms;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true, updatable = false)
    private Long customerId;

    @Nonnull
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = false, updatable = false)
    private CUSTOMER customer;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_STATUS, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_ORDER_STATUS)
    private String orderStatus;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = true, updatable = false)
    private Long storeId;

    @Nonnull
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = false, updatable = false)
    private STORE store;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(
            mappedBy = MappedOrderItem.ATTRIBUTE_NAME_ORDER,
            fetch = FetchType.LAZY
    )
    private List<@Valid @NotNull ORDER_ITEM> orderItems;

    /**
     * Returns the total price of this order.
     *
     * @param mc a math context to use.
     * @return the total price of this order.
     * @see MappedOrderItem#getTotalPrice(MathContext)
     */
    public BigDecimal getTotalPrice(@Nullable final MathContext mc) {
        // TODO: implement!
        throw new UnsupportedOperationException("not yet implemented");
    }
}
