package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@Entity
@Table(name = OrderItem.TABLE_NAME)
public class OrderItem extends __MappedEntity<OrderItem, OrderItemId> {

    @Serial
    private static final long serialVersionUID = -834208923628903482L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "ORDER_ITEMS";

    // -------------------------------------------------------------------------------------------------------- ORDER_ID
    public static final String COLUMN_NAME_ORDER_ID = "ORDER_ID";

    // ---------------------------------------------------------------------------------------------------- LINE_ITEM_ID
    public static final String COLUMN_NAME_LINE_ITEM_ID = "LINE_ITEM_ID";

    // ------------------------------------------------------------------------------------------------------ PRODUCT_ID
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    // ------------------------------------------------------------------------------------------------------ UNIT_PRICE
    public static final String COLUMN_NAME_UNIT_PRICE = "UNIT_PRICE";

    public static final int COLUMN_PRECISION_UNIT_PRICE = 10;

    public static final int COLUMN_SCALE_UNIT_PRICE = 2;

    public static final String DECIMAL_MIN_UNIT_PRICE = "00000000.00";

    public static final String DECIMAL_MAX_UNIT_PRICE = "99999999.99";

    // ------------------------------------------------------------------------------------------------------- QUANTITY
    public static final String COLUMN_NAME_QUANTITY = "QUANTITY";

    // ----------------------------------------------------------------------------------------------------- SHIPMENT_ID
    public static final String COLUMN_NAME_SHIPMENT_ID = "SHIPMENT_ID";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public OrderItem() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
                "id=" + id +
//                ",order=" + order +
//                ",product=" + product +
                ",unitPrice=" + unitPrice +
                ",quantity=" + quantity +
//                ",shipment=" + shipment +
                '}';
    }

    // ------------------------------------------------------------------------------------------------------ super._id_
    @Override
    protected final OrderItemId _id_() {
        return getId();
    }

    @Override
    protected final void _id_(final OrderItemId _id_) {
        setId(_id_);
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation
    @AssertTrue(message = "shipment's customer should be equal to the order's customer")
    private boolean isSShipmentCustomerValid() {
        return shipment == null ||
                order == null ||
                Objects.equals(shipment.getCustomer(), order.getCustomer());
    }

    @AssertTrue(message = "shipment's store should be equal to the order's store")
    private boolean isShipmentStoreValid() {
        return shipment == null ||
                order == null ||
                Objects.equals(shipment.getStore(), order.getStore());
    }

    // -------------------------------------------------------------------------------------------------------------- id
    public OrderItemId getId() {
        return id;
    }

    void setId(final OrderItemId id) {
        this.id = id;
    }

    public OrderItemId getIdOrElseSetSuppliedAndGet(final Supplier<? extends OrderItemId> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        return Optional.ofNullable(getId()).orElseGet(() -> {
            setId(Objects.requireNonNull(supplier.get(), "null supplied from " + supplier));
            return getId();
        });
    }

    public OrderItemId getIdOrElseSetNewAndGet() {
        return getIdOrElseSetSuppliedAndGet(OrderItemId::new);
    }

    // ----------------------------------------------------------------------------------------------------------- order
    public Order getOrder() {
        return order;
    }

    public void setOrder(final Order order) {
        this.order = order;
        if (false) { // <<<<<<<< @MapsId("orderId")
            getIdOrElseSetNewAndGet().setOrderId(
                    Optional.ofNullable(this.order)
                            .map(Order::getOrderId)
                            .orElse(null)
            );
        }
    }

    // --------------------------------------------------------------------------------------------------------- product
    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    // ------------------------------------------------------------------------------------------------------- unitPrice
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // -------------------------------------------------------------------------------------------------------- quantity
    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(final Long quantity) {
        this.quantity = quantity;
    }

    // -------------------------------------------------------------------------------------------------------- shipment
    @Nullable
    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(@Nullable final Shipment shipment) {
        this.shipment = shipment;
        Optional.ofNullable(this.shipment).ifPresent(s -> {
            s.setStore(order.getStore());
            s.setCustomer(order.getCustomer());
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @EmbeddedId
    private OrderItemId id;

    // -----------------------------------------------------------------------------------------------------------------
    @MapsId("orderId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ORDER_ID, nullable = false,
                insertable = true, // EclipseLink
                updatable = false)
    private Order order;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = true, updatable = false)
    private Product product;

    @Nonnull
    @DecimalMax(DECIMAL_MAX_UNIT_PRICE)
    @DecimalMin(DECIMAL_MIN_UNIT_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE, nullable = false, insertable = true, updatable = false,
            precision = COLUMN_PRECISION_UNIT_PRICE, scale = COLUMN_SCALE_UNIT_PRICE)
    private BigDecimal unitPrice;

    @Nonnull
    @Positive
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY, nullable = false, insertable = true, updatable = true)
    private Long quantity;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SHIPMENT_ID, insertable = true, updatable = true)
    private Shipment shipment;

    // -----------------------------------------------------------------------------------------------------------------
    @Transient
    public BigDecimal getTotalPrice() {
        if (unitPrice == null) {
            throw new IllegalStateException("unitPrice is null");
        }
        if (quantity == null) {
            throw new IllegalStateException("quantity is null");
        }
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal getTotalPrice(final MathContext mc) {
        Objects.requireNonNull(mc, "mc is null");
        return unitPrice.multiply(BigDecimal.valueOf(quantity), mc);
    }
}