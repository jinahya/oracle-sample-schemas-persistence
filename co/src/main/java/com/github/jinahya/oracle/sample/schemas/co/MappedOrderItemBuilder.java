package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;

import java.math.BigDecimal;

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedOrderItemBuilder<
        SELF extends MappedOrderItemBuilder<SELF, ORDER_ITEM, ID, ORDER, PRODUCT, SHIPMENT>,
        ORDER_ITEM extends MappedOrderItem<ID, ORDER, PRODUCT, SHIPMENT>,
        ID extends MappedOrderItemId,
        ORDER extends MappedOrder<?, ?, ORDER_ITEM>,
        PRODUCT extends MappedProduct,
        SHIPMENT extends MappedShipment<?, ?>
        >
        extends __MappedEntityBuilder<SELF, ORDER_ITEM> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedOrderItemBuilder(final Class<ORDER_ITEM> entityClass) {
        super(entityClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    // -------------------------------------------------------------------------------------------------------------- id
    public ID id() {
        return id;
    }

    public SELF id(final ID id) {
        this.id = id;
        return (SELF) this;
    }

    // ----------------------------------------------------------------------------------------------------------- order
    public ORDER order() {
        return order;
    }

    public SELF order(final ORDER order) {
        this.order = order;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- productId
    @Deprecated(forRemoval = true)
    public Long productId() {
        return productId;
    }

    @Deprecated(forRemoval = true)
    public SELF productId(final Long productId) {
        this.productId = productId;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------------- product
    public PRODUCT product() {
        return product;
    }

    public SELF product(final PRODUCT product) {
        this.product = product;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- unitPrice
    public BigDecimal unitPrice() {
        return unitPrice;
    }

    public SELF unitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- quantity
    public Long quantity() {
        return quantity;
    }

    public SELF quantity(final Long quantity) {
        this.quantity = quantity;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------ shipmentId
    @Deprecated(forRemoval = true)
    public Long shipmentId() {
        return shipmentId;
    }

    @Deprecated(forRemoval = true)
    public SELF shipmentId(final Long shipmentId) {
        this.shipmentId = shipmentId;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- shipment
    public SHIPMENT shipment() {
        return shipment;
    }

    public SELF shipment(final SHIPMENT shipment) {
        this.shipment = shipment;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ID id;

    // -----------------------------------------------------------------------------------------------------------------
    private ORDER order;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    private Long productId;

    private PRODUCT product;

    // -----------------------------------------------------------------------------------------------------------------
    private BigDecimal unitPrice;

    private Long quantity;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    private Long shipmentId;

    private SHIPMENT shipment;
}
