package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = _MappedOrderItem.TABLE_NAME)
public class OrderItem extends _MappedOrderItem<OrderItem, OrderItemId, Order, Product, Shipment> {

    protected OrderItem() {
        super();
    }

    // -------------------------------------------------------------------------------------------------------------- id
    public OrderItemId getIdOrElseSetNewAndGet() {
        return getIdOrElseSetSuppliedAndGet(OrderItemId::new);
    }
}
