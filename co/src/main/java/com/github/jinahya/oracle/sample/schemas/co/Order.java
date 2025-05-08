package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity;
import jakarta.persistence.Basic;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

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

    public enum OrderStatus {

        CANCELLED,

        COMPLETE,

        OPEN,

        PAID,

        REFUNDED,

        SHIPPED;
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
                '}';
    }

    // ------------------------------------------------------------------------------------------------------ super._id_
    @Override
    protected final Long _id_() {
        return orderId;
    }

    @Override
    protected final void _id_(final Long _id_) {
        orderId = _id_;
    }

    // --------------------------------------------------------------------------------------------------------- orderId
    public Long getOrderId() {
        return orderId;
    }

    // -------------------------------------------------------------------------------------------------------- orderTms
    public Instant getOrderTms() {
        return orderTms;
    }

    public void setOrderTms(final Instant orderTms) {
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
    @Column(name = COLUMN_NAME_ORDER_ID, nullable = false, insertable = false, updatable = false)
    private Long orderId;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_TMS, nullable = false, insertable = true, updatable = true)
    private Instant orderTms;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true, updatable = false)
    private Customer customer;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = COLUMN_NAME_ORDER_STATUS, nullable = false, insertable = true, updatable = true, length = 10)
    private OrderStatus orderStatus;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = true, updatable = false)
    private Store store;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(
            mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = {
            }
    )
    private List<@Valid @NotNull OrderItem> orderItems;

    /**
     * Returns the total price of all order items.
     *
     * @return the total price of all order items
     */
    public BigDecimal getOrderItemsTotalPrice() {
        return orderItems.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getOrderItemsTotalPrice1(final EntityManager entityManager) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        return entityManager.createQuery(
                        """
                                SELECT SUM(oi.unitPrice * oi.quantity)
                                FROM OrderItem AS oi
                                WHERE oi.order = :order""",
                        BigDecimal.class
                )
                .setParameter("order", this)
                .getSingleResult();
    }

    public BigDecimal getOrderItemsTotalPrice2(final EntityManager entityManager) {
        final var builder = entityManager.getCriteriaBuilder();
        final var query = builder.createQuery(BigDecimal.class);
        final var root = query.from(OrderItem.class);
        query.select(
                builder.sum(
                        builder.prod(
                                root.get(OrderItem_.unitPrice),
                                builder.toBigDecimal(root.get(OrderItem_.quantity))
                        )
                )
        );
        query.where(builder.equal(root.get(OrderItem_.order), this));
        return entityManager.createQuery(query).getSingleResult();
    }
}