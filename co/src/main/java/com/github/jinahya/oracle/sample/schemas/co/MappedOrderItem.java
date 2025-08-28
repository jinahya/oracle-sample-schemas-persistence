package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@MappedSuperclass
public class MappedOrderItem<
        ID extends MappedOrderItemId,
        ORDER extends MappedOrder<?, ?, ?>,
        PRODUCT extends MappedProduct,
        SHIPMENT extends MappedShipment<?, ?>
        >
        extends __MappedEntity<ID> {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "ORDER_ITEMS";

    // -------------------------------------------------------------------------------------------------------- ORDER_ID
    public static final String COLUMN_NAME_ORDER_ID = "ORDER_ID";

    public static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    public static final String ATTRIBUTE_NAME_ORDER = "order";

    // ---------------------------------------------------------------------------------------------------- LINE_ITEM_ID
    public static final String COLUMN_NAME_LINE_ITEM_ID = "LINE_ITEM_ID";

    // -------------------------------------------------------------------------------- PRODUCT_ID / productId / product
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    // ------------------------------------------------------------------------------------------------------ UNIT_PRICE
    public static final String COLUMN_NAME_UNIT_PRICE = "UNIT_PRICE";

    public static final int COLUMN_PRECISION_UNIT_PRICE = 10;

    public static final int COLUMN_SCALE_UNIT_PRICE = 2;

    public static final String DECIMAL_MIN_UNIT_PRICE = "00000000.00";

    public static final String DECIMAL_MAX_UNIT_PRICE = "99999999.99";

    // -------------------------------------------------------------------------------------------------------- QUANTITY
    public static final String COLUMN_NAME_QUANTITY = "QUANTITY";

    // ----------------------------------------------------------------------------- SHIPMENT_ID / shipmentId / shipment
    public static final String COLUMN_NAME_SHIPMENT_ID = "SHIPMENT_ID";

    public static final String ATTRIBUTE_SHIPMENT_ID = "shipmentId";

    public static final String ATTRIBUTE_SHIPMENT = "shipment";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrderItem() {
        super();
    }

    protected MappedOrderItem(final MappedOrderItemBuilder<?, ?, ID, ORDER, PRODUCT, SHIPMENT> builder) {
        super(builder);
        this.id = builder.id();
        this.order = builder.order();
        this.productId = builder.productId();
        setProduct(builder.product());
        this.unitPrice = builder.unitPrice();
        this.quantity = builder.quantity();
        this.shipmentId = builder.shipmentId();
        setShipment(builder.shipment());
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
                "id=" + id +
                ",productId=" + productId +
                ",unitPrice=" + unitPrice +
                ",quantity=" + quantity +
                ",shipmentId=" + shipmentId +
                '}';
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation
//    @AssertTrue(message = "shipment.customer should be equal to the order.customer")
    private boolean isSShipmentCustomerValid() {
        return shipment == null
                || order == null
                || Objects.equals(shipment.getCustomer(), order.getCustomer());
    }

    //    @AssertTrue(message = "shipment.store should be equal to the order.store")
    private boolean isShipmentStoreValid() {
        return shipment == null
                || order == null
                || Objects.equals(shipment.getStore(), order.getStore());
    }

    // -------------------------------------------------------------------------------------------------------------- id
    @Nonnull
    public ID getId() {
        return id;
    }

    void setId(@Nonnull final ID id) {
        this.id = id;
    }

    public ID getIdOrElseGet(@Nonnull final Supplier<? extends ID> instantiator) {
        Objects.requireNonNull(instantiator, "instantiator is null");
        return Optional.ofNullable(getId())
                .orElseGet(() -> {
                    setId(Objects.requireNonNull(instantiator.get(), "null supplied from " + instantiator));
                    return getId();
                });
    }

    // ----------------------------------------------------------------------------------------------------------- order
    @Nonnull
    public ORDER getOrder() {
        return order;
    }

    void setOrder(@Nonnull final ORDER order) {
        this.order = order;
    }

    // ------------------------------------------------------------------------------------------------------- productId
    @Nonnull
    Long getProductId() {
        return productId;
    }

    void setProductId(@Nonnull final Long productId) {
        this.productId = productId;
    }

    // --------------------------------------------------------------------------------------------------------- product
    public PRODUCT getProduct() {
        return product;
    }

    void setProduct(final PRODUCT product) {
        this.product = product;
        setProductId(
                Optional.ofNullable(this.product)
                        .map(MappedProduct::getProductId)
                        .orElse(null)
        );
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

    // ------------------------------------------------------------------------------------------------------ shipmentId
    @Nullable
    Long getShipmentId() {
        return shipmentId;
    }

    void setShipmentId(@Nullable final Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    // -------------------------------------------------------------------------------------------------------- shipment
    @Nullable
    public SHIPMENT getShipment() {
        return shipment;
    }

    public void setShipment(@Nullable final SHIPMENT shipment) {
        this.shipment = shipment;
        setShipmentId(
                Optional.ofNullable(shipment)
                        .map(MappedShipment::getShipmentId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @EmbeddedId
    private ID id;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @MapsId(MappedOrderItemId.ATTRIBUTE_NAME_ORDER_ID)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(
            name = COLUMN_NAME_ORDER_ID,
            nullable = false,
            insertable = true /* EclipseLink */,
            updatable = false
    )
    private ORDER order;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = true, updatable = false)
    private Long productId;

    @Nonnull
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = false, updatable = false)
    private PRODUCT product;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @DecimalMax(DECIMAL_MAX_UNIT_PRICE)
    @DecimalMin(DECIMAL_MIN_UNIT_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE,
            nullable = false,
            insertable = true,
            updatable = false,
            precision = COLUMN_PRECISION_UNIT_PRICE,
            scale = COLUMN_SCALE_UNIT_PRICE
    )
    private BigDecimal unitPrice;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY, nullable = false, insertable = true, updatable = true)
    private Long quantity;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_SHIPMENT_ID, nullable = true, insertable = true, updatable = true)
    private Long shipmentId;

    @Nullable
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SHIPMENT_ID, nullable = true, insertable = false, updatable = false)
    private SHIPMENT shipment;

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
