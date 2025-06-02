package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/// An entity class for mapping {@value Order#TABLE_NAMe} table.
///
/// @author Jin Kwon &lt;onacit_at_gmail.com&gt;
@NamedQuery(
        name = "Order.findAllByStore",
        query = """
                SELECT e
                FROM Order AS e
                WHERE e.store = :store"""
)
@NamedQuery(
        name = "Order.findAllByCustomer",
        query = """
                SELECT e
                FROM Order AS e
                WHERE e.customer = :customer"""
)
@Entity
@Table(name = Order.TABLE_NAMe)
public class Order extends __MappedEntity<Order, Long> {

    @Serial
    private static final long serialVersionUID = 6871992783901039564L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAMe = "ORDERS";

    // -------------------------------------------------------------------------------------------------------- ORDER_ID
    public static final String COLUMN_NAME_ORDER_ID = "ORDER_ID";

    // ------------------------------------------------------------------------------------------------------- ORDER_TMS
    public static final String COLUMN_NAME_ORDER_TMS = "ORDER_TMS";

    // ----------------------------------------------------------------------------------------------------- CUSTOMER_ID
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    // ---------------------------------------------------------------------------------------------------- ORDER_STATUS
    public static final String COLUMN_NAME_ORDER_STATUS = "ORDER_STATUS";

    public static final String COLUMN_VALUE_ORDER_STATUS_CANCELLED = "CANCELLED";

    public static final String COLUMN_VALUE_ORDER_STATUS_COMPLETED = "COMPLETE";

    public static final String COLUMN_VALUE_ORDER_STATUS_OPEN = "OPEN";

    public static final String COLUMN_VALUE_ORDER_STATUS_PAID = "PAID";

    public static final String COLUMN_VALUE_ORDER_STATUS_REFUNDED = "REFUNDED";

    public static final String COLUMN_VALUE_ORDER_STATUS_SHIPPED = "SHIPPED";

    /// An enum for {@link Order_#orderStatus orderStatus} attribute.
    ///
    /// @author Jin Kwon &lt;onacit_at_gmail.com&gt;
    public enum OrderStatus {

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

    static final Map<OrderStatus, Set<OrderStatus>> TRANSITIONS = Map.of(
            OrderStatus.OPEN, Collections.unmodifiableSet(EnumSet.of(OrderStatus.PAID, OrderStatus.CANCELLED)),
            OrderStatus.CANCELLED, Collections.unmodifiableSet(EnumSet.noneOf(OrderStatus.class)),
            OrderStatus.PAID, Collections.unmodifiableSet(EnumSet.of(OrderStatus.REFUNDED, OrderStatus.SHIPPED)),
            OrderStatus.REFUNDED, Collections.unmodifiableSet(EnumSet.noneOf(OrderStatus.class)),
            OrderStatus.SHIPPED, Collections.unmodifiableSet(EnumSet.of(OrderStatus.COMPLETE)),
            OrderStatus.COMPLETE, Collections.unmodifiableSet(EnumSet.noneOf(OrderStatus.class))
    );

    static boolean isFinalStage(final OrderStatus orderStatus) {
        Objects.requireNonNull(orderStatus, "orderStatus is null");
        return TRANSITIONS.get(orderStatus).isEmpty();
    }

    // -------------------------------------------------------------------------------------------------------- STORE_ID
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public Order() {
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

    // ------------------------------------------------------------------------------------------------------ super._id_
    @Override
    protected final Long _id_() {
        return getOrderId();
    }

    @Override
    protected final void _id_(final Long _id_) {
        setOrderId(_id_);
    }

    // --------------------------------------------------------------------------------------------------------- orderId
    public Long getOrderId() {
        return orderId;
    }

    void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }

    // -------------------------------------------------------------------------------------------------------- orderTms
    public LocalDateTime getOrderTms() {
        return orderTms;
    }

    public void setOrderTms(final LocalDateTime orderTms) {
        this.orderTms = orderTms;
    }

    // -------------------------------------------------------------------------------------------------------- customer
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    // ----------------------------------------------------------------------------------------------------- orderStatus
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(final OrderStatus orderStatus) {
        if (this.orderStatus != null && orderStatus != null && this.orderStatus != orderStatus) {
            final var transitions = TRANSITIONS.get(this.orderStatus);
            if (!transitions.contains(orderStatus)) {
                throw new IllegalArgumentException(
                        "invalid transition from " + this.orderStatus + " to " + orderStatus);
            }
        }
        this.orderStatus = orderStatus;
    }

    // --------------------------------------------------------------------------------------------------------- storeId
    public Store getStore() {
        return store;
    }

    public void setStore(final Store store) {
        this.store = store;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_ORDER_ID, nullable = false,
            insertable = true, // EclipseLink
            updatable = false)
    private Long orderId;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_TMS, nullable = false, insertable = true, updatable = true)
//    private Instant orderTms;
    private LocalDateTime orderTms;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true, updatable = false)
    private Customer customer;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_STATUS, nullable = false, insertable = true, updatable = true, length = 10)
    private OrderStatus orderStatus = OrderStatus.OPEN;

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