package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = MappedOrderItem.TABLE_NAME)
class OrderItem extends MappedOrderItem<OrderItemId, Order, Product, Shipment> {

    static MappedOrderItemBuilder<?, OrderItem, OrderItemId, Order, Product, Shipment> builder() {
        return new OrderItemBuilder();
    }
    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected OrderItem() {
        super();
    }

    OrderItem(final OrderItemBuilder builder) {
        super(builder);
    }
}
