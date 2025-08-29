package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = OrderItem.TABLE_NAME)
public class OrderItem extends MappedOrderItem<OrderItemId, Order, Product, Shipment> {
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
