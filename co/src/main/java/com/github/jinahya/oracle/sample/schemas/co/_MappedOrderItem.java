package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntity;
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
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@Entity
@Table(name = _MappedOrderItem.TABLE_NAME)
abstract class _MappedOrderItem<
        SELF extends _MappedOrderItem<SELF, ID, ORDER>,
        ID extends _MappedOrderItemId<ID>,
        ORDER extends _MappedOrder<ORDER, SELF, ID>
        >
        extends __MappedEntity<SELF, ID> {

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
    public _MappedOrderItem() {
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

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof _MappedOrderItem<?, ?, ?>)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    // ------------------------------------------------------------------------------------------------------ super._id_
    @Override
    protected final ID getId__() {
        return getId();
    }

    @Override
    protected final void setId__(final ID id__) {
        setId(id__);
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
    public ID getId() {
        return id;
    }

    void setId(final ID id) {
        this.id = id;
    }

    public ID getIdOrElseSetSuppliedAndGet(final Supplier<? extends ID> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        return Optional.ofNullable(getId()).orElseGet(() -> {
            setId(Objects.requireNonNull(supplier.get(), "null supplied from " + supplier));
            return getId();
        });
    }

    // ----------------------------------------------------------------------------------------------------------- order
    @Nonnull
    public Order getOrder() {
        return order;
    }

    public void setOrder(@Nonnull final Order order) {
        this.order = order;
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
        if (this.quantity != null && this.quantity <= 0L && order != null) {
            order.getOrderItems().remove(this);
        }
    }

    public void adjustQuantityBy(final int delta) {
        setQuantity(Optional.ofNullable(getQuantity()).orElse(0L) + delta);
    }

    public void increaseQuantityBy(final int delta) {
        if (delta <= 0L) {
            throw new IllegalArgumentException("non-positive delta: " + delta);
        }
        adjustQuantityBy(delta);
    }

    public void decreaseQuantityBy(final int delta) {
        if (delta <= 0L) {
            throw new IllegalArgumentException("non-positive delta: " + delta);
        }
        adjustQuantityBy(-delta);
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
    private ID id;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @MapsId(_MappedOrderItemId.ATTRIBUTE_NAME_ORDER_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ORDER_ID, nullable = false, insertable = true /* EclipseLink */, updatable = false)
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
