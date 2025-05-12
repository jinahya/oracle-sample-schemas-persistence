package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity;
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
    @AssertTrue
    private boolean isSShipmentCustomerValid() {
        return shipment == null || Objects.equals(shipment.getCustomer(), order.getCustomer());
    }

    @AssertTrue
    private boolean isShipmentStoreValid() {
        return shipment == null || Objects.equals(shipment.getStore(), order.getStore());
    }

    // ----------------------------------------------------------------------------------------------------- orderItemId
    public OrderItemId getId() {
        return id;
    }

    public OrderItemId getId(final Supplier<? extends OrderItemId> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        return Optional.ofNullable(getId()).orElseGet(() -> {
            setId(Objects.requireNonNull(supplier.get(), "null supplied from " + supplier));
            return getId();
        });
    }

    void setId(final OrderItemId id) {
        this.id = id;
    }

    // ----------------------------------------------------------------------------------------------------------- order
    public Order getOrder() {
        return order;
    }

    public void setOrder(final Order order) {
        this.order = order;
        if (true) { // @MapsId
            getId(OrderItemId::new).setOrderId(
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
    }

    // -----------------------------------------------------------------------------------------------------------------
    @EmbeddedId
    private OrderItemId id;

    @MapsId("orderId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ORDER_ID, nullable = false, insertable = false, updatable = false)
    private Order order;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = true, updatable = false)
    private Product product;

    @DecimalMax("99999999.99")
    @DecimalMin("00000000.00")
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE, nullable = false, insertable = true, updatable = false, precision = 10,
            scale = 2)
    private BigDecimal unitPrice;

    @Positive
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY, nullable = false, insertable = true, updatable = false)
    private Long quantity;

    // -----------------------------------------------------------------------------------------------------------------
    @jakarta.annotation.Nullable
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SHIPMENT_ID, insertable = true, updatable = true)
    private Shipment shipment;

    // -----------------------------------------------------------------------------------------------------------------
    @Transient
    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal getTotalPrice(final MathContext mc) {
        return unitPrice.multiply(BigDecimal.valueOf(quantity), mc);
    }
}