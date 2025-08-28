package com.github.jinahya.oracle.sample.schemas.co;

class OrderItemBuilder extends MappedOrderItemBuilder<
        OrderItemBuilder,
        OrderItem,
        OrderItemId,
        Order,
        Product,
        Shipment> {
    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    OrderItemBuilder() {
        super(OrderItem.class);
    }
}
