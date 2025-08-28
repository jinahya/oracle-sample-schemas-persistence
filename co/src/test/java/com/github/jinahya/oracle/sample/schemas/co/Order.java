package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import com.github.jinahya.persistence.more.__AttributeEnum;
import com.github.jinahya.persistence.more.__AttributeEnumConverter;
import com.github.jinahya.persistence.more.__AttributeEnumUtils;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = Order.TABLE_NAME)
public class Order extends __MappedEntity<Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ORDERS";

    // -------------------------------------------------------------------------------------------------------- ORDER_ID

    /**
     * The name of the table column to which the {@link Order_#orderId orderId} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_ID = "ORDER_ID";

    // ------------------------------------------------------------------------------------------------------- ORDER_TMS
    public static final String COLUMN_NAME_ORDER_TMS = "ORDER_TMS";

    public static final int FRACTIONAL_SECONDS_PRECISION_ORDER_TMS = 6;

    // ----------------------------------------------------------------------------------------------------- CUSTOMER_ID
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    // ---------------------------------------------------------------------------------------------------- ORDER_STATUS
    public static final String COLUMN_NAME_ORDER_STATUS = "ORDER_STATUS";

    public static final int COLUMN_LENGTH_ORDER_STATUS = 10;

    public static final String COLUMN_VALUE_ORDER_STATUS_CANCELLED = "CANCELLED";

    public static final String COLUMN_VALUE_ORDER_STATUS_COMPLETE = "COMPLETE";

    public static final String COLUMN_VALUE_ORDER_STATUS_OPEN = "OPEN";

    public static final String COLUMN_VALUE_ORDER_STATUS_PAID = "PAID";

    public static final String COLUMN_VALUE_ORDER_STATUS_REFUNDED = "REFUNDED";

    public static final String COLUMN_VALUE_ORDER_STATUS_SHIPPED = "SHIPPED";

    public interface __OrderStatus<E extends Enum<E> & __OrderStatus<E>> extends __AttributeEnum.__OfString<E> {

    }

    /**
     * An enum for {@link Order_#orderStatus orderStatus} attribute.
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

//    static final Map<OrderStatus, Set<OrderStatus>> TRANSITIONS = Map.of(
//            OrderStatus.OPEN, Collections.unmodifiableSet(EnumSet.of(OrderStatus.PAID, OrderStatus.CANCELLED)),
//            OrderStatus.CANCELLED, Collections.unmodifiableSet(EnumSet.noneOf(OrderStatus.class)),
//            OrderStatus.PAID, Collections.unmodifiableSet(EnumSet.of(OrderStatus.REFUNDED, OrderStatus.SHIPPED)),
//            OrderStatus.REFUNDED, Collections.unmodifiableSet(EnumSet.noneOf(OrderStatus.class)),
//            OrderStatus.SHIPPED, Collections.unmodifiableSet(EnumSet.of(OrderStatus.COMPLETE)),
//            OrderStatus.COMPLETE, Collections.unmodifiableSet(EnumSet.noneOf(OrderStatus.class))
//    );
//
//    static boolean isFinalStage(final OrderStatus orderStatus) {
//        Objects.requireNonNull(orderStatus, "orderStatus is null");
//        return TRANSITIONS.get(orderStatus).isEmpty();
//    }

    // -------------------------------------------------------------------------------------------------------- STORE_ID
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

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
                ", orderTms=" + orderTms +
//                ",customer=" + customer +
                ", orderStatus=" + orderStatus +
//                ",store=" + store +
//                ",orderItems=" + orderItems +
                '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (false && !(obj instanceof Order)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public final int hashCode() {
        return super.hashCode();
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
     * Returns current value of {@link Order_#orderTms orderTms} attribute as an {@link Instant instant} at specified
     * zone.
     *
     * @param zone the zone.
     * @return {@link Order_#orderTms orderTms} attribute as an {@link Instant instant} at {@code zone}.
     */
    public Instant getOrderTmsAsInstant(final ZoneId zone) {
        return Optional.ofNullable(getOrderTmsAsZonedDatetime(zone))
                .map(ZonedDateTime::toInstant)
                .orElse(null);
    }

    /**
     * Replaces current value of {@link Order_#orderTms orderTms} attribute with specified instant at specified zone.
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
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(@Nonnull final Customer customer) {
        this.customer = customer;
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
    public void setOrderStatusFrom(final _OrderStatus enumInstance) {
        setOrderStatus(
                Optional.ofNullable(enumInstance)
                        .map(__AttributeEnum::attributeValue)
                        .orElse(null)
        );
    }

    // --------------------------------------------------------------------------------------------------------- storeId
    @Nonnull
    public Store getStore() {
        return store;
    }

    public void setStore(@Nonnull final Store store) {
        this.store = store;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_ORDER_ID, nullable = false,
            insertable = true, // EclipseLink
            updatable = false)
    private Long orderId;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_TMS, nullable = false, insertable = true, updatable = false)
    private LocalDateTime orderTms;

    @Nonnull
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true, updatable = false)
    private Customer customer;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_STATUS, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_ORDER_STATUS)
    private String orderStatus;

    @Nonnull
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = true, updatable = false)
    private Store store;

    // -----------------------------------------------------------------------------------------------------------------
    public List<OrderItem> getOrderItems() {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        return orderItems;
    }

    @OneToMany(
            mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            },
            orphanRemoval = true
    )
    private List<@Valid @NotNull OrderItem> orderItems;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the total price of all order items.
     *
     * @return the total price of all order items
     */
    @Nonnull
    @Transient
    public BigDecimal getOrderItemsTotalPrice1() {
        return getOrderItems().stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Nullable
    public BigDecimal getOrderItemsTotalPrice2(@Nonnull final EntityManager entityManager) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        return entityManager.createQuery(
                        """
                                SELECT SUM(e.unitPrice * e.quantity)
                                FROM OrderItem AS e
                                WHERE e.order = :order""",
                        BigDecimal.class
                )
                .setParameter("order", this)
                .getSingleResult(); // NoResultException
    }

    @Nullable
    public BigDecimal getOrderItemsTotalPrice3(@Nonnull final EntityManager entityManager) {
        final var builder = entityManager.getCriteriaBuilder();
        final var criteria = builder.createQuery(BigDecimal.class);
        final var root = criteria.from(OrderItem.class);
        criteria.select(
                builder.sum(
                        builder.prod(
                                root.get(OrderItem_.unitPrice),
                                builder.toBigDecimal(root.get(OrderItem_.quantity))
                        )
                )
        );
        criteria.where(builder.equal(root.get(OrderItem_.order), this));
        return entityManager.createQuery(criteria).getSingleResult(); // NoResultException
    }
}
